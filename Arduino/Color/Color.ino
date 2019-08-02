
int r = 0, g = 0, b = 0;

void setup() {
  pinMode(4, INPUT);
  pinMode(3, INPUT);
  pinMode(2, INPUT);

  randomSeed(analogRead(A0)); //analogRead : 0 ~ 255 // A0 전압이 읽혀짐 // ADC // 아무것도 연결 안되어있으니까 잡음값 들어옴
  
}

void loop() {
  if(digitalRead(4) == HIGH){
      // 랜덤
    analogWrite(11, random(256)); //0~255까지 256가지의 값
    analogWrite(10, random(256));
    analogWrite(9, random(256));
  } 



//  delay(1000);
  // 버튼으로 삼색 LED값 증가시키기 
  /*
  if(digitalRead(4) == HIGH) {
    ++r;
    if(r > 255) r = 0;
        
  }
  if(digitalRead(3) == HIGH) {
    ++g;
    if(g > 255) g = 0;
  }
  if(digitalRead(2) == HIGH) {
    ++b;
    if(b > 255) b = 0;
  }

  analogWrite(11, r);
  analogWrite(10, g);
  analogWrite(9, b);

  delay(10);
  */

  
}
