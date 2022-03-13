import java.util.HashMap;

public class NumberPathRegular implements Comparable<NumberPathRegular>{

    private ChildedInt value;
    private Integer curInt;
    private double lowerBound;
    private double upperBound;
    private double factorial;
    private double exact;
    private int numRoots;
    private boolean isFloor;
    private static HashMap<Integer, Double> logs = new HashMap<>();
    private static HashMap<Integer, Double> factorials = new HashMap<>();

    public NumberPathRegular(ChildedInt value){
        this.value = value;
        lowerBound = log(value.getValue() - 1);
        upperBound = log(value.getValue() + 1);
        exact = log(value.getValue());
        curInt = 1;
        factorial = 0;
        nextValue();
    }

    public double log(Integer i){
        if (logs.containsKey(i)){
            return logs.get(i);
        } else {
            double log = Math.log(i);
            logs.put(i, log);
            System.out.println(logs.size());
            return log;
        }
    }

    public ChildedInt getValue(){
        return value;
    }

    public Integer thisValue(){
        return curInt;
    }

    public ChildedInt nextValue(){
        int i = curInt + 1;
        int addRoots = 0;
        for (; lowerBound >= factorial || i == (curInt + 1); i++){
            if (factorials.containsKey(i)){
                factorial = factorials.get(i);
            } else {
                factorial += log(i);
                factorials.put(i, factorial);
            }
            if (upperBound - factorial < 0.000000001){
                lowerBound *= 2;
                upperBound *= 2;
                exact *= 2;
                addRoots++;
            }
        }
        i--;
        Integer temp = curInt;
        curInt = i;
        boolean isFloor = exact < factorial;
        int tempRoots = numRoots;
        numRoots += addRoots;
        boolean tempFloor = this.isFloor;
        this.isFloor = isFloor;
        return new ChildedInt(temp, value.getValue(), tempRoots, tempFloor);
    }

    @Override
    public int compareTo(NumberPathRegular o) {
        int comp = this.thisValue().compareTo(o.thisValue());
        if (comp == 0)
            return this.value.getValue().compareTo(o.value.getValue());
        else
            return comp;
    }

    public String toString(){
        return value.getValue() + ": " + curInt;
    }

}