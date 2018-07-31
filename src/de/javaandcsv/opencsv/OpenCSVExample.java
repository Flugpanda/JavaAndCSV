package de.javaandcsv.opencsv;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * This class utilizes the OpenCSV library for reading and writing csv files
 * 
 * @author Bastian Bräunel
 *
 */
public class OpenCSVExample {

	/**
	 * Read csv file line by line and process each line as string array
	 * 
	 * @param file	the csv file
	 */
	public void retrieveRecorsAsStringArray(File file) {
		
		try {
			// get a buffered reader
			Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
			// create the OpenCSV reader
			CSVReader csvReader = new CSVReader(reader);
			
			String[] currentLineAsArray;
			
			// read every line and parse it as string array
			// stop if the result is a null value -> everything is read
			while ((currentLineAsArray = csvReader.readNext()) != null) {
				printCurrentLine(currentLineAsArray);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Read csv file line by line and skip the first line containing the header
	 * 
	 * @param file	the csv file
	 */
	public void retrieveRecorAndSkipHeader(File file) {
		
		try {
			Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
			// use the CSVReaderBuilder to create a custom reader
			// that skips the first row
			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
			
			String[] currentLineAsArray;
			
			// read every line and parse it as string array
			// stop if the result is a null value -> everything is read
			while ((currentLineAsArray = csvReader.readNext()) != null) {
				printCurrentLine(currentLineAsArray);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * print the content from the currently read line to the console
	 * 
	 * @param line	the line from the csv file as string array
	 */
	private void printCurrentLine(String[] line) {
        System.out.println("Name : " + line[0]);
        System.out.println("Email : " + line[1]);
        System.out.println("Phone : " + line[2]);
        System.out.println("Country : " + line[3]);
        System.out.println("==========================");
	}
}
