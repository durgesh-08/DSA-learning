package com.learning.graphs;

import java.util.*;

public class Graph<T> {

    Map<T, List<T>> adjList;

    public Graph()  {
        this.adjList = new HashMap<>();
    }

    void addVertex(T node) {
        this.adjList.putIfAbsent(node, new ArrayList<>());
    }

    void addVertices(T ...vertices) {
        Arrays.stream(vertices).forEach(node -> this.adjList.putIfAbsent(node, new ArrayList<>()));
    }

    void addDirectedEdges(T node, List<T> neighbours)   {
        this.adjList.get(node).addAll(neighbours);
    }

    void removeVertex(T node)  {
        adjList.values().forEach(e -> e.remove(node));
        adjList.remove(node);
    }

    void addDirectedEdge(T node1, T node2)  {
        adjList.get(node1).add(node2);
    }

    void addEdges(T node, List<T> neighbors)    {
        neighbors.forEach(neighbor -> addEdge(node, neighbor));
    }

    void addEdge(T node1, T node2)  {
        adjList.putIfAbsent(node1, new ArrayList<>());
        adjList.putIfAbsent(node2, new ArrayList<>());
        adjList.get(node1).add(node2);
        adjList.get(node2).add(node1);
    }

    void removeDirectedEdge(T node1, T node2)   {
        var edges = adjList.get(node1);
        if(edges != null)   {
            edges.remove(node2);
        }
    }

    void removeEdge(T node1, T node2)   {
        var edges = adjList.get(node1);
        var edges2 = adjList.get(node2);
        if(edges != null)   {
            edges.remove(node2);
        }
        if(edges2 != null)  {
            edges2.remove(node1);
        }
    }

    List<T> getAdjVertices(T node)  {
        return adjList.get(node);
    }

    Map<T, List<T>> getAdjList()    {
        return this.adjList;
    }

    void depthFirstSearchIterative(Map<T, List<T>> adjList, T node)  {
        Stack<T> stack = new Stack<>();
        stack.push(node);
        while(!stack.isEmpty()) {
            T currentNode = stack.pop();
            System.out.print(currentNode + " ");
            adjList.get(currentNode).forEach(stack::push);
        }
    }

    void depthFirstSearch(Map<T, List<T>> adjList, T node)  {
        System.out.print(node + " ");
        adjList.get(node).forEach(neighbour -> depthFirstSearch(adjList, neighbour));
    }

    void breadthFirstSearch(Map<T, List<T>> adjList, T node)    {
        Queue<T> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty())    {
            T currentNode = queue.poll();
            System.out.print(currentNode + " ");
            adjList.get(currentNode).forEach(queue::offer);
        }
    }

    boolean hasPathUsingDepthFirstSearch(Map<T, List<T>> adjList, T src, T dest) {
        Stack<T> stack = new Stack<>();
        stack.push(src);
        while (!stack.isEmpty())    {
            var current = stack.pop();
            if(current == dest) {
                return true;
            }
            adjList.get(current).forEach(stack::push);
        }
        return false;
    }

    boolean hasPathUsingDepthFirstSearchRecursively(Map<T, List<T>> adjList, T src, T dest) {
        if(src == dest) {
            return true;
        }
        return adjList.get(src).stream().anyMatch(neighbour -> hasPathUsingDepthFirstSearchRecursively(adjList, neighbour, dest));
    }

    boolean hasPathUsingBreadthFirstSearch(Map<T, List<T>> adjList, T src, T dest)  {
        Queue<T> queue = new LinkedList<>();
        queue.offer(src);
        while (!queue.isEmpty())    {
            var current = queue.poll();
            if(current == dest) {
                return true;
            }
            adjList.get(current).forEach(queue::offer);
        }
        return false;
    }

    // For graph with cycle
    boolean hasPathUsingDepthFirstSearchForGraphWithCycle(Map<T, List<T>> adjList, T src, T dest)   {
        Stack<T> stack = new Stack<>();
        HashSet<T> visited = new HashSet<>();
        stack.push(src);
        visited.add(src);
        while (!stack.isEmpty())    {
            var currentNode = stack.pop();
            if(currentNode == dest) {
                return true;
            }
            adjList.get(currentNode).forEach(neighbour -> {
                if(!visited.contains(neighbour))    {
                    stack.push(neighbour);
                    visited.add(neighbour);
                }
            });
        }
        return false;
    }

    boolean hasPathUsingDepthFirstSearchForGraphWithCycleRecursively(Map<T, List<T>> adjList, T src, T dest)   {
        return hasPathCycleRecursive(adjList, src, dest, new HashSet<>());
    }

    boolean hasPathCycleRecursive(Map<T, List<T>> adjList, T src, T dest, Set<T> visited)   {
        if(src == dest) return true;
        if(visited.contains(src)) return false;
        visited.add(src);
        return adjList.get(src).stream().anyMatch(neighbour -> hasPathCycleRecursive(adjList, neighbour, dest, visited));
    }

    boolean hasPathUsingBreadthFirstSearchForGraphWithCycle(Map<T, List<T>> adjList, T src, T dest) {
        Queue<T> queue = new LinkedList<>();
        HashSet<T> visited = new HashSet<>();
        queue.offer(src);
        visited.add(src);
        while (!queue.isEmpty())    {
            var current = queue.poll();
            if(current == dest) {
                return true;
            }
            adjList.get(current).forEach(neighbour -> {
                if(!visited.contains(neighbour))    {
                    queue.offer(neighbour);
                    visited.add(neighbour);
                }
            });
        }
        return false;
    }
}
