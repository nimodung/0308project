/*
 * Measure.c
 *
 * Created: 2019-04-24 오후 2:15:28
 *  Author: Kim Hee Ram
 */ 

#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>
#include "Uart.h"
#include "Keypad.h"
#include "DHT11.h" //습도 온도
#include "ADC.h" //가변저항, cds


int Measure_main(void)
{
	char long_key_flag = 0;
	char RH_integral = 0, RH_decimal = 0, Tmpr_integral = 0, Tmpr_decimal = 0;
	int value = 0;
	
	Keypad_init();
	DHT11_init();
	ADC_init();
	_delay_us(200); 
	UART0_init(9600);
	
	while(1)
	{
	
		if(long_key_flag)
		{
			if(Keyscan() != 'A')
			{
				if(Keyscan() == 'B') // 습도 출력
				{
					printf("RH : ");
					DHT11_trigger(); //트리거
					  
					data_input(); //입력전환
					dumi_read(); // 데이터 전송전에 시간 흘려보내기
					
					RH_integral = rx_byte(); //습도 정수
					RH_decimal  = rx_byte(); //습도 소수
					rx_byte(); //온도 정수 흘려보내기
					rx_byte(); //온도 소수 흘려보내기
					rx_byte(); //check_sum 흘려보내기
					
					data_output(); // data pin 출력 전환
					  
					printf(" %d.%d %% \n", RH_integral, RH_decimal);
					
					_delay_ms(1500);
					long_key_flag = 0;
				}
				
				if(Keyscan() == 'C') // 온도 출력
				{
					printf("Temperature : ");
					DHT11_trigger(); //트리거
					
					data_input(); //입력전환
					dumi_read(); // 데이터 전송전에 시간 흘려보내기
									
					rx_byte(); //습도 정수 흘려보내기
					rx_byte(); //습도 소수 흘려보내기
					Tmpr_integral = rx_byte(); //온도 정수
					Tmpr_decimal = rx_byte(); //온도 소수
					rx_byte(); //check_sum 흘려보내기
					
					data_output(); // data pin 출력 전환
					
					printf(" %d.%d ", Tmpr_integral, Tmpr_decimal);
					TX0_char(39);
					TX0_char('C');
					TX0_char('\n');
					_delay_ms(1500);
					long_key_flag = 0;
				}
				
				if(Keyscan() == 'D') // 가변저항 출력
				{
					//가변 저항
					value = ADC_converting_value(0);
					printf("Potentionmeter : %d.%d%d V\n", value/100%10, value/10%10, value%10);
					
					long_key_flag = 0;
				}
				
				if(Keyscan() == 'E') // CDS 출력
				{
					//CDS
					value = ADC_converting_value(1);
					printf("CDS value : %d.%d%d V\n", value/100%10, value/10%10, value%10);
					
					long_key_flag = 0;
				}
				else if(Keyscan() == 'F') //적외선 거리센서 //2Y0A21
				{
					
					value = ADC_converting_value(2);
					printf("Distance : %d.%d%d V\n", value/100%10, value/10%10, value%10);
					
					long_key_flag = 0;
				}
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
}