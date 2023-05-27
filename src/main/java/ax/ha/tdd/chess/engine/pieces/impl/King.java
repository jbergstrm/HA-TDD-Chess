package ax.ha.tdd.chess.engine.pieces.impl;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.ChessPieceBase;
import ax.ha.tdd.chess.engine.pieces.ChessPieceMovingUtils;
import ax.ha.tdd.chess.engine.pieces.PieceType;

/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public class King extends ChessPieceBase implements ChessPiece {

    public King(final Player player, final Square location) {
        super(PieceType.KING, player, location);
    }

    @Override
    public String getSymbol() {
        return this.getPieceType().getSymbol();
    }

    @Override
    public boolean canMove(final Chessboard chessboard, final Square destination) {

        // If move is more than one square
        if (Math.abs(location.getX() - destination.getX()) > 1 || Math.abs(location.getY() - destination.getY()) > 1) {
            return false;
        }

        return !ChessPieceMovingUtils.isThreatened(chessboard, destination, player)
                && chessboard.isValidSquare(destination, player);
    }
}
