#include <stdio.h>
#include <string.h>

int main()
{
    char input_str[100];
    char find_ch, replace_ch;
    
    printf("���� ���ڸ� �Է� : \n");
    gets (input_str);
    printf("ã�� ���ڿ� ��ü�� ���ڸ� �Է� : \n");
    scanf("  %c %c", & find_ch, &replace_ch);
    for(int i = 0; i < strlen(input_str); i++)
    {
        if(input_str[i] == find_ch ){
            input_str[i] = replace_ch;

        }
    }
    
    

    printf("%s \n",input_str);
    
    return 0;

}
