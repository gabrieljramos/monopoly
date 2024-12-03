public class stocks extends squares{
    private int value;
    
    public int getValue()
    {
        return value;
    }

    public void payDebt (wallet money)
    {
        money.pay(value);
    }
}
