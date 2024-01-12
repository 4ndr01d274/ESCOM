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
		float a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,i=0,j=0,k=0,l=0,m=0,n=0,o=0,p=0,q=0,r=0,s=0,t=0,u=0,v=0,w=0,y=0,x=0,z=0,contador=0;
		float caracter;
		char po='%';
		while((caracter = fgetc(archivo)) != EOF){
			contador++;
				if(caracter=='a'){
					a++;
				}
				else if(caracter=='b'){
					b++;
				}
				else if(caracter=='c'){
					c++;
				}
				else if(caracter=='d'){
					d++;
				}
				else if(caracter=='e'){
					e++;
				}
				else if(caracter=='f'){
					f++;
				}
				else if(caracter=='g'){
					g++;
				}
				else if(caracter=='h'){
					h++;
				}
				else if(caracter=='i'){
					i++;
				}
				else if(caracter=='j'){
					j++;
				}
				else if(caracter=='k'){
					k++;
				}
				else if(caracter=='l'){
					l++;
				}
				else if(caracter=='m'){
					m++;
				}
				else if(caracter=='n'){
					n++;
				}
				else if(caracter=='o'){
					o++;
				}
				else if(caracter=='p'){
					p++;
				}
				else if(caracter=='q'){
					q++;
				}
				else if(caracter=='r'){
					r++;
				}
				else if(caracter=='s'){
					s++;
				}
				else if(caracter=='t'){
					t++;
				}
				else if(caracter=='u'){
					u++;
				}
				else if(caracter=='v'){
					v++;
				}
				else if(caracter=='x'){
					x++;
				}
				else if(caracter=='w'){
					w++;
				}
				else if(caracter=='y'){
					y++;
				}
				else if(caracter=='z'){
					z++;
				}
			
				}
				
				fflush(archivo);	
				fclose(archivo);	
		printf("El numero de palabras son: %.f y representa el 100%\n",contador);
		printf("El porcentaje en el que aparecen las palabras son las siguientes:\n");
		printf("a-%.2f%c\n",((a*100)/contador),po);
		printf("b-%.2f%c\n",((b*100)/contador),po);
		printf("c-%.2f%c\n",((c*100)/contador),po);
		printf("d-%.2f%c\n",((d*100)/contador),po);
		printf("e-%.2f%c\n",((e*100)/contador),po);
		printf("f-%.2f%c\n",((f*100)/contador),po);
		printf("g-%.2f%c\n",((g*100)/contador),po);
		printf("h-%.2f%c\n",((h*100)/contador),po);
		printf("i-%.2f%c\n",((i*100)/contador),po);
		printf("j-%.2f%c\n",((j*100)/contador),po);
		printf("k-%.2f%c\n",((k*100)/contador),po);
		printf("l-%.2f%c\n",((l*100)/contador),po);
		printf("m-%.2f%c\n",((m*100)/contador),po);
		printf("n-%.2f%c\n",((n*100)/contador),po);
		printf("o-%.2f%c\n",((o*100)/contador),po);
		printf("p-%.2f%c\n",((p*100)/contador),po);
		printf("q-%.2f%c\n",((q*100)/contador),po);
		printf("r-%.2f%c\n",((r*100)/contador),po);
		printf("s-%.2f%c\n",((s*100)/contador),po);
		printf("t-%.2f%c\n",((t*100)/contador),po);
		printf("u-%.2f%c\n",((u*100)/contador),po);
		printf("v-%.2f%c\n",((v*100)/contador),po);
		printf("w-%.2f%c\n",((w*100)/contador),po);
		printf("x-%.2f%c\n",((x*100)/contador),po);
		printf("y-%.2f%c\n",((y*100)/contador),po);
		printf("z-%.2f%c\n",((z*100)/contador),po);
	
		}
	
	
	
	
	return 0;
}
