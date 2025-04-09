package com.learning.heap;

import java.util.Arrays;

public class MinIntHeap {

    private int capacity = 10;
    private int size = 0;

    int[] items = new int[capacity];

    private int getLeftChildIndex(int parentIndex)   {
        return 2 * parentIndex + 1;
    }

    private int  getRightChildIndex(int parentIndex)    {
        return 2 * parentIndex + 2;
    }

    private int getParentIndex(int childIndex)  {
        return (childIndex - 1)/2;
    }

    private boolean hasLeftChild(int parentIndex)   {
        return getLeftChildIndex(parentIndex) < size;
    }

    private boolean hasRightChild(int parentIndex)  {
        return getRightChildIndex(parentIndex) < size;
    }

    private boolean hasParent(int childIndex)   {
        return getParentIndex(childIndex) >= 0;
    }

    private int leftChild(int index)    {
        return items[getLeftChildIndex(index)];
    }

    private int rightChild(int index)   {
        return items[getRightChildIndex(index)];
    }

    private int parent(int index)   {
        return items[getParentIndex(index)];
    }

    private void swap(int indexA, int indexB)    {
        var temp = items[indexA];
        items[indexA] = items[indexB];
        items[indexB] = temp;
    }

    private void ensureExtraCapacity()  {
        if(size == capacity)    {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        }
    }

    public int peek() throws IllegalAccessException {
        if(size == 0) throw new IllegalAccessException();
        return items[0];
    }

    public int poll() throws IllegalAccessException {
        if(size == 0) throw new IllegalAccessException();
        int item = items[0];
        items[0] = items[size - 1];
        size -= 1;
        heapifyDown();
        return item;
    }

    public void addAll(int ...items)    {
        Arrays.stream(items).forEach(this::add);
    }

    public int getSize() {
        return size;
    }

    public void add(int item)   {
        ensureExtraCapacity();
        items[size] = item;
        size++;
        heapifyUp();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("| ");
        for (int i = 0; i < size; i++) {
            string.append(items[i]).append(" | ");
        }
        return string.toString();
    }

    public void heapifyUp() {
        int index = size - 1;
        while(hasParent(index) && parent(index) > items[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    public void heapifyDown()   {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if(hasRightChild(index) && rightChild(index) < leftChild(index))    {
                smallerChildIndex = getRightChildIndex(index);
            }

            if(items[index] > items[smallerChildIndex]) {
                swap(index, smallerChildIndex);
                index =  smallerChildIndex;
            }   else {
                break;
            }
        }
    }

}
