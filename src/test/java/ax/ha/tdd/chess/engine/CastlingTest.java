package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.ChessPieceMovingUtils;
import ax.ha.tdd.chess.engine.pieces.impl.King;
import ax.ha.tdd.chess.engine.pieces.impl.Knight;
import ax.ha.tdd.chess.engine.pieces.impl.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public class CastlingTest {

    @Test
    public void testWhiteKingCastling() {
        Chessboard chessboard = new ChessboardImpl();
        King e1 = new King(Player.WHITE, new Square("e1"));
        Rook a1 = new Rook(Player.WHITE, new Square("a1"));
        Rook h1 = new Rook(Player.WHITE, new Square("h1"));
        chessboard.addPiece(e1);
        chessboard.addPiece(a1);
        chessboard.addPiece(h1);
        assertTrue(ChessPieceMovingUtils.kingCastling(chessboard, e1, a1));
        assertTrue(ChessPieceMovingUtils.kingCastling(chessboard, e1, h1));
    }

    @Test
    public void testBlackKingCastling() {
        Chessboard chessboard = new ChessboardImpl();
        King e8 = new King(Player.BLACK, new Square("e8"));
        Rook a8 = new Rook(Player.BLACK, new Square("a8"));
        Rook h8 = new Rook(Player.BLACK, new Square("h8"));
        chessboard.addPiece(e8);
        chessboard.addPiece(a8);
        chessboard.addPiece(h8);
        assertTrue(ChessPieceMovingUtils.kingCastling(chessboard, e8, a8));
        assertTrue(ChessPieceMovingUtils.kingCastling(chessboard, e8, h8));
    }

    @Test
    public void testWhiteKingCastlingWhenKingHasMovedShouldBeIllegal() {
        Chessboard chessboard = new ChessboardImpl();
        King e1 = new King(Player.WHITE, new Square("e1"));
        e1.isMoved(true);
        Rook a1 = new Rook(Player.WHITE, new Square("a1"));
        Rook h1 = new Rook(Player.WHITE, new Square("h1"));
        chessboard.addPiece(e1);
        chessboard.addPiece(a1);
        chessboard.addPiece(h1);
        assertFalse(ChessPieceMovingUtils.kingCastling(chessboard, e1, a1));
        assertFalse(ChessPieceMovingUtils.kingCastling(chessboard, e1, h1));
    }

    @Test
    public void testWhiteKingCastlingWhenRookHasMovedShouldBeIllegal() {
        Chessboard chessboard = new ChessboardImpl();
        King e1 = new King(Player.WHITE, new Square("e1"));
        Rook a1 = new Rook(Player.WHITE, new Square("a1"));
        a1.isMoved(true);
        Rook h1 = new Rook(Player.WHITE, new Square("h1"));
        h1.isMoved(true);
        chessboard.addPiece(e1);
        chessboard.addPiece(a1);
        chessboard.addPiece(h1);
        assertFalse(ChessPieceMovingUtils.kingCastling(chessboard, e1, a1));
        assertFalse(ChessPieceMovingUtils.kingCastling(chessboard, e1, h1));
    }

    @Test
    public void testWhiteKingCastlingWhenBlocked() {
        Chessboard chessboard = new ChessboardImpl();
        King e1 = new King(Player.WHITE, new Square("e1"));
        Rook a1 = new Rook(Player.WHITE, new Square("a1"));
        Rook h1 = new Rook(Player.WHITE, new Square("h1"));
        Knight b1 = new Knight(Player.WHITE, new Square("b1"));
        Knight g1 = new Knight(Player.WHITE, new Square("g1"));
        chessboard.addPiece(e1);
        chessboard.addPiece(a1);
        chessboard.addPiece(h1);
        chessboard.addPiece(b1);
        chessboard.addPiece(g1);
        assertFalse(ChessPieceMovingUtils.kingCastling(chessboard, e1, a1));
        assertFalse(ChessPieceMovingUtils.kingCastling(chessboard, e1, h1));
    }

    @Test
    public void testWhiteKingCastlingWhenThreatenedShouldBeIllegal() {
        Chessboard chessboard = new ChessboardImpl();
        King e1 = new King(Player.WHITE, new Square("e1"));
        Rook a1 = new Rook(Player.WHITE, new Square("a1"));
        Rook h1 = new Rook(Player.WHITE, new Square("h1"));
        Rook e6 = new Rook(Player.BLACK, new Square("e6"));
        chessboard.addPiece(e1);
        chessboard.addPiece(a1);
        chessboard.addPiece(h1);
        chessboard.addPiece(e6);
        assertFalse(ChessPieceMovingUtils.kingCastling(chessboard, e1, a1));
        assertFalse(ChessPieceMovingUtils.kingCastling(chessboard, e1, h1));
    }

    @Test
    public void testWhiteKingCastlingWhenRookIsThreatenedShouldBeIllegal() {
        Chessboard chessboard = new ChessboardImpl();
        King e1 = new King(Player.WHITE, new Square("e1"));
        Rook a1 = new Rook(Player.WHITE, new Square("a1"));
        Rook h1 = new Rook(Player.WHITE, new Square("h1"));
        Rook h6 = new Rook(Player.BLACK, new Square("h6"));
        Rook a6 = new Rook(Player.BLACK, new Square("a6"));
        chessboard.addPiece(e1);
        chessboard.addPiece(a1);
        chessboard.addPiece(h1);
        chessboard.addPiece(h6);
        chessboard.addPiece(a6);
        assertFalse(ChessPieceMovingUtils.kingCastling(chessboard, e1, a1));
        assertFalse(ChessPieceMovingUtils.kingCastling(chessboard, e1, h1));
    }

    @Test
    public void testWhiteKingCastlingWhenSquaresBetweenAreThreatenedShouldBeIllegal() {
        Chessboard chessboard = new ChessboardImpl();
        King e1 = new King(Player.WHITE, new Square("e1"));
        Rook a1 = new Rook(Player.WHITE, new Square("a1"));
        Rook h1 = new Rook(Player.WHITE, new Square("h1"));
        Rook f6 = new Rook(Player.BLACK, new Square("f6"));
        Rook b6 = new Rook(Player.BLACK, new Square("b6"));
        chessboard.addPiece(e1);
        chessboard.addPiece(a1);
        chessboard.addPiece(h1);
        chessboard.addPiece(f6);
        chessboard.addPiece(b6);
        assertFalse(ChessPieceMovingUtils.kingCastling(chessboard, e1, a1));
        assertFalse(ChessPieceMovingUtils.kingCastling(chessboard, e1, h1));
    }
}
