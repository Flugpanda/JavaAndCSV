package de.javaandcsv.apache;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
 * This class utilizes the Apache Commons CSV for reading and writing csv files
 * 
 * @author Bastian Bräunel
 *
 */
public class ApacheCSVReader {

	/**
	 * Read a csv file line by line and retrieve informations by the number of the
	 * row
	 * 
	 * @param csvFile the csv file
	 */
	public void accessCSVByRecordNumber(File csvFile) {

		try {
			// create a buffered reader
			Reader reader = Files.newBufferedReader(Paths.get(csvFile.getAbsolutePath()));
			// read and parse the file
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

			// print the inforamtion
			printCSVInformation(csvParser);

		} catch (IOException e) {
			System.err.println(this.getClass().toString() + "accessCSVByRecordNumber: Can't access the file at: "
					+ csvFile.getAbsolutePath());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Read a csv file an auto detecting the header at the first line
	 * 
	 * @param file the csv file
	 */
	public void accessCSVWithHeader(File file) {

		try {
			Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));

			// configure the reader to treat the first line as header
			CSVParser parser = new CSVParser(reader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			printCSVInformationWithHeader(parser);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Read a csv file configured with a custom header
	 * 
	 * @param file the csv file
	 */
	public void accessCSVWithCustomHeader(File file) {

		try {
			Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));

			// configure the reader to treat the first line as header
			CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("Name", "Email", "Phone", "Country")
					.withIgnoreHeaderCase().withTrim());

			printCSVInformationWithHeader(parser);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * prints all information from a parsed csv file
	 * 
	 * @param parser the parser
	 */
	private void printCSVInformation(CSVParser parser) {
		// walk over every line and print the content
		for (CSVRecord csvRecord : parser) {
			// Accessing Values by Column Index
			String name = csvRecord.get(0);
			String email = csvRecord.get(1);
			String phone = csvRecord.get(2);
			String country = csvRecord.get(3);

			System.out.println("Record No - " + csvRecord.getRecordNumber());
			System.out.println("---------------");
			System.out.println("Name : " + name);
			System.out.println("Email : " + email);
			System.out.println("Phone : " + phone);
			System.out.println("Country : " + country);
			System.out.println("---------------\n\n");
		}
	}

	/**
	 * write a csv file from scratch
	 * 
	 * @param file the output destination
	 */
	public void writeCSVFile(File file) {
		// check if the file exists
		// otherwise create a new file
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			// get a buffered writer
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()));

			// configure the printer with the the header
			CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
					.withHeader("ID", "Name", "Designation", "Company")
					.withDelimiter('|'));

			// create csv entries by hand
			csvPrinter.printRecord("1", "Sundar Pichai ♥", "CEO", "Google");
			csvPrinter.printRecord("2", "Satya Nadella", "CEO", "Microsoft");
			csvPrinter.printRecord("3", "Tim cook", "CEO", "Apple");

			// create csv entry from Array
			csvPrinter.printRecord(Arrays.asList("4", "Mark Zuckerberg", "CEO", "Facebook"));

			// flush writer
			csvPrinter.flush();
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * prints all information from a parsed csv file using the header
	 * 
	 * @param parser the parser
	 */
	private void printCSVInformationWithHeader(CSVParser parser) {
		// walk over every line and print the content
		for (CSVRecord csvRecord : parser) {
			// Accessing values by Header names
			String name = csvRecord.get("Name");
			String email = csvRecord.get("Email");
			String phone = csvRecord.get("Phone");
			String country = csvRecord.get("Country");

			System.out.println("Record No - " + csvRecord.getRecordNumber());
			System.out.println("---------------");
			System.out.println("Name : " + name);
			System.out.println("Email : " + email);
			System.out.println("Phone : " + phone);
			System.out.println("Country : " + country);
			System.out.println("---------------\n\n");
		}
	}
}
