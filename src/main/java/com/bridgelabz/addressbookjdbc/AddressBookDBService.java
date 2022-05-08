package com.bridgelabz.addressbookjdbc;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBService {
    private static AddressBookDBService addressBookDBService;
    private PreparedStatement addressBookDataStatement;

    public static AddressBookDBService getInstance() {
        if (addressBookDBService == null)
            addressBookDBService = new AddressBookDBService();
        return addressBookDBService;
    }

    /*
     *purpose:connectiong JDBC connection
     * */
    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/addressbookservice?useSSL=false";
        String userName = "root";
        String password = "Archana@123";
        Connection connection;
        System.out.println("Connecting to database:" + jdbcURL);
        connection = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("Connection is successful!!!" + connection);
        return connection;
    }

    /*
     * Reading data from database
     */
    public List<Person> readData() {
        String query = "SELECT * from addressbook;";
        return this.getPersonDetailsFromDatabase(query);
    }

    //getting person details form pojo class Person
    private List<Person> getPersonDetailsFromDatabase(String query) {
        List<Person> personList = new ArrayList<Person>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            personList = this.getPersonData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }


    private List<Person> getPersonData(ResultSet resultSet) {
        List<Person> personList = new ArrayList<>();
        try {
            while (resultSet.next()) {

                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String zip = resultSet.getString("zip");
                String phonenumber= resultSet.getString("phonenumber");
                String email = resultSet.getString("email");
                personList.add(new Person(firstName,lastName,address,city,state,zip,phonenumber,email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;

    }

    /*
     * update conatcts in address book
     */
    public int updateContactNumber(String firstName, String contactNumber) {
        return this.updateAddressBookDataUsingStatement(firstName, contactNumber);

    }
    /*
     * update mobileNo by their firstName
     */
    private int updateAddressBookDataUsingStatement(String firstName, String phonenumber) {
        String sql = String.format("update addressbook set state = '%s' where firstName = '%s';", phonenumber, firstName);
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Person> getaddressBookData(String firstName) {
        List<Person> personList = null;
        if (this.addressBookDataStatement == null)
            this.prepareStatementForAddressBookData();
        try {
            addressBookDataStatement.setString(1, firstName);
            ResultSet resultSet = addressBookDataStatement.executeQuery();
            personList = this.getPersonData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    //display first name of table
    private void prepareStatementForAddressBookData() {
        try {
            Connection connection = this.getConnection();
            String sql = "SELECT * FROM addressbook WHERE firstName = ?";
            addressBookDataStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private List<Person> getAddressBookDataUsingDB(String sql) {
        List<Person> addressBookList = new ArrayList<>();
        try (Connection connection = this.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            addressBookList = this.getPersonData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressBookList;
    }

    //count people by their city
    public List<Person> countPeopleFromGivenCity(String city) {
        String sql = String.format("SELECT * FROM  addressbook WHERE city =  '%s';", city);
        return this.getAddressBookDataUsingDB(sql);
    }

    //main
    public static void main(String[] args) {
        AddressBookDBService addressBookDBService = new AddressBookDBService();
        List<Person> dataList = addressBookDBService.readData();
        System.out.println(dataList);
    }
}



