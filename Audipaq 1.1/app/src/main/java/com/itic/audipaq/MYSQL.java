package com.itic.audipaq;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MYSQL
{
    static String host      = "192.168.1.75";
    static String baseDatos = "audipaq";
    static String usuario   = "root";
    static String password  = "";
    static String cadCon	= "jdbc:mysql://"+host+"/"+baseDatos;

    public static Connection con;
    public static Statement st;
    public static String Message;
    private static ResultSet rs;

    private static void ConexionDriver() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try{
            Class.forName( "com.mysql.jdbc.Driver").newInstance();
            con = (Connection) DriverManager.getConnection( cadCon, usuario, password);
            st = (Statement) con.createStatement();
            Message = "Conexion Exitosa";
        } catch (Exception e) {
            e.printStackTrace();
            Message = e.getMessage();
        }
    }

    public  Boolean Conectar(){
        try {
            ConexionDriver();
            if(Message=="Conexion Exitosa") return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void DBQuery(String sql) throws SQLException {
        rs = (ResultSet) st.executeQuery(sql);
    }

    public ResultSet Query (String sql){
        rs = null;
        try {
            DBQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs ;
    }

    public Boolean UpdateQuery (String sql){
        rs = null;
        try {
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean InsertQuery (String sql){
        rs = null;
        try {
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void cerrarConexion() {
        try {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e)
        {

        }

        //catch (java.sql.SQLException e)
       // {
           // e.printStackTrace();
        //}
    }
}
