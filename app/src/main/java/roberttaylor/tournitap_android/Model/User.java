package roberttaylor.tournitap_android.Model;

/**
 * Created by Robert Taylor on 10/25/2017.
 */

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private String password;
    private String gender;
    private String email;
    private String userName;


    public User(){

    }
    public User(int id, String firstName, String lastName, String middleInitial, String password, String gender, String email, String userName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleInitial = middleInitial;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.userName = userName;

    }



    public User(String firstName, String lastName, String middleInitial, String password, String gender, String email, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleInitial = middleInitial;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.userName = userName;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
