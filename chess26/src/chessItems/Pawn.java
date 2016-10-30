/**
 * 
 */
package chessItems;

/**
 * @author Matthew Ya
 * @author Taehee Lee
 */
public class Pawn implements ChessPiece {

	/**
	 * 
	 */
	private boolean white;
	private boolean promotion;
	private boolean firstMove;
	public Pawn(boolean white) {
		// TODO Auto-generated constructor stub
		this.white = white;
		this.promotion = false;
		this.firstMove = true;
	}

	/* (non-Javadoc)
	 * @see chessItems.ChessPiece#isLegal(int, int, int, int, ChessPiece[][])
	 */
	@Override
	public boolean isLegal(int fstart, int rstart, int fend, int rend, ChessPiece board[][]) throws ArrayIndexOutOfBoundsException{
		// TODO Auto-generated method stub
		
		//can not move pawn backwards
		if((this.white && rend > rstart) || (!this.white && rstart > rend)){
			return false;
		}
		
		//checks if pawn is moving one or two spaces foward.
		//DELETE ASAP: RESTRUCTURE IF STATEMENT TO START CHECKING IF [REND][FEND] IS NULL OR CONTAINS A PIECE
		if(fstart == fend){
			//change this if statement
			if((Math.abs(rstart - rend) == 2) && this.firstMove && (board[rstart + (rend-rstart)][fend] == null) && (board[rend][fend] == null)){
				board[rend][fend] = board[rstart][fstart];
				board[rstart][fstart] = null;
			}else if(Math.abs(rstart - rend) == 1 && board[rend][fend] == null){
				board[rend][fend] = board[rstart][fstart];
				board[rstart][fstart] = null;
			}else{
				return false;
			}
		}else if(Math.abs(fstart-fend) == 1 && Math.abs(rstart-rend) == 1 ){
			if(board[rend][fend] == null){
				if(board[rend+(rstart-rend)][fend] != null && (board[rend+(rstart-rend)][fend].toString().charAt(1) == 'p')){	//is not null and is a pawn
					if(board[rend+(rstart-rend)][fend].isWhite() != board[rstart][fstart].isWhite()){
						if(board[rend+(rstart-rend)][fend].isWhite() && rstart == 4){
							board[rend][fend] = board[rstart][fstart];
							board[rstart][fstart] = null;
							board[rend+(rstart-rend)][fend] = null;
						}else if(!board[rend+(rstart-rend)][fend].isWhite() && rstart == 3){
							board[rend][fend] = board[rstart][fstart];
							board[rstart][fstart] = null;
							board[rend+(rstart-rend)][fend] = null;
						}else{
							return false;
						}
					}else{
						return false;
					}
				}else{
					return false;
				}
			}else{
				if(board[rstart][fstart].isWhite() != board[rend][fend].isWhite()){
					board[rend][fend] = board[rstart][fstart];
					board[rstart][fstart] = null;
				}else{
					return false;
				}
			}
		}else{
			return false;
		}
		//at this point the move was successful
		
		//checks if it is the first move and adjusts value to false after first move
		if(this.firstMove){
			this.firstMove = false;
		}
		//checks for promotion at the end
		if((this.white && rend == 0) || (!this.white && rend == 7)){
				this.promotion = true;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see chessItems.ChessPiece#isWhite()
	 */
	@Override
	public boolean isWhite() {
		// TODO Auto-generated method stub
		return this.white;
	}
	
	/* (non-Javadoc)
	 * @see chessItems.ChessPiece#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(this.white){
			return "wp";
		}
		return "bp";
	}

}
