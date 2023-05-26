package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.ChessPiece;

public interface Chessboard extends Iterable<ChessPiece[]>{

    ChessPiece getPieceAt(int x, int y);

    ChessPiece getPieceAt(Square square);

    void addPiece(ChessPiece chessPiece);

    //If you prefer, modify this to use a location instead.
    void removePieceAt(Square square);
}
