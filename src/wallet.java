public class wallet {
    private long money;

    public void receive(long value)
    {
        if (value < 0)
            return;
        this.money += value;
    }

    public boolean pay (long value)
    {
        if (value < 0 || this.money < value)
            return false;
        this.money -= value;
        return true;
    }
    
    public long check()
    {
        return this.money;
    }
}