
# Graph Algorithms and Data Structures Project 

## About the Project
This project was part of my Data Structures course.  
It focuses on building and testing different data structures and graph algorithms using Java.

The main topics covered are:
- Minimum Spanning Tree using **Kruskal's Algorithm**
- **Weighted Graphs** implementation
- **Disjoint Sets** (Union-Find)
- **Hash Tables**
- **Custom Linked Lists**

The code is organized into packages based on the type of data structure or algorithm.

---

## Features
- Build and manipulate a weighted undirected graph (WUGraph)
- Implement Kruskal’s algorithm to find a minimum spanning tree
- Create and use hash tables with chaining
- Create linked list classes for queue operations
- Handle disjoint sets for Kruskal’s algorithm

---

## Technologies Used
- **Java** (version 17+)
- Basic concepts of **Data Structures and Algorithms**
- Manual implementation of **Hash Tables**, **Queues**, **Linked Lists**, and **Graphs**

---

## How to Run the Program 🚀
1. Make sure you have Java installed.
2. Clone or download this repository.
3. Navigate to the project folder.
4. Compile all Java files:

```bash
javac */*.java *.java
```

5. Run the main test files to check the implementations:

```bash
java KruskalTest
```
or
```bash
java WUGTest
```

---

## Project Structure
```
Graph-Algorithms-Project/
├── KruskalTest.java
├── WUGTest.java
├── graph/
│   ├── Neighbors.java
│   ├── WUGraph.java
│   ├── VertexPair.java
├── list/
│   ├── LinkedQueue.java
│   ├── DList.java
│   ├── SListNode.java
│   ├── Queue.java
│   ├── sorts.java
│   ├── QueueEmptyException.java
├── graphalg/
│   ├── Kruskal.java
├── set/
│   ├── DisjointSets.java
├── dict/
│   ├── HashTableChained.java
│   ├── Dictionary.java
│   ├── Entry.java
├── readme.pdf (original project instructions, optional)
├── README.md (this file)
```

---

## What I Learned
- How graphs are built and represented in code
- How minimum spanning trees work
- How to design hash tables and linked lists
- How to organize larger Java projects into multiple classes

---

## Author
**Gustavo Oliveira Dos Santos**  
Computer Science Student | Future Software Engineer 🚀
