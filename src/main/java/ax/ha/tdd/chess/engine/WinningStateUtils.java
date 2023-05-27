package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.ChessPieceMovingUtils;
import ax.ha.tdd.chess.engine.pieces.PieceType;

import java.util.Arrays;

/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public final class WinningStateUtils {

    private static final int[][] KING_MOVEMENT = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    public static WinningState checkState(final Chessboard chessboard, final Player player) {
        return ChessPieceMovingUtils.isThreatened(chessboard, chessboard.getPieceLocation(PieceType.KING, player), player)
                ? isCheckmate(chessboard, player) : WinningState.PLAYING;
    }

    private static WinningState isCheckmate(final Chessboard chessboard, final Player player) {
         return isAvoidable(chessboard, player) ? WinningState.CHECK : WinningState.CHECKMATE;
    }

    private static boolean isAvoidable(final Chessboard chessboard, final Player player) {
        final Square location = chessboard.getPieceLocation(PieceType.KING, player);
        return Arrays.stream(KING_MOVEMENT)
                .anyMatch(ints -> {
                    int dx = location.getX() + ints[0];
                    int dy = location.getY() + ints[1];

                    return dx >= 0 && dx < 8 && dy >= 0 && dy < 8
                            && !ChessPieceMovingUtils.isThreatened(chessboard, new Square(dx, dy), player);
                });
    }
}
