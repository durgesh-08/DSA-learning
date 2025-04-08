package com.learning.graphs;

import java.util.List;

public class DirectedGraph {

    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();

//        graph.addVertices("a", "b", "c", "d", "e", "f");
//        graph.addDirectedEdge("a", "b");
//        graph.addDirectedEdge("a", "c");
//        graph.addDirectedEdge("b", "d");
//        graph.addDirectedEdge("c", "e");
//        graph.addDirectedEdge("d", "f");
//        graph.depthFirstSearch(graph.getAdjList(), "a");
//        System.out.println();
//        graph.breadthFirstSearch(graph.getAdjList(), "a");
        graph.addVertices("f", "g", "h", "i", "j", "k");
        graph.addDirectedEdges("f", List.of("g", "i"));
        graph.addDirectedEdge("g", "h");
        graph.addDirectedEdge("j", "i");
        graph.addDirectedEdges("i", List.of("g", "k"));
        System.out.println(graph.hasPathUsingBreadthFirstSearch(graph.getAdjList(), "f", "k"));
    }
}
