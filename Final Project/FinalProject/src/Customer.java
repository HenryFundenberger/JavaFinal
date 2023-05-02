public class Customer extends User {


    private String name;
    private int KCElectricID;
    private String phoneNumber;
    private String address;
    private String email;
    private String city;
    private String state;
    private String zipCode;

    public Customer(String name, int KCElectricID, String phoneNumber, String address, String city, String state, String zipCode, String email) {
        super(name, false);
        this.name = name;
        this.KCElectricID = KCElectricID;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.email = email;
    }


    public Customer (String name, int KCElectricID){
        super(name, false);
        this.name = name;
        this.KCElectricID = KCElectricID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getKCElectricID() {
        return KCElectricID;
    }

    public void setKCElectricID(int KCElectricID) {
        this.KCElectricID = KCElectricID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
