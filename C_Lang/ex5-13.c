#include <stdio.h>
char upper (char ch)
{ return ch - ('a' - 'A'); }
char lower (char ch)
{return ch + ('a' -'A'); }
int main() {
    char in [100], out [100];
    char ch;
    int i = 0;
    printf ("���ڿ��� �Է� (100�� �̳�) :");
    scanf("%s", in);
    do{
        ch = in[i];
        if(ch >= 'A' && ch <= 'Z') out [i] = lower(ch);
        else if (ch >= 'a' && ch <= 'z') out[i] = upper(ch);
        else out[i] = ch;
        i++;
    }while (ch != '\0');

    out[i] = '\0';
    printf("��ȯ�� ��� ==> %s\n", out);
}