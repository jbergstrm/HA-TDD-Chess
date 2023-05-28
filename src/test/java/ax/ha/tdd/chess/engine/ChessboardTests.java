package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.impl.King;
import ax.ha.tdd.chess.engine.pieces.impl.Queen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChessboardTests {

    @Test
    public void Chessboard_fromBeginning_isEmpty() {
        final ChessboardImpl chessboard = new ChessboardImpl();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Assertions.assertNull(chessboard.getPieceAt(new Square(x, y)));
            }
        }
    }

    @Test
    public void fullboard_whitePieces_areInCorrectSpot() {
        final ChessboardImpl chessboard = ChessboardImpl.startingBoard();
        for (int x = 0; x < 8; x++) {
            for (int y = 6; y < 8; y++) {
                Assertions.assertEquals(Player.WHITE, chessboard.getPieceAt(new Square(x, y)).getPlayer());
            }
        }
    }

    @Test
    public void fullboard_BlackPieces_areInCorrectSpot() {
        final ChessboardImpl chessboard = ChessboardImpl.startingBoard();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 2; y++) {
                Assertions.assertEquals(Player.BLACK, chessboard.getPieceAt(new Square(x, y)).getPlayer());
            }
        }
    }

    @Test
    public void fullboard_Pawns_areInCorrectSpot() {
        final ChessboardImpl chessboard = ChessboardImpl.startingBoard();
        for (int x = 0; x < 8; x++) {
            Assertions.assertEquals("P", chessboard.getPieceAt(new Square(x, 1)).getSymbol());
            Assertions.assertEquals("P", chessboard.getPieceAt(new Square(x, 6)).getSymbol());
        }
    }

    @Test
    public void fullboard_Rooks_areInCorrectSpot() {
        final ChessboardImpl chessboard = ChessboardImpl.startingBoard();
        Assertions.assertEquals("R", chessboard.getPieceAt(new Square(0, 0)).getSymbol());
        Assertions.assertEquals("R", chessboard.getPieceAt(new Square(7, 0)).getSymbol());
        Assertions.assertEquals("R", chessboard.getPieceAt(new Square(0, 7)).getSymbol());
        Assertions.assertEquals("R", chessboard.getPieceAt(new Square(7, 7)).getSymbol());
    }

    @Test
    public void fullboard_Knights_areInCorrectSpot() {
        final ChessboardImpl chessboard = ChessboardImpl.startingBoard();
        Assertions.assertEquals("K", chessboard.getPieceAt(new Square(1, 0)).getSymbol());
        Assertions.assertEquals("K", chessboard.getPieceAt(new Square(6, 0)).getSymbol());
        Assertions.assertEquals("K", chessboard.getPieceAt(new Square(1, 7)).getSymbol());
        Assertions.assertEquals("K", chessboard.getPieceAt(new Square(6, 7)).getSymbol());
    }

    @Test
    public void fullboard_Bishops_areInCorrectSpot() {
        final ChessboardImpl chessboard = ChessboardImpl.startingBoard();
        Assertions.assertEquals("B", chessboard.getPieceAt(new Square(2, 0)).getSymbol());
        Assertions.assertEquals("B", chessboard.getPieceAt(new Square(5, 0)).getSymbol());
        Assertions.assertEquals("B", chessboard.getPieceAt(new Square(2, 7)).getSymbol());
        Assertions.assertEquals("B", chessboard.getPieceAt(new Square(5, 7)).getSymbol());
    }

    @Test
    public void fullboard_Kings_areInCorrectSpot() {
        final ChessboardImpl chessboard = ChessboardImpl.startingBoard();
        Assertions.assertEquals(new King(Player.BLACK, new Square(4, 0)), chessboard.getPieceAt(new Square(4, 0)));
        Assertions.assertEquals(new King(Player.WHITE, new Square(4, 7)), chessboard.getPieceAt(new Square(4, 7)));
    }

    @Test
    public void fullboard_Queens_areInCorrectSpot() {
        final ChessboardImpl chessboard = ChessboardImpl.startingBoard();
        Assertions.assertEquals(new Queen(Player.BLACK, new Square(3, 0)), chessboard.getPieceAt(new Square(3, 0)));
        Assertions.assertEquals(new Queen(Player.WHITE, new Square(3, 7)), chessboard.getPieceAt(new Square(3, 7)));
    }
}
