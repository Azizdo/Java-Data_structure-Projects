import java.util.ArrayList;

public class Heap<AnyType extends Comparable<AnyType>> {
    private final static boolean DEFAULT_iS_MAX = true;
    private ArrayList<AnyType> data = new ArrayList<>();
    private final boolean isMax;
    private int currentSize;

    /**
     * Constructer without parameter (it's makes a maxHeap).
     *
     */
    public Heap() {
        isMax = DEFAULT_iS_MAX;
        data = new ArrayList<>();
        data.add(null);
        currentSize = 0;
    }

    /**
     * Constructer with 1 parameter (it's makes a maxHeap).
     *
     * @param newData an ArrayList to heap.
     */
    public Heap(ArrayList<AnyType> newData) {
        isMax = DEFAULT_iS_MAX;
        currentSize = newData.size();
        data.add(null);

        data.addAll(newData);
        heapify();
    }

    /**
     * Constructer with 1 parameter.
     *
     * @param isMaxHeap boolean value to set a maxHeap (true) or not (false).
     */
    public Heap(boolean isMaxHeap) {
        isMax = isMaxHeap;
        data = new ArrayList<>();
        data.add(null);
        currentSize = 0;
    }

    /**
     * Constructer with 2 parameters.
     *
     * @param newData an ArrayList to heap.
     * @param isMaxHeap boolean value to set a maxHeap (true) or not (false).
     */
    public Heap(ArrayList<AnyType> newData, boolean isMaxHeap) {
        isMax = isMaxHeap;
        currentSize = newData.size();
        data.add(null);

        data.addAll(newData);

        heapify();
    }

    /**
     * public methods to get index of elements related to current index.
     *
     * @param index is the index of the element wanted.
     *
     */
    public int getIndexOfParent(int index) {
        return index / 2;
    }

    public int getIndexOfLeft(int index) {
        return (index * 2);
    }

    public int getIndexOfRight(int index) {
        return (index * 2) + 1;
    }

    /**
     * public methods to get element related to current index.
     *
     * @param index is the index of the element wanted.
     *
     * */
    public AnyType getParent(int index) {
        return data.get(index / 2);
    }

    public AnyType getLeft(int index) {
        return data.get(index * 2);
    }

    public AnyType getRight(int index) {
        return data.get((index * 2) + 1);
    }

    /**
     * Internal method to swap 2 elements in the heap.
     *
     * @param index1 is the index of the first element to swap with.
     * @param index2 is the index of the second element to swap with.
     */
    private void swap(int index1, int index2) {
        AnyType tmp = data.get(index1);
        data.set(index1, data.get(index2));
        data.set(index2, tmp);
    }

    /**
      * Establish heap order property from an arbitrary
      * arrangement of items. Runs in linear time.
     */
    private void heapify() {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }

    /**
     * Internal method to percolate down in the heap.
     *
     * @param hole the index at which the percolate begins.
     */
    private void
    percolateDown(int hole) {
        int child;
        AnyType tmp = data.get(hole);

        if (!isMax) {
            for (; hole * 2 <= currentSize; hole = child) {
                child = hole * 2;
                if (child != currentSize &&
                        data.get(child + 1).compareTo(data.get(child)) < 0)
                    child++;
                if (data.get(child).compareTo(tmp) < 0)
                    data.set(hole, data.get(child));
                else
                    break;
            }
            data.set(hole, tmp);
        } else {
            for (; hole * 2 <= currentSize; hole = child) {
                child = hole * 2;
                if (child != currentSize &&
                        data.get(child + 1).compareTo(data.get(child)) > 0)
                    child++;
                if (data.get(child).compareTo(tmp) > 0)
                    data.set(hole, data.get(child));
                else
                    break;
            }
            data.set(hole, tmp);
        }
    }

    /**
     * Internal method to percolate up in the heap.
     *
     * @param value is the value of the element to perlocate up.
     */
    private void percolateUp(AnyType value) {
        
        int hole = ++currentSize;

        if (!isMax) {
            for (; hole > 1 && value.compareTo(data.get(hole / 2)) < 0; hole /= 2)
                data.set(hole, data.get(hole / 2));
        } else {

            for (; hole > 1 && value.compareTo(data.get(hole / 2)) > 0; hole /= 2)
                data.set(hole, data.get(hole / 2));
        }
        data.set(hole, value);
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     *
     * @param value the item to insert.
     */
    public void insert(AnyType value) {
        data.add(null); //mettre un espace de plus à la fin pour l'insertion
        // Percolate up
        percolateUp(value);
    }


    /**
     * Remove the smallest item from the priority queue.
     * return the mallest item, or throw UnderflowException, if empty.
     */
   public AnyType deleteRoot(){
        if (isEmpty())
            throw new UnderflowException();

        AnyType item;

        if (!isMax){
            item = findMin();
        }
        else {
            item = findMax();
        }

        data.set(1, data.get(currentSize--));
        percolateDown(1);

        return item;
    }

    /**
     * public method to sort in the heap.
     * return sorted ArrayList.
     */
    public ArrayList<AnyType> sort() {
        ArrayList<AnyType> tmpArr = new ArrayList<>(this.data);
        int tempCurrentSize = currentSize;
        ArrayList<AnyType> orderArr = new ArrayList<>();
        for (int i = 1; i <= tempCurrentSize; i++) {
            orderArr.add(data.get(1));
            deleteRoot(); //diminue currentSize
        }

        data = new ArrayList<>(tmpArr);
        currentSize = tempCurrentSize;

        return orderArr;
    }

    /**
     * Internal method to find the minimal element.
     *.
     * return the maximum element
     */
    private AnyType findMax( ) {
        AnyType max = data.get(1);
        for( int i = currentSize/2 + 1; i <= currentSize; i++ ) {
            if ( data.get(i).compareTo (max) > 0 )
                max = data.get(i);
        }
        return max;
    }

    /**
     * Internal method to find the maximal element.
     *.
     * return minimum element
     */
    private AnyType findMin( ) {
        AnyType min = data.get(1);
        for( int i = currentSize/2 + 1; i <= currentSize; i++ ) {
            if ( data.get(i).compareTo (min) < 0 )
                min = data.get(i);
        }
        return min;
    }

    /**
     * public method to see if the heap is empty.
     *.
     * return a bool ifEmpty or not
     */
    public boolean isEmpty(){
        return currentSize == 0;
    }

    /**
     * public method to make the heap empty.
     *.
     */
    public void makeEmpty(){
        data.clear();
        data = new ArrayList<>();
        currentSize = 0;
    }

    /**
     * public method to sort in the heap.
     * return sorted ArrayList.
     */
    public void printHeap() {
        for (int i = 0; i <= currentSize; i++) {
            System.out.printf("%d : ", i);
            System.out.println(data.get(i));
        }
    }
}
