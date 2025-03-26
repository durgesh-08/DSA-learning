package com.learning.sorting;

import java.util.ArrayList;
import java.util.Random;

public class SelectionSort {


    public static void sortElements(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            int minIndex = i;
            for (int j = i+1; j < numbers.size(); j++)  {
                if(numbers.get(j) < numbers.get(minIndex))  {
                    minIndex = j;
                }
            }
            var tmp = numbers.get(minIndex);
            numbers.set(minIndex, numbers.get(i));
            numbers.set(i, tmp);
        }
    }


    public static void main(String[] args) {
        var numbers = new ArrayList<Integer>();
        new Random().ints(10, 1000, 99999).forEach(numbers::add);
        numbers.forEach(System.out::println);
        System.out.println();
        sortElements(numbers);
        numbers.forEach(System.out::println);
    }
}
