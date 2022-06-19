package Pieces;

public enum PieceColor {
    WHITE(0),
    BLACK(6);

    final int index;

    PieceColor(int index){
        this.index = index;
    }
    public int getIndex(){ return this.index;}
}
