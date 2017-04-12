/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project4_hulbert_1181;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Admin
 */
public class BubbleSortThread extends SortingAlgorithms implements Runnable
{
    private int[] arr;
    private int[] arr2;
    private Queue<int[]>qMergedArray = new LinkedList<>();
    private int nArraySize;

    public BubbleSortThread(int[] array,int[] array2,int nInputSize, int nBlockSize) {
        super(nInputSize, nBlockSize);
        this.arr = array;
        this.arr2 = array2;
        this.nArraySize = nInputSize;
       
    }

    @Override
    public void run()
    {
           if(arr.length == 0 && arr2.length !=0){
            qMergedArray.offer(arr2);
            return;
        }
        if(arr2.length == 0 && arr.length != 0){
            qMergedArray.offer(arr);
            return;
        }
        if(arr.length != 0 && arr2.length != 0){
       int[] mergedArray = new int[arr.length + arr2.length];
        
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            mergedArray[i] = arr[i];
            count++;
        }
        for (int j = 0; j < arr2.length; j++) {
            mergedArray[count] = arr2[j];
            count++;
            }
         qMergedArray.offer(mergedArray);
         
        }
        System.out.println(Arrays.toString(qMergedArray.peek()));
    }
}
    