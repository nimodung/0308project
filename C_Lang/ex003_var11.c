#include <stdio.h>

int main() {
    
    int input_number;
    
    scanf("%d",&input_number);
    printf("500�� ���� : %d�� \n ", input_number/500);

    input_number = input_number % 500;
    printf("\n 100�� ���� : %d�� \n", input_number/100);

    input_number = input_number % 100;
    printf("50�� ���� : %d�� \n", input_number/50);

    input_number = input_number % 10;
    printf("10�� ���� : %d�� \n " , input_number/10);
    
    return 0;

} 