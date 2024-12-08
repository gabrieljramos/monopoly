import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.*;
import java.util.*;

public class draw extends Application {
    private double boardSize;
    private static double stepSize;

    private Label[] moneyLabels = new Label[monopoly.board.getPlayers()];

    private static ImageView players[] = new ImageView[monopoly.board.getPlayers()];

    private int menu(Stage primaryStage) {
        Button startButton = new Button("Iniciar Jogo");
        Button continueButton = new Button("Continuar");
        Button quitButton = new Button("Sair");

        quitButton.setOnAction(e -> {
            return 0;
        });
        startButton.setOnAction(e -> {
            return 1;
        });
        continueButton.setOnAction(e -> {
            return 2; // Exemplo fixo
        });

        return 0;
    }
    
    public void start(Stage primaryStage) {
        int numP = 0, op = 0;
        op = menu(primaryStage);
        if (op == 0)
            return;
        else if (op == 1)
            numP = showPlayerSelectionDialog(primaryStage);
        else if (op == 2)
            //Logica p/ continuar um save
        StackPane root = createGameLayout(primaryStage);
        Scene scene = new Scene(root);
        diceUI(3, 4, root);

        // background da tela
        BackgroundFill fill = new BackgroundFill(Color.web("#FFEE8C90"), null, null);
        root.setBackground(new Background(fill));

        // titulos e mostra tela
        primaryStage.setTitle("Monopoly");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        
        run game = new run();
        initializer start = new initializer();
        monopoly.board tabuleiro = start.startBoard(numP,40);
        game.startGameLoop(numP,tabuleiro,scene,primaryStage);
    }

    private int showPlayerSelectionDialog(Stage primaryStage) {
        // Modal dialog for player selection
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Select Players");

        VBox dialogLayout = new VBox(10);
        dialogLayout.setStyle("-fx-padding: 10; -fx-alignment: center;");

        Label promptLabel = new Label("Enter number of players (1-6):");
        TextField playerInput = new TextField();
        Button confirmButton = new Button("Confirm");
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        confirmButton.setOnAction(e -> {
            try {
                int players = Integer.parseInt(playerInput.getText());
                if (players >= 1 && players <= 6) {
                    dialog.setUserData(players);
                    dialog.close();
                } else {
                    errorLabel.setText("Please enter a number between 1 and 6.");
                }
            } catch (NumberFormatException ex) {
                errorLabel.setText("Invalid input. Please enter a number.");
            }
        });

        dialogLayout.getChildren().addAll(promptLabel, playerInput, confirmButton, errorLabel);

        Scene dialogScene = new Scene(dialogLayout, 250, 150);
        dialog.setScene(dialogScene);
        dialog.showAndWait();

        // Return the number of players selected
        Object userData = dialog.getUserData();
        return userData != null ? (int) userData : 0;
    }

    // Carrega imagens e cria setup da janela do jogo
    private StackPane createGameLayout(Stage primaryStage) {
        imageManager.loadEssentialImages();
        ImageView boardViewer = createBoardViewer(primaryStage);
        StackPane root = new StackPane(boardViewer);
        //AnchorPane UI = createUI();
        Pane paneAux = new Pane();

        this.boardSize = boardViewer.getImage().getWidth();
        stepSize = boardSize / 23;

        // Pane pra posicionamento de players
        root.getChildren().add(paneAux);

        paneAux.prefWidthProperty().bind(boardViewer.fitWidthProperty());
        paneAux.prefHeightProperty().bind(boardViewer.fitHeightProperty());

        double xStartPercent = 0.225;
        double yStartPercent = 0.85;
        double xSpacingPercent = 0.02;

        for (int i = 0; i < monopoly.board.getPlayers(); i++) {
            players[i] = createPlayerViewer(boardViewer, i);

            players[i].layoutXProperty().bind(paneAux.widthProperty().multiply(xStartPercent + (i * xSpacingPercent)));
            players[i].layoutYProperty().bind(paneAux.heightProperty().multiply(yStartPercent));

            paneAux.getChildren().add(players[i]);
        }
        //root.getChildren().addAll(UI);
        return root;
    }

    private ImageView createBoardViewer(Stage primaryStage) {
        ImageView boardViewer = new ImageView(imageManager.getImage("board"));
        boardViewer.setPreserveRatio(true);
        boardViewer.fitHeightProperty().bind(primaryStage.heightProperty());
        return boardViewer;
    }

    private ImageView createPlayerViewer(ImageView boardViewer, int id) {
        String c = String.valueOf(id + 1);
        System.out.println("player" + c);
        ImageView playerViewer = new ImageView(imageManager.getImage("player" + c));
        playerViewer.setPreserveRatio(true);
        playerViewer.fitWidthProperty().bind(boardViewer.fitWidthProperty().multiply(0.05));
        playerViewer.fitHeightProperty().bind(boardViewer.fitHeightProperty().multiply(0.05));

        return playerViewer;
    }

    // Move player movement vezes
    public static void movePlayer(player player, int movement) {
        while (movement > 0) {
            int pos = player.getPosition();

            if (pos < 10)
                players[player.getId()].setTranslateY(players[player.getId()].getTranslateY() - stepSize);
            else if (pos < 20)
                players[player.getId()].setTranslateX(players[player.getId()].getTranslateX() + stepSize);
            else if (pos < 30)
                players[player.getId()].setTranslateY(players[player.getId()].getTranslateY() + stepSize);
            else if (pos < 40)
                players[player.getId()].setTranslateX(players[player.getId()].getTranslateX() - stepSize);

            movement--;
        }
        return;
    }

    private AnchorPane createUI() {
        AnchorPane uiPane = new AnchorPane();

        for (int i = 0; i < 4; i++) {
            moneyLabels[i] = new Label("Player " + (i + 1) + monopoly.board.getPlayer(i).getWallet());
            //moneyLabels[i].setStyle("-fx-font-size: 16px; -fx-background-color: #ffffff; -fx-padding: 5px;");
        }

        AnchorPane.setTopAnchor(moneyLabels[0], 10.0); // Top-left
        AnchorPane.setLeftAnchor(moneyLabels[0], 10.0);

        AnchorPane.setTopAnchor(moneyLabels[1], 10.0); // Top-right
        AnchorPane.setRightAnchor(moneyLabels[1], 10.0);

        AnchorPane.setBottomAnchor(moneyLabels[2], 10.0); // Bottom-left
        AnchorPane.setLeftAnchor(moneyLabels[2], 10.0);

        AnchorPane.setBottomAnchor(moneyLabels[3], 10.0); // Bottom-right
        AnchorPane.setRightAnchor(moneyLabels[3], 10.0);

        uiPane.getChildren().addAll(moneyLabels);

        return uiPane;

    }

    private void diceUI(int value1, int value2, StackPane root) {
        String base = "dice_";
        ImageView dice1 = new ImageView(imageManager.getImage(base + String.valueOf(value1)));
        ImageView dice2 = new ImageView(imageManager.getImage(base + String.valueOf(value2)));
        dice1.setFitWidth(100);
        dice1.setPreserveRatio(true);
        dice2.setFitWidth(100);
        dice2.setPreserveRatio(true);

        HBox diceBox = new HBox(10); // Spacing of 10 between dice
        diceBox.getChildren().addAll(dice1, dice2);
        diceBox.setAlignment(Pos.CENTER);

        System.out.println("dice");
        root.getChildren().addAll(diceBox);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> root.getChildren().remove(diceBox));
        pause.play();
        return;

    }

    // deprecado
    private GridPane setGridPane(Stage primaryStage) {
        GridPane grid = new GridPane();
        GridPane.setConstraints(grid, 11, 11);
        grid.setGridLinesVisible(true);
        return grid;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Button manageButton(String name, boolean on) {
        Button specialButton = new Button(name);
        buttonSwitch(specialButton, on);

        HBox gameLayout = new HBox();
        gameLayout.getChildren().add(specialButton);

        return specialButton;
    }

    private void buttonSwitch(Button specialButton, boolean on) {
        if (on) {
            specialButton.setDisable(false);
            specialButton.setOpacity(1);
        } else {
            specialButton.setDisable(true);
            specialButton.setOpacity(0.5);
        }
    }
}
