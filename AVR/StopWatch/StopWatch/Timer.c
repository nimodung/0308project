/*
 * Timer.c
 *
 * Created: 2019-04-10 오전 11:04:02
 *  Author: user
 */ 

#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>


/* TIMSK0 – Timer/Counter Interrupt Mask Register
	어떤 interrupt 걸리게 할건지 정하는
	비교 매치 b, 비교 매치 a, overflow
*/
/* TIFR0 – Timer/Counter 0 Interrupt Flag Registe
	interrupt 발생하면 flag 1
	자동으로 / 상태 register
*/

void Timer0_init(void)
{
	//타이머 초기화
	TCCR0A |= 1 << WGM01;					//0b00000010;  //CTC Mode
	TCCR0B |= (1 << CS00 | 1 << CS01);		//0b00000011;  //64분주 사용(하위 3bit : 011)
	OCR0A = 249;							//비교할 값 => (16,000,000 / 64) / 1000 = 250 (0 ~ "249")			//1ms 주기
	TIMSK0 |= 1 << OCIE0A;                  //0b00000010; //어떤 interrupt 걸리게 할건지 정하는	// OC0A 비교 매치 인터럽트 활성화
	
	return;
}