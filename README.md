Interstellar Empires
===================

Open source massive-scaled continous multiplayer Real-Time-Strategy game, powered by Java 8, Libgdx and Kryonet. It is open source, free as in speech to hack with, play with and mod for.


## About this Game

The game is an MMO Real Time Strategy game where players take control of a colony in deep space, take action and make decisions for their people to advance their colony and extend their empire to the stars.
To read more about gameplay concepts and design ideas, head over to the wiki.


## How to build

This project is built on Java 8 and Gradle! It is recommended to checkout the stable `master` branch.

```console
$ git clone https://github.com/tr-lonelyrobot/interstellarempires.git
```

There are several gradle targets to choose from. To list them all use `gradle tasks`. To build everything, just run `gradle build`.

NOTE: This repository doesn't come with a gradle wrapper. So make sure you have it installed on your system! Java 8 is also required.

To import this project into Eclipse we recommend [this plugin](https://github.com/spring-projects/eclipse-integration-gradle#installing-gradle-tooling-from-update-site).

You will also need to install the Project Lombok Jar that you can download [here](https://projectlombok.org/download.html).


When running the Interstellar Empires desktop client from the commandline you need to provide at least one flag: either `--fullscreen` or `--nofullscreen` needs to be set, otherwise the client will throw an error.


## How to play

Playing is currently only possible with self-hosted servers. You can build the core server components with the gradle build tasks included in this repository. Make sure to check out the default server configurations (for Ergosphere, the main game server) to setup your own game world. (More information will follow).


## How to contribute

You can always lurk in the Interstellar Empires IRC channel on freenode: #interstellarempires.
Otherwise, there are always open issues that need closing. And features from the roadmap can be submitted via pull requests.


## Gameplay

Following is a short summary of the gameplay stages a player goes through when joining the universe of Interstellar Empires.

### Stage I
A new player spawns in the world and takes control of a single colony with their mothership orbiting around it. They now have 2 weeks of immunity from other players. In that time they discover what the game is about, how to do certain things, manage planetary interactions and even spread to different celestial bodies (planets, moons, asteroids and asteroid belts, etc.) in their starting solar system.
The game is being made interesting by a number pirate attacks that originate from an outpost in the system.
Stage I ends either when the player successfully defeated their pirate base and got ultimate control over their star system or after a 2 week counter has run up.
After that, Stage II begins.

### Stage II
After Stage I the player is thrown into the world of PvP where players fight massive wars over territories to expand their empires. Because old players would dominate the new there is a limit on how many game points a player needs to be attackable by someone else. A player will always be able to attack upwards but only be able to attack players that have at least ⅓ the game points of themselves. This way in Stage II a lot of skirmish wars start to happen. Players are usually by themselves, plotting strategies and expanding their empires.
They start to create diplomatic networks or turn into ruthless dictators that burn everything in their way.
There is no exact transition between Stage II and Stage III

### Stage III
At some point players will reach a state where they start joining or creating alliances with other players. Alliances have similar rules to bullying the weak so can not be used to drive new players out of the game and be easy prey.
With time alliances can either merge or fight terrible wars with chemical, biological and drone warfare.
This is the last stage of the game that either lasts until the entire galaxy is dominated by a single player (very unlikely) or a player is completely destroyed (slightly more likely).

Giving up and stopping to play the game is not an option! We will find you! Wherever you are, we will find you. And we wills trap you to a chair and force you play this game.
We will find you...!