package memo.game.avxc.memorizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Finjuego extends AppCompatActivity implements View.OnClickListener{

    TextView puntaje,puntaje_max;

    SharedPreferences sharedPreferences;
    private String guarda_puntaje,res;
    private int converte_puntaje,puntaje_actual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finjuego);
        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        puntaje = (TextView)findViewById(R.id.puntuacion);
        puntaje.setOnClickListener(this);

        puntaje_max =(TextView)findViewById(R.id.txt_puntuamax);

        //sharepreference para almacenar datros en memoria

        sharedPreferences = getSharedPreferences(getString(R.string.guarda_puntaje), Context.MODE_PRIVATE);

        finaliza();
        gravador();
    }

    public void finaliza(){

        Bundle datos  = this.getIntent().getExtras();

        /*res me convierte el puntaje en un srtring
        * para luego colocarlo en pantalla*/
        res  = datos.getString("puntaje");
        puntaje.setText(res);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.puntuacion:
                finish();
                break;
        }

    }

    /*
    * graba los datos del valor maximo*/
    public void gravador(){

        puntaje_actual = Integer.valueOf(res);

        guarda_puntaje= sharedPreferences.getString(getString(R.string.guarda_puntaje),"0");
        converte_puntaje = Integer.valueOf(guarda_puntaje);


        if (puntaje_actual>converte_puntaje){
            SharedPreferences.Editor  editor  = sharedPreferences.edit();
            editor.putString(getString(R.string.guarda_puntaje),res);
            editor.commit();
            guarda_puntaje= sharedPreferences.getString(getString(R.string.guarda_puntaje),"0");

            /*coloca el texto obtenido en pantalla*/
            puntaje_max.setText(getText(R.string.Puntuacion_maxima)+" "+guarda_puntaje);

        }else {
            puntaje_max.setText("no tienes puntuacion maxima");
        }


    }


}
