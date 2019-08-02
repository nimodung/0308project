
#include <Servo.h>

Servo myservo;

void setup() {
  myservo.attach(9);

}

void loop() {
  int val = analogRead(A0);
  int radian = map(val, 0, 1023, 0, 120);

  myservo.write(radian);
  delay(15);
}
