/**
* This program rolls dice
* CPSC 224 - Sring 2020
* Programming Assignment #1 (dice.java)
* Sources: Bruce Worobek yahtzee.cpp
* 
* @author Matthew Triebes 
* @version v1.0 2/2/20
*/

import java.lang.Math;
import java.io.*;

public class dice
{
    int sides;
    int roll;

    public dice ()
    {
        this.sides = 6;
        this.roll = 0;
    }

    public int roll (int sides)
    {
        int max = sides;
        int min = 1;
        int range = max - min + 1;

        this.roll = (int)(Math.random() * range) + min;

        return this.roll;
    }
}