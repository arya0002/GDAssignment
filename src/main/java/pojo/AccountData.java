package pojo;

public class AccountData {
    // For both tests
    private String caseName;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;

    // For account creation only
    private String email;
    private String phone;
    private String state;
    private String dob;
    private String licenseNumber;
    private String licenseExp;
    private String participantId;
    private String joinDate;

    // Getters & Setters for all fields

    public String getCaseName() { return caseName; }
    public void setCaseName(String caseName) { this.caseName = caseName; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public String getLicenseExp() { return licenseExp; }
    public void setLicenseExp(String licenseExp) { this.licenseExp = licenseExp; }

    public String getParticipantId() { return participantId; }
    public void setParticipantId(String participantId) { this.participantId = participantId; }

    public String getJoinDate() { return joinDate; }
    public void setJoinDate(String joinDate) { this.joinDate = joinDate; }

    @Override
    public String toString() {
        return caseName != null ? caseName : (firstName + " " + lastName);
    }
}
