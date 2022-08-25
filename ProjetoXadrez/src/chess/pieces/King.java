package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import tabuleiro.Position;
import tabuleiro.tabuleiro;

public class King extends ChessPiece{

	public King(tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
	}

	@Override
	public String toString() {
		return "K";
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getTabuleiro().piece(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getRows()][getTabuleiro().getColumns()];
		
		Position p = new Position(0,0);
		
		//acima
		p.setValues(position.getRow() - 1, position.getColumn());
		if(getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		//abaixo
		p.setValues(position.getRow() + 1, position.getColumn());
		if(getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//esquerda
		p.setValues(position.getRow(), position.getColumn()-1);
		if(getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//direita
		p.setValues(position.getRow(), position.getColumn()+1);
		if(getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//noroeste
		p.setValues(position.getRow()-1 , position.getColumn()-1);
		if(getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//nordeste
		p.setValues(position.getRow()-1, position.getColumn()+1);
		if(getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//sudoeste
		p.setValues(position.getRow()+1, position.getColumn()-1);
		if(getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//sudeste
		p.setValues(position.getRow()+1, position.getColumn()+1);
		if(getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}

	
}
