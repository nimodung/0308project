#include <stdio.h>

int main() {
   int a, b, tmp;
   int *p1, *p2;
   printf("a �� �Է� : ");
   scanf("%d", &a);
   printf("b �� �Է� : ");
   scanf("%d", &b);
   p1 = &a;
   p2 = &b;
   tmp = *p1;
   *p1 = *p2;
   *p2 = tmp;
   printf("�ٲ� �� a�� %d, b�� %d \n",a, b);

}
