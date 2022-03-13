import java.util.HashMap;

public class NumberPathFast implements Comparable<NumberPathFast>{

    private ChildedInt value;
    private Integer curInt;
    private double lowerBound;
    private double upperBound;
    private double exact;
    private int numRoots;
    private boolean isFloor;
    public static Factorializer f = new Factorializer();

    public NumberPathFast(ChildedInt value){
        this.value = value;
        lowerBound = Math.log(value.getValue() - 1)/2.;
        upperBound = Math.log(value.getValue() + 1)/2.;
        exact = Math.log(value.getValue())/2.;
        numRoots = -1;
        curInt = 1;
        nextValue();
    }

    public ChildedInt getValue(){
        return value;
    }

    public Integer thisValue(){
        return curInt;
    }

    public ChildedInt nextValue(){
        int addRoots = 1;
        lowerBound *= 2;
        upperBound *= 2;
        exact *= 2;
        int i = lowerFact(lowerBound);
        double factorial = f.goToNumber(i);
        for (; lowerBound >= factorial || i == lowerFact(lowerBound + 2); i++){
            factorial = f.goToNumber(i + 1);
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

    public static int lowerFact(double lowerBound){
        return (int) Math.floor(Math.pow(Math.E, LambertWFunction.W((lowerBound - Math.log(Math.sqrt(2 * Math.PI)))/Math.E) + 1) - 1/2.);
    }

    @Override
    public int compareTo(NumberPathFast o) {
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