package tabuleiro;

public class tabuleiro {
	private int rows;
	private int columns;
	private peça[][] pieces;
	
	
	public tabuleiro(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw  new BoardException("ERRO: EH NECESSARIO QUE HAJA AO MENOS UMA LINHA E UMA COLUNA");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new peça[rows][columns];
	}
	

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	
	public peça piece(int row, int column) {
		if(!positionExists(row, column)) {
			throw new BoardException("Posição nao esta no tabuleiro");
		}
		return pieces[row][column];
	}
	
	public peça piece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Posição nao esta no tabuleiro");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(peça piece, Position position) {
		if(thereIsAPiece(position)) {
			throw new BoardException("HA UMA PEÇA NESSA POSICAO"+ position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public peça removePiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("POSICAO NAO ESTA NO TABULEIRO");
		}
		if(piece(position) == null) {
			return null;
		}
		peça aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}
	
	private boolean positionExists(int row, int Column) {
		return row >= 0 && row < rows && Column >= 0 && Column < columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardException("Posição nao esta no tabuleiro");
		}
		return piece(position) != null;
	}
}
