/*
 * LEDBar.c
 *
 * Created: 2019-04-04 오전 10:34:54
 *  Author: Kim Hee Ram
 */ 

#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>
#include "LEDBar.h"

int LEDBar_main(void)
{
	Ledbar_init();
    while (1) 
    {
		//Led_up_down();
		//Led_on_up();
		//Led_off_up();
		//Led_on_down();
		//Led_off_down();
		Led_on_increase();
		Led_on_decrease();
    }
}

void Ledbar_init(void)
{
	LEDBar_DDR = 0b11111111; //포트를 출력(1)으로 설정
	LEDBar_PORT = 0b11111111; //LED를 전부 끄는걸로 초기화(VCC에 연결했기때문에 1:꺼짐 0:켜짐)
	return;
}

void Led_up_down(void)
{
	for(int i = 0; i < 9; i++)
	{
		LEDBar_PORT = ~(0b00000001 << i);
		_delay_ms(300);
	}
		
	for(int i = 0; i < 9; i++)
	{
		LEDBar_PORT = ~(0b10000000 >> i);
		_delay_ms(300);
	}
}

void Led_on_up(void) 
{
	for(int i = 0; i < 9; i++)
	{
		LEDBar_PORT &= ~(0b00000001 << i);
		_delay_ms(300);
	}
	
	return;
}

void Led_on_down(void)
{
	for(int i = 0; i < 9; i++)
	{
		LEDBar_PORT &= ~(0b10000000 >> i);
		_delay_ms(300);
	}
	
	return;
}

void Led_off_up(void)
{
	/*for(int i = 0; i < 9; i++)
	{
		LEDBar_PORT = ~(0b11111111 << i);
		_delay_ms(300);
	}*/
	
	/*for(int i = 0; i < 9; i++)
	{
		LEDBar_PORT |= 0b00000001 << i;
		_delay_ms(300);
	}*/
	
	/*for(int i = 0; i < 9; i++)
	{
		LEDBar_PORT = (LEDBar_PORT <<1) + 1;
		_delay_ms(300);
	}*/
	
	for(int i = 0; i < 9; i++)
	{
		LEDBar_PORT = 0b11111111 >> (8-i);
		_delay_ms(300);
	}
	
	
	return;
}

void Led_off_down(void)
{
	for(int i = 0; i < 9; i++)
	{
		LEDBar_PORT = ~(0b11111111 >> i);
		_delay_ms(300);
	}
	
	return;
}

void Led_on_increase(void)
{
	LEDBar_PORT = 0b11111111;
	
	/*for(int i = 0; i < 4; i++)
	{
		LEDBar_PORT = (0b01111111 >> i)^(~(0b11111110 << i));
		_delay_ms(400);
	}
	*/
	/*
	for(int i = 0; i < 4; i++)
	{
		LEDBar_PORT = ((0b01110000 >> i)&(0b11110000)) | ((0b00001110 <<i)&(0b00001111));
		_delay_ms(400);
	}*/
	for(int i = 0; i < 4; i++)
	{
		LEDBar_PORT = (0b01111111 >> i)&(0b11111110 << i);	
		_delay_ms(400);	
	}
}
void Led_on_decrease(void)
{
	//LEDBar_PORT = 0b00000000;
	_delay_ms(400);
	for(int i = 0; i < 4; i++)
	{
		LEDBar_PORT = (0b11111111 >> (3-i))&((0b11111111 << (3-i)));
		_delay_ms(400);
	}
	/*
	for(int i = 3; i > -1; i--)
	{
		LEDBar_PORT = (0b11111111 >> i) & (0b11111111 <<i);
		_delay_ms(400);
	}
	*/
}
