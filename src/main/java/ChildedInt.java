public class ChildedInt {

    private Integer child;
    private Integer value;
    private int numRoots;
    private boolean isFloor;

    public ChildedInt(Integer v, Integer c, int numRoots, boolean isFloor){
        value = v;
        child = c;
        this.numRoots = numRoots;
        this.isFloor = isFloor;
    }

    public Integer getChild() {
        return child;
    }

    public void setChild(Integer child) {
        this.child = child;
    }

    public int getNumRoots() {
        return numRoots;
    }

    public void setNumRoots(int numRoots) {
        this.numRoots = numRoots;
    }

    public boolean isFloor() {
        return isFloor;
    }

    public void setFloor(boolean floor) {
        isFloor = floor;
    }

    public Integer getValue(){
        return value;
    }

    public ParentedInt toParentedInt(){
        return new ParentedInt(child, value, numRoots, isFloor);
    }

    public String toString(){
        return (isFloor ? "floor" : "ceil") + "((" + value + "!)^"+ "(1/" + (int) Math.pow(2, numRoots) + "))";
    }
}
