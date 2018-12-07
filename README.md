# SYSC3310Project
SYSC3310 Project Repository

Hardware Set-Up:

Connect ultrasonic sensors to arduino using schematics provided (Figure x)

Connect arduino to Pi using arduino cable


Ras-Pi & Software Set-Up:


Add jssc package to blue-jay 

Create project and import jssc package

Go to GITHUB and download 

For Sender (Bus): Bus#.java, and Arduino_RPI_Comm_Final.ino

For Receiver (Bus Center):  BusCenter.java, getData.php

From the receiver initialize the database.

Download Apache2, PhpMyAdmin, and MySQL onto the Pi

Paste getData.php file in var/WWW/html folder in Bus Center RPI 

Enter: https://(IP_ADDRESS)/phpmyadmin into the browser in the RPI

Inside phpMyAdmin create new database busmonitoringsystem

Inside the database create a new table names details2 with required fields


Android App: 


Enter the database’s IP address in the android app code (API.java file).

The phone must be connected to the same wireless connection as the database.

Launch the application,Enter desired bus number and hit get data button

Running the System:


Run the arduino code from the Bus Raspberry Pi (Arduino_RPI_Comm_Final.ino)

Run the Java code (Bus#.java and BusCenter.java)

(At any time, the app could be opened but until passengers are added to the busses, it shows green)

Add passengers (add at-least 3 to move to yellow zone) on the bus, search that bus in the app, and a safety zone of yellow should appear.


