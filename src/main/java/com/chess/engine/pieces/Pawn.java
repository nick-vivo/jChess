package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;
import com.google.common.collect.ImmutableList;;

public class Pawn extends Piece
{

    private final static int[] CANDIDATE_MOVE_COORDINATES = {8, 16, 7, 9};

    public Pawn(final Alliance pieceAlliance, final int piecePosition) 
    {
        super(PieceType.PAWN, piecePosition, pieceAlliance);
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
                //TODO more to do here
                legalMoves.add( new MajorMove(board, this, candidateDestinationCoordinate) );
            } 
            else if(currentCandidateOffset == 16 && this.isFirstMove() && (
                     (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) ||
                     (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite()) ) 
            )
            {
                //TODO по туториалу  final int behindCandidateDestinationCoordinate = this.piecePosition +  this.getPieceAlliance().getDirection() * 8, но я думаю надо поставить 16
                final int behindCandidateDestinationCoordinate = this.piecePosition +  this.getPieceAlliance().getDirection() * 8;
                if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() && !board.getTile(candidateDestinationCoordinate).isTileOccupied())
                {
                    legalMoves.add(new MajorMove(board, this, behindCandidateDestinationCoordinate));
                }
            }
            else if(currentCandidateOffset == 7 && !(
            (BoardUtils.EIGHT_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()) ||
            (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()) ) )
            {
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied())
                {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance())
                    {
                        //TODO more to do here
                        legalMoves.add( new AttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate ) );
                    }
                }
            }   
            else if(currentCandidateOffset == 9 && !(
            (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()) ||
            (BoardUtils.EIGHT_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()) ) )
            {
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied())
                {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance())
                    {
                        //TODO more to do here
                        legalMoves.add( new AttackMove(board, this, candidateDestinationCoordinate, pieceOnCandidate ) );
                    }
                }

            }

        }
        
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public String toString()
    {
        return Piece.PieceType.PAWN.toString();
    }
    
}