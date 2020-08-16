package javafxapplication2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Matt
 */
public class LearningV2 extends Application {
    private List<String> pictureList = new ArrayList<>();
    private final String pictureFile = "data/pictureFiles.txt";
    
    Image background, newPicture;
    ImageView image;
    ImageView picture = new ImageView();
    
    @Override
    public void start(Stage primaryStage) {
        
        try {
            background = new Image(new FileInputStream("data/BackGround.jpg"));
        }catch (FileNotFoundException ex){
            System.out.println(ex);
        }        
        
        image = new ImageView(background);
        image.fitWidthProperty().bind(primaryStage.widthProperty());
        image.fitHeightProperty().bind(primaryStage.heightProperty());
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        
        Button nextBtn = new Button("Next");
        nextBtn.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        
        nextBtn.setOnAction((ActionEvent e) -> {
            ReadPictureFile();
            NewPicture();
            picture.setImage(newPicture);
            grid.add(picture, 0, 0);
        });
        
        grid.add(nextBtn, 0, 1);
        
        StackPane root = new StackPane();
        root.getChildren().addAll(image, grid);
        
        Scene scene = new Scene(root, 720, 480);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void NewPicture(){
        try {
            Random rand = new Random();
            int randomIndex = rand.nextInt(pictureList.size());
            String randomPicture = pictureList.get(randomIndex);
            newPicture = new Image(new FileInputStream("data/pictures/" + randomPicture));
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
    private void ReadPictureFile(){
        try(Stream<String> fileDataStream = Files.lines(Paths.get(pictureFile))) {
            pictureList = fileDataStream.collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}
