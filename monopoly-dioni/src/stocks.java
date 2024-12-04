public class stocks extends squares{
    private int value;
    
    public stocks(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public void payDebt (wallet money)
    {
        money.pay(value);
    }
}
