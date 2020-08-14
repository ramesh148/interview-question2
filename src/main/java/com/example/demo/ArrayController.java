package com.example.demo;

import java.util.*;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

@RestController
public class ArrayController {


    @GetMapping("/store")
    public String store(@RequestParam(name = "numbers", required = true) String numbers) {
        String strArray[] = new String(numbers.toString()).split(",");
        return strArray[0];
    }

    @GetMapping("/permutation")
    public String permutation(@RequestParam(name = "id", required = true) String id) {
        //Hard coded the array because Rest is state less using Session is against Rest principles.
        Integer numbers[] = {2,1,3,4,6,5,7};

        double random = Math.random() * (numbers.length) + 1;
        return permutationArray(numbers, random);
    }

    private String permutationArray(Integer[] numbers, double id){

        int size = numbers.length;
        ArrayList<Integer>  arrList= new ArrayList<Integer>();

        for(int i=0; i<size; i++) {
            arrList.add(numbers[i]);
        }

        ArrayList<Integer> permuteArray = permute(arrList, id);
        return BuildString(permuteArray);
    }

    private ArrayList<Integer> permute(ArrayList<Integer> arr, double k) {
        for (double i = k; i < arr.size(); i++) {
            Collections.swap(arr, (int)i, (int)k);
            permute(arr, k + 1);
        }
        return arr;
    }

    private String BuildString(ArrayList<Integer> arr){
        StringBuilder sbStr = new StringBuilder();
        for (Integer i : arr)
        {
            sbStr.append(i.toString());
            sbStr.append(",");
        }

        sbStr.deleteCharAt(sbStr.length()-1);
        return sbStr.toString();
    }
}
