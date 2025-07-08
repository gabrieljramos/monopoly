# Java Monopoly Game

This project is a Java-based implementation of the classic board game Monopoly, featuring a graphical user interface built with JavaFX. Players can buy, sell, and trade properties, manage their finances, and interact with various board squares, aiming to be the last player not to go bankrupt.

## Project Overview

The game simulates the core mechanics of Monopoly. It includes a game board, player tokens, dice rolling, property ownership, rent collection, and special event cards. The application is structured with a clear separation of game logic and the graphical user interface (GUI).

### Core Components

* **`draw.java`**: The main entry point of the application, responsible for rendering the game board, player tokens, and all UI elements using JavaFX. It handles the main game window, menus, and visual updates.
* **`run.java` (inferred)**: Contains the main game loop, orchestrating the flow of the game, managing player turns, and processing game events.
* **`board.java` (inferred)**: Represents the game board itself, holding a collection of all the squares.
* **`player.java` (inferred)**: Represents a player in the game, managing their position, wallet, and portfolio of owned properties.
* **`dice.java`**: Simulates the rolling of two six-sided dice to determine player movement.
* **`bank.java`**: Manages all financial transactions, including property sales, rent payments, salary collection, and trades between players.
* **`wallet.java`**: A simple class to manage a player's money, including paying and receiving funds and checking for bankruptcy.
* **`portfolio.java` (inferred)**: Manages the collection of properties and stocks owned by a player.

### Board Squares

The game board is composed of different types of squares, implemented using an inheritance structure:

* **`squares.java`**: An abstract base class for all types of squares on the board.
* **`property.java` (inferred)**: Represents a deed that can be bought, improved with houses/hotels, and mortgaged.
* **`stocks.java`**: Represents company stocks that can be purchased by players. Rent is paid based on the number of related stocks the owner has.
* **`special.java`**: Represents non-property squares with special actions, such as:
  * **Go to Jail**: Sends the player to the Jail square.
  * **Holiday/Jail**: Causes the player to miss turns.
  * **Taxes**: Requires the player to pay a fixed amount to the bank.
  * **News/Chance (`cartas.java`)**: Draws a card with a random event, which can affect the player's money, position, or properties.

### Features

* **Graphical User Interface**: A visual representation of the Monopoly board, players, and game information.
* **Player Management**: Supports 1 to 4 players.
* **Property Transactions**: Players can buy properties from the bank or trade with other players.
* **Rent and Upgrades**: Rent is collected when opponents land on owned properties. Properties can be improved to increase their rent value.
* **Special Events**: "News" cards introduce random events that add an element of chance.
* **Save/Load Game**: Functionality to save the current game state and continue later (inferred from `save.java`).

## How to Compile and Run

This project uses JavaFX for its graphical interface. You will need to have a JDK with JavaFX included or have the JavaFX SDK configured in your development environment.

### Prerequisites

* Java Development Kit (JDK) 11 or higher.
* JavaFX SDK.
