public class special extends squares{
    private int category;

    private int timeOut;

    public special(int category, int timeOut){
        this.timeOut = timeOut;
        this.category = category;
    }

    public int getCategory ()
    {
        return category;
    }

    public void setCategory (int category)
    {
        this.category = category;
    }

    public void activateHolyday ()  //dado tem que pedir permissao da casa para poder andar!
    {
        if (category == 1)
            timeOut++;
    }
    
    public void activatePrison()
    {
        if (category == 2)
            timeOut += 3;
    }
    public void updateHolyday ()
    {
        if (timeOut > 0)
            timeOut--;
    }

    public int getTimeOut ()
    {
        return timeOut;
    }

    public void finishLine(wallet money, long value)
    {
        if (category == 3)
            money.receive(value);
    }

    public int fallSpecial (wallet money, long salary)
    {
        activateHolyday();
        activatePrison();
        finishLine(money, salary);
        //aqui tem que testar para cair na casa de noticia!!!
        //chama aqui a funcao da carta e a distancia de deslocamento e o retorno!
        return 0;
    }
}
