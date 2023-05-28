package ax.ha.tdd.chess.engine;

/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public interface GameService {

    Player getPlayerToMove();

    Chessboard getChessboard();

    boolean move(String move);

    boolean isMoveInvalid(String move);

    boolean isGameOver();

    boolean isCheck(Player player);

    void updatePlayerToMove();

    void setGameStarted(boolean started);

    boolean isGameStarted();

    void setMoveSuccessful(boolean successful);

    boolean isMoveSuccessful();

    void setLatestMove(String move);

    String getLatestMove();
}
