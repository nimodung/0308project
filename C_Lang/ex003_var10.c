#include <stdio.h>

int main() {
    
    unsigned int a = 0xFF;
    printf("%d�� ������ 1ȸ ����Ʈ�ϸ� %x �̴�. \n", a, a>>1);
    printf("%d�� ������ 2ȸ ����Ʈ�ϸ� %x �̴�. \n", a, a>>2);
    printf("%d�� ������ 3ȸ ����Ʈ�ϸ� %x �̴�. \n", a, a>>3);
    printf("%d�� ������ 4ȸ ����Ʈ�ϸ� %x �̴�. \n", a, a>>4);
} //float�� shift ���꿡 ���x  �������� ���
