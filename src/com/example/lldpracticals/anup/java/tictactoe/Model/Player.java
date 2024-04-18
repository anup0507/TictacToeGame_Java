package com.example.lldpracticals.anup.java.tictactoe.Model;

import java.util.Scanner;

public class Player {
    private String name;
    private PlayerType playerType;
    private Long id;
    private Symbol symbol;
    private Scanner scanner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Player(String name, PlayerType playerType, Long id, Symbol symbol) {
        this.name = name;
        this.playerType = playerType;
        this.id = id;
        this.symbol = symbol;
        this.scanner=new Scanner(System.in);
    }

    public Move makeMove(Board board) {
        System.out.println("Please enter row count where you want to make a move starting from zero?");
        int getrow=scanner.nextInt();
        System.out.println("Please enter column count where you want to make a move starting from zero?");
        int getcol=scanner.nextInt();
        return new Move(new Cell(getrow,getcol),this);
    }
}
