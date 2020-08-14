package com.example.demo.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/store")
public class PersistenceArrayController {
    @Autowired
    ArrayService service;

    @GetMapping("/store")
    public String store(@RequestParam(name = "numbers", required = true) String numbers) throws RecordNotFoundException {
        ArrayEntity entity = new ArrayEntity();
        entity.setStore(numbers);
        ArrayEntity updated = service.createOrUpdateStore(entity);

        return updated.getId().toString();
    }

    @GetMapping("/permutation")
    public String permutation(@RequestParam(name = "id", required = true) String id)
            throws RecordNotFoundException {
        ArrayEntity entity = service.getStoreById(Long.parseLong(id));
        String strNumbers = entity.getStore();
        String[] numbers = strNumbers.split(strNumbers);
        double random = Math.random() * (numbers.length) + 1;
        return permutationArray(numbers, random);
    }

    private String permutationArray(String[] numbers, double id){

        int size = numbers.length;
        ArrayList<Integer> arrList= new ArrayList<Integer>();

        for(int i=0; i<size; i++) {
            arrList.add(Integer.parseInt(numbers[i]));
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
