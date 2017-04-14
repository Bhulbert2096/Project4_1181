package project4_hulbert_1181;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Admin
 */
public class SortingAlgorithms {

    private int[] nNumberArray;
    private int nInputSize;
    private int nBlockSize;
    private Queue<int[]> qArray = new LinkedList<>();
    private int[] nArray;
    private Queue<int[]> qSortedArray = new LinkedList<>();
    private ArrayList<int[]> MergedArray = new ArrayList<>();
    

    public SortingAlgorithms(int nInputSize, int nBlockSize) {
        this.nInputSize = nInputSize;
        this.nBlockSize = nBlockSize;
    }
    public synchronized int[] selectionSort(int[] nArray) {
       

        int nMin = 0;
        int temp = 0;
        for (int i = 0; i < nArray.length-1; i++)
        {
            nMin = i;
            for (int j = i; j < nArray.length; j++)
            {
                if (nArray[j] < nArray[nMin])
                {
                    nMin = j;
                }
            temp = nArray[nMin];
            nArray[nMin] = nArray[i];
            nArray[i] = temp;
            
            }
            
            
            notifyAll();
        }
        
    
        return nArray;
    }

    public void ObtainTheMergedQueueFromAThread(Queue<int[]> mergedQueue){
        qSortedArray = mergedQueue;
       
    }
    public synchronized int[] insertionSort(int[] nArray) {
        for (int i = 1; i < nArray.length; i++) {
            int temp;
            for (int k = i; k > 0; k--) {
                if (nArray[k] < nArray[k - 1]) {
                    temp = nArray[k];
                    nArray[k] = nArray[k - 1];
                    nArray[k - 1] = temp;

                }
            }

        }
        return nArray;
    }

    public synchronized int[] bubbleSort(int[] nArray) {

        int temp = 0;

        for (int i = 0; i < nArray.length - 1; i++) {

            for (int j = 1; j < nArray.length - i; j++) {
                if (nArray[j - 1] > nArray[j]) {
                    temp = nArray[j - 1];
                    nArray[j - 1] = nArray[j];
                    nArray[j] = temp;
                }
            }

        }
        return nArray;
    }

    public synchronized void quicksort(int nLow, int nHigh, int[] nArray) {
        int i = nLow;
        int k = nHigh;
        //this will give me the middle of the high and the low values
        int pivot = nArray[(nLow + nHigh) / 2];
        if (nArray.length == 0 || nArray == null) {
            return;
        }
        //while the low is greater than or equal too the high
        while (i <= k) {
            //while the low is less than the pivot increment it
            while (nArray[i] < pivot) {
                i++;

            }
            //this is while the high pointer is greater than the pivot decroment it
            while (nArray[k] > pivot) {
                k--;
            }
            if (i <= k) {
                swap(nArray, i, k);
                i++;
                k--;
            }
        }
        if (nLow < k) {
            quicksort(nLow, k, nArray);
        }
        if (i < nHigh) {
            quicksort(i, nHigh, nArray);
        }

    }

    public synchronized void swap(int[] arr, int low, int high) {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
    }

    public int[] AlreadySorted() {
        //this is an array already sorted in ascending order
        //I am passing in the array and the inputSize
        int nNum = nInputSize;
        int[] nArray = new int[nNum];

        for (int i = 0; i < nArray.length; i++) {
            nArray[i] = i;
        }
        this.nArray = nArray;
        return nArray;
    }

    //this will create an array in reverse order from nInputSize
    public int[] ReverseOrder() {
        int[] nArray = new int[nInputSize];
        for (int i = nInputSize; i > 0; i--) {
            nArray[(nArray.length - i)] = i;

        }
        this.nArray = nArray;
        return nArray;
    }

    //this will store a random array into nArray
    public int[] RandomArray() {
        int nRandom;
        int[] nArray = new int[nInputSize];
        for (int i = 0; i < nArray.length; i++) {
            nRandom = (int) (0 + Math.random() * nInputSize);
            nArray[i] = nRandom;
        }
        this.nArray = nArray;
        return nArray;
    }
    
    public synchronized void SortChunks2(int nSortingType) throws InterruptedException{
       
      
        int nQueueSize = qArray.size();
        
        while(qArray.size() > 0){
            switch(nSortingType){
                case 0:
                   Thread thread1 = new Thread(new SelectionSortThread( qArray.poll(), nInputSize, nBlockSize, qSortedArray));
                   thread1.start();
                   thread1.join();
                    break;
            }
        }
         System.out.println("Got here:"+Arrays.toString(qSortedArray.peek()));
    }
    
   
    public synchronized int[] MergeArray(int[] array1, int[] array2){
        int size = array1.length + array2.length;
        //this is the array that we will be merging the halves into
        int[] mergedArray = new int[size];
        //first element to consider
        int nFirst = 0;
        //second element to consider in the array
        int nSecond = 0;
        //the next open spot left in the merge array
        int j = 0;
        
        while(nFirst < array1.length && nSecond < array2.length){
            if(array1[nFirst] < array2[nSecond]){
                mergedArray[j] = array1[nFirst];
                nFirst++;
            }
            else{
                mergedArray[j] = array2[nSecond];
                nSecond++;
            }
            j++;
            }
        
       
        System.arraycopy(array1,nFirst,mergedArray,j,array1.length - nFirst);
        
        System.arraycopy(array2,nSecond,mergedArray,j,array2.length - nSecond);
        return mergedArray;
    }
    public synchronized void MergeThreadQueue(int nSortingType) throws InterruptedException{
        while(qSortedArray.size() !=1){
            Thread thread1 = new Thread(new MergeThread(qSortedArray.poll(),qSortedArray.poll(), nInputSize, nBlockSize,qSortedArray));
            thread1.start();
            thread1.join();    
            }
        System.out.println("FINAL:"+Arrays.toString(qSortedArray.poll()));
    }

    public synchronized void SortChunks(int nSortingType) throws InterruptedException {
        int nNum = qArray.size();
        int count = 0;
        while (qArray.size() > 1) {
            switch (nSortingType) {
                case 0:
                   for (int i = 0; i < nNum; i++) {
                            Thread Thread1 = new Thread(new SelectionSortThread(qArray.poll(), nInputSize, nBlockSize,qSortedArray));
                            Thread1.start();
                            for (int j = 0; j < i; j++) {
                                Thread1.join();
                            }
                        }
                    break;
                case 1:
                    for (int i = 0; i < nNum; i++) {
                            Thread Thread1 = new Thread(new BubbleSortThread(qArray.poll(), nInputSize, nBlockSize,qSortedArray));
                            Thread1.start();
                            for (int j = 0; j < i; j++) {
                            Thread1.join();
                            }
                        }
                    break;
                case 2:
                    for (int i = 0; i < nNum; i++) {
                        Thread Thread3 = new Thread(new InsertionSortThread(qArray.poll(), nInputSize, nBlockSize,qSortedArray));
                        Thread3.start();
                        for (int j = 0; j < i; j++) {
                            Thread3.join();
                        }

                    }
                    break;
                case 3:
                    for (int i = 0; i < nNum; i++) {
                        Thread Thread4 = new Thread(new QuickSortThread(qArray.poll(), nInputSize, nBlockSize,qSortedArray));
                        Thread4.start();
                        for (int j = 0; j < i; j++) {
                            Thread4.join();
                        }

                    }
                    break;
            }

        }
    }

    public void CreateSubArray(int[] nArray) {

        for (int i = 0; i < nArray.length; i += nBlockSize) {
            int[] chunk = Arrays.copyOfRange(nArray, i, Math.min(nArray.length, i + nBlockSize));

            qArray.offer(chunk);
        }

    }

    public void Merge(int[] arr, int[] arr2,int counter){
        int[] mergedArray = new int[nInputSize];
        if (arr2 == null) {
            for (int i = 0; i < arr.length; i++) {
                mergedArray[counter] = arr[i];
                counter++;
            }
        }

        if (arr2 != null && arr != null) {
            if (arr.length != 0 && arr2.length != 0) {

                for (int i = 0; i < arr.length; i++) {
                    mergedArray[counter] = arr[i];
                    counter++;

                }
                //System.out.println(""+Arrays.toString(mergedArray));
                for (int j = 0; j < arr2.length; j++) {
                    mergedArray[counter] = arr2[j];
                    counter++;
                }
                //System.out.println(Arrays.toString(mergedArray));
                //qMergedArray.offer(mergedArray);

            }
            //System.out.println(Arrays.toString(mergedArray));

        }
        System.out.println(Arrays.toString(mergedArray));
    }
    @Override
    public String toString() {
        return Arrays.toString(nNumberArray);
    }

}
