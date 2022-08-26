package chess.pieces;

import chess.ChessPiece;
import chess.Color;
import tabuleiro.Position;
import tabuleiro.tabuleiro;

public class Knight extends ChessPiece {

	public Knight(tabuleiro tabuleiro, Color color) {
		super(tabuleiro, color);
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getTabuleiro().piece(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public String toString() {
		return "C";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabuleiro().getRows()][getTabuleiro().getColumns()];

		Position p = new Position(0, 0);

		// acima
		p.setValues(position.getRow() - 1, position.getColumn() - 2);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// abaixo
		p.setValues(position.getRow() - 2, position.getColumn() - 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// esquerda
		p.setValues(position.getRow() - 2, position.getColumn() + 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// direita
		p.setValues(position.getRow() - 1, position.getColumn() + 2);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// noroeste
		p.setValues(position.getRow() + 1, position.getColumn() + 2);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// nordeste
		p.setValues(position.getRow() + 2, position.getColumn() + 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// sudoeste
		p.setValues(position.getRow() + 2, position.getColumn() - 1);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// sudeste
		p.setValues(position.getRow() + 1, position.getColumn() - 2);
		if (getTabuleiro().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}

}
