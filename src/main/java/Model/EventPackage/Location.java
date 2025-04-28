package Model.EventPackage;

public class Location {
    private String address;
    private String city;
    private String country;
    private String contactInfo;

    public Location(String address, String city, String country, String contactInfo) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.contactInfo = contactInfo;
    }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    @Override
    public String toString() {
        return address + ", " + city + ", " + country;
    }
}
