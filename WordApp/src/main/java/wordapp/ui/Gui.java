package wordapp.ui;

import wordapp.dao.*;
import wordapp.domain.*;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import wordapp.dao.FileLexiconDao;
import wordapp.dao.FileMounceDictionary;

public class Gui extends Application {
    
    private Scene firstScene;
    private Scene createScene;
    private Scene studyScene;
    
    private WordStudy wordStudy;
    private LexiconDao lexiconDao;
    private String greekWord = "First";
    
    private Timer timer;
    private boolean first;
    
    @Override
    public void start(Stage primaryStage) {
        //main scene
        HBox startPage = new HBox();   
        startPage.setAlignment(Pos.CENTER);
        Button createNewStudyButton = new Button("New study");     
        createNewStudyButton.setOnAction(e-> {
            primaryStage.setScene(createScene);
            }
        );        
        if (savedExists()) {
            Button returnToSavedButton = new Button("Return to saved");  
            returnToSavedButton.setOnAction(e-> {
                load();
                primaryStage.setScene(studyScene);
                }
            );
            startPage.getChildren().addAll(returnToSavedButton, createNewStudyButton);
        } else {
            startPage.getChildren().addAll(createNewStudyButton);
        }
        firstScene = new Scene(startPage, 500, 500);

        
        //create new study -scene
        VBox newStudyPane = new VBox();         
        newStudyPane.setAlignment(Pos.CENTER);
        Label inputLabel = new Label("You can limit the vocabulary to the most common words.\n"
                + "For example, if you type '100', you will study only the 100 most frequent words.");
        Label readingError = new Label("");
        TextField numberInput = new TextField();
        Label check = new Label("");
        Button startNew = new Button("Start!");     
        startNew.setOnAction(e-> {        
            if (readInteger(numberInput.getText())) {
                createNew(Integer.parseInt(numberInput.getText()));            
                primaryStage.setScene(studyScene);                
            } else {
                readingError.setText("PLEASE ENTER AN INTEGER");
            }

        });
        newStudyPane.getChildren().addAll(inputLabel, readingError, numberInput, startNew);
        createScene = new Scene(newStudyPane, 500, 500);
        

        //study scene
        
        VBox study = new VBox();            
        study.setAlignment(Pos.CENTER);
        Label answerInputLabel = new Label("Click next to start learning greek vocabulary!");
        
        TextField answerInput = new TextField();
        Button answer = new Button("Answer!");
        Button next = new Button("next");
        Button saveAndQuit = new Button("save and quit");
        Label correct = new Label("");
        Label definition = new Label("");
        answer.setOnAction(e-> {            
            if (greekWord != null && !wordStudy.answered()) {
                String meanings = wordStudy.returnCurrentMeaningsAsString();
                String spelling = "";
                if (checkAnswer(answerInput.getText())) {   
                    if (wordStudy.spellingMistake()) {
                        spelling = " There was propably a spelling mistake.";
                    }
                    correct.setText("Correct!" + spelling);   
                    definition.setText("\nDefinition of the word " + greekWord +  ":\n" + meanings + "...");
                } else if (!greekWord.equals("First")) {      
                    correct.setText("Wrong answer");  
                    definition.setText("\nDefinition of the word " + greekWord +  ":\n" + meanings + "...");
                }                       
     
            }           
            answerInput.setText("");
        });
        next.setOnAction(e-> {
            setNext();
            correct.setText("");
            definition.setText("");
            if (greekWord == null) {
                answerInputLabel.setText("You have studied all the words!");
            } else {
                answerInputLabel.setText("What is the meaning of the word " + greekWord); 
            }         
            answerInput.setText("");
        });
        
        saveAndQuit.setOnAction(e-> {
            saveAndExit();
            
        });
        study.getChildren().addAll(answerInputLabel, answerInput, answer, next, correct, definition, saveAndQuit);
        studyScene = new Scene(study, 500, 500);        
        
        //stage        
        primaryStage.setScene(firstScene);  
        primaryStage.setTitle("WordApp");
        primaryStage.show();
    }
    
    public void saveAndExit() {
        wordStudy.quitWordStudy();
        System.exit(0);
    }
    
    public boolean checkAnswer(String answer) {
        if (wordStudy.isCorrect(answer)) {
            return true;
        }
        return false;
    }
    
    public void setNext() {
        this.wordStudy.chooseNextWord();   
        greekWord = this.wordStudy.returnCurrentWordAsString();        
    }
    
    public boolean savedExists() {
        if (new File("saved.ser").exists()) {
            return true;
        }
        return false;
    }
    
    public void load() {
        this.lexiconDao = new FileLexiconDao("saved.ser");
        this.wordStudy = new WordStudy(this.lexiconDao);
    }
    
    public boolean readInteger(String input) {
        try {
            int number = Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void createNew(int number) {
        this.lexiconDao = new FileLexiconDao("saved.ser");
        FileMounceDictionary mounce = new FileMounceDictionary("dictionary.txt");
        mounce.filterTopWords(number);
        HashMap fileContent = mounce.returnFileContent();
        
        this.lexiconDao.setFileContent(fileContent);    
        this.wordStudy = new WordStudy(this.lexiconDao);
        
    }
    
    @Override
    public void stop() {
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
