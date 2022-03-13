public class NumberPathNew implements Comparable<NumberPathNew>{
    private ChildedInt value;
    private Integer curInt;
    private double lowerBound;
    private double upperBound;
    private double exact;
    private int numRoots;
    private int addRoots;
    private boolean isFloor;
    public static Factorializer f = new Factorializer();

    public NumberPathNew(ChildedInt value){
        this.value = value;
        lowerBound = Math.log(value.getValue() - 1);
        upperBound = Math.log(value.getValue() + 1);
        exact = Math.log(value.getValue());
        numRoots = -1;
        curInt = 1;
    }

    public ChildedInt getValue(){
        return value;
    }

    public ChildedInt thisValue(){
        if (numRoots <= -1){
            return null;
        }
        return new ChildedInt(curInt, value.getValue(), numRoots, isFloor);
    }

    public int getNumRoots(){
        return numRoots;
    }

    public boolean nextValueIfUnder(Integer integer){
        int j = Math.max(curInt, lowerFact(lowerBound));
        int i = j;
        if (i > integer){
            return false;
        }
        double factorial = 0;
        for (; (lowerBound >= factorial || i <= j + 1); i++){
            factorial = f.goToNumber(i + 1);
            if (i > integer){
                return false;
            }
            if (upperBound - factorial < 0.000001){
                lowerBound *= 2;
                upperBound *= 2;
                exact *= 2;
                addRoots++;
                i = Math.max(curInt, lowerFact(lowerBound));
                i--;
            }
        }
        i--;
        if (numRoots <= -1){
            numRoots++;
        }
        numRoots += addRoots;
        addRoots = 0;
        curInt = i;
        isFloor = exact < factorial;
        return true;
    }

    public static int lowerFact(double lowerBound){
        return (int) Math.floor(Math.pow(Math.E, LambertWFunction.W((lowerBound - Math.log(Math.sqrt(2 * Math.PI)))/Math.E) + 1) - 1/2.);
    }

    @Override
    public int compareTo(NumberPathNew o) {
        int comp = this.curInt.compareTo(o.curInt);
        if (comp == 0)
            return this.value.getValue().compareTo(o.value.getValue());
        else
            return comp;
    }

    public String toString(){
        return value.getValue() + ": " + curInt;
    }
}
