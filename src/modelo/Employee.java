package modelo;

public class Employee {
    private String id;
    private String name;
    private String address;
    private String locality;
    private String peopleLivingWith;
    private String currentHealthStatus;
    private String password;
    private boolean hadCovid;
    private String covidImpactLevel;
    private boolean hasComorbidities;
    private boolean familyWithComorbidities;
    private String dailyStatus;
    private boolean contactWithCovidPersons;
    private double dailyTemperature;
    private String role;

    // Constructor with parameters
    public Employee(String id, String name, String password, String address, String locality, String peopleLivingWith, String currentHealthStatus,
                    boolean hadCovid, String covidImpactLevel, boolean hasComorbidities, boolean familyWithComorbidities,
                    String dailyStatus, boolean contactWithCovidPersons, double dailyTemperature, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.address = address;
        this.locality = locality;
        this.peopleLivingWith = peopleLivingWith;
        this.currentHealthStatus = currentHealthStatus;
        this.hadCovid = hadCovid;
        this.covidImpactLevel = covidImpactLevel;
        this.hasComorbidities = hasComorbidities;
        this.familyWithComorbidities = familyWithComorbidities;
        this.dailyStatus = dailyStatus;
        this.contactWithCovidPersons = contactWithCovidPersons;
        this.dailyTemperature = dailyTemperature;
        this.role = role;
    }

    // Constructor
 

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getPeopleLivingWith() {
        return peopleLivingWith;
    }

    public void setPeopleLivingWith(String peopleLivingWith) {
        this.peopleLivingWith = peopleLivingWith;
    }

    public String getCurrentHealthStatus() {
        return currentHealthStatus;
    }

    public void setCurrentHealthStatus(String currentHealthStatus) {
        this.currentHealthStatus = currentHealthStatus;
    }

    public boolean isHadCovid() {
        return hadCovid;
    }

    public void setHadCovid(boolean hadCovid) {
        this.hadCovid = hadCovid;
    }

    public String getCovidImpactLevel() {
        return covidImpactLevel;
    }

    public void setCovidImpactLevel(String covidImpactLevel) {
        this.covidImpactLevel = covidImpactLevel;
    }

    public boolean isHasComorbidities() {
        return hasComorbidities;
    }

    public void setHasComorbidities(boolean hasComorbidities) {
        this.hasComorbidities = hasComorbidities;
    }

    public boolean isFamilyWithComorbidities() {
        return familyWithComorbidities;
    }

    public void setFamilyWithComorbidities(boolean familyWithComorbidities) {
        this.familyWithComorbidities = familyWithComorbidities;
    }

    public String getDailyStatus() {
        return dailyStatus;
    }

    public void setDailyStatus(String dailyStatus) {
        this.dailyStatus = dailyStatus;
    }

    public boolean isContactWithCovidPersons() {
        return contactWithCovidPersons;
    }

    public void setContactWithCovidPersons(boolean contactWithCovidPersons) {
        this.contactWithCovidPersons = contactWithCovidPersons;
    }

    public double getDailyTemperature() {
        return dailyTemperature;
    }

    public void setDailyTemperature(double dailyTemperature) {
        this.dailyTemperature = dailyTemperature;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // toString method
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", locality='" + locality + '\'' +
                ", peopleLivingWith='" + peopleLivingWith + '\'' +
                ", currentHealthStatus='" + currentHealthStatus + '\'' +
                ", hadCovid=" + hadCovid +
                ", covidImpactLevel='" + covidImpactLevel + '\'' +
                ", hasComorbidities=" + hasComorbidities +
                ", familyWithComorbidities=" + familyWithComorbidities +
                ", dailyStatus='" + dailyStatus + '\'' +
                ", contactWithCovidPersons=" + contactWithCovidPersons +
                ", dailyTemperature=" + dailyTemperature +
                ", role='" + role + '\'' +
                '}';
    }

    public String getId( ){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getPassword( ){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
