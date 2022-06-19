package Pieces;

import Board.Board;
import Board.SquarePosition;

import java.util.ArrayList;

public class Knight extends Piece {
    final PieceNames piece_name = PieceNames.KNIGHT;
    public Knight(PieceColor color, SquarePosition position) {
        super(color, position);
    }

    @Override
    public boolean move(SquarePosition pos_move, Board board) {

        ArrayList<SquarePosition> possible = new ArrayList<>();

        if (position.getRank() <= 5 && position.getColumn() != 7){
            possible.add(new SquarePosition((position.getColumn() + 1), position.getRank() + 2));
        }
        if (position.getRank() <= 5 && position.getColumn() != 0){
            possible.add(new SquarePosition((position.getColumn() - 1), position.getRank() + 2));
        }
        if (position.getRank() >= 2 && position.getColumn() != 0){
            possible.add(new SquarePosition((position.getColumn() - 1), position.getRank() - 2));
        }
        if (position.getRank() >= 2 && position.getColumn() != 7){
            possible.add(new SquarePosition((position.getColumn() + 1), position.getRank() - 2));
        }

        if (position.getColumn() <= 5 && position.getRank() != 7){
            possible.add(new SquarePosition((position.getColumn() + 2), position.getRank() + 1));
        }
        if (position.getColumn() <= 5 && position.getRank() != 0){
            possible.add(new SquarePosition((position.getColumn() + 2), position.getRank() - 1));
        }
        if (position.getColumn() >= 2 && position.getRank() != 7){
            possible.add(new SquarePosition((position.getColumn() - 2), position.getRank() + 1));
        }
        if (position.getColumn() >= 2 && position.getRank() != 0){
            possible.add(new SquarePosition((position.getColumn() - 2), position.getRank() - 1));
        }
        for (SquarePosition square_position : possible) {
            if (square_position.getRank() == pos_move.getRank() && square_position.getColumn() == pos_move.getColumn()) {
                return true;
            }
        }
        return false;
    }

    public PieceColor get_Piece_color(){return this.color;}

    @Override
    public SquarePosition get_position() {
        return this.position;
    }

    @Override
    public void set_position(SquarePosition position) {
        this.position.set_position(position.getColumn(), position.getRank());
    }

    public PieceNames get_piece_name(){return  piece_name;}

}