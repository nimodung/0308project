#include <stdio.h>

int main(){

    float a, b;
    float result;
    scanf("%f", &a);
    scanf("%f", &b);
    result = a + b;
    printf("첫번째 계산할 값을 입력하세요 ==>");
    result = a - b;
    printf("%f - %f = %f \n", a, b, result);
    result = a * b;
    printf("%f * %f = %f \n", a, b, result);
    result = a / b;
    printf("%f / %f = %f \n", a, b, result);

}