void setup() {
  // put your setup code here, to run once:

}

void loop() {

  int light = analogRead(A0);
  int hertz = map(light, 0, 1023, 31, 4978);

  tone(8, hertz, 100);
  delay(100);
  noTone(8);
  delay(200);
  /*
  int light = analogRead(A0);
  int ledLight = map(light, 0, 1023, 255, 0); // 비율에 따라 0~1023 값이 255~0으로 매핑됌
  analogWrite(9, ledLight);
*/
}
