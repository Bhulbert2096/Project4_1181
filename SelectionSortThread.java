
package project4_hulbert_1181;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Admin
 */
public class SelectionSortThread extends SortingAlgorithms implements Runnable
{
    //private int[] arr = super.getnArray();
    private int[] nArray;
    private int[] arr2;
    private Queue<int[]>qMergedArray = new LinkedList<>();
    private int nArraySize;
    private int counter;
   
//    public SelectionSortThread(int[] array,int[] array2,int nInputSize, int nBlockSize,int count) {
//        super(nInputSize, nBlockSize);
//        this.arr = array;
//        this.arr2 = array2;
//        this.nArraySize = nInputSize;
//        this.counter = count;
//       
//    }
    public SelectionSortThread(int[] array, int nInputSize, int nBlockSize, Queue<int[]> queue) {
        super(nInputSize, nBlockSize);
        this.nArray = array;
        this.qMergedArray = queue;
        this.nArraySize = nInputSize;
    }
    
    public SelectionSortThread(int nInputSize, int nBlockSize) {
        super(nInputSize, nBlockSize);
    }

   
    @Override
    public void run()
    {
        //this will allow me to still have access to the queue throughout every thread to thread N
         qMergedArray.offer(super.selectionSort(nArray));
         //now here I need a way to pass the Queue to something in sorting algorithms which will store th queue
         super.ObtainTheMergedQueueFromAThread(qMergedArray);
         System.out.println(Arrays.toString(qMergedArray.peek()));
         
         
         
         
         
//        int[] mergedArray = new int[nArraySize];
//          
//        if (arr2 == null) {
//            for (int i = 0; i < arr.length; i++) {
//                mergedArray[counter] = arr[i];
//                counter++;
//            }
//        }
//
//        if (arr2 != null && arr != null) {
//            if (arr.length != 0 && arr2.length != 0) {
//
//                for (int i = 0; i < arr.length; i++) {
//                    mergedArray[counter] = arr[i];
//                    counter++;
//
//                }
//                //System.out.println(""+Arrays.toString(mergedArray));
//                for (int j = 0; j < arr2.length; j++) {
//                    mergedArray[counter] = arr2[j];
//                    counter++;
//                }
//                //System.out.println(Arrays.toString(mergedArray));
//                //qMergedArray.offer(mergedArray);
//
//            }
//            //System.out.println(Arrays.toString(mergedArray));
//
//        }
//        System.out.println(Arrays.toString(mergedArray));
    
     
    }
}
