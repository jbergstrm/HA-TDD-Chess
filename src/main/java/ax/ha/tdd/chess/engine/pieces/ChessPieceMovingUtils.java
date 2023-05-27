package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.Square;

import java.util.Arrays;

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

    public static boolean knightJump(final Square destination, final Square location) {
        final int dx = Math.abs(location.getX() - destination.getX());
        final int dy = Math.abs(location.getY() - destination.getY());

        // Shape: two squares in one direction and one square in the other
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }

    public static boolean isThreatened(final Chessboard chessboard, final Square location, final Player player) {
        // Definition of all possible moves
        final int[][] threatenedMap = {
                {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}, // Knight
                {0, 1}, {0, -1}, {1, 0}, {-1, 0},                                       // Horizontal and vertical
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}                                      // Diagonal
        };

        // Check each direction
        return Arrays.stream(threatenedMap)
                .anyMatch(move -> checkDirection(chessboard, location, player, move[0], move[1]));
    }

    private static boolean checkDirection(final Chessboard chessboard, final Square location, final Player player, final int dx, final int dy) {
        int x = location.getX();
        int y = location.getY();

        while (x >= 0 && x < 8 && y >= 0 && y < 8) {
            final ChessPiece chessPiece = chessboard.getPieceAt(x, y);

            if (chessPiece != null && chessPiece.getPlayer() != player) {
                final boolean isAdjacent = Math.abs(x - location.getX()) + Math.abs(y - location.getY()) == 1;
                final boolean isKnightMove = Math.abs(dx) + Math.abs(dy) == 3;

                // True if piece in current spot is a threat
                if (isKnightMove && chessPiece.getPieceType() == PieceType.KNIGHT
                        || isAdjacent && chessPiece.getPieceType() == PieceType.KING
                        || (!isKnightMove && !isAdjacent && (chessPiece.getPieceType() == PieceType.QUEEN))
                        || (!isKnightMove && !isAdjacent && player == Player.BLACK && chessPiece.getPieceType() == PieceType.PAWN && dy == 1)) {
                    return true;
                }

                // Current piece isn't a knight, and we're not checking a knight move, break out of the loop
                if (!isKnightMove) {
                    break;
                }
            }

            // Move to next spot in current direction
            x += dx;
            y += dy;
        }

        return false;
    }
}
