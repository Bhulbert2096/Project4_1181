
package project4_hulbert_1181;


import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Admin
 */
public class SelectionSortThread extends SortingAlgorithms implements Runnable
{
    
    private int[] nArray;
    private int[] arr2;
    private Queue<int[]>qMergedArray = new LinkedList<>();
    private int nArraySize;
    private int counter;
   

    public SelectionSortThread(int[] array, int nInputSize, int nBlockSize, Queue<int[]> queue) {
        super(nInputSize, nBlockSize);
        this.nArray = array;
        this.qMergedArray = queue;
        this.nArraySize = nInputSize;
    }
    @Override
    public void run()
    {
        //this will allow me to still have access to the queue throughout every thread to thread N
         qMergedArray.offer(super.selectionSort(nArray));
         //now here I need a way to pass the Queue to something in sorting algorithms which will store th queue
         super.ObtainTheMergedQueueFromAThread(qMergedArray);
   
     
    }
}
