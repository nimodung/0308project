/*
 * arduino_uno.c
 *
 * Created: 2019-04-03 오후 2:03:18
 * Author : user
 */ 

#include <avr/io.h>
#define F_CPU 16000000UL
#include <util/delay.h>
#include <avr/interrupt.h>

//C언어 문법. 프로토타입 선언을 해줘야함. extern으로 외부에 선언했다는걸 알림.
//extern void Ledbar_init(void);
//atmelstudio는 프로토타입 선언 안해도 실행된다.
//프로토 타입 선언할게 많으니까 헤더파일을 작성해서 include하는 방식.
//#include "LEDBar.h"
//#include "FND.h"
//#include "Dot_matrix.h"
//#include "FND4digit.h"
//#include "Timer.h"
#include "Uart.h"
//#include "Keypad.h"
//#include "HC_SR04.h"
//#include "DHT11.h"
//#include "ADC.h"

//extern char FND_B[4], FND_C[4];
//extern char FND4digit_digit[4];

//extern int Speakr_main(void);

extern int Bluetooth_main(void);
extern int Temp_submotor_main(void);
int main(void)
{
	//FND_init();
	//FND4digit_init();
	
	sei();				//먼저해야징 fnd4digit_main()들어가면 while에서 못나옴
	//FND4digit_main();
	//Bluetooth_main();
	//Dot_matrix_main();
	//Timer_main();
    Uart_main();
	//Keypad_main();
	//HC_SR04_main();
	//DHT11_main();
	//ADC_main();
	//Measure_main();
	//Speakr_main();
	while (1)
    {
	    //FND_test();
	    //FND4digit_test();
		//FND_update_value(1234);
		//for(int i = 0; i < 4; i++)//
		//{
 			
 		//	FND_COM_PORT |= FND4digit_digit[2];
 		//	FND_DATA_PORT = FND_B[2];
 			//FND_COM_PORT |= FND_C[2];
			//k = k/10;
			//_delay_ms(2);
		//}
    }
}





