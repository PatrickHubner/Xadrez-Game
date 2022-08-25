package chess;

import java.util.ArrayList;
import java.util.List;

import chess.pieces.King;
import chess.pieces.Rook;
import tabuleiro.Position;
import tabuleiro.peça;
import tabuleiro.tabuleiro;

public class ChessMatch {
	
	private tabuleiro Tabuleiro;
	private int turn;
	private Color currentPlayer;
	
	private List<peça> piecesOnTheBoard = new ArrayList<>();
	private List<peça> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		Tabuleiro = new tabuleiro(8,8);
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
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[Tabuleiro.getRows()][Tabuleiro.getColumns()];
		for (int i = 0; i<Tabuleiro.getRows(); i++) {
			for(int j = 0; j<Tabuleiro.getColumns(); j++) {
				mat[i][j] = (ChessPiece) Tabuleiro.piece(i,j);
			}
		}
		return mat;
	}
	
	public boolean [][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return Tabuleiro.piece(position).possibleMoves();
		
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source,target);
		peça capturedPiece = makeMove(source, target);
		nextTurn();
		return  (ChessPiece)capturedPiece;
	}
	
	private peça makeMove(Position source, Position target) {
		peça p = Tabuleiro.removePiece(source);
		peça capturedPiece = Tabuleiro.removePiece(target);
		Tabuleiro.placePiece(p, target);
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece;
		}
	
	private void validateSourcePosition(Position position) {
		if(!Tabuleiro.thereIsAPiece(position)) {
			throw new ChessException("NAO HA UMA PECA NESSA POSICAO");
		}
		
		if(currentPlayer != ((ChessPiece)Tabuleiro.piece(position)).getColor()) {
			throw new ChessException("A PECA ESCOLHIDA NAO EH SUA");
		}
		
		if(!Tabuleiro.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("NAO EH POSSIVEL REALIZAR ESTES MOVIMENTOS NA PECA SELECIONADA");
		}
	}
	
	private void validateTargetPosition(Position source,Position target) {
		if(!Tabuleiro.piece(source).possibleMove(target)) {
			throw new ChessException("A PECA ESCOLHIDA NAO PODE SE MOVER PARA A POSICAO ESCOLHIDA!!");
		}
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
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
