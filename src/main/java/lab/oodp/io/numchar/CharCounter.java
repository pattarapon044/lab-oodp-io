package lab.oodp.io.numchar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharCounter {
	
	int numE = 0;
    int total = 0;
    
    

    public int getNumE() {
		return numE;
	}

	public int getTotal() {
		return total;
	}

	public void start() {
        printNumEsWithFileReader();
    }

    private void printNumEsWithFileReader() {
    	
    	//TODO: read file and count Es and total number of character, uncomment sysout below
    	File input1 = new File("input1.txt");
    	try (BufferedReader bfr = new BufferedReader(new FileReader(input1))) {
    		int read = -1;
    		while ((read = bfr.read()) != -1) {
    			char check = (char) read;
    			if (check == 'e' || (char) check == 'E')numE++;
    			total++;
    		}
    	}
    	catch (IOException e) {
    		System.out.println(e);
    	}

        System.out.println("Number of e/E's: " + numE + " out of " + total);
    }

    public static void main(String[] args) {
        new CharCounter().start();
    }

}
