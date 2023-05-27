package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.impl.Bishop;
import ax.ha.tdd.chess.engine.pieces.impl.King;
import ax.ha.tdd.chess.engine.pieces.impl.Knight;
import ax.ha.tdd.chess.engine.pieces.impl.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public class WinningStateTest {

    @Test
    public void testWinningStateOnGameStart() {
        Chessboard chessboard = ChessboardImpl.startingBoard();
        assertEquals(WinningState.PLAYING, WinningStateUtils.checkState(chessboard, Player.WHITE));
        assertEquals(WinningState.PLAYING, WinningStateUtils.checkState(chessboard, Player.BLACK));
    }

    @Test
    public void testWhitePlayerWinningStateCheckByKnight() {
        Chessboard chessboard = new ChessboardImpl();
        King e1 = new King(Player.WHITE, new Square("e1"));
        Knight d3 = new Knight(Player.BLACK, new Square("d3"));
        chessboard.addPiece(e1);
        chessboard.addPiece(d3);
        assertEquals(WinningState.CHECK, WinningStateUtils.checkState(chessboard, Player.WHITE));
    }

    @Test
    public void testBlackPlayerWinningStateCheckByKnight() {
        Chessboard chessboard = new ChessboardImpl();
        King e8 = new King(Player.BLACK, new Square("e8"));
        Knight f6 = new Knight(Player.WHITE, new Square("f6"));
        chessboard.addPiece(e8);
        chessboard.addPiece(f6);
        assertEquals(WinningState.CHECK, WinningStateUtils.checkState(chessboard, Player.BLACK));
    }

    @Test
    public void testWhitePlayerWinningStateCheckByBishop() {
        Chessboard chessboard = new ChessboardImpl();
        King e1 = new King(Player.WHITE, new Square("e1"));
        Bishop b4 = new Bishop(Player.BLACK, new Square("b4"));
        chessboard.addPiece(e1);
        chessboard.addPiece(b4);
        assertEquals(WinningState.CHECK, WinningStateUtils.checkState(chessboard, Player.WHITE));
    }

    @Test
    public void testBlackPlayerWinningStateCheckByBishop() {
        Chessboard chessboard = new ChessboardImpl();
        King e8 = new King(Player.BLACK, new Square("e8"));
        Bishop h5 = new Bishop(Player.WHITE, new Square("h5"));
        chessboard.addPiece(e8);
        chessboard.addPiece(h5);
        assertEquals(WinningState.CHECK, WinningStateUtils.checkState(chessboard, Player.BLACK));
    }

    @Test
    public void testWhitePlayerAvoidingWinningStateCheck() {
        Chessboard chessboard = new ChessboardImpl();
        King e1 = new King(Player.WHITE, new Square("e1"));
        Bishop b4 = new Bishop(Player.BLACK, new Square("b4"));
        chessboard.addPiece(e1);
        chessboard.addPiece(b4);
        assertEquals(WinningState.CHECK, WinningStateUtils.checkState(chessboard, Player.WHITE));

        chessboard.removePieceAt(new Square("e1"));
        King f1 = new King(Player.WHITE, new Square("f1"));
        chessboard.addPiece(f1);
        assertEquals(WinningState.PLAYING, WinningStateUtils.checkState(chessboard, Player.WHITE));
    }

    @Test
    public void testBlackPlayerAvoidingWinningStateCheck() {
        Chessboard chessboard = new ChessboardImpl();
        King e8 = new King(Player.BLACK, new Square("e8"));
        Bishop h5 = new Bishop(Player.WHITE, new Square("h5"));
        chessboard.addPiece(e8);
        chessboard.addPiece(h5);
        assertEquals(WinningState.CHECK, WinningStateUtils.checkState(chessboard, Player.BLACK));

        chessboard.removePieceAt(new Square("e8"));
        King f8 = new King(Player.BLACK, new Square("f8"));
        chessboard.addPiece(f8);
        assertEquals(WinningState.PLAYING, WinningStateUtils.checkState(chessboard, Player.BLACK));
    }

    @Test
    public void testWhitePlayerWinningStateCheckmate() {
        Chessboard chessboard = new ChessboardImpl();
        King e1 = new King(Player.WHITE, new Square("e1"));
        Rook a1 = new Rook(Player.BLACK, new Square("a1"));
        Rook h2 = new Rook(Player.BLACK, new Square("h2"));
        chessboard.addPiece(e1);
        chessboard.addPiece(a1);
        chessboard.addPiece(h2);
        assertEquals(WinningState.CHECKMATE, WinningStateUtils.checkState(chessboard, Player.WHITE));
    }

    @Test
    public void testBlackPlayerWinningStateCheckmate() {
        Chessboard chessboard = new ChessboardImpl();
        King e8 = new King(Player.BLACK, new Square("e8"));
        Rook a8 = new Rook(Player.WHITE, new Square("a8"));
        Rook h7= new Rook(Player.WHITE, new Square("h7"));
        chessboard.addPiece(e8);
        chessboard.addPiece(a8);
        chessboard.addPiece(h7);
        assertEquals(WinningState.CHECKMATE, WinningStateUtils.checkState(chessboard, Player.BLACK));
    }
}
