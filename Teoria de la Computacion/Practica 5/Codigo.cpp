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
		int x=0,a=0,b=0,c=0,d=0,e=0,g=0,h=0,j=0,k=0,l=0,m=0,n=0,o=0,p=0,q=0,r=0;
		int contador = 0, contador1=0,contador2=0, contador3=0, contador19=0,contador20=0,contador21=0,contador22=0,contador23=0,contador24=0,contador25=0;
		int contador4=0,contador5=0,contador6=0,contador7,contador8=0,contador9=0,contador10=0,contador11=0,contador12=0,contador13=0,contador14=0,contador15=0,contador16=0,contador17=0,contador18=0;
		int caracter, cara;
		char po='%';
		float conta=0;
		while((caracter = fgetc(archivo)) != EOF){
				conta++;
				if(caracter == '<'){
					x=0;
					a=0;
					h=0;
					g=0;
					e=0;
					p=0;
					b=0;
					c=1;							
					m=0;
					n=0;
					o=0;
					d=0;
					j=0;
					k=0;
					l=0;
				contador++;
				}
				else if(caracter == '>'){
					x=0;
					g=0;
					e=0;
					p=0;
					h=0;
					a=0;
					b=0;
					c=0;
					m=0;
					n=0;
					o=0;
					d=1;
					j=0;
					k=0;
					l=0;
					contador1++;
				}
				else if(caracter == '('){
					x=0;
					h=0;
					m=0;
					n=0;
					o=0;
					a=0;
					b=0;
					p=0;
					e=0;
					g=0;
					c=0;
					j=0;
					k=0;
					l=0;
					d=0;
					contador2++;
				}
				else if(caracter == ')'){
					x=0;
					a=0;
					h=0;
					m=0;
					n=0;
					o=0;
					b=0;
					p=0;
					c=0;
					d=0;
					j=0;
					k=0;
					l=0;
					g=0;
					e=0;
					contador3++;
				}
				else if(caracter == '['){
					x=0;
					a=0;
					b=0;
					e=0;
					h=0;
					c=0;
					p=0;
					j=0;
					m=0;
					n=0;
					o=0;
					k=0;
					l=0;
					g=0;
					d=0;
					contador4++;
				}
				else if(caracter == ']'){
					x=0;
					a=0;
					b=0;
					e=0;
					j=0;
					p=0;
					k=0;
					l=0;
					c=0;
					m=0;
					n=0;
					o=0;
					g=0;
					h=0;
					d=0;
					contador5++;
				}
				else if(caracter == '{'){
					x=0;
					a=0;
					b=0;
					c=0;
					j=0;
					k=0;
					l=0;
					p=0;
					m=0;
					n=0;
					o=0;
					g=0;
					h=0;
					d=0;
					e=0;
					contador6++;
				}
				else if(caracter== '}'){
					x=0;
					a=0;
					j=0;
					p=0;
					k=0;
					l=0;
					b=0;
					g=0;
					m=0;
					n=0;
					o=0;
					c=0;
					h=0;
					d=0;
					e=0;
					contador7++;
				}
				else if(caracter=='+'){
					a=0;
					b=0;
					h=0;
					p=0;
					g=0;
					j=0;
					k=0;
					l=0;
					c=0;
					d=0;
					m=0;
					n=0;
					o=0;
					e=0;
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
					h=0;
					p=0;
					m=0;
					n=0;
					o=0;
					g=0;
					c=0;
					e=0;
					j=0;
					k=0;
					l=0;
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
					m=0;
					n=0;
					p=0;
					o=0;
					g=0;
					j=0;
					k=0;
					l=0;
					b=0;
					e=0;
					c=0;
					d=0;
					if(h==1){
					contador21++;
					}
					else{
					contador10++;
					}
				}
						
				else if(caracter=='/'){
					x=0;
					a=0;
					j=0;
					p=0;
					m=0;
					n=0;
					o=0;
					k=0;
					l=0;
					b=0;
					c=0;
					e=0;
					d=0;
					h=1;
					if(g==1){
						contador20++;
						contador11--;
					}
					else{
					g=1;	
					contador11++;
					}
				}
				else if (caracter=='='){
					x=0;
					a=0;
					m=0;
					p=0;
					n=0;
					o=0;
					j=0;
					k=0;
					l=0;
					e=0;
					h=0;
					g=0;
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
				else if(caracter=='i'){
					j=1;
					m=0;
					n=0;
					o=0;
					p=1;
				}
				else if(caracter=='n'){
					if(j==1){
					k=1;
					m=0;
					n=0;
					o=0;
					p=0;
					}
					else{
						j=0;
						k=0;
						l=0;
						m=0;
					n=0;
					o=0;
					p=0;
					}
				}
				else if(caracter=='t'){
					if(k==1){
						contador22++;
					}
					else{
						j=0;
						k=0;
						m=0;
					n=0;
					o=0;
					p=0;
						l=0;
					}
				}
				else if(caracter=='c'){
					m=1;
				}
				else if(caracter=='h'){
					if(m==1){
					n=1;
					}
					else{
						m=0;
						n=0;
						o=0;
						p=0;
					}
				}
				else if(caracter=='a'){
					if(n==1){
						o=1;
					}
					else{
						m=0;
						n=0;
						o=0;
						p=0;
					}
				}
				else if(caracter=='r'){
					if(o==1){
						contador23++;
					}
					else{
						m=0;
						n=0;
						o=0;
						p=0;
					}
				}
				else if(caracter=='f'){
					if(p==1){
						contador24++;
					}
					else{
						p=0;
						x=0;
					a=0;
					h=0;
					g=0;
					e=0;
					p=0;
					b=0;
					m=0;
					n=0;
					o=0;
					d=0;
					j=0;
					k=0;
					l=0;
					}
				}
				else if(caracter=='e'){
					q=1;
					if(r==1){
						contador25++;
					}
					else{
					x=0;
					a=0;
					h=0;
					g=0;
					e=0;
					p=0;
					b=0;
					m=0;
					n=0;
					o=0;
					d=0;
					j=0;
					k=0;
					l=0;
					r=0;
					}
				}
				else if(caracter=='l'){
					if(q==1){
						l=1;
					}
					else{
					x=0;
					a=0;
					h=0;
					g=0;
					e=0;
					p=0;
					b=0;
					m=0;
					n=0;
					o=0;
					d=0;
					j=0;
					k=0;
					l=0;
					r=0;
					q=0;
					}
				}
				else if(caracter=='s'){
					if(l==1){
						r=1;
					}
					else{
					x=0;
					a=0;
					h=0;
					g=0;
					e=0;
					p=0;
					b=0;
					m=0;
					n=0;
					o=0;
					d=0;
					j=0;
					k=0;
					l=0;
					r=0;
					q=0;
					}
				}
				else if (caracter=='0'||'1'||'2'||'3'||'4'||'5'||'6'||'7'||'8'||'9'||'.'){
					x=0;
					p=0;
					a=0;
					h=0;
					b=0;
					g=0;
					c=0;
					d=0;
					j=0;
					k=0;
					l=0;
					contador14++;
					if(e==1){
						contador14--;
					}
					else{
						contador14++;
						e=0;
					}
					if(caracter=='.'&&e==1){
						contador14--;
						contador15++;
					}
					e=1;
					
				}
				}
				
				fflush(archivo);	
				fclose(archivo);	
		printf("El numero de < es de %d y su porcentaje de aparicion es %.2f%c\n",contador,((contador*100)/conta),po);
		printf("El numero de > es de %d y su porcentaje de aparicion es %.2f%c\n",contador1,((contador1*100)/conta),po);
		printf("El numero de ( es de %d y su porcentaje de aparicion es %.2f%c\n",contador2,((contador2*100)/conta),po);
		printf("El numero de ) es de %d y su porcentaje de aparicion es %.2f%c\n",contador3,((contador3*100)/conta),po);
		printf("El numero de [ es de %d y su porcentaje de aparicion es %.2f%c\n",contador4,((contador4*100)/conta),po);
		printf("El numero de ] es de %d y su porcentaje de aparicion es %.2f%c\n",contador5,((contador5*100)/conta),po);
		printf("El numero de { es de %d y su porcentaje de aparicion es %.2f%c\n",contador6,((contador6*100)/conta),po);
		printf("El numero de } es de %d y su porcentaje de aparicion es %.2f%c\n",contador7,((contador7*100)/conta),po);
		printf("El numero de + es de %d y su porcentaje de aparicion es %.2f%c\n",contador8,((contador8*100)/conta),po);
		printf("El numero de - es de %d y su porcentaje de aparicion es %.2f%c\n",contador9,((contador9*100)/conta),po);
		printf("El numero de * es de %d y su porcentaje de aparicion es %.2f%c\n",contador10,((contador10*100)/conta),po);
		printf("El numero de / es de %d y su porcentaje de aparicion es %.2f%c\n",contador11,((contador11*100)/conta),po);
		printf("El numero de = es de %d y su porcentaje de aparicion es %.2f%c\n",contador19,((contador19*100)/conta),po);
		printf("El numero de ++ es de %d y su porcentaje de aparicion es %.2f%c\n",contador12,((contador12*100)/conta),po);
		printf("El numero de -- es de %d y su porcentaje de aparicion es %.2f%c\n",contador13,((contador13*100)/conta),po);
		printf("El numero de == es de %d y su porcentaje de aparicion es %.2f%c\n",contador16,((contador16*100)/conta),po);
		printf("El numero de <= es de %d y su porcentaje de aparicion es %.2f%c\n",contador17,((contador17*100)/conta),po);
		printf("El numero de >= es de %d y su porcentaje de aparicion es %.2f%c\n",contador18,((contador18*100)/conta),po);
		printf("La cantidad de enteros son: %d y su porcentaje de aparicion es %.2f%c\n",contador14,((contador14*100)/conta),po);
		printf("La cantidad de decimales son: %d y su porcentaje de aparicion es %.2f%c\n",contador15,((contador15*100)/conta),po);
		printf("La cantidad de comentarios simples son: %d y su porcentaje de aparicion es %.2f%c\n",contador20,((contador20*100)/conta),po);
		printf("La cantidad de comentarios de varias lineas son: %d y su porcentaje de aparicion es %.2f%c\n",contador21,((contador21*100)/conta),po);
		printf("La cantidad de int's son: %d y su porcentaje de aparicion es %.2f%c\n",contador22,((contador22*100)/conta),po);
		printf("La cantidad de char's son: %d y su porcentaje de aparicion es %.2f%c\n",contador23,((contador23*100)/conta),po);
		printf("La cantidad de if's son: %d y su porcentaje de aparicion es %.2f%c\n",contador24,((contador24*100)/conta),po);
		printf("La cantidad de else's son: %d y su porcentaje de aparicion es %.2f%c\n",contador25,((contador25*100)/conta),po);
		}
	
	
	
	
	return 0;
}
