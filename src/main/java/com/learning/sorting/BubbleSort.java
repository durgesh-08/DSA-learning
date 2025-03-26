package com.learning.sorting;

import java.util.ArrayList;
import java.util.Random;

public class BubbleSort {

    public static void sortElements(ArrayList<Integer> numbers)  {
        for (int i = 0; i < numbers.size(); i++) {
            for (int k = 0; k < numbers.size() - i - 1; k++)    {
                if(numbers.get(k) > numbers.get(k+1))   {
                    var element = numbers.get(k + 1);
                    numbers.set(k+1, numbers.get(k));
                    numbers.set(k, element);
                }
            }
        }
    }

    public static void main(String[] args) {
        var numbers = new ArrayList<Integer>();
        var random = new Random();
        random.ints(10, 1000, 99999).forEach(numbers::add);
        numbers.forEach(System.out::println);
        sortElements(numbers);
        System.out.println();
        numbers.forEach(System.out::println);
    }

}
