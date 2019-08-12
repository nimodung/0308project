#include <stdio.h>
#include <string.h>

int main()
{
    int aa [9][9];
    int i, k;
    int val = 1;
    for ( i = 0; i<9; i++){
        for( k= 0; k <9; k++){
            aa[i][k] = val;
            

        }
        val ++;
    }
        printf("aa[0][0]부터 aa[2][3]까지 출력 \n");
        for( i=1; i<9; i++){
            for(k=0; k<9; k++){
                printf("  %2d X %2d = %2d ", aa[i][k],aa[k][i],aa[i][k]*aa[k][i]);
            }
            printf("\n");
        }

}
    
        
    