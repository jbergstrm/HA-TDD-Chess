package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.Player;

public class Pawn extends ChessPieceBase implements ChessPiece {

    public Pawn(final Player player, final Square location) {
        super(PieceType.PAWN, player, location);
    }

    @Override
    public String getSymbol() {
        return this.getPieceType().getSymbol();
    }

    @Override
    public boolean canMove(final Chessboard chessboard, final Square destination) {

        final ChessPiece chessPiece = chessboard.getPieceAt(destination);

        // Moving vertically and destination is unoccupied
        if (chessPiece == null && location.getX() == destination.getX()) {

            // Moving one square forward
            if (location.getY() == destination.getY() - getDirectionMultiplier()) {
                return true;
            }

            // Moving two spaces forward
            if (startingLocation() && location.getY() == destination.getY() - (2 * getDirectionMultiplier())
                    && chessboard.getPieceAt(destination.getX(), destination.getY() - getDirectionMultiplier()) == null) {
                return true;
            }
        }

        // Moving diagonally forward and destination piece is of different color
        if (chessPiece != null && chessPiece.getPlayer() != player
                && Math.abs(location.getX() - destination.getX()) == 1
                && location.getY() == destination.getY() - getDirectionMultiplier()) {
            return true;
        }

        // None of the conditions met
        return false;
    }

    private boolean startingLocation() {
        return ((location.getY() == 1 && player == Player.BLACK)
                || (location.getY() == 6 && player == Player.WHITE));
    }
}
