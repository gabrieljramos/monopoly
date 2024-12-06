public class stocks extends squares{
    private int value;
    private static final double MULTIPLIER = 1.5;

    public stocks(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public void payDebt (wallet money, int stocksQuantity)  //quando alguem cair no stock tem que pedir pro dono informar quantos tem
    {
        int increase = stocksQuantity;
        if (increase > 0)
            increase *= MULTIPLIER;
        else
            increase = 1;
        money.pay(value * increase);
    }
}
