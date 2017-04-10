/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project4_hulbert_1181;

/**
 *
 * @author Admin
 */
public class QuickSortThread extends SortingAlgorithms implements Runnable
{
    private int[] arr = super.getnArray();

    public QuickSortThread(int nInputSize, int nBlockSize) {
        super(nInputSize, nBlockSize);
    }

    @Override
    public void run()
    {
        super.sort(arr);
        //need to look at high and low pointers
        super.quicksort(0, 0, arr);
    }
    
}
