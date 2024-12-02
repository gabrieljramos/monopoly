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

    private boolean thirdPartyTransfer (player owner, player buyer)
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

        if (exchange(buyer, owner, value))
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

    public boolean sellProperties(player owner, player buyer)   //negocio entre players
    {
        return thirdPartyTransfer(owner, buyer);
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
    
    public boolean exchange (player giver, bank receiver, long value)
    {
        if (giver.pay(value))
            return true;
        return false;
    }

    public boolean exchange (bank giver, player receiver, long value)
    {
        receiver.receive(value);
        return true;
    }
    
}