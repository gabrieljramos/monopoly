import java.io.*;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.Math;

public class propInfo {

    int numLines;
    List<String> allLines = new ArrayList<String>();

    public propInfo() {

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
    public int getStartingValue(int pos) {

        String propInfo = getPropertyInfo(pos);
        String[] parts = propInfo.split("[,]");
        return Integer.parseInt(parts[0]);

    }    
    public int getStartingHouses(int pos) {

        String propInfo = getPropertyInfo(pos);
        String[] parts = propInfo.split("[,]");
        return Integer.parseInt(parts[1]);

    }    
    public int getStartingRent(int pos) {

        String propInfo = getPropertyInfo(pos);
        String[] parts = propInfo.split("[,]");
        return Integer.parseInt(parts[2]);

    }
    public int getStartingMortgage(int pos) {

        String propInfo = getPropertyInfo(pos);
        String[] parts = propInfo.split("[,]");
        return Integer.parseInt(parts[3]);
        
    }
    public int getMultiplier(int pos) {
        
        String propInfo = getPropertyInfo(pos);
        String[] parts = propInfo.split("[,]");
        return Integer.parseInt(parts[4]);
        
    }
    public int getSetType(int pos) {
            
        String propInfo = getPropertyInfo(pos);
        String[] parts = propInfo.split("[,]");
        return Integer.parseInt(parts[5]);
        
    }
}
