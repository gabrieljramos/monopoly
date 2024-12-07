import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import java.util.*;

public class draw extends Application {

    public void start(Stage primaryStage) {
        GridPane grid = setGridPane(primaryStage);
        
        StackPane root = createGameLayout(primaryStage);
        Scene scene = new Scene(root);
        
        // background da tela
        BackgroundFill fill = new BackgroundFill(Color.web("#FFEE8C90"), null, null);
        root.setBackground(new Background(fill));
        root.getChildren().add(grid);
        // titulos e mostra tela
        primaryStage.setTitle("Monopoly");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private GridPane setGridPane(Stage primaryStage){
        GridPane grid = new GridPane();
        GridPane.setConstraints(grid, 11, 11);
        grid.setGridLinesVisible(true);
        return grid; 
    }

    private StackPane createGameLayout(Stage primaryStage) {
        imageManager.loadEssentialImages();
        ImageView boardViewer = createBoardViewer(primaryStage);
        ImageView playerViewer = createPlayerViewer(boardViewer);

        return new StackPane(boardViewer, playerViewer);
    }

    private ImageView createBoardViewer(Stage primaryStage) {
        ImageView boardViewer = new ImageView(imageManager.getImage("board"));
        boardViewer.setPreserveRatio(true);
        boardViewer.fitHeightProperty().bind(primaryStage.heightProperty());
        return boardViewer;
    }

    private ImageView createPlayerViewer(ImageView boardViewer) {
        ImageView playerViewer = new ImageView(imageManager.getImage("player1"));
        playerViewer.setPreserveRatio(true);
        playerViewer.fitWidthProperty().bind(boardViewer.fitWidthProperty().multiply(0.05));
        playerViewer.fitHeightProperty().bind(boardViewer.fitHeightProperty().multiply(0.05));
        
        return playerViewer;
    }

    public static void main(String[] args) {
        
        launch(args); 
    }
}
