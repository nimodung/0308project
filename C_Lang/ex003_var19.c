#include <stdio.h>

int main() {
    int a, b;
    char ch;
    printf ("������ ���ٷ� ����� �Է��ϼ��� : ");
    scanf("%d %c %d", &a, &ch, &b);
    switch (ch) {
    case '+' :
        printf("%d +%d = %d �Դϴ�. \n", a, b, a+b);
        break;
    case '-' :
        printf("%d -%d = %d �Դϴ�. \n", a, b, a-b);
        break;
    case '*' :
        printf("%d *%d = %d �Դϴ�. \n", a, b, a*b);
        break;
    case '/' :
        printf("%d /%d = %d �Դϴ�. \n", a, b, a/b);
        break;
    case '%' :
        printf("%d %% %d = %d �Դϴ�. \n", a, b, a%b);
        break;
    
            
}

}