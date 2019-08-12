#include <stdio.h>

void gugu(int dan){
    int i;
    for (i=1; i<=9; i++){
        printf("%2d X %2d = %2d \n", dan , i, dan*i);
    }  
}
int main(){
    int input;
    printf("출력하고 싶은 단을 입력 : ");
    scanf("%d", &input);
    gugu(input);

}