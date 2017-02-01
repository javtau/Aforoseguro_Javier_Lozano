package com.javier.aforoseguro;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView lbl_num = null;
    RelativeLayout rl = null;
    int aforo = 0;
    int maximo;
    Button btn_menos;
    Button btn_mas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lbl_num = (TextView) findViewById(R.id.lbl_num);
        rl = (RelativeLayout) findViewById(R.id.activity_main);
        maximo = getIntent().getIntExtra(LogginActivity.CLAVE_AFORO, 0);
        lbl_num.setText("" + aforo);
        btn_menos = (Button) findViewById(R.id.button7);
        btn_mas = (Button) findViewById(R.id.btn_mas);
    }

    public void aumentarAforo(View v) {
        if (aforo == 0) btn_menos.setEnabled(true);
        aforo++;

        if (aforo < maximo) {
            lbl_num.setText("" + aforo);
            comprobarAforo(aforo);
        } else if (aforo == maximo) {
            lbl_num.setText("" + aforo);
            Toast.makeText(this, getString(R.string.toast_maximo), Toast.LENGTH_LONG).show();
            llamar();
            btn_mas.setEnabled(false);
        }
    }

    public void disminuirAforo(View v) {
        if (aforo == maximo) btn_mas.setEnabled(true);
        aforo--;
        if (aforo == 0) btn_menos.setEnabled(false);
        lbl_num.setText("" + aforo);
        comprobarAforo(aforo);
    }

    private void comprobarAforo(int aforo) {
        if (aforo >= maximo * 0.9) {
            rl.setBackgroundColor(Color.parseColor("#e15258"));
        } else if (aforo >= maximo * 0.7) {
            rl.setBackgroundColor(Color.parseColor("#f9845b"));
        } else {
            rl.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_llamar) {
            crear_dialogollamada().show();
        } else if (item.getItemId() == R.id.action_reiniciar) {
            aforo = 0;
            lbl_num.setText("" + aforo);
            rl.setBackgroundColor(Color.WHITE);
        }
        return super.onOptionsItemSelected(item);
    }

    private Dialog crear_dialogollamada() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(getString(R.string.ask_llamada));
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                llamar();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }

    public void llamar() {
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+34658417534"));
        startActivity(i);
    }

    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}
