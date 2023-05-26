package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.Square;

/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public class Queen extends ChessPieceBase implements ChessPiece {

    public Queen(final Player player, final Square location) {
        super(PieceType.QUEEN, player, location);
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
            return ChessPieceMovingUtils.isDiagonalClear(chessboard, destination, location)
                    || ChessPieceMovingUtils.isStraightLineClear(chessboard, destination, location);
        }

        // None of the conditions met
        return false;
    }
}
