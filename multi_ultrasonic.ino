int trigPin1 = 4;
int echoPin1 = 5;

int trigPin2 = 9;
int echoPin2 = 10;

int trigPin3 = 12;
int echoPin3 = 13;


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

  pinMode(echoPin1, INPUT);

  duration1 = pulseIn(echoPin1, HIGH);

  distance1 = (duration1 / 2) / 29.1;

  if (distance1 <= 0) {
    Serial.println("Sensor 1 Out of range");
  }
  else {
    Serial.print ( "Sensor1  ");
    Serial.print ( distance1);
    Serial.println("cm");
  }
  delay(10);
////////////////////////////////////////////////////
  long duration2, distance2;
  digitalWrite(trigPin2, LOW);  // Added this line
  delayMicroseconds(5); // Added this line
  digitalWrite(trigPin2, HIGH);
  delayMicroseconds(10); // Added this line
  digitalWrite(trigPin2, LOW);

  pinMode(echoPin2, INPUT);

  duration2 = pulseIn(echoPin2, HIGH);

  distance2 = (duration2 / 2) / 29.1;

  if (distance2 <= 0) {
    Serial.println("Sensor 2 Out of range");
  }
  else {
    Serial.print ( "Sensor2  ");
    Serial.print ( distance2);
    Serial.println("cm");
  }
  delay(10);
///////////////////////////////////////////////////
  long duration3, distance3;
  digitalWrite(trigPin3, LOW);  // Added this line
  delayMicroseconds(5); // Added this line
  digitalWrite(trigPin3, HIGH);
  delayMicroseconds(10); // Added this line
  digitalWrite(trigPin3, LOW);

  pinMode(echoPin3, INPUT);

  duration3 = pulseIn(echoPin3, HIGH);

  distance3 = (duration3 / 2) / 29.1;

  if (distance3 <= 0) {
    Serial.println("Sensor 3 Out of range");
  }
  else {
    Serial.print ( "Sensor3  ");
    Serial.print ( distance3);
    Serial.println("cm");
  }
  delay(10);
///////////////////////////////////////////////////
}
