void setup() {
  pinMode(2, OUTPUT);
  pinMode(3, INPUT); 
}

void loop() { //무한 반복문 x, while(1)은 main문에 있고 while 안에서 loop를 계속해서 호출
 

   //trriger
   digitalWrite(2, HIGH);
   delayMicroseconds(10);
   digitalWrite(2, LOW);

   long duration = pulseIn(3, HIGH); //HIGH인 동안의 시간을 읽어서 저장

   if(duration == 0){
      return; //반복문에서의 continue 같은 역할
   }

   long distance = duration / 58.2;

   tone(8, 1000, 20);
   delay(20);
   noTone(8);

   if(distance < 50)
     delay(20 * distance);
   else delay(1000);
   
   analogWrite(11, 0);
   analogWrite(10, 0);
   analogWrite(9, 0);

   if(distance < 10){
    analogWrite(11, 255);
   } else if(distance < 20) {
    analogWrite(10, 255);
   } else if(distance < 30) {
    analogWrite(9, 255);
   }

  
}
