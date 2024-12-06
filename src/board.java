public class board {
    private dice dado;
    private bank comp;
    private player gamers[];
    private portfolio map;

    private int numPlayers;
    private static final int MAX_PLAYERS = 4;

    public void setPlayers(int value)   //numero maximo de players
    {
        if (value < MAX_PLAYERS)
            numPlayers = value;
    }

    public int getPlayers()
    {
        return numPlayers;
    }

    public void setPlayers(player gamer, int id)
    {
        gamers[id] = gamer;
    }

    public player getPlayer (int id)    //pegar qualquer player especifico
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

    public player getOwner(int position)
    {
        int id = comp.getOwner(position);
        return getPlayer(id);
    }
}
