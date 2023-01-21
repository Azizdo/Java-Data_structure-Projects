import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * class to test Heap class in 3 parts
 *.
 */
public class Test {

    /**
     * static method to fill the arrays with random values from 0 to 100
     *.
     */
    public static void fillArrayList(  ArrayList<Integer> arr , int numberOfValues ) {
        for (int i = 0; i < numberOfValues ; i++){
            Integer randomNumber = ThreadLocalRandom.current().nextInt(0, 100); // from https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
            arr.add(randomNumber);
        }
    }

    /**
     * method main to test and print in console
     *.
     */
    public static void main(String[] args) {

        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Integer> second = new ArrayList<>();
        ArrayList<Integer> third = new ArrayList<>();

        fillArrayList(first, 25);
        fillArrayList(second, 25);
        fillArrayList(third, 25);

        Heap maxHeapFirst = new Heap(first,true);
        Heap maxHeapSecond = new Heap(second,true);
        Heap maxHeapThird = new Heap(third,true);

        System.out.println("heaps followed by their sorted ArrayList:");
        maxHeapFirst.printHeap();
        System.out.println(maxHeapFirst.sort());
        System.out.println("#########################################################################################");
        maxHeapSecond.printHeap();
        System.out.println(maxHeapSecond.sort());
        System.out.println("##########################################################################################");
        maxHeapThird.printHeap();
        System.out.println(maxHeapThird.sort());

        System.out.println("#########################################################################################");
        ArrayList<Integer> tab = new ArrayList<>(Arrays.asList(1,1,1,1,2,3,4,5,5,6,7,8,9,9,9));
        Heap maxHeap = new Heap(tab);
        System.out.println(maxHeap.sort());
        System.out.println("element with 2th most frequency:");
        MostFrequency mostFrequency = new MostFrequency<>(maxHeap.sort(), 2) ;
        System.out.println(mostFrequency.ElementWithFrequencyK());

        System.out.println("#########################################################################################");
        ArrayList<String> stringTab = new ArrayList<>(Arrays.asList("aaa", "abc", "aab", "AaAb", "zsw"));
        Heap maxStringHeap = new Heap(stringTab);
        System.out.println(maxStringHeap.sort());
        System.out.println("element with 2th most frequency:");
        MostFrequency mostStringFrequency = new MostFrequency<>(maxStringHeap.sort(), 2) ;
        System.out.println(mostStringFrequency.ElementWithFrequencyK());
    }

}
