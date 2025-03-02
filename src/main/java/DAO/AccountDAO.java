package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    
    public List<Account> getAllAccounts(){
        Connection connection = ConnectionUtil.getConnection();
        List<Account> accounts = new ArrayList<>();
        try {
            //SQL logic
            String sql = "SELECT * FROM account";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"), 
                    rs.getString("username"),
                    rs.getString("password"));
                accounts.add(account);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return accounts;
    }

    /*Account ID is not passed so must be generated */

    public Account insertAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "INSERT INTO account (username, password) VALUES(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            //Password must be 4 or more characters long
            //Username must not be blank
            if(account.getUsername() == "" || account.getPassword().length() < 4){
                return null;
            }

            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account getAccountByUsername(String username){
        Connection connection = ConnectionUtil.getConnection();
        try{
            //SQL logic
            String sql = "SELECT * FROM account WHERE username = '" + username + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            ResultSet rs = preparedStatement.executeQuery();

            //return account if found, return 'null' otherwise
            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"), 
                rs.getString("username"), 
                rs.getString("password"));

                return account;
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account getAccountById(int id){
        Connection connection = ConnectionUtil.getConnection();
        try{
            //SQL logic
            String sql = "SELECT * FROM account WHERE account_id = " + id;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            //return account if found, return 'null' otherwise
            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"), 
                rs.getString("username"), 
                rs.getString("password"));

                return account;
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account loginAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();

        try {
            //SQL logic
            String sql = "SELECT * FROM account WHERE username = '"+ account.getUsername() + 
            "' AND password = '" + account.getPassword() + "'";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();
            //user name is not in use, or password didn't match
            while(rs.next()){
                Account userAccount = new Account(rs.getInt("account_id"), 
                rs.getString("username"), 
                rs.getString("password"));

                return userAccount;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
