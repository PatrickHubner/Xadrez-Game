package chess;

import tabuleiro.Position;
import tabuleiro.peça;
import tabuleiro.tabuleiro;

public abstract class ChessPiece extends peça{
	
	private Color color;
	
	
	public ChessPiece(tabuleiro tabuleiro, Color color) {
		super(tabuleiro);
		this.color = color;
	}


	public Color getColor() {
		return color;
	}

	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getTabuleiro().piece(position);
		return p != null && p.getColor() != color;
	}
}