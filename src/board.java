public class board {
    private dice dado;
    private bank comp;
    private player gamers[];
    private portfolio map;

    private int numPlayers;
    private static final int MAX_PLAYERS = 4;

    public board(int playerQnt){
        this.dado = new dice(playerQnt);
        this.comp = new bank();
        this.map = new portfolio();
        this.gamers = new player[playerQnt];
        
        for(int i = 0; i < playerQnt; i++){
            gamers[i] = new player(i);
        }   

        setPlayers(playerQnt);
    }

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

    public player getOwner(int position)
    {
        int id = comp.getOwner(position);
        return getPlayer(id);
    }
}
