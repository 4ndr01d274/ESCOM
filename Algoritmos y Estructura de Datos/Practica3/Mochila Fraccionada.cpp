// C/C++ program to solve fractional Knapsack Problem
#include <bits/stdc++.h>

using namespace std;

// Structure for an item which stores weight and
// corresponding value of Item
struct Item {
	int value, weight;

	// Constructor
	Item(int value, int weight)
	{
	this->value=value;
	this->weight=weight;
	}
};

// Comparison function to sort Item according to val/weight
// ratio
bool cmp(struct Item a, struct Item b)
{
	double r1 = (double)a.value / (double)a.weight;
	double r2 = (double)b.value / (double)b.weight;
	return r1 > r2;
}

// Main greedy function to solve problem
double fractionalKnapsack(int W, struct Item arr[], int n)
{
	// sorting Item on basis of ratio
	sort(arr, arr + n, cmp);

	// Uncomment to see new order of Items with their
	// ratio
	printf("\nOrden\tValor\tPeso\tProporcion\n");
	for (int i = 0; i < n; i++)
	{
		cout << "No." << i+1 <<"\t" << arr[i].value << "\t " << arr[i].weight << "\t  "
			<< ((double)arr[i].value / arr[i].weight) <<
	endl;
	}
	

	int curWeight = 0; // Current weight in knapsack
	double finalvalue = 0.0; // Result (value in Knapsack)

	// Looping through all Items
	for (int i = 0; i < n; i++) {
		// If adding Item won't overflow, add it completely
		if (curWeight + arr[i].weight <= W) {
			curWeight += arr[i].weight;
			finalvalue += arr[i].value;
		}

		// If we can't add current Item, add fractional part
		// of it
		else {
			int remain = W - curWeight;
			finalvalue += arr[i].value
						* ((double)remain
							/ (double)arr[i].weight);
			break;
		}
	}

	// Returning final value
	return finalvalue;
}

// Driver code
int main()
{	
	int W; // Weight of knapsack
	int x;
	int i;
	Item arr[]={{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0}};
	printf("Ingrese el peso de la Mochila: ");
	scanf("%i",&W); fflush(stdin);
	printf("\nIngrese la cantidad de Objetos que desea guardar (Maximo 15 Objetos): ");
	scanf("%i",&x); fflush(stdin);
	
	for (i = 0; i < x; i++) {
		printf("\nIngrese el peso del objeto %i: ",i+1);
		scanf("%i",&arr[i].weight); fflush(stdin);
		printf("\nIngrese el valor del objeto %i: ",i+1);
		scanf("%i",&arr[i].value); fflush(stdin);
		}
	
	int n = x;

	// Function call
	cout << "El maximo valor que nosotros podemos guardar tener es= "
		<< fractionalKnapsack(W, arr, n);
	return 0;
}

