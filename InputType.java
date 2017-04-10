/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project4_hulbert_1181;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Admin
 */
public class InputType
{

    private int nInputSize;
    private int nBlockSize;
    private Queue<int[]> qArray = new LinkedList<>();
    private int[] nArray;

    public InputType(int nInput, int nBlock)
    {
        this.nInputSize = nInput;
        this.nBlockSize = nBlock;
        
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
        return nArray;
    }
    public int[] mergeThreads(){
        int nNum = qArray.size();
        ArrayList<Thread1> threadList = new ArrayList<>();
        while(qArray.size() > 1){
        for (int i = 0; i < ; i++)
        {
            //this is where i will poll two elements 
        }
            for (int i = 0; i < 10; i++)
            {
                //this is where i will offer things back into one array/queue
            }
        
        }
        return qArray.poll();
    }
    public void CreateSubArray(int[] nArray){
        int nBlockStart = 0;
        int arrayCount = 0;
        
        for (int i = 0; i < nArray.length; i++)
        {
            
            if(((i+1) % nBlockSize) == 0){
                int[] arr = new int[nBlockSize];
                arrayCount = 0;
            for (int j = nBlockStart; j <= i; j++)
                {
                    int temp = nArray[j];
                     arr[arrayCount]= nArray[j];
                    arr[arrayCount] = temp;
                    arrayCount++;
                     
                }
                qArray.offer(arr);
                nBlockStart+=i+1;
            }
            
            
        }
    }
    
    public void MakeThreads(){
        int qArraySize = qArray.size();
        for (int i = 0; i < qArraySize; i++)
        {
            
        }
    }

}
