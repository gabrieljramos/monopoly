public class bank {
    private static final long SALARY = 2000;

    private portfolio holdings;
    //como fazer a hipoteca?
    //vender propriedades entre os jogadores?
    private void transfer (bank comp, player gamer, property land)
    {
        gamer.addProp(comp.holdings.remProp(land));
    }
    public boolean sellProperties(player gamer)
    {
        squares land = this.holdings.search(gamer.getPosition());
        if (!(land instanceof property))
            return false;
        
        if (gamer.pay(((property) land).getValue())) {
            transfer(this, gamer, (property) land);
            return true;
        }
        
        return false;
    }

    public void payDay (player gamer)   //quando da volta no tabuleiro ou cai em casa especial
    {
        gamer.receive(SALARY);
    }
    
    public void payUp (player gamer, long value)    //quando cai em casa especial
    {
        gamer.pay(value);
    }
    
    public void mortgage ()
    { 
    }
}
