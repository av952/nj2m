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
    private String guarda_puntaje;
    private int converte_puntaje;

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

        String res  =datos.getString("puntaje");
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

        guarda_puntaje=  puntaje.getText().toString();
        converte_puntaje = Integer.valueOf(guarda_puntaje);

        SharedPreferences.Editor  editor  = sharedPreferences.edit();
        editor.putString(getString(R.string.guarda_puntaje),guarda_puntaje);
        editor.commit();

    }


}
