package com.learning.heap;

import java.util.Arrays;

public class MaxIntHeap {

    private int size = 0;
    private int capacity = 10;

    int[] items = new int[capacity];

    private int getLeftChildIndex(int parentIndex) {return 2 * parentIndex + 1;}
    private int getRightChildIndex(int parentIndex) {return 2 * parentIndex + 2;}
    private int getParentIndex(int index)   { return (index -1 )/2;}

    private boolean hasLeftChild(int parentIndex)   {return getLeftChildIndex(parentIndex) < size;}
    private boolean hasRightChild(int parentIndex)  {return getRightChildIndex(parentIndex) < size;}
    private boolean hasParent(int index)  { return getParentIndex(index) >= 0; }

    private int leftChild(int index)    { return items[getLeftChildIndex(index)]; }
    private int rightChild(int index)   { return items[getRightChildIndex(index)]; }
    private int parent(int index)   { return items[getParentIndex(index)]; }

    private void swap(int indexA, int indexB)   {
        int temp = items[indexA];
        items[indexA] = items[indexB];
        items[indexB] = temp;
    }

    private void ensureExtraCapacity()  {
        if(size == capacity)    {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        }
    }

    public int peek()  {
        if(size == 0) throw new IllegalArgumentException();
        return items[0];
    }

    public int poll()   {
        if(size == 0) throw new IllegalArgumentException();
        var item = items[0];
        items[0] = items[size - 1];
        size--;
        heapifyDown();
        return item;
    }

    public void addAll(int ...items)    {
        Arrays.stream(items).forEach(this::add);
    }

    public void add(int item)   {
        ensureExtraCapacity();
        items[size] = item;
        size++;
        heapifyUp();
    }

    private void heapifyUp()    {
        var index = size - 1;
        while(hasParent(index) && parent(index) < items[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown()  {
        var index = 0;
        while (hasLeftChild(index)) {
            var biggerValueIndex = getLeftChildIndex(index);
            if(hasRightChild(index) && rightChild(index) > leftChild(index)) {
                biggerValueIndex = getRightChildIndex(index);
            }
            if(items[index] > items[biggerValueIndex])  {
                break;
            }   else {
                swap(index, biggerValueIndex);
                index = biggerValueIndex;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("| ");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(items[i]).append(" | ");
        }
        return stringBuilder.toString();
    }
}
