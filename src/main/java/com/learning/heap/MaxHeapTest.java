package com.learning.heap;

public class MaxHeapTest {

    public static void main(String[] args) {

        MaxIntHeap maxIntHeap = new MaxIntHeap();
        maxIntHeap.addAll(20,17, 10, 15, 25);
        System.out.println(maxIntHeap);

        System.out.println(maxIntHeap.poll());
        System.out.println(maxIntHeap);
        maxIntHeap.add(100);
        maxIntHeap.add(99);
        maxIntHeap.add(1);
        maxIntHeap.add(9);
        maxIntHeap.add(-1);
        maxIntHeap.add(1000);
        maxIntHeap.add(2000);
        System.out.println(maxIntHeap);


    }
}
