package ax.ha.tdd.chess.engine;

import ax.ha.tdd.chess.engine.pieces.ChessPiece;
import ax.ha.tdd.chess.engine.pieces.ChessPieceBase;
import ax.ha.tdd.chess.engine.pieces.ChessPieceStub;
import ax.ha.tdd.chess.engine.pieces.PieceType;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ChessboardImpl implements Chessboard {
    // This could just as easily be replaced with a List or Set,
    // since the ChessPieces right now keep track of their own location.
    // Feel free to change this however you like
    // [y][x]
    private final ChessPiece[][] board = new ChessPieceBase[8][8];

    public static ChessboardImpl startingBoard() {
        final ChessboardImpl chessboard = new ChessboardImpl();

        chessboard.withMirroredPiece(PieceType.PAWN, List.of(0,1,2,3,4,5,6,7), 1)
                .withMirroredPiece(PieceType.ROOK, List.of(0,7), 0)
                .withMirroredPiece(PieceType.KNIGHT, List.of(1,6), 0)
                .withMirroredPiece(PieceType.BISHOP, List.of(2,5), 0)
                .withMirroredPiece(PieceType.QUEEN, List.of(3), 0)
                .withMirroredPiece(PieceType.KING, List.of(4), 0);
        return chessboard;
    }

    @Override
    public ChessPiece getPieceAt(final int x, final int y) {
        return board[y][x];
    }

    @Override
    public ChessPiece getPieceAt(final Square square) {
        return getPieceAt(square.getX(), square.getY());
    }

    @Override
    public Square getPieceLocation(final PieceType pieceType, final Player player) {
        return Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(chessPiece -> chessPiece.getPieceType() == pieceType)
                .filter(chessPiece -> chessPiece.getPlayer() == player)
                .map(ChessPiece::getLocation)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addPiece(final ChessPiece chessPiece) {
        board[chessPiece.getLocation().getY()][chessPiece.getLocation().getX()] = chessPiece;
    }

    @Override
    public void removePieceAt(final Square square) {
        board[square.getY()][square.getX()] = null;
    }

    @Override
    public boolean isValidSquare(final Square square, final Player player) {
        final ChessPiece chessPiece = this.getPieceAt(square);
        return chessPiece == null
                || (chessPiece.getPieceType() != PieceType.KING && chessPiece.getPlayer() != player);
    }

    /**
     * Helper method to initialize chessboard with {@link ChessPieceStub}.
     * Basically mirrors all added pieces for both players.
     * When all pieces has been implemented, this should be replaced with the proper implementations.
     *
     * @param pieceType pieceType
     * @param xCoordinates xCoordinates
     * @param yCoordinate yCoordinateOffset
     * @return itself, like a builder pattern
     */
    private ChessboardImpl withMirroredPiece(final PieceType pieceType,
                                             final List<Integer> xCoordinates, final int yCoordinate) {
        xCoordinates.forEach(xCoordinate -> {
            addPiece(new ChessPieceStub(pieceType, Player.BLACK, new Square(xCoordinate, yCoordinate)));
            addPiece(new ChessPieceStub(pieceType, Player.WHITE, new Square(xCoordinate, 7 - yCoordinate)));
        });
        return this;
    }

    @Override
    public Iterator<ChessPiece[]> iterator() {
        return List.of(board).iterator();
    }
}
