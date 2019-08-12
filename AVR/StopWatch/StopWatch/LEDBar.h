/*
 * LEDBar.h
 *
 * Created: 2019-04-04 오전 10:41:55
 *  Author: Kim Hee Ram
 */ 

//헤더파일이나 디파인 선언한 것들

#ifndef LEDBAR_H_
#define LEDBAR_H_

void Ledbar_init(void);
void Led_up_down(void);
void Led_on_up(void);
void Led_on_down(void);
void Led_off_up(void);
void Led_off_down(void);
void Led_on_increase(void);
void Led_on_decrease(void);

#define LEDBar_DDR DDRD
#define LEDBar_PORT PORTD

#endif /* LEDBAR_H_ */