import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * public class to find the element with the k(th) most frequencu.
 *.
 */
public class MostFrequency<AnyType> {
    private final int kFrequ;
    private final ArrayList<AnyType> data;

    /**
     * Constructer with 2 parameters
     *
     * @param sortedArraylist  is a sorted Heap.
     * @param  k, it's the k(th) most frequency wanted.
     *
     */
    public MostFrequency(ArrayList<AnyType> sortedArraylist, int k){
        kFrequ = k;
        data = new ArrayList<>(sortedArraylist);
    }

    /**
     * public method find the element with the k(th) most frequencu.
     *.
     * uses kFrequ, it's the k(th) most frequency wanted.
     * return the element with the k(th) most frequency
     */
    public AnyType ElementWithFrequencyK(){
        int start = 0;
        if (kFrequ > data.size() || kFrequ < 1)
            return null;

        Map<AnyType, Integer> elementFrequency = new HashMap<>();
        ArrayList<Integer> frequency = new ArrayList<>();

        int aFrequency;
        for (AnyType elem : data) {
            aFrequency = 0;
            if (elem.getClass().getName().equals("java.lang.String")){
                for(int i = 0; i < String.valueOf(elem).length(); i++) {
                    if(String.valueOf(elem).charAt(i) == 'a')
                        aFrequency++;
                }
                elementFrequency.put(elem, aFrequency);
                frequency.add(aFrequency);
            }else {
                elementFrequency.put(elem, Collections.frequency(data, elem));
                frequency.add(Collections.frequency(data, elem));
            }
        }

        ArrayList<Integer> orderedFrequency = new Heap<>(frequency).sort();

        int KfrequToUse = 0;

        while(start < kFrequ){
            KfrequToUse = orderedFrequency.get(0);
            while (orderedFrequency.get(0) == KfrequToUse){
                orderedFrequency.remove(0);
            }
            start++;
        }

        AnyType elementWithKfreq = null;
        for (AnyType key : elementFrequency.keySet()) {
            if (elementFrequency.get(key) == KfrequToUse)
                elementWithKfreq = key;
        }

        return elementWithKfreq;
    }
}
