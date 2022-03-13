import java.math.BigInteger;
import java.util.HashMap;

public class NumberPathFaster implements Comparable<NumberPathFaster>{

    private ChildedInt value;
    private Integer curInt;
    private double lowerBound;
    private double upperBound;
    private double exact;
    private int numRoots;
    private boolean isFloor;
    private static HashMap<Integer, Double> logs = new HashMap<>();
    //private static HashMap<Integer, Double> factorials = new HashMap<>();

    public NumberPathFaster(ChildedInt value){
        this.value = value;
        lowerBound = log(value.getValue() - 1)/2;
        upperBound = log(value.getValue() + 1)/2;
        exact = log(value.getValue())/2;
        curInt = 1;
        numRoots = -1;
        nextValue();
    }

    public double log(Integer i){
        if (logs.containsKey(i)){
            return logs.get(i);
        } else {
            double log = Math.log(i);
            logs.put(i, log);
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
        int addRoots = 1;
        upperBound *= 2;
        lowerBound *= 2;
        exact *= 2;
        String[] boundedFactorial = getBoundedFactorial(upperBound, lowerBound, exact);
        while (boundedFactorial == null){
            upperBound *= 2;
            lowerBound *= 2;
            exact *= 2;
            addRoots++;
            boundedFactorial = getBoundedFactorial(upperBound, lowerBound, exact);
        }
        Integer temp = curInt;
        curInt = Integer.valueOf(boundedFactorial[0]);
        boolean isFloor = Boolean.parseBoolean(boundedFactorial[1]);
        int tempRoots = numRoots;
        numRoots += addRoots;
        boolean tempFloor = this.isFloor;
        this.isFloor = isFloor;
        return new ChildedInt(temp, value.getValue(), tempRoots, tempFloor);
    }



    public static String[] getBoundedFactorial(double upperBound, double lowerBound, double exact){
        double invFact = (Math.pow(Math.E, LambertWFunction.W((upperBound - Math.log(Math.sqrt(2 * Math.PI)))/Math.E) + 1) - 1/2.);
        double invFactl = (Math.pow(Math.E, LambertWFunction.W((lowerBound - Math.log(Math.sqrt(2 * Math.PI)))/Math.E) + 1) - 1/2.);
        double invFacte = (Math.pow(Math.E, LambertWFunction.W((exact - Math.log(Math.sqrt(2 * Math.PI)))/Math.E) + 1) - 1/2.);
        if (Math.floor(invFact) >= Math.ceil(invFactl)){
            boolean isFloor = invFacte < Math.ceil(invFactl);
            return new String[]{(int) Math.ceil(invFactl) + "", isFloor + ""};
        } else {
            return null;
        }
    }

    @Override
    public int compareTo(NumberPathFaster o) {
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
