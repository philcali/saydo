package com.github.philcali

import scala.io.Source.{fromFile => open}
import scalendar._

object App {
  class BooleanExpression(origin: Boolean) {
    def and (expr: => Boolean) = origin && expr
    def or (expr: => Boolean) = origin || expr
  }

  implicit def booleanExpression(any: Boolean) =
    new BooleanExpression(any)

  def main(args: Array[String]) {
    val now = Scalendar.now

    val duration = Scalendar.beginDay(now) to (Scalendar.endWeek(now))

    // Header to split on
    val header = "## Weekly Todo's"

    // Append the data to an existing file
    val writer = args.contains("-a") match {
      case true => val ix = args.indexOf("-a")
        val file = try {
          val next = args(ix + 1)
          // invalid argument
          if(next.contains("-"))
            throw new IllegalArgumentException("Whatever")
          new java.io.File(next)
        } catch {
          case _ => new java.io.File("todos.md")
        }

        // Replace the junks dawg
        if(args.contains("-r") and file.exists) {
          val oldcontents = open(file).getLines.mkString("\n")
          val w = new java.io.FileWriter(file)
          w.write(oldcontents.split(header)(0))
          w.close
        }

        new java.io.PrintStream(new java.io.FileOutputStream(file, true))
      case false => Console.out
    }

    // Interactive mode allows for interactivity
    val handler = args.contains("-i") match {
      case true => 
        println("Input todo's for the day ...")
        println("Multiple entries are delimited by a |")
        (d: Duration) => {
          println("For %s" format(d.day.name))
          (d.day.name, Console.readLine("> ").split("\\|"))
        }
      case false => (d: Duration) => {
        (d.day.name, Array[String](""))
      }
    }

    // Map handler with days
    val contents = duration by (1 day) map handler

    // Printout this junks (to be used as a cmd-line app) 
    writer.println(header)
    writer.println("### %s" format(now.calendarWeek)) 
    writer.println
    contents foreach { what =>
      val (name, todos) = what
      writer.println("- **%s**" format(name))
      writer.println(todos.map("    - " + _.trim).mkString("\n"))
      writer.println
    }
  }
}

class App extends xsbti.AppMain {
  def run(config: xsbti.AppConfiguration) = {
    App.main(config.arguments)
    Exit(0)
  }
}
case class Exit(val code: Int) extends xsbti.Exit
