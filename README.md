# Saydo

Say it needs to be done... yeah.

Stupid little commandline app that templates out my markdown weekly todo list.

No frills or anything. This was thrown together rather quickly, I'm not even sure that
I'll use it.

## Usage

    saydo

    #Unix world
    saydo >> todos.md

Interactive mode allows you to type in the stuffs for the week.

    saydo -i

Append to a file, in a non Unix world

    saydo -a todos.md
    # Even works in interactive mode
    saydo -i -a todos.md

Append to a file, but only replace the *Weekly* boilerplate stuff.

    saydo -i -r -a todos.md

## Installation

    cs philcali/saydo

## License

Knock yourself out.

Below is example output from it:

## Weekly Todo's
### 04/10/2011 00:00:00 - 04/16/2011 23:59:59

- **Sunday**
    - Go to church
    - sleep

- **Monday**
    - Email some people
    - eat a lot

- **Tuesday**
    - Eat less than I did on Monday

- **Wednesday**
    - Eat way more than Tuesday and Monday combined

- **Thursday**
    - Slow down a little...
    - Feel my pants Expanding

- **Friday**
    - Eat like a champ

- **Saturday**
    - Eat like a king
    - Do it all over again

