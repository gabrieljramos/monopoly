import java.util.List;
import java.util.ArrayList;

public class portfolio{
    private List<squares> properties;

    public portfolio(){
        properties = new ArrayList<>();
    }

    public void addProp(squares item) {
        properties.add(item);
    }

    public squares remProp(squares item) {
        properties.remove(item);
        return item;
    }
    
    public squares search (int location)
    {
        for (squares place : properties)
        {
            if (place.getPosition() == location)
                return place;
        }

        return null;
    }

    public int checkStocks ()
    {
        int count = 0;
        for (squares place : properties)
            if (place instanceof stocks)
                count++;
        return count;
    }
}