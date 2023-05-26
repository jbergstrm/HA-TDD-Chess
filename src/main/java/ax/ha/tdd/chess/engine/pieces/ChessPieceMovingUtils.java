package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Square;

/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public final class ChessPieceMovingUtils {

    public static boolean isStraightLineClear(final Chessboard chessboard, final Square destination, final Square location) {
        final int dx = Integer.compare(destination.getX(), location.getX());
        final int dy = Integer.compare(destination.getY(), location.getY());

        // Piece is not moving in a straight line
        if (dx != 0 && dy != 0) {
            return false;
        }

        // Scan through each cell between the current location and the destination
        for (int x = location.getX() + dx, y = location.getY() + dy; x != destination.getX() || y != destination.getY(); x += dx, y += dy) {
            // Piece in the path
            if (chessboard.getPieceAt(x, y) != null) {
                return false;
            }
        }

        return true;
    }

    public static boolean isDiagonalClear(final Chessboard chessboard, final Square destination, final Square location) {

        // Piece is not moving diagonal
        if (Math.abs(location.getX() - destination.getX()) != Math.abs(location.getY()) - destination.getY()) {
            return false;
        }

        final int dx = Integer.compare(destination.getX(), location.getX());
        final int dy = Integer.compare(destination.getY(), location.getY());

        // Scan through each cell between the current location and the destination
        for (int x = location.getX() + dx, y = location.getY() + dy; x != destination.getX() && y != destination.getY(); x += dx, y += dy) {
            // Piece in the path
            if (chessboard.getPieceAt(x, y) != null) {
                return false;
            }
        }

        return true;
    }

    public static boolean KnightJump(final Square destination, final Square location) {
        final int dx = Math.abs(location.getX() - destination.getX());
        final int dy = Math.abs(location.getY() - destination.getY());

        // Shape: two squares in one direction and one square in the other
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
}
