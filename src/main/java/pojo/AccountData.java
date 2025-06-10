package pojo;

public class AccountData {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String state;
    private String dob;            // date of birth, format: dd-MMMM-yyyy or placeholder
    private String licenseNumber;
    private String licenseExp;     // license expiry date
    private String participantId;
    private String joinDate;       // join date

    // Getters & setters

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
}
