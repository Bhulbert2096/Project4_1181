package project4_hulbert_1181;

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
    

    public SortingAlgorithms(int nInputSize, int nBlockSize) {
        this.nInputSize = nInputSize;
        this.nBlockSize = nBlockSize;
    }

    

    //use merge sort to get the blocks of threads together
    //use one thread per subarray
    //have a for loop create how many threads you need based on number of subarrays you have
    //pivot left high moves first 
    public synchronized int[] selectionSort(int[] nArray) {
        System.out.println("Got here");

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
            
            System.out.println(Arrays.toString(nArray));
            notifyAll();
        }
        
    
        return nArray;
    }

    public void insertionSort(int[] nArray) {
        for (int i = 1; i < nArray.length; i++) {
            int temp;
            for (int k = i; k > 0; k--) {
                if (nArray[k] < nArray[k - 1]) {
                    temp = nArray[k];
                    nArray[k] = nArray[k - 1];
                    nArray[k - 1] = temp;

                }
                System.out.println(Arrays.toString(nArray));
            }

        }
    }

    public void bubbleSort(int[] nArray) {

        int temp = 0;

        for (int i = 0; i < nArray.length - 1; i++) {

            for (int j = 1; j < nArray.length - i; j++) {
                if (nArray[j - 1] > nArray[j]) {
                    temp = nArray[j - 1];
                    nArray[j - 1] = nArray[j];
                    nArray[j] = temp;
                }
                System.out.println(Arrays.toString(nArray));
            }

        }

    }

    public void quicksort(int nLow, int nHigh, int[] nArray) {
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

    public void swap(int[] arr, int low, int high) {
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
    
    public synchronized void SortChunks(int nSortingType) throws InterruptedException{
       // Then, “chunk” the array into the desired block size and pass each chunk to a thread that
        //sorts the chunk into ascending order using the algorithm chosen by the user
        //this needs to create a thread to sort one chunk then once the chunk
        //I know the queue contains each chunk so I need to take one chunk out and sort that chunk
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

    }

    public int[] CreateThreads(int nSortingType) throws InterruptedException {
        int nNum = qArray.size();
        int count = 0;
        while (qArray.size() > 1) {
            switch (nSortingType) {
                case 0:
                    //create the sub arrays then sort one thread per subarray to do the sorting threads to sort and then
                    //then have another thread to merge
                    
                    for (int i = 0; i <= nNum; i++) {
                        int[] Arraypoll1 = qArray.poll();
                        int[] Arraypoll2 = qArray.poll();
                        if (Arraypoll1 != null && Arraypoll2 != null) {

                           // Thread Thread1 = new Thread(new SelectionSortThread(Arraypoll1, Arraypoll2, nInputSize, nBlockSize,count));
                            //Thread1.start();
                            
                            for (int j = 0; j < i; j++) {
                             
                               // Thread1.join();
                                
                            }
                        }

                    }
                    break;
                case 1:
                    for (int i = 0; i < nNum / 2; i++) {

                        Thread Thread2 = new Thread(new BubbleSortThread(qArray.poll(), qArray.poll(), nInputSize, nBlockSize));
                        Thread2.start();
                        for (int j = 0; j < i; j++) {
                            Thread2.join();
                        }

                    }
                    break;
                case 2:
                    for (int i = 0; i < nNum / 2; i++) {

                        Thread Thread3 = new Thread(new InsertionSortThread(qArray.poll(), qArray.poll(), nInputSize, nBlockSize));
                        Thread3.start();
                        for (int j = 0; j < i; j++) {
                            Thread3.join();
                        }

                    }
                    break;
                case 3:
                    for (int i = 0; i < nNum / 2; i++) {

                        Thread Thread4 = new Thread(new QuickSortThread(qArray.poll(), qArray.poll(), nInputSize, nBlockSize));
                        Thread4.start();
                        for (int j = 0; j < i; j++) {
                            Thread4.join();
                        }

                    }
                    break;
            }

        }
        return qArray.poll();
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
