void setup() {

  pinMode(9, OUTPUT);
  
}

void loop() {

/* led 불켰다 껐다
  digitalWrite(12, HIGH);
  delay(500);
  digitalWrite(12, LOW);
  delay(500);
 */

 for(int i = 0; i < 50; i++) {
  analogWrite(9, i);
  delay(10);
 }

 for(int i = 50; i > -1; i--) {
  analogWrite(9, i);
  delay(10);
 }
}
