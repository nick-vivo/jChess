package com.chess.engine.player;

import java.util.Collection;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import com.chess.engine.pieces.Piece;

public class BlackPlayer extends Player
{
	public BlackPlayer( Board board, 
                        Collection<Move> whiteStandartLegalMoves,
			            Collection<Move> blackStandartLegalMoves) 
    {
		super(board, blackStandartLegalMoves, whiteStandartLegalMoves);
	}
    
	@Override
	public Collection<Piece> getActivePieces()
	{
		return this.board.getBlackPieces(); 
	}
}
