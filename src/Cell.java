public class Cell {

    protected enum CellType{BLANK, NUMBER, BOMB}

    private boolean isRevealed = false;
    private CellType type;
    private int number = 0;

    public void setType(CellType type) {
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public void incrementNumber(){
        number = number + 1;
    }

    public void reveal(){
        isRevealed = true;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    @Override
    public String toString() {
        if(isRevealed){
            if(type == CellType.BLANK){
                return " ";
            } else if(type == CellType.NUMBER){
                return Integer.toString(number);
            } else {
                return "B";
            }
        }
        return "?";
    }
}
