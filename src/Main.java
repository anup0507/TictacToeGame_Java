import com.example.lldpracticals.anup.java.tictactoe.Controller.GameController;
import com.example.lldpracticals.anup.java.tictactoe.Model.*;
import com.example.lldpracticals.anup.java.tictactoe.Strategies.WinningStrategies.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);
        try
        {
            Player player1 = new Player("Anup", PlayerType.HUMAN, 1L,new Symbol('X'));
            Player player2 = new Bot("Bot", 2L,new Symbol('O'), BotDifficultyLevel.EASY);
            List<Player> playerList = new ArrayList<>();
            playerList.add(player1);
            playerList.add(player2);
            List<WinningStrategy> winningStrategyList = new ArrayList<>();
            winningStrategyList.add(new ColWiseWinningStrategy());
            winningStrategyList.add(new RowWiseWinningStrategy());
            winningStrategyList.add(new LeftDiagnolWinningStrategy());
            winningStrategyList.add(new RightDiagnolWinningStrategy());
            int dimensionofBoard= 3;
            Game game=gameController.StartGame(dimensionofBoard,playerList,winningStrategyList);
            boolean firstmove=true;
            while(gameController.checkState(game).equals(GameState.IN_PROGRESS))
            {
                gameController.printBoard(game);
                if(game.getLastPlayerMoveIndex()!=-1 && game.getPlayers().get(game.getLastPlayerMoveIndex()).getPlayerType().equals(PlayerType.BOT))
                {
                    gameController.makeMove(game);
                    continue;
                }
                if(!firstmove)
                {
                    System.out.println("Do you want to undo the move? press  y/n");
                    String undo = scanner.next();
                    if(undo.equals("y"))
                    {
                        gameController.UndoMove(game);
                        System.out.println("Move is undone.Now the borad looks like below:");
                        // gameController.printBoard(game);
                        continue;
                    }
                }

                gameController.makeMove(game);
                firstmove=false;
            }

            System.out.println("Game is finished now");
            GameState gameState = gameController.checkState(game);
            if(gameState.equals(GameState.DRAW))
            {
                System.out.println("Game is draw");
            }
            else
                System.out.println("Winner is "+gameController.getWinner(game).getName());
            gameController.printBoard(game);

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }



    }
}