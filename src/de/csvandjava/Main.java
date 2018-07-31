package de.csvandjava;

import java.io.File;

import de.javaandcsv.apache.ApacheCSVExample;
import de.javaandcsv.opencsv.OpenCSVExample;
import de.javaandcsv.opencsv.OpenCSVParser;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public class Main {
	
	public static void main(String[] args) {
// Apache
		File csvFile = new File("res/CSV_without_header.csv");
		File csvFileWithHeader = new File("res/CSV_with_header.csv");
		File csvOutputFile = new File("res/CSV_output.csv");
		File csvSimplePersonOutputFile = new File("res/CSV_simple_person.csv");
		
		ApacheCSVExample apacheCSVReader = new ApacheCSVExample();
		
		apacheCSVReader.accessCSVByRecordNumber(csvFile);
		apacheCSVReader.accessCSVWithCustomHeader(csvFileWithHeader);
		apacheCSVReader.accessCSVWithHeader(csvFileWithHeader);
		
		apacheCSVReader.writeCSVFile(csvOutputFile);

// OpenCSV		
		OpenCSVExample openCSV = new OpenCSVExample();
		OpenCSVParser openCSVParser = new OpenCSVParser();
		
		openCSV.retrieveRecorsAsStringArray(csvFile);
		openCSV.retrieveRecorAndSkipHeader(csvFileWithHeader);
		
		openCSVParser.readPersonsFromCSV(csvFileWithHeader);
		openCSVParser.parseObjectsWithoutAnnotations(csvFileWithHeader);
//		
		openCSVParser.writeObjectsToCSV(csvSimplePersonOutputFile);
//		openCSVParser.writeFromArrayOfStrings(csvSimplePersonOutputFile);
		openCSV.retrieveRecorAndSkipHeader(csvSimplePersonOutputFile);
	}
}
