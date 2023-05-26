package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.Square;

/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public class Bishop extends ChessPieceBase implements ChessPiece {

    public Bishop(final Player player, Square location) {
        super(PieceType.BISHOP, player, location);
    }

    @Override
    public String getSymbol() {
        return this.getPieceType().getSymbol();
    }

    @Override
    public boolean canMove(final Chessboard chessboard, final Square destination) {

        final ChessPiece chessPiece = chessboard.getPieceAt(destination);

        // Destination is unoccupied or contains a piece of different color
        if (chessPiece == null || chessPiece.getPlayer() != player) {
            return ChessPieceMovingUtils.isDiagonalClear(chessboard, destination, location);
        }

        // None of the conditions met
        return false;
    }
}
