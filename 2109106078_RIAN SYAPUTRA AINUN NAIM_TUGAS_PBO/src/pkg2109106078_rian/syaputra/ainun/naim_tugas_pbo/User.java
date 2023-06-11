/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2109106078_rian.syaputra.ainun.naim_tugas_pbo;


/**
 *
 * @author LENOVO
 */
public class User {
    //properties
    private String username;
    private String password;
    
    
    //set and get
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username= username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    //constructor
    public User (String user, String pass){
        this.username = user;
        this.password = pass;
    }
}
