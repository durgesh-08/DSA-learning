package com.learning.searching;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    
    public static int search(ArrayList<Integer> numbers, Integer elementToFind) {
        int low = 0, high = numbers.size() - 1;
        while(low <= high)  {
            var mid = low + (high - low) / 2;
            if(numbers.get(mid).equals(elementToFind))  {
                return mid + 1;
            }
            if(numbers.get(mid) > elementToFind)    {
                high = mid - 1;
            }   else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        var numbers = new ArrayList<>(List.of(10,29, 89, 97, 99));
        System.out.println(search(numbers, 99));
    }
    
}
