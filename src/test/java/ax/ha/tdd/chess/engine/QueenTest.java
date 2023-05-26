package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.Queen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public class QueenTest {

    @Test
    public void testWhiteQueenStraightLineMovement() {
        Chessboard chessboard = new ChessboardImpl();
        Queen d1 = new Queen(Player.WHITE, new Square("d1"));
        assertTrue(d1.canMove(chessboard, new Square("h1")));
        assertTrue(d1.canMove(chessboard, new Square("d8")));
    }

    @Test
    public void testWhiteQueenDiagonalMovement() {
        Chessboard chessboard = new ChessboardImpl();
        Queen d1 = new Queen(Player.WHITE, new Square("d1"));
        assertTrue(d1.canMove(chessboard, new Square("h5")));
        assertTrue(d1.canMove(chessboard, new Square("a4")));
    }

    @Test
    public void testBlackQueenInvalidMovement() {
        Chessboard chessboard = new ChessboardImpl();
        Queen d8 = new Queen(Player.BLACK, new Square("d8"));
        assertFalse(d8.canMove(chessboard, new Square("e5")));
        assertFalse(d8.canMove(chessboard, new Square("f7")));
    }

    @Test
    public void testQueenMovingOverBlockedSquare() {
        Chessboard chessboard = new ChessboardImpl();
        Queen d1 = new Queen(Player.WHITE, new Square("d1"));
        Queen d4 = new Queen(Player.BLACK, new Square("d4"));
        chessboard.addPiece(d1);
        chessboard.addPiece(d4);
        assertFalse(d1.canMove(chessboard, new Square("d8")));
    }

    @Test
    public void testWhiteQueenEatsBlackQueen() {
        Chessboard chessboard = new ChessboardImpl();
        Queen d1 = new Queen(Player.WHITE, new Square("d1"));
        Queen b3 = new Queen(Player.BLACK, new Square("b3"));
        chessboard.addPiece(d1);
        chessboard.addPiece(b3);
        assertTrue(d1.canMove(chessboard, new Square("b3")));
    }
}
