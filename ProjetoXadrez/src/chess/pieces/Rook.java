package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import tabuleiro.Position;
import tabuleiro.tabuleiro;

public class Rook extends ChessPiece{

	public Rook(tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
	}

	@Override
	public String toString() {
		return "R";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getRows()][getTabuleiro().getColumns()];
		Position p = new Position(0,0);
		//acima
		p.setValues(position.getRow() - 1, position.getColumn());
		while(getTabuleiro().positionExists(p)&& !getTabuleiro().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		if(getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//esquerda
		p.setValues(position.getRow(), position.getColumn() - 1);
		while(getTabuleiro().positionExists(p)&& !getTabuleiro().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if(getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//direita
		p.setValues(position.getRow(), position.getColumn()+1);
		while(getTabuleiro().positionExists(p)&& !getTabuleiro().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if(getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//baixo
		p.setValues(position.getRow() + 1, position.getColumn());
		while(getTabuleiro().positionExists(p)&& !getTabuleiro().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if(getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}
}
