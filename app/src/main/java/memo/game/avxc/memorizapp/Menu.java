package memo.game.avxc.memorizapp;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import static memo.game.avxc.memorizapp.R.raw.sonido_tiempo;

public class Menu extends AppCompatActivity implements View.OnClickListener{

    private Button btnjugar,btnfacil,btndificil,btnmedio;
    //ENTERO PARA SABER QUE BOTON FUE  ORPIMIDO
    int boton;

    Animation animation;
    private SoundPool  click;
    private int flujodemusica;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //CREACION DE BOTONES
        btnjugar = (Button)findViewById(R.id.btn_jugar);
        btndificil=(Button)findViewById(R.id.btn_difiil);
        btnmedio = (Button)findViewById(R.id.btn_medio);
        btnfacil = (Button)findViewById(R.id.btn_facil);
        //HACIENDO CLEQUEABLES LOS BOTONES
        btnjugar.setOnClickListener(this);
        btndificil.setOnClickListener(this);
        btnmedio.setOnClickListener(this);
        btnfacil.setOnClickListener(this);


        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pruebaanima);


        click = new SoundPool(0, AudioManager.STREAM_MUSIC,0);
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        flujodemusica = click.load(this,R.raw.click,1);

    }

    @Override
    public void onClick(View v) {

    switch (v.getId()){

        case R.id.btn_facil:
            click.play(flujodemusica,1,1,0,0,1);
            btnjugar.startAnimation(animation);
            btnjugar.setText(R.string.jugar_facil);
            btnjugar.setVisibility(View.VISIBLE);

            boton=1;
            btnfacil.setAlpha(1);
            btnmedio.setAlpha(0.5f);
            btndificil.setAlpha(0.5f);
            break;
        case R.id.btn_difiil:
            click.play(flujodemusica,1,1,0,0,1);

            btnjugar.startAnimation(animation);
            btnjugar.setText(R.string.jugar_difícil);
            btnjugar.setVisibility(View.VISIBLE);
            boton=3;
            btndificil.setAlpha(1);
            btnfacil.setAlpha(0.5f);
            btnmedio.setAlpha(0.5f);
            break;
        case R.id.btn_medio:
            click.play(flujodemusica,1,1,0,0,1);

            btnjugar.startAnimation(animation);
            btnjugar.setText(R.string.jugarm_medio);
            btnjugar.setVisibility(View.VISIBLE);
            boton=2;
            btnmedio.setAlpha(1);
            btnfacil.setAlpha(0.5f);
            btndificil.setAlpha(0.5f);
            break;
        case R.id.btn_jugar:
            click.play(flujodemusica,1,1,0,0,1);
            if(boton==1){
                Intent intent = new Intent(this,Nivel_facil.class);
                startActivity(intent);
                //finish();
            }else if(boton==2){
                Intent intent = new Intent(this,Nivel_medio.class);
                startActivity(intent);
                //finish();
            }else if(boton==3){
                Intent intent = new Intent(this,Nivel_dificil.class);
                startActivity(intent);
                //finish();
            }else {
                Toast.makeText(this,"No haz seleccionado un nivel de dicicultad",Toast.LENGTH_SHORT).show();
            }

            break;
    }

    }

    private static final int INTERVALO = 2000;
    private long tiempoPrimerClick;

    @Override
    public void onBackPressed(){
        if (tiempoPrimerClick + INTERVALO > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Toast.makeText(this, "Vuelve a presionar para salir", Toast.LENGTH_SHORT).show();
        }
        tiempoPrimerClick = System.currentTimeMillis();
    }
}
