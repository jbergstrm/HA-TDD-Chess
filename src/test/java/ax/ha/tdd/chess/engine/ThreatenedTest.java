package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.ChessPieceMovingUtils;
import ax.ha.tdd.chess.engine.pieces.impl.Bishop;
import ax.ha.tdd.chess.engine.pieces.impl.King;
import ax.ha.tdd.chess.engine.pieces.impl.Knight;
import ax.ha.tdd.chess.engine.pieces.impl.Pawn;
import ax.ha.tdd.chess.engine.pieces.impl.Queen;
import ax.ha.tdd.chess.engine.pieces.impl.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public class ThreatenedTest {

    @Test
    public void testSquareThreatenedByPawn() {
        Chessboard chessboard = new ChessboardImpl();
        Pawn c3 = new Pawn(Player.WHITE, new Square("c3"));
        chessboard.addPiece(c3);
        assertTrue(ChessPieceMovingUtils.isThreatened(chessboard, new Square("b4"), Player.BLACK));
        assertTrue(ChessPieceMovingUtils.isThreatened(chessboard, new Square("c4"), Player.BLACK));
    }

    @Test
    public void testSquareThreatenedByRook() {
        Chessboard chessboard = new ChessboardImpl();
        Rook c3 = new Rook(Player.WHITE, new Square("c3"));
        chessboard.addPiece(c3);
        assertTrue(ChessPieceMovingUtils.isThreatened(chessboard, new Square("c8"), Player.BLACK));
        assertTrue(ChessPieceMovingUtils.isThreatened(chessboard, new Square("g3"), Player.BLACK));
    }

    @Test
    public void testSquareThreatenedByKnight() {
        Chessboard chessboard = new ChessboardImpl();
        Knight c3 = new Knight(Player.WHITE, new Square("c3"));
        chessboard.addPiece(c3);
        assertTrue(ChessPieceMovingUtils.isThreatened(chessboard, new Square("b5"), Player.BLACK));
        assertTrue(ChessPieceMovingUtils.isThreatened(chessboard, new Square("e2"), Player.BLACK));
    }

    @Test
    public void testSquareThreatenedByBishop() {
        Chessboard chessboard = new ChessboardImpl();
        Bishop c3 = new Bishop(Player.WHITE, new Square("c3"));
        chessboard.addPiece(c3);
        assertTrue(ChessPieceMovingUtils.isThreatened(chessboard, new Square("h8"), Player.BLACK));
        assertTrue(ChessPieceMovingUtils.isThreatened(chessboard, new Square("a5"), Player.BLACK));
    }

    @Test
    public void testSquareThreatenedByQueen() {
        Chessboard chessboard = new ChessboardImpl();
        Queen c3 = new Queen(Player.WHITE, new Square("c3"));
        chessboard.addPiece(c3);
        assertTrue(ChessPieceMovingUtils.isThreatened(chessboard, new Square("h3"), Player.BLACK));
        assertTrue(ChessPieceMovingUtils.isThreatened(chessboard, new Square("f6"), Player.BLACK));

    }

    @Test
    public void testSquareThreatenedByKing() {
        Chessboard chessboard = new ChessboardImpl();
        King c3 = new King(Player.WHITE, new Square("c3"));
        chessboard.addPiece(c3);
        assertTrue(ChessPieceMovingUtils.isThreatened(chessboard, new Square("b4"), Player.BLACK));
        assertTrue(ChessPieceMovingUtils.isThreatened(chessboard, new Square("b2"), Player.BLACK));
    }
}
