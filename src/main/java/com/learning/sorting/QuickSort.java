package com.learning.sorting;

import java.util.ArrayList;
import java.util.Random;

public class QuickSort {

    public static int partition(ArrayList<Integer> numbers, int low, int high)  {
        var mid = low + (high - low) / 2;
        swapElements(numbers, mid, high);
        var pivot = numbers.get(high);
        var i = low - 1;
        for (int j = low; j < high; j++) {
            if(numbers.get(j) < pivot)  {
                i++;
                swapElements(numbers, i, j);
            }
        }
        swapElements(numbers, i +1, high);
        return i + 1;
    }

    public static void swapElements(ArrayList<Integer> numbers, int i, int j)   {
        var tmp = numbers.get(i);
        numbers.set(i, numbers.get(j));
        numbers.set(j,  tmp);
    }

    public static void sortElements(ArrayList<Integer> numbers, int low, int high)  {
        if(low < high)  {
            int pi = partition(numbers, low,high);
            sortElements(numbers, low, pi);
            sortElements(numbers, pi +1, high);
        }
    }

    public static void main(String[] args) {
        var numbers = new ArrayList<Integer>();
        new Random().ints(5,100, 999).forEach(numbers::add);
        numbers.forEach(System.out::println);
        System.out.println();
        sortElements(numbers, 0, numbers.size() - 1);
        numbers.forEach(System.out::println);
    }
}
