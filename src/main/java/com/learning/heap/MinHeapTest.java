package com.learning.heap;

public class MinHeapTest {

    public static void main(String[] args) throws IllegalAccessException {
        MinIntHeap minIntHeap = new MinIntHeap();
        minIntHeap.addAll(25, 20, 17, 15, 10);
        System.out.println(minIntHeap);
        System.out.println(minIntHeap.peek());
        System.out.println(minIntHeap.poll());
        System.out.println(minIntHeap.peek());
        System.out.println(minIntHeap);
        System.out.println();
        minIntHeap.add(9);
        minIntHeap.add(1);
        minIntHeap.add(100);
        System.out.println(minIntHeap);

        minIntHeap.add(-1);
        minIntHeap.add(-100);
        minIntHeap.add(2000);
        System.out.println(minIntHeap);
    }
}
