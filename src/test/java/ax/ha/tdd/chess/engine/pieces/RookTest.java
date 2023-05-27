package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.ChessboardImpl;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.pieces.impl.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public class RookTest {

    @Test
    public void testWhiteRookForwardOneStepUnblocked() {
        Chessboard chessboard = new ChessboardImpl();
        Rook a1 = new Rook(Player.WHITE, new Square("a1"));
        assertTrue(a1.canMove(chessboard, new Square("a2")));
        assertTrue(a1.canMove(chessboard, new Square("b1")));
    }

    @Test
    public void testBlackRookForwardOneStepUnblocked() {
        Chessboard chessboard = new ChessboardImpl();
        Rook a8 = new Rook(Player.BLACK, new Square("a8"));
        assertTrue(a8.canMove(chessboard, new Square("a7")));
        assertTrue(a8.canMove(chessboard, new Square("b8")));
    }

    @Test
    public void testWhiteRookForwardMultipleStepsUnblocked() {
        Chessboard chessboard = new ChessboardImpl();
        Rook a1 = new Rook(Player.WHITE, new Square("a1"));
        assertTrue(a1.canMove(chessboard, new Square("a8")));
        assertTrue(a1.canMove(chessboard, new Square("h1")));
    }

    @Test
    public void testBlackRookForwardMultipleStepsUnblocked() {
        Chessboard chessboard = new ChessboardImpl();
        Rook a8 = new Rook(Player.BLACK, new Square("a8"));
        assertTrue(a8.canMove(chessboard, new Square("a1")));
        assertTrue(a8.canMove(chessboard, new Square("h8")));
    }

    @Test
    public void testWhiteRookForwardMultipleStepsBlocked() {
        Chessboard chessboard = new ChessboardImpl();
        Rook a1 = new Rook(Player.WHITE, new Square("a1"));
        Rook a3 = new Rook(Player.BLACK, new Square("a3"));
        chessboard.addPiece(a1);
        chessboard.addPiece(a3);
        assertFalse(a1.canMove(chessboard, new Square("a8")));
    }

    @Test
    public void testMoveDiagonalShouldBeIllegal() {
        Chessboard chessboard = new ChessboardImpl();
        Rook a1 = new Rook(Player.WHITE, new Square("a1"));
        assertFalse(a1.canMove(chessboard, new Square("c3")));
    }

    @Test
    public void testWhiteRookEatsBlackRook() {
        Chessboard chessboard = new ChessboardImpl();
        Rook a1 = new Rook(Player.WHITE, new Square("a1"));
        Rook a3 = new Rook(Player.BLACK, new Square("a3"));
        chessboard.addPiece(a1);
        chessboard.addPiece(a3);
        assertTrue(a1.canMove(chessboard, new Square("a3")));
    }
}
