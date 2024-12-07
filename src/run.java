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
            gameLoop(playerAmount, tabuleiro, scene, primaryStage);
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
            lastKeyPressed = ""; 
        });
    }
    private void pauseMenu(AnimationTimer gameTimer, Stage primaryStage) {
    // Pause the game timer
    gameTimer.stop();

    // Create a new Stage for the pause menu
    Stage pauseStage = new Stage();
    pauseStage.initOwner(primaryStage); // Set the owner of the pause menu
    pauseStage.setTitle("Pause Menu");

    // Create buttons for "Continue" and "Quit"
    Button continueButton = new Button("Continue");
    Button quitButton = new Button("Quit");

    // Layout for the pause menu
    VBox layout = new VBox(10);
    layout.setStyle("-fx-padding: 10; -fx-alignment: center;");
    layout.getChildren().addAll(continueButton, quitButton);

    Scene pauseScene = new Scene(layout, 200, 150);
    pauseStage.setScene(pauseScene);

    // Set button actions
    continueButton.setOnAction(e -> {
        pauseStage.close(); // Close the pause menu
        gameTimer.start();  // Resume the game
    });

    quitButton.setOnAction(e -> {
        pauseStage.close(); // Close the pause menu
        primaryStage.close(); // Close the main game window
    });

    // Show the pause menu
    pauseStage.showAndWait(); // Blocks interaction with the main game window
}
    private void startGameLoop(int totalPlayers, board tabuleiro, Scene scene, Stage primaryStage) {
        int currentPlayer = 0; // Track current player using an array for mutability
        double FPS = 60;
        double drawInterval = 1_000_000_000 / FPS; // Frame interval in nanoseconds
        long[] lastUpdateTime = {System.nanoTime()}; // Store last update time

        AnimationTimer gameTimer = new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                if (currentTime - lastUpdateTime[0] >= drawInterval) {
                    // Update game logic
                    System.out.println("Jogador " + (currentPlayer + 1) + " está jogando.");
                    player gamer = tabuleiro.getGamers()[currentPlayer];

                    if ("ENTER".equals(lastKeyPressed)) {
                        if (gamer.move(tabuleiro.getPlace(gamer.getPosition()), tabuleiro.getDie(), tabuleiro.getSquaresQuantity())) {
                            int stocks = tabuleiro.getBank().getOwner(gamer.getPosition());
                            stocks = tabuleiro.getGamers()[stocks].checkStocks();
                            gamer.update(tabuleiro.getLocation(gamer.getPosition()), tabuleiro.getBank(),
                                    tabuleiro.getSquaresQuantity(), stocks, tabuleiro.getGamers(), tabuleiro.getPlayers());
                        }

                        // Handle game logic (bankruptcy, victory, etc.)
                        if (!gamer.getBankruptcy() && !(tabuleiro.getLocation(gamer.getPosition()) instanceof special)) {
                            if (!gamer.verifyOwnership(tabuleiro.getBank())) {
                                if (tabuleiro.getBank().getOwner(gamer.getPosition()) != 0) {
                                    // Forced purchase logic
                                } else {
                                    // Purchase from bank logic
                                }
                            } else {
                                // Improve or mortgage property
                            }
                        }

                        if (gamer.checkVictory(tabuleiro.getBank(), tabuleiro.getStocksQuantity()) == 1) {
                            System.out.println("Jogador " + (currentPlayer[0] + 1) + " venceu o jogo!");
                            stop(); // Stop the game loop
                        } else {
                            currentPlayer++; // Move to next player
                        }
                    } else if ("ESC".equals(lastKeyPressed)) {
                        pauseMenu(this,primaryStage); // Pause logic
                    }

                    // Update time
                    lastUpdateTime[0] = currentTime;
                }
            }
        };

        gameTimer.start();
    }
}
