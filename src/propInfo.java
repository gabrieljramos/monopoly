import java.io.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.Math;

class property {

    int numLines;
    List<String> allLines = new ArrayList<String>();

    public Cartas() {

        try {
        this.allLines = Files.readAllLines(Paths.get("propertyInfo.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.numLines = allLines.size();

    }
    public String getPropertyInfo(int pos) {

        return this.allLines.get(pos);

    }
    public int getValue(int pos) {

        String propInfo = getPropertyInfo(pos);
        String[] parts = propInfo.split("[,]");
        return (int) parts[0];

    }    
    public int getHouses(int pos) {

        String propInfo = getPropertyInfo(pos);
        String[] parts = propInfo.split("[,]");
        return (int) parts[1];

    }    
    public int getRent(int pos) {

        String propInfo = getPropertyInfo(pos);
        String[] parts = propInfo.split("[,]");
        return (int) parts[2];

    }

