# Tennis Stringing Kiosk Management System

**Author: Jake Gertz**

A comprehensive Java Swing desktop application designed to manage the entire workflow of a tennis stringing kiosk. This system provides distinct interfaces for players (customers), stringers (employees), and administrators (managers) to handle job tracking, inventory, and user management.

## Project Status & Roadmap

This project is currently a work in progress. The core logic and object model are established, but there are several exciting goals for the future.

* **Immediate Goal: Database**
  The next development step is to create a way to store the state of the TennisKiosk so that when the Kiosk is closed all information **is** not lost. This **is** the top priority goal for development moving forward.

* **Immediate Goal: Working Demo**
  Another **short-term** development goal is to create a working demo version (`TennisKioskGUI.java`) that launches the application with pre-loaded stringers, players, and strings. This will allow for full testing of the user workflow from start to finish.

* **Next Goal: GUI Enhancements**
  Once the demo is functional, a major goal is to refactor the GUI to make it look nicer and more modern. This involves improving component layouts, adding icons, and creating a more polished, user-friendly experience.

* **Long-Term Goal: Arduino Integration**
  The eventual goal for this system is to integrate with an Arduino microcontroller. This would allow the Java application to control physical (lock/unlock) cabinets, creating a secure system for players to drop off rackets or pick up finished jobs at any time.
  
## Table of Contents

* [About The Project](#about-the-project)
* [Core Features](#core-features)
  * [Player (Customer) Portal](#player-customer-portal)
  * [Stringer (Employee) Portal](#stringer-employee-portal)
  * [Admin (Manager) Portal](#admin-manager-portal)
* [Core Object Model](#core-object-model)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [How to Run](#how-to-run)

## About The Project

This project solves the real-world problem of managing racket stringing requests at a tennis pro shop or kiosk. It digitizes the process, replacing paper-based tracking with a reliable, multi-user system.

The application is built entirely in **Java** using the **Java Swing** library for the graphical user interface. It follows an object-oriented design to model the relationships between the kiosk, stringers, players, and their rackets.

## Core Features

The application is split into three user roles, each with a dedicated set of features.

### Player (Customer) Portal

* **View Rackets:** See a visual grid of all personal rackets (`SeeRackets.java`).
* **Browse Inventory:** See a list of all available strings currently in stock at the kiosk (`SeeAvailableStrings.java`).
* **Submit Rackets:** Drop off a racket for stringing (requires selecting a racket and a string).
* **Pick Up Rackets:** View all completed rackets that are ready for pickup and mark them as retrieved (`PickUpRacket.java`).

### Stringer (Employee) Portal

* **View Job Queue:** See a dashboard of all rackets needing to be strung, organized by the player who owns them (`SeeStringerRackets.java`).
* **Complete Jobs:** Mark rackets as "strung," which automatically moves them from the job queue to the owner's "ready for pickup" list (`StringRackets.java`).
* **Manage Client Roster:**
  * Add new or existing players to a personal client list (`AddPlayerToStringer.java`).
  * Remove players from the roster, with safety checks to prevent removal if jobs are pending (`RemovePlayer.java`).

### Admin (Manager) Portal

* **Stringer Management:**
  * Add new stringers to the kiosk (`AddStringer.java`).
  * Update any stringer's name or User ID.
  * Remove stringers, with a safety check that prevents deletion if they have outstanding jobs (`UpdateStringerInfo.java`).
* **Inventory Management:**
  * Add new string types and their initial stock levels to the kiosk inventory (`AddStringToKiosk.java`).
  * (Future) Update stock levels or remove strings from the kiosk.
* **Kiosk Settings:**
  * Securely update the global Admin ID (`UpdateAdminInfo.java`).
  * **Factory Reset:** A feature with a multi-step confirmation to wipe all stringers, strings, and data from the kiosk, restoring it to default values.

## Core Object Model

The logic of the application is built on these core Java classes:

* **`TennisKiosk.java`**: The central "hub" class. It holds the master lists of all `TennisStringer` objects and all `TennisString` (inventory) objects.
* **`TennisStringer.java`**: Represents an employee. Each stringer has their own `LinkedList` of `TennisPlayer`s they are responsible for.
* **`TennisPlayer.java`**: Represents a customer. Each player has two key `LinkedList`s: `racketsToString` and `racketsToPickUp`.
* **`TennisRacket.java`**: The main object that gets passed between lists. It contains information about its model and the `TennisString` it uses.
* **`TennisString.java`**: Represents a type of string in the inventory, with a `TennisStringBrand`, name, and stock level.

## Getting Started

### Prerequisites

* [Java JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or newer (Java 8+ should also be compatible).

### How to Run

1. Clone this repository (or download the source code).
2. Open your terminal or command prompt that is using git bash
3. Use the command
  ```bash
    ./gradlew run
  ```