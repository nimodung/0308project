/*
 * FND.h
 *
 * Created: 2019-04-04 오후 2:42:57
 *  Author: Kim Hee Ram
 */ 


#ifndef FND_H_ //if not def : 만약 FND_H_가 정의되어있지않으면 ifndef와 endif 사이의 것들을 실행 => fnd.h가 중복 def 되지않게 하기 위함
#define FND_H_

#define FND_DDR DDRD
#define FND_PORT PORTD

#define FND_f 0
#define FND_d 1
#define FND_g 2
#define FND_e 3
#define FND_a 4
#define FND_c 5
#define FND_b 6
#define FND_p 7

void FND_init(void);
void FND_test(void);



#endif /* FND_H_ */
