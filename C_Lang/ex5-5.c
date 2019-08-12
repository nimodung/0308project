#include <stdio.h>
#include <string.h>

int main()
{
    char input_str[100];
    char find_ch, replace_ch;
    
    printf("여러 글자를 입력 : \n");
    gets (input_str);
    printf("찾을 문자와 대체할 문자를 입력 : \n");
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
