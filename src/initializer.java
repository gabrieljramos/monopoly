public class initializer {
    // Isso precisa do arquivo para criar listas de propriedades
    private propInfo data;

    public monopoly.board startBoard(int playerAmount)
    {
        monopoly jogo = new monopoly();
        monopoly.board tabuleiro = new monopoly.board(playerAmount);
        data = new propInfo();
    }

    public void prepareMap(monopoly.board tabuleiro, propInfo data)
    {
        for (int i = 0; i < 40; i++)
        {
            if (nsq == -2)
            {
                tabuleiro.addLocation();
            }
        }
    }

    public int[] maping()
    {
        int[] locations = new int[40];
        locations[0] = 3;
        locations[1] = -1;
        locations[2] = -1;

    }
}
