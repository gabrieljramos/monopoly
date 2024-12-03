public class bank {
    private static final long SALARY = 2000;

    private portfolio holdings;
    private register dataBank;

    private boolean transfer (player gamer, squares place, boolean sell)
    {
        property land;
        stocks local;

        int value;

        if (place instanceof property)
        {
            land = (property) place;
            value = land.getValue();
        }
        else if (place instanceof stocks)
        {
            local = (stocks) place;
            value = local.getValue();
        }
        else
            return false;

        if (sell && exchange(this, gamer, value))
        {
            holdings.addProp(gamer.remProp(place));
            dataBank.setOwner(place.getPosition(), 0);
        }
        else if (!sell && exchange(gamer, this, value))
        {
            gamer.addProp(holdings.remProp(place));
            dataBank.setOwner(place.getPosition(), gamer.getId());
        }
        else
            return false;
        
        return true;
    }

    private boolean thirdPartyTransfer (player owner, player buyer, boolean mode)
    {
        property land;
        stocks local;

        int position, value;
        squares square = owner.currentSquare();

        if (square instanceof property)
        {
            land = (property) square;
            position = land.getPosition();
            value = land.getValue();
        }
        else if (square instanceof stocks)
        {
            local = (stocks) square;
            position = local.getPosition();
            value = local.getValue();
        }
        else
            return false;

        if (mode && exchange(buyer, owner, value))
        {
            buyer.addProp(square);
            owner.remProp(square);
            dataBank.setOwner(position, buyer.getId());
            return true;
        }
        return false;
    }
    public boolean sellProperties(player gamer, boolean sell)   //negocio entre banco e player
    {
        return transfer(gamer, gamer.currentSquare(), sell);
    }

    public boolean sellProperties(player owner, player buyer, boolean mode)   //negocio entre players
    {
        return thirdPartyTransfer(owner, buyer, mode);  //1 para vender, 0 para trocar
    }

    public boolean tradeProperties(player trader1, player trader2)
    {
        return (thirdPartyTransfer(trader1, trader2, false) && thirdPartyTransfer(trader2, trader1, false));
    }

    public void seize (player gamer, property land)
    {
        this.holdings.addProp(gamer.remProp(land));
    }

    public void payDay (player gamer, long value)   //quando da volta no tabuleiro ou cai em casa especial
    {
        gamer.receive(value);
    }
    
    public void payUp (player gamer, long value)    //quando cai em casa especial
    {
        gamer.pay(value);
    }

    public boolean exchange (player giver, player receiver, long value)
    {
        if (giver.pay(value)) {
            receiver.receive(value);
            return true;
        }
        return false;
    }
    
    public boolean exchange (player giver, bank receiver, long value)   //pagar pro banco!
    {
        if (giver.pay(value))
            return true;
        return false;
    }

    public boolean exchange (bank giver, player receiver, long value)   //receber do banco!
    {
        receiver.receive(value);
        return true;
    }
    
    public int getOwner(int position)
    {
        return dataBank.getOwner(position);
    }

    public void setOwner(int position, int owner_id)
    {
        dataBank.setOwner(position, owner_id);
    }

    public boolean checkMonopoly(int set, int owner_id)
    {
        return dataBank.checkMonopoly(set, owner_id);
    }

    public long getSalary()
    {
        return SALARY;
    }
}