public class ChildedIntExpanded {

    private Long child;
    private Long value;
    private int numRoots;
    private boolean isFloor;

    public ChildedIntExpanded(Long v, Long c, int numRoots, boolean isFloor){
        value = v;
        child = c;
        this.numRoots = numRoots;
        this.isFloor = isFloor;
    }

    public Long getChild() {
        return child;
    }

    public void setChild(Long child) {
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

    public Long getValue(){
        return value;
    }

    public ParentedIntExpanded toParentedIntExpanded(){
        return new ParentedIntExpanded(child, value, numRoots, isFloor);
    }

    public String toString(){
        return (isFloor ? "floor" : "ceil") + "((" + value + "!)^"+ "(1/" + (int) Math.pow(2, numRoots) + "))";
    }
}
