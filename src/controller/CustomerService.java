package controller;

import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerService {
    public boolean addCustomer(Customer c) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String id);
    public Customer getCustomer(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Customer> getAllCustomers();
}