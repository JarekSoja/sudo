import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cell implements Cloneable {

    private List<Integer> possible = new ArrayList<>();
    private int value = 0;
    private int row;
    private int column;

    Cell(int row, int column) {
        for (int i = 1; i < 10; i++) {
            possible.add(i);
        }
        this.row = row;
        this.column = column;
    }

    List<Integer> getPossible() {
        return possible;
    }

    void setPossible(List<Integer> possible) {
        this.possible = possible;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
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

    public int getColumn() {
        return column;
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
        clonedCell.possible.addAll(possible);
        return clonedCell;
    }


}
