package com.chess.engine.board;

public class BoardUtils {
    
    private BoardUtils()
    {
        throw new RuntimeException("You cannot instance me!");
    }

    public static boolean isValidTileCoordinate(int coordinate)
    {
        return coordinate >= 0 && coordinate < 64;
    }
}
