/*
 * FND.C
 *
 * Created: 2019-04-04 오전 11:57:10
 *  Author: Kim Hee Ram
 */ 

#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>

#include "FND.h"

char FND_font[10] = {
		~(1 << FND_a | 1 << FND_b | 1 << FND_c | 1 << FND_d | 1 << FND_e | 1 << FND_f), //0
		~(1 << FND_c | 1 << FND_b), //1
		~(1 << FND_a | 1 << FND_b | 1 << FND_g | 1 << FND_e | 1 << FND_d), //2
		~(1 << FND_a | 1 << FND_b | 1 << FND_g | 1 << FND_c | 1 << FND_d), //3
		~(1 << FND_c | 1 << FND_b | 1 << FND_g | 1 << FND_f), //4
		~(1 << FND_a | 1 << FND_f | 1 << FND_g | 1 << FND_d | 1 << FND_d | 1 << FND_c), //5
		~(1 << FND_a | 1 << FND_f | 1 << FND_e | 1 << FND_d | 1 << FND_d | 1 << FND_g | 1 << FND_c), //6		
		~(1 << FND_a | 1 << FND_b | 1 << FND_c), //7
		~(1 << FND_a | 1 << FND_b | 1 << FND_c | 1 << FND_d | 1 << FND_e | 1 << FND_f | 1 << FND_g), //8
		~(1 << FND_a | 1 << FND_b | 1 << FND_c | 1 << FND_d | 1 << FND_f | 1 << FND_g), //9
	};
//FND_PORT = FND_PORT & (~(0b00000001 << FND_c));
//FND_PORT &= ~(1 << FND_c | 1 << FND_b); //1
//FND_PORT &= ~(1 << FND_c | 1 << FND_b | 1 << FND_g | 1 << FND_f); //4

void FND_main()
{
	FND_init();
	
	while(1)
	{
		FND_test();
	}
	
	//return;
	//무한 반복문이 있기때문에 return 생략한다.
	//무한 반복문은 필수! 
}

void FND_init(void)
{
	FND_DDR = 0b11111111; //포트를 출력(1)으로 설정
	FND_PORT = 0b11111111; //LED를 전부 끄는걸로 초기화(VCC에 연결했기때문에 1:꺼짐 0:켜짐)
	return;
}

void FND_test(void)
{
	for(int i = 0; i < 10; i++)
	{
		FND_PORT = FND_font[i];
		_delay_ms(400);
	}
	return;
}