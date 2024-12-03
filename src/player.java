//Arquivo que contem a classe dos players
public class player {
    private int id;
    private wallet money;
    private int position;
    private portfolio resources;

    public static final long FIRST_MONEY = 2000;    //passar isso pra initializer!
    
    public void receive (long value)
    {
        this.money.receive(value);
    }

    public boolean pay (long value)
    {
        return this.money.pay(value);
    }

    public long check()
    {
        return this.money.check();
    }

    public void addProp (squares item)
    {
        this.resources.addProp(item);
    }

    public squares remProp (squares item)
    {
        this.resources.remProp(item);
        return item;
    }

    public void move(squares place, dice dado, int totalSquares)
    {
        dado.throwDie();
        if (place instanceof special) {
            if (((special) place).getTimeOut() > 0) {
                ((special) place).updateHolyday();
                return;
            }
        }
        int distance = dado.checkTotalValue();
        position = (position + distance) % totalSquares; 
    }
    
    public void improveProperty(property land, bank comp)
    {
        if (comp.getOwner(position) == id)
            land.improve(this);
    }

    public void update (squares place, bank comp)
    {
        int owner = comp.getOwner(position);
        if (place instanceof property)
        {
            if (owner != id)
                ((property) place).payRent(money, comp.checkMonopoly(((property)place).getSet(), owner));
        }
        else if (place instanceof stocks)
        {
            if (owner != id)
                ((stocks) place).payDebt(money);
        }
        else    //considerando que so tem 3 tipos de squares
            ((special)place).fallSpecial(money, comp.getSalary());
    }
    public void compra(bank comp)
    {
        comp.sellProperties(this, false);
    }
    
    public void compra(player p2, bank comp) //compra forcada
    {
        if (p2 == this)
            return;
        comp.sellProperties(p2, this);
    }

    public void vende (bank comp)
    {
        long value = 0;
        if (resources.search(position) instanceof property)
            value = ((property)resources.search(position)).getValue();
        else if (resources.search(position) instanceof stocks)
            value = ((stocks)resources.search(position)).getValue();

        comp.exchange(this, comp, value);
    }

    public int getPosition ()
    {
        return position;
    }

    public squares currentSquare()
    {
        return this.resources.search(position);
    }

    public int getId()
    {
        return id;
    }
}