﻿/*
 * Timer.c
 *
 * Created: 2019-04-10 오전 11:04:02
 *  Author: user
 */ 

#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>
#include <avr/interrupt.h> // interrupt 사용하기 위함
#include "Dot_matrix.h"
#include "Timer.h"


//전역 변수는 초기화 안하면 0으로 초기화된다
volatile char check_DHT11_flag = 1, delay_flag = 1, dot_toggle_flag = 1;	
volatile int check_DHT11_msec = 0, delay_msec = 0;				
volatile char i = 0;		
	
extern uint32_t dotmatrix[8];																
extern uint32_t dotmatrixdata[8];
//isr은 가급적 빨리 실행해야 되기 때문에 딜레이 x, 함수호출x ...

ISR(TIMER0_COMPA_vect)						//interrupt 걸리면 실행하는 내용 //Interrupt Service Routine
{	

	check_DHT11_msec++;
	
	if(check_DHT11_msec >= 1200) {
		
		check_DHT11_msec = 0;
		check_DHT11_flag = 1;
		PORTB ^= (1<< PORTB5);
	}
	 
/*
	 
	if(!delay_flag) delay_msec++;
	if(delay_msec >= 100) {
		delay_flag = 1;
		delay_msec = 0;
		
	}*/

/*
	
	if(check_DHT11_flag){
		
		i++;
		if(i>7)i=0;
		  
		  
		Dotmatrix_shift_out(dotmatrix[i]);
	}*/
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

void Timer1_init_CTC_outA(void) //A 출력을 쓰기위한 초기화 함수
{
	DDRB |= 1 << PORTB1;
	TCCR1A |= (1 << COM1A1); //TOGGLE
	TCCR1B |= (1 << WGM12) | (1 <<  CS11);  //CTC 모드//64분주
	OCR1A = 5000; // 소리 끄기
	
//	ICR1 = 24999;
	
	return;
}

void Timer1_init_fast_PWM_outA(void){
	DDRB |= 1 << PORTB1 | 1 << PORTB2;
	TCCR1A |= (1 << WGM11) | (1 << COM1A1) | (1 << COM1B1);
	TCCR1B |= (1 << WGM13) | (1 << WGM12)  | (1 << CS11);
	
	OCR1A = 4900;
	OCR1B = 4900;
	ICR1 = 39999; 
	
}
void Timer2_init_fast_PWM_outA_Motor(void){
	//DDRD |= 1 << DDD3; //DDD <- 레지스터의 비트 이름 //PORTDx랑 define 값이 같음
	DDRB |= 1 << DDD3;
	DDRD |= 1 << DDD3;
	
	TCCR2A |= (1 << COM2A1) | (1 << COM2B1) | (1 << WGM21) | (1 << WGM20);
	//TCCR2A |= (1 << COM2B1) | (1 << WGM21) | (1 << WGM20);
	//COM2B1 , COM2B0 : 11 이면 Set OC2B on Compare Match, clear OC2B at BOTTOM, (inverting mode).
	//->match가 되면 1로 되는것. ocr값을 작게 주면 high가 길다. 반대라서 헷갈리니까 10으로 준것
	TCCR2B |= (1 << CS20); //모터 구동 시에는 CS20
	
	OCR2A = 0; //BOTTOM
	OCR2B = 0;
	//따로 ICR 없음 //0~255까지가 100프로
}

