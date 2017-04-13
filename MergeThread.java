/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project4_hulbert_1181;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Admin
 */
public class MergeThread implements Runnable{
    private Queue<int[]> queue = new LinkedList<>();
    private ArrayList<Integer> mergedList = new ArrayList<>();

    public MergeThread(Queue<int[]> q) {
        this.queue = q;
    }
    
    @Override
    public void run() {
        int[] array1 = queue.poll();
        int[] array2 = queue.poll();
           int size = array1.length + array2.length;
        //this is the array that we will be merging the halves into
        //int[] mergedList = new int[size];
        //first element to consider
        int nFirst = 0;
        //second element to consider in the array
        int nSecond = 0;
        //the next open spot left in the merge array
        int j = 0;
        while(nFirst < array1.length && nSecond < array2.length){
            if(array1[nFirst] < array2[nSecond]){
                mergedList.set(j, array1[nFirst]);
                nFirst++;
            }
            else{
                mergedList.set(j,array2[nSecond]);
                 nSecond++;
            }
            j++;
        }
        
        System.arraycopy(array1,nFirst,mergedList.toArray(),j,array1.length - nFirst);
        System.arraycopy(array2,nSecond,mergedList.toArray(),j,array2.length - nSecond);
        System.out.println(mergedList.toString());
        
    }
    
}
