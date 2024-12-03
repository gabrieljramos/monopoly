public class property extends squares{
    private int value[] = new int[6];
    private int houses[] = new int[5]; //vetor com os valores de melhoria pra casas
    private int rent[] = new int[6]; //aluguel com base nas melhorias
    private int state; //estado das melhorias
    private int set;
    private boolean recent;
    private log register;
    
    public void payRent(wallet player, boolean monopoly) {
        int value = rent[state];
        if (monopoly)
            value *= 3;
        player.pay(value);
    }

    public void improve(player character) {
        if (character.check() >= this.houses[state]) {
            if (state < 4 || recent)
            {
                state++;
                character.pay(this.houses[state]);
                recent = false;
            }
        }
    }

    public int getValue ()
    {
        return value[state];
    }

    public int getState()
    {
        return state;
    }

    public void update()    //atualiza a hipoteca e a possibilidade de comprar hotel
    {
        register.update();
        recent = true;
    }
    
    public void setSet(int value)
    {
        set = value;
    }

    public int getSet()
    {
        return set;
    }

    public boolean updateMortgage(player gamer, property land, bank comp)
    {
        return register.updateMorgage(gamer, land, comp);
    }
}
