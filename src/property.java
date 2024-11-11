public class property extends squares{
    private int value[] = new int[6];
    private int houses[] = new int[5]; //vetor com os valores de melhoria pra casas
    private int mortgage[] = new int[6]; //vetor com os valores de hipoteca dependendo das melhorias
    private int rent[] = new int[6]; //aluguel com base nas melhorias
    private int state; //estado das melhorias

    public void pay_rent(wallet player) {
        player.pay(rent[state]);
    }

    public void improve(player character) {
        if (this.state < 5 && (character.check() >= this.houses[state + 1])) {
            state++;
            character.pay(this.houses[state]);
        }
    }

    public int getValue ()
    {
        return value[state];
    }
}
