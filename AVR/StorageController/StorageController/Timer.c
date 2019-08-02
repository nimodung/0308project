/*
 * Timer.c
 *
 * Created: 2019-04-10 오전 11:04:02
 *  Author: user
 */ 

#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>
#include <avr/interrupt.h> // interrupt 사용하기 위함
//#include "Dot_matrix.h"
#include "FND4digit.h"
#include "Timer.h"

#include "Uart.h"

//Dot_matrix
/*
extern char dotmatrix_row[8]; //Dot_matrix.c에서 선언한 dotmatrix_row 배열 변수에 접근할 수 있게된다.
extern const char KIM[8];
extern const char HEE[8];
extern const char RAM[8];
extern char name[3][8];
*/
//FND4digit
extern char FND4digit_digit[4];
extern char FND[4];


volatile char start_flag=1, w_flag, delay_flag, time_flag;		//전역 변수는 초기화 안하면 0으로 초기화된다
volatile int msec = 0, delay_msec = 0;				
volatile char i = 0, sec = 0, min = 0;							//volatile : 워킹레지스터에 선언하지말고 RAM에다가 할당하라는 의미(휘발성)
																			//전역변수 선언시에 많이 사용

//isr은 가급적 빨리 실행해야 되기 때문에 딜레이 x, 함수호출x ...
ISR(TIMER0_COMPA_vect)						//interrupt 걸리면 실행하는 내용 //Interrupt Service Routine
{	
						
	//static int msec = 0;					//static -> 처음 할당될때 메모리 받아서 반납하지 않음
	//static char i = 0, sec = 0, min = 0;
	msec++;
	if(delay_flag) delay_msec++;	
	if(msec >= 1000)
	{
		msec = 0;
		time_flag = 1;
	}
	if(delay_msec >= 500) {
		delay_msec = 0;
		delay_flag = 0;
	}
	
/*	
	if(!(msec % 10)) 
	{
		if(w_flag)
			time_flag = 1;
	}
	
	if(clear_flag)
	{
		clear_flag = 0;
		FND_update_time(msec, sec);
		msec = 0;
		sec = 0;
		min = 0;
	}
*/
	//FND를 실제로 출력시키는 
	i++;
 	
 	if(i >=4) i = 0;
	
	//끄고 데이터 주고 끄고
	FND_COM_PORT &= 0b00001111; //상위 4비트 끄고
	FND_shift_out(FND[i]);
	FND_COM_PORT |= FND4digit_digit[i]; //< FND_shift_out보다 먼저하면 이전 데이터가 출력돼서 정확한 출력이 나오지 않음
	//FND_DATA_PORT = FND[i];
	
	

	//dot_matrix 출력	
	/* dot_matrix 출력
		dot_matrix_comm_low_PORT &= 0b11110000;
		dot_matrix_comm_high_PORT &= 0b11110000;
		dot_matrix_comm_high_PORT |= 1 << (i-4);
		dot_matrix_data_PORT = dotmatrix_row[i];
	*/
	
	//if(!(msec % 500))						//아두이노 우노 상의 led 출력 //인터럽트 잘 걸리는지 확인용
	//	PORTB ^= (1<<PORTB5);				// (1<<PORTB5) == 0b00100000 //토글 켜져있으면 끄고 꺼져있으면 켠다
} 

int Timer_main(void)
{
	//char long_key_flag = 1;
	char blue_up_down_flag = 1; //1 : up , 0 : down
	char red_up_down_flag = 1;
	char green_up_down_flag = 1, sub_motor_flag = 1;
	
	char green = 0, red = 0, blue = 0;
	
	char tmpr= 0, rh = 0, old_tmpr = 0;
	char count = 0;
	
	//포트 b의 5번핀만 set -> 출력으로 설정//PORTB5는 5로 define 되어있음
	//dotmatrix_init();
	//FND4digit_init();
	//DDRB &= ~(1<<PORTB4 | 1<<PORTB5); //버튼 
	//FND_update_value(0);
	//Timer1_init_fast_PWM_outA_SurvoMotor();
	Timer1_init_fast_PWM_outA();
	Timer2_init_fast_PWM_outA();
	FND4digit_init_shiftR();
	Timer0_init();							//8bit 메모리 => 0~255까지 셀 수 있음
	//DHT11_init();
	UART0_init(9600);
	
	//Timer0_init_CTC_outA();
	sei();									//전체(글로벌) interrupt on/off (interrupt 활성화) 
										//cli() : interrupt 해제 / 처리중 interrupt가 들어오면 안될때 강제적으로 막을때 사용
													//인터럽트 서비스 루틴에서 현재 서비스 루틴이 다른 경로에의해 간섭이나 처리에 영향을 받지않도록 cli로막는다
	
	//OCR1B = 20;
	while(1)
	{ 
		
		
		
/*
		count++;
		if(count >= 100) {
			count = 0;
			old_tmpr = tmpr;
			tmpr = getTemperature();
			if(tmpr != 255)
			{
				printf("Temperature : %d \n", tmpr);
				FND_update_value(tmpr);
				OCR1B = tmpr * 8 / 11 + 142;
			}
			else {
				printf("Temperature : %d \n", old_tmpr);
				FND_update_value(old_tmpr + 1000);
				OCR1B = old_tmpr * 8 / 11 + 142;
			}*/
			//rh = getHumi();
			//_delay_ms(1500);
			 
// 			if(OCR1B >= 210) OCR1B = 209;
// 			if(OCR1B <= 120) OCR1B = 121;
	
		
		//_delay_ms(30);
	
		/*OCR1B = Tmpr_integral;
		
		
 		*/
		
	
		/*if(green_up_down_flag) {
			green += 4;
			
			if(green >= 150) green_up_down_flag = 0;
		}
		else {
			green -= 4;
			
			if(green <= 0) green_up_down_flag = 1;
		}
		
		if(red_up_down_flag){
			red++;
			
			if(red >= 200) red_up_down_flag = 0;
		}
		else {
			red--;
		
			if(red <= 120) red_up_down_flag = 1;
		}
		
		
		if(blue_up_down_flag){
			blue += 2;
			
			if(blue >= 150) blue_up_down_flag = 0;
		}
		else {
			blue -= 2;
			
			if(blue <= 0) blue_up_down_flag = 1;
		}
		
		OCR1A = blue;
		OCR1B = red;
		OCR2A = green;
		FND_update_value(OCR1B);
		_delay_ms(100);*/
		//3색 LED 불 켜기
		/*if(blue_up_down_flag){
			i++;
			OCR1A = 1000 - i;
			FND_update_value(OCR1A);
			if(i >= 1000) blue_up_down_flag = 0;
		}
		else {
			i--;
			OCR1A = 1000 - i;
			FND_update_value(OCR1A);
			if(i <= 0) blue_up_down_flag = 1;
		}
		
		if(red_up_down_flag){
			j++;
			OCR1B = 1000 - j *2;
			//FND_update_value(OCR1B);
			if(j >= 500) red_up_down_flag = 0;
		}
		else {
			j--;
			OCR1B = 1000 - j *2;
			//FND_update_value(OCR1B);
			if(j <= 0) red_up_down_flag = 1;
		}
		
		_delay_ms(1);*/
		
		
		//ISR에서 업데이트 해주기 때문에 필요 없어짐
		/*
		_delay_ms(1);
		msec++;
		if(msec >= 1000)
		{
			msec = 0;
			sec++;
		}
		
		FND_update_time(msec, sec);
		*/
		//dotmatrix 출력
		/*
		dotmatrix_flow_buffer(name, 3);
		for(int i = 0; i <3; i++)
		{
			dotmatrix_update(name[i]);
			_delay_ms(500);
		}
		*/
		//stopwatch < fnd4digit
		/*
		if(long_key_flag) //long_key_flag가 1 일때 if문 실행
		{
			if(!(PINB & 0b00010000)) //스위치를 눌렀을 때 -> 연산 결과가 0일때 //pinb = 00000000 -> pinb & 00010000 = 0 의 논리 반전 => 참
			{   //KEY1  //(PINB & 0b00010000) == 0
				//PINB & 0b00010000 != 0b00010000 
				if(start_flag) start_flag = 0;
				else start_flag = 1;
				
				//start_flag = 1; //if(!start_flag) start_flag = 1;
				
				//if(w_flag) w_flag = 0;
				//else w_flag = 1;
				
				long_key_flag = 0;
			}
			else if(!(PINB & 0b00100000))
			{ //KEY2
				FND_clock(sec, min);
				start_flag = 0;
				msec = 0;
				sec = min = 0;
				//w_flag = 0;
				long_key_flag = 0;
			}
		}
		else //long_key_flag 가 1일 때
		{
			if((PINB & 0b00010000) && (PINB & 0b00100000)) //스위치를 안누를 때 -> 연산 결과가 16 //pinb = 00010000
			{
				long_key_flag = 1;
			}
			
		}
		*/
		//Timer
		/*if(time_flag) 
		{
			FND_update_time(msec, sec);
			time_flag = 0;
		}
		
		if(long_key_flag)
		{
			if(!(PINB & 0b00010000))
			{
				_delay_ms(1);
				if(!(PINB & 0b00010000))
				{
					start_flag = 1;
					if(w_flag) w_flag = 0;
					else w_flag = 1;
					long_key_flag = 0;
				}
				
			}
			else if(!(PINB & 0b00100000))
			{
				_delay_ms(1);
				if(!(PINB & 0b00100000))
				{
					start_flag = 0;
					w_flag = 0;
					clear_flag = 1;
					long_key_flag = 0;
				}
			}
		}
		else
		{
			if((PINB & 0b00010000) && (PINB & 0b00100000))
				long_key_flag = 1;
		}*/
	
	
	}
	
	return 0;
}

/* TIMSK0 – Timer/Counter Interrupt Mask Register
	어떤 interrupt 걸리게 할건지 정하는
	비교 매치 b, 비교 매치 a, overflow
*/
/* TIFR0 – Timer/Counter 0 Interrupt Flag Registe
	interrupt 발생하면 flag 1
	자동으로 / 상태 register
*/

void Timer0_init(void)
{
	//타이머 초기화
	TCCR0A |= 1 << WGM01;					//0b00000010;  //CTC Mode
	TCCR0B |= (1 << CS00 | 1 << CS01);		//0b00000011;  //64분주 사용(하위 3bit : 011)
	OCR0A = 249;							//비교할 값 => (16,000,000 / 64) / 1000 = 250 (0 ~ "249")			//1ms 주기
	TIMSK0 |= 1 << OCIE0A;                  //0b00000010; //어떤 interrupt 걸리게 할건지 정하는	// OC0A 비교 매치 인터럽트 활성화
	
	return;
}

void Timer0_HC_SR04_init(void)
{
	TCCR0A |= 1 << WGM01;					//CTC 모드
	TCCR0B |= (1 << CS02) | (1 << CS00);	//1024분주
	OCR0A = 255;							//
	
	return;
}

void Timer0_init_CTC_outA(void) //A 출력을 쓰기위한 초기화 함수
{
	DDRD |= 1 << PORTD6;
	TCCR0A |= 1<< WGM01 | 1 <<COM0A0;
	TCCR0B |= 1 <<  CS00 | 1 <<CS01;
	
	OCR0A = 30; //4000Hz
	//OCR0A = 63; // 2000Hz
	//OCR0A = 124;							//(250 / 2) - 1 //한 주기를 1msec
	//OCR0A = 249;							//한 주기 2msec //500Hz
	//250Hz를 만들고싶다면? => OCR0A를 늘리면 오버플로우(8bit) => 분주를 늘린다
	//CS0x (분주)랑 OCR값을 이용해서 주기, 주파수 조정
	TIMSK0 |= 1 << OCIE0A;                  //0b00000010; //어떤 interrupt 걸리게 할건지 정하는	// OC0A 비교 매치 인터럽트 활성화
	
	return;
}

void Timer1_init_CTC_outA(void) //A출력을 쓰기위한 초기화 함수
{
	DDRB |= 1 << PORTB1;
	TCCR1A |= 1 << COM1A0; //TOGGLE
	TCCR1B |= 1 << WGM12 | 1 <<  CS11;  //CTC 모드//64분주
	OCR1A = 0; // 소리 끄기
	
	//OCR0A = 30; //4000Hz
	//OCR0A = 63; // 2000Hz
	//OCR0A = 124;							//(250 / 2) - 1 //한 주기를 1msec
	//OCR0A = 249;							//한 주기 2msec //500Hz
	//250Hz를 만들고싶다면? => OCR0A를 늘리면 오버플로우(8bit) => 분주를 늘린다
	//CS0x (분주)랑 OCR값을 이용해서 주기, 주파수 조정
	//TIMSK0 |= 1 << OCIE0A;                  //0b00000010; //어떤 interrupt 걸리게 할건지 정하는	// OC0A 비교 매치 인터럽트 활성화
	
	return;
}

void Timer1_init_fast_PWM_outA(void){
	DDRB |= 1 << PORTB1 | 1<< PORTB2; //outA, 출력
	//MODE : 14, WGM13 : 1, WGM12 : 1, WGM11 : 1, WGM10 : 0 
	// Fast PWM //TOP : ICR1, Update of OCR1x at: BOTTOM, TOV1 Flag Set on : TOP 
	TCCR1A |= (1 << WGM11) | (1 << COM1A1) | (1 << COM1A0) | (1 << COM1B1) | (1 << COM1B0); //COM1A1 : 1, COM1A0 : 0  //OCR값이 커지면 펄스폭이 길어지고
											//Clear OC1A/OC1B on Compare Match, set Clear OC1A/OC1B on Compare Match, set OC1A/OC1B at BOTTOM (non-inverting mode)
	TCCR1B |= (1 << WGM12) | (1 << WGM13) | (1 << CS11);  //CS12 :0, CS11: 1, CS10: 0 //clkI/O/8 (From prescaler) //8분주
	OCR1A = 0; //BOTTOM
	OCR1B = 0;
	ICR1 = 255; //1차이가 크지 않기때문에 보통 9999대신 10000을 쓴다//19999; //0부터 2만개를 COUNT 하겠다는 의미 
}

void Timer2_init_fast_PWM_outA(void){
	DDRB |= 1 << PORTB3; 
	
	TCCR2A |= (1 << COM2A1) | (1 << COM2A0) | (1 << WGM21) | (1 << WGM20); 
	TCCR2B |= (1 << CS20); //모터 구동 시에는 CS20  
	OCR2A = 0; //BOTTOM
	
	//따로 ICR 없음 //0~255까지가 100프로
}

void Timer1_init_fast_PWM_outA_SurvoMotor(void){
	DDRB |= 1 << PORTB1 | 1<< PORTB2; 
	TCCR1A |= (1 << WGM11) | (1 << COM1A1) | (1 << COM1A0) | (1 << COM1B1) | (1 << COM1B0);
	TCCR1B |= (1 << WGM12) | (1 << WGM13) | (1 << CS12);  
	
	OCR1A = 0; 
	OCR1B = 0;
	ICR1 = 255;
}

