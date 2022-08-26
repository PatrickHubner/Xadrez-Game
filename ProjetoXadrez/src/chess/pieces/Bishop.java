package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import tabuleiro.Position;
import tabuleiro.tabuleiro;

public class Bishop extends ChessPiece{

	public Bishop(tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
	}
	

	@Override
	public String toString() {
		return "B";
	}


	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getRows()][getTabuleiro().getColumns()];
		Position p = new Position(0,0);
		//noroeste
		p.setValues(position.getRow() - 1, position.getColumn()-1);
		while(getTabuleiro().positionExists(p)&& !getTabuleiro().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}
		if(getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//nordeste
		p.setValues(position.getRow()-1, position.getColumn() + 1);
		while(getTabuleiro().positionExists(p)&& !getTabuleiro().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}
		if(getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//sudeste
		p.setValues(position.getRow()+1, position.getColumn()+1);
		while(getTabuleiro().positionExists(p)&& !getTabuleiro().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		if(getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//sudoeste
		p.setValues(position.getRow() + 1, position.getColumn()-1);
		while(getTabuleiro().positionExists(p)&& !getTabuleiro().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}
		if(getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}

}
