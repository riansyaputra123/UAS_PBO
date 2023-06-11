/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg2109106078_rian.syaputra.ainun.naim_tugas_pbo;

import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class SYAPUTRAAINUNNAIM_TUGAS_PBO {
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Login lg = new Login();
                 lg.setVisible(true);
                 lg.pack();
                 lg.setLocationRelativeTo(null);
                 lg.setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
    }
}
