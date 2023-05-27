package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.ChessboardImpl;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.pieces.impl.King;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public class KingTest {

    @Test
    public void testWhiteKingLegalMovementUnBlocked() {
        Chessboard chessboard = new ChessboardImpl();
        King e1 = new King(Player.WHITE, new Square("e1"));
        assertTrue(e1.canMove(chessboard, new Square("d1")));
        assertTrue(e1.canMove(chessboard, new Square("f1")));
        assertTrue(e1.canMove(chessboard, new Square("e2")));
        assertTrue(e1.canMove(chessboard, new Square("d2")));
    }

    @Test
    public void testBlackKingIllegalMovement() {
        Chessboard chessboard = new ChessboardImpl();
        King e8 = new King(Player.BLACK, new Square("e8"));
        assertFalse(e8.canMove(chessboard, new Square("e6")));
        assertFalse(e8.canMove(chessboard, new Square("f6")));
        assertFalse(e8.canMove(chessboard, new Square("h8")));
    }

    @Test
    public void testWhiteKingEatsBlackKingShouldBeIllegal() {
        Chessboard chessboard = new ChessboardImpl();
        King e1 = new King(Player.WHITE, new Square("e1"));
        King f2 = new King(Player.BLACK, new Square("f2"));
        chessboard.addPiece(e1);
        chessboard.addPiece(f2);
        assertFalse(e1.canMove(chessboard, new Square("f2")));
    }

    @Test
    public void testWhiteKingMovingToThreatenedSquareShouldBeIllegal() {
        Chessboard chessboard = new ChessboardImpl();
        King e1 = new King(Player.WHITE, new Square("e1"));
        King f2 = new King(Player.BLACK, new Square("f2"));
        chessboard.addPiece(e1);
        chessboard.addPiece(f2);
        assertFalse(e1.canMove(chessboard, new Square("f1")));
    }
}
