#include <bits/stdc++.h>
#include <windows.h>


using namespace std;

												//Method 2 (Use Dynamic Programming) 
class GFG{
     
public:
int fib(int n)
{
     
    // Declare an array to store
    // Fibonacci numbers.
    // 1 extra to handle
    // case, n = 0
    int f[n + 2];
    int i;
 
    // 0th and 1st number of the
    // series are 0 and 1
    f[0] = 0;
    f[1] = 1;
 
    for(i = 2; i <= n; i++)
    {
         
       //Add the previous 2 numbers
       // in the series and store it
       f[i] = f[i - 1] + f[i - 2];
    }
    return f[n];
    }
};

																//Method 8   DP using memoization(Top down approach)
int dp[10];
int fib(int n)
{
    if (n <= 1)
        return n;
 
    // temporary variables to store
    //  values of fib(n-1) & fib(n-2)
    int first, second;
 
    if (dp[n - 1] != -1)
        first = dp[n - 1];
    else
        first = fib(n - 1);
 
    if (dp[n - 2] != -1)
        second = dp[n - 2];
    else
        second = fib(n - 2);
 
    // memoization
    return dp[n] = first + second;
}


															//Coeficiente binomial
int binomialCoeff(int n, int k)
{
    // Base Cases
    if (k > n)
        return 0;
    if (k == 0 || k == n)
        return 1;
 
    // Recur
    return binomialCoeff(n - 1, k - 1)
           + binomialCoeff(n - 1, k);
}


																	//Method 2: Dynamic Programming(DP) 
// A utility function that returns
// maximum of two integers
int max(int a, int b)
{
    return (a > b) ? a : b;
}
 
// Returns the maximum value that
// can be put in a knapsack of capacity W
int knapSack(int W, int wt[], int val[], int n)
{
    int i, w;
      vector<vector<int> > K(n + 1, vector<int>(W + 1));
 
    // Build table K[][] in bottom up manner
    for(i = 0; i <= n; i++)
    {
        for(w = 0; w <= W; w++)
        {
            if (i == 0 || w == 0)
                K[i][w] = 0;
            else if (wt[i - 1] <= w)
                K[i][w] = max(val[i - 1] +
                                K[i - 1][w - wt[i - 1]],
                                K[i - 1][w]);
            else
                K[i][w] = K[i - 1][w];
        }
    }
    return K[n][W];
}
															//Mochila Fraccionaria (Greedy)

int knapSackRecG(int W, int wt[],
                int val[], int i,
                int** dp)
{
    // base condition
    if (i < 0)
        return 0;
    if (dp[i][W] != -1)
        return dp[i][W];
 
    if (wt[i] > W) {
 
        // Store the value of function call
        // stack in table before return
        dp[i][W] = knapSackRecG(W, wt,
                               val, i - 1,
                               dp);
        return dp[i][W];
    }
    else {
        // Store value in a table before return
        dp[i][W] = max(val[i]
                      + knapSackRecG(W - wt[i],
                                   wt, val,
                                   i - 1, dp),
                       knapSackRecG(W, wt, val,
                                   i - 1, dp));
 
        // Return value of table after storing
        return dp[i][W];
    }
}
 
int knapSackG(int W, int wt[], int val[], int n)
{
    // double pointer to declare the
    // table dynamically
    int** dp;
    dp = new int*[n];
 
    // loop to create the table dynamically
    for (int i = 0; i < n; i++)
        dp[i] = new int[W + 1];
 
    // loop to initially filled the
    // table with -1
    for (int i = 0; i < n; i++)
        for (int j = 0; j < W + 1; j++)
            dp[i][j] = -1;
    return knapSackRecG(W, wt, val, n - 1, dp);
}

																	//Algoritmo de 'LCS (Enfoque Top-down)'

int maxi(int a, int b);

/* Returns length of LCS for X[0..m-1], Y[0..n-1] */
int lcs( char *X, char *Y, int m, int n )
{
	if (m == 0 || n == 0)
		return 0;
	if (X[m-1] == Y[n-1])
		return 1 + lcs(X, Y, m-1, n-1);
	else
		return maxi(lcs(X, Y, m, n-1), lcs(X, Y, m-1, n));
}

/* Utility function to get max of 2 integers */
int maxi(int a, int b)
{
	return (a > b)? a : b;
}

																	//Algoritmo de 'LCS (Enfoque Botton-Up)'

int maxis(int a, int b);

/* Returns length of LCS for X[0..m-1], Y[0..n-1] */
int lcsb( char *X, char *Y, int m, int n )
{
int L[m+1][n+1];
int i, j;

/* Following steps build L[m+1][n+1] in bottom up fashion. Note
	that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
for (i=0; i<=m; i++)
{
	for (j=0; j<=n; j++)
	{
	if (i == 0 || j == 0)
		L[i][j] = 0;

	else if (X[i-1] == Y[j-1])
		L[i][j] = L[i-1][j-1] + 1;

	else
		L[i][j] = maxis(L[i-1][j], L[i][j-1]);
	}
}
	
/* L[m][n] contains length of LCS for X[0..n-1] and Y[0..m-1] */
return L[m][n];
}

/* Utility function to get max of 2 integers */
int maxis(int a, int b)
{
	return (a > b)? a : b;
}

int main()
	{
	char o;
	int n,k,W,t,m;
	int wt[100];
	int val[100];
	char X[100];
	char Y[100];
	printf(" Lopez Perez Alberto Andrei \t '202160404' \n Campos Duran Fabrizio \t '2021630223' \n");
	printf("\n");
	while(o!='#'){
	printf("\n__________________________________________________________________________________________\n");
	printf("Bienvenidos al codigo de la 'Practica 4'.\n");
	printf("Ingrese la opcion correspondiente a la 'Programación dinámica (DP)' que desea utilizar.\n");
	printf("\tIngrese '1' para la secuencia Fibonacci de 'Enfoque Botton-up'.\n");
	printf("\tIngrese '2' para la secuencia Fibonacci de 'Enfoque Top-down'.\n");
	printf("\tIngrese '3' para al Algoritmo de 'Coeficiente Binomial'.\n");
	printf("\tIngrese '4' para el Algoritmo de 'Mochila (DP)'.\n");
	printf("\tIngrese '5' para el Algoritmo de 'Mochila Fraccionada (Greedy)'.\n");
	printf("\tIngrese '6' para el Algoritmo de 'LCS (Enfoque Top-down)'.\n");
	printf("\tIngrese '7' para el Algoritmo de 'LCS (Enfoque Botton-Up)'.\n");
	printf("\tIngrese '0' para salir del programa.\n");	
	printf("-------------------------------------------------------------------------------------------\n");
	printf("Su opcion es: ");
	scanf("%c",&o); fflush(stdin);
	


	switch(o){
		case '0':
			return 0;
			break;
			
		case '1':
			printf("Bienvenido a la Secuencia Fibonacci de Enfoque Botton-up\n");
			printf("\nIngrese el tamano de la serie Fibonacci: ");
			scanf("%i",&n); fflush(stdin);
			GFG g;
			printf("\nSu numero Fibonacci es: %i",g.fib(n));
			printf("\n");
			system("PAUSE");
			break;
			
		case '2':
			printf("Bienvenido a la Secuencia Fibonacci de Enfoque Top-down\n");
			printf("\nIngrese el tamano de la serie Fibonacci: ");
			scanf("%i",&n); fflush(stdin);
			memset(dp, -1, sizeof(dp));
			printf("\nSu numero Fibonacci es: %i",fib(n));
			printf("\n");
			system("PAUSE");
			break;
		
			
		case '3':
			printf("Bienvenido a al Algoritmo de Coeficiente Binomial\n");
			printf("\nIngrese la n del Coeficiente Binomial: ");
			scanf("%i",&n); fflush(stdin);
			printf("\nIngrese la k del Coeficiente Binomial: ");
			scanf("%i",&k); fflush(stdin);			
			printf("\nEl valor del Coeficiente Binomial '(n/k)' es: %i",binomialCoeff(n, k));
			printf("\n");
			system("PAUSE");		
			break;
			
		case '4':			
			printf("Bienvenido a al Algoritmo de Mochila (DP)\n");
			printf("\nIngrese el peso que va a soportar la mochila: ");
			scanf("%i",&W); fflush(stdin);
			printf("\nIngrese la cantidad de objetos a meter en la mochila: ");
			scanf("%i",&n); fflush(stdin);
			for(t=1;t<=n;t++){
				printf("Ingrese el peso del objeto %i: ",t);
				scanf("%i",&wt[t-1]); fflush(stdin);
				printf("Ingrese el valor del objeto %i: ",t);
				scanf("%i",&val[t-1]); fflush(stdin);				
			}
			printf("\nEl valor maximo de la mochila es: %i",knapSack(W, wt, val, n));
			printf("\n");
			system("PAUSE");			
			break;
		case '5':
			printf("Bienvenido a al Algoritmo de Mochila Fraccionaria (Greedy)\n");
			printf("\nIngrese el peso que va a soportar la mochila: ");
			scanf("%i",&W); fflush(stdin);
			printf("\nIngrese la cantidad de objetos a meter en la mochila: ");
			scanf("%i",&n); fflush(stdin);
			for(t=1;t<=n;t++){
				printf("Ingrese el peso del objeto %i: ",t);
				scanf("%i",&wt[t-1]); fflush(stdin);
				printf("Ingrese el valor del objeto %i: ",t);
				scanf("%i",&val[t-1]); fflush(stdin);				
			}
			printf("\nEl valor maximo de la mochila es: %i",knapSackG(W, wt, val, n));	
			printf("\n");
			system("PAUSE");					
			break;
		case '6':
			printf("Bienvenido a al Algoritmo de 'LCS (Enfoque Top-down)'\n");
			printf("\nIngrese cuantas letras lleva la primera palabra: ");
			scanf("%i",&m); fflush(stdin);
			for(t=1;m>=t;t++){
				printf("Ingrese la letra %i de su primera palabra: ",t);
				scanf("%s",&X[t-1]);
			}
			printf("\nIngrese cuantas letras lleva la segunda palabra: ");
			scanf("%i",&n); fflush(stdin);
			for(t=1;n>=t;t++){
				printf("Ingrese la letra %i de su palabra: ",t);
				scanf("%s",&Y[t-1]);
			}			
			printf("El tamano de LCS es: %i",lcs( X, Y, m, n ) );
			printf("\n");
			system("PAUSE");			
			break;
		case '7':
			printf("Bienvenido a al Algoritmo de 'LCS (Enfoque Botton-Up)'\n");
			printf("\nIngrese cuantas letras lleva la primera palabra: ");
			scanf("%i",&m); fflush(stdin);
			for(t=1;m>=t;t++){
				printf("Ingrese la letra %i de su primera palabra: ",t);
				scanf("%s",&X[t-1]);
			}
			printf("\nIngrese cuantas letras lleva la segunda palabra: ");
			scanf("%i",&n); fflush(stdin);
			for(t=1;n>=t;t++){
				printf("Ingrese la letra %i de su palabra: ",t);
				scanf("%s",&Y[t-1]);
			}			
			printf("El tamano de LCS es: %i",lcsb( X, Y, m, n ) );
			printf("\n");
			system("PAUSE");			
			break;
		default:
			printf("\nHas ingresado algo erroneo, vuelve a intentarlo\n");
			break;
			
			
	}
	}

	return 0;}

