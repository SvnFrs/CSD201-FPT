
/**
 *
 * @author Nguyen Chi Hau CE170023
 */
public class GEdge {

    private int value;
    private GVertex start, end;
    
    private boolean isSelected = false;
    private int x,y;//center location of the edge

    public GEdge(int value, GVertex start, GVertex end) {
        this.value = value;
        this.start = start;
        this.end = end;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public GVertex getStart() {
        return start;
    }

    public void setStart(GVertex start) {
        this.start = start;
    }

    public GVertex getEnd() {
        return end;
    }

    public void setEnd(GVertex end) {
        this.end = end;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void toggle(){
        this.isSelected = !this.isSelected;
    } 
}
