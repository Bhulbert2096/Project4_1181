
package project4_hulbert_1181;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;



/**
 *
 * @author Admin
 */
public class SortingAlgorithms
{
    private int [] nNumberArray;
    private int nInputSize;
    private int nBlockSize;
    private Queue<int[]> qArray = new LinkedList<>();
    private int[] nArray;

    public SortingAlgorithms(int nInputSize, int nBlockSize) {
        this.nInputSize = nInputSize;
        this.nBlockSize = nBlockSize;
    }

    
    public int[] getnArray() {
        return nArray;
    }
    
    public void setnArray(int[] nArray) {
        this.nArray = nArray;
    }

    
    //use merge sort to get the blocks of threads together
    //use one thread per subarray
    //have a for loop create how many threads you need based on number of subarrays you have
    //pivot left high moves first 
    public synchronized int[] selectionSort(int[] nArray){
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
  
        }
        return nArray;
        
    }
    
    public void insertionSort(int[] nArray){
        for(int i = 1; i < nArray.length; i++){
             int temp;
            for (int k = i; k > 0; k--)
            {
                if(nArray[k] < nArray[k - 1]){
                    temp = nArray[k];
                    nArray[k] = nArray[k-1];
                    nArray[k-1] = temp;
                    
                }
                System.out.println(Arrays.toString(nArray));
            }
            
        }
    }
    
    public void bubbleSort(int[] nArray){
        
        int temp = 0;
        
            for (int i = 0; i < nArray.length-1; i++)
            {
                
                for (int j = 1; j < nArray.length - i; j++)
                {
                    if(nArray[j-1] > nArray[j]){
                        temp = nArray[j-1];
                        nArray[j-1] = nArray[j];
                        nArray[j] = temp;
                }
                 System.out.println(Arrays.toString(nArray));
            }
               
        }
        
    }
    public void sort(int [] nArray){
        if(nArray == null || nArray.length == 0){
            
        }
        int number;
        this.nNumberArray = nArray;
        number = nArray.length;
        quicksort(0,number - 1,nArray);
        
    }
    
    public void quicksort(int nLow, int nHigh, int[] nArray){
        int i = nLow;
        int k = nHigh;
        //this will give me the middle of the high and the low values
        int pivot = nArray[nLow+(nArray.length - nLow)/2];
        
        //while the low is greater than or equal too the high
        while(i <= k){
            //while the low is less than the pivot increment it
            while(nArray[i] < pivot){
                i++;
                
            }
            //this is while the high pointer is greater than the pivot decroment it
            while(nArray[k] > pivot){
                k--;
            }
            if(i <=k){
                int temp = nArray[i];
                nArray[i] = nArray[k];
                nArray[k] = temp;
                i++;
                k--;
            }
            if(nLow < k){
                quicksort(nLow, k, nArray);
            }
            if(i < nHigh){
                quicksort(i, nHigh, nArray);
            }
            
            System.out.println(Arrays.toString(nArray));
            
        }
        
    }
    
   
    public int[] AlreadySorted()
    {
        //this is an array already sorted in ascending order
        //I am passing in the array and the inputSize
        int nNum = nInputSize;
        int[] nArray = new int[nNum];

        for (int i = 0; i < nArray.length; i++)
        {
            nArray[i] = i;
        }
        this.nArray = nArray;
        return nArray;
    }

    //this will create an array in reverse order from nInputSize
    public int[] ReverseOrder()
    {
        int[] nArray = new int[nInputSize];
        for (int i = nInputSize; i > 0; i--)
        {
            nArray[(nArray.length - i)] = i;

        }
        this.nArray = nArray;
        return nArray;
    }

    //this will store a random array into nArray
    public int[] RandomArray()
    {
        int nRandom;
        int[] nArray = new int[nInputSize];
        for (int i = 0; i < nArray.length; i++)
        {
            nRandom = (int) (0 + Math.random() * nInputSize);
            nArray[i] = nRandom;
        }
        this.nArray = nArray;
        return nArray;
    }
    public int[] mergeThreads(int nSortingType) throws InterruptedException{
        int nNum = qArray.size();
         while(qArray.size() > 1){
            switch(nSortingType){
                case 0:
                    for (int i = 0; i < nNum/2; i++) {
                        
                      Thread Thread1 = new Thread(new SelectionSortThread(qArray.poll(),qArray.poll(),nInputSize,nBlockSize));
                        Thread1.start();
                     for (int j = 0; j < i; j++) {
                        Thread1.join();
                    }
                     //this will get the first item in the merge array queue
                        
                    }
                    
                case 1:
                     for (int i = 0; i < nNum/2; i++) {
                        
                      Thread Thread2 = new Thread(new BubbleSortThread(qArray.poll(),qArray.poll(),nInputSize,nBlockSize));
                        Thread2.start();
                     for (int j = 0; j < i; j++) {
                        Thread2.join();
                    }
                       
                    }
                case 2:
                   for (int i = 0; i < nNum/2; i++) {
                        
                      Thread Thread3 = new Thread(new InsertionSortThread(qArray.poll(),qArray.poll(),nInputSize,nBlockSize));
                        Thread3.start();
                     for (int j = 0; j < i; j++) {
                        Thread3.join();
                    }  
                     
                    }
                case 3:
                    for (int i = 0; i < nNum/2; i++) {
                        
                      Thread Thread4 = new Thread(new QuickSortThread(qArray.poll(),qArray.poll(),nInputSize,nBlockSize));
                        Thread4.start();
                     for (int j = 0; j < i; j++) {
                        Thread4.join();
                    }
                       
                    }
            }
        
        }
        return qArray.poll();
    }
    public void CreateSubArray(int[] nArray){
        
        for (int i = 0; i < nArray.length; i+=nBlockSize) {
            int[] chunk = Arrays.copyOfRange(nArray, i, Math.min(nArray.length, i + nBlockSize));
            qArray.offer(chunk);
        }
        
    }
    
    
    @Override
    public String toString()
    {
        return Arrays.toString(nNumberArray) ;
    }
    
    
}
