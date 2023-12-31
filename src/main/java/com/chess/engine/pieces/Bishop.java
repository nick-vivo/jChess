package com.chess.engine.pieces;


import java.util.Collection;

import java.util.List;
import java.util.ArrayList;

import com.google.common.collect.ImmutableList;

import com.chess.engine.Alliance;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;
import com.chess.engine.board.Tile;


public class Bishop extends Piece{
 
    private static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9, -7, 7, 9};
    
    public Bishop(final Alliance pieceAlliance, final int piecePosition)
    {
        super(PieceType.BISHOP, piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board)
    {
        final List<Move> legalMoves = new ArrayList<>();

        for(final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES)
        {
            int candidateDestinationCoordinate = this.piecePosition;

            while( BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate) )
            {
                if( isFirstColumnExclusion(this.piecePosition, candidateCoordinateOffset) ||
                isEightColumnExclusion(this.piecePosition, candidateCoordinateOffset) )
                {
                    break;
                }

                candidateDestinationCoordinate += candidateCoordinateOffset;

                if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate))
                {
                
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                    if (!candidateDestinationTile.isTileOccupied()) 
                    {
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    } 
                    else 
                    {
                        final Piece pieceAtDestionation = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestionation.getPieceAlliance();

                        if (this.pieceAlliance != pieceAlliance) 
                        {
                            legalMoves.add(
                                    new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestionation));
                        }
                        break;
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public String toString()
    {
        return Piece.PieceType.BISHOP.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset)
    {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == 7);
    }

    private static boolean isEightColumnExclusion(final int currentPosition, final int candidateOffset)
    {
        return BoardUtils.EIGHT_COLUMN[currentPosition] && (candidateOffset == 9 || candidateOffset == -7);
    }

}