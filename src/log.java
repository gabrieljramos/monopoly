public class log {
    private boolean mortgaged;
    private int parcelas;   //contador ou prazo ate pagamento
    private int debt;
    private int mortgage[] = new int[6]; //vetor com os valores de hipoteca dependendo das melhorias

    private static final int DURATION = 5;

    public boolean mortgage(property land, wallet money)
    {
        if (mortgaged)
            return false;
        mortgaged = true;
        parcelas = DURATION;
        debt = mortgage[land.getState()];
        money.receive(debt);///hipoteca com a prop ou o banco?

        return true;
    }

    public boolean payMortgage (portfolio gamer, wallet money, property land, bank comp)
    {
        if (!mortgaged)
            return true;
        if (money.pay(debt)) {
            mortgaged = false;
            debt = 0;
            return true;
        }
        comp.seize(gamer, land);
        return false;
    }
    
    public boolean update ()
    {
        if (parcelas > 0)
            parcelas -= 1;
        if (parcelas == 0 && mortgaged)     //retorna 1 caso esteja na hora de quitar divida
            return true;
        return false;
    }

    public boolean updateMortgage(portfolio gamer, wallet money, property land, bank comp)
    {
        if (update())
            return payMortgage(gamer, money, land, comp);
        return true;
    }
}