package com.javier.aforoseguro;

/**
 * Created by javi0 on 17/11/2016.
 */

public class Empleado {
    private String nomUsuario;
    private String password;

    public Empleado(String nomUsuario, String password) {
        this.nomUsuario = nomUsuario;
        this.password = password;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
