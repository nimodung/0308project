#include <stdio.h>

int main() {
    
    unsigned int a = 0xFF;
    printf("%d를 오른쪽 1회 시프트하면 %x 이다. \n", a, a>>1);
    printf("%d를 오른쪽 2회 시프트하면 %x 이다. \n", a, a>>2);
    printf("%d를 오른쪽 3회 시프트하면 %x 이다. \n", a, a>>3);
    printf("%d를 오른쪽 4회 시프트하면 %x 이다. \n", a, a>>4);
} //float은 shift 연산에 사용x  정수값만 사용
