#include <stdio.h>

int main() {
    int a, b, c, d;

    a = 100 + 100;
    b = a + 100;
    c = a + b - 100;
    d = a + b +c;
    printf ("a, b, c, d ÀÇ °ª ==> %d, %d, %d, %d", a,b,c,d);

    return 0;
}