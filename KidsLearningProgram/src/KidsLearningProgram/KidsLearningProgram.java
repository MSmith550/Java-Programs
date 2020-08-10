package KidsLearningProgram;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Matt
 */
public class KidsLearningProgram extends Application {
    //sets the text field and the font and size for text field
    TextArea soundsWordField = new TextArea();
    TextArea wordField = new TextArea();
    TextArea numbersField = new TextArea();
    TextArea mathField = new TextArea();
    TextArea sentenceField = new TextArea();
    
    //creating the different scenes
    Scene mainMenu, sounds, numbers, heartWords, math, sentences;
    
    //variables for the math section, addition up to 20
    int randNumber, randNumber2, answer, randAnswerNumber;
    
    //lists for holding the data from files
    private List<String> sentenceList = new ArrayList<>();
    private List<String> wordList = new ArrayList<>();
    private List<String> soundList = new ArrayList<>();
    private List<String> blendList = new ArrayList<>();
    private List<String> shortVowelList = new ArrayList<>();
    
    //files names for the files
    private final String sentenceFile = "data/InformationFiles/Sentence.data.txt";
    private final String wordFile = "data/InformationFiles/WordList.data.txt";
    private final String soundFile = "data/InformationFiles/Sounds.data.txt";
    private final String blendFile = "data/InformationFiles/BlendSounds.data.txt";  
    private final String shortVowelFile = "data/InformationFiles/ShortVowel.data.txt";
    
    //variables for backgrounds
    Image mainMenuImage, soundsImage, numbersImage,heartWordsImage, mathImage, sentencesImage;
    ImageView mainMenuImageView, soundsImageView, numbersImageView, heartWordsImageView, mathImageView, sentencesImageView;
        
    
    @Override
    public void start(Stage primaryStage) {
        //setting up the backgrounds
        try {
            mainMenuImage = new Image(new FileInputStream("data/BackGrounds/MainMenuBackground.png"));   
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        mainMenuImageView = new ImageView(mainMenuImage);
        mainMenuImageView.fitWidthProperty().bind(primaryStage.widthProperty());
        mainMenuImageView.fitHeightProperty().bind(primaryStage.heightProperty());
        
        try {
            soundsImage = new Image(new FileInputStream("data/BackGrounds/LettersScene.png"));   
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        soundsImageView = new ImageView(soundsImage);
        soundsImageView.fitWidthProperty().bind(primaryStage.widthProperty());
        soundsImageView.fitHeightProperty().bind(primaryStage.heightProperty());
        
        try {
            numbersImage = new Image(new FileInputStream("data/BackGrounds/MathSceneBackground.png"));   
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        numbersImageView = new ImageView(numbersImage);
        numbersImageView.fitWidthProperty().bind(primaryStage.widthProperty());
        numbersImageView.fitHeightProperty().bind(primaryStage.heightProperty());
        
        try {
            heartWordsImage = new Image(new FileInputStream("data/BackGrounds/WordsSceneBackground.png"));   
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        heartWordsImageView = new ImageView(heartWordsImage);
        heartWordsImageView.fitWidthProperty().bind(primaryStage.widthProperty());
        heartWordsImageView.fitHeightProperty().bind(primaryStage.heightProperty());
        
        try {
            mathImage = new Image(new FileInputStream("data/BackGrounds/MathSceneBackground.png"));   
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        mathImageView = new ImageView(mathImage);
        mathImageView.fitWidthProperty().bind(primaryStage.widthProperty());
        mathImageView.fitHeightProperty().bind(primaryStage.heightProperty());
        
        try {
            sentencesImage = new Image(new FileInputStream("data/BackGrounds/SentenceBackground.png"));   
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        sentencesImageView = new ImageView(sentencesImage);
        sentencesImageView.fitWidthProperty().bind(primaryStage.widthProperty());
        sentencesImageView.fitHeightProperty().bind(primaryStage.heightProperty());
        
        //boarder pains for the different screens
        BorderPane mainBorder = new BorderPane();
        BorderPane soundsBorder = new BorderPane();
        BorderPane numbersBorder = new BorderPane();
        BorderPane heartWordsBorder = new BorderPane();
        BorderPane mathBorder = new BorderPane();
        BorderPane sentencesBorder = new BorderPane();
        
        //setting up the text display box for sounds
        soundsWordField.setEditable(false);
        soundsWordField.setWrapText(true);
        soundsWordField.setFont(Font.font("Serif", FontWeight.BOLD, 24));
        soundsWordField.setMaxSize(300, 50);
        //setting up the text display box for heart words
        wordField.setEditable(false);
        wordField.setWrapText(true);
        wordField.setFont(Font.font("Serif", FontWeight.BOLD, 24));
        wordField.setMaxSize(300, 50);
        //setting up the text display box for numbers
        numbersField.setEditable(false);
        numbersField.setWrapText(true);
        numbersField.setFont(Font.font("Serif", FontWeight.BOLD, 24));
        numbersField.setMaxSize(300, 50);
        //setting up the text display box for math
        mathField.setEditable(false);
        mathField.setWrapText(true);
        mathField.setFont(Font.font("Serif", FontWeight.BOLD, 24));
        mathField.setMaxSize(300, 50);
        //setting up the text display box for sentences
        sentenceField.setEditable(false);
        sentenceField.setWrapText(true);
        sentenceField.setFont(Font.font("Serif", FontWeight.BOLD, 24));
        sentenceField.setMaxSize(300, 50);      
        
        //MainMenu
        //main menu buttons
        Button soundsBtn = new Button("Sounds");
        Button heartWordsBtn = new Button("Heart Words");
        Button numbersBtn = new Button("Numbers");
        Button mathBtn = new Button("Math");
        Button sentenceBtn = new Button("Sentences");
        soundsBtn.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        heartWordsBtn.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        numbersBtn.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        mathBtn.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        sentenceBtn.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        //button actions, changing to the proper scenes when buttons are clicked
        soundsBtn.setOnAction(e -> primaryStage.setScene(sounds));
        heartWordsBtn.setOnAction(e -> primaryStage.setScene(heartWords));
        numbersBtn.setOnAction(e -> primaryStage.setScene(numbers));
        mathBtn.setOnAction(e -> primaryStage.setScene(math));
        sentenceBtn.setOnAction(e -> primaryStage.setScene(sentences));
        //main menu  layout
        Label welcome = new Label("Welcome To The Learning Program");
        welcome.setFont(Font.font("Serif", FontWeight.BOLD, 40));
        welcome.setTextFill(Color.BLUE);
        welcome.setWrapText(true);
        mainBorder.setTop(welcome);
        BorderPane.setAlignment(welcome, Pos.TOP_CENTER);
        VBox layoutMenu = new VBox(20);
        HBox buttons1 = new HBox(25);
        buttons1.setAlignment(Pos.CENTER);
        buttons1.getChildren().addAll(soundsBtn, heartWordsBtn, sentenceBtn);
        HBox buttons2 = new HBox(25);
        buttons2.setAlignment(Pos.CENTER);
        buttons2.getChildren().addAll(numbersBtn, mathBtn);
        layoutMenu.setAlignment(Pos.CENTER);
        layoutMenu.getChildren().addAll(buttons1, buttons2);
        StackPane Menu = new StackPane();
        Menu.getChildren().addAll(mainMenuImageView, mainBorder, layoutMenu);
        mainMenu = new Scene(Menu, 720, 480);
        
        //Sounds Scene
        //setting up the buttons for the sounds scene
        Button singleLettersBtn = new Button("Single Letters");
        Button blendSoundsBtn = new Button("Letter Blends");
        Button back = new Button("Back to Menu");
        singleLettersBtn.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        blendSoundsBtn.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        back.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        //button actions, changing to the proper scenes when buttons are clicked
        singleLettersBtn.setOnAction(e -> {
            ReadLetterSoundFiles();
            GetNewLetter();
        });
        blendSoundsBtn.setOnAction(e -> {
            ReadBlendFile();
            GetNewBlendSound();
                });
        back.setOnAction(e -> primaryStage.setScene(mainMenu));
        //Sounds Scene layout
        Label soundsTitle = new Label("Lets Work On Some Sounds");
        soundsTitle.setFont(Font.font("Serif", FontWeight.BOLD, 40));
        soundsTitle.setTextFill(Color.BLUE);
        soundsBorder.setTop(soundsTitle);
        BorderPane.setAlignment(soundsTitle, Pos.TOP_CENTER);
        VBox layoutSounds = new VBox(20);
        layoutSounds.setAlignment(Pos.CENTER);
        HBox soundButtons = new HBox(25);
        soundButtons.setAlignment(Pos.CENTER);
        soundButtons.getChildren().addAll(singleLettersBtn, blendSoundsBtn);
        layoutSounds.getChildren().addAll(soundsWordField, soundButtons, back);
        StackPane sound = new StackPane();
        sound.getChildren().addAll(soundsImageView, soundsBorder, layoutSounds);
        sounds = new Scene(sound, 720, 480);
        
        //HeartWords Scene
        //setting up the buttons for heart words
        Button newHeartWordsBtn = new Button("Heart Word");
        Button newShortVowelWordBtn = new Button("Short Vowel Word");
        Button back2 = new Button("Back to Menu");
        newHeartWordsBtn.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        newShortVowelWordBtn.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        back2.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        //setting the button actions
        newHeartWordsBtn.setOnAction(e -> {
            ReadWordFile();
            GetNewWord();
                });
        newShortVowelWordBtn.setOnAction(e -> {
            ReadShortVowelFile();
            GetNewShortVowelWord();
                });
        back2.setOnAction(e -> primaryStage.setScene(mainMenu));
        //layout for heartwords scene
        Label heartWordsLabel = new Label("Lets Work On Some Heart Words");
        heartWordsLabel.setFont(Font.font("Serif", FontWeight.BOLD, 40));
        heartWordsLabel.setTextFill(Color.BLUE);
        heartWordsBorder.setTop(heartWordsLabel);
        BorderPane.setAlignment(heartWordsLabel, Pos.TOP_CENTER);
        VBox heartWordsLayout = new VBox(20);
        heartWordsLayout.setAlignment(Pos.CENTER);
        HBox wordButtons = new HBox(25);
        wordButtons.setAlignment(Pos.CENTER);
        wordButtons.getChildren().addAll(newHeartWordsBtn, newShortVowelWordBtn);
        heartWordsLayout.getChildren().addAll(wordField, wordButtons, back2);
        StackPane heart = new StackPane();
        heart.getChildren().addAll(heartWordsImageView, heartWordsBorder, heartWordsLayout);
        heartWords = new Scene(heart, 720, 480);
        
        //Numbers Scene
        //setting up the buttons for numbers
        Button numberBtn = new Button("New Number");
        Button back3 = new Button("Back to Menu");
        numberBtn.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        back3.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        //setting the button action
        numberBtn.setOnAction(e -> {
            GetRandomNumber();
                });
        back3.setOnAction(e -> primaryStage.setScene(mainMenu));
        //layout for heartwords scene
        Label numberLabel = new Label("Lets Work On Some Numbers");
        numberLabel.setFont(Font.font("Serif", FontWeight.BOLD, 40));
        numberLabel.setTextFill(Color.BLUE);
        numbersBorder.setTop(numberLabel);
        BorderPane.setAlignment(numberLabel, Pos.TOP_CENTER);
        VBox numberLayout = new VBox(20);
        numberLayout.setAlignment(Pos.CENTER);
        numberLayout.getChildren().addAll(numbersField, numberBtn, back3);
        StackPane number = new StackPane();
        number.getChildren().addAll(numbersImageView, numbersBorder, numberLayout);
        numbers = new Scene(number, 720, 480);
        
        //Math Scene
        //setting up the buttons for math
        Button mathBtn1 = new Button("New Math Question");
        Button mathAnswerBtn = new Button("Math Answer");
        Button back4 = new Button("Back to Menu");
        mathBtn1.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        mathAnswerBtn.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        back4.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        mathAnswerBtn.setDisable(true);
        //setting the button actions
        mathBtn1.setOnAction((ActionEvent e) -> {
            Random rand = new Random();
            randNumber = rand.nextInt(10);
            randNumber2 = rand.nextInt(10);
            randAnswerNumber = rand.nextInt(2);
            switch (randAnswerNumber){
                case 0: mathField.setText(Integer.toString(randNumber) + " + " + Integer.toString(randNumber2) + " = ? ");
                        break;
                case 1: mathField.setText(Integer.toString(randNumber) + " - " + Integer.toString(randNumber2) + " = ? ");
                        break;
            }
            mathAnswerBtn.setDisable(false);
        });
        mathAnswerBtn.setOnAction((ActionEvent e) -> {
            switch (randAnswerNumber){
                case 0: answer = randNumber + randNumber2;
                        break;
                case 1: answer = randNumber - randNumber2;
                        break;
            }
            mathField.setText(mathField.getText() + " \n " + Integer.toString(answer));
            mathAnswerBtn.setDisable(true);
        });
        back4.setOnAction(e -> primaryStage.setScene(mainMenu));
        //layout for math scene
        Label mathLabel = new Label("Lets Work On Some Math");
        mathLabel.setFont(Font.font("Serif", FontWeight.BOLD, 40));
        mathLabel.setTextFill(Color.BLUE);
        mathBorder.setTop(mathLabel);
        BorderPane.setAlignment(mathLabel, Pos.TOP_CENTER);
        VBox mathLayout = new VBox(20);
        mathLayout.setAlignment(Pos.CENTER);
        HBox mathButtons = new HBox(25);
        mathButtons.setAlignment(Pos.CENTER);
        mathButtons.getChildren().addAll(mathBtn1, mathAnswerBtn);
        mathLayout.getChildren().addAll(mathField, mathButtons, back4);
        StackPane mathBack = new StackPane();
        mathBack.getChildren().addAll(mathImageView, mathBorder, mathLayout);
        math = new Scene(mathBack, 720, 480);
        
        //Sentence Scene
        //Setting up the buttons for the sentence scene
        Button newSentenceBtn = new Button("New Sentence");
        Button back5 = new Button("Back to Menu");
        newSentenceBtn.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        back5.setStyle("-fx-font-size: 2em; -fx-background-color: #00ff00");
        //setting the button actions
        newSentenceBtn.setOnAction(e -> {
            ReadSentenceFile();
            GetNewSentence();
        });
        back5.setOnAction(e -> primaryStage.setScene(mainMenu));
        //layout for the sentence scene
        Label sentenceLabel = new Label("Lets Work On Some Sentences");
        sentenceLabel.setFont(Font.font("Serif", FontWeight.BOLD, 40));
        sentenceLabel.setTextFill(Color.BLUE);
        sentencesBorder.setTop(sentenceLabel);
        BorderPane.setAlignment(sentenceLabel, Pos.TOP_CENTER);
        VBox sentenceLayout = new VBox(20);
        sentenceLayout.setAlignment(Pos.CENTER);
        sentenceLayout.getChildren().addAll(sentenceField, newSentenceBtn, back5);
        StackPane sentence = new StackPane();
        sentence.getChildren().addAll(sentencesImageView, sentencesBorder, sentenceLayout);
        sentences = new Scene(sentence, 720, 480);
                
        primaryStage.setTitle("Kids Learning Program");
        primaryStage.setScene(mainMenu);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    //method for getting a random word from the list
    public void GetNewWord(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(wordList.size());
        String randomElement = wordList.get(randomIndex);
        wordField.setText(randomElement);
    }
    
    //method for getting short vowel word
    public void GetNewShortVowelWord(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(shortVowelList.size());
        String randomElement = shortVowelList.get(randomIndex);
        wordField.setText(randomElement);
    }
    
    //method for getting random sentence from the list
    public void GetNewSentence(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(sentenceList.size());
        String randomElement = sentenceList.get(randomIndex);
        sentenceField.setText(randomElement);
    }
    
    //method for getting random letter from list
    public void GetNewLetter(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(soundList.size());
        String randomElement = soundList.get(randomIndex);
        soundsWordField.setText(randomElement);
    }
    
    //method for getting random blend sound from list
    public void GetNewBlendSound(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(blendList.size());
        String randomElement = blendList.get(randomIndex);
        soundsWordField.setText(randomElement);
    }
    
    //Method for getting random number
    public void GetRandomNumber(){
        Random rand = new Random();
        randNumber = rand.nextInt(100);
        numbersField.setText(Integer.toString(randNumber));
    }
    
    //Filling lists from files
    public void ReadLetterSoundFiles(){
        //reads the sound file
        try(Stream<String> fileDataStream = Files.lines(Paths.get(soundFile))) {
            soundList = fileDataStream.collect(Collectors.toList());
            
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
        }
    }
    
    public void ReadWordFile(){
        //reads the word file
        try(Stream<String> fileDataStream = Files.lines(Paths.get(wordFile))) {
            wordList = fileDataStream.collect(Collectors.toList());
            
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
        }
    }
    
    public void ReadSentenceFile(){
        //reads the sentence file
        try(Stream<String> fileDataStream = Files.lines(Paths.get(sentenceFile))) {
            sentenceList = fileDataStream.collect(Collectors.toList());
            
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
        }
    }
    
    public void ReadBlendFile(){
        //reads the blend file
        try(Stream<String> fileDataStream = Files.lines(Paths.get(blendFile))) {
            blendList = fileDataStream.collect(Collectors.toList());
            
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
        }
    }
    
    public void ReadShortVowelFile(){
        //reads the short vowel file
        try(Stream<String> fileDataStream = Files.lines(Paths.get(shortVowelFile))) {
            shortVowelList = fileDataStream.collect(Collectors.toList());
            
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
        }
    }
}
