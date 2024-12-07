import java.io.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.Math;

public class cartas {

    int numCards;
    static List<String> allCards = new ArrayList<String>();

    public cartas() {

        if (allCards == null) {
            try {
            this.allCards = Files.readAllLines(Paths.get("cards.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.numCards = allCards.size();
        }

    }
    private String getCard(int n) {

        return this.allCards.get(n);

    }
    private int getRandomPlayer(int currentPlayer, int playerAmount) {
        int randomPlayer = currentPlayer;
        while (randomPlayer == currentPlayer)
            randomPlayer = (int)(Math.random()*playerAmount);
        return randomPlayer;
    }
    public int drawACard(int currentPlayer, player[] gamers) {

        int randomCard = (int)(Math.random()*this.numCards);
        String card = getCard(randomCard);
        return manageCard(card,currentPlayer,gamers);

    }

    private int movementCard(String[] parts) {

        return Integer.parseInt(parts[4]);

    }
    private int moneyCard(String[] parts, player[] gamers, int currentPlayer) {

        boolean success = true;
        String origin = parts[2], destination = parts[3];
        int amount = Integer.parseInt(parts[4]);

        if (origin.equals("0") || destination.equals("1"))
            success = gamers[currentPlayer].pay(amount);
        else if (origin.equals("2"))
            for (int i = 0; i < gamers.length; i++)
                if (i != currentPlayer)
                    success = gamers[i].pay(amount);


        if (destination.equals("0"))
            gamers[currentPlayer].receive(amount);
            if (origin.equals("2"))
                gamers[currentPlayer].receive((amount)*(gamers.length - 2));

        else if (destination.equals("2")) {
            int randomPlayer = getRandomPlayer(currentPlayer, gamers.length);
            gamers[randomPlayer].receive(amount);
        }

        if (success == false)
            return -1;
        return 0;

    }
    private void propertyCard(String[] parts, player[] gamers, int currentPlayer) {

        if (parts[4].equals("0"))
            gamers[currentPlayer].playerTrade();    //tem que colocar os parametros nessa chamada!  

    }
    private int manageCard(String card, int currentPlayer, player[] gamers) {

        int retValue = 0;
        String[] parts = card.split("[,]");

        if (parts[1].equals("0"))         //Handles cards related to the tansfer of money
            retValue = moneyCard(parts,gamers,currentPlayer);
        else if (parts[1].equals("1"))    //Handles cards related to moving around the board
            retValue = movementCard(parts);
        else if (parts[1].equals("2"))       //Handle cards related to property management
            propertyCard(parts,gamers,currentPlayer);

        return retValue;


    }   
}
