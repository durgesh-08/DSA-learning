package com.learning.sorting;

import java.util.ArrayList;
import java.util.Random;

public class HeapSort {

    public static void heapify(ArrayList<Integer> numbers, int n, int i)    {
        var largest = i;
        var left = 2 *i+1;
        var right = 2 * i +2;
        if(left < n && numbers.get(left) > numbers.get(largest))
            largest = left;
        if(right < n && numbers.get(right) > numbers.get(largest))
            largest = right;

        if(largest != i)    {
            var tmp = numbers.get(i);
            numbers.set(i, numbers.get(largest));
            numbers.set(largest, tmp);
            heapify(numbers, n , largest);
        }
    }

    public static void sortElements(ArrayList<Integer> numbers) {
        var n = numbers.size();
        for (int i = n/2 -1; i >=0; i--) {
            heapify(numbers, n, i);
        }

        for (int i = n-1; i > 0; i--) {
            var tmp = numbers.get(0);
            numbers.set(0, numbers.get(i));
            numbers.set(i, tmp);
            heapify(numbers, i, 0);
        }
    }

    public static void main(String[] args) {
        var numbers = new ArrayList<Integer>();
        new Random().ints(5, 100, 999).forEach(numbers::add);
        numbers.forEach(System.out::println);
        System.out.println();
        sortElements(numbers);
        numbers.forEach(System.out::println);
    }

}
