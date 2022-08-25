package tabuleiro;

public abstract class peça {
	protected Position position;
	private tabuleiro Tabuleiro;

	public peça(tabuleiro tabuleiro) {
		Tabuleiro = tabuleiro;
		position = null;
	}

	protected tabuleiro getTabuleiro() {
		return Tabuleiro;
	}

	public abstract boolean[][] possibleMoves();

	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}

	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

}
