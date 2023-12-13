package com.chess.engine.player;


import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import com.chess.engine.pieces.Piece;


public class WhitePlayer extends Player{

	public WhitePlayer(	final Board board, 
						final Collection<Move> whiteStandartLegalMoves,
						final Collection<Move> blackStandartLegalMoves)
	{
		super(board, whiteStandartLegalMoves, blackStandartLegalMoves);	
	}

	@Override
	public Collection<Piece> getActivePieces()
	{
		return this.board.getWhitePieces(); 
	}

	@Override
	public Alliance getAlliance()
	{
		return Alliance.BLACK;
	}

	@Override
	public Player getOpponent()
	{
		return this.board.blackPlayer();
	}
    
}
