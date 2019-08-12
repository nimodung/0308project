#include <stdio.h>

int main() {
   
   int arr[10];
   int tmp;
   int *p;
   p = arr;

   for (int i = 0; i <10; i++){
       printf("%d번째 정수 입력 ", i+1);
       scanf("%d", &arr[i]);

   }
   for(int i = 0; i<10; i++){
       printf("%d  ", arr[i]);

   }
   printf("\n");    

   for(int j=0; j<9; j++){
       for(int i=j+1; i<10; i++){
           if(*(p+j) < *(p+i)){
               tmp = *(p+j);
               *(p+j) = *(p+j);
               *(p+i) = tmp;
           }
       }
   }
   for(int i = 0; i<10; i++){
       printf("%d ", arr[i]);

   }
   printf("\n");
   return 0;
}
