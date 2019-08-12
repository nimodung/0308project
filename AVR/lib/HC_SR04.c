/*
 * HC_SR04.c
 *
 * Created: 2019-04-22 오전 10:38:58
 *  Author: Kim Hee Ram
 */ 

#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>
#include <avr/interrupt.h>
#include "Timer.h"
#include "Uart.h"
#include "HC_SR04.h"
#include "External_INT.h"

char echo_time;


ISR(INT0_vect)  /* External Interrupt Request 0 */ //RISING 엣지 //timer 0으로 초기화
{
	TCNT0 = 0;
}

ISR(INT1_vect)  /* External Interrupt Request 1 */ //falling 엣지 // timer 값 읽기
{
	echo_time = TCNT0;  //16,000,000 (1초에 들어오는 클락) / 1024분주 = 1초에 15625클락 들어옴 //1클락당 1
	
	//(1/(160000000 / 1024)) = 0.000064  // 갔다 오는데 걸린 시간
	//((echo_time * (1/(160000000 / 1024)) / 2 ) * 340)  // 거리 계산
	
}
int HC_SR04_main(void){
	
	char cm = 0, hight = 0;
	
	HC_SR04_init();
		
	Timer0_HC_SR04_init(); //타이머 초기화
	EINT_init_for_HC_SR04();
	
	//측정된 값을 uart통신으로 날림
	UART0_init(9600); //uart 통신 초기화
	
	sei(); //인터럽트 활성화
	
	while(1)
	{
		HC_SR04_trigger();
		_delay_ms(1000);				//1초에 한번씩 측정
		
		cm = time_to_cm(echo_time);
		hight = cm_to_hight(cm);
		printf(" hight : %d cm\n", hight);
		//printf(" distance : %d cm\n", cm);
	}
	
	return 0;
}


void HC_SR04_init(void)
{
	//TRIG
	Trg_DDR |= (1 << Trg_pinnum);			//포트 b 4번 핀 출력 설정
	Trg_PORT &= ~(1 << Trg_pinnum);	//4번 핀 0으로 초기화
	
	return;
}

void HC_SR04_trigger(void)
{
	//Triggering //트리거신호
	Trg_PORT |= (1 << Trg_pinnum);			//high
	_delay_ms(10);
	Trg_PORT &= ~(1 << Trg_pinnum);		//low
	
	return;
}

char time_to_cm(char time)
{
	char cm;
	cm = (time+1) * 64 / 59;	//펄스 폭에 대한 시간(echo_time)을 거리로 변환
	//측정한 거리를 cm로 변환까지 //timer가 0부터니까 +1
	//(1/(16000000/1024)) * echo_time * 340 * 100 * (1/2)
	
	return cm;
}

char cm_to_hight(char cm)
{
	char hight;
	hight = 200 - cm;
	
	return hight;
}