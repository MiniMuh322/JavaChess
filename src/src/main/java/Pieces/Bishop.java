package Pieces;

import Board.Board;
import Board.SquarePosition;

import java.util.ArrayList;

public class Bishop extends Piece {

    final PieceNames piece_name = PieceNames.BISHOP;

    public Bishop(PieceColor color, SquarePosition position) {
        super(color,position);
    }

    @Override
    public boolean move(SquarePosition pos_move, Board board) {

        ArrayList<SquarePosition> possible = new ArrayList<>();

        if (pos_move.getColumn() > position.getColumn() && pos_move.getRank() > position.getRank()){
            int x = position.getColumn() + 1, y = position.getRank() + 1;
            while(x <= pos_move.getColumn() && y <= pos_move.getRank()){

                if (!board.getSquare(x,y).is_occupied() || pos_move.getRank() == y && pos_move.getColumn() == x){
                    possible.add(new SquarePosition(x,y));
                }else{
                    break;
                }
                x++;
                y++;
            }
        }
        if (pos_move.getColumn() < position.getColumn() && pos_move.getRank() > position.getRank()){
            int x = position.getColumn() - 1, y = position.getRank() + 1;
            while( x >= pos_move.getColumn() && y <= pos_move.getRank()){
                if (!board.getSquare(x,y).is_occupied() || pos_move.getRank() == y && pos_move.getColumn() == x){
                    possible.add(new SquarePosition(x,y));
                }else{
                    break;
                }
                x--;
                y++;
            }
        }
        if (pos_move.getColumn() > position.getColumn() && pos_move.getRank() < position.getRank()){
            int x = position.getColumn() + 1, y = position.getRank() - 1;
            while( x <= pos_move.getColumn() && y >= pos_move.getRank()){
                if (!board.getSquare(x,y).is_occupied() || pos_move.getRank() == y && pos_move.getColumn() == x){
                    possible.add(new SquarePosition(x,y));
                }else{
                    break;
                }
                x++;
                y--;
            }
        }
        if (pos_move.getColumn() < position.getColumn() && pos_move.getRank() < position.getRank()){
            int x = position.getColumn() - 1, y = position.getRank() - 1;
            while( x >= pos_move.getColumn() && y >= pos_move.getRank()){
                if (!board.getSquare(x,y).is_occupied() || pos_move.getRank() == y && pos_move.getColumn() == x){
                    possible.add(new SquarePosition(x,y));
                }else{
                    break;
                }
                x--;
                y--;
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

    public PieceNames get_piece_name(){return  piece_name;}
}