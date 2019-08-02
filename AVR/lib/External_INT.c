/*
 * External_INT.c
 *
 * Created: 2019-04-22 오후 3:16:43
 *  Author: Kim Hee Ram
 */ 

#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>
#include <avr/interrupt.h>

void External_INT_main(void)
{
	
	while(1)
	{
		
		
	}
	
	return;
}

void EINT_init_for_HC_SR04(void)
{
	//외부 인터럽트 초기화
	//echo 입력핀 low->high로 바뀌는 순간 읽기 //1. while으로 계속 읽기 // 2. 입력 외부 인터럽트 써서 읽기
	//trigger 설정
	//rising 엣지 <= PORTB2 / Interrupt 0 ,  falling 엣지 <= PORTB3 / Interrupt 1
	EICRA |= (1 << ISC00) | (1 << ISC01) | (1 << ISC11);		//00,01 : rising , 11 : falling (10은 0)
																//INT0 rising edge, INT1 falling edge
	EIMSK |= (1 << INT0) | (1 << INT1);							//외부 인터럽트 enable 쓸것
																//INT0, INT1 connect to echo pin
	//EIFR 자동으로 (상태 레지스터)
	return;
}