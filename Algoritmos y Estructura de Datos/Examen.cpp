#include <stdio.h>
#include <stdlib.h>
int main(){
	char c;
	int i,h,x=0,j,n;
	float y=1;
	int *equi;
	int A[3][3];
	int B[3][3];
	int C[3][3];
	printf("__________________________________________________________________________________________\n");
	printf("Bienvenidos al codigo del Examen\n");
	printf("Ingrese la opcion correspondiente al metodo que desea utilizar\n");
	printf("\tIngrese '0' para salir del programa\n");	
	printf("\tIngrese '1' para calcular las resistencias en serie \n");
	printf("\tIngrese '2' para calcular las resistencias en paralelo\n");
	printf("\tIngrese '3' para el programa de la matriz\n");
	printf("-------------------------------------------------------------------------------------------\n");
	printf("Su opcion es: ");
	scanf("%c",&c); fflush(stdin);
	i=0,h=0,x=0;
	
	switch(c){
		case '0':
			return 0;
			break;
		case '1':
			printf("\n Ingrese la cantidad de resistencias en serie que desea sumar: ");
			scanf("%i",&i); fflush(stdin);
			equi=(int*)malloc(i*sizeof(int));
			for(h=0;h<i;h++){
				printf("\nIngrese el valor de la resistencia %i: ",h+1);
				scanf("%i",&equi[h]); fflush(stdin);
				x=x+equi[h];
			}
			printf("La suma equivalente de estas resistencias es: %i",x);
			break;
		case '2':
			printf("\n Ingrese la cantidad de resistencias en paralelo que desea sumar: ");
			scanf("%i",&i); fflush(stdin);
			equi=(int*)malloc(i*sizeof(int));
			for(h=0;h<i;h++){
				printf("\nIngrese el valor de la resistencia %i: ",h+1);
				scanf("%i",&equi[h]); fflush(stdin);
				
			y=(y*equi[h])/(y+equi[h]);	
			}
			printf("La suma equivalente de estas resistencias es: %f",y);
			break;			
			
		case '3':
		//codigo para leer la primera matriz
			printf("\nIngrese la matriz con la que se desea trabajar.\n");
				for(i=0;i<3;i++){
			    	for(j=0;j<3;j++){
			    		printf("Ingresa los valores de la matriz [%d][%d]: ",i+1,j+1);
			    		scanf("%d", &A[i][j]);
			    	}
			    }
			//codigo para imprimir la matriz ingresada
			printf("\n\nLa matriz ingresada es la siguiente: ");    
			    for(int i=0;i<3;++i){
    			printf("\n");
        			for(int j=0;j<3;++j){
          			  printf("[%d] \t",A[i][j]);
       				 }
  				}
  			//Codigo para la matriz traspuesta.
  				for(i=0;i<3;i++){
			    	for(j=0;j<3;j++){
			    		B[j][i]=A[i][j];
			    	}
			    }
		//Codigo para imprimir la matriz traspuesta
			printf("\n\nLa matriz traspuesta es la siguiente: ");    
			    for(int i=0;i<3;++i){
    			printf("\n");
        			for(int j=0;j<3;++j){
          			  printf("[%d] \t",B[i][j]);
       				 }
  				} 
			
		//Codigo para sumar estas 2 matrices
			 	for(i=0;i<3;i++){
			    	for(j=0;j<3;j++){
			    	C[i][j]=B[i][j]+A[i][j];
			    	}
			    }
		//Codigo para imprimir la matriz traspuesta
			printf("\n\nLa suma de la matriz ingresada con la matriz trasnpuesta es la siguiente: ");    
			    for(int i=0;i<3;++i){
    			printf("\n");
        			for(int j=0;j<3;++j){
          			  printf("[%d] \t",C[i][j]);
       				 }
  				} 
			break;
			
	}
	
	
	
	
	return 0;}
	
