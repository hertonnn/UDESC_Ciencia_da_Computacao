// BFS algorithm in C

#include <stdio.h>
#include <stdlib.h>

#define SIZE 2000

struct queue {
  int items[SIZE];
  int front;
  int rear;
};

typedef struct {
    float sepal_length;
    float sepal_width;
    float petal_length;
    float petal_width;
    int key;
} Flower;

struct node {
  int vertex;
  struct node* next;
};

struct node* createNode(int);

struct Graph {
  int numVertices;
  struct node** adjLists;
  int* visited;
};

struct queue* createQueue();
void enqueue(struct queue* q, int);
int dequeue(struct queue* q);
int isEmpty(struct queue* q);
void printQueue(struct queue* q);
void printGraph(struct Graph* graph);

// BFS algorithm
void bfs(struct Graph* graph, int startVertex) {
  int count = 0;
  struct queue* q = createQueue();

  graph->visited[startVertex] = 1;
  enqueue(q, startVertex);

  while (!isEmpty(q)) {
    //printQueue(q);
    int currentVertex = dequeue(q);
    //printf("Visited %d\n", currentVertex);
    count++;

    struct node* temp = graph->adjLists[currentVertex];

    while (temp) {
      int adjVertex = temp->vertex;

      if (graph->visited[adjVertex] == 0) {
        graph->visited[adjVertex] = 1;
        enqueue(q, adjVertex);
      }
      temp = temp->next;
    }
  }
}

// Creating a node
struct node* createNode(int v) {
  struct node* newNode = malloc(sizeof(struct node));
  newNode->vertex = v;
  newNode->next = NULL;
  return newNode;
}

// Creating a graph
struct Graph* createGraph(int vertices) {
  struct Graph* graph = malloc(sizeof(struct Graph));
  graph->numVertices = vertices;

  graph->adjLists = malloc(vertices * sizeof(struct node*));
  graph->visited = malloc(vertices * sizeof(int));

  int i;
  for (i = 0; i < vertices; i++) {
    graph->adjLists[i] = NULL;
    graph->visited[i] = 0;
  }

  return graph;
}

// Add edge
void addEdge(struct Graph* graph, Flower src, Flower dest) {
  // Add edge from src to dest
  struct node* newNode = createNode(dest.key);
  newNode->next = graph->adjLists[src.key];
  graph->adjLists[src.key] = newNode;

  // Add edge from dest to src
  newNode = createNode(src.key);
  newNode->next = graph->adjLists[dest.key];
  graph->adjLists[dest.key] = newNode;
}

// Create a queue
struct queue* createQueue() {
  struct queue* q = malloc(sizeof(struct queue));
  q->front = -1;
  q->rear = -1;
  return q;
}

// Check if the queue is empty
int isEmpty(struct queue* q) {
  if (q->rear == -1)
    return 1;
  else
    return 0;
}

// Adding elements into queue
void enqueue(struct queue* q, int value) {
  if (q->rear == SIZE - 1)
    printf("\nQueue is Full!!");
  else {
    if (q->front == -1)
      q->front = 0;
    q->rear++;
    q->items[q->rear] = value;
  }
}

// Removing elements from queue
int dequeue(struct queue* q) {
  int item;
  if (isEmpty(q)) {
    printf("Queue is empty");
    item = -1;
  } else {
    item = q->items[q->front];
    q->front++;
    if (q->front > q->rear) {
      //printf("Resetting queue ");
      q->front = q->rear = -1;
    }
  }
  return item;
}

// Print the queue
void printQueue(struct queue* q) {
  int i = q->front;

  if (isEmpty(q)) {
    printf("Queue is empty");
  } else {
    printf("\nQueue contains \n");
    for (i = q->front; i < q->rear + 1; i++) {
      printf("%d ", q->items[i]);
    }
  }
}

// Print the graph 

void printGraph(struct Graph* graph){
    int v;
    printf("\nGrafo\n");
    for(v = 0; v < graph->numVertices; v++){
        struct node* temp = graph->adjLists[v];
        printf("\nVertex %d: ", v);
        while (temp)
        {
          printf("%d -> ", temp->vertex);
          temp = temp->next;
        } 
    }
    printf("\n");
}