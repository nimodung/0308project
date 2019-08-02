/*
 * Bluetooth.c
 *
 * Created: 2019-05-14 오후 2:47:19
 *  Author: KIM HEE RAM
 */ 
#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>
#include <avr/interrupt.h>

#include "Bluetooth.h"
#include "Uart.h"
#include "Timer.h"
#include "FND4digit.h"

extern volatile char RX_flag ,RX_data;

int Bluetooth_main(void) {

	char red = 0, green = 0, blue = 0;
	
	DDRB |= 1 << PORTB5;
	PORTB &= ~(1 << PORTB5);
	Bluetooth_init();
	Timer1_init_fast_PWM_outA();
	Timer2_init_fast_PWM_outA();
	FND4digit_init_shiftR();
	Timer0_init();							//8bit 메모리 => 0~255까지 셀 수 있음
	sei();
	
	//setName("IoT_Ram");
	if(RX_flag) PORTB &= ~(1 << PORTB5);
	while(1) {
		if(RX_flag) {
			RX_flag = 0;
			//PORTB &= ~(1 << PORTB5);
			switch(RX_data){
				case 'F' :
					red++;
					if(red >= 150) red = 150;	
					FND_update_value(red + 1000);
					break;
				case 'B' :
					if(red <= 0) red = 1;
					red--;
					FND_update_value(red + 1000);
					break;
				case 'L' :
					
					blue++;
					if(blue >= 150) blue = 150;
					FND_update_value(blue + 3000);
					break;
				case 'R' :
					if(blue <= 0) blue = 1;
					blue--;
					
					FND_update_value(blue + 3000);
					break;
				case 'I' :
					
					green++;
					if(green >= 150) green = 150;
					FND_update_value(green + 2000);
					break;
				case 'H' :
					if(green <= 0) green = 1;
					green--;
					
					FND_update_value(green + 2000);
					break;
				default: break;
			}
			OCR1A = red;
			OCR1B = blue;
			OCR2A = green;
			_delay_ms(100);
			PORTB |= 1 << PORTB5;
		}		
	}	
	return 0;
}

void Bluetooth_init(void) {
	UART0_init(9600);
	
	return;
}

void setName(char *name){
	TX0_string("AT+NAME");
	TX0_string(name);
	
	return;
}

void setPIN(char *pin) { //네 자리 숫자
	TX0_string("AT+PIN");
	TX0_string(pin);
	return;
}

 void connectTest(void) {
	 TX0_string("AT");
	 return;
 }