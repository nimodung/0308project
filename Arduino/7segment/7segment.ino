int digit = 0;
byte digits[10][7] = 
{
  {0, 0, 0, 0, 0, 0, 1}, //0
  {1, 0, 0, 1, 1, 1, 1}, //1
  {0, 0, 1, 0, 0, 1, 0}, //2
  {0, 0, 0, 0, 1, 1, 0}, //3
  {1, 0, 0, 1, 1, 0, 0}, //4
  {0, 1, 0, 0, 1, 0, 0}, //5
  {0, 1, 0, 0, 0, 0, 0}, //6
  {0, 0, 0, 1, 1, 1, 1}, //7
  {0, 0, 0, 0, 0, 0, 0}, //8
  {0, 0, 0, 0, 1, 0, 0} //9
};

void setup() {
  randomSeed(analogRead(A0));
  pinMode(10, INPUT);
  pinMode(11, INPUT);
  pinMode(12, INPUT);
  
  for (int i = 2; i < 9; i++){
    pinMode(i, OUTPUT);  
  }
  digitalWrite(9, HIGH);
}

void loop() {
    
    if(digitalRead(11) == HIGH) {
      ++digit;
      if(digit > 9) {
        digit = 9;
      }
    }
    if(digitalRead(12) == HIGH) {
      --digit;
      if(digit < 0) {
        digit = 0;
      }
    }
    digit = random(10);
    displayDigit(digit);
    delay(500);
  
}


void displayDigit(int num){
  int pin = 2;
  for(int i = 0 ;i< 7; i++){
    digitalWrite(pin + i, digits[num][i]);
  }
}
