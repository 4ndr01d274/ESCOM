#include <stdio.h>
#include <stdlib.h>
int main()
{
	char o;
	int n,m,l,c=0,c2=0,a=1,p,p1=0,t=0,uno=0,cero=0,b=1;
	char z[10];
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
	printf("\tIngrese '5' para calcular la magnitud de las cadenas\n");
	printf("\tIngrese '6' ver la igualdad de cadenas\n");
	printf("\tIngrese '7' ver si tus cadenas son palindromos\n");
	printf("\tIngrese '8' para remplazar tu cadena\n");
	printf("\tIngrese '9' para complemento a 1'\n");
	printf("\tIngrese 'a' para buscar una cadena dentro de otra cadena\n");
	printf("\tIngrese 'b' para volver a imprimir las cadenas\n");
	printf("\tIngrese '0' para salir del programa\n");
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
						while(c<m){
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
			printf("\nLa magnitud de tus cadenas es: \n");
			printf("Cadena 1: ");
				while(c<n){
			printf("%c",x[c]);
			c++;
			}
			c=0;
			printf("\nSu magnitud es: %i",n);
			printf("\nCadena 2: ");
			while(c<m){
			printf("%c",y[c]);
			c++;
			}
			c=0;
			printf("\nSu magnitud es: %i",m);
			break;
		case '6':
			printf("Igualdad de cadenas\n");
			printf("En la cadena uno: ");
				while(c<n){
			printf("%c",x[c]);
			c++;
			}
			c=0;
				while(c<n){
					if (x[c]=='1'){
						uno++;
					}
					else if (x[c]=='0'){
						cero++;
					}
				c++;
			}
			c=0;
			printf("\nTenemos %i ceros y %i unos",cero,uno);
			if(uno==cero){
				printf("\nLa cadena 1 tiene igualdad");
			}
			else{
				printf("\nLa cadena 1 no tiene igualdad");
			}
			uno=0,cero=0;
			printf("\nEn la cadena dos: ");
			while(c<m){
				printf("%c",y[c]);
				c++;
			}
			c=0;
			while(c<m){
					if (y[c]=='1'){
						uno++;
					}
					else if (y[c]=='0'){
						cero++;
					}
			c++;
			}
			c=0;
			printf("\nTenemos %i ceros y %i unos",cero,uno);
			if(uno==cero){
				printf("\nLa cadena 2 tiene igualdad");
			}
			else{
				printf("\nLa cadena 2 no tiene igualdad");
			}
			uno=0,cero=0;
			break;
		case'7':
			printf("\nLa primera cadena: ");
			while(c<n){
				printf("%c",x[c]);
				c++;
				}
			c=0;
			while(c<n){
				if(x[c]==x[n-c-1]){
					uno=1;
				}
				else{
					uno=0;
				}
			c++;
			}
			c=0;
			if(uno==1){
				printf("\nLa cadena uno es un palindromo");
			}
			else{
				printf("\nLa cadena uno no es un palindromo");
			}
			uno=0;
			printf("\nLa segunda cadena: ");
				while(c<m){
				printf("%c",y[c]);
				c++;
			}
			c=0;
				while(c<m){
					if(y[c]==y[m-c-1]){
						uno=1;
					}
					else{
						uno=0;
					}
				c++;
			}
			c=0;
			if(uno==1){
				printf("\nLa cadena dos es un palindromo");
			}
			else{
				printf("\nLa cadena dos no es un palindromo");
			}
			uno=0;
			break;
		case '8':
			printf("\nIngresa '1' para remplazar la primera cadena, y '2' para remplazar la segunda cadena: ");
			while(b==1){
				scanf("%i",&uno); fflush(stdin);
				if (uno==1){
					printf("\nIngresa el tamano de tu cadena: ");
					scanf("%i",&n); fflush(stdin);
					printf("\nIngresa tu cadena: ");
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
						b=0;
				}
				else if(uno=2){
					printf("\nIngresa el tamano de tu cadena: ");
					scanf("%i",&m); fflush(stdin);
					printf("\nIngresa tu cadena: ");
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
						b=0;
				}
				else{
						printf("\nIngresaste un valor invalido, solo pudes ingresar '1' o '2'");
									b=1;
				}
			}
			b=1;
			uno=0;
			break;
		case '9':
			printf("\nTu primera cadena es: ");
				while(c<n){
					printf("%c",x[c]);
					c++;
				}
			c=0;
			printf("\nEl complemento a 1 de la cadena 1 es: ");
				while(c<n){
					if(x[c]=='1'){
						printf("0");
					}
					else if (x[c]=='0'){
					
						printf("1");
					}
					c++;
				}
			c=0;
			printf("\nTu segunda cadena es: ");	
			while(c<m){
				printf("%c",y[c]);
				c++;
			}
			c=0;
			printf("\nEl complemento a 1 de la cadena 2 es: ");
				while(c<m){
					if(y[c]=='1'){
						printf("0");
					}
					else if (y[c]=='0'){
						printf("1");
					}
					c++;
				}
			c=0;
			break;
		case 'a':
			printf("\nBusqueda de una cadena dentro de otra cadena");
			printf("\nIngresa el tamano de tu cadena que va a ser buscada: ");
					scanf("%i",&l); fflush(stdin);
					printf("\nIngresa tu cadena a buscar: ");
					while(c<l){
						while(a==1){
							printf("\nEspacio '%i': ",c+1);
							scanf("%c",&z[c]); fflush(stdin);
								if(z[c]=='1' or z[c]=='0'){
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
			printf("\nTu cadena a buscar es: ");
			while(c<l){
				printf("%c",z[c]);
				c++;
				}
			c=0;
			while(a==1){
				printf("\nIngresa '1' para buscar en la cadena 1 y '2' para buscar en la cadena 2: ");
				scanf("%i",&b); fflush(stdin);
				if(b==1){
					while(c<n){
						while(c2<l){
							if(x[c+c2]==z[c2]){
								uno=1;
							}
							else{
								uno=0;
							}
							c2++;
						}
						if(uno==1){
							printf("\nSe encontro la cadena dentro de la cadena 1");
							c=n;
						}
						c++;
						c2=0;
					}
					if (uno==0){
						printf("\nNo se encontro la cadena dentro de la cadena 1");
					}
				c=0;
				a=0;
				uno=0;
				}
			else if(b==2){
					while(c<m){
						while(c2<l){
							if(y[c+c2]==z[c2]){
								uno=1;
							}
							else{
								uno=0;
							}
							c2++;
						}
						if(uno==1){
							printf("\nSe encontro la cadena dentro de la cadena 2");
							c=m;
						}
						c++;
						c2=0;
					}
					if (uno==0){
						printf("\nNo se encontro la cadena dentro de la cadena 1");
					}
				c=0;
				a=0;
				uno=0;
				}
				else{
					printf("\nElegiste una opcion invalida");
					a=1;
				}
			}
			a=1;
			
			break;
		case 'b':
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
