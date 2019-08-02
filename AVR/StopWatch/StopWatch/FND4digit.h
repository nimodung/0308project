/*
 * FND4digit.h
 *
 * Created: 2019-04-05 오후 2:52:22
 *  Author: user
 */ 


#ifndef FND4DIGIT_H_
#define FND4DIGIT_H_

#define FND_COM_DDR DDRB
#define FND_COM_PORT PORTB
#define FND_DATA_DDR DDRD
#define FND_DATA_PORT PORTD

#define FND_e 0
#define FND_d 1
#define FND_p 2
#define FND_c 3
#define FND_g 4
#define FND_a 5
#define FND_f 6
#define FND_b 7

#define digit4 1
#define digit3 2
#define digit2 3
#define digit1 0

void FND4digit_init(void);
void FND4digit_test(void);
void FND_update_value(int number);
void FND_update_time(int msec, char sec);
void FND_clock(char sec, char min);



#endif /* FND4DIGIT_H_ */