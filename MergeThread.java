/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project4_hulbert_1181;


import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Admin
 */
public class MergeThread extends SortingAlgorithms implements Runnable{
    private Queue<int[]> queue = new LinkedList<>();
    private Queue<int[]> mergedList = new LinkedList<>();
    private int[] array1;
    private int[] array2;

    public MergeThread(int[] arr,int[] arr2,int nInputSize, int nBlockSize,Queue<int[]> queue) {
        super(nInputSize, nBlockSize);
        this.array1 = arr;
        this.array2 = arr2;
        this.mergedList = queue;
    }
     @Override
    public void run() {
        mergedList.offer(super.MergeArray(array1, array2));
        super.ObtainTheMergedQueueFromAThread(mergedList);
        
    }
    
}
