/*
 * DotmatrixController.c
 *
 * Created: 2019-07-30 오후 1:43:11
 * Author : KIM HEERAM
 */ 

#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>
#include <avr/interrupt.h>
#include "Uart.h"
#include "Timer.h"

#define Dot_DDR DDRC
#define Dot_PORT PORTC
#define RClk PORTC4
#define SRClk PORTC5
#define SER PORTC3

#define MOVESTOP 0
#define MOVELEFT 1
#define MOVERIGHT 2
#define MOVEUP 3
#define MOVEDOWN 4

const char KIM[8] = {
	0b10001101, 0b11101101, 0b11011101, 0b10111101, 0b11111111, 0b11000011, 0b11011011,0b11000011
};

const char HEE[8] = {
	0b11001111, 0b10000101, 0b11111101, 0b11001101, 0b10110101, 0b11001101, 0b11111101, 0b10000011
};
const char RAM[8] = {
	0b10001011, 0b11101001, 0b10001011, 0b10111011, 0b10001111, 0b11110001, 0b11110101, 0b11110001
};
volatile unsigned char TempArr[8]= {
	0b00000000, 0b00000000,0b00000000,0b00000000,0b00000000,0b00000000,0b00000000, 0b00000000
};
uint8_t dotmatrix[8]; //가상의 DotMatrix
uint8_t dotmatrix_data[8];

extern volatile char RX_data, count;
extern volatile char RX_flag, RX_cmd_count;
extern char buffer[COMMAND_MAX][BUFFER_MAX]; 

char moveMode = MOVESTOP;
int main(void)
{
	char cmd_idx = 0;
	char* str ="11110000";
	DDRB |= 1 << PORTB5;
	PORTB &= ~(1 << PORTB5);
    Dotmatrix_init_shiftR();
	UART0_init(9600);
//	_delay_ms(1);
	Timer0_init();
	sei();
	
	
    while (1) 
    {
		
		
		/*
		for(int j = 0; j < 8; j++) {
			for(int i = 0; i < 8; i++) {
				
				if(str[i] == '0') {KIM2[j] |= 0b00000000;}
				else {
					KIM2[j] |= (0b00000001 << (7-i));
				}
			}
			dotmatrix[j] = KIM2[j];
			KIM2[j] = 0b00000000;
		}
		*/
		
		
		
		if(RX_cmd_count) {
			RX_cmd_count--;
			char* cmdtok = strtok(buffer[cmd_idx], " ");
			
			
			//printf(buffer[cmd_idx]);
			if(!strcmp(cmdtok, "dotTrans")) {
				while(cmdtok != NULL) {
					cmdtok = strtok(NULL, "/");
					if(cmdtok != NULL) {
						for(int i = 0; i < 8; i++) {
							if(cmdtok[i] == '0') {TempArr[count] |= 0b00000000;}
							else {TempArr[count] |= (0b00000001 << (7-i));}
						}
						dotmatrix_data[count] = TempArr[count];
						dotmatrix[count] = dotmatrix_data[count];
						TempArr[count] = 0b00000000;
						
						count++;
						if(count >= 8) count = 0;
					}
				}
				
			}
			else if(!strcmp(cmdtok, "dotClear")){
				for(int i = 0; i < 8; i++){
					dotmatrix[i] = 0xff;
					dotmatrix_data[i] = 0xff;
				}
			}
			
			else if(!strcmp(cmdtok, "moveLeft")){
				moveMode = MOVELEFT;
			}
			else if(!strcmp(cmdtok, "moveRight")){
				moveMode = MOVERIGHT;
			}
			else if(!strcmp(cmdtok, "moveStop")){
				moveMode = MOVESTOP;
				for(int i = 0; i < 8; i++) {
					dotmatrix[i] = dotmatrix_data[i];
				}
			}
			else if(!strcmp(cmdtok, "moveUp")){
				moveMode = MOVEUP;
				
			}
			else if(!strcmp(cmdtok, "moveDown")){
				moveMode = MOVEDOWN;
				
			}
			//_delay_ms(500);
			cmd_idx++;
			cmd_idx = cmd_idx % COMMAND_MAX;
		}
		
		if(moveMode == MOVELEFT) {
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					dotmatrix[j] = (dotmatrix_data[j] << i) | (dotmatrix_data[j] >> (8-i));
				}
				_delay_ms(100);
			}
		}
		else if(moveMode == MOVERIGHT) {
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					dotmatrix[j] = (dotmatrix_data[j] << (8-i)) | (dotmatrix_data[j] >> i);
				}
				_delay_ms(100);
			}
		}
		else if(moveMode == MOVEUP){
			char count = 0;
			for(int k = 0; k < 8; k++) {
				count = k;
				for(int j = 0; j< 8; j++) {
					count++;
					if(count >= 8) count = 0;
					dotmatrix[j] = dotmatrix_data[count];
					
				}
				_delay_ms(100);
			}
		}
		else if(moveMode == MOVEDOWN){
			char count = 0;
			
			for(int i = 7; i >= 0; i--) {
				count = i;
				for(int j = 0; j < 8; j++) {
					dotmatrix[j] = KIM[count];
					count++;
					if(count >= 8) count = 0;
					
				}
				_delay_ms(100);
			}
		}
		
		
    }
}

void Dotmatrix_init_shiftR(void)
{
	Dot_DDR |= 1 << RClk | 1 << SRClk | 1 << SER; //595 3bit 연결 // 3, 4,5번 연결
	Dot_PORT |= 1 << RClk; // 0b00010000; //RClk : 1 //RClk을 상승 edge로 둬서 다른 데이터가 들어가지 않게
	
	for(int i = 0; i < 8; i++)
		dotmatrix[i] = 0xff;
	
	return;
}

void Dotmatrix_shift_out(uint8_t data, uint8_t comm)
{
	
	Dot_PORT &= ~(1 << RClk);
	for(int i = 0; i < 8; i++) //data 한개씩 넣는걸 8번
	{
		Dot_PORT &= ~(1 << SRClk);
		Dot_PORT &= ~(1 << SER); //일단 0으로 떨어트려
		Dot_PORT |= ((data >> i) & 1) << SER; //받아온 data 값을 0 아니면 1
		Dot_PORT |= 1 << SRClk;//0b00100000; //상승 edge
	}
	
	for(int i = 0; i < 8; i++) //data 한개씩 넣는걸 8번
	{
		Dot_PORT &= ~(1 << SRClk);
		Dot_PORT &= ~(1 << SER); //일단 0으로 떨어트려
		Dot_PORT |= ((comm >> i) & 1) << SER; //받아온 data 값을 0 아니면 1
		Dot_PORT |= 1 << SRClk;//0b00100000; //상승 edge
	}
	
	Dot_PORT |= 1 << RClk; //RClk 상승 edge
	return;
}