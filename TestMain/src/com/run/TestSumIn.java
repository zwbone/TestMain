package com.run;

import java.util.Arrays;

/**
 * Create with IntelliJ IDEA
 * Create By zhangwenbao
 * Date:{$Date}
 * Time:{TIME}
 */
public class TestSumIn {

    public static void main(String[] args){
        int[] arr = new int[]{17,2,7,9,11,15,21};
        int[] arrTemp = new int[]{17,2,7,9,11,15,21};
        int sum = 9;
        int[] indexArr = getIndexs(arrTemp,sum);
        printArry(indexArr);

    }
    public static int[] getIndexs(int[] arr,int sum){
        int[] rest = new int[2];
        printArry(arr);
        Arrays.sort(arr);
        printArry(arr);//{2,7,9,11,15,17,21} //排序之后用二分法查找
        int preMiddle = arr[arr.length/2 -1];
        int nextMiddle = arr[(arr.length/2)];
        int middleSum = preMiddle+nextMiddle;
        if(middleSum==sum){
            rest[0]=preMiddle;
            rest[1]=nextMiddle;
        }else if(middleSum>sum){
            int[] tem = Arrays.copyOfRange(arr,0,arr.length/2);
            getIndexs(tem,sum);
        }else if(middleSum<sum){
            int[] tem = Arrays.copyOf(arr,arr.length/2);
            getIndexs(tem,sum);
        }
        return rest;
    }
    public static void printArry(int[]array){

        for(int i=0;i<array.length;i++){
            System.out.print(" "+array[i]+", ");
        }
        System.out.println();
    }
}
