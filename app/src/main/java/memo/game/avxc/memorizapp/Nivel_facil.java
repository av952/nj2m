package memo.game.avxc.memorizapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Nivel_facil extends AppCompatActivity implements View.OnClickListener,
        View.OnTouchListener{

    private ImageView imgper1,imgper2,imgitem1,imgitem2,imglibro1,imglibro2,imglibro3;
    //intanciando alamacen para obtener libros random
    private Almacen almacen = new Almacen();
    //random
    private Random random = new Random();
    //enteros para asignacion libro
    private int l,L2,L3,r,r2,r3,ir,ir2,ir3,lr1,lr2,lr3;
    private int libroelegido,perelegido;
    //textvie
    private TextView puntaje;
    //handler
    private Handler handler;

    //score
    private int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facil);
        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //CREANDO LAS IMAGENES
        imgitem1 = (ImageView)findViewById(R.id.imgv_item);
        imgitem2 = (ImageView)findViewById(R.id.imgv_item2);
        imglibro1=(ImageView)findViewById(R.id.imgv_libro1);
        imglibro2=(ImageView)findViewById(R.id.imgv_libro2);
        imglibro3=(ImageView)findViewById(R.id.imgv_libro3);
        imgper1=(ImageView)findViewById(R.id.imgv_per1);
        imgper2=(ImageView)findViewById(R.id.imgv_per2);

        //textview puntaj
        puntaje = (TextView)findViewById(R.id.puntaje);

        //VOLVIENDOLOS CLIEKEABLES
        imglibro1.setOnClickListener(this);
        imglibro2.setOnClickListener(this);
        imglibro3.setOnClickListener(this);
        imgper1.setOnClickListener(this);
        imgper2.setOnClickListener(this);

        //moviendolos
        imglibro1.setOnTouchListener(this);

        //handler
        handler = new Handler();

        //llamandometodo

        itemaleatorio(1,1);
        asignacion();



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgv_libro1:
                libroelegido =lr1;
                //asignacion();
                break;

            case R.id.imgv_libro2:
                libroelegido=lr2;
                break;
            case  R.id.imgv_libro3:
                libroelegido=lr3;
                break;

            case  R.id.imgv_per1:
                perelegido=1;
                evaluacion();
                break;
            case R.id.imgv_per2:
                perelegido=2;
                evaluacion();
                break;
        }

    }

    public void libroaleatorio(){
        r = random.nextInt(almacen.listalibros.length);
        r2 = random.nextInt(almacen.listalibros.length);
        r3 = random.nextInt(almacen.listalibros.length);

        if(r==r2||r==r3||r2==r||r2==r3||r3==r||r3==r2){
            libroaleatorio();
        }else{
            imglibro1.setImageResource(almacen.listalibros[r]);
            imglibro2.setImageResource(almacen.listalibros[r2]);
            imglibro3.setImageResource(almacen.listalibros[r3]);
        }

    }

    public void itemaleatorio(int op1,int op2){
        /*
        determino cual item quiero actualizzar
         */
        if(op1==1){
            ir = random.nextInt(almacen.listaitem.length);
            imgitem1.setImageResource(almacen.listaitem[ir]);
        }else if(op2==1){
            ir2 = random.nextInt(almacen.listaitem.length);
            imgitem2.setImageResource(almacen.listaitem[ir2]);

        }
    }

    //PARA ASIGNARLE UN TEMA A CADA LIBRO
    public void asignacion(){

        lr1=random.nextInt(almacen.listaitem.length);
        lr2=random.nextInt(almacen.listaitem.length);
        lr3=random.nextInt(almacen.listaitem.length);

        if(lr1==lr2||lr1==lr2||lr2==lr1||lr2==lr3||lr3==lr1||lr3==lr2){
            asignacion();
        }else {
            imglibro1.setImageResource(almacen.listaitem[lr1]);
            imglibro2.setImageResource(almacen.listaitem[lr2]);
            imglibro3.setImageResource(almacen.listaitem[lr3]);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                libroaleatorio();
            }
        },5000);
    }

    public void evaluacion(){

        switch (perelegido){

            case 1:
                if(libroelegido==ir){
                    score= score+1;
                    String string = String.valueOf(score);
                    puntaje.setText(string);
                    libroelegido=-1;
                    itemaleatorio(1,0);
                }else {
                    score = score-1;
                    String string = String.valueOf(score);
                    puntaje.setText(string);
                }
                break;
            case 2:
                if(libroelegido==ir2){
                    score= score+1;
                    String string = String.valueOf(score);
                    puntaje.setText(string);
                    libroelegido=-1;
                    itemaleatorio(0,1);
                }else {
                    score = score-1;
                    String string = String.valueOf(score);
                    puntaje.setText(string);
                }

        }


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        /*
        pasa a entero el evento asociado al moition event
        para poderutilizar el switch
         */

        int action = MotionEventCompat.getActionMasked(event);

        /*
        SE PREPARAN COORDENADAS
         */
        float cordx = event.getX();
        float cordy = event.getY();

        switch(action){

            /*
            DETECTA QUE SE HA PULSADO LA PANTALLA
            DEVUELVE TRUE PARA DEMOSTRAR QUE LA ACCION SE A COMPLETADO
            QUE AL SALIR DE ELLA NO SE VA A  HACER NADA
             */
            case (MotionEvent.ACTION_DOWN):

                imglibro1.getX();
                imglibro1.getY();

                return true;
            /*
            Detecta el movimiento a travez de la pantalla
             */
            case (MotionEvent.ACTION_MOVE):

                imglibro1.setX(cordx);
                imglibro1.setY(cordy);

                return true;
            /*
            Se activa al levantar el dedo de la pantalla
             */
            case (MotionEvent.ACTION_UP):

                return  true;
            /*
            Cuando se cancela un movimiento en mitad
             */
            case (MotionEvent.ACTION_CANCEL):

                return true;
            /*
            Cuando se produce un movimiento en los limites externos del objeto
             */
            case (MotionEvent.ACTION_OUTSIDE):

                return true;

        }

        return false;
    }
}
