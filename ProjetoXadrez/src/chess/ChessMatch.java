package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import chess.pieces.King;
import chess.pieces.Rook;
import tabuleiro.Position;
import tabuleiro.peça;
import tabuleiro.tabuleiro;

public class ChessMatch {

	private tabuleiro Tabuleiro;
	private int turn;
	private Color currentPlayer;
	private boolean check = false;

	private List<peça> piecesOnTheBoard = new ArrayList<>();
	private List<peça> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		Tabuleiro = new tabuleiro(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean getCheck() {
		return check;
	}
	
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[Tabuleiro.getRows()][Tabuleiro.getColumns()];
		for (int i = 0; i < Tabuleiro.getRows(); i++) {
			for (int j = 0; j < Tabuleiro.getColumns(); j++) {
				mat[i][j] = (ChessPiece) Tabuleiro.piece(i, j);
			}
		}
		return mat;
	}

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return Tabuleiro.piece(position).possibleMoves();

	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		peça capturedPiece = makeMove(source, target);
		
		if(testCheck(currentPlayer)) {
			undoMove(source,target,capturedPiece);
			throw new ChessException ("VOCE NAO PODE SE COLOCAR EM CHEQUE");
		}
		
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		nextTurn();
		return (ChessPiece) capturedPiece;
	}

	private peça makeMove(Position source, Position target) {
		peça p = Tabuleiro.removePiece(source);
		peça capturedPiece = Tabuleiro.removePiece(target);
		Tabuleiro.placePiece(p, target);

		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}

		return capturedPiece;
	}

	private void undoMove(Position source, Position target, peça capturedPiece) {
		peça p = Tabuleiro.removePiece(target);
		Tabuleiro.placePiece(p, source);

		if (capturedPiece != null) {
			Tabuleiro.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}

	private void validateSourcePosition(Position position) {
		if (!Tabuleiro.thereIsAPiece(position)) {
			throw new ChessException("NAO HA UMA PECA NESSA POSICAO");
		}

		if (currentPlayer != ((ChessPiece) Tabuleiro.piece(position)).getColor()) {
			throw new ChessException("A PECA ESCOLHIDA NAO EH SUA");
		}

		if (!Tabuleiro.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("NAO EH POSSIVEL REALIZAR ESTES MOVIMENTOS NA PECA SELECIONADA");
		}
	}

	private void validateTargetPosition(Position source, Position target) {
		if (!Tabuleiro.piece(source).possibleMove(target)) {
			throw new ChessException("A PECA ESCOLHIDA NAO PODE SE MOVER PARA A POSICAO ESCOLHIDA!!");
		}
	}

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private ChessPiece king(Color color) {
		List<peça> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		for (peça p : list) {
			if (p instanceof King) {
				return (ChessPiece) p;
			}
		}
		throw new IllegalStateException("NAO TEM REI" + color + "NO TABULEIRO");
	}

	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<peça> opponentPieces = piecesOnTheBoard.stream()
				.filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());
		for (peça p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		Tabuleiro.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}

	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(Tabuleiro, Color.WHITE));
		placeNewPiece('c', 2, new Rook(Tabuleiro, Color.WHITE));
		placeNewPiece('d', 2, new Rook(Tabuleiro, Color.WHITE));
		placeNewPiece('e', 2, new Rook(Tabuleiro, Color.WHITE));
		placeNewPiece('e', 1, new Rook(Tabuleiro, Color.WHITE));
		placeNewPiece('d', 1, new King(Tabuleiro, Color.WHITE));

		placeNewPiece('c', 7, new Rook(Tabuleiro, Color.BLACK));
		placeNewPiece('c', 8, new Rook(Tabuleiro, Color.BLACK));
		placeNewPiece('d', 7, new Rook(Tabuleiro, Color.BLACK));
		placeNewPiece('e', 7, new Rook(Tabuleiro, Color.BLACK));
		placeNewPiece('e', 8, new Rook(Tabuleiro, Color.BLACK));
		placeNewPiece('d', 8, new King(Tabuleiro, Color.BLACK));

	}
}
