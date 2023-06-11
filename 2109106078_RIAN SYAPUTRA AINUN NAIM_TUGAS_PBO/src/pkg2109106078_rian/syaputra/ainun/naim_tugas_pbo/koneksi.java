/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2109106078_rian.syaputra.ainun.naim_tugas_pbo;

import java.sql.*;

public class koneksi {
    private static Connection koneksi;
    
    public static Connection getKoneksi() throws SQLException{
        String db = "jdbc:mysql://localhost:3306/db_sepatu";
        String user = "root";
        String pass = "";
        
        try{
            koneksi = DriverManager.getConnection(db, user, pass);
            return koneksi;
        }catch (SQLException e){
            System.out.println("Koneksi gagal : " + e.getMessage());
            return null;
        }
    }
}
