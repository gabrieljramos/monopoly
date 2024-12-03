public class board {
    private dice dado;
    private bank comp;
    private player gamers[];
    private portfolio map;

    private int numPlayers;
    private static final int MAX_PLAYERS = 4;

    public void setPlayers(int value)
    {
        if (value < MAX_PLAYERS)
            numPlayers = value;
    }

    public void setPlayers(player gamer, int id)
    {
        gamers[id] = gamer;
    }

    public player getPlayer (int id)
    {
        return gamers[id];
    }

    public bank getBank()
    {
        return comp;
    }

    public dice getDie()
    {
        return dado;
    }

    public squares getPlace(int position)
    {
        return map.search(position);
    }
}
