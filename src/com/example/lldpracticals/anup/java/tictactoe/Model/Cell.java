package com.example.lldpracticals.anup.java.tictactoe.Model;

public class Cell {
    private int row;
    private int col;
    private CellState state;
    private Player player;
    private Symbol symbol;

    public void display()
    {
        if(player==null)
            System.out.print("| - |");
        else
            System.out.print("| "+player.getSymbol().getCh()+" |");
    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.state = CellState.EMPTY;
        this.player = null;
    }
}
