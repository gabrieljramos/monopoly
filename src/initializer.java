public class initializer {
    // Isso precisa do arquivo para criar listas de propriedades
    private propInfo data;
    private propInfo specifications;

    public monopoly.board startBoard(int playerAmount, int squaresQuantity)
    {
        monopoly jogo = new monopoly();
        monopoly.board tabuleiro = new monopoly.board(playerAmount);
        data = new propInfo("initializer.txt");
        specifications = new propInfo("propertyInfo.csv");

        prepareMap(tabuleiro, data, squaresQuantity);
    }

    public void prepareMap(monopoly.board tabuleiro, propInfo data, int squaresQuantity)
    {
        for (int i = 0; i < squaresQuantity; i++) {
            int value = data.getLine(i);
            if (value == -2) {
                stocks acao = new stocks(0);
                tabuleiro.addLocation(acao);
            } else if (value == -1) {
                property land = new property(0, 0);
                tabuleiro.addLocation(land);
            } else {
                special space = new special(value, 0);
                tabuleiro.addLocation(space);
            }
        }
    }
    
    public void setar(monopoly.board tabuleiro, propInfo specifications, int squaresQuantity)
    {
        for (int i = 0; i < squaresQuantity; i++)
        {
            squares land = tabuleiro.getLocation(i);
            if (land instanceof property)
            {
                int value1 = specifications.getStartingMortgage(i);
                log register = new log()
                land = new property(0, i);
            }
        }
    }
}
