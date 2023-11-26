package org.example.main;

import org.example.pieces.Pawn;
import org.example.pieces.Piece;

public class Move {
    int oldCol;
    int oldRow;

    int newCol;
    int newRow;

    Piece piece;
    Piece capture;

    public Move(Board board, Piece piece, int newCol,int newRow){
        this.oldCol = piece.row;
        this.oldRow = piece.col;

        this.newCol = newCol;
        this.newRow = newRow;

        this.piece = piece;
        this.capture =board.getPiece(newCol, newRow);
    }
}
