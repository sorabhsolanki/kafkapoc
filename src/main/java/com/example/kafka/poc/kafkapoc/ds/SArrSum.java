package com.example.kafka.poc.kafkapoc.ds;

import java.util.HashMap;
import java.util.Map;

public class SArrSum {

    public static void main(String[] args) {
        int[] arr = {10,2,-2,-20,10};

        subArraySum(arr, 10);
    }

    private static void subArraySum(int[] arr, int sum) {
        int currSum = 0;
        int start = 0;
        int end = -1;

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++){

            currSum += arr[i];
            if(currSum - sum == 0){
                start = 0;
                end = i;
                break;
            }

            if(map.containsKey(currSum - sum)){
                start = map.get(currSum - sum)  + 1;
                end = i;
                break;
            }

            map.put(currSum, i);
        }
    }
}
