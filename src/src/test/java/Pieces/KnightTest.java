package Pieces;

import Board.Board;
import Board.SquarePosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

    @Test
    void Basic_movement(){
        Board board = new Board();

        Knight knight = new Knight(PieceColor.BLACK, new SquarePosition(3,4));

        board.getSquare(3,4).set_piece(knight);

        assertTrue(knight.move(new SquarePosition(4,6), board));
        assertTrue(knight.move(new SquarePosition(4,2), board));
        assertTrue(knight.move(new SquarePosition(5,5), board));
        assertTrue(knight.move(new SquarePosition(5,3), board));
        assertTrue(knight.move(new SquarePosition(2,6), board));
        assertTrue(knight.move(new SquarePosition(2,2), board));
    }

    @Test
    void Edge_move(){
        Board board = new Board();

        Knight knight = new Knight(PieceColor.BLACK, new SquarePosition(0,4));

        board.getSquare(0,4).set_piece(knight);

        assertFalse(knight.move(new SquarePosition(-1,6), board));
        assertFalse(knight.move(new SquarePosition(-1,2), board));

    }
}