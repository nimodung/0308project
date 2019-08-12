/*
 * StopWatch.c
 *
 * Created: 2019-04-05 오후 2:57:17
 * Author : user
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

volatile char start_flag, w_flag, clear_flag, time_flag;		//전역 변수는 초기화 안하면 0으로 초기화된다
int msec = 0;					
char i = 0, sec = 0, min = 0;

//isr은 가급적 빨리 실행해야 되기 때문에 딜레이 x, 함수호출x ...
ISR(TIMER0_COMPA_vect)						//interrupt 걸리면 실행하는 내용 //Interrupt Service Routine
{						
	//static int msec = 0;					//static -> 처음 할당될때 메모리 받아서 반납하지 않음
	//static char i = 0, sec = 0, min = 0;
	if(start_flag) msec++;
		
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
		//if(w_flag)
		//FND_clock(sec, min);
	}
	
	
	if(!(msec % 10)) 
	{
		if(w_flag)
			time_flag = 1;
	}
	
	if(clear_flag)
	{
		clear_flag = 0;
		FND_update_time(msec, sec);
		msec = 0;
		sec = 0;		min = 0;
		
	}
	
	i++;
 	
 	if(i >=4) i = 0;
	
	FND_COM_PORT &= 0b11110000;
	FND_COM_PORT |= FND4digit_digit[i];
	FND_DATA_PORT = FND[i];

	
} 

int main(void)
{
    char long_key_flag = 1;
	
	DDRB |= 1<<PORTB5;						//포트 b의 5번핀만 set -> 출력으로 설정//PORTB5는 5로 define 되어있음
	//dotmatrix_init();
	FND4digit_init();
	DDRB &= ~(1<<PORTB4 | 1<<PORTB5); //버튼 
	FND_update_value(0);
	Timer0_init();							//8bit 메모리 => 0~255까지 셀 수 있음
	sei();									//전체(글로벌) interrupt on/off (interrupt 활성화) 
											//cli() : interrupt 해제 / 처리중 interrupt가 들어오면 안될때 강제적으로 막을때 사용
													//인터럽트 서비스 루틴에서 현재 서비스 루틴이 다른 경로에의해 간섭이나 처리에 영향을 받지않도록 cli로막는다
		
	while(1)
	{ 
		if(time_flag) 
		{
			FND_update_time(msec, sec);
			time_flag = 0;
		}
		if(long_key_flag)
		{
			if(!(PINB & 0b00010000))
			{
				_delay_ms(1);
				if(!(PINB & 0b00010000))
				{
					start_flag = 1;
					if(w_flag) w_flag = 0;
					else w_flag = 1;
					long_key_flag = 0;
				}
				
			}
			else if(!(PINB & 0b00100000))
			{
				_delay_ms(1);
				if(!(PINB & 0b00100000))
				{
					start_flag = 0;
					w_flag = 0;
					clear_flag = 1;
					long_key_flag = 0;
				}
			}
		}
		else
		{
			if((PINB & 0b00010000) && (PINB & 0b00100000))
				long_key_flag = 1;
		}
	
	
	}
	
	return 0;
}

