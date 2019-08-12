/*
 * Keypad.c
 *
 * Created: 2019-04-16 오후 12:06:32
 *  Author: Kim Hee Ram
 */ 

#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>
#include <stdio.h>

#include "Uart.h"
#include "Keypad.h"

/*
int Keypad_main(void)
{
	char long_key_flag = 0;
	int number = 0;
	Keypad_init();
	UART0_init(9600);
	
	while(1)
	{
		//number++;
		//TX0_char('\r');
		//TX0_4Digit(number);
		
		if(long_key_flag) 
		{
			if(Keyscan() != 'A') 
			{
				if(Keyscan() != 'B') TX0_char(Keyscan());
				if(Keyscan() == 'B') TX0_string("Hello World\n");
				long_key_flag = 0;
			}
		}
		else 
		{
			if(Keyscan() == 'A') //스위치를 안누를 때 -> 연산 결과가 16 //pinb = 00010000
			{
				long_key_flag = 1;
			}
		}
		
		
		
	
	}
	return 0;
}*/

void Keypad_init(void)
{
	//Keypad_PORT_DDR |= 0b00001111;
	//Keypad_PORT |= 0b00001111; 
	Keypad_PIN_DDR &= 0b11110000; 
	Keypad_PIN_PORT |= 0b00001111;
	//MCUCR Register : bit 4번 PUD : Pull-up Disable //내부적으로 풀업저항 사용 가능 //initial value : 0
	//DDRxn : 0 (입력 설정), PORTxn : 1, PUD : 0 => pull-up yes
	
	return;
}

char Keyscan_sub(void)
{
	if(!(Keypad_PIN & 0b00000001)) return 1; //키가 눌렸을때
	else if(!(Keypad_PIN & 0b00000010)) return 2;
	else if(!(Keypad_PIN & 0b00000100)) return 3;
	else if(!(Keypad_PIN & 0b00001000)) return 4;
	else return 0;
}

char Keyscan(void)
{
	for(int i = 0; i < 4; i++)
	{
		Keypad_PORT |= 0b00001111;
		Keypad_PORT &= ~(0b00001000 >> i); //clear &=
		_delay_ms(1);
		if(Keyscan_sub()) return 'A' + Keyscan_sub() + (i*4);
	}
	
	return 'A'; //아무것도 눌리지 않았음
}


void key_test(void)
{
	char long_key_flag = 0;
	
	if(long_key_flag) {
		if(Keyscan() != 'A')
		{
			_delay_us(200);
			if (Keyscan() != 'A')
			{
				switch(Keyscan()) {
					case 'B' : FND_update_value(1); break;
					case 'C' : FND_update_value(2); break;
					case 'D' :  FND_update_value(3); break;
					case 'E' :  FND_update_value(4); break;
					case 'F' : FND_update_value(5); break;
					case 'G': FND_update_value(6); break;
					case 'H': FND_update_value(7); break;
					case 'I': FND_update_value(8); break;
					case 'J': FND_update_value(9); break;
					case 'K': FND_update_value(10); break;
					case 'L': FND_update_value(11); break;
					case 'M': FND_update_value(12); break;
					case 'N': FND_update_value(13); break;
					case 'O': FND_update_value(14); break;
					case 'P': FND_update_value(15); break;
					case 'Q': FND_update_value(16); break;
				}
			
				long_key_flag = 0;
			}
		
		}
	}
	else
	{
		if(Keyscan() == 'A')
		{
			long_key_flag = 1;
		}
	}

}