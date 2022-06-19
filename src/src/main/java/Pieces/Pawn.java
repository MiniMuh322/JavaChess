package Pieces;

import Board.Board;
import Board.SquarePosition;

import java.util.ArrayList;

public class Pawn extends Piece{
    final PieceNames piece_name = PieceNames.PAWN;
    public Pawn(PieceColor color, SquarePosition position) {
        super(color, position);
    }
    @Override
    public boolean move(SquarePosition pos_move, Board board) {

        ArrayList<SquarePosition> possible = new ArrayList<>();

        if (color == PieceColor.WHITE && position.getRank() == 6
                && !board.getSquare(position.getColumn(), position.getRank() - 2).is_occupied()
                && !board.getSquare(position.getColumn(), position.getRank() - 1).is_occupied()) {
            possible.add(new SquarePosition(position.getColumn(), position.getRank() - 2));
        }

        if (color == PieceColor.WHITE && position.getRank() != 0
                && !board.getSquare(position.getColumn(), position.getRank() - 1).is_occupied()) {
            possible.add(new SquarePosition(position.getColumn(), position.getRank() - 1));
        }

        if (color == PieceColor.WHITE && position.getRank() != 0) {
            if (position.getColumn() != 0) {
                if (board.getSquare(position.getColumn() - 1, position.getRank() - 1).is_occupied() &&
                        board.getSquare(position.getColumn() - 1, position.getRank() - 1).get_piece().get_Piece_color() == PieceColor.BLACK) {
                    possible.add(new SquarePosition(position.getColumn() - 1, position.getRank() - 1));
                }
            }
            if (position.getColumn() != 7) {
                if (board.getSquare(position.getColumn() + 1, position.getRank() - 1).is_occupied() &&
                        board.getSquare(position.getColumn() + 1, position.getRank() - 1).get_piece().get_Piece_color() == PieceColor.BLACK) {
                    possible.add(new SquarePosition(position.getColumn() + 1, position.getRank() - 1));
                }
            }
        }
        if (color == PieceColor.BLACK && position.getRank() == 1
                && !board.getSquare(position.getColumn(), position.getRank() + 2).is_occupied()
                && !board.getSquare(position.getColumn(), position.getRank() + 1).is_occupied()) {
            possible.add(new SquarePosition(position.getColumn(), position.getRank() + 2));
        }

        if (color == PieceColor.BLACK && position.getRank() != 7
                && !board.getSquare(position.getColumn(), position.getRank() + 1).is_occupied()) {
            possible.add(new SquarePosition(position.getColumn(), position.getRank() + 1));
        }

        if (color == PieceColor.BLACK && position.getRank() != 7) {
            if (position.getColumn() != 0) {
                if (board.getSquare(position.getColumn() - 1, position.getRank() + 1).is_occupied() &&
                        board.getSquare(position.getColumn() - 1, position.getRank() + 1).get_piece().get_Piece_color() == PieceColor.WHITE) {
                    possible.add(new SquarePosition(position.getColumn() - 1, position.getRank() + 1));
                }
            }
            if (position.getColumn() != 7) {
                if (board.getSquare(position.getColumn() + 1, position.getRank() + 1).is_occupied() &&
                        board.getSquare(position.getColumn() + 1, position.getRank() + 1).get_piece().get_Piece_color() == PieceColor.WHITE) {
                    possible.add(new SquarePosition(position.getColumn() + 1, position.getRank() + 1));
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

    public PieceNames get_piece_name(){return  piece_name;}

}