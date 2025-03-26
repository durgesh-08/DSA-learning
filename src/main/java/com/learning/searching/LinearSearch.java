package com.learning.searching;

import java.util.ArrayList;
import java.util.List;

public class LinearSearch {

    public static int search(ArrayList<Integer> numbers, Integer elementToFind) {
        for (int i = 0; i < numbers.size(); i++) {
            if(numbers.get(i).equals(elementToFind))    {
                return i + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        var numbers = new ArrayList<>(List.of(10, 12, 85, 99, 11));
        var index = search(numbers, 12);
        System.out.println(index);
    }

}
