/*
 * Timer.c
 *
 * Created: 2019-04-10 오전 11:04:02
 *  Author: user
 */ 

#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>
#include <avr/interrupt.h> // interrupt 사용하기 위함

#include "FND4digit.h"
#include "Timer.h"



//FND4digit
extern char FND4digit_digit[4];
extern char FND[4];
extern int right_tune[], right_beat[], fail_tune[], fail_beat[], san_rabit_bell_tune[], san_rabit_bell_beat[];

volatile char start_flag, beep_flag, right_flag, fail_flag, sndsucess_flag, time_flag, startInfo_flag = 1, 
			sucess_flag, print_sucess_flag, next_beat_flag = 1, speakout_flag;		//전역 변수는 초기화 안하면 0으로 초기화된다
volatile int msec = 0, delay_msec = 0, sucess_msec = 0, music_i;				
volatile char i = 0, sec = 0, min = 0;							//volatile : 워킹레지스터에 선언하지말고 RAM에다가 할당하라는 의미(휘발성)
volatile int beat, interval;																			//전역변수 선언시에 많이 사용

//isr은 가급적 빨리 실행해야 되기 때문에 딜레이 x, 함수호출x ...
ISR(TIMER0_COMPA_vect)						//interrupt 걸리면 실행하는 내용 //Interrupt Service Routine
{	
	if(start_flag)msec++;				
	//static int msec = 0;					//static -> 처음 할당될때 메모리 받아서 반납하지 않음
	//static char i = 0, sec = 0, min = 0;
	if(time_flag)delay_msec++;
	if(sucess_flag) sucess_msec++;
	
	if(sucess_msec >= 500) {
		print_sucess_flag = 1;
		if(sucess_msec >= 1000) {
			print_sucess_flag = 0;
			sucess_msec = 0;
		}
	}
	if(delay_msec >= 700) {
		
		time_flag = 0;
		delay_msec = 0;
	}
	
	if(msec >= 1000)
	{
		msec = 0;
		sec++; //fnd 출력이 바뀌는 시점
		if(sec >= 60)
		{
			sec = 0;
			min++;
			if(min >= 60) min = 0;
		}
		
	}
	
	if(beep_flag) {
		
		OCR1A = 500;
		if(!(msec%200)) {
			OCR1A = 0;
			beep_flag = 0;
		}
		
	}
	
	if(right_flag) {
		if(!interval) {
			if(next_beat_flag) {
				next_beat_flag = 0;
				
				if(right_tune[music_i]) OCR1A = F_CPU / 2 / 8 / right_tune[music_i];
				else OCR1A = 0;
				beat = right_beat[music_i];
				
				if(right_tune[music_i] == '/0')
				{
					right_flag = 0;
					OCR1A = 0;
				}
				music_i++;
			}
			
			beat--;
			if(beat == 0){
				
				next_beat_flag = 1;
				interval = 10;
			}
		}
		else {
			OCR1A = 0;
			interval--;
		}
		
	}
	
	if(fail_flag) {
		if(!interval) {
			if(next_beat_flag) {
				next_beat_flag = 0;
					
				if(fail_tune[music_i]) OCR1A = F_CPU / 2 / 8 / fail_tune[music_i];
				else OCR1A = 0;
				beat = fail_beat[music_i];
					
				if(fail_tune[music_i] == '/0')
				{
					fail_flag = 0;
					OCR1A = 0;
				}
				music_i++;
			}
				
			beat--;
			if(beat == 0){
					
				next_beat_flag = 1;
				interval = 10;
			}
		}
		else {
			OCR1A = 0;
			interval--;
		}
			
	}
	
	if(sndsucess_flag) {
		if(!interval) {
			if(next_beat_flag) {
				next_beat_flag = 0;
				
				if(san_rabit_bell_tune[music_i]) OCR1A = F_CPU / 2 / 8 / san_rabit_bell_tune[music_i];
				else OCR1A = 0;
				beat = san_rabit_bell_beat[music_i];
				
				if(san_rabit_bell_tune[music_i] == '/0')
				{
					sndsucess_flag = 0;
					OCR1A = 0;
				}
				music_i++;
			}
			
			beat--;
			if(beat == 0){
				
				next_beat_flag = 1;
				interval = 10;
			}
		}
		else {
			OCR1A = 0;
			interval--;
		}
		
	}

//FND를 실제로 출력시키는 
	i++;

	if(i >=4) i = 0;
	
	//끄고 데이터 주고 끄고
	FND_COM_PORT &= 0b00001111;
	FND_shift_out(FND[i]);
	
	FND_COM_PORT |= FND4digit_digit[i]; //< FND_shift_out보다 먼저하면 이전 데이터가 출력돼서 정확한 출력이 나오지 않음
	//FND_DATA_PORT = FND[i];

} 

void Timer0_init(void)
{
	//타이머 초기화
	TCCR0A |= 1 << WGM01;					//0b00000010;  //CTC Mode
	TCCR0B |= (1 << CS00 | 1 << CS01);		//0b00000011;  //64분주 사용(하위 3bit : 011)
	OCR0A = 249;							//비교할 값 => (16,000,000 / 64) / 1000 = 250 (0 ~ "249")			//1ms 주기
	TIMSK0 |= 1 << OCIE0A;                  //0b00000010; //어떤 interrupt 걸리게 할건지 정하는	// OC0A 비교 매치 인터럽트 활성화
	
	return;
}

void Timer0_HC_SR04_init(void)
{
	TCCR0A |= 1 << WGM01;					//CTC 모드
	TCCR0B |= (1 << CS02) | (1 << CS00);	//1024분주
	OCR0A = 255;							//
	
	return;
}

void Timer0_init_CTC_outA(void) //A 출력을 쓰기위한 초기화 함수
{
	DDRD |= 1 << PORTD6;
	TCCR0A |= 1<< WGM01 | 1 <<COM0A0;
	TCCR0B |= 1 <<  CS00 | 1 <<CS01;
	
	OCR0A = 30; //4000Hz
	//OCR0A = 63; // 2000Hz
	//OCR0A = 124;							//(250 / 2) - 1 //한 주기를 1msec
	//OCR0A = 249;							//한 주기 2msec //500Hz
	//250Hz를 만들고싶다면? => OCR0A를 늘리면 오버플로우(8bit) => 분주를 늘린다
	//CS0x (분주)랑 OCR값을 이용해서 주기, 주파수 조정
	TIMSK0 |= 1 << OCIE0A;                  //0b00000010; //어떤 interrupt 걸리게 할건지 정하는	// OC0A 비교 매치 인터럽트 활성화
	
	return;
}

void Timer1_init_CTC_outA(void) //A출력을 쓰기위한 초기화 함수
{
	DDRB |= 1 << PORTB1;
	TCCR1A |= 1 << COM1A0; //TOGGLE
	TCCR1B |= 1 << WGM12 | 1 <<  CS11;  //CTC 모드//64분주
	OCR1A = 0; // 소리 끄기
	
	//OCR0A = 30; //4000Hz
	//OCR0A = 63; // 2000Hz
	//OCR0A = 124;							//(250 / 2) - 1 //한 주기를 1msec
	//OCR0A = 249;							//한 주기 2msec //500Hz
	//250Hz를 만들고싶다면? => OCR0A를 늘리면 오버플로우(8bit) => 분주를 늘린다
	//CS0x (분주)랑 OCR값을 이용해서 주기, 주파수 조정
	//TIMSK0 |= 1 << OCIE0A;                  //0b00000010; //어떤 interrupt 걸리게 할건지 정하는	// OC0A 비교 매치 인터럽트 활성화
	
	return;
}

void Timer1_init_fast_PWM_outA(void){
	DDRB |= 1 << PORTB1 | 1<< PORTB2; //outA, 출력
	//MODE : 14, WGM13 : 1, WGM12 : 1, WGM11 : 1, WGM10 : 0 
	// Fast PWM //TOP : ICR1, Update of OCR1x at: BOTTOM, TOV1 Flag Set on : TOP 
	TCCR1A |= (1 << WGM11) | (1 << COM1A1) | (1 << COM1A0) | (1 << COM1B1) | (1 << COM1B0); //COM1A1 : 1, COM1A0 : 0  //OCR값이 커지면 펄스폭이 길어지고
											//Clear OC1A/OC1B on Compare Match, set Clear OC1A/OC1B on Compare Match, set OC1A/OC1B at BOTTOM (non-inverting mode)
	TCCR1B |= (1 << WGM12) | (1 << WGM13) | (1 << CS11);  //CS12 :0, CS11: 1, CS10: 0 //clkI/O/8 (From prescaler) //8분주
	OCR1A = 0; //BOTTOM
	OCR1B = 0;
	ICR1 = 255; //1차이가 크지 않기때문에 보통 9999대신 10000을 쓴다//19999; //0부터 2만개를 COUNT 하겠다는 의미 
}

void Timer2_init_fast_PWM_outA(void){
	DDRB |= 1 << PORTB3; 
	
	TCCR2A |= (1 << COM2A1) | (1 << COM2A0) | (1 << WGM21) | (1 << WGM20); 
	TCCR2B |= (1 << CS20); //모터 구동 시에는 CS20  
	OCR2A = 0; //BOTTOM
	
	//따로 ICR 없음 //0~255까지가 100프로
}

void Timer1_init_fast_PWM_outA_SurvoMotor(void){
	DDRB |= 1 << PORTB1 | 1<< PORTB2; 
	TCCR1A |= (1 << WGM11) | (1 << COM1A1) | (1 << COM1A0) | (1 << COM1B1) | (1 << COM1B0);
	TCCR1B |= (1 << WGM12) | (1 << WGM13) | (1 << CS12);  
	
	OCR1A = 0; 
	OCR1B = 0;
	ICR1 = 255;
}

