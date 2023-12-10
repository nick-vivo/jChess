package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.MajorMove;;

public class Pawn extends Piece
{

    private final static int[] CANDIDATE_MOVE_COORDINATES = {8, 16, 7, 9};

    Pawn(final int piecePosition, final Alliance pieceAlliance) 
    {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) 
    {
        final List<Move> legalMoves = new ArrayList<>();
        
        for( final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES)
        {
            final int candidateDestinationCoordinate = this.piecePosition +  this.pieceAlliance.getDirection() * currentCandidateOffset;

            if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
            {
                continue;
            }

            if( currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied())
            {
                legalMoves.add( new MajorMove(board, this, candidateDestinationCoordinate) );
            } else if(currentCandidateOffset == 16 && this.isFirstMove() && (
                     (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) ||
                     (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite()) ) )
            {
                final int behindCandidateDestinationCoordinate = this.piecePosition +  this.getPieceAlliance().getDirection() * currentCandidateOffset;
                if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() && !board.getTile(candidateDestinationCoordinate).isTileOccupied())
                {
                    legalMoves.add(new MajorMove(board, this, behindCandidateDestinationCoordinate));
                }
            }
                    


        }
        
        return legalMoves;
    }
    
}