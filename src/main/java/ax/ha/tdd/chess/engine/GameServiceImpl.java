package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.ChessPieceMovingUtils;
import ax.ha.tdd.chess.engine.pieces.PieceType;
import ax.ha.tdd.chess.engine.pieces.impl.King;
import ax.ha.tdd.chess.engine.pieces.impl.Rook;

/**
 * TODO: What does this class do
 *
 * @author Joakim Bergström
 */
public class GameServiceImpl implements GameService {

    private final ChessboardImpl chessboard;

    /** The current player that can move */
    private Player playerToMove;

    /** Stores the latest move that was tried */
    private String latestMove;

    /** Was the latest move a success */
    private boolean successful;

    /** Has the game started */
    private boolean started;

    private static final String VALID_MOVE_REGEX = "^[a-hA-H][1-8]-[a-hA-H][1-8]$";
    private static final String VALID_MOVE_KING_SIDE_REGEX = "^O-O$";
    private static final String VALID_MOVE_QUEEN_SIDE_REGEX = "^O-O-O$";

    public GameServiceImpl() {
        this.started = false;
        this.successful = false;
        this.playerToMove = Player.WHITE;
        this.chessboard = ChessboardImpl.startingBoard();
    }

    @Override
    public Player getPlayerToMove() {
        return playerToMove;
    }

    @Override
    public Chessboard getChessboard() {
        return chessboard;
    }

    @Override
    public boolean move(final String move) {
        if (move.matches(VALID_MOVE_REGEX)) {
            final Square from = new Square(move.split("-")[0].toLowerCase());
            final Square to = new Square(move.split("-")[1].toLowerCase());

            return normalMove(from, to);
        }

        if (move.matches(VALID_MOVE_KING_SIDE_REGEX)) {
            final King king = (King) chessboard.getPieceAt(chessboard.getPieceLocation(PieceType.KING, playerToMove));
            final Rook rook = (Rook) chessboard.getPieceAt(7, playerToMove == Player.BLACK ? 0 : 7);

            return castlingMove(king, rook, true);
        }

        if (move.matches(VALID_MOVE_QUEEN_SIDE_REGEX)) {
            final King king = (King) chessboard.getPieceAt(chessboard.getPieceLocation(PieceType.KING, playerToMove));
            final Rook rook = (Rook) chessboard.getPieceAt(0, playerToMove == Player.BLACK ? 0 : 7);

            return castlingMove(king, rook, false);
        }

        return false;
    }

    @Override
    public boolean isMoveInvalid(final String move) {
        return !(move.matches(VALID_MOVE_REGEX)
                || move.matches(VALID_MOVE_KING_SIDE_REGEX)
                || move.matches(VALID_MOVE_QUEEN_SIDE_REGEX));
    }

    @Override
    public boolean isGameOver() {
        return WinningStateUtils.checkState(chessboard, Player.WHITE) == WinningState.CHECKMATE
                || WinningStateUtils.checkState(chessboard, Player.BLACK) == WinningState.CHECKMATE;
    }

    @Override
    public boolean isCheck(final Player player) {
        return WinningStateUtils.checkState(chessboard, player) == WinningState.CHECK;
    }

    @Override
    public void updatePlayerToMove() {
        this.playerToMove = this.playerToMove == Player.WHITE ? Player.BLACK : Player.WHITE;
    }

    @Override
    public void setGameStarted(boolean started) {
        this.started = started;
    }

    @Override
    public boolean isGameStarted() {
        return started;
    }

    @Override
    public void setMoveSuccessful(boolean successful) {
        this.successful = successful;
    }

    @Override
    public boolean isMoveSuccessful() {
        return successful;
    }

    @Override
    public void setLatestMove(final String move) {
        this.latestMove = move;
    }

    @Override
    public String getLatestMove() {
        return latestMove;
    }

    private boolean normalMove(final Square from, final Square to) {
        final ChessPiece chessPiece = chessboard.getPieceAt(from);

        if (chessPiece == null || chessPiece.getPlayer() != playerToMove) {
            return false;
        }

       if (!chessPiece.canMove(chessboard, to)) {
           return false;
       }

       chessboard.removePieceAt(from);

       if (chessboard.getPieceAt(to) != null) {
           chessboard.removePieceAt(to);
       }

       chessPiece.setLocation(to);
       chessboard.addPiece(chessPiece);
       chessPiece.isMoved(true);

        return true;
    }

    private boolean castlingMove(final King king, final Rook rook, final boolean isKingSide) {
        if (king == null || rook == null) {
            return false;
        }

        if (!ChessPieceMovingUtils.kingCastling(chessboard, king, rook)) {
            return false;
        }

        chessboard.removePieceAt(king.getLocation());
        chessboard.removePieceAt(rook.getLocation());

        if (isKingSide) {
            king.setLocation(new Square(rook.getLocation().getX() - 1, rook.getLocation().getY()));
            rook.setLocation(new Square(king.getLocation().getX() - 1, king.getLocation().getY()));
        }
        else {
            king.setLocation(new Square(rook.getLocation().getX() + 2, rook.getLocation().getY()));
            rook.setLocation(new Square(king.getLocation().getX() + 1, king.getLocation().getY()));
        }

        chessboard.addPiece(king);
        chessboard.addPiece(rook);

        king.isMoved(true);
        rook.isMoved(true);

        return true;
    }
}
