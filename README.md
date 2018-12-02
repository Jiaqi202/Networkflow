# Networkflow
PROJECT FOR TCSS543 
Empirical Study of Network Flow
## GOAL: 
This project will show three network flow algorithms performance on different types of graphs. We will see which algorithm is better than others.
## Three algorithms for implementing and experimenting
1. Ford-Fulkerson  
2. Scaling Ford-Fulkerson
3. Preflow-push
## Code Structure
### Three algorithms: 
1. FordFulkerson.java    - Moran Wang
2. ScalingFordFulkerson.java         -  Jiaqi Wang
3. Preflowpush.java       - Zac Lu
### Graph code:
1. Edge.java
2. Vertex.java
3. InputLib.java
4. VertexData.java
5. KeyboardReader.java
6. SimpleGraph.java
### Auxilliary code
1. ResidualGraph.java
2. ResidualVertex.java
3. ResidualEdge.java
### Testcases:
1. Bipartite
2. FixedDegree
3. Mesh
4. Random
### Output data:
Codeotput.rtf
## Test Cases: -- By Jiaqi
### 1. Bipartite
#### Edge Capacity
* 50s-50t-0.5p-1min-200max.txt
* 50s-50t-0.5p-25min-200max.txt
* 50s-50t-0.5p-50min-200max.txt
* 50s-50t-0.5p-75min-200max.txt
* 50s-50t-0.5p-100min-200max.txt
* 50s-50t-0.5p-125min-200max.txt
* 50s-50t-0.5p-150min-200max.txt
* 50s-50t-0.5p-175min-200max.txt

#### Density
* 50s-50t-0.1p-1min-200max.txt
* 50s-50t-0.2p-1min-200max.txt
* 50s-50t-0.3p-1min-200max.txt
* 50s-50t-0.4p-1min-200max.txt
* 50s-50t-0.5p-1min-200max.txt
* 50s-50t-0.6p-1min-200max.txt
* 50s-50t-0.7p-1min-200max.txt
* 50s-50t-0.8p-1min-200max.txt

#### Vertices Amount
* 25s-25t-0.5p-1min-200max.txt
* 25s-50t-0.5p-1min-200max.txt
* 25s-75t-0.5p-1min-200max.txt
* 25s-100t-0.5p-1min-200max.txt
* 25s-125t-0.5p-1min-200max.txt
* 25s-150t-0.5p-1min-200max.txt
* 25s-175t-0.5p-1min-200max.txt
* 25s-200t-0.5p-1min-200max.txt
### 2. Fixed Degree
#### Edge Capacity
* （omitted）
#### Density
* （omitted）
#### Vertices Amount
* （omitted）
### 3. Random
#### Edge Capacity
* （omitted）
#### Density
* （omitted）
#### Vertices Amount
* （omitted）
### 4. Mesh
#### Edge Capacity
* （omitted）
#### Vertices Amount
* （omitted）
