package Board;

/**
 * Square_position class - represents position.
 * Contains column and rank.
 *
 */
public class SquarePosition {
    int column, rank;

    public SquarePosition(int column, int rank) {
        this.column = column;
        this.rank = rank;
    }
    /**
     * @return rank of the position
     */
    public int getRank() {
        return this.rank;
    }

    /**
     * @return this.column returns column of the position
     */
    public int getColumn() {
        return this.column;
    }

    /**Sets new current position.
     * @param column  column of the new position
     * @param rank    rank of the new position
     */
    public void set_position(int column, int rank) {
        this.column = column;
        this.rank = rank;
    }

}