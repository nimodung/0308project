#include <stdio.h>

int plus(int v1, int v2) {
    int result;
    result = v1 + v2;
    return result;
}
int main() {
    int hap;
    hap = plus(200,200);

    printf("100�� 200�� plus () �Լ� ����� : %d \n", hap);

}