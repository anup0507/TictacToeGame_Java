package com.example.lldpracticals.anup.java.tictactoe.Strategies.WinningStrategies;

import com.example.lldpracticals.anup.java.tictactoe.Model.Board;
import com.example.lldpracticals.anup.java.tictactoe.Model.Move;
import com.example.lldpracticals.anup.java.tictactoe.Model.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RightDiagnolWinningStrategy implements WinningStrategy{

    private Map<Symbol,Integer> rightDiagnolCounts=new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col=move.getCell().getCol();
        int row=move.getCell().getRow();
        Symbol symbol=move.getPlayer().getSymbol();
        if(col+row==board.getSize()-1){

            if(rightDiagnolCounts.containsKey(symbol)){
                int count=rightDiagnolCounts.get(symbol);
                count++;
                if(count==board.getSize()){
                    return true;
                }
                rightDiagnolCounts.put(symbol,count);
            }else{
                rightDiagnolCounts.put(symbol,1);
            }

        }
        return false;
    }

    @Override
    public void handleUndo(Board board,Move move) {

            int col=move.getCell().getCol();
            int row=move.getCell().getRow();
            Symbol symbol=move.getPlayer().getSymbol();
            if(col+row==board.getSize()-1){
                if(!rightDiagnolCounts.containsKey(symbol)){
                    return;
                }

                int count=rightDiagnolCounts.get(symbol);
                if(count-1==0)
                {
                    rightDiagnolCounts.remove(symbol);
                }
                else {
                    count--;
                    rightDiagnolCounts.put(symbol,count);
                }
            }
    }
}
