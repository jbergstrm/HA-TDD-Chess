package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.Square;

/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public class Rook extends ChessPieceBase implements ChessPiece {

    public Rook(final Player player, final Square location) {
        super(PieceType.ROOK, player, location);
    }

    @Override
    public String getSymbol() {
        return this.getPieceType().getSymbol();
    }

    @Override
    public boolean startingLocation() {
        return ((location.getY() == 0 && (location.getX() == 0 || location.getX() == 7) && player == Player.BLACK)
                || (location.getY() == 7 && (location.getX() == 0 || location.getX() == 7)) && player == Player.WHITE);
    }

    @Override
    public boolean canMove(final Chessboard chessboard, final Square destination) {

        final ChessPiece chessPiece = chessboard.getPieceAt(destination);

        // Destination is unoccupied or contains a piece of different color
        if (chessPiece == null || chessPiece.getPlayer() != player) {
            return ChessPieceMovingUtils.isStraightLineClear(chessboard, destination, location);
        }

        // None of the conditions met
        return false;
    }
}
