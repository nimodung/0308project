#include <stdio.h>

int main() {
    char ch;
    char* p;
    char* q;
    ch ='A';
    p = &ch;
    q = p;
    *q = 'Z';

    printf ("ch�� ������ �ִ� ��: ch ==> %c \n\n", ch);
    
}
