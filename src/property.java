public class property extends squares{
    private int value[] = new int[6];
    private int houses[] = new int[5]; //vetor com os valores de melhoria pra casas
    private int rent[] = new int[6]; //aluguel com base nas melhorias
    private int state; //estado das melhorias
    private int set;
    private boolean recent;
    private log register;
    
    public void pay_rent(wallet player) {
        player.pay(rent[state]);
    }

    public void improve(player character) {
        if (character.check() >= this.houses[state + 1]) {
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

    public void update()
    {
        register.update();
        recent = true;        
    }
}
