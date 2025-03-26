package com.learning.sorting;

import java.util.ArrayList;
import java.util.Random;

public class InsertionSort {

    public static void sortElements(ArrayList<Integer> numbers) {
        for (int i = 1; i < numbers.size(); i++) {
            var element = numbers.get(i);
            var j = i -1;
            while(j >= 0 && element < numbers.get(j))   {
                numbers.set(j +1, numbers.get(j));
                j--;
            }
            numbers.set(j + 1 , element);
        }
    }

    public static void main(String[] args) {
        var numbers = new ArrayList<Integer>();
        var random = new Random();
        random.ints(10, 1000, 99999).forEach(numbers::add);
        numbers.forEach(System.out::println);
        System.out.println();
        sortElements(numbers);
        numbers.forEach(System.out::println);
    }
}
