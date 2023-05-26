package ax.ha.tdd.chess.engine.pieces;

import ax.ha.tdd.chess.engine.Chessboard;
import ax.ha.tdd.chess.engine.Square;
import ax.ha.tdd.chess.engine.Player;

import java.util.Objects;

public abstract class ChessPieceBase implements ChessPiece {

    protected final Player player;
    protected final PieceType pieceType;

    protected Square location;

    public ChessPieceBase(final PieceType pieceType, final Player player,
                          final Square location) {
        this.pieceType = pieceType;
        this.player = player;
        this.location = location;
    }

    public abstract String getSymbol();

    public PieceType getPieceType() {
        return pieceType;
    }

    public Player getPlayer() {
        return player;
    }

    public Square getLocation() {
        return location;
    }

    @Override
    public int getDirectionMultiplier() {
        return player == Player.BLACK ? 1 : -1;
    }

    /**
     * @return true if piece is still on the starting square
     */
    public abstract boolean startingLocation();

    /**
     * Suggestion of design:
     * Checks if the chessPiece can move to a certain destination.
     * I preferred to keep this logic tightly coupled to the pieces instead of the board,
     * since that makes the separation of logic easier.
     *
     * @param chessboard chessboard
     * @param destination destination
     * @return true if piece can move to the destination
     */
    public abstract boolean canMove(final Chessboard chessboard, final Square destination);

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPieceBase that = (ChessPieceBase) o;
        return player == that.player && pieceType == that.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, pieceType);
    }

    @Override
    public String toString() {
        return getPlayer().name() + " " + getClass().getSimpleName();
    }
}
