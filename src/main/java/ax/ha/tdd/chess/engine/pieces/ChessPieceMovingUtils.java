package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.pieces.impl.King;
import ax.ha.tdd.chess.engine.pieces.impl.Rook;

import java.util.Arrays;

/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public final class ChessPieceMovingUtils {

    private static final int[][] THREAT_MAP = {
            {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1},     // Knight
            {0, 1}, {0, -1}, {1, 0}, {-1, 0},                                           // StraightLine
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}                                          // Diagonal
    };

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
        if (Math.abs(location.getX() - destination.getX()) != Math.abs(location.getY() - destination.getY())) {
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

    public static boolean kingCastling(final Chessboard chessboard, final King king, final Rook rook) {

        if (king.isMoved() || rook.isMoved()
                || isThreatened(chessboard, king.getLocation(), king.getPlayer())
                || isThreatened(chessboard, rook.getLocation(), rook.getPlayer())) {
            return false;
        }

        final int[] range = rook.getLocation().getX() == 7
                ? new int[]{5, 6} : new int[]{1, 2, 3};

        return Arrays.stream(range).noneMatch(x -> isThreatened(chessboard, new Square(x, king.getLocation().getY()), king.getPlayer()))
                && Arrays.stream(range).allMatch(x -> chessboard.getPieceAt(x, king.getLocation().getY()) == null);
    }

    public static boolean isThreatened(final Chessboard chessboard, final Square location, final Player player) {
        return Arrays.stream(THREAT_MAP)
                .anyMatch(move -> isThreat(chessboard, location, player, move));
    }

    private static boolean isThreat(final Chessboard chessboard, final Square location, final Player player, final int[] move) {
        return Math.abs(move[0]) == 2 || Math.abs(move[1]) == 2
                ? isThreatenedByKnight(chessboard, location, player, move)
                : isThreatenedByDiagonalOrStraightLine(chessboard, location, player, move);
    }

    private static boolean isThreatenedByDiagonalOrStraightLine(final Chessboard chessboard, final Square location, final Player player, final int[] move) {
        for (int x = location.getX() + move[0], y = location.getY() + move[1]; x >= 0 && x < 8 && y >= 0 && y < 8; x += move[0], y += move[1]) {
            final ChessPiece chessPiece = chessboard.getPieceAt(new Square(x, y));

            if (chessPiece != null && chessPiece.getPlayer() != player) {
                return switch (chessPiece.getPieceType()) {
                    case KING -> Math.abs(location.getX() - x) <= 1 && Math.abs(location.getY() - y) <= 1;
                    case PAWN -> ((player == Player.WHITE && move[1] == -1) || (player == Player.BLACK && move[1] == 1)) && Math.abs(location.getX() - x) <= 1 && Math.abs(location.getY() - y) <= 1;
                    case QUEEN, ROOK, BISHOP -> true;
                    case KNIGHT -> false;
                };
            }
        }

        return false;
    }

    private static boolean isThreatenedByKnight(final Chessboard chessboard, final Square location, final Player player, final int[] move) {
        try {
            final ChessPiece chessPiece = chessboard.getPieceAt(
                    new Square(location.getX() + move[0], location.getY() + move[1]));

            return chessPiece != null && chessPiece.getPlayer() != player && chessPiece.getPieceType() == PieceType.KNIGHT;
        }
        catch (IllegalArgumentException e) {
            // Threat location is outside the board
            return false;
        }
    }
}
