package mx.itesm.proyecto_final_libreria_online_srb;

import java.io.Serializable;

/**
 * Created by saul on 25/2/2017.
 */

public class Usuario implements Serializable {
    private String username;
    private String password;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
