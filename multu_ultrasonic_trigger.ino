int trigPin1 = 4;
int echoPin1 = 5;

int trigPin2 = 9;
int echoPin2 = 10;

int trigPin3 = 12;
int echoPin3 = 13;

long trigger1 = 0;
long trigger2 = 0;
long trigger3 = 0;

long trigger_1 = 0;
long trigger_2 = 0;
long trigger_3 = 0;

void setup() {
  Serial.begin (9600);
  pinMode(trigPin1, OUTPUT);
  pinMode(echoPin1, INPUT);
  pinMode(trigPin2, OUTPUT);
  pinMode(echoPin2, INPUT);
  pinMode(trigPin3, OUTPUT);
  pinMode(echoPin3, INPUT);

}

void loop() {

  /////////////////////////////////////////////////////
  long duration1, distance1;
  digitalWrite(trigPin1, LOW);  // Added this line
  delayMicroseconds(5); // Added this line
  digitalWrite(trigPin1, HIGH);
  delayMicroseconds(10); // Added this line
  digitalWrite(trigPin1, LOW);

  duration1 = pulseIn(echoPin1, HIGH);

  distance1 = (duration1 / 2) / 29.1;

  if (distance1 > 0 & distance1 < 10) {
    if (trigger_3 == 1 & trigger_2 == 1) {
      trigger_1 = 1;
    } else {
      trigger1 = 1;
    }
    Serial.print ( "Sensor1  ");
    Serial.print ( distance1);
    Serial.println("cm");
  }
  delay(100);
  ////////////////////////////////////////////////////
  long duration2, distance2;
  digitalWrite(trigPin2, LOW);  // Added this line
  delayMicroseconds(5); // Added this line
  digitalWrite(trigPin2, HIGH);
  delayMicroseconds(10); // Added this line
  digitalWrite(trigPin2, LOW);

  duration2 = pulseIn(echoPin2, HIGH);

  distance2 = (duration2 / 2) / 29.1;

  if (distance2 > 0 & distance2 < 10) {
    if (trigger_3 == 1) {
      trigger_2 = 1;
    } else {
      trigger2 = 1;
    }
    Serial.print ( "Sensor2  ");
    Serial.print ( distance2);
    Serial.println("cm");
  }
  delay(100);
  ///////////////////////////////////////////////////
  long duration3, distance3;
  digitalWrite(trigPin3, LOW);  // Added this line
  delayMicroseconds(5); // Added this line
  digitalWrite(trigPin3, HIGH);
  delayMicroseconds(10); // Added this line
  digitalWrite(trigPin3, LOW);

  duration3 = pulseIn(echoPin3, HIGH);

  distance3 = (duration3 / 2) / 29.1;

  if (distance3 > 0 & distance3 < 10) {
    if (trigger1 == 1 & trigger2 == 1) {
      trigger3 = 1;
    }
    else {
      trigger_3 = 1;
    }
    Serial.print ( "Sensor3  ");
    Serial.print ( distance3);
    Serial.println("cm");

  }
  delay(100);
  ///////////////////////////////////////////////////

  if (trigger1 == 1) {
    if (trigger2 == 1) {
      if (trigger3 == 1) {
        Serial.println("Passenger has Entered");
        trigger1 = 0;
        trigger2 = 0;
        trigger3 = 0;
      }
    }
  }
  else if (trigger_3 == 1) {
    if (trigger_2 == 1) {
      if (trigger_1 == 1) {
        Serial.println("Passenger has Exited");
        trigger_3 = 0;
        trigger_2 = 0;
        trigger_1 = 0;
      }
    }
  }
}
