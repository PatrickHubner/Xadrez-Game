package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import tabuleiro.Position;
import tabuleiro.tabuleiro;

public class Pawn extends ChessPiece{

	public Pawn(tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getRows()][getTabuleiro().getColumns()];
		Position p = new Position(0,0);
		
		
		if(getColor() == Color.WHITE){
			p.setValues(position.getRow() - 1,  position.getColumn());
			if(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 2,  position.getColumn());
			Position p2 = new Position(position.getRow() - 1,  position.getColumn());
			if(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p) && getTabuleiro().positionExists(p2) && !getTabuleiro().thereIsAPiece(p2)  && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 1,  position.getColumn() - 1);
			if(getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 1,  position.getColumn()+1);
			if(getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}		
		}
		else {
			p.setValues(position.getRow() + 1,  position.getColumn());
			if(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 2,  position.getColumn());
			Position p2 = new Position(position.getRow() + 1,  position.getColumn());
			if(getTabuleiro().positionExists(p) && !getTabuleiro().thereIsAPiece(p) && getTabuleiro().positionExists(p2) && !getTabuleiro().thereIsAPiece(p2)  && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 1,  position.getColumn() - 1);
			if(getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 1,  position.getColumn()+1);
			if(getTabuleiro().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}		
		}
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}

}
