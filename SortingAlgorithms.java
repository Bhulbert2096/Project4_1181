
package project4_hulbert_1181;

import java.util.Arrays;
import java.util.Queue;



/**
 *
 * @author Admin
 */
public class SortingAlgorithms
{
    private int [] nNumberArray;
    private int nNum;
    //use merge sort to get the blocks of threads together
    //use one thread per subarray
    //have a for loop create how many threads you need based on number of subarrays you have
    //pivot left high moves first 
    public void selectionSort(int[] nArray){
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
        this.nNumberArray = nArray;
        nNum = nArray.length;
        quicksort(0,nArray.length - 1,nArray);
        
    }
    
    public void quicksort(int nLow, int nHigh, int[] nArray){
        int i = nLow;
        int k = nHigh;
        //this will give me the middle of the high and the low values
        int pivot = nArray[nLow+(nHigh - nLow)/2];
        
        //while the low is greater than or equal too the high
        while(i <= k){
            //while the low is less than the pivot increment it
            while(nArray[nLow] < pivot){
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
    
   
    

    @Override
    public String toString()
    {
        return Arrays.toString(nNumberArray) ;
    }
    
    
}
