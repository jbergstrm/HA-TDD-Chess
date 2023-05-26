package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.Bishop;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public class BishopTest {

    @Test
    public void testWhiteBishopMovement() {
        Chessboard chessboard = new ChessboardImpl();
        Bishop c1 = new Bishop(Player.WHITE, new Square("c1"));
        assertTrue(c1.canMove(chessboard, new Square("a3")));
        assertTrue(c1.canMove(chessboard, new Square("h6")));
    }

    @Test
    public void testBlackBishopInvalidMovement() {
        Chessboard chessboard = new ChessboardImpl();
        Bishop c8 = new Bishop(Player.BLACK, new Square("c8"));
        assertFalse(c8.canMove(chessboard, new Square("d5")));
        assertFalse(c8.canMove(chessboard, new Square("c6")));
    }

    @Test
    public void testBishopMovingOverABlockedSquare() {
        Chessboard chessboard = new ChessboardImpl();
        Bishop c1 = new Bishop(Player.WHITE, new Square("c1"));
        Bishop e3 = new Bishop(Player.WHITE, new Square("e3"));
        chessboard.addPiece(c1);
        chessboard.addPiece(e3);
        assertFalse(c1.canMove(chessboard, new Square("h6")));
    }

    @Test
    public void testWhiteBishopEatsBlackBishop() {
        Chessboard chessboard = new ChessboardImpl();
        Bishop c1 = new Bishop(Player.WHITE, new Square("c1"));
        Bishop f4 = new Bishop(Player.BLACK, new Square("f4"));
        chessboard.addPiece(c1);
        chessboard.addPiece(f4);
        assertTrue(c1.canMove(chessboard, new Square("f4")));
    }
}
