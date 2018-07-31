package de.javaandcsv.opencsv;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * Use the mapping capabilities of the OpenCSV library to map csv to java objects
 * 
 * @author Bastian Bräunel
 *
 */
public class OpenCSVParser {

	/**
	 * Read the csv file and parse the content to objects
	 * 
	 * @param file	the csv file
	 */
	public void readPersonsFromCSV(File file) {

		try {
			// create the bufferd file reader
			Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));

			// create the parser
			CsvToBean<Person> csvToBean = new CsvToBeanBuilder(reader)
					.withType(Person.class)
					.withIgnoreLeadingWhiteSpace(true)
					.build();

			// parse all the persons from the csv to java objects
			List<Person> parsedPersons = csvToBean.parse();

			for(Person person: parsedPersons) {
			    System.out.println("Name : " + person.getName());
			    System.out.println("Email : " + person.getEmail());
			    System.out.println("PhoneNo : " + person.getPhone());
			    System.out.println("Country : " + person.getCountry());
			    System.out.println("==========================");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Parse csv file to any kind of object 
	 * 
	 * @param file the csv file
	 */
	public void parseObjectsWithoutAnnotations(File file) {
		
		try {
			// create a buffered reader
			Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
		
			// define the strategy for mapping the content of the csv file to type of object 
			// create a mapping strategy object
			 ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			// define the type of the object you want to map
            strategy.setType(SimplePerson.class);
            // define the fields to use
            String[] memberFieldsToBindTo = {"Name", "Email", "Phone", "Country"};
            // bind members to fields
            strategy.setColumnMapping(memberFieldsToBindTo);

            // create the bean parser
            CsvToBean<SimplePerson> csvToBean = new CsvToBeanBuilder(reader)
                    .withMappingStrategy(strategy)	// use the mapping strategy from above
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            
            // create an iterator to walk over the mapped item
            Iterator<SimplePerson> simplePersonIterator = csvToBean.iterator();

            // loop over the file
            while (simplePersonIterator.hasNext()) {
                SimplePerson myPerson = simplePersonIterator.next();
                
                System.out.println("Name : " + myPerson.getName());
                System.out.println("Email : " + myPerson.getEmail());
                System.out.println("PhoneNo : " + myPerson.getPhone());
                System.out.println("Country : " + myPerson.getCountry());
                System.out.println("---------------------------");
            }
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Serialize a list of objects and write them to a file
	 * 
	 * @param file
	 */
	public void writeObjectsToCSV(File file) {
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		
		// create a list of persons to write
		List<SimplePerson> personsToWrite = new ArrayList<>();
		personsToWrite.add(new SimplePerson("Donald Duck", "donald@duck.com", "1234", "Duckburg"));
		personsToWrite.add(new SimplePerson("Daisy Duck", "daisy@duck.com", "9876", "Duckburg"));
		
		try {
			// create a buffered writer
			Writer writer = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()));
						
			// configure the builder which maps the bean to the csv
			StatefulBeanToCsv<SimplePerson> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
			
			// write the csv file
            beanToCsv.write(personsToWrite);
            // flush the buffer
            writer.flush();
						
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvDataTypeMismatchException e) {
			e.printStackTrace();
		} catch (CsvRequiredFieldEmptyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Write a string array to a csv file
	 * 
	 * @param file
	 */
	public void writeFromArrayOfStrings(File file) {
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		
        try (
        	// get a buffered writer
            Writer writer = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()));

        	// configure the CSVWriter
            CSVWriter csvWriter = new CSVWriter(writer,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
        ) {
        	// generate header
            String[] headerRecord = {"Name", "Email", "Phone", "Country"};
            // write header
            csvWriter.writeNext(headerRecord);

            // write entries
            csvWriter.writeNext(new String[]{"Donald Duck", "donald@duck.com", "1234", "Duckburg"});
            csvWriter.writeNext(new String[]{"Daisy duck", "daisy@duck.com", "+9876", "Duckburg"});
            
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}