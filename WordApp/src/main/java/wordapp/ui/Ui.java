package wordapp.ui;

import wordapp.dao.*;
import wordapp.dao.*;
import wordapp.domain.*;
import java.util.*;
import java.io.*;

public class Ui {
    
    //Tähän tulee graafinen käyttöliittymä myöhemmin. Nyt main-metodin alla on yksinkertainen tekstikäyttöliittymä muiden osioiden kokeilua varten.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   
        FileLexiconDao ld = returnWords(scanner);
        WordStudy study = new WordStudy(ld);
        studyWords(study, scanner);
        
    }
    
    static void studyWords(WordStudy study, Scanner scanner) {
        
        System.out.println("----------------------\nThis is the first version of the app\n");
        System.out.println("q=quit\n");
        System.out.println("What is the meaning of the word?\n");
        while (true) {
            System.out.println("");
            
            study.chooseNextWord();
            String word = study.returnCurrentWordAsString();
            if (word==null) {
                study.quitWordStudy();
                System.out.println("You have studied all the words");
                break;
            }
            System.out.println("word: " + word);
            
            System.out.print("answer: ");
            String answer = scanner.nextLine();
            
            if (answer.equals("q")) {
                study.quitWordStudy();
                break;
            }
            String currentMeanings = study.returnCurrentMeaningsAsString();
            if (study.isCorrect(answer)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong answer");
            }
            System.out.println("Possible meanings:" + currentMeanings);
        }
    }
    
    static FileLexiconDao returnWords(Scanner scanner) {
        String savedFile = "saved.ser";
        String originalFile = "file.txt";

        return new FileLexiconDao(savedFile, originalFile);
    }
}