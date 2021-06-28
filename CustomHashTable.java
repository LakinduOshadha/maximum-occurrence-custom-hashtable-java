package HashTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * finds out which character occurs the maximum number of times
 * and the number of its occurrence, in the given string.
 * If two characters occur equal number of times, output will be the character with the lower ASCII value.
 * For example, if your string was: aaaaAAAA, your output would be: A 4
 *
 * @author Lakindu Oshadha (lakinduoshadha98@gmail.com)
 */
public class CustomHashTable {

    // Initializing global parameters (Customized)
    private Entry[] hashArr;
    private int asciiMax = 126;
    private int size = asciiMax;
    private int[] count = new int[asciiMax];    // To track the count of each Character

    /**
     * Inner class for Entry
     */
    private class Entry {
        // Initializing parameters
        int key;
        char value;
        Entry next;

        /**
         * constructor for Entry
         * @param key the key
         * @param value the value
         */
        public Entry(int key, char value) {
            this.key = key;
            this.value = value;
            next = null;
        }

        /**
         * Another constructor for Entry
         *
         */
        public Entry() {
            next = null;
        }

        /**
         * getter for key
         *
         * @return
         */
        public int getKey() {
            return key;
        }

        /**
         * getter for value
         *
         * @return
         */
        public char getValue() {
            return value;
        }
    }

    /**
     * Constructor for CustomHashTable
     *
     */
    public CustomHashTable() {
        hashArr = new Entry[size];
        for(int i = 0; i < size; i++) {
            hashArr[i] = new Entry();
        }
    }

    /**
     * Finds the index of a given key
     *
     * @param key The key which the hashIndex should be found
     * @return  The index
     */
    public int findHash(int key) {
        return key%size;
    }

    /**
     * Adds new entry to the table
     *
     * @param key The key value of the entru
     * @param value the value of the entry
     */
    public void add(int key, char value) {
        int hashIndex = findHash(key);  //finds the corresponding hash index
        Entry arrVal = hashArr[hashIndex];
        // Creating the new Entry
        Entry newEntry = new Entry(key,value);
        // Setting the links
        newEntry.next = arrVal.next;
        arrVal.next = newEntry;
        // Increases the count of the relevant index
        count[hashIndex]++;
    }

    /**
     * Gives the character in given index
     *
     * @param index The index which the character should get
     * @return  the character of the given index
     */
    public char getCharacter(int index) {
        return hashArr[index].next.getValue();
    }

    /**
     * getter for count
     * @param index Index which the count of the entries should get
     * @return  the count of entries for corresponding index
     */
    public int getCount(int index) {
        return count[index];
    }

    /**
     * Finds and print the The character which occurs the maximum number of times
     * & The number of its occurrence
     *
     */
    public void printOutput() {
        // Initializing local variables
        int maxIndex = 0;
        int maxCount = 0;
        // Finding the max count and the index which corresponds to it
        for(int i = 0; i < size; i++) {
            if(maxCount < getCount(i)) {
                maxCount = getCount(i);
                maxIndex = i;
            }
        }
        // Printing the results
        System.out.println("The character which occurs the maximum number of times : " + getCharacter(maxIndex) );
        System.out.println("The number of occurrence : " + maxCount);

    }


    /**
     * main
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // Giving a brief intro to user
        System.out.print("This program will find The character which occurs the maximum number of times.\n" +
                "& The number of its occurrence in a given String Using a custom HashTable.\n" +
                "Enter the string: " );

        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String str= reader.readLine();
        // Creating a new object of CustomHashTable
        CustomHashTable hashTable = new CustomHashTable();

        // Adding entries to the created hashtable
        if(str.length() <= 1000) {
            int[] arr = new int[str.length()];
            for (int i = 0; i < str.length();i++) {
                hashTable.add((int)str.charAt(i), (char)str.charAt(i) );    // Adding entries using add method
            }

        } else {
            System.out.println("ERROR : The maximum length of the string can be 1000"); //Error : String exceeds
                                                                                            // the length limit
        }

        // Finds and print the results
        hashTable.printOutput();
    }
}
