package ax.ha.tdd.chess.engine;

public class GameImpl implements Game {

    private final GameService service;

    public GameImpl() {
        this.service = new GameServiceImpl();
    }

    @Override
    public Player getPlayerToMove() {
        return service.getPlayerToMove();
    }

    @Override
    public Chessboard getBoard() {
        return service.getChessboard();
    }

    @Override
    public String getLastMoveResult() {
        if (!service.isGameStarted()) {
            return !service.isMoveSuccessful()
                    ? String.format("Last move was unsuccessful: %s", service.getLatestMove())
                    : "";
        }

        return service.isMoveSuccessful()
                ? String.format("Last move was successful: %s", service.getLatestMove())
                : String.format("Last move was unsuccessful: %s", service.getLatestMove());
    }

    @Override
    public String getWinningState() {
        if (service.isGameOver()) {
            return String.format("Player %s has won!", service.getPlayerToMove().name());
        }

        if (service.isCheck(Player.WHITE) && service.isCheck(Player.BLACK)) {
            return String.format("Player %s and Player %s are in CHECK position.",
                    Player.WHITE.name(), Player.BLACK.name());
        }

        if (service.isCheck(Player.WHITE)) {
            return String.format("Player %s king is in CHECK by %s.",
                    Player.WHITE.name(), Player.BLACK.name());
        }

        if (service.isCheck(Player.BLACK)) {
            return String.format("Player %s king is in CHECK by %s.",
                    Player.BLACK.name(), Player.WHITE.name());
        }

        if (!service.isGameStarted()) {
            return "Game hasn't begun";
        }

        return WinningState.PLAYING.name();
    }

    @Override
    public void move(final String move) {
        service.setLatestMove(move);

        if (service.isGameOver()) {
            System.out.println("TODO: GAME-OVER");
            return;
        }

        if (service.isMoveInvalid(move)) {
            System.out.println("TODO: INVALID-MOVE");
            return;
        }

        if (service.move(move)) {
            service.setMoveSuccessful(true);
        }
        else {
            service.setMoveSuccessful(false);

            System.out.println("TODO: UNSUCCESSFUL-MOVE");
            return;
        }

        if (service.isGameOver()) {
            System.out.println("TODO: GAME-OVER");
            return;
        }

        service.updatePlayerToMove();

        service.setGameStarted(true);
        System.out.println("Player perform move: " + move);
    }
}
