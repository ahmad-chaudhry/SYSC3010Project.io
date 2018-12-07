//define Pin Numbers
int echoPin1 = 4; // Sensor 1 echoPin
int trigPin1 = 5; // Sensor 1 trigPin
int echoPin2 = 9; // Sensor 2 echoPin
int trigPin2 = 10; // Sensor 2 trigPin
int echoPin3 = 12; // Sensor 3 echoPin
int trigPin3 = 13; // Sensor 3 trigPin


//Initialize trigger variables
int trigger1 = 0;
int trigger2 = 0;
int trigger3 = 0;
int trigger_1 = 0;
int trigger_2 = 0;
int trigger_3 = 0;

//Setup for Program
void setup() {
  Serial.begin (9600); // Serial Communication baudrate
  pinMode(trigPin1, OUTPUT); // set trigPin1 as an output Pin
  pinMode(echoPin1, INPUT); // set echoPin1 as an input Pin
  pinMode(trigPin2, OUTPUT); // set trigPin2 as an output Pin
  pinMode(echoPin2, INPUT); // set echoPin2 as an input Pin
  pinMode(trigPin3, OUTPUT); // set trigPin3 as an output Pin
  pinMode(echoPin3, INPUT); // set echoPin3 as an input Pin
}

//Loop for program
void loop() {
  /////////////////////////////////////////////////////
  long duration1, distance1; //Initialize duration and distance
  digitalWrite(trigPin1, LOW);  // Clear trig pin
  delayMicroseconds(5); // delay 5 microseconds
  digitalWrite(trigPin1, HIGH); // set trig pin
  delayMicroseconds(10); // delay 10 microseconds
  digitalWrite(trigPin1, LOW); // clear trig pin
  
  duration1 = pulseIn(echoPin1, HIGH); // get time required for echo pin to receive wave

  // calculate distance, speed of sound is 0.0343 cm/us, divide by 2 becuase wave going and coming back
  distance1 = (duration1 / 2) / 29.1;

  //if distance less than 0, trig never sent out wave, check wiring, potentially faulty trig
  if (distance1 <= 0) {
    Serial.println(4);
  }
  //Sensor triggers only if within the distance of 0-15cm
  if (distance1 > 0 & distance1 < 15) {
    //if passenger exiting, set trigger_1 to 1
    if (trigger_3 == 1 & trigger2 == 1) {
      trigger_1 = 1;
      //else passenger is entering, set trigger1 to 1
    } else {
      trigger1 = 1;
    }
  }
  //delay 50 milliseconds
  delay(50);
  ////////////////////////////////////////////////////
  long duration2, distance2; //Initialize duration and distance
  digitalWrite(trigPin2, LOW);  // Clear trig pin
  delayMicroseconds(5); // delay 5 microseconds
  digitalWrite(trigPin2, HIGH); // set trig pin
  delayMicroseconds(10); // delay 10 microseconds
  digitalWrite(trigPin2, LOW); // clear trig pin

  duration2 = pulseIn(echoPin2, HIGH); // get time required for echo pin to receive wave

  // calculate distance, speed of sound is 0.0343 cm/us, divide by 2 becuase wave going and coming back
  distance2 = (duration2 / 2) / 29.1;

  //if distance less than 0, trig never sent out wave, check wiring, potentially faulty trig
  if (distance2 <= 0) {
    Serial.println(5);
  }
  //Sensor triggers only if within the distance of 0-15cm
  if (distance2 > 0 & distance2 < 15) {
    //if passenger has triggered a enter or exit sequence then passenger can trigger sensor 2
    if (trigger1 != 0 | trigger_3 != 0 ) {
      trigger2 = 1;
    }
  }
  //delay 50 milliseconds
  delay(50);
  ///////////////////////////////////////////////////
  long duration3, distance3; //Initialize duration and distance
  digitalWrite(trigPin3, LOW); //Clear trig pin
  delayMicroseconds(5); // delay 5 microseconds
  digitalWrite(trigPin3, HIGH); // Set trig pin
  delayMicroseconds(10); // delay 10 microseconds
  digitalWrite(trigPin3, LOW); // Clear trig pin

  duration3 = pulseIn(echoPin3, HIGH); // get time required for echo pin to receive wave

  // calculate distance, speed of sound is 0.0343 cm/us, divide by 2 becuase wave going and coming back
  distance3 = (duration3 / 2) / 29.1;

  //if distance less than 0, trig never sent out wave, check wiring, potentially faulty trig
  if (distance3 <= 0) {
    Serial.println(6);
  }
  //Sensor triggers only if within the distance of 0-15cm
  if (distance3 > 0 & distance3 < 15) {
    //if passenger has triggered a enter sequence set trigger3 to 1
    if (trigger1 == 1 & trigger2 == 1) {
      trigger3 = 1;
    //else passenger has triggered a exit sequence set trigger_3 to 1
    } else {
      trigger_3 = 1;
    }
  }
  //delay for 50 milliseconds
  delay(50);
  ///////////////////////////////////////////////////

  //check if passengers have triggered a enter/exit sequence (first trigger is passenger entering)
  if (trigger1 == 1) {
    if (trigger_3 == 1) {
      if (trigger2 == 1) {
        //send enter/exit seqeuence data to RPI for processing
        Serial.println(3);
        //reset triggers
        trigger1 = 0;
        trigger_3 = 0;
        trigger2 = 0;
        //delay for 1 second
        delay(1000);
      }
    }
    //else if passenger has triggered a enter sequence 
    else if (trigger2 == 1) {
      if (trigger3 == 1) {
        //send enter sequence data to RPI for processing
        Serial.println(1);
        //reset triggers
        trigger1 = 0;
        trigger2 = 0;
        trigger3 = 0;
        //delay for 1 second
        delay(1000);
      }
    }
  }
  //check if passengers have triggered a exit/enter sequence (first trigger is passenger exiting)
  else if (trigger_3 == 1) {
    if (trigger1 == 1) {
      if (trigger2 == 1) {
        //send exit/enter sequence data to RPI for processing
        Serial.println(3);
        //reset triggers
        trigger1 = 0;
        trigger_3 = 0;
        trigger2 = 0;
        //delay for 1 second
        delay(1000);
      }
    }
    //check if passenger has triggered a exit sequence
    else if (trigger2 == 1) {
      if (trigger_1 == 1) {
        //send exit sequence data to RPI for processing
        Serial.println(2);
        //reset triggers
        trigger_3 = 0;
        trigger_2 = 0;
        trigger_1 = 0;
        //delay for 1 second
        delay(1000);
      }
    }
  }
}
