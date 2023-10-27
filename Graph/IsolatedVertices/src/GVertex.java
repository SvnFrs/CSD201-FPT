
/**
 *
 * @author Nguyen Chi Hau CE170023
 */
public class GVertex {

    private int value;
    private boolean isSelected = false;

    public GVertex(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return value + "";
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

}
