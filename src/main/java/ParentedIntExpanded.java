public class ParentedIntExpanded {

    private Long parent;
    private Long value;
    private int numRoots;
    private boolean isFloor;

    public ParentedIntExpanded(Long v, Long p, int numRoots, boolean isFloor){
        value = v;
        parent = p;
        this.numRoots = numRoots;
        this.isFloor = isFloor;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
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

    public String toString(){
        return (isFloor ? "floor" : "ceil") + "((" + parent + "!)^"+ "(1/" + (int) Math.pow(2, numRoots) + "))";
    }
}

