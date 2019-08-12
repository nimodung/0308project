/*
 * Temp_submotor.c
 *
 * Created: 2019-05-13 오후 4:56:50
 *  Author: tjoeun
 */ 

#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>
#include "Uart.h"
#include "DHT11.h"
#include "FND4digit.h"

extern char FND4digit_digit[4];
extern char FND[4];

int Temp_submotor_main() {
	
	char RH_integral = 0, RH_decimal = 0, Tmpr_integral = 0, Tmpr_decimal = 0;
	char sub_motor_flag = 1;
	int sub = 0;
	DHT11_init();
	Timer1_init_fast_PWM_outA();
	FND4digit_init_shiftR();
	Timer0_init();
	UART0_init(9600);
	
	
	while(1){
		DHT11_trigger(); //트리거

		data_input(); //입력전환
		dumi_read(); // 데이터 전송전에 시간 흘려보내기


		rx_byte();//RH_integral 
		rx_byte();//RH_decimal 
		Tmpr_integral = rx_byte();
		Tmpr_decimal = rx_byte();

		rx_byte(); //check_sum

		//DHT11_init();
		data_output(); // data pin 출력 전환
		_delay_ms(1500); //적당한 시간을 주지않으면 초기화를 못해서 한번만 출력하고 안된당

		printf("RH : %d.%d, Temperature : %d.%d \n", RH_integral, RH_decimal, Tmpr_integral, Tmpr_decimal);
		FND_update_value(Tmpr_integral * 100 + Tmpr_decimal);
		//_delay_ms(2000);
		
		if(sub_motor_flag){
			sub += Tmpr_integral;
			
			if(sub >= 210) sub_motor_flag = 0;
		}
		else {
			sub--;
			
			if(sub <= 120) sub_motor_flag = 1;
		}
		
		OCR1B = sub;
	}
}
