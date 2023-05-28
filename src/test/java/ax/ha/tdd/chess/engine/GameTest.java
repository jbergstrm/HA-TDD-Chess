package ax.ha.tdd.chess.engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public class GameTest {

    @Test
    public void testChangePlayerAfterSuccessfulMove() {
        Game game = new GameImpl();
        assertEquals(game.getPlayerToMove(), Player.WHITE);

        String a2a4 = "a2-a4";
        game.move(a2a4);
        assertEquals(game.getLastMoveResult(), String.format("Last move was successful: %s", a2a4));
        assertEquals(game.getPlayerToMove(), Player.BLACK);

        String a7a6 = "a7-a6";
        game.move(a7a6);
        assertEquals(game.getLastMoveResult(), String.format("Last move was successful: %s", a7a6));
        assertEquals(game.getPlayerToMove(), Player.WHITE);
    }

    @Test
    public void testInvalidInput() {
        Game game = new GameImpl();
        String random = "dpfsoujindspuijhdsfoijn";
        game.move(random);
        assertEquals(game.getLastMoveResult(), String.format("Last move was unsuccessful: %s", random));

        String a0a1 = "a0-a1";
        game.move(a0a1);
        assertEquals(game.getLastMoveResult(), String.format("Last move was unsuccessful: %s", a0a1));

        String a9a8 = "a9-a8";
        game.move(a9a8);
        assertEquals(game.getLastMoveResult(), String.format("Last move was unsuccessful: %s", a9a8));

        String i0k1 = "i0-k1";
        game.move(i0k1);
        assertEquals(game.getLastMoveResult(), String.format("Last move was unsuccessful: %s", i0k1));
    }

    @Test
    public void testQueenSideCastling() {
        Game game = new GameImpl();
        game.move("b1-c3");
        game.move("a7-a6");
        game.move("b2-b3");
        game.move("b7-b6");
        game.move("c1-a3");
        game.move("e7-e6");
        game.move("e2-e3");
        game.move("b6-b5");
        game.move("d1-e2");
        game.move("a8-a7");
        game.move("O-O-O");
        assertEquals(game.getLastMoveResult(), String.format("Last move was successful: %s", "O-O-O"));
    }

    @Test
    public void testKingSideCastling() {
        Game game = new GameImpl();
        game.move("e2-e3");
        game.move("a7-a6");
        game.move("f1-e2");
        game.move("a6-a5");
        game.move("g1-h3");
        game.move("a5-a4");
        game.move("O-O");
        assertEquals(game.getLastMoveResult(), String.format("Last move was successful: %s", "O-O"));
    }

    @Test
    public void testGameEndsAfterCheckmate() {
        Game game = new GameImpl();
        game.move("f2-f4");
        game.move("e7-e5");
        game.move("g2-g4");
        game.move("d8-h4");
        assertEquals(game.getWinningState(), String.format("Player %s has won!", Player.BLACK.name()));
    }

    @Test
    public void testWhiteKingIsCheck() {
        Game game = new GameImpl();
        game.move("d2-d3");
        game.move("e7-e6");
        game.move("e2-e3");
        game.move("f8-b4");
        assertEquals(game.getWinningState(), String.format("Player %s king is in CHECK by %s.",
                Player.WHITE.name(), Player.BLACK.name()));
    }
}
