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
	printf("El archivo que vamos a ocupar se llama Lectura.txt\n");
	FILE *archivo = fopen("Lectura.txt","r");
	if(archivo == NULL){
	perror("Error al abrir el archivo\n");
	}
	else{
		int x=0,a=0,b=0,c=0,d=0;
		int contador = 0, contador1=0,contador2=0, contador3=0, contador19=0;
		int contador4=0,contador5=0,contador6=0,contador7,contador8=0,contador9=0,contador10=0,contador11=0,contador12=0,contador13=0,contador14=0,contador15=0,contador16=0,contador17=0,contador18=0;
		int caracter, cara;
		while((caracter = fgetc(archivo)) != EOF){
				
				if(caracter == '<'){
					x=0;
					a=0;
					b=0;
					c=1;
					d=0;
				contador++;
				}
				else if(caracter == '>'){
					x=0;
					a=0;
					b=0;
					c=0;
					d=1;
					contador1++;
				}
				else if(caracter == '('){
					x=0;
					a=0;
					b=0;
					c=0;
					d=0;
					contador2++;
				}
				else if(caracter == ')'){
					x=0;
					a=0;
					b=0;
					c=0;
					d=0;
					contador3++;
				}
				else if(caracter == '['){
					x=0;
					a=0;
					b=0;
					c=0;
					d=0;
					contador4++;
				}
				else if(caracter == ']'){
					x=0;
					a=0;
					b=0;
					c=0;
					d=0;
					contador5++;
				}
				else if(caracter == '{'){
					x=0;
					a=0;
					b=0;
					c=0;
					d=0;
					contador6++;
				}
				else if(caracter== '}'){
					x=0;
					a=0;
					b=0;
					c=0;
					d=0;
					contador7++;
				}
				else if(caracter=='+'){
					a=0;
					b=0;
					c=0;
					d=0;
					if(x==1){
						contador12++;
						contador8--;
						x=0;
					}
					else{
					x=1;
					contador8++;
					}
				}
				else if(caracter=='-'){
					x=0;
					b=0;
					c=0;
					d=0;
					if(a==1){
						contador13++;
						contador9--;
						a=0;
					}
					else{
						a=1;
					contador9++;
					}
				}
				else if(caracter=='*'){
					x=0;
					a=0;
					b=0;
					c=0;
					d=0;
					contador10++;
				}
						
				else if(caracter=='/'){
					x=0;
					a=0;
					b=0;
					c=0;
					d=0;
					contador11++;
				}
				else if (caracter=='='){
					x=0;
					a=0;
					if(b==1){
						contador16++;
						contador19--;
						b=0;
					}
					else{
					contador19++;
					b=1;
					}
					if(c==1){
						contador17++;
						contador--;
						c=0;
					}
					if(d==1){
						contador18++;
						contador1--;
						d=0;
					}
					
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
		printf("El numero de + es de %d\n",contador8);
		printf("El numero de - es de %d\n",contador9);
		printf("El numero de * es de %d\n",contador10);
		printf("El numero de / es de %d\n",contador11);
		printf("El numero de = es de %d\n",contador19);
		printf("El numero de ++ es de %d\n",contador12);
		printf("El numero de -- es de %d\n",contador13);
		printf("El numero de == es de %d\n",contador16);
		printf("El numero de <= es de %d\n",contador17);
		printf("El numero de >= es de %d\n",contador18);
		}
	
	
	
	
	return 0;
}