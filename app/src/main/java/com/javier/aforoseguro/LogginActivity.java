package com.javier.aforoseguro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Iterator;

public class LogginActivity extends AppCompatActivity {
    public static final String CLAVE_AFORO = "password";
    EditText txt_user = null;
    EditText txt_pass = null;
    EditText txt_aforo = null;

    Empleado[] empleados = {
            new Empleado("cboto", "1234abc"),
            new Empleado("acarrasco", "password"),
            new Empleado("dgonzalez", "1111bbb"),
            new Empleado("mhalys", "6161fff"),
            new Empleado("bprieto", "9999zzz"),
            new Empleado("grodriguez", "6789ooo"),
            new Empleado("drosino", "4321jjj"),
            new Empleado("ssantana", "2222ttt")};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);
        txt_user = (EditText) findViewById(R.id.txt_user);
        txt_pass = (EditText) findViewById(R.id.txt_pass);
        txt_aforo = (EditText) findViewById(R.id.txt_aforo);

    }

    public void confirmar(View v) {

        if (txt_user.getText().toString().equals("")) {
            Toast.makeText(this, getString(R.string.toast_introuser), Toast.LENGTH_SHORT).show();
        } else if (txt_pass.getText().toString().equals("")) {
            Toast.makeText(this, getString(R.string.toast_intropass), Toast.LENGTH_SHORT).show();
        } else if (txt_aforo.getText().toString().equals("")) {
            Toast.makeText(this, getString(R.string.toast_introaforo), Toast.LENGTH_SHORT).show();
        } else {
            Boolean encontrado = false;
            for (int i = 0; i < empleados.length && !encontrado; i++) {
                if (empleados[i].getNomUsuario().equals(txt_user.getText().toString())) {
                    encontrado = true;
                    if (empleados[i].getPassword().equals(txt_pass.getText().toString())){
                        Intent intent = new Intent(this,MainActivity.class);
                        intent.putExtra(CLAVE_AFORO,Integer.parseInt(txt_aforo.getText().toString()));
                        startActivity(intent);
                    }else {
                        Toast.makeText(this, getString(R.string.toast_contraseña), Toast.LENGTH_SHORT).show();
                    }
                }

            }
            if (!encontrado) Toast.makeText(this, getString(R.string.toast_credenciales), Toast.LENGTH_SHORT).show();

        }
    }
}
