//Arquivo que contem a classe dos players
public class player
{
    private long money;
    private int position;
    private int[] propriedades = new int[40];

    public static final long FIRST_MONEY = 10000;
    
    public void begin()
    {
        money = FIRST_MONEY;
        position = 0;
        for (int i = 0; i < propriedades.length; i++)
        {
            propriedades[i] = 0;
        }
    }

    public void rodaDado()
    {
        //tem que chamar um metodo de sortear numero do objeto dado 
        //position = (position + quantoAndou) mod
        //tem que ver para adicionar a cada tanto de tempo!
    }

    public void compra(/*Propriedade p */)
    {
        //if (money < p.price)
        //  return;
    }
    public void vende(){}
}