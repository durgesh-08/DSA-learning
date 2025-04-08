package com.learning.graphs;

public class UndirectedGraph {

    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        graph.addEdge("i", "j");
        graph.addEdge("k", "i");
        graph.addEdge("m", "k");
        graph.addEdge("k", "l");
        graph.addEdge("o", "n");
        System.out.println(graph.getAdjList());
        System.out.println(graph.hasPathUsingDepthFirstSearchForGraphWithCycle(graph.getAdjList(), "i", "o"));
        System.out.println(graph.hasPathUsingBreadthFirstSearchForGraphWithCycle(graph.getAdjList(), "i", "k"));
        System.out.println(graph.hasPathUsingDepthFirstSearchForGraphWithCycleRecursively(graph.getAdjList(), "i", "o"));
    }
}
