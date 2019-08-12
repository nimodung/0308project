/*
 * StorageController.c
 *
 * Created: 2019-06-03 오전 10:46:07
 * Author : Kim Hee Ram
 */ 

#include <avr/io.h>
#include <util/delay.h>
#define F_CPU 16000000UL
#include <avr/interrupt.h>
#include <stdlib.h> 

#include "ADC.h"
#include "FND4digit.h"
#include "Keypad.h"
#include "Timer.h"
#include "Uart.h"

#define STORAGE1 0
#define STORAGE2 1
#define STORAGE3 2
#define STORAGE0 5 //창고를 선택하지 않음

#define SELECT_STORAGE_MODE 1
#define STOCK_MANAGER_MODE 2
#define TEMPER_MANAGER_MODE 3
#define READY_MODE 5

#define ROOM1_DESIRE_UP '1'

extern volatile char RX_flag, RX_data, RX_cmd_count, time_flag, delay_flag;
extern char buffer[COMMAND_MAX][BUFFER_MAX];
extern char FND[4];

void StorageController_Init(void);
void Command_Process(void);
void SelectStorage_Porcess(void);
void StockManage_Process(void);
void TemperManage_Process(void);
void Storage_LED_On(char storage);
void print_stock(char storage);

char mode = READY_MODE;
char selected_storage = STORAGE0, cmd_idx;
char print_flag, long_key_flag;
int current_temp, desire_temp;

char* stockDB_name[] = {"apple", "coke", "fish"};
char* stockDB_date[] = {"19/06/04", "18/07/05", "19/12/23"};
char* stockDB_count[] = {"100", "200", "150"};
	
char* temperDB_current[] = {"", "", ""};
char* temperDB_desire[] = {"20", "10", "-10"};

char room1_desire, room2_desire, room3_desire;
char cooling_flag[] = {0, 0, 0};	
int main(void)
{

	StorageController_Init();
  
    while (1) 
    {
		if(RX_flag){
			if(RX_data == ROOM1_DESIRE_UP){
				room1_desire++;
				if(room1_desire >= 20) room1_desire = 20;
				printf("room1_desire : %d\n\r", room1_desire);
			}
			RX_flag =0;
		}
		if(time_flag && (selected_storage != STORAGE0)) {
			time_flag = 0;
			
			itoa(Volt_to_temperature(ADC_converting_value(0)), temperDB_current[selected_storage], 10);
			if(cooling_flag[selected_storage] == 1) {
				if(atoi(temperDB_current[selected_storage]) > atoi(temperDB_desire[selected_storage])) //현재 온도가 희망 온도보다 높으면
				{
					PORTD |= 1 << PORTD2;
				}
				else PORTD &= ~(1 << PORTD2);
				
			}
			else PORTD &= ~(1 << PORTD2);
		}
		
		if(mode == SELECT_STORAGE_MODE) SelectStorage_Porcess();
		else if(mode == STOCK_MANAGER_MODE) StockManage_Process();
		else if(mode == TEMPER_MANAGER_MODE) TemperManage_Process();
	
		
		Command_Process();
		
	
    }
}

void StorageController_Init(void) {
	
	Timer0_init();
	FND4digit_init_shiftR();
	
	Keypad_init();
	ADC_init();
	UART0_init(9600);
	
	DDRD |= 1 << PORTD3 | 1 << PORTD2; //LED RED, LED YELLOW
	PORTD &= ~(1 << PORTD3 | 1 << PORTD2);
	DDRB |= 1 << PORTB5 | 1 << PORTB4; //LED BLUE , LED GREEN
	PORTB &= ~(1 << PORTB5 | 1 << PORTB4);
	
	
	sei();
	  
	return;
}

void Command_Process(void) {
	if(RX_cmd_count) {
		RX_cmd_count--;
		
		if(!strcmp(buffer[cmd_idx], "ready")) {
			FND_update_value(4000);
			mode = READY_MODE;
		}
		else if(!strcmp(buffer[cmd_idx], "select storage")) { //strcmp() : 비교해서 같으면 return 0
			FND_update_value(1000);
			printf("select storage mode");
			mode = SELECT_STORAGE_MODE;
			
		}
		else if(!strcmp(buffer[cmd_idx], "stock manager")) {
			FND_update_value(2000);
			print_stock(selected_storage);
			mode = STOCK_MANAGER_MODE;
			
		}
		
		else if(!strcmp(buffer[cmd_idx], "temper manager")) {
			FND_update_value(3000);
			itoa(Volt_to_temperature(ADC_converting_value(0)), temperDB_current[selected_storage], 10);
			
			printFND_temper(atoi(temperDB_current[selected_storage])); //현재 온도 출력
			mode = TEMPER_MANAGER_MODE;
			print_temper();
		}
		
		else if((!strcmp(buffer[cmd_idx], "storage1")) && (mode == SELECT_STORAGE_MODE)) { //strcmp() : 비교해서 같으면 return 0
			selected_storage = STORAGE1;
			Storage_LED_On(selected_storage);
		}
		else if((!strcmp(buffer[cmd_idx], "storage2")) &&  (mode == SELECT_STORAGE_MODE)) {
			selected_storage = STORAGE2;
			Storage_LED_On(selected_storage);
		}
		else if((!strcmp(buffer[cmd_idx], "storage3"))  &&  (mode == SELECT_STORAGE_MODE)) {
			selected_storage = STORAGE3;
			Storage_LED_On(selected_storage);
		}
		
	
		
		delay_flag = 1;
		while(delay_flag);
		cmd_idx++;
		cmd_idx = cmd_idx % COMMAND_MAX; //cmd_idx가 COMMAND_MAX 안넘어가게
		
	}
	
	return;
}

void SelectStorage_Porcess(void){
	
	Command_Process();
	
	return;
}

void StockManage_Process(void) {
	int stock_count = atoi(stockDB_count[selected_storage]);
	
	while(1) {
		if(long_key_flag) {
			if(Keyscan_sub() != 0)
			{
				_delay_us(200);
				if (Keyscan_sub() != 0)
				{
					
					if(Keyscan_sub() == 1) {
						stock_count++;
						if(stock_count >= 10000) stock_count = 9999;
						FND_update_value(stock_count);
						long_key_flag = 0;
					}
					else if(Keyscan_sub() == 2)
					{
						stock_count--;
						if(stock_count <= -1) stock_count = 0;
						FND_update_value(stock_count);
						long_key_flag = 0;
					}
					else if(Keyscan_sub() == 4) {
						itoa(stock_count, stockDB_count[selected_storage], 10);
						long_key_flag = 0;
						break;
					}
				}
				
			}
		}
		else
		{
			if(Keyscan_sub() == 0)
			{
				long_key_flag = 1;
			}
		}
		_delay_ms(10);
	}

	mode = READY_MODE;
	print_stock(selected_storage);
	
	return;
}

void TemperManage_Process(void){
	int desire_temp = atoi(temperDB_desire[selected_storage]);
	
	while(1) {
		if(long_key_flag) {
			if(Keyscan_sub() != 0)
			{
				_delay_us(200);
				if (Keyscan_sub() != 0)
				{
					
					if(Keyscan_sub() == 1) {
						desire_temp++;
						if(desire_temp >= 31) desire_temp = 30;
						printFND_temper(desire_temp);
						long_key_flag = 0;
					}
					else if(Keyscan_sub() == 2)
					{
						desire_temp--; 
						if(desire_temp <= -26) desire_temp = -25;
						printFND_temper(desire_temp);
						long_key_flag = 0;
					}
					else if(Keyscan_sub() == 4) {
						cooling_flag[selected_storage] = 1;
						long_key_flag = 0;
						break;
					}
				}
				
			}
		}
		else
		{
			if(Keyscan_sub() == 0)
			{
				long_key_flag = 1;
			}
		}
		_delay_ms(10);
	}
	itoa(desire_temp,temperDB_desire[selected_storage],10);
	
	printf("\r\n");
	
	mode = READY_MODE;
	print_temper();
	
	return;
}

void Storage_LED_On(char storage) {
	if(storage == STORAGE1) {
		//RED
		PORTD |= 1 << PORTD3;
		PORTB &= ~(1 << PORTB4);
		PORTB &= ~(1 << PORTB5);
	}
	else if(storage == STORAGE2) {
		//BLUE
		PORTD &= ~(1 << PORTD3);
		PORTB &= ~(1 << PORTB4);
		PORTB |= 1 << PORTB5;
	}
	else if(storage == STORAGE3) {
		//GREEN
		PORTD &= ~(1 << PORTD3);
		PORTB |= 1 << PORTB4;
		PORTB &= ~(1 << PORTB5);
	}
	
	return;
}

void print_stock(char storage) {
	printf(" ");
	printf(stockDB_name[storage]);
	printf(" ");
	printf(stockDB_date[storage]);
	printf(" ");
	printf(stockDB_count[storage]);
	//printf(" ");
	printf("\n");
	
	return;
}

void print_temper(void) {
	//TX0_char('a');
	TX0_string(" current temper : ");
	//printf(" current temper : ");
	TX0_string(temperDB_current[selected_storage]);
	//printf("", );
	TX0_string(", desire temper : ");
	//printf(", desire temper : ");
	TX0_string(temperDB_desire[selected_storage]);
	TX0_string("\n");
	return;
}
void printFND_temper(int temp) {
	
	
	if(temp >= 0) {
		FND_update_value(temp);
		FND[2] = 255;
		//FND[3] = FND4digit_font[COOLING];
	}
	else{
		//temp = temp * -1; //양수 값으로 변경
		FND_update_value(temp * -1);
		FND[2] = ~(1 << FND_g);
		//FND[3] = FND4digit_font[COOLING];
	}
	
	return;
}

