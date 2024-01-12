#include<iostream>
#include<stdlib.h>
#include<string.h>
#include<fstream>
using namespace std;

void lectura();
int main(){
	long int a[];
	lectura();
	system("pause");
	return 0;
}
void lectura(){
	long int* numeros,total; /// Puntero para guardar los numeros extraidos del archivo.
    int menor=0,i=0,j=0; /// variables para el ordenamiento.
    string texto; /// variable para almacenar los datos linea a linea del archivo.
    unsigned t0, t1; /// variables para saber el tiempo que se tardó en ejecutar la ordenacion
    numeros = (long int *) malloc(total*sizeof(long int)); /// Crea la memoria requerida para los n numeros señalados por el usuario.
    
    ifstream archivo; /// Se declara para la direccion del archivo.
	archivo.open("numeros10millones.txt",ios::in); /// Se ingresa como archivo de lectura.
	if(archivo.fail()) /// Lectura por si hay error con el archivo.
	{
		cout << "No se pudo abrir" <<endl;
		exit(1);
	}
	for( i = 0 ; i < total ; i++ ) /// Se obtendra dato por dato del archivo hacia el puntero donde se almacenaran.
	{
    	getline(archivo,texto);
		numeros[i] = atoi(texto.c_str());
	}
	//printf("funciono \n%i\n%i\n%i\n",numeros[0],numeros[1],numeros[2];
	/*for( i = 0 ; i < total ; i++ ) /// Se obtendra dato por dato del archivo hacia el puntero donde se almacenaran.
	{
    	printf("%i",numeros[i]);
	}*/
	archivo.close(); /// Cierre del archivo.
	return numeros;
}
