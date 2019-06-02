# The best (almost) game engine of the 2019

This is my contribute to the text gaming: the engine is very general and can be customized for many stories.
In this moment not all the functions are implemented, feel free to add your contribute ... :-)

## Instructions

Run the class **GameStart** to start. The system will show to you a prompt, you need to start creating a player with
the command **create <name of the player>**. From this moment you will have all the commands available:

- **create <name of the player>** create a new player and save it
- **load <name of the player>** load a saved player (it replace the current player without saving it)
- **save** save the current player
- **north**, **south**, **east**, **west** move the player in a direction
- **look** show the current map
- **kill <name of the monster>** fight with a monster
- **exit** exit from the beautiful game and return to the gray reality

## Extensions

There are a lot of extension possible:

- it's possible to add layers to the map (extends the class Position)
- it's possible to add elements like **elevator** or **door** (new type of WorldItem and GameCommand to interact)
- it's possible to add shortcut for the commands (**n** for **north**, ...)
- the system currently haven't an **help** command, it should be based on the GameCommand extension
- currently a monster never die, it's possible to remove monsters killed from a specific player
- extending the PlayerRepository and using a telnet server, it's possible to create a multi player online game
- the design of the world can be created using scripting or an *edit* mode

## Tech details

- I used Java 1.8 to create the engine
- I used gradle to manage the build
- I used spock to create the tests (usually in TDD mode)
- I worked mainly in the engine, the graphics isn't perfect :-)

