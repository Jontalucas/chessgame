package boardgame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
			
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece [rows][columns];
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public Piece piece(int row, int column) {
		if(!positionExists(row,column)) {
			throw new BoardException("Position does not exist");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position pos) {
		if(!positionExists(pos)) {
			throw new BoardException("Position does not exist");
		}
		return pieces[pos.getRow()][pos.getColumn()];
	}
	
	public void placePiece(Piece piece, Position pos) {
		if(thereIsPiece(pos)) {
			throw new BoardException("There is already a piece on " + pos);
		}
		pieces[pos.getRow()][pos.getColumn()] = piece;
		piece.position = pos;
	}
	
	public Boolean positionExists(int row, int column) {
		return row >= 0 && row < this.rows && column >= 0 && column < this.columns;
	}
	
	public Boolean positionExists(Position pos) {
		return positionExists(pos.getRow(), pos.getColumn());
	}
	
	public Boolean thereIsPiece(Position pos) {
		if(!positionExists(pos)) {
			throw new BoardException("Position does not exist");
		}
		return piece(pos) != null;
	}
	
	public Piece removePiece(Position pos) {
		if(!positionExists(pos)) {
			throw new BoardException("Position does not exist");
		}
		if (piece(pos) == null) {
			return null;
		}
		Piece aux = piece(pos);
		aux.position = null;
		pieces[pos.getRow()][pos.getColumn()] = null;
		return aux;
	}
}
