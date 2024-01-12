#include <bits/stdc++.h>
#include <windows.h>
#define COINS 4
#define CANTIDAD 4
#define MAX 200
#define R 9

using namespace std;
														//Cambio Minimo de Monedas
int coins[COINS] = { 60, 24};      
int cantidad[CANTIDAD] = {40, 25};  
void findMinL(int cost)
{
    int coinList[MAX] = { 0 };  
    int i, a, k = 0,x=0;   
    for (i = COINS - 1; i >= 0; i--) {     
    	a = cantidad[i];
        while (cost >= coins[i] && a > 0) {   
            cost -= coins[i];								
            a--;						
            coinList[k++] = coins[i];		
        }
    }
    for (i = 0; i < k; i++) {
        x++;
        printf("%d ", coinList[i]);
    }
    printf("\nLa cantidad de monedas utilizadas son: %i",x);
    return;
}

	int deno[] = { 60, 24};
	int n = sizeof(deno) / sizeof(deno[0]);

	void findMin(int V)
	{
		sort(deno, deno + n);
		vector<int> ans;
		for (int i = n - 1; i >= 0; i--) {
			while (V >= deno[i]) {
			V -= deno[i];
			ans.push_back(deno[i]);
		}
	}

	for (int i = 0; i < ans.size(); i++)
		cout << ans[i] << " ";
}





														//Mochila Fraccionada
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


														// Codificacion de Huffman
struct MinHeapNode {

	// One of the input characters
	char data;

	// Frequency of the character
	unsigned freq;

	// Left and right child
	MinHeapNode *left, *right;

	MinHeapNode(char data, unsigned freq)

	{

		left = right = NULL;
		this->data = data;
		this->freq = freq;
	}
};

// For comparison of
// two heap nodes (needed in min heap)
struct compare {

	bool operator()(MinHeapNode* l, MinHeapNode* r)

	{
		return (l->freq > r->freq);
	}
};

// Prints huffman codes from
// the root of Huffman Tree.
void printCodes(struct MinHeapNode* root, string str)
{

	if (!root)
		return;

	if (root->data != '$')
		cout << root->data << ": " << str << "\n";

	printCodes(root->left, str + "0");
	printCodes(root->right, str + "1");
}

// The main function that builds a Huffman Tree and
// print codes by traversing the built Huffman Tree
void HuffmanCodes(char data[], int freq[], int size)
{
	struct MinHeapNode *left, *right, *top;

	// Create a min heap & inserts all characters of data[]
	priority_queue<MinHeapNode*, vector<MinHeapNode*>, compare> minHeap;

	for (int i = 0; i < size; ++i)
		minHeap.push(new MinHeapNode(data[i], freq[i]));

	// Iterate while size of heap doesn't become 1
	while (minHeap.size() != 1) {

		// Extract the two minimum
		// freq items from min heap
		left = minHeap.top();
		minHeap.pop();

		right = minHeap.top();
		minHeap.pop();

		// Create a new internal node with
		// frequency equal to the sum of the
		// two nodes frequencies. Make the
		// two extracted node as left and right children
		// of this new node. Add this node
		// to the min heap '$' is a special value
		// for internal nodes, not used
		top = new MinHeapNode('$', left->freq + right->freq);

		top->left = left;
		top->right = right;

		minHeap.push(top);
	}

	// Print Huffman codes using
	// the Huffman tree built above
	printCodes(minHeap.top(), "");
}

														//Arbol de expansion minimo de Prim
														
int minKey(int key[], bool mstSet[])
{
	// Initialize min value
	int min = INT_MAX, min_index;

	for (int v = 0; v < R; v++)
		if (mstSet[v] == false && key[v] < min)
			min = key[v], min_index = v;

	return min_index;
}

// A utility function to print the
// constructed MST stored in parent[]
void printMST(int parent[], int graph[R][R])
{
	cout<<"Edge \tWeight\n";
	for (int i = 1; i < R; i++)
		cout<<parent[i]<<" - "<<i<<" \t"<<graph[i][parent[i]]<<" \n";
}

// Function to construct and print MST for
// a graph represented using adjacency
// matrix representation
void primMST(int graph[R][R])
{
	// Array to store constructed MST
	int parent[R];
	
	// Key values used to pick minimum weight edge in cut
	int key[R];
	
	// To represent set of vertices included in MST
	bool mstSet[R];

	// Initialize all keys as INFINITE
	for (int i = 0; i < R; i++)
		key[i] = INT_MAX, mstSet[i] = false;

	// Always include first 1st vertex in MST.
	// Make key 0 so that this vertex is picked as first vertex.
	key[0] = 0;
	parent[0] = -1; // First node is always root of MST

	// The MST will have V vertices
	for (int count = 0; count < R - 1; count++)
	{
		// Pick the minimum key vertex from the
		// set of vertices not yet included in MST
		int u = minKey(key, mstSet);

		// Add the picked vertex to the MST Set
		mstSet[u] = true;

		// Update key value and parent index of
		// the adjacent vertices of the picked vertex.
		// Consider only those vertices which are not
		// yet included in MST
		for (int v = 0; v < R; v++)

			// graph[u][v] is non zero only for adjacent vertices of m
			// mstSet[v] is false for vertices not yet included in MST
			// Update the key only if graph[u][v] is smaller than key[v]
			if (graph[u][v] && mstSet[v] == false && graph[u][v] < key[v])
				parent[v] = u, key[v] = graph[u][v];
	}

	// print the constructed MST
	printMST(parent, graph);
}

																		//Algoritmo de arbol de expansion minimo de Kruskal
																		/*
class Edge {
public:
	int src, dest, weight;
};

// a structure to represent a connected,
// undirected and weighted graph
class Grafico {
public:
	
	// V-> Number of vertices, E-> Number of edges
	int B, E;

	// graph is represented as an array of edges.
	// Since the graph is undirected, the edge
	// from src to dest is also edge from dest
	// to src. Both are counted as 1 edge here.
	Edge* edge;
};

// Creates a graph with V vertices and E edges
Grafico* createGrafico(int B, int E)
{
	Grafico* grafico = new Grafico;
	grafico->B = B;
	grafico->E = E;

	grafico->edge = new Edge[E];

	return grafico;
}

// A structure to represent a subset for union-find
class subset {
public:
	int parents;
	int rank;
};

// A utility function to find set of an element i
// (uses path compression technique)
int find(subset subsets[], int i)
{
	// find root and make root as parent of i
	// (path compression)
	if (subsets[i].parents != i)
		subsets[i].parents
			= find(subsets, subsets[i].parents);

	return subsets[i].parents;
}

// A function that does union of two sets of x and y
// (uses union by rank)
void Union(subset subsets[], int x, int y)
{
	int xroot = find(subsets, x);
	int yroot = find(subsets, y);

	// Attach smaller rank tree under root of high
	// rank tree (Union by Rank)
	if (subsets[xroot].rank < subsets[yroot].rank)
		subsets[xroot].parents = yroot;
	else if (subsets[xroot].rank > subsets[yroot].rank)
		subsets[yroot].parents = xroot;

	// If ranks are same, then make one as root and
	// increment its rank by one
	else {
		subsets[yroot].parents = xroot;
		subsets[xroot].rank++;
	}
}

// Compare two edges according to their weights.
// Used in qsort() for sorting an array of edges
int myComp(const void* a, const void* b)
{
	Edge* a1 = (Edge*)a;
	Edge* b1 = (Edge*)b;
	return a1->weight > b1->weight;
}

// The main function to construct MST using Kruskal's
// algorithm
void KruskalMST(Grafico* grafico)
{
	int B = grafico->B;
	Edge result[B]; // Tnis will store the resultant MST
	int e = 0; // An index variable, used for result[]
	int i = 0; // An index variable, used for sorted edges

	// Step 1: Sort all the edges in non-decreasing
	// order of their weight. If we are not allowed to
	// change the given graph, we can create a copy of
	// array of edges
	qsort(grafico->edge, grafico->E, sizeof(grafico->edge[0]),
		myComp);

	// Allocate memory for creating V ssubsets
	subset* subsets = new subset[(B * sizeof(subset))];

	// Create V subsets with single elements
	for (int v = 0; v < B; ++v)
	{
		subsets[v].parents = v;
		subsets[v].rank = 0;
	}

	// Number of edges to be taken is equal to V-1
	while (e < B - 1 && i < grafico->E)
	{
		// Step 2: Pick the smallest edge. And increment
		// the index for next iteration
		Edge next_edge = grafico->edge[i++];

		int x = find(subsets, next_edge.src);
		int y = find(subsets, next_edge.dest);

		// If including this edge does't cause cycle,
		// include it in result and increment the index
		// of result for next edge
		if (x != y) {
			result[e++] = next_edge;
			Union(subsets, x, y);
		}
		// Else discard the next_edge
	}

	// print the contents of result[] to display the
	// built MST
	cout << "Following are the edges in the constructed "
			"MST\n";
	int minimumCost = 0;
	for (i = 0; i < e; ++i)
	{
		cout << result[i].src << " -- " << result[i].dest
			<< " == " << result[i].weight << endl;
		minimumCost = minimumCost + result[i].weight;
	}
	// return;
	cout << "Minimum Cost Spanning Tree: " << minimumCost
		<< endl;
}
*/
int main()
	{
	char o;
	int n,W,x,i,size,B,E;
	Item arr[]={{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0},{0,0}};
					 	//0  1  2  3  4  5  6  7  8												
	int graph[R][R] = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, // 0
						{ 4, 0, 8, 0, 0, 0, 0, 11, 0 }, // 1
						{ 0, 8, 0, 7, 0, 4, 0, 0, 2 }, // 2
						{ 0, 0, 7, 0, 9, 14, 0, 0, 0 }, // 3
						{ 0, 0, 0, 9, 0, 10, 0, 0, 0 }, // 4
						{ 0, 0, 4, 14, 10, 0, 2, 0, 0 }, // 5
						{ 0, 0, 0, 0, 0, 2, 0, 1, 6 }, // 6
						{ 8, 11, 0, 0, 0, 0, 1, 0, 7 }, // 7
						{ 0, 0, 2, 0, 0, 0, 6, 7, 0 }};// 8
	printf(" Lopez Perez Alberto Andrei \t '202160404' \n Campos Duran Fabrizio \t '2021630223' \n");
	printf("\n");
	while(o!='#'){
	printf("\n__________________________________________________________________________________________\n");
	printf("Bienvenidos al codigo de la 'Practica 3.'\n");
	printf("Ingrese la opcion correspondiente al 'Algoritmo Voraz' que desea utilizar.\n");
	printf("\tIngrese '1' para el ordenamiento 'Cambio Minimo de Monedas'.\n");
	printf("\tIngrese '2' para el ordenamiento 'Mochila Fraccionada'.\n");
	printf("\tIngrese '3' para la 'Codificacion de Huffman'.\n");
	printf("\tIngrese '4' para el 'Arbol de expansion minimo de Prim'.\n");
//	printf("\tIngrese '5' para el 'Arbol de expansion minimo de Prim'.\n");
	printf("\tIngrese '0' para salir del programa.\n");	
	printf("-------------------------------------------------------------------------------------------\n");
	printf("Su opcion es: ");
	scanf("%c",&o); fflush(stdin);
	


	switch(o){
		case '0':
			return 0;
			break;
			
		case '1':
			printf("\n\t\tBienvenido al Problema de Cambio Minimo de Monedas\n");
			printf("___________________________________________________________________________________");
			printf("\nIngrese la cantidad que desea cambiar: ");
			scanf("%i",&n); fflush(stdin);	
			printf("El cambio de %i es: ",n);
			findMin(n);
			printf("\n");
			printf("\nEl cambio de %i Limitado es: ",n);
			findMinL(n);
			printf("\n");
			system("PAUSE");
			break;
			
		case '2':
			printf("\n\t\tBienvenido al Problema de Mochila Fraccionada\n");
			printf("___________________________________________________________________________________");
			printf("\nIngrese el peso de la Mochila: ");
			scanf("%i",&W); fflush(stdin);
			printf("\nIngrese la cantidad de Objetos que desea guardar (Maximo 15 Objetos): ");
			scanf("%i",&x); fflush(stdin);
	
			for (i = 0; i < x; i++) {
				printf("\nIngrese el peso del objeto %i: ",i+1);
				scanf("%i",&arr[i].weight); fflush(stdin);
				printf("\nIngrese el valor del objeto %i: ",i+1);
				scanf("%i",&arr[i].value); fflush(stdin);
				}
			n = x;
			// Function call
			cout << "El maximo valor que nosotros podemos guardar tener es= "
				<< fractionalKnapsack(W, arr, n);
			break;
			
		case '4':
/*					 	//0  1  2  3  4  5  6  7  8
	int graph[V][V] = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, // 0
						{ 4, 0, 8, 0, 0, 0, 0, 11, 0 }, // 1
						{ 0, 8, 0, 7, 0, 4, 0, 0, 2 }, // 2
						{ 0, 0, 7, 0, 9, 14, 0, 0, 0 }, // 3
						{ 0, 0, 0, 9, 0, 10, 0, 0, 0 }, // 4
						{ 0, 0, 4, 14, 10, 0, 2, 0, 0 }, // 5
						{ 0, 0, 0, 0, 0, 2, 0, 1, 6 }, // 6
						{ 8, 11, 0, 0, 0, 0, 1, 0, 7 }, // 7
						{ 0, 0, 2, 0, 0, 0, 6, 7, 0 }};// 8		*/
				//Es el grafo que ya esta comentado en la parte de arriba.
			printf("\n\t\tBienvenido al Problema deArbol de expansion minimo de Prim\n");
			printf("___________________________________________________________________________________\n");
				primMST(graph);
			break;
/*		case '5':
	B = 9; // Number of vertices in graph
	E = 13; // Number of edges in graph
	Grafico* grafico = createGrafico(B, E);

	// add edge 0-1
	grafico->edge[0].src = 0;
	grafico->edge[0].dest = 1;
	grafico->edge[0].weight = 4;

	// add edge 0-7
	grafico->edge[1].src = 0;
	grafico->edge[1].dest = 7;
	grafico->edge[1].weight = 8;

	// add edge 1-2
	grafico->edge[2].src = 1;
	grafico->edge[2].dest = 2;
	grafico->edge[2].weight = 8;

	// add edge 1-7
	grafico->edge[3].src = 1;
	grafico->edge[3].dest = 7;
	grafico->edge[3].weight = 11;

	// add edge 2-3
	grafico->edge[4].src = 2;
	grafico->edge[4].dest = 3;
	grafico->edge[4].weight = 7;
	
	// add edge 2-8
	grafico->edge[5].src = 2;
	grafico->edge[5].dest = 8;
	grafico->edge[5].weight = 2;

	// add edge 2-5
	grafico->edge[6].src = 2;
	grafico->edge[6].dest = 5;
	grafico->edge[6].weight = 4;
	
	// add edge 7-8
	grafico->edge[7].src = 7;
	grafico->edge[7].dest = 8;
	grafico->edge[7].weight = 7;
	
	// add edge 7-6
	grafico->edge[8].src = 7;
	grafico->edge[8].dest = 6;
	grafico->edge[8].weight = 1;
	
	// add edge 3-4
	grafico->edge[9].src = 3;
	grafico->edge[9].dest = 4;
	grafico->edge[9].weight = 9;
	
	// add edge 3-5
	grafico->edge[10].src = 3;
	grafico->edge[10].dest = 5;
	grafico->edge[10].weight = 14;
	
	// add edge 5-6
	grafico->edge[11].src = 5;
	grafico->edge[11].dest = 6;
	grafico->edge[11].weight = 2;
	
	// add edge 4-5
	grafico->edge[12].src = 4;
	grafico->edge[12].dest = 5;
	grafico->edge[12].weight = 10;
	
	// Function call
	KruskalMST(grafico);
			break;*/
		default:
			printf("\nHas ingresado algo erroneo, vuelve a intentarlo\n");
			break;
			
		case '3':
			printf("\n\t\tBienvenido a la 'Codificacion de Huffman'.\n");
			printf("___________________________________________________________________________________");
			printf("\nIngresa la cantidad de elementos que quieres codificar: ");
			scanf("%i",&size); fflush(stdin);
			int freq[size-1]; 
			char arrr[size-1];
			printf("\nIngresa letra por letra la palabra a cifrar, y su respectivo peso");
			for(i=0;i<size;i++){
				printf("\nIngresa la letra %i: ",i+1);
				scanf("%c",&arrr[i]); fflush(stdin);
				printf("\nIngresa el peso de la letra %i: ",i+1);
				scanf("%i",&freq[i]); fflush(stdin);
			}
			HuffmanCodes(arrr, freq, size);
			break;
			
	}
	}

	return 0;}

