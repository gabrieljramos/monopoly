import java.util.*;

public class dice {
    private int value1;
    private int value2;

    private int playerId;
    private int totalPlayers;   //COLOCAR ISSO QUANDO MONTAR OS DADOS!

    private Random random;

    public int checkValue1()
    {
        return value1;
    }

    public int checkValue2()
    {
        return value2;
    }

    public int checkTotalValue()
    {
        return value1 + value2;
    }

    public void update()    //atualiza quem está segurando o dado no momento
    {
        playerId = (playerId + 1) % totalPlayers;
    }

    public int throwDice()
    {
        int value = random.nextInt(6); //aleatorio entre 0 e 5
        return value + 1;
    }
    
    public void throwDie()
    {
        value1 = throwDice();
        value2 = throwDice();
    }

}
