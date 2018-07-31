package de.csvandjava;

import java.io.File;

import de.javaandcsv.apache.ApacheCSVReader;

/**
 * 
 * @author Bastian Bräunel
 *
 */
public class Main {
	
	public static void main(String[] args) {

		File csvFile = new File("res/CSV_without_header.csv");
		File csvFileWithHeader = new File("res/CSV_with_header.csv");
		File csvOutputFile = new File("res/CSV_output.csv");
		
		ApacheCSVReader apacheCSVReader = new ApacheCSVReader();
		
		apacheCSVReader.accessCSVByRecordNumber(csvFile);
		apacheCSVReader.accessCSVWithCustomHeader(csvFileWithHeader);
		apacheCSVReader.accessCSVWithHeader(csvFileWithHeader);
		
		apacheCSVReader.writeCSVFile(csvOutputFile);
	}
}
