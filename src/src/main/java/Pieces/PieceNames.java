package Pieces;

public enum PieceNames {
    KING(0),KNIGHT(3),ROOK(4),QUEEN(1),PAWN(5),BISHOP(2);
    final int index;

    PieceNames(int index){
        this.index = index;
    }
    public int getIndex(){ return this.index;}
}
