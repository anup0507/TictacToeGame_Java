package com.example.lldpracticals.anup.java.tictactoe.Strategies.BotPlayingStrategies;

import com.example.lldpracticals.anup.java.tictactoe.Model.Board;
import com.example.lldpracticals.anup.java.tictactoe.Model.Cell;
import com.example.lldpracticals.anup.java.tictactoe.Model.CellState;
import com.example.lldpracticals.anup.java.tictactoe.Model.Move;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board)
    {
        for(int i=0;i<board.getSize();i++)
        {
            for(int j=0;j< board.getSize();j++)
            {
                if(board.getCell(i,j).getState()== CellState.EMPTY)
                {
                    return new Move(new Cell(i,j),null);
                }
            }
        }
        return null;
    }
}
