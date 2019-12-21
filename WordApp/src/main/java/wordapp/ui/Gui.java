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
import javafx.scene.text.Font;

/**
 * Graphical user interface
 *
 */

public class Gui extends Application {
    
    private Scene firstScene;
    private Scene createScene;
    private Scene studyScene;
    
    private WordStudyService service;
    
    
    @Override
    public void init() {
        service = new WordStudyService();
    }
    
    @Override
    public void start(Stage primaryStage) {
        // Initialize "next" button for study scene already here.
        // It is used to to move from create or main scene to study scene
        Button next = new Button("Next");

        //main scene
        HBox startPane = new HBox(10);   
        startPane.setAlignment(Pos.CENTER);
        Button createNewStudyButton = new Button("New study");     
        createNewStudyButton.setMinWidth(150);
        createNewStudyButton.setOnAction(e-> {
            primaryStage.setScene(createScene);
            }
        );        
        if (service.savedExists()) {
            Button returnToSavedButton = new Button("Return to saved");  
            returnToSavedButton.setMinWidth(150);
            returnToSavedButton.setOnAction(e-> {
                service.load();
                primaryStage.setScene(studyScene);
                next.fire();
                }
            );
            startPane.getChildren().addAll(returnToSavedButton, createNewStudyButton);
        } else {
            startPane.getChildren().addAll(createNewStudyButton);
        }
        firstScene = new Scene(startPane, 700, 500);
        
        //create new study -scene
        VBox newStudyPane = new VBox(10);  
        HBox inputArea = new HBox(10);
        VBox.setMargin(newStudyPane, new Insets(10,10,10,10));
        newStudyPane.setAlignment(Pos.CENTER);
        Label inputLabel = new Label("How many most common words you want to study?");
        Label readingError = new Label("");
        TextField numberInput = new TextField();
        numberInput.setMaxWidth(100);
        Label check = new Label("");
        Button startNew = new Button("Start!");     
        startNew.setOnAction(e-> {        
            if (service.tryToCreateNew(numberInput.getText())) {
                primaryStage.setScene(studyScene);    
                next.fire();

            } else {
                readingError.setText("PLEASE ENTER AN INTEGER (RANGE=1-5381)");
            }

        });
        inputArea.getChildren().addAll(numberInput, startNew);
        inputArea.setAlignment(Pos.CENTER);
        newStudyPane.getChildren().addAll(inputLabel, readingError, inputArea);
        createScene = new Scene(newStudyPane, 700, 500);
        

        //study scene
        
        VBox study = new VBox(10); 
        HBox buttons = new HBox(10);

        study.setAlignment(Pos.CENTER);
        Label answerInputLabelTop = new Label("Write the meaning of the greek word!");
        Label answerInputLabelBottom = new Label();
        answerInputLabelBottom.setFont(new Font("Arial", 30));
        TextField answerInput = new TextField();
        
        answerInput.setMaxWidth(200);
        Button answer = new Button("Answer");
        answer.setMinWidth(80);
        next.setMinWidth(80);
        Button saveAndQuit = new Button("save and quit");
        Label correct = new Label("");
        Label definitionTop = new Label(" ");
        Label definitionBottom = new Label(" ");
        answer.setOnAction(e-> {            
            String meanings = service.getWordStudy().getCurrentMeanings();
            String spelling = "";
            if (service.getWordStudy().isCorrect(answerInput.getText())) {   
                if (service.getWordStudy().spellingMistake()) {
                    spelling = " There was propably a spelling mistake.";
                }
                correct.setText("Correct!" + spelling);   
                definitionBottom.setText(meanings);
            } else {      
                correct.setText("Wrong answer");  
                definitionBottom.setText(meanings);
            }              
            definitionTop.setText("Definition of the word: " + service.getWordStudy().getCurrentWord());                
            answerInput.setText("");
        });
        next.setOnAction(e-> {            
            answer.setMaxSize(100, 100);
            service.getWordStudy().chooseNextWord();
            correct.setText("");
            definitionTop.setText(" ");
            definitionBottom.setText(" ");
            if (service.getWordStudy().getCurrentWord() == null) {
                answerInputLabelTop.setText("You have studied all the words!");
            } else {
                answerInputLabelBottom.setText(service.getWordStudy().getCurrentWord()); 
            }         
            answerInput.setText("");
        });
        
        saveAndQuit.setOnAction(e-> {
            service.saveAndExit();
            
        });
        
        buttons.getChildren().add(answer);
        buttons.getChildren().add(next);
        buttons.setAlignment(Pos.CENTER);
        definitionBottom.setAlignment(Pos.CENTER);
        study.getChildren().addAll(answerInputLabelTop, answerInputLabelBottom, answerInput, buttons, correct, definitionTop, definitionBottom, saveAndQuit);
        studyScene = new Scene(study, 700, 500);        
        
        //stage        
        primaryStage.setScene(firstScene);  
        primaryStage.setTitle("WordApp");
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}
