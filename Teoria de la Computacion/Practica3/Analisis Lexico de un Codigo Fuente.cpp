#include <errno.h>	
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <stdlib.h>
#include <fcntl.h>

int main(){
	printf(" Lopez Perez Alberto Andrei \t '202160404' \n Campos Duran Fabrizio \t '2021630223' \n  Yael Almanza Rios \t '2021630171' \n");
	printf("\n \n \n");
	printf("Bienvenido al Programa 'Analisis Lexico de un Codigo Fuente'\n");
	printf("El archivo que vamos a ocupar se llama Lecutura.txt\n");
	FILE *archivo = fopen("Lectura.txt","r");
	if(archivo == NULL){
	perror("Error al abrir el archivo\n");
	}
	else{
		int contador = 0, contador1=0,contador2=0, contador3=0;
		int contador4=0,contador5=0,contador6=0,contador7=0;	
		int caracter;
		while((caracter = fgetc(archivo)) != EOF){
				
				if(caracter == '<'){
				contador++;
				}
				else if(caracter == '>'){
					contador1++;
				}
				else if(caracter == '('){
					contador2++;
				}
				else if(caracter == ')'){
					contador3++;
				}
				else if(caracter == '['){
					contador4++;
				}
				else if(caracter == ']'){
					contador5++;
				}
				else if(caracter == '{'){
					contador6++;
				}
				else if(caracter== '}'){
					contador7++;
				}
				}
				
				fflush(archivo);	
				fclose(archivo);	
		printf("El numero de < es de %d\n",contador);
		printf("El numero de > es de %d\n",contador1);
		printf("El numero de ( es de %d\n",contador2);
		printf("El numero de ) es de %d\n",contador3);
		printf("El numero de [ es de %d\n",contador4);
		printf("El numero de ] es de %d\n",contador5);
		printf("El numero de { es de %d\n",contador6);
		printf("El numero de } es de %d\n",contador7);
		}
	
	
	
	
	return 0;
}
