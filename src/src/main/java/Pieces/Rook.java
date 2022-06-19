package Pieces;

import Board.Board;
import Board.SquarePosition;

import java.util.ArrayList;

public class Rook extends Piece{
    final PieceNames piece_name = PieceNames.ROOK;
    public Rook(PieceColor color, SquarePosition position) {
        super( color, position );
    }

    @Override
    public boolean move(SquarePosition pos_move, Board board) {
        ArrayList<SquarePosition> possible = new ArrayList<>();

        if (pos_move.getRank() == position.getRank()){
            if (pos_move.getColumn() > position.getColumn()){
                for(int x = position.getColumn() + 1; x <= pos_move.getColumn(); ++x){
                    if (!board.getSquare(x, pos_move.getRank()).is_occupied() || x == pos_move.getColumn()){
                        possible.add(new SquarePosition(x, position.getRank()));
                    }else{
                        break;
                    }
                }

            }
        }
        if (pos_move.getColumn() < position.getColumn()) {
            for (int x = position.getColumn() - 1; x >= pos_move.getColumn(); --x) {
                if (!board.getSquare(x, pos_move.getRank()).is_occupied() || x == pos_move.getColumn()) {
                    possible.add(new SquarePosition(x, position.getRank()));
                } else {
                    break;
                }
            }
        }
        if (pos_move.getColumn() == position.getColumn()){
            if (pos_move.getRank() > position.getRank()){
                for(int y = position.getRank() + 1; y <= pos_move.getRank(); ++y){
                    if(!board.getSquare(pos_move.getColumn(), y).is_occupied() || y == pos_move.getRank()){
                        possible.add(new SquarePosition(position.getColumn(), y));
                    }else{
                        break;
                    }
                }

            }
            if (pos_move.getRank() < position.getRank()){
                for(int y = position.getRank() - 1; y >= pos_move.getRank(); --y){
                    if(!board.getSquare(pos_move.getColumn(), y).is_occupied() || y == pos_move.getRank()){
                        possible.add(new SquarePosition(position.getColumn(), y));
                    }else{
                        break;
                    }
                }

            }
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

    public PieceNames get_piece_name(){return piece_name;}
}