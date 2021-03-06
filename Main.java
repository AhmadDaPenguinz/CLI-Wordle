package com.company;

import java.util.*;

import static java.lang.Integer.valueOf;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner getWord = new Scanner(System.in);

        String solution = "await";
        String guess;
        do {

            System.out.println("Enter Guess: ");
            guess = getWord.nextLine();

        } while (guess.length() != 5);

      
        String lowerSolution = solution.toLowerCase();
        String lowerGuess = guess.toLowerCase();

      
        char[] lowerSolutionArray = lowerSolution.toCharArray();
        char[] lowerGuessArray = lowerGuess.toCharArray();

        
        Map<Character, List<Integer>> solutionMap = Main.createMap(lowerSolutionArray);
        Map<Character, List<Integer>> guessMap= Main.createMap(lowerGuessArray);

        // Example Solution Maps
        // a -> 2
        // l -> 1
        // o -> 1, 3
        // f -> 4
        // Example Guess Map
        // a -> 0, 2
        // w -> 1
        // i -> 3
        // t -> 4

       
        String[] result = new String[lowerGuessArray.length];

       
        for (Map.Entry<Character, List<Integer>> entry: guessMap.entrySet()) {
            char character = entry.getKey();
            List<Integer> indexes = entry.getValue();
            if(solutionMap.containsKey(character)){
                List<Integer> solutionIndexes = solutionMap.get(character);
                int numCharSolutionOccurrences = solutionIndexes.size();
                List<Integer> correctIndexes = new ArrayList<>();
                for(int i: indexes){
                    if(solutionIndexes.contains(i)){
                        correctIndexes.add(i);
                    }
                }
                for(int i: correctIndexes){
                    result[i] = "🟩";
                    
                    indexes.remove(valueOf(i));
                    numCharSolutionOccurrences--;
                }
                for(int i=0;i<indexes.size();i++){
                    if(i<numCharSolutionOccurrences){
                        result[indexes.get(i)] = "🟨";
                    } else{
                        result[indexes.get(i)] = "⬛️";
                    }
                }
            } else{
                for(int i: indexes){
                    result[i] = "⬛️";
                }
            }
        }

        for(String s: result){
            System.out.print(s);
            System.out.print(" ");
        }
        System.out.println();
    }

    private static Map<Character, List<Integer>> createMap(char[] lowerCharacterArray){
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i=0; i<lowerCharacterArray.length;i++) {
            List<Integer> indexes = map.getOrDefault(lowerCharacterArray[i], new ArrayList<>());
            indexes.add(i);
            map.put(lowerCharacterArray[i], indexes);
        }
        return map;
    }
}
