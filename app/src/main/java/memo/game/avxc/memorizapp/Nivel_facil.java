package memo.game.avxc.memorizapp;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.R.attr.x;
import static android.view.View.Y;

public class Nivel_facil extends AppCompatActivity implements View.OnDragListener,View.OnLongClickListener,
        View.OnClickListener,View.OnTouchListener{

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


    //ANIMACION
    Animation animation;


    //futantes para ontouch
     float pinicialx,pinicialy; //posicion inicial xy de la imagen que se toca
    float dX,dY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facil);
        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //Animacion
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.itemanimado);



        //CREANDO LAS IMAGENES
        imgitem1 = (ImageView)findViewById(R.id.imgv_item);
        imgitem2 = (ImageView)findViewById(R.id.imgv_item2);
        imglibro1=(ImageView)findViewById(R.id.imgv_libro1);
        imglibro2=(ImageView)findViewById(R.id.imgv_libro2);
        imglibro3=(ImageView)findViewById(R.id.imgv_libro3);
        imgper1=(ImageView)findViewById(R.id.imgv_per1);
        imgper2=(ImageView)findViewById(R.id.imgv_per2);

        //ASIGNARLIBROS ONDRAG Y ONLONG

        imglibro1.setOnClickListener(this);
        imglibro2.setOnTouchListener(this);
        imglibro3.setOnLongClickListener(this);

        imgper1.setOnDragListener(this);
        imgper2.setOnDragListener(this);




        //textview puntaj
        puntaje = (TextView)findViewById(R.id.puntaje);

        //VOLVIENDOLOS CLIEKEABLES
        /*imglibro1.setOnClickListener(this);
        imglibro2.setOnClickListener(this);
        imglibro3.setOnClickListener(this);
        imgper1.setOnClickListener(this);
        imgper2.setOnClickListener(this);*/

        //moviendolos
       /* imglibro1.setOnTouchListener(this);*/

        //handler
        handler = new Handler();

        //llamandometodo

        itemaleatorio(1,1);
        asignacion();

        //imgitem2.startAnimation(animation);
    }

    public void libroaleatorio(){
        r = random.nextInt(almacen.listalibros.length);
        r2 = random.nextInt(almacen.listalibros.length);
        r3 = random.nextInt(almacen.listalibros.length);

        if(r==r2||r==r3||r2==r||r2==r3||r3==r||r3==r2){
            libroaleatorio();
        }else{
            imglibro1.setImageResource(almacen.listalibros[r]);
            imglibro1.clearAnimation();
            imglibro2.setImageResource(almacen.listalibros[r2]);
            imglibro2.clearAnimation();
            imglibro3.setImageResource(almacen.listalibros[r3]);
            imglibro3.clearAnimation();

        }

    }

    public void itemaleatorio(int op1,int op2){
        /*
        determino cual item quiero actualizzar
         */
        if(op1==1){
            ir = random.nextInt(almacen.listaitem.length);
            imgitem1.setImageResource(almacen.listaitem[ir]);
            imgitem1.startAnimation(animation);
        }
        if(op2==1){
            ir2 = random.nextInt(almacen.listaitem.length);
            imgitem2.setImageResource(almacen.listaitem[ir2]);
            imgitem2.startAnimation(animation);

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
            imglibro1.setAnimation(animation);
            imglibro2.setImageResource(almacen.listaitem[lr2]);
            imglibro2.setAnimation(animation);
            imglibro3.setImageResource(almacen.listaitem[lr3]);
            imglibro3.setAnimation(animation);

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
    public boolean onDrag(View v, DragEvent event) {

        final View view =(View)event.getLocalState();
        switch (event.getAction()){

            case DragEvent.ACTION_DRAG_ENTERED:

                        if(view.getId()==R.id.imgv_libro1){
                            puntaje.setText("lo lograste");
                        }
                return true;

             case DragEvent.ACTION_DROP:
                 puntaje.setText("ACTION_DROP");
                 view.setVisibility(View.VISIBLE);
                 break;

             case DragEvent.ACTION_DRAG_EXITED:
                 puntaje.setText("ACTION_DRAG_EXITED");
                 view.setVisibility(View.VISIBLE);

                 break;

            case DragEvent.ACTION_DRAG_ENDED:
                //puntaje.setText("ACTION_DRAG_ENDED:");
                view.setVisibility(View.VISIBLE);

                break;

        }


        return true;
    }

    @Override
    public boolean onLongClick(View v) {
        ClipData clipData = ClipData.newPlainText("","");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

        v.startDrag(clipData,shadowBuilder,v,0);
        v.setVisibility(View.INVISIBLE);

        return true ;
    }

    @Override
    public void onClick(View v) {

        ClipData clipData = ClipData.newPlainText("","");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

        v.startDrag(clipData,shadowBuilder,v,0);
        v.setVisibility(View.VISIBLE);
        puntaje.setText("onclick");

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        pinicialx = v.getX()-event.getRawX();
        pinicialy= v.getY()-event.getRawY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                puntaje.setText("down");

                ClipData clipData = ClipData.newPlainText("","");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

                v.startDrag(clipData,shadowBuilder,v,0);
                v.setVisibility(View.VISIBLE);

                dX = v.getX() - event.getRawX();
                dY = v.getY() - event.getRawY();


                return true;

        }
        return false;
    }


    public void ondrag(){

    }
}
