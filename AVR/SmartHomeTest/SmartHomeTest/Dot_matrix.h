/*
 * Dot_matrix.h
 *
 * Created: 2019-04-09 오후 5:07:43
 *  Author: hee ram
 */ 


#ifndef DOT_MATRIX_H_
#define DOT_MATRIX_H_

#define Myhome_DATA_DDR DDRC
#define Myhome_DATA_PORT PORTC
#define RClk PORTC4 // 래치핀
#define SRClk PORTC5 // 시프트 클럭
#define SER PORTC3

void Myhome_dotmatrix_init_shiftR(void);
void Dotmatrix_flow(void);
void Dotmatrix_flow_left_out(void);
void Dotmatrix_flow_left_in(void);
void Dotmatrix_shift_out(uint32_t data);


#endif /* DOT_MATRIX_H_ */