import java.io.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.Math;

class Cartas {

    int numCards;
    static List<String> allCards = new ArrayList<String>();

    public Cartas() {

        if (allCards == NULL) {
            try {
            this.allCards = Files.readAllLines(Paths.get("cards.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.numCards = allCards.size();
        }

    }
    public String getCard(int n) {

        return this.allCards.get(n);

    }
    public void drawACard() {

        randomCard = (int)(Math.random()*this.numCards);
        String card = getCard(randomCard);
        manageCard(card);

    }
    public void movementCard(String[] parts) {
        int pos = currentPlayer.getPosition();
        if (parts[4].equals("0"))
            currentPlayer.walkTo(parts[3]);
        else
            currentPlayer.teleportTo(parts[3]);
    }
    public void moneyCard(String[] parts) {
        //Origin of the money
        if (parts[2].equals("0") || parts[3].equals("1"))
            currentPlayer.pay(parts[4]);

        //Destination of the money
        if (parts[3].equals("0"))
            currentPlayer.receive(parts[4]);        
        else if (parts[3].equals("2"))
            certainPlayer.receive(parts[4]);        

    }
    public void propertyCard(String[] parts) {
        certainPlayer.resources.remProp();
        currentPlayer.resources.addProp();
    }
    public void manageCard(String card) {

        String[] parts = card.split("[,]");

        if (parts[1].equals("0"))
            moneyCard();
        else if (parts[1].equals("1"))
            movementCard();
        else (parts[1].equals("2"))
            propertyCard();
    }   


}
