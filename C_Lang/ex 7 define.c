#include <string.h>
#define KIM 0
#define LEE 1
#define PARK 2
int main () {
    char name[3][10];
    int kor [3];
    int eng[3];
    float avg[3];
    int i;
    strcpy(name[KIM], "Kim");
    kor[KIM] = 90;
    eng[KIM] = 80;
    avg[KIM] = (kor[KIM] + eng[KIM])/ 2.0f;
    strcpy(name[LEE], "Lee");
    kor[LEE] = 70;
    eng[LEE] = 60;
    avg[LEE] = (kor[LEE] + eng[LEE])/ 2.0f;  

    
}