public class NumberPathExpanded implements Comparable<NumberPathExpanded>{
    private ChildedIntExpanded value;
    private long curInt;
    private double lowerBound;
    private double upperBound;
    private double exact;
    private int numRoots;
    private int addRoots;
    private boolean isFloor;
    public static Factorializer f = new Factorializer();

    public NumberPathExpanded(ChildedIntExpanded value){
        this.value = value;
        lowerBound = Math.log(value.getValue() - 1);
        upperBound = Math.log(value.getValue() + 1);
        exact = Math.log(value.getValue());
        numRoots = -1;
        curInt = 1;
    }

    public ChildedIntExpanded getValue(){
        return value;
    }

    public ChildedIntExpanded thisValue(){
        if (numRoots <= -1){
            return null;
        }
        return new ChildedIntExpanded(curInt, value.getValue(), numRoots, isFloor);
    }

    public int getNumRoots(){
        return numRoots;
    }

    public void nextValue(){
        long j = Math.max(curInt, lowerFact(lowerBound));
        long i = j;
        double factorial = 0;
        for (; (lowerBound >= factorial || i < j + 1); i++){
            factorial = getFact(i + 1);
            if (upperBound - factorial < 0.000001){
                lowerBound *= 2;
                upperBound *= 2;
                exact *= 2;
                addRoots++;
                i = Math.max(curInt, lowerFact(lowerBound));
                i--;
            }
        }
        if (numRoots <= -1){
            numRoots++;
        }
        numRoots += addRoots;
        addRoots = 0;
        curInt = i;
        isFloor = exact < factorial;
    }

    public boolean nextValueIfUnder(Long integer){
        long j = Math.max(curInt, lowerFact(lowerBound));
        long i = j;
        if (i > integer){
            return false;
        }
        double factorial = 0;
        for (; (lowerBound >= factorial || i < j + 1); i++){
            factorial = getFact(i + 1);
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

    public static double getFact(double i){
        double v = i * Math.log(i) - i + Math.log(i * (1 + 4 * i * (1 + 2 * i))) / 6. + Math.log(Math.PI) / 2;
        return v;
    }

    @Override
    public int compareTo(NumberPathExpanded o) {
        int comp = Long.compare(curInt, o.curInt);
        if (comp == 0)
            return this.value.getValue().compareTo(o.value.getValue());
        else
            return comp;
    }

    public String toString(){
        return value.getValue() + ": " + curInt;
    }
}
