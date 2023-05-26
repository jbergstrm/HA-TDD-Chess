package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.Player;

public interface ChessPiece {
    /**
     * The graphical symbol to use for display of this piece
     */
    String getSymbol();

    /**
     * Is it a rook, pawn, queen, etc?
     */
    PieceType getPieceType();

    /**
     * White or black?
     */
    Player getPlayer();

    Square getLocation();

    int getDirectionMultiplier();

    boolean canMove(Chessboard chessboard, Square destination);
}
