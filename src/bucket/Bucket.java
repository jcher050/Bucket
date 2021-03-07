/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bucket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author cheryjeff
 */
public class Bucket {

    /**
     * @param args the command line arguments
     */
    private char minInitial;
    private char maxInitial;
    private LinkedList<String> list;
    private static File UserFileName = null;

    public Bucket(char min, char max) {
        minInitial = min;
        maxInitial = max;
        list = new LinkedList<String>();
    }

    
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner scnr = new Scanner(System.in);
        
        System.out.println("Enter the input file name to read the data from");

        String nName = scnr.nextLine();

        System.out.println("Enter the output file name to write the data to");

        String oName = scnr.nextLine();
        
        createaNewFile(oName);
        
        ArrayList<Bucket> buckets = new ArrayList<Bucket>(8);

        buckets.add(new Bucket('a', 'b'));

        buckets.add(new Bucket('c', 'c'));

        buckets.add(new Bucket('d', 'f'));

        buckets.add(new Bucket('g', 'k'));

        buckets.add(new Bucket('l', 'o'));

        buckets.add(new Bucket('p', 'r'));

        buckets.add(new Bucket('s', 's'));

        buckets.add(new Bucket('t', 'z'));
        
        String[] words=null;

        try {

            BufferedReader Bfr = new BufferedReader(new FileReader(nName));

            String tempfileword;

            String commasplit = ",";
            //loop through bufferreader seperating each words 
            while ((tempfileword = Bfr.readLine()) != null) {

                words = tempfileword.trim().replaceAll("\\s", "").toLowerCase().
                        split(commasplit);
                
            }
           // close bufferreader
            Bfr.close();

        } catch (FileNotFoundException ex) {

            System.out.println("Input file not found" + ex.getMessage());
        }
        // add file to the correct bucket
        addtobin(words, buckets);
        // call method to sort through the words in each buckets
        binSort(oName, buckets);
        System.out.println(" Done check your file for the sorted list");
        // close scanner 
        scnr.close();
    }
    
   //this method will sort each bin content
    private static void binSort(String Name, ArrayList<Bucket> buckets) 
            throws IOException 
    {
        // looping through the bucket list
        for (int i = 0; i < buckets.size(); i++) 
        {
            // take the list for each bucket
            LinkedList<String> list = buckets.get(i).getList();
            // declare and set an array to list
            String[] words = list.toArray(new String[list.size()]);
            // use sort method to organize the list in the array
            Arrays.sort(words);
            // the method and write sorted list to output file
            writeToFile(Name, words);

        }


    }
    
    //return linklist
    public LinkedList<String> getList() {

        return list;

    }
    
    // write the list to the output file after sorting it
    private static void writeToFile(String Name, String[] array) 
            throws IOException  {
        
    // declaring and setting the bufferedwriter 
         BufferedWriter out = new BufferedWriter(new FileWriter(Name, true));

    // looping through the array to output to file with a comma after each word 
            for (int i = 0; i < array.length; i++) 
            {
                    out.write(array[i] + ",");
            }
            
            //close buffer
                out.close();

    }

    
    
    // adding each string to the correct bin
    private static void addtobin(String[] inputArray, ArrayList<Bucket> buckets) {

        for (int i = 0; i < inputArray.length; i++) 
        {
           // find the correct bin to add each words in 

            String aword = inputArray[i];
            
            char letter = aword.toLowerCase().charAt(0);

            if (letter == 'a' || letter == 'b') {

                buckets.get(0).list.add(aword);

            } else if (letter == 'b') {

                buckets.get(1).list.add(aword);

            } else if (letter >= 'd' && letter <= 'f') {

                buckets.get(2).list.add(aword);

            } else if (letter >= 'g' && letter <= 'k') {

                buckets.get(3).list.add(aword);

            } else if (letter >= 'l' && letter <= 'o') {

                buckets.get(4).list.add(aword);

            } else if (letter >= 'p' && letter <= 'r') {

                buckets.get(5).list.add(aword);

            } else if (letter == 's') {

                buckets.get(6).list.add(aword);

            } else if (letter >= 't' && letter <= 'z') {

                buckets.get(7).list.add(aword);

            }

        }
        
        
    }
  
    // create a file for the output
    // check if the file existed then delete it if not create a new file
    private static void createaNewFile (String name) {
        UserFileName = new File(name);


        if (UserFileName.exists()) {

            UserFileName.delete();

        } else    
        {
                try 
                { UserFileName.createNewFile();
                } 
                catch (IOException ex)
                { System.out.println("Output File error. " + ex);
                }
        }

    }
    
    
   
    
}
