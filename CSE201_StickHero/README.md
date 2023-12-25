# STICK HERO
# Advanced Programming Project


**Group Members:**  
**1.Ananya Garg - 2022068**   
**2.Swara Parekh - 2022524**

## Assumptions
The user cannot retrieve but the best score of every game is being stored.

## GitHub
Link to the github repository - https://github.com/ananya-garg-04/CSE201_StickHero.git

## Run the file
It can be cloned from the github link.
The code is available in ***CSE201_StickHero/SticHero/src/main/java/com/example/stickhero***.
The main file is GameScreen.java which will launch the game. 


## Functionalities
Increasing stick
Horizontal stick
Hero walk
Shift Pillars
Save game
Load Game
Sound
Go to Home page
Current and Best Score
Progress saved and written on file


## Implementation

### Design Patterns used
Singleton:
Used in Character class and Stick class
Composite:
Used in Platform class

### Threads
Threds have been used for audio and media

### IO Stream
Used in writing the best Score in file

### JUnit Testing
Necessary Junit tests have also been applied

## How to play?
Pop-up of the first Screen with a start button will be displayed on running the code.<br>
User clicks on the PLAY button and the game starts.<br>
Stick starts to extend on mouse-click and retracts and lands on the platform on mouse-click release.
<br>If the stick lands on the platform, the hero moves to the next platform and the game continues in a loop.
<br>But if the stick does not land on the stick or if it exceeds the platform width then the hero falls down and the game ends.
<br>The final screen with current score and the best score of the user will be displayed.


