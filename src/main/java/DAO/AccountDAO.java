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

    public Account insertAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "INSERT INTO account VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, account.getAccount_id());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3, account.getPassword());

            preparedStatement.executeUpdate();
            return account;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
