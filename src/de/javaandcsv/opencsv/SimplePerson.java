package de.javaandcsv.opencsv;

/**
 * A copy of the Person class, but without the OpenCSV annotations
 * 
 * @author Bastian Bräunel
 *
 */
public class SimplePerson {

    private String name;
    private String email;
    private String phone;
    private String country;

    /**
     * Default constructor
     */
    public SimplePerson() {

    }

    /**
     * Constructor with parameters
     * 
     * @param name
     * @param email
     * @param phoneNo
     * @param country
     */
    public SimplePerson(String name, String email, String phone, String country) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.country = country;
    }

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
		return phone;
	}

	public void setPhone(String phoneNo) {
		this.phone = phoneNo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
        
}
