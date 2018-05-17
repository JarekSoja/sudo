import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cell implements Cloneable {

    private List<Integer> possible = new ArrayList<>();
    private int value = 0;
    private int row = 0;
    private int column = 0;


    public Cell(int row, int column) {
        for (int i = 1; i < 10; i++) {
            possible.add(i);
        }
        this.row = row;
        this.column = column;
    }

    public List<Integer> getPossible() {
        return possible;
    }

    public void setPossible(List<Integer> possible) {
        this.possible = possible;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    void removeOtherPossibleValues(int value) {
        List<Integer> possibleValuesAfterTesting = possible.stream()
                .filter(v -> v == value)
                .collect(Collectors.toList());
        this.possible.clear();
        this.possible.addAll(possibleValuesAfterTesting);
    }

    void removeValue(Integer value) {
        possible.remove(value);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return getRow() == cell.getRow() &&
                getColumn() == cell.getColumn();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRow(), getColumn());
    }

    @Override
    public Cell clone() throws CloneNotSupportedException {
        Cell clonedCell = (Cell) super.clone();
        clonedCell.possible = new ArrayList<>();
        for (Integer value : possible) {
            clonedCell.possible.add(value);
        }
        return clonedCell;
    }
}
