//Arquivo que contem a classe dos players
public class player
{
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

    public void begin()
    {
        // passar para initialize
    }

    public void rodaDado()
    {
        //tem que chamar um metodo de sortear numero do objeto dado 
        //position = (position + quantoAndou) mod
        //tem que ver para adicionar a cada tanto de tempo!
    }

    public void compra(bank comp)
    {
        comp.sellProperties(this);
    }
    
    public void vende()
    {

    }

    public int getPosition ()
    {
        return position;
    }
}