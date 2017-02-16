package memo.game.avxc.memorizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class Menu extends AppCompatActivity implements View.OnClickListener{

    private Button btnjugar,btnfacil,btndificil,btnmedio;
    //ENTERO PARA SABER QUE BOTON FUE  ORPIMIDO
    int boton;

    Animation animation;


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

    }

    @Override
    public void onClick(View v) {

    switch (v.getId()){

        case R.id.btn_facil:
            btnjugar.startAnimation(animation);
            btnjugar.setText("jugar facil");
            btnjugar.setVisibility(View.VISIBLE);

            boton=1;
            btnfacil.setAlpha(1);
            btnmedio.setAlpha(0.5f);
            btndificil.setAlpha(0.5f);
            break;
        case R.id.btn_difiil:
            btnjugar.startAnimation(animation);
            btnjugar.setText("jugar dificil");
            btnjugar.setVisibility(View.VISIBLE);
            boton=3;
            btndificil.setAlpha(1);
            btnfacil.setAlpha(0.5f);
            btnmedio.setAlpha(0.5f);
            break;
        case R.id.btn_medio:
            btnjugar.startAnimation(animation);
            btnjugar.setText("jugar medio");
            btnjugar.setVisibility(View.VISIBLE);
            boton=2;
            btnmedio.setAlpha(1);
            btnfacil.setAlpha(0.5f);
            btndificil.setAlpha(0.5f);
            break;
        case R.id.btn_jugar:
            if(boton==1){
                Intent intent = new Intent(this,Nivel_facil.class);
                startActivity(intent);
                //finish();
            }else if(boton==2){
                Intent intent = new Intent(this,Prueba.class);
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
}
