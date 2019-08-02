void setup() {
  pinMode(12, OUTPUT); //LED
  pinMode(8, INPUT); //BUTTON

}

void loop() {
 int value = digitalRead(8);
 if(value == HIGH) { //BUTTON 눌림
  digitalWrite(12, HIGH);
 }
 else {
  digitalWrite(12, LOW);
 }
}
