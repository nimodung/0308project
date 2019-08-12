#include <stdio.h>

int main() {
    int num1, num2;
    char opcode;
    printf("정수 입력 .");
    scanf("%d", &num1);
    printf("두번째 정수 입력 .");
    scanf("%d", &num2);
    printf("%d + %d = %d\n", num1,num2,num1+num2);
    scanf("%d", &num2);
    switch(opcode) {
        
        case '+' : printf("%d +%d = %d\n", num1, num2, num1+num2);break;
        case '-' : printf("%d -%d = %d\n", num1, num2, num1-num2);break;
        case '*' : printf("%d *%d = %d\n", num1, num2, num1*num2); break;
        case '/' : printf("%d /%d = %d\n", num1, num2, num1/num2); break;
        default : printf("연산자를 입력하세요.");
    }
    
    return 0;

    
}
void sub(void){
    int num1, num2;
    printf 
    
    return;
}