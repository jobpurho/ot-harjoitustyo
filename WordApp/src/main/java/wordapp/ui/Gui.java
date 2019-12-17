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
        //main scene
        HBox startPane = new HBox(10);   
        startPane.setAlignment(Pos.CENTER);
        Button createNewStudyButton = new Button("New study");     
        createNewStudyButton.setOnAction(e-> {
            primaryStage.setScene(createScene);
            }
        );        
        if (service.savedExists()) {
            Button returnToSavedButton = new Button("Return to saved");  
            returnToSavedButton.setOnAction(e-> {
                service.load();
                primaryStage.setScene(studyScene);
                }
            );
            startPane.getChildren().addAll(returnToSavedButton, createNewStudyButton);
        } else {
            startPane.getChildren().addAll(createNewStudyButton);
        }
        firstScene = new Scene(startPane, 500, 500);

        
        //create new study -scene
        VBox newStudyPane = new VBox(10);  
        VBox.setMargin(newStudyPane, new Insets(10,10,10,10));
        newStudyPane.setAlignment(Pos.CENTER);
        Label inputLabel = new Label("How many most common words you want to study?\n");
        Label readingError = new Label("");
        TextField numberInput = new TextField();
        numberInput.setMaxWidth(100);
        Label check = new Label("");
        Button startNew = new Button("Start!");     
        startNew.setOnAction(e-> {        
            if (service.tryToCreateNew(numberInput.getText())) {
                primaryStage.setScene(studyScene);                
            } else {
                readingError.setText("PLEASE ENTER AN INTEGER (RANGE=1-5381)");
            }

        });
        newStudyPane.getChildren().addAll(inputLabel, readingError, numberInput, startNew);
        createScene = new Scene(newStudyPane, 500, 500);
        

        //study scene
        
        VBox study = new VBox(10);            
        study.setAlignment(Pos.CENTER);
        Label answerInputLabel = new Label("Click next to start learning greek vocabulary!");
        
        TextField answerInput = new TextField();
        answerInput.setMaxWidth(200);
        Button answer = new Button("Answer!");
        Button next = new Button("next");
        Button saveAndQuit = new Button("save and quit");
        Label correct = new Label("");
        Label definition = new Label(" \n ");
        answer.setOnAction(e-> {            
            if (service.getGreekWord() != null && !service.getWordStudy().answered()) {
                String meanings = service.getWordStudy().getCurrentMeaningsAsString();
                String spelling = "";
                if (service.checkAnswer(answerInput.getText())) {   
                    if (service.getWordStudy().spellingMistake()) {
                        spelling = " There was propably a spelling mistake.";
                    }
                    correct.setText("Correct!" + spelling);   
                    definition.setText("Definition of the word " + service.getGreekWord() +  ":\n" + meanings + "...");
                } else if (!service.getWordStudy().equals("First")) {      
                    correct.setText("Wrong answer");  
                    definition.setText("Definition of the word " + service.getGreekWord() +  ":\n" + meanings + "...");
                }                       
     
            }           
            answerInput.setText("");
        });
        next.setOnAction(e-> {
            service.setNext();
            correct.setText("");
            definition.setText(" \n ");
            if (service.getGreekWord() == null) {
                answerInputLabel.setText("You have studied all the words!");
            } else {
                answerInputLabel.setText("What is the meaning of the word " + service.getGreekWord()); 
            }         
            answerInput.setText("");
        });
        
        saveAndQuit.setOnAction(e-> {
            service.saveAndExit();
            
        });
        study.getChildren().addAll(answerInputLabel, answerInput, answer, next, correct, definition, saveAndQuit);
        studyScene = new Scene(study, 500, 500);        
        
        //stage        
        primaryStage.setScene(firstScene);  
        primaryStage.setTitle("WordApp");
        primaryStage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}
