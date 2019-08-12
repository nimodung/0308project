#include <stdio.h>

int main() {
   int a[10];
   int *p;
   int k,tmp,i;

    p= a;
    printf("%d 숫자를 입력하시오 : ",a+1);
    for(i=0; i<10; i++)
        scanf("%d", (p+i));
    
    for(i=0; i<9; i++)
    {
        for(k=i+1; k<10; k++)
        {
            if(*(p+i) < *(p+k))
        {
            tmp = *(p+i);
            *(p+i) = *(p+k);
            *(p+k) = tmp;
        }
        

    }
}
for(i=0; i<10; i++)
        printf("값 : %d\n", *(p+i));
}
