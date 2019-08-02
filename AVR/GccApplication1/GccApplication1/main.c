/*
 * GccApplication1.c
 *
 * Created: 2019-04-02 오후 4:45:35
 * Author : user
 */ 

#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>

int main(void)
{
	DDRB = 0b00100000;
	PORTB &= 0b11011111;
	DDRD = 0b11111111;
	PORTD = 32;
	
    while (1) 
    {
		/*
		PORTD = 0b11111111;
		_delay_ms(500);
		
		for(int i = 0; i <9; i++) {
			//PORTD = 0b11111111 - (0b00000001 << i);
			PORTD = ~(0b00000001 << i);
			_delay_ms(500);
		}
		
		for(int i = 0; i <9; i++) {
			//PORTD = 0b11111111 - (0b01000000 >> i);
			PORTD = ~(0b01000000 >> i);
			_delay_ms(500);
		}
		*/
		/*for(int i = 0; i <8; i++) {
			PORTD |= (0b00000001 << i);
			_delay_ms(500);
		}
		
		for(int i = 0; i <8; i++) {
			PORTD >>= 1; 
			//PORTD &= (0b11111111 >> i);
			_delay_ms(500);
		}*/ 
		
	}
}

