#include <stdio.h>

int main() {
    int a, b;
    char ch;
    printf ("ù��° ���� �Է��ϼ��� : ");
    scanf("%d", &a);
    printf ("����� �����ڸ� �Է��ϼ��� : ");
    scanf(" %c", &ch);
    printf ("�ι�° ���� �Է��ϼ��� : ");
    scanf("%d", &b);
   

    if (ch == '+')
        printf("%d + %d = %d �Դϴ�. \n", a, b, a+b);
    else if (ch == '-')
        printf("%d - %d = %d �Դϴ�. \n", a, b, a-b);        
    else if (ch == '*')
        printf("%d * %d = %d �Դϴ�. \n", a, b, a*b);
    else if (ch == '/')
        printf("%d / %d = %d �Դϴ�. \n", a, b, a/(float)b);
    else if (ch == '%')
        printf("%d %% %d = %d �Դϴ�. \n", a, b, a%b); 

    return 0;              
}

