# SYSC3310Project
SYSC3310 Project Repository
Fall 2018

Hardware Set-Up:

Connect ultrasonic sensors to arduino using schematics provided (Figure x)

Connect arduino to Pi using arduino cable

Ras-Pi & Software Set-Up:

Add jssc package to blue-jay 

Create project and import jssc package

Go to GITHUB and download 

For Sender (Bus): Bus#.java, and Arduino_RPI_Comm_Final.ino

For Receiver (Bus Center):  BusCenter.java.

From the receiver initialize the database.

Android App:

Android Studio: Minimum requirement of the application was to display the capacity zone of the bus. The Progress Bar, Notification Push,Text/Progress bar changing colors and Applications Icon were all added to build a good looking and Practical application.

Main activity.Java is where all of the processing for input and outtakes place. The APi.JAVA file is responsible for using Retrofit Lib and connected the application to the Database and calling the PHP file which fetches JSon data.

Android studio libraries were used for including a Progress bar, Sending Notification to notification bar and using RetroFit.

The application is responsible to checks if user's input is a supported bus number else the user is returned an error and user can input a correct bus number.

Depending on the Bus's Current Capacity zone, the process bar's color and length changes for visual aid and a notification is also sent to the user if the bus is in heavy traffic zone.

Running the System:

Run the arduino code from the Bus Raspberry Pi (Arduino_RPI_Comm_Final.ino)

Run the Java code (Bus#.java and BusCenter.java)

(At any time, the app could be opened but until passengers are added to the busses, it shows green)

Add passengers (add at-least 3 to move to yellow zone) on the bus, search that bus in the app, and a safety zone of yellow should appear.


