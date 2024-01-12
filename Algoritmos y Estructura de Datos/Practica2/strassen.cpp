#include <iostream>
 
using namespace std;
 
#define N 2 // Tome una matriz cuadrada bidimensional como ejemplo
 
 template <typename T> // Use la plantilla para asegurarse de que la matriz pueda ser de diferentes tipos, como int, double, etc.
void output(T D[N][N],int n);
 
template<typename T>
void strassen(T A[N][N],T B[N][N],T C[N][N],int n);
 
int main()
{
    int A[N][N]={1,2,3,4};
         cout << "valor de la matriz A" << endl;
         output (A, N); // Genera el valor de la matriz A primero
    int B[N][N]={5,6,7,8};
         cout << "El valor de la matriz B" << endl;
         output (B, N); // Genera el valor de la matriz B primero
    int C[N][N];
    strassen(A,B,C,N);
         cout << "El valor de la matriz C" << endl;
         output (C, N); // valor de matriz de salida C
 
    return 0;
}
 
template<typename T>
void output(T D[N][N],int n)
{
    for(int i=0;i<n;++i)
    {
        for(int j=0;j<n;++j)
        {
            cout<<D[i][j]<<" ";
        }
        cout<<endl;
    }
 
 
}
 
template<typename T>
 void matrixAdd (T a [N] [N], T b [N] [N], T c [N] [N], int n) // Definir la suma de la matriz
{
    for(int i=0;i<n;++i)
        for(int j=0;j<n;++j)
            c[i][j]=a[i][j]+b[i][j];
}
 
template<typename T>
 void matrixSub (T a [N] [N], T b [N] [N], T c [N] [N], int n) // definir la resta de la matriz
{
    for(int i=0;i<n;++i)
        for(int j=0;j<n;++j)
            c[i][j]=a[i][j]-b[i][j];
}
 
template<typename T>
 void matrixMul (T a [N] [N], T b [N] [N], T c [N] [N]) // definir la multiplicación de matrices
{
         for (int i = 0; i <2; ++ i) // Menos de 2 es porque strassen usa la recursividad, y el signo final de la recursividad finalmente se divide en una matriz de segundo orden
        for(int j=0;j<2;++j)
        {
            c[i][j]=0;
            for(int k=0;k<2;++k)
                c[i][j]+=a[i][k]*b[k][j];
        }
}
 
template<typename T>
void strassen(T A[N][N],T B[N][N],T C[N][N],int n)
{
         T A11 [N] [N], A12 [N] [N], A21 [N] [N], A22 [N] [N]; // dividir la matriz en bloques
    T B11[N][N],B12[N][N],B21[N][N],B22[N][N];
    T C11[N][N],C12[N][N],C21[N][N],C22[N][N];
         T S1 [N] [N], S2 [N] [N], S3 [N] [N], S4 [N] [N], S5 [N] [N], S6 [N] [N], S7 [N] [N]; // 7 coeficientes definidos por strassen
         T temp1 [N] [N], temp2 [N] [N]; // almacenar la cantidad intermedia
         if (n == 2) // indicador de fin recursivo
        matrixMul(A,B,C);
    else
    {
        for(int i=0;i<n/2;++i)
            for(int j=0;j<n/2;++j)
            {
                                 A11 [i] [j] = A [i] [j]; // dividir la matriz en 4 bloques idénticos
                A12[i][j]=A[i][j+n/2];
                A21[i][j]=A[i+n/2][j];
                A22[i][j]=A[i+n/2][j+n/2];
 
                B11[i][j]=B[i][j];
                B12[i][j]=B[i][j+n/2];
                B21[i][j]=B[i+n/2][j];
                B22[i][j]=B[i+n/2][j+n/2];
            }
     /*S1=(A11+A22)×(B11+B22)*/
     matrixAdd(A11,A22,temp1,n/2);
     matrixAdd(B11,B22,temp2,n/2);
     strassen(temp1,temp2,S1,n/2);
     /*S2=(A21+A22)×B11*/
     matrixAdd(A21,A22,temp1,n/2);
     strassen(temp1,B11,S2,n/2);
     /*S3=A11*(B12+B22)*/
     matrixSub(B12,B22,temp1,n/2);
     strassen(A11,temp1,S3,n/2);
     /*S4=A22×(B21+B11)*/
     matrixSub(B21,B11,temp1,n/2);
     strassen(A22,temp1,S4,n/2);
     /*S5=(A11+A12)×B22*/
     matrixAdd(A11,A12,temp1,n/2);
     strassen(temp1,B22,S5,n/2);
     /*S6=(A11+A22)×(B11+B22)*/
     matrixSub(A21,A11,temp1,n/2);
     matrixAdd(B11,B12,temp2,n/2);
     strassen(temp1,temp2,S6,n/2);
     /*S7=(A12+A22)×(B21+B22)*/
     matrixSub(A12,A22,temp1,n/2);
     matrixAdd(B21,B22,temp2,n/2);
     strassen(temp1,temp2,S7,n/2);
     /*C11 = S1+S4-S5+S7*/
     matrixAdd(S1,S4,temp1,n/2);
     matrixSub(S7,S5,temp2,n/2);
     matrixAdd(temp1,temp2,C11,n/2);
     /*C12 = S3+S5*/
     matrixAdd(S3,S5,C12,n/2);
     /*C21 = S2+S4*/
     matrixAdd(S2,S4,C21,n/2);
     /*C22 = S1-S2+S3+S6*/
     matrixSub(S1,S2,temp1,n/2);
     matrixAdd(S3,S6,temp2,n/2);
     matrixAdd(temp1,temp2,C22,n/2);
 
           for (int i = 0; i <n / 2; ++ i) // Transformación inversa
         for(int j=0;j<n/2;++j)
         {
             C[i][j]=C11[i][j];
             C[i][j+n/2]=C12[i][j];
             C[i+n/2][j]=C21[i][j];
             C[i+n/2][j+n/2]= C22[i][j];
         }
    }
}
