public class run {
    public static void main (String[] args) {

        int playerAmount, FPS = 60;

        //Imagino q tem coisa o JavaFX pra inicilizar, ent inicializa aqui

        //Pensei que o menu pd retornar a qntd de player desejada?
        //Pq imagino q vai precisar dela pra inicilizar as coisas, e se escolher quit no menu playerAmount vai ser 0
        playerAmount = menu();
        if (!playerAmount)
            return;

        //Todas as inicializacoes necessarias aqui
        initializer();

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;


        while (!quit) {

            //Desenha o tabuleiro

            for (int currentPlayer = 0; currentPlayer < totalPlayers; currentPlayer++) { //Loop de rodadas
                //Uma funcao ou algo assim, pra pedir q aperte ENTER pra jogar os dados
                currentPlayer.move();                         //Joga os dados e move o player
                currentPlayer.update();                       //Ja faz varias das funcoes possiveis q podem acontecer numa casa  
                if (currentPlayer.verifyOwnership() == false) //Caso ele nao seja dono da casa
                    //Alguma funcao perguntando se quer comprar a propriedade, se puder, seja ela do banco ou outro player

                

            }

            //Uma maneira de fazer timer q eu vi, so n sei se funfa com javaFX
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if (remainingTime < 0)
                    remainingTime = 0;
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterrupedException e)
                e.printStackTrace();




        }




    }

}
