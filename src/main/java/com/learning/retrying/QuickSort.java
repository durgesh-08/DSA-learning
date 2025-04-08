package com.learning.retrying;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class QuickSort {

    public int partition(List<Integer> numbers, int low, int high) {
        var mid = low + (high - low) /2;
        swapElements(numbers, mid, high);
        var pivot = numbers.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if(numbers.get(j) < pivot)  {
                i++;
                swapElements(numbers, i , j);
            }
        }
        swapElements(numbers, i + 1, high);
        return i + 1;
    }

    public void swapElements(List<Integer> numbers, int i, int j)   {
        var temp = numbers.get(i);
        numbers.set(i, numbers.get(j));
        numbers.set(j, temp);
    }

    public void sortElements(List<Integer> numbers, int l, int r) {
        if(l < r)   {
            var pi = partition(numbers, l , r);
            sortElements(numbers, l, pi);
            sortElements(numbers, pi + 1, r);
        }

    }

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();
        var numbers = new ArrayList<Integer>();
        new Random().ints(10, 100,999).forEach(numbers::add);
        numbers.forEach(System.out::println);
        qs.sortElements(numbers, 0, numbers.size() - 1);
        System.out.println();
        numbers.forEach(System.out::println);
    }
}
