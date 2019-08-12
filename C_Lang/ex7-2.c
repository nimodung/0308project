#include <string.h>

int main(){
    struct bibim {
        int a;
        float b;
        char c;
        char d[5];

    };
    struct bibim b1;
    b1.a = 10;
    b1.b = 1.1f;
    b1.c = 'A';
    strcpy(b1.d, "ABCD");
    printf (" b1.a ==> %d \n", b1.a);
    printf (" b1.b ==> %d \n", b1.b);
    printf (" b1.c ==> %d \n", b1.c);
    printf (" b1.d ==> %d \n", b1.d);

}