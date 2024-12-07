import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

public class run extends Application {

    private boolean quit = false;
    private String lastKeyPressed = "";

    public static void main(String[] args) {
        launch(args); // Inicializa o JavaFX
    }

    @Override
    public void start(Stage primaryStage) {
        // Configurar a janela principal do JavaFX
        VBox layout = new VBox();
        Button startButton = new Button("Iniciar Jogo");
        Button quitButton = new Button("Sair");

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Jogo");
        primaryStage.show();

        startButton.setOnAction(e -> {
            int playerAmount = menu();
            if (playerAmount == 0) {
                System.out.println("Jogo encerrado.");
                return;
            }
            initializer();
            board tabuleiro = new board(playerAmount);   //TEM QUE INICIALIZAR O TABULEIRO COM TUDO PRONTO AQUI E MANDAR PRO LOOP!!! 
            gameLoop(playerAmount, tabuleiro, scene);
        });

        quitButton.setOnAction(e -> {
            quit = true;
            primaryStage.close();
        });

        layout.getChildren().addAll(startButton, quitButton);

    }

    private int menu() {
        // Implementar lógica do menu (exemplo: retornar número de jogadores)
        return 2; // Exemplo fixo
    }

    private void initializer() {
        // Implementar lógica de inicialização do jogo
        System.out.println("Inicializando o jogo...");
    }

    private void detectEnterOrEsc(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                lastKeyPressed = "ENTER";
            } else if (event.getCode() == KeyCode.ESCAPE) {
                lastKeyPressed = "ESC";
            }
        });
        scene.setOnKeyReleased(event -> {
            lastKeyPressed = ""; // Reset after processing
        });
    }

    private void gameLoop(int totalPlayers, board tabuleiro) {
        // Implementar lógica do loop do jogo
        int currentPlayer = 0;
        double FPS = 60;
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (!quit) {
            // Desenha o tabuleiro
            System.out.println("Jogador " + (currentPlayer + 1) + " está jogando.");
            detectEnterOrEsc(scene); //DE ONDE VEM ESSA SCENE?
            player gamer = tabuleiro.getGamers()[currentPlayer];    //separa o player jogando
            if (lastKeyPressed.equals("ENTER")) {
                if (gamer.move(tabuleiro.getPlace(gamer.getPosition()), tabuleiro.getDie(), tabuleiro.getSquaresQuantity()))    //verifica se ele se moveu
                {
                    int stocks = tabuleiro.getBank().getOwner(gamer.getPosition()); //separa a id do dono do quadrado em que o player chegou
                    stocks = tabuleiro.getGamers()[stocks].checkStocks();   //separa a quanitdade de ações do dono do quadrado
                    gamer.update(tabuleiro.getLocation(gamer.getPosition()), tabuleiro.getBank(),   //atualiza o player por chegar em um quadrado novo
                            tabuleiro.getSquaresQuantity(), stocks, tabuleiro.getGamers(), tabuleiro.getPlayers());
                }
                if (!gamer.getBankruptcy()) //se ainda não faliu
                {
                    if (!(tabuleiro.getLocation(gamer.getPosition()) instanceof special)) //se a posicao e compravel
                    {
                        if (gamer.verifyOwnership(tabuleiro.getBank()) == false) {
                            if (tabuleiro.getBank().getOwner(gamer.getPosition()) != 0) //se o dono nao e o banco
                            {
                                //opcao de comprar forcadamente
                            } else {
                                //opcao de comprar do banco
                            }
                        } else {
                            //opcao de melhorar a propriedade (ou hipotecar?)
                        }
                    }
                }
                //TECLA PRA PASSAR A VEZ?
            }
            else if (lastKeyPressed.equals("ESQ")) {
                pauseMenu();    //mas o que e isso?
            }
            if (gamer.checkVictory(tabuleiro.getBank(), tabuleiro.getStocksQuantity()) == 1)
            {
                //ACABA O JOGO!
            }
            currentPlayer++;
            if (currentPlayer >= totalPlayers)
                currentPlayer = 0;
            // Temporizador para FPS
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if (remainingTime < 0)
                    remainingTime = 0;
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
