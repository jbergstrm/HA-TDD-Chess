package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.Knight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public class KnightTest {

    @Test
    public void testWhiteKnightMovement() {
        Chessboard chessboard = new ChessboardImpl();
        Knight b1 = new Knight(Player.WHITE, new Square("b1"));
        assertTrue(b1.canMove(chessboard, new Square("a3")));
        assertTrue(b1.canMove(chessboard, new Square("c3")));
        assertTrue(b1.canMove(chessboard, new Square("d2")));
    }

    @Test
    public void testBlackKnightInvalidMovement() {
        Chessboard chessboard = new ChessboardImpl();
        Knight g8 = new Knight(Player.BLACK, new Square("g8"));
        assertFalse(g8.canMove(chessboard, new Square("h5")));
        assertFalse(g8.canMove(chessboard, new Square("f7")));
    }

    @Test
    public void testKnightMovingOverABlockedSquare() {
        Chessboard chessboard = new ChessboardImpl();
        Knight b1 = new Knight(Player.WHITE, new Square("b1"));
        Knight b2 = new Knight(Player.BLACK, new Square("b2"));
        chessboard.addPiece(b1);
        chessboard.addPiece(b2);
        assertTrue(b1.canMove(chessboard, new Square("a3")));
    }

    @Test
    public void testWhiteKnightEatsBlackKnight() {
        Chessboard chessboard = new ChessboardImpl();
        Knight b1 = new Knight(Player.WHITE, new Square("b1"));
        Knight d2 = new Knight(Player.BLACK, new Square("d2"));
        chessboard.addPiece(b1);
        chessboard.addPiece(d2);
        assertTrue(b1.canMove(chessboard, new Square("d2")));
    }
}
