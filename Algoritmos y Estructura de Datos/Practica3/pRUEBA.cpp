#include <stdio.h>
#include <stdlib.h>

main(){
	printf("Este un codigo de C");
	int x;
	printf("\n Ingresa un numero pofavor: ");
	scanf("%i",&x);
	int y,z,multi;
	printf("\n");
	printf("Tu numero es: %i",x);
	y=x*10;
	printf("\nTu numero multiplicado por 10 es: %i",y);
	z=rand()%100;
	multi=x*z;
	printf("\nTu numero multiplicado por un random es: %i",multi);
	printf("\nEl numero random es: %i",z);
	return 0;
}
