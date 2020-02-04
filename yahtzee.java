/**
* This program play yahtzee
* CPSC 224 - Sring 2020
* Programming Assignment #1 (yahtzee.java)
* Sources: Bruce Worobek yahtzee.cpp
* 
* @author Matthew Triebes 
* @version v1.0 2/2/20
*/

import java.io.*;
import java.util.Scanner;

public class yahtzee
{
    public static void main(String args[]) // ------------------------------------------------------------------ Main
    {
        // Runs the whole game of Yahtzee
        dice d6 = new dice();

        Scanner input = new Scanner(System.in);
        final int num_dice = 5;

        String playAgain = "y";
        int turn = 0;
        String keep = "";
        int hand[] = new int[num_dice];

        while (playAgain.equals("y"))
        {
            keep = "nnnnn";
            turn = 1;
            while (turn < 4 & !keep.equals("yyyyy")) 
            {  
                for (int i = 0; i < num_dice; i++) {
                    if (keep.charAt(i) != 'y') {
                        hand[i] = d6.roll(6);
                    }
                }
                
                System.out.print("Your Roll Was: ");
                for (int i = 0; i < num_dice; i++){
                    System.out.print(hand[i] + " ");
                }
                
                System.out.println();
                if (turn < 3) {
                    System.out.print("Enter the Dice you want to keep (y or n): ");
                    keep = input.nextLine();
                }
                turn++;
            }

            sort(hand, num_dice);
            System.out.print("Here is your sorted hand : ");
            for (int i = 0; i < num_dice; i++)
            {
                System.out.print(hand[i] + " ");
            }
            
            System.out.println();

            for (int i = 1; i <=6; i++)
            {
                int cur = 0;
                for (int j = 0; j < 5; j++)
                {
                    if (hand[j] == i)
                        cur++;
                }
                System.out.print("Score " + i * cur + " on the ");
                System.out.print(i + " line");
                System.out.println();
            }

            if (maxOfAKindFound(hand) >= 3)
            {
                System.out.print("Score " + totalAllDice(hand) + " on the ");
                System.out.print("3 of a Kind line");
                System.out.println();
            }
            else 
                System.out.println("Score 0 on the 3 of a Kind line");

            if (maxOfAKindFound(hand) >= 4)
            {
                System.out.print("Score " + totalAllDice(hand) + " on the ");
                System.out.print("4 of a Kind line");
                System.out.println();
            }
            else 
                System.out.println("Score 0 on the 4 of a Kind line");

            if (fullHouseFound(hand))
                System.out.println("Score 25 on the Full House line");
            else
                System.out.println("Score 0 on the Full House line");

            if (maxStraightFound(hand) >= 4)
                System.out.println("Score 30 on the Small Straight line");
            else
                System.out.println("Score 0 on the Small Straight line");

            if (maxStraightFound(hand) >= 5)
                System.out.println("Score 40 on the Large Straight line");
            else
                System.out.println("Score 0 on the Large Straight line");

            if (maxOfAKindFound(hand) >= 5)
                System.out.println("Score 50 on the Yahtzee line");
            else
                System.out.println("Score 0 on the Yahtzee line");

            System.out.print("Score " + totalAllDice(hand) + " on the ");
            System.out.print("Chance line");
            System.out.println();
            
            System.out.println("Enter 'y' to play again: ");
            playAgain = input.nextLine();
        
        } // end of while
    } // end of main

    public static void sort(int array[], int size) // ------------------------------------------------------------------ Sort Function (Bubble Sort)
    {
        boolean swap = false;
        int temp = 0;
        do {
            swap = false;
            for (int i = 0; i < (size - 1); i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swap = true;
                }
            }
        } while (swap);
    }

    public static int maxOfAKindFound(int hand[]) // ------------------------------------------------------------------ Max of a Kind
    {
        int max = 0;
        int cur = 0;
        for (int dieValue = 1; dieValue <= 6; dieValue++) {
            cur = 0;
            for (int diePosition = 0; diePosition < 5; diePosition++) {
                if (hand[diePosition] == dieValue)
                    cur++;
            }
            if (cur > max)
                max = cur;
        }
        return max;
    }

    public static int totalAllDice(int hand[]) // ------------------------------------------------------------------ Dice Total                                          
    {
        int total = 0;
        for (int i = 0; i < 5; i++) {
            total += hand[i];
        }
        return total;
    }

    public static int maxStraightFound(int hand[]) // ------------------------------------------------------------------ Max Straight
    {                                              // Length of longest straight found in hand 
        int max = 1;
        int cur = 1;
        for (int i = 0; i < 4; i++) {
            if (hand[i] + 1 == hand[i + 1]) // jump of 1
                cur++;
            else if (hand[i] + 1 < hand[i + 1]) // jump of >= 2
                cur = 1;
            if (cur > max)
                max = cur;
        }
        return max;
    }

    public static boolean fullHouseFound(int hand[]) // ------------------------------------------------------------------ Full House
    {   
    boolean foundFH = false;
    boolean found3K = false;
    boolean found2K = false;
    int currentCount ;
    for (int dieValue = 1; dieValue <=6; dieValue++)
    {
        currentCount = 0;
        for (int diePosition = 0; diePosition < 5; diePosition++)
        {
            if (hand[diePosition] == dieValue)
                currentCount++;
        }
        if (currentCount == 2)
            found2K = true;
        if (currentCount == 3)
            found3K = true;
    }
    if (found2K && found3K)
        foundFH = true;
    return foundFH;
    }

} //end of class

