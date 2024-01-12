#include <stdio.h>
#include <stdlib.h>
int main()
{
	char o;
	int n,m,c=0,a=1,p,p1=0,t=0;
	printf(" Lopez Perez Alberto Andrei \t '202160404' \n Campos Duran Fabrizio \t '2021630223' \n  Yael Almanza Rios \t '2021630171' \n");
	printf("\n \n \n");
	printf("Bienvenido al Programa 'Cadenas y Lenguajes'\n");
	printf("\nIngresa el tamano de tu primera cadena: ");
	scanf("%i",&n); fflush(stdin);
	printf("\nIngresa tu primera cadena: ");
	char x[n];
	while(c<n){
		while(a==1){
		printf("\nEspacio '%i': ",c+1);
		scanf("%c",&x[c]); fflush(stdin);
			if(x[c]=='1' or x[c]=='0'){
				a=0;	
			}		
			else{
			printf("\nIngresaste un valor invalido, solo pudes ingresar '1' o '0'");
			a=1;
			}
		}
		a=1;
		c++;
	}
	c=0;
	printf("\nIngresa el tamano de tu segunda cadena: ");
	scanf("%i",&m); fflush(stdin);
	printf("\nIngresa tu segunda cadena: \n");
	char y[m];
	while(c<m){
		while(a==1){
			printf("\nEspacio '%i': ",c+1);
			scanf("%c",&y[c]); fflush(stdin);
				if(y[c]=='1' or y[c]=='0'){
				a=0;	
			}		
				else{
				printf("\nIngresaste un valor invalido, solo pudes ingresar '1' o '0'");
				a=1;
			}
		}
		a=1;
		c++;
	}
	c=0;
	printf("\n\n\n");
	printf("\nTu primera cadena es: ");
	while(c<n){
		printf("%c",x[c]);
		c++;
	}
	c=0;
	printf("\nTu segunda cadena es: ");	
	while(c<m){
		printf("%c",y[c]);
		c++;
	}
	c=0;
	while(o!='#'){
	printf("\n__________________________________________________________________________________________\n");
	printf("\nRecuerda que solo contamos con el alfabeto '1' y '0'");	
	printf("\nIngrese la opcion de la operacion quq quieres utilizar\n");
	printf("\tIngrese '1' para la operacion de 'Concatenacion'\n");
	printf("\tIngrese '2' para la operacion de 'Potencia'\n");
	printf("\tIngrese '3' para la operacion de 'Sufijos y Prefijos'\n");
	printf("\tIngrese '4' para la operacion de 'Subcadenas'\n");
	printf("\tIngrese '5' para la operacion de 'volver a imprimir'\n");
	printf("-------------------------------------------------------------------------------------------\n");
	printf("Su opcion es: ");
	scanf("%c",&o); fflush(stdin);
	


	switch(o){
		case '0':
			return 0;
			break;
			
		case '1':
			printf("\nLa operacion de 'Concatenacion' con tus cadenas es: ");
				while(c<n){
					printf("%c",x[c]);
					c++;
					}
				c=0;
				while(c<m){
					printf("%c",y[c]);
					c++;
				}
				c=0;
			break;
			
		case '2':
				printf("\n Ingresa la potencia por cual quieres elevar estas cadenas: ");
				scanf("%p",&p); fflush(stdin);
				printf("\nTu primera cadena elevada  a la potencia %i es: ",p);
					while(p1<p){
						while(c<n){
							printf("%c",x[c]);
							c++;
						}
					c=0;
					p1++;
					}
				p1=0;
				printf("\nTu segunda cadena elevada  a la potencia %i es: ",p);
					while(p1<p){
						while(c<n){
							printf("%c",y[c]);
							c++;
						}
					c=0;
					p1++;
					}
				p1=0;
			break;
			
		case '3':
			printf("El prefijo de la primera cadena es: ");
			printf("E, ");
				while(c<n){
					while(t<c+1){
			printf("%c",x[t]);
			t++;
			}
			printf(", ");
			c++;
			t=0;
			}
			c=0;			
			printf("\nEl prefijo de la segunda cadena es: ");
			printf("E, ");
				while(c<m){
					while(t<c+1){
			printf("%c",y[t]);
			t++;
			}
			printf(", ");
			c++;
			t=0;
			}
			c=0;
			printf("\nEl sufijo de la primera cadena es: ");
			printf("E, ");
				while(c<n){
					while(t<c+1){
			printf("%c",x[(n-t)-1]);
			t++;
			}
			printf(", ");
			c++;
			t=0;
			}
			c=0;
			printf("\nEl sufijo de la segunda cadena es: ");
			printf("E, ");
				while(c<m){
					while(t<c+1){
			printf("%c",y[(m-t)-1]);
			t++;
			}
			printf(", ");
			c++;
			t=0;
			}
			c=0;		
			break;
		case '4':
			printf("\nLa subcadena de la primera cadena es: ");
			printf("E, ");
				while(c<n){
					while(t<c+1){
						printf("%c",x[t]);
						t++;
					}
					printf(", ");
					c++;
					t=0;
				}
				c=0;
				printf("0, 1");	
					printf("\nLa subcadena de la segunda cadena es: ");
			printf("E, ");
				while(c<m){
					while(t<c+1){
			printf("%c",y[t]);
			t++;
			}
			printf(", ");
			c++;
			t=0;
			}
			c=0;
			printf("0, 1");			
			break;
		case '5':
	printf("\nTu primera cadena es: ");
	while(c<n){
		printf("%c",x[c]);
		c++;
	}
	c=0;
	printf("\nTu segunda cadena es: ");	
	while(c<m){
		printf("%c",y[c]);
		c++;
	}
	c=0;
			break;
		
		default:
			printf("\nHas ingresado algo erroneo, vuelve a intentarlo\n");
			break;
	}
	}
	return 0;
}
 
