package LRModel;

import java.io.Serializable;

public class Gestor implements Serializable {

    String username;
    String password;


    public Gestor(String username, String password){
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }







}

