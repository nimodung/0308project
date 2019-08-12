/*
 * Dot_matrix.c
 *
 * Created: 2019-04-08 오전 10:50:17
 *  Author: hee ram
 */ 
#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>
#include "Dot_matrix.h"

uint32_t dotmatrix[8];
uint32_t dotmatrixdata[8];

extern volatile char delay_flag; 

const char last_data[8] = { //은
	0b11100111,0b11011011,0b11100111,0b11111111,0b10000001,0b11111111,0b11011111,0b11000011};
const char middle_data[8] = {//주
	0b11000111,0b11101111,0b11010111,0b11111111,0b10000011,0b11101111,0b11101111,0b11101111};
const char first_data[8] = {//집
	0b11000101,0b11101101,0b11010101,0b11111101,0b11011011,0b11000011,0b11011011,0b11000011};

void Myhome_dotmatrix_init_shiftR(void){                        //도트매트릭스 이름변경 포트c로 바꾸기 , DDRC,PORTC
	Myhome_DATA_DDR |= 1 << RClk | 1 << SRClk | 1 << SER;
	Myhome_DATA_PORT |= 1 << RClk;
	for(int i=0; i<8; i++)
	{
		dotmatrix[i] = 0;
	}
	return;
}


void Dotmatrix_flow(void){
	
	for(int j=0;j<24;j++){
		
		for(int i=0;i<8;i++){
			dotmatrix[i] = (0x01000000 << i) | ((~(~dotmatrixdata[i] << j) & 0x00ffffff));
		}
		delay_flag = 0;
		while (!delay_flag);
		
	}
	for(int j=0;j<24;j++){
		
		for(int i=0;i<8;i++){
			dotmatrix[i] = (0x01000000 << i) | ((~(~dotmatrixdata[i] >> (23-j)) & 0x00ffffff));
		}
		delay_flag = 0;
		while (!delay_flag);
	}
	
}
void Dotmatrix_flow_left_out(void){
	for(int j=0;j<24;j++){
		//if(delay_flag){
			for(int i=0;i<8;i++){
				dotmatrix[i] = (0x01000000 << i) | ((~(~dotmatrixdata[i] << j) & 0x00ffffff));
			}
		//}
		delay_flag = 0;
		while(!delay_flag);
	}
}

void Dotmatrix_flow_left_in(void){
	for(int j=0;j<24;j++){
		//if(delay_flag){
			for(int i=0;i<8;i++){
				dotmatrix[i] = (0x01000000 << i) | ((~(~dotmatrixdata[i] >> (23-j)) & 0x00ffffff));
			}
		//}
		delay_flag = 0;
		while(!delay_flag);
	}
}




void Dotmatrix_shift_out(uint32_t data){
	Myhome_DATA_PORT &= ~(1 << RClk);
	for(int i=0;i<32;i++){
		Myhome_DATA_PORT &= ~(1 << SRClk);
		Myhome_DATA_PORT &= ~(1 << SER);
		Myhome_DATA_PORT |= ((data>>i) & 1) << SER;
		Myhome_DATA_PORT |= 1 << SRClk;
	}
	Myhome_DATA_PORT |= 1 << RClk;
	return;
}
