package memo.game.avxc.memorizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

public class Finjuego extends AppCompatActivity implements View.OnClickListener {

    TextView puntaje, puntaje_max;

    SharedPreferences sharedPreferences;
    private String guarda_puntaje, res;
    private int converte_puntaje, puntaje_actual;

    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finjuego);
        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        puntaje = (TextView) findViewById(R.id.puntuacion);
        puntaje.setOnClickListener(this);

        puntaje_max = (TextView) findViewById(R.id.txt_puntuamax);

        //sharepreference para almacenar datros en memoria

        sharedPreferences = getSharedPreferences(getString(R.string.guarda_puntaje), Context.MODE_PRIVATE);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        publicidad_interticial();
        finaliza();
        gravador();


    }

    public void finaliza() {

        Bundle datos = this.getIntent().getExtras();

        /*res me convierte el puntaje en un srtring
        * para luego colocarlo en pantalla*/
        res = datos.getString("puntaje");
        puntaje.setText(res);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.puntuacion:
                //finish();
                publicidad_interticial();
                break;
        }

    }

    /*
    * graba los datos del valor maximo*/
    public void gravador() {

        puntaje_actual = Integer.valueOf(res);

        guarda_puntaje = sharedPreferences.getString(getString(R.string.guarda_puntaje), "0");
        converte_puntaje = Integer.valueOf(guarda_puntaje);


        if (puntaje_actual > converte_puntaje) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.guarda_puntaje), res);
            editor.commit();
            guarda_puntaje = sharedPreferences.getString(getString(R.string.guarda_puntaje), "0");

            /*coloca el texto obtenido en pantalla*/
            puntaje_max.setText(getText(R.string.Puntuacion_maxima) + " " + guarda_puntaje);

        } else {
            puntaje_max.setText("no tienes puntuacion maxima");
        }

    }

    private void publicidad_interticial() {
        Log.i("publicidad", "se cargo la publicidad");
        final AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                interstitialAd.loadAd(adRequest);
                super.onAdClosed();
            }

            public void onAdLoaded() {


                Random random = new Random();

                int ran = random.nextInt(3);
                Log.i("publicidad", "random" + ran);

                if (ran == 1) {
                    interstitialAd.show();

                }

                //interstitialAd.show();
            }
        });

    }
}