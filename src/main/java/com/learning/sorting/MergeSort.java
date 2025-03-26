package com.learning.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class MergeSort {

    public static void merge(ArrayList<Integer> numbers, int start, int mid, int end)   {
        int n1 = mid - start + 1;
        int n2 = end - mid;

        //Create a copy of left and right arrys
        var left = new ArrayList<Integer>();
        var right = new ArrayList<Integer>();
        IntStream.range(0, n1).forEach(i ->  left.add(numbers.get(start + i)));
        IntStream.range(0, n2).forEach(i -> right.add(numbers.get(mid + 1 + i)));

        int i = 0, j = 0, k = start;
        while(i < n1 && j < n2) {
            if(left.get(i) <= right.get(j)) {
                numbers.set(k , left.get(i));
                i++;
            }   else {
                numbers.set(k, right.get(j));
                j++;
            }
            k++;
        }
        while(i < n1)   {
            numbers.set(k, left.get(i));
            i++;
            k++;
        }
        while(j < n2)   {
            numbers.set(k, right.get(j));
            j++;
            k++;
        }
    }

    public static void sortElements(ArrayList<Integer> numbers, int start, int end)   {
        if(start < end) {
            var mid = start + (end - start) / 2;
            sortElements(numbers, start, mid);
            sortElements(numbers, mid + 1, end);
            merge(numbers, start, mid, end);
        }
    }


    public static void main(String[] args) {
        var numbers = new ArrayList<Integer>();
        new Random().ints(10, 1000, 9999).forEach(numbers::add);
        numbers.forEach(System.out::println);
        System.out.println();
        sortElements(numbers, 0, numbers.size() -1);
        numbers.forEach(System.out::println);
    }

}
