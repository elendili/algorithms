#include <stdio.h>

int freq[101];

int main() {
  int number;
  int r=0i, r1;
  //skip first
  scanf("%d",&number);
  do {
    r = getchar();
    // skip first number in line
    if (r == '\n') {
      scanf("%d",&number);
    } else if (scanf("%d",&number)>0) {
      freq[number]++;
//      printf("- %d\n",number);
    }
  //  printf("> %c <\n",r);
  
  } while ( r!= EOF);
  
  for(int i = 0; i < 101; i++){
    int count = freq[i];
    for(int j=0;j < count; j++){
        printf("%d ", i);
    }
  }
  printf("\n");

  return 0;
}
