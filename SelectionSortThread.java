
package project4_hulbert_1181;
/**
 *
 * @author Admin
 */
public class SelectionSortThread extends SortingAlgorithms implements Runnable
{
    //private int[] arr = super.getnArray();
    private int[] arr;

    public SelectionSortThread(int[] array,int nInputSize, int nBlockSize) {
        super(nInputSize, nBlockSize);
        this.arr = array;
    }
    
    public SelectionSortThread(int nInputSize, int nBlockSize) {
        super(nInputSize, nBlockSize);
    }
    
    @Override
    public void run()
    {
        
        super.selectionSort(arr);
        
    }
    
}
