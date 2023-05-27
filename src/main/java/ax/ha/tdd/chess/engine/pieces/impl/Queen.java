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
        return chessboard.isValidSquare(destination, player)
                && (ChessPieceMovingUtils.isDiagonalClear(chessboard, destination, location)
                || ChessPieceMovingUtils.isStraightLineClear(chessboard, destination, location));
    }
}
