import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class save {

    String text;
    int numLines;
    List<String> allLines = new ArrayList<String>();

    public void saveGame(player[] gamers, int playerAmount) {

        checkFile();

        for (int i = 0; i < playerAmount; i++) {

            player p = gamers[i];
            text = text + String.valueOf(p.getPosition()) + ",";
            text = text + Long.toString(p.Check()) + ",";

            squares casa;

            for (int j = 0; j < 40; j++) {
                casa = p.getPortfolio().search(j);
                if (casa != null) {
                    if (casa instanceof stocks) {
                        text = text + String.valueOf(j) + ",";
                        text = text + "-1" + ",";
                        text = text + "-1" + ",";
                        text = text + "-1" + ",";

                    }
                    else if (casa instanceof property) {
                        property prop = (property) casa;
                        text = text + String.valueOf(j) + ",";
                        text = text + String.valueOf(prop.getState()) + ",";
                        text = text + String.valueOf(prop.getDebt()) + ",";
                        text = text + String.valueOf(prop.getDuration()) + ",";
                    }

                }
            }

            text = text + String.valueOf(p.getId());

            Path save = Path.of("../assets/save.csv");
            try {
                Files.writeString(save, text + "\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void getInfo() {

        try {
        this.allLines = Files.readAllLines(Paths.get("../assets/save.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.numLines = allLines.size();
    }

    public int getPlayers() {
        return this.numLines;
    }

    private void checkFile() {
        File f = new File("../assets/save.csv");
        if (f.isFile()) {
            f.delete();
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public boolean fileExists() {
        File f = new File("../assets/save.csv");
        return f.isFile(); 
    }


    public void getSave(player[] gamers, monopoly.board tabuleiro) {
        
        getInfo();
        for (int i = 0; i < numLines; i++) {

            String line = this.allLines.get(i);
            String[] parts = line.split("[,]");

            player p = gamers[i];
            p.setPosition(Integer.parseInt(parts[0])); 
            p.setMoney(Long.parseLong(parts[1]));

            squares casa;

            for (int j = 2; j < parts.length - 1; j++) {
                casa = tabuleiro.getMap().search(Integer.parseInt(parts[j]));
                wallet carteira = new wallet(20000000);
                tabuleiro.getBank().sellProperties(p.getPortfolio(),carteira,i,casa,false);
                if (casa instanceof property) {
                    property prop = (property) casa;
                    prop.setState(Integer.parseInt(parts[j+1]));
                    prop.setDebt(Integer.parseInt(parts[j+2]));
                    prop.setDuration(Integer.parseInt(parts[j+3]));
                    if (Integer.parseInt(parts[j+2]) > 0)
                        prop.setMortgage(true);
                    else
                        prop.setMortgage(false);
                }
                j += 3;
            }
            p.setId(Integer.parseInt(parts[parts.length-1])); 
        }
    }
}
