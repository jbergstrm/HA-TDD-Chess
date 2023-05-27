package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.console.ChessboardWriter;
import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.ChessboardImpl;
import ax.ha.tdd.chess.engine.Game;
import ax.ha.tdd.chess.engine.GameImpl;
import ax.ha.tdd.chess.engine.Player;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.pieces.impl.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnTests {

    //If you use exceptions for your illegal moves, change this test to expect an exception instead.
    @Test
    public void testMoveMoreThanTwoSquaresAtStartShouldBeIllegal(){
        //Arrange
        Game game = new GameImpl();

        //Act
        game.move("e2-e5"); //if you use real chess notation in your implementation, use simply "e5"

        //Assert
        assertEquals(Player.WHITE, game.getPlayerToMove());
        ChessPiece piece = game.getBoard().getPieceAt(new Square("e2"));
        assertEquals(Player.WHITE, piece.getPlayer());
        assertEquals(PieceType.PAWN, piece.getPieceType());


        //For debugging, you can print the board to console, or if you want
        //to implement a command line interface for the game
        System.out.println(new ChessboardWriter().print(game.getBoard()));

    }

    @Test
    public void testMoveTwoSquareAtStartShouldBeLegal() {
        Chessboard chessboard = new ChessboardImpl();
        Pawn e2 = new Pawn(Player.WHITE, new Square("e2"));
        assertTrue(e2.canMove(chessboard, new Square("e4")));
    }

    @Test
    public void testMoveTwoSquaresNotAtStartShouldBeIllegal() {
        Chessboard chessboard = new ChessboardImpl();
        Pawn c3 = new Pawn(Player.WHITE, new Square("c3"));
        assertFalse(c3.canMove(chessboard, new Square("c5")));
    }

    @Test
    public void testWhitePawnForwardOneStepUnblocked(){
        Chessboard chessboard = new ChessboardImpl();
        Pawn e2 = new Pawn(Player.WHITE, new Square("e2"));
        assertTrue(e2.canMove(chessboard, new Square("e3")));
    }

    @Test
    public void testBlackPawnForwardOneStepUnblocked() {
        Chessboard chessboard = new ChessboardImpl();
        Pawn e7 = new Pawn(Player.BLACK, new Square("e7"));
        assertTrue(e7.canMove(chessboard, new Square("e6")));
    }

    @Test
    public void testWhitePawnForwardOneStepBlocked() {
        Chessboard chessboard = new ChessboardImpl();
        Pawn e2 = new Pawn(Player.WHITE, new Square("e2"));
        Pawn e3 = new Pawn(Player.BLACK, new Square("e3"));
        chessboard.addPiece(e2);
        chessboard.addPiece(e3);
        assertFalse(e2.canMove(chessboard, new Square("e3")));
    }

    @Test
    public void testWhitePawnEatBlackPawn() {
        Chessboard chessboard = new ChessboardImpl();
        Pawn e4 = new Pawn(Player.WHITE, new Square("e4"));
        Pawn d5 = new Pawn(Player.BLACK, new Square("d5"));
        chessboard.addPiece(e4);
        chessboard.addPiece(d5);
        assertTrue(e4.canMove(chessboard, new Square("d5")));
    }

    @Test
    public void testMoveDiagonallyToEmptySquareShouldBeIllegal() {
        Chessboard chessboard = new ChessboardImpl();
        Pawn c3 = new Pawn(Player.WHITE, new Square("c3"));
        assertFalse(c3.canMove(chessboard, new Square("d4")));
    }
}
