package de.javaandcsv.opencsv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

/**
 * This class represents a Person, that will be parsen with OpenCSV
 * 
 * @author Bastian Bräunel
 *
 */
public class Person {
	
	// bind by name
	@CsvBindByName(column = "Name")
	private String name;
	
	@CsvBindByName(column = "Email", required = true)
	private String email;
	
	@CsvBindByName(column = "Phone")
	private String phoneNo;
	
	// don*t mix bind by position with bind by name
//	@CsvBindByPosition(position = 3)
	@CsvBindByName(column = "Country")
	private String country;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phoneNo;
	}

	public void setPhone(String phone) {
		this.phoneNo = phone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
