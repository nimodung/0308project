/*
 * SmartHomeTest.c
 *
 * Created: 2019-06-25 오전 10:17:36
 * Author : tjoeun
 */ 

#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>
#include <avr/interrupt.h>
#include "Uart.h"
#include "Bluetooth.h"
#include "Timer.h"
#include "DHT11.h"
#include "ADC.h"
//#include "Dot_matrix.h"


extern volatile char RX_data;
extern volatile char RX_flag, RX_cmd_count;
extern char buffer[COMMAND_MAX][BUFFER_MAX]; //BUFFER_MAX글자 안에서 명령어 입력

extern volatile char check_DHT11_flag, delay_flag, dot_toggle_flag;
int lux;
volatile char doorAutoFlag = 1, bathroomAutoFlag = 1, doorOpenFalg = 0;

/*
//extern uint32_t dotmatrix[8];
extern uint32_t dotmatrixdata[8];
extern const char last_data[8];
extern const char middle_data[8];
extern const char first_data[8];*/

int main(void)
{
	char cmd_idx = 0;
	
	DDRD |= (1 << PORTD2) | (1 << PORTD4) | (1 << PORTD5) | (1 << PORTD6) | (1 << PORTD7);
	PORTD &= ~((1 << PORTD2) | (1 << PORTD4) | (1 << PORTD5) | (1 << PORTD6) | (1 << PORTD7));
	DDRB |= 1 << PORTB5;
	PORTB &= ~(1<< PORTB5);
	Bluetooth_init();
	//Myhome_dotmatrix_init_shiftR();
	Timer0_init();
	Timer2_init_fast_PWM_outA_Motor();
	Timer1_init_fast_PWM_outA();
	DHT11_init();
	ADC_init();
	
/*
	for(int i = 0; i<8; i++)
	{
		dotmatrixdata[i] = 0xffffffff;
		dotmatrixdata[i] = (dotmatrixdata[i]<<8) | last_data[i];
		dotmatrixdata[i] = (dotmatrixdata[i]<<8) | middle_data[i];
		dotmatrixdata[i] = (dotmatrixdata[i]<<8) | first_data[i];
	}
	for(int i=0;i<8;i++)
	{
		dotmatrix[i] = 0x01000000 << i | dotmatrixdata[i];

	}
	*/
	
	sei();
	
	 
	
	while (1) 
    {
		
		if(RX_cmd_count) {
			RX_cmd_count--;
			if(!strcmp(buffer[cmd_idx], "led room on")) { //strcmp() : 비교해서 같으면 return 0
				PORTD |= 1 << PORTD2;
				//delay_flag = 1;
				printf("led : room on\n");
			}
			else if(!strcmp(buffer[cmd_idx], "led room off")) {
				PORTD &= ~(1 << PORTD2);
				//delay_flag = 1;
				printf("led : room off\n");
			}
			else if(!strcmp(buffer[cmd_idx], "led living on")) {
				PORTD |= 1 << PORTD4;
				//delay_flag = 1;
				printf("led : living on\n");
			}
			else if(!strcmp(buffer[cmd_idx], "led living off")) {
				PORTD &= ~(1 << PORTD4);
				//delay_flag = 1;
				printf("led : living off\n");
			}
			else if(!strcmp(buffer[cmd_idx], "led kitchen on")) {
				PORTD |= 1 << PORTD5;
				//delay_flag = 1;
				printf("led : kitchen on\n");
			}
			else if(!strcmp(buffer[cmd_idx], "led kitchen off")) {
				PORTD &= ~(1 << PORTD5);
				//delay_flag = 1;
				printf("led : kitchen off\n");
			}
			else if(!strcmp(buffer[cmd_idx], "led bathroom on")) {
				PORTD |= 1 << PORTD6;
				//delay_flag = 1;
				printf("led : bathroom on\n");
			}
			else if(!strcmp(buffer[cmd_idx], "led bathroom off")) {
				PORTD &= ~(1 << PORTD6);
				//delay_flag = 1;
				printf("led : bathroom off\n");
			}
			else if(!strcmp(buffer[cmd_idx], "led door on")) {
				PORTD |= 1 << PORTD7;
				//delay_flag = 1;
				printf("led : door on\n");
			}
			else if(!strcmp(buffer[cmd_idx], "led door off")) {
				PORTD &= ~(1 << PORTD7);
				//delay_flag = 1;
				printf("led : door off\n");
			}
			else if(!strcmp(buffer[cmd_idx], "led whole on")) {
				PORTD |= 1 << PORTD2 | 1 << PORTD4 | 1 << PORTD5 | 1 << PORTD6 | 1 << PORTD7;
				//delay_flag = 1;
				printf("led : whole on\n");
			}
			else if(!strcmp(buffer[cmd_idx], "led whole off")) {
				PORTD &= ~(1 << PORTD2 | 1 << PORTD4 | 1 << PORTD5 | 1 << PORTD6 | 1 << PORTD7);
				//delay_flag = 1;
				printf("led : whole off\n");
			}
			else if(!strcmp(buffer[cmd_idx], "led whole on auto")) {
				PORTD |= 1 << PORTD2 | 1 << PORTD4 | 1 << PORTD5;
				//delay_flag = 1;
				printf("led : whole on\n");
			}
			else if(!strcmp(buffer[cmd_idx], "led whole off auto")) {
				PORTD &= ~(1 << PORTD2 | 1 << PORTD4 | 1 << PORTD5);
				//delay_flag = 1;
				printf("led : whole off\n");
			}
			else if(!strcmp(buffer[cmd_idx], "led whole on door")) {
				PORTD |= 1 << PORTD2 | 1 << PORTD4 | 1 << PORTD5 | 1 << PORTD6;
				//delay_flag = 1;
				printf("led : wholed on\n");
			}
			else if(!strcmp(buffer[cmd_idx], "led whole off door")) {
				PORTD &= ~(1 << PORTD2 | 1 << PORTD4 | 1 << PORTD5 | 1 << PORTD6);
				//delay_flag = 1;
				printf("led : wholed off\n");
			}
			else if(!strcmp(buffer[cmd_idx], "led whole on bathroom")) {
				PORTD |= 1 << PORTD2 | 1 << PORTD4 | 1 << PORTD5 | 1 << PORTD7;
				//delay_flag = 1;
				printf("led : wholeb on\n");
			}
			else if(!strcmp(buffer[cmd_idx], "led whole off bathroom")) {
				PORTD &= ~(1 << PORTD2 | 1 << PORTD4 | 1 << PORTD5 | 1 << PORTD7);
				//delay_flag = 1;
				printf("led : wholeb off\n");
			}
			else if(!strcmp(buffer[cmd_idx], "fan on")) {
				OCR2B = 200;
				//delay_flag = 1;
			}
			else if(!strcmp(buffer[cmd_idx], "fan off")) {
				OCR2B = 0;
				//delay_flag = 1;
			}
			else if(!strcmp(buffer[cmd_idx], "door open")){
				OCR1A = 2900;
				//printf("door : closed\")
				
			}
			else if(!strcmp(buffer[cmd_idx], "door close")){
				OCR1A = 4900;
				
			}
			else if(!strcmp(buffer[cmd_idx], "window open")){
				OCR1B = 1300;
				//printf("door : closed\")
				
			}
			
			else if(!strcmp(buffer[cmd_idx], "window close")){
				OCR1B = 4900;
				
			}
			else if(!strcmp(buffer[cmd_idx], "doorLedAutoStatetrue")){
				doorAutoFlag = 1;
			}
			else if(!strcmp(buffer[cmd_idx], "doorLedAutoStatefalse")){
				doorAutoFlag = 0;
			}
			else if(!strcmp(buffer[cmd_idx], "bathroomLedAutoStatetrue")){
				bathroomAutoFlag = 1;
			}
			else if(!strcmp(buffer[cmd_idx], "bathroomLedAutoStatefalse")){
				bathroomAutoFlag = 0;
			}
			//_delay_ms(1000);
			//while(delay_flag);
			cmd_idx++;
			cmd_idx = cmd_idx % COMMAND_MAX;
		}
		
	//	Dotmatrix_flow_left_in();
	//	Dotmatrix_flow_left_out();
		
		if(check_DHT11_flag) {
			check_DHT11_flag = 0;
			check_DHT11();
			if(bathroomAutoFlag){check_Sharp();}
		}
		
	}
}

void check_Sharp(){
	int cm = 0;
	
	cm = Volt_to_cm(ADC_converting_value(2));
	// printf("%d\n", cm);
	if(cm <= 9) printf("bathroom : on\n");
	else printf("bathroom : off\n");
	
	
	return;
}
void check_DHT11() {
	 char RH_integral, RH_decimal, Tmpr_integral, Tmpr_decimal;
	
	 int lux = ADC_converting_value(1);
	 
	 if(doorAutoFlag) {
		 if(lux <= 200) printf("lux : off\n");
		 else printf("lux : on\n");
	 }
	 
	
	 DHT11_trigger(); //트리거
	 data_input(); //입력 전환
	 dumi_read(); // 데이터 전송 전에 시간 흘려보내기
	
	 
	 RH_integral = rx_byte(); //데이터 값 저장
	 RH_decimal  = rx_byte();
	 Tmpr_integral = rx_byte();
	 Tmpr_decimal = rx_byte();
	 
	 rx_byte(); //check_sum
	
	 //DHT11_init();
	 data_output(); // data pin 출력 전환

	 printf("env : humi %d.%d temper %d.%d lux %d\n", RH_integral, RH_decimal, Tmpr_integral, Tmpr_decimal, lux);
	
	 //printf("temper %d.%d\n", Tmpr_integral, Tmpr_decimal);
	 
	// _delay_ms(1500); //적당한 시간을 주지않으면 초기화를 못해서 한번만 출력하고 안된당
	 //delay_flag = 1;
	 
	return;
	  
	
}


