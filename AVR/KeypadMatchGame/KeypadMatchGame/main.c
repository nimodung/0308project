/*
 * KeypadMatchGame.c
 *
 * Created: 2019-05-21 오후 12:16:31
 * Author : tjoeun
 */ 

#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>
#include <avr/interrupt.h>
#include <stdlib.h> //rand()함수와 srand()함수를 사용할 수 있는 라이브러리

#include "FND4digit.h"
#include "Keypad.h"
#include "Speaker.h"
#include "Timer.h"

#define WORD_DISMATCH 1
#define WORD_MATCH 2

#define GAME_READY 3
#define GAME_START 4
#define GAME_END 5


void words_Shuffle(void);

extern char FND4digit_font_alphabet[25], FND4digit_font[10];
extern char FND[], sec, min;
extern volatile int msec, music_i;
extern volatile char startInfo_flag, time_flag, sucess_flag, print_sucess_flag, start_flag,
	 beep_flag, right_flag, fail_flag, sndsucess_flag, speakout_flag;

char word[] = {"cold"};
char *words[] = {"cold", "math", "font", "road", "bear", "pair", "note","code"};
char start_game[] = {"    pair game    "};

char key_words[16] = {0 ,};//단어 index 랜덤 저장
	
char disable_keypad[16] = {0, }; //단어가 매치상태인지 저장

char match_1 = NULL, match_2 = NULL; 	
char game_state = GAME_READY;
char gamesucess_count = 0;


int main(void)
{
	char long_key_flag = 0;
	int i =0;
	Timer0_init(); 
	Timer1_init_CTC_outA(); //speaker
    FND4digit_init_shiftR();
	Keypad_init();
		
	sei();
	
	
	while (1) 
    {
		if(startInfo_flag) print_FND_word_flow(start_game);
		
		if(sucess_flag) {
			if(print_sucess_flag) {
				
				FND_clock(sec, min);
				
			}
			else {
			
				FND_off();
			}
		}

		
		if(!startInfo_flag && game_state == GAME_READY) 
		{
			FND_off();
			words_Shuffle();
			game_state = GAME_START;
			start_flag = 1;
		}
		
		if(!right_flag && !fail_flag && !sndsucess_flag) {music_i = 0; OCR1A = 0;}
	
		if(long_key_flag)
		{
			_delay_us(200);
			if(Keyscan() != 'A')
			{
				beep_flag = 1;
				if(game_state == GAME_START) {
					if(Keyscan()) {
						
						if(disable_keypad[Keyscan() - 'B'] == WORD_DISMATCH){
								
							FND_update_word(words[key_words[Keyscan() - 'B']]);
							keypad_Click(Keyscan());
								
							long_key_flag = 0;
						}
							
						else if(disable_keypad[Keyscan() - 'B'] == WORD_MATCH){
							FND_off();
							long_key_flag = 0;
						}
						
					}
				}
				else if(game_state == GAME_END) {
					if(Keyscan()) {
						beep_flag = 1; 
						game_state = GAME_READY; sucess_flag = 0; startInfo_flag = 1;
						msec = 0; sec = 0; min = 0;
						long_key_flag = 0;
					}
					
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
		_delay_ms(10);	
		
    }
}

void print_FND_word_flow(char *words) {
	char long_key_flag = 0;
	
	_delay_us(200);
	for(int j = 0; j < 13; j++) {
		for(int i = 0; i < 4; i++) {
			/*if(*(words + i + j) == ' ') {
				FND[3-i] = 255;
			}
			else {
				FND[3-i] = FND4digit_font_alphabet[*(words +i +j) - 'a'];
			}*/
			if(!time_flag) FND[3-i] = (*(words + i + j) == ' ')?255:FND4digit_font_alphabet[*(words +i +j) - 'a'];
			
			if(long_key_flag)
			{	
				if(Keyscan() != 'A')
				{
					startInfo_flag = 0;
					long_key_flag = 0;
					return; 
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
		time_flag = 1;
		while(time_flag);	
	}
		
	return;
	
}

void words_Shuffle(void) {
	int x, y, temp = 0;
	
	for(int j = 0; j < 16; j++) {
		key_words[j] = j/2;	
		disable_keypad[j] = WORD_DISMATCH;
	}
	
	//srandom(msec);
	for(int i = 0; i < 100; i++) {
		x = random()%16; //0~15 
		y = random()%16;
		
		 
		temp = key_words[x];
		key_words[x] = key_words[y];
		key_words[y] = temp;
	}
	
	return;
}

void keypad_Click(char key) {
	
	if(match_1 == NULL) {
		match_1 = key;
		FND_update_word(words[key_words[Keyscan() - 'B']]);
	}
	else {
		if(match_2 == NULL) {
			if(match_1 != key) {
				match_2 = key;
				FND_update_word(words[key_words[Keyscan() - 'B']]);
				checkMatch();
				
			}
		}
	}
	
	return;
}
void checkMatch(){ //match_1, match_2 : keypad 위치
		
	if(!strcmp(words[key_words[match_1 - 'B']], words[key_words[match_2 - 'B']])) {
				
		disable_keypad[match_1 - 'B'] = WORD_MATCH;
		disable_keypad[match_2 - 'B'] = WORD_MATCH;
		gamesucess_count++;
		if(gamesucess_count == 8) {
			sucess_flag = 1; gamesucess_count = 0;
			game_state = GAME_END; start_flag = 0;
			sndsucess_flag = 1; match_1 = NULL; match_2 = NULL;
			return;
		}
		
		time_flag = 1;
		while(time_flag);
		FND_off();
		FND_update_word("good");
		right_flag = 1;
		match_1 = NULL; match_2 = NULL;
		
		
	}
	else {
				
		disable_keypad[match_1 - 'B'] = WORD_DISMATCH;
		disable_keypad[match_2 - 'B'] = WORD_DISMATCH;
		
		time_flag = 1;
		while(time_flag);
		FND_off();
		FND_update_word("fail");
		fail_flag = 1;
		match_1 = NULL; match_2 = NULL;
		
	}
	

}