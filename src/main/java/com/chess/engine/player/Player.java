package com.chess.engine.player;


import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;

import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.King;

import com.chess.engine.board.Move;


public abstract class Player 
{
    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves; 
    
    Player( final Board board,
            final Collection<Move> legalMoves,
            final Collection<Move> opponentsMoves)
    {
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;  
    }

    private King establishKing()
    {
        for(final Piece piece : getActivePieces())
        {
            if(piece.getPieceType().isKing())
            {
                return (King) piece;
            }
        }
        throw new RuntimeException("Should not reach here! Not a valid board!!");
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
}
