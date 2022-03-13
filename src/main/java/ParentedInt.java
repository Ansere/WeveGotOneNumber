public class ParentedInt {

    private Integer parent;
    private Integer value;
    private int numRoots;
    private boolean isFloor;

    public ParentedInt(Integer v, Integer p, int numRoots, boolean isFloor){
        value = v;
        parent = p;
        this.numRoots = numRoots;
        this.isFloor = isFloor;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
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

    public Integer getValue(){
        return value;
    }

    public String toString(){
        return (isFloor ? "floor" : "ceil") + "((" + parent + "!)^"+ "(1/" + (int) Math.pow(2, numRoots) + "))";
    }
}
