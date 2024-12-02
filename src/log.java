public class log {
    private boolean mortgaged;
    private int parcelas;
    private int debt;
    private int mortgage[] = new int[6]; //vetor com os valores de hipoteca dependendo das melhorias

    private static final int DURATION = 5;

    public boolean mortgage(player gamer)
    {
        if (mortgaged)
            return false;
        property land = (property)gamer.currentSquare();
        mortgaged = true;
        parcelas = DURATION;
        debt = mortgage[land.getState()];
        gamer.receive(debt);///hipoteca com a prop ou o banco?

        return true;
    }

    public boolean payMortgage (player gamer, property land, bank comp)
    {
        if (!mortgaged)
            return true;
        if (gamer.pay(debt)) {
            mortgaged = false;
            debt = 0;
            return true;
        }
        comp.seize(gamer, land);
        return false;
    }
    
    public void update ()
    {
        if (parcelas > 0)
            parcelas -= 1;
        return;
    }
}