package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.PieceType;

public interface Chessboard extends Iterable<ChessPiece[]>{

    ChessPiece getPieceAt(int x, int y);

    ChessPiece getPieceAt(Square square);

    Square getPieceLocation(PieceType pieceType, Player player);

    void addPiece(ChessPiece chessPiece);

    void removePieceAt(Square square);

    /**
     * Validates if square is a valid move for the player.
     *
     * @param square    The destination square
     * @param player    The player who initiated the move
     * @return          True if valid else false
     */
    boolean isValidSquare(Square square, Player player);
}
