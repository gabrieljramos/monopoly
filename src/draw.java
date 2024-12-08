import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.*;

public class draw extends Application {
    private double boardSize;
    private double stepSize;

    ImageView players[] = new ImageView[monopoly.board.getPlayers()];

    public void start(Stage primaryStage) {
        
        StackPane root = createGameLayout(primaryStage);
        Scene scene = new Scene(root);
        
        // background da tela
        BackgroundFill fill = new BackgroundFill(Color.web("#FFEE8C90"), null, null);
        root.setBackground(new Background(fill));

        // titulos e mostra tela
        primaryStage.setTitle("Monopoly");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    // Carrega imagens e cria setup da janela do jogo
    private StackPane createGameLayout(Stage primaryStage) {
        imageManager.loadEssentialImages();
        ImageView boardViewer = createBoardViewer(primaryStage);
        StackPane root = new StackPane(boardViewer);
        Pane paneAux = new Pane();

        this.boardSize = boardViewer.getImage().getWidth();
        this.stepSize = boardSize / 23;
        
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
        
        return root;
    }

    private ImageView createBoardViewer(Stage primaryStage) {
        ImageView boardViewer = new ImageView(imageManager.getImage("board"));
        boardViewer.setPreserveRatio(true);
        boardViewer.fitHeightProperty().bind(primaryStage.heightProperty());
        return boardViewer;
    }

    private ImageView createPlayerViewer(ImageView boardViewer, int id) {
        String c = String.valueOf(id+1);
        System.out.println("player" + c);
        ImageView playerViewer = new ImageView(imageManager.getImage("player" + c));
        playerViewer.setPreserveRatio(true);
        playerViewer.fitWidthProperty().bind(boardViewer.fitWidthProperty().multiply(0.05));
        playerViewer.fitHeightProperty().bind(boardViewer.fitHeightProperty().multiply(0.05));
        
        return playerViewer;
    }

    // Move player movement vezes
    public void movePlayer(player player, int movement){
        while(movement > 0){
            int pos = player.getPosition();
            
            if(pos < 10)
                players[player.getId()].setTranslateY(players[player.getId()].getTranslateY()-stepSize);
            else if(pos < 20)
                players[player.getId()].setTranslateX(players[player.getId()].getTranslateX()+stepSize);
            else if(pos < 30)
                players[player.getId()].setTranslateY(players[player.getId()].getTranslateY()+stepSize);
            else if(pos < 40)
                players[player.getId()].setTranslateX(players[player.getId()].getTranslateX()-stepSize);
            
            movement--;
        }
        return;
    }
    
    // deprecado
    private GridPane setGridPane(Stage primaryStage){
        GridPane grid = new GridPane();
        GridPane.setConstraints(grid, 11, 11);
        grid.setGridLinesVisible(true);
        return grid; 
    }

    public static void main(String[] args) {
        launch(args);
    }
}
