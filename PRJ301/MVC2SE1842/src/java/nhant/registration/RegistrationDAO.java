/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhant.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nhant.util.DBHelper;

/**
 *
 * @author ADMIN
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        boolean result = false;
        //declaration
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //(buoc so 12)
        try {
            //1. get connection object and check it not null
            con = DBHelper.getConnection();
            if (con != null) {
                //2. write SQL String
                String sql = "Select username "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ? ";
                //3. call create statement from connection object --> Statement object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. call execute query from statement --> Result
                rs = stm.executeQuery();
                //5. process Result
                if(rs.next()){
                    result = true;
                }//username and password are existed
            }//con is available
        } finally {
            //closed is required
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }
    
    public void searchLastname(String searchValue) throws SQLException, ClassNotFoundException{//don nhan tham so ten searchValue
        //lay hoac tim toan bo thong tin cua bang Registration voi dieu kien cot lastName co chua substring cua searchValue
        //declaration
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //(buoc so 12)
        try {
            //1. get connection object and check it not null
            con = DBHelper.getConnection();
            if (con != null) {
                //2. write SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";
                //3. call create statement from connection object --> Statement object
                stm = con.prepareStatement(sql);
                stm.setString(1,"%" + searchValue + "%");
                //4. call execute query from statement --> Result
                rs = stm.executeQuery();
                //5. process Result
                while(rs.next()){
                    //map data
                    //5.1 get data from Result Set
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set data to DTO object
                    //a.new DTO object
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, role);
                    //b.set data into DTO object
                    if(this.accounts == null){
                        this.accounts = new ArrayList<>();
                    }//accounts are not available
                    this.accounts.add(dto);
                }//traverse each row of table
            }//con is available
        } finally {
            //closed is required
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
