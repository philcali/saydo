package com.github.philcali

import scala.io.Source.{fromFile => open}
import scalendar._

object App {
  def main(args: Array[String]) {
    val now = Scalendar.now

    val duration = Scalendar.beginDay(now) to (Scalendar.endWeek(now))

    val handler = args.contains("-i") match {
      case true => 
        println("Input todo's for the day ...")
        println("Multiple entries are delimited by a |")
        (d: Duration) => {
          println("For %s" format(d.day.name))
          Console.flush
          (d.day.name, Console.readLine("> ").split("\\|"))
        }
      case false => (d: Duration) => {
        (d.day.name, Array[String](""))
      }
    }

    val contents = duration by (1 day) map handler

    // Printout this junks (to be used as a cmd-line app) 
    println("## Weekly Todo's")
    println("### %s" format(now.calendarWeek)) 
    println
    contents foreach { what =>
      val (name, todos) = what
      println("- **%s**" format(name))
      println(todos.map("    - " + _.trim).mkString("\n"))
      println
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
