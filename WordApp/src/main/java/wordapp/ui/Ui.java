package wordapp.ui;

import wordapp.dao.*;
import wordapp.domain.*;
import java.util.*;
import java.io.*;

public class Ui {
    
    //Tähän tulee graafinen käyttöliittymä myöhemmin. Nyt main-metodin alla on yksinkertainen tekstikäyttöliittymä muiden osioiden kokeilua varten.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   
        HashMap words = returnWords(scanner);
        WordStudy study = new WordStudy(words);
        studyWords(study, scanner);
        
    }
    
    static void studyWords(WordStudy study, Scanner scanner) {
        System.out.println("----------------------\nThis is the first version of the app\n");
        System.out.println("q=quit\n");
        System.out.println("What is the meaning of the word?\n");
        while (true) {
            System.out.println("");
            
            String word = study.giveNextWord();
            if (word==null) {
                System.out.println("You have studied all the words");
                break;
            }
            System.out.println("word: " + word);
            
            System.out.print("answer: ");
            String answer = scanner.nextLine();
            
            if (answer.equals("q")) {
                break;
            }
            
            if (study.isCorrect(answer)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong answer");
            }
            System.out.println("Possible meanings:" + study.returnCurrentMeaningsAsString());
        }
    }
    
    static HashMap returnWords(Scanner scanner) {
        String file = "file.txt";
        if (new File("save.txt").exists()) {
            System.out.println("Return to saved? (y=yes/n=no)");
            while (!scanner.nextLine().matches("y|n")) {
                if (scanner.nextLine().equals("y")) {
                    file = "file.txt";
                }
            }
        }
        return new FileWordApp(file).returnFileContent();
    }
}
