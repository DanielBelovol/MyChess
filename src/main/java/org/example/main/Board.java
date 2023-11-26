package org.example.main;

import org.example.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Board extends JPanel {
    public int titleSize = 85;
    int cols = 8;
    int rows = 8;

    ArrayList<Piece> piecesList = new ArrayList<>();

    public Piece selectedPiece;

    Input input = new Input(this);

    public Board() {
        this.setPreferredSize(new Dimension(cols * titleSize, rows * titleSize));

        this.addMouseListener(input);
        this.addMouseMotionListener(input);

        addPieces();
    }

    public Piece getPiece(int col, int row) {
        for (Piece piece : piecesList) {
            if (piece.col == col && piece.row == row) {
                return piece;
            }
        }
        return null;
    }

    public void makeMove(Move move) {
        move.piece.col = move.newCol;
        move.piece.row = move.newRow;

        move.piece.xPos = move.newCol * titleSize;
        move.piece.yPos = move.newRow * titleSize;

        capture(move);
    }

    public void capture(Move move) {
        piecesList.remove(move.capture);
    }

    public boolean isValidMove(Move move) {

        if(sameTeam(move.piece, move.capture)){
            return false;
        }

        return true;
    }

    public boolean sameTeam(Piece p1, Piece p2) {
        if (p1 == null || p2 == null) {
            return false;
        }
        return p1.isWhite == p2.isWhite;
    }


    public void addPieces() {
        //BLACK
        piecesList.add(new Rook(this, 0, 0, false));
        piecesList.add(new Knight(this, 1, 0, false));
        piecesList.add(new Bishop(this, 2, 0, false));
        piecesList.add(new Queen(this, 3, 0, false));
        piecesList.add(new King(this, 4, 0, false));
        piecesList.add(new Bishop(this, 5, 0, false));
        piecesList.add(new Knight(this, 6, 0, false));
        piecesList.add(new Rook(this, 7, 0, false));
        for (int i = 0; i < 8; i++) {
            piecesList.add(new Pawn(this, i, 1, false));
        }

        //WHITE
        piecesList.add(new Rook(this, 0, 7, true));
        piecesList.add(new Knight(this, 1, 7, true));
        piecesList.add(new Bishop(this, 2, 7, true));
        piecesList.add(new Queen(this, 3, 7, true));
        piecesList.add(new King(this, 4, 7, true));
        piecesList.add(new Bishop(this, 5, 7, true));
        piecesList.add(new Knight(this, 6, 7, true));
        piecesList.add(new Rook(this, 7, 7, true));
        for (int i = 0; i < 8; i++) {
            piecesList.add(new Pawn(this, i, 6, true));
        }

    }

    public void paintComponent(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                g2d.setColor((c + r) % 2 == 0 ? new Color(234, 184, 126) : new Color(180, 116, 48));
                g2d.fillRect(c * titleSize, r * titleSize, titleSize, titleSize);
            }

        for (Piece piece : piecesList) {
            piece.print(g2d);
        }

    }
}
