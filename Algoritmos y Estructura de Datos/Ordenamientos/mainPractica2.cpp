#include <stdio.h>
#include <stdlib.h>
int main (int argc, char *argv[]){
	float temporal;	
	int cambio=0;
	int aux,rr;
	int dd;
//	int a[1];                                  
	int y=0,b=0,r=0,d=0;
	FILE * flujo = fopen("numeros10millones.txt","rb");
	if (flujo == NULL){
		printf("Error en la apertura del archivo");
		return 1;
	}
	fseek(flujo, 0, SEEK_END);
	int num_elementos = ftell(flujo);
	rewind(flujo);
	
	int * a = (int *) calloc(sizeof (int), num_elementos);
	if (a == NULL){
		perror("Error en reserva de memoria");
		return 2;
	}
	int num_elementos_leidos = fread(a, sizeof(int), num_elementos, flujo);
	if (num_elementos_leidos != num_elementos) {
		perror ("Error leyendo el archivo");
		return 3;
	}
	printf("%i",a);
	printf("Ingrese la cantidad de elementos que desea ordenar, maximo 99999: ");
	scanf("%i",&b); fflush(stdin);
	
	fclose(flujo);
	
	char opcion=' ';								
	printf("¡Bienvenido al programa de Algoritomos de Ordenamiento Directo!");
	printf("\nPrograma echo por:\nAlberto Andrei Lopez Porez\t'2021630404'\nCampos Duran Fabrizio\t\t'2021630223'\n");
	while(opcion!='#'){
/*	while(y<=b){									
		a[y] = (rand()%10000);
		printf("[%d]  ",a[y]);						 
		y++;									 
	}*/
	y=0;
	printf("\n_______________________________________________________________________________________________________________________");
	printf("\nIngrese '1' para ordenar los elementos por Ordenamiento de Burbuja");
	printf("\nIngrese '2' para ordenar los elementos por Inserccion");
	printf("\nIngrese '3' para ordenar los elementos por Seleccion");
	printf("\nIngrese '4' para ordenar los elementos por Shell");
	printf("\nIngrese '5' para ordenar los elementos por Ordenamiento de Burbuja bidireccional");
	printf("\nIngrese '#' para cerrar el programa: ");
	printf("\n-----------------------------------------------------------------------------------------------------------------------\n");
	scanf("%c",&opcion); fflush(stdin);
	switch(opcion){
	case '1': 						//Ordenamiento de Burbuja
		printf("\n'Bienvendido al Ordenamiento de burbuja'");
		printf("\nIngrese '1' para ordenar de menor a mayor y '2' para mayor a menor: ");
		scanf("%i",&r); fflush(stdin);
		if (r==1){
			for (int i = 0;i < b; i++){
				for (int j = 0; j< b-1; j++){
						if (a[j] > a[j+1]){ 
						temporal = a[j]; 
						a[j] = a[j+1]; 
						a[j+1] = temporal;
					}
				}
			}
		}
		else if(r==2){
			for (int i = 0;i < b; i++){
				for (int j = 0; j< b-1; j++){
						if (a[j] < a[j+1]){ 
						temporal = a[j]; 
						a[j] = a[j+1]; 
						a[j+1] = temporal;
					}
				}
			}
		}
		else {
			printf("Ingresaste mal el dato.");
		}
			for (int i = 0; i < b; i++) {
				 printf("[%i]-",a[i]);
				}
		break;
	case '2':
		printf("\n'Bienvendido al Ordenamiento de Insercion\n'");
	for (int i = 1; i < b; i++){	   
		d = a[i];
		r = i-1;
		while (r >= 0 && a[r] > d){
			a[r+1] = a[r];
			r = r-1;
		}
		a[r+1] = d;
	}    
	for (int i = 0; i < b; i++) {
	 printf("[%i]-",a[i]);
	}
		break;
	case '3':
		printf("\n'Bienvendido al Ordenamiento de Seleccion\n'");
		for (int i = 0; i < b - 1; i++) {
    		for (int j = (i + 1); j < b; j++) {
    			if (a[i] > a[j]) {			// ...intercambiarlos, es decir, mover el actual a la derecha y el de la derecha al actual
        			temporal = a[i];
        			a[i] = a[j];
        			a[j] = temporal;
        		}
    		}
    	}
        for (int i = 0; i < b; i++) {
			printf("[%i]-",a[i]);
		}
		break;
	case '4':
		printf("\n'Bienvendido al Ordenamiento de Shell\n'");
		for(int gap = b/2; gap > 0; gap /= 2){
        	for(int i = gap; i < b; i += 1){
           		temporal = a[i]; 
            	int j;
            	for(j = i; j >= gap && a[j - gap] > temporal; j -= gap) 
                	a[j] = a[j - gap]; 
            	a[j] = temporal;
        	}
   		}
   		 for (int i = 0; i < b; i++){
			printf("[%i]-",a[i]);
		}
		break;
	case '5':
		printf("\n'Bienvendido al Ordenamiento de burbuja bidireccional'\n");
		r=0;
		d=0;
		d=b-1;
		int i,ayuda;
		
		while(r<=d){
			aux=d;
			dd=r;	
			for(i=r; i<d; i++){
				if(a[i]>a[i+1]){
					ayuda=a[i];
					a[i]=a[i+1];
					a[i+1]=ayuda;
					cambio=1;
					dd=i;
				}
			}
			if(!cambio){
			break;
		}
			d=dd;
			cambio=0;
			for(i=d; i>=r;i--){
				if(a[i]>a[i+1]){
					ayuda=a[i];
					a[i]=a[i+1];
					a[i+1]=ayuda;
					cambio++;
					rr=i;
				}
			}
			if(!cambio){
			break;
			r=rr+1;
		}
	}
		for (i = 0; i < b; i++) {
			printf("[%i]-",a[i]);
		}
		break;
	case '#':
			break;
	default:
		printf("Has ingresado algo erroneo, vuelve a intentarlo");
		break;
	}
	system("PAUSE");
	}
	return 0;
}

