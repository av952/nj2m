package memo.game.avxc.memorizapp;

import android.content.ClipData;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

import static memo.game.avxc.memorizapp.R.id.tiempo;

public class Nivel_medio extends AppCompatActivity implements View.OnDragListener, View.OnLongClickListener,
        View.OnClickListener, View.OnTouchListener, Comunicador {

    private ImageView imgper1, imgper2, imgitem1, imgitem2, imglibro1, imglibro2, imglibro3;

    private ImageView imglibro4, imglibro5, imglibro6;
    //intanciando alamacen para obtener libros random
    private Almacen almacen = new Almacen();
    //random
    private Random random = new Random();
    //enteros para asignacion libro
    private int l, L2, L3, r, r2, r3, ir, ir2, ir3, lr1, lr2, lr3, lr4, lr5, lr6;
    private int libroelegido, perelegido;
    //textvie
    private TextView puntaje, cronometro;
    //handler
    private Handler handler, handler2, handler3;

    private boolean ver = true;

    //score
    private int score = 0;

    //TEXTOCLIKEABLE
    TextView txtclikeable;

    //countdown
    CountDownTimer countDownTimer2;


    //ANIMACION
    Animation animation, animation2;


    //futantes para ontouch
    float pinicialx, pinicialy; //posicion inicial xy de la imagen que se toca
    float dX, dY;


    //cronometro2
    public Cronometro_2 cronometro_2;

    int[] imgv_libros = {
            R.id.imgv_libro1,
            R.id.imgv_libro2,
            R.id.imgv_libro3,
            R.id.imgv_libro4,
            R.id.imgv_libro5,
            R.id.imgv_libro6,

    };

    int[] randomiza;
    int[] randomizalibro;
    int[] randomizaper;


    //finjuego
    Finjuego finjuego = new Finjuego();

    //progresbar
    public ProgressBar progressBar, progressBar2;
    int progreso = 100, progreso2 = 100;


    //hilos
    private Thread hilo_1;
    private Thread hilo_2;


    //variable para que no se llame dos veces el final del juego
    private int seleccion = 0;

    //groseria
    private TextView groseria1, groseria2;

    //cuentaatras
    private TextView tvcuentra_atras;
    private int cuenta_atras=6;

    ////////////-------------------------------------------------------------------------------------------------



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel_medio);
        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //Animacion
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.itemanimado);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animaseleccion);


        //CREANDO LAS IMAGENES
        imgitem1 = (ImageView) findViewById(R.id.imgv_item);
        imgitem2 = (ImageView) findViewById(R.id.imgv_item2);
        imglibro1 = (ImageView) findViewById(R.id.imgv_libro1);
        imglibro2 = (ImageView) findViewById(R.id.imgv_libro2);
        imglibro3 = (ImageView) findViewById(R.id.imgv_libro3);
        imgper1 = (ImageView) findViewById(R.id.imgv_per1);
        imgper2 = (ImageView) findViewById(R.id.imgv_per2);


        imglibro4 = (ImageView) findViewById(R.id.imgv_libro4);
        imglibro5 = (ImageView) findViewById(R.id.imgv_libro5);
        imglibro6 = (ImageView) findViewById(R.id.imgv_libro6);

        imglibro4.setOnTouchListener(this);
        imglibro5.setOnTouchListener(this);
        imglibro6.setOnTouchListener(this);

        //ASIGNARLIBROS ONDRAG Y ONLONG

        imglibro1.setOnTouchListener(this);
        imglibro2.setOnTouchListener(this);
        imglibro3.setOnTouchListener(this);

        imgper1.setOnDragListener(this);
        imgper2.setOnDragListener(this);

        //textview puntaj
        puntaje = (TextView) findViewById(R.id.puntaje);

        //textview txtclikeable
        //txtclikeable = (TextView)findViewById(R.id.txt_clikeable);
        //txtclikeable.setOnClickListener(this);

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
        handler2 = new Handler();
        handler3 = new Handler();

        //llamandometodo

        //itemaleatorio(1,1);
        asignacion();
        contador();

        //cronometro2
        cronometro = (TextView) findViewById(tiempo);
        cronometro_2 = new Cronometro_2(cronometro);
        //imgitem2.startAnimation(animation);


        //inicia new thread


        //progressbar
        progressBar = (ProgressBar) findViewById(R.id.progres1);
        progressBar.setVisibility(View.GONE);

        progressBar2 = (ProgressBar) findViewById(R.id.progres2);
        progressBar2.setVisibility(View.GONE);


        groseria1 = (TextView) findViewById(R.id.groseria);
        groseria2 = (TextView) findViewById(R.id.groseria2);


        //cuenta atras
        tvcuentra_atras = (TextView)findViewById(R.id.tv_cuenta_atras);


    }

    public void libroaleatorio() {
       /* r = random.nextInt(almacen.listalibros.length);
        r2 = random.nextInt(almacen.listalibros.length);
        r3 = random.nextInt(almacen.listalibros.length);*/

        Log.i("b", "inicio libroaletorio");


        int i = 0;
        randomizalibro = new int[almacen.listalibros.length];

        randomizalibro[i] = random.nextInt(almacen.listalibros.length);

        for (i = 1; i < almacen.listalibros.length; i++) {

            Log.i("c", "ingreso al for");
            randomizalibro[i] = random.nextInt(almacen.listalibros.length);
            for (int j = 0; j < i; j++) {
                if (randomizalibro[i] == randomizalibro[j]) {
                    i--;
                }
            }
        }


       /* if(r==r2||r==r3||r2==r||r2==r3||r3==r||r3==r2){
            libroaleatorio();
        }else{*/

        Log.i("d", "asignando libros");
        imglibro1.setImageResource(almacen.listalibros[randomizalibro[0]]);
        imglibro1.clearAnimation();
        imglibro2.setImageResource(almacen.listalibros[randomizalibro[1]]);
        imglibro2.clearAnimation();
        imglibro3.setImageResource(almacen.listalibros[randomizalibro[2]]);
        imglibro3.clearAnimation();

        imglibro4.setImageResource(almacen.listalibros[randomizalibro[3]]);
        imglibro4.clearAnimation();
        imglibro5.setImageResource(almacen.listalibros[randomizalibro[4]]);
        imglibro5.clearAnimation();
        imglibro6.setImageResource(almacen.listalibros[randomizalibro[5]]);
        imglibro6.clearAnimation();

    }

    //}

    public void itemaleatorio(int op1, int op2) {
        /*
        determino cual item quiero actualizzar
         */
        if (op1 == 1) {

            ir = random.nextInt(6);
            imgitem1.setImageResource(almacen.listaitem[randomiza[ir]]);
            imgitem1.startAnimation(animation);
        }
        if (op2 == 1) {
            ir2 = random.nextInt(6);
            imgitem2.setImageResource(almacen.listaitem[randomiza[ir2]]);
            imgitem2.startAnimation(animation);

        }
    }

    public void personaje_aleatorio(int a, int b) {

        int i = 0;
        randomizaper = new int[almacen.listapersonajes.length];


        randomizaper[i] = random.nextInt(almacen.listapersonajes.length);

        for (i = 1; i < almacen.listapersonajes.length; i++) {

            Log.i("c", "ingreso al for del per");
            randomizaper[i] = random.nextInt(almacen.listapersonajes.length);
            for (int j = 0; j < i; j++) {
                if (randomizaper[i] == randomizaper[j]) {
                    i--;
                }
            }
        }


        //int persojerandom = random.nextInt(almacen.listapersonajes.length);


        if (a == 1) {
            imgper1.setImageResource(almacen.listapersonajes[randomizaper[0]]);
        }
        if (b == 1) {
            imgper2.setImageResource(almacen.listapersonajes[randomizaper[1]]);
        }


    }

    //PARA ASIGNARLE UN TEMA A CADA LIBRO

    public void asignacion() {

        int i = 0;
        randomiza = new int[almacen.listaitem.length];

        randomiza[i] = random.nextInt(almacen.listaitem.length);

        for (i = 1; i < almacen.listaitem.length; i++) {
            randomiza[i] = random.nextInt(almacen.listaitem.length);
            for (int j = 0; j < i; j++) {
                if (randomiza[i] == randomiza[j]) {
                    i--;
                }
            }
        }

        imglibro1.setImageResource(almacen.listaitem[randomiza[0]]);
        imglibro1.setAnimation(animation2);
        imglibro2.setImageResource(almacen.listaitem[randomiza[1]]);
        imglibro2.setAnimation(animation2);
        imglibro3.setImageResource(almacen.listaitem[randomiza[2]]);
        imglibro3.setAnimation(animation2);
        imglibro4.setImageResource(almacen.listaitem[randomiza[3]]);
        imglibro4.setAnimation(animation2);
        imglibro5.setImageResource(almacen.listaitem[randomiza[4]]);
        imglibro5.setAnimation(animation2);
        imglibro6.setImageResource(almacen.listaitem[randomiza[5]]);
        imglibro6.setAnimation(animation2);

        imgper1.setVisibility(View.GONE);
        imgitem1.setVisibility(View.GONE);
        imgper2.setVisibility(View.GONE);
        imgitem2.setVisibility(View.GONE);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cronometro_2.cuentaatras();
                //cambio();
                libroaleatorio();
                retardador();
            }
        }, 5000);

    }

    public void retardador() {

        tvcuentra_atras.setVisibility(View.GONE);
        tvcuentra_atras.setTextSize(0);
        Log.i("a", "retardador");

        //vuelve invisible el texto
        //txtclikeable.setVisibility(View.INVISIBLE);

        // txtclikeable.setTextSize(0);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgper1.setVisibility(View.VISIBLE);
                imgitem1.setVisibility(View.VISIBLE);
                imgper2.setVisibility(View.VISIBLE);
                imgitem2.setVisibility(View.VISIBLE);


                itemaleatorio(1, 1);
                personaje_aleatorio(1, 1);
                //new Tarea_asincrona().execute();

                hilo1();
                hilo2();

                cronometro_2.cuentaatras();
                //es el cambio de cara es alpha
                //cambio();

            }
        }, 1000);
    }

    public void evaluacion() {

        switch (perelegido) {

            case 1:
                if (libroelegido == randomiza[ir]) {
                    score = score + 1;
                    accionDeEvaluacion();
                    libroelegido = -1;
                    itemaleatorio(1, 0);
                    personaje_aleatorio(1, 0);
                    progreso = 100;
                    groseria1.setVisibility(View.INVISIBLE);

                } else {
                    //score = score - 1;
                    String string = String.valueOf(score);
                    puntaje.setText(string);
                    finjuego();
                }
                break;
            case 2:
                if (libroelegido == randomiza[ir2]) {
                    score = score + 1;
                    accionDeEvaluacion();
                    // String string = String.valueOf(score);
                    // puntaje.setText(string);
                    libroelegido = -1;
                    itemaleatorio(0, 1);
                    personaje_aleatorio(0, 1);
                    progreso2 = 100;
                    groseria2.setVisibility(View.INVISIBLE);


                } else {
                    //score = score - 1;
                    String string = String.valueOf(score);
                    puntaje.setText(string);
                    finjuego();
                }
        }

    }

    //para reducir el codigo que estaba arriba
    public void accionDeEvaluacion() {
        cronometro_2.countDownTimer.cancel();
        //countDownTimer2.cancel();
        cronometro_2.cuentaatras();
        String string = String.valueOf(score);
        puntaje.setText(string);

    }


    @Override
    public boolean onDrag(View v, DragEvent event) {

        final View view = (View) event.getLocalState();
        switch (event.getAction()) {

            case DragEvent.ACTION_DRAG_ENTERED:

                return true;

            case DragEvent.ACTION_DROP:

                int a = v.getId();
                // puntaje.setText(""+a);

                switch (a) {
                    case R.id.imgv_per1:
                        //puntaje.setText("personaje 1");
                        perelegido = 1;
                        evaluacion();
                        break;
                    case R.id.imgv_per2:
                        //puntaje.setText("personaje2");
                        perelegido = 2;
                        evaluacion();
                }

                //view.setVisibility(View.VISIBLE);
                break;

            case DragEvent.ACTION_DRAG_EXITED:
                //puntaje.setText("ACTION_DRAG_EXITED");
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
        ClipData clipData = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

        v.startDrag(clipData, shadowBuilder, v, 0);
        v.setVisibility(View.INVISIBLE);

        return true;
    }

    @Override
    public void onClick(View v) {


       /* switch (v.getId()){
            case R.id.txt_clikeable:
                retardador();
                libroaleatorio();
                break;


        }*/

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        pinicialx = v.getX() - event.getRawX();
        pinicialy = v.getY() - event.getRawY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                //i me devuelve el valor del elemento selecionado
                int i = v.getId();


                // puntaje.setText("down"+i);


                if (i == imgv_libros[0]) {
                    libroelegido = randomiza[0];

                } else if (i == imgv_libros[1]) {

                    libroelegido = randomiza[1];

                } else if (i == imgv_libros[2]) {
                    libroelegido = randomiza[2];

                } else if (i == imgv_libros[3]) {
                    libroelegido = randomiza[3];

                } else if (i == imgv_libros[4]) {
                    libroelegido = randomiza[4];

                } else if (i == imgv_libros[5]) {
                    libroelegido = randomiza[5];

                } else if (i == imgv_libros[6]) {
                    libroelegido = randomiza[6];

                }

                /*switch (i){
                    case R.id.imgv_libro1:
                        //puntaje.setText("libro 1");

                        //evaluacion();
                        break;
                    case R.id.imgv_libro2:
                        //puntaje.setText("libro 2");
                        libroelegido =lr2;
                        //evaluacion();
                        break;
                    case R.id.imgv_libro3:
                       // puntaje.setText("libro 3");
                        libroelegido=lr3;
                        //evaluacion();
                        break;
                    case R.id.imgv_libro4:*/


                //}

                ClipData clipData = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

                v.startDrag(clipData, shadowBuilder, v, 0);

                //VISIBILIDAD DE LOS LIBROS AL ARRASTRAR
                v.setVisibility(View.VISIBLE);

                dX = v.getX() - event.getRawX();
                dY = v.getY() - event.getRawY();


                return true;

        }
        return false;
    }

    @Override
    public void cambio() {

        countDownTimer2 = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                imgper1.setImageResource(R.drawable.per1_2);
            }
        }.start();


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width2 = metrics.widthPixels; // ancho absoluto en pixels
        int height2 = metrics.heightPixels; // alto absoluto en pixels
        int densidad_dpi = metrics.densityDpi;

        /*float x = imgper1.getX();
        float y = imgper1.getY();

        cronometro.setX(x);
        cronometro.setY((y+20)*5);



        puntaje.setText("x "+x+"y "+y+"crono x"+cronometro.getX());*/
    }

    public void finjuego() {
        Intent intent = new Intent(this, Finjuego.class);
        intent.putExtra("puntaje", puntaje.getText());
        startActivity(intent);
        ver = false;
        seleccion=1;
        hilo_1.interrupt();
        hilo_2.interrupt();
        finish();


    }

    public class Tarea_asincrona extends AsyncTask<Void, Integer, Void> {


        @MainThread
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            progressBar2.setVisibility(View.VISIBLE);

            // progreso =100;
            //progreso2=100;
            // progressBar.setProgress(100);
            // progressBar2.setProgress(100);
        }


        @Override
        protected Void doInBackground(Void... params) {

            while (progreso > 0) {
                progreso--;
                publishProgress(progreso);
                SystemClock.sleep(100);
            }

            while (progreso2 > 0) {
                progreso2--;
                publishProgress(progreso2);
                SystemClock.sleep(100);
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            progressBar.setProgress(values[0]);
            progressBar2.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(Void result) {

            finjuego();

        }
    }

    public void hilo1() {
        //puntaje.setText("hilo");
        //ver= false;
        progressBar.setVisibility(View.VISIBLE);
        progreso = 100;

        hilo_1 = new Thread(new Runnable() {
            public void run() {
                //puntaje.setText("run");

                while (progreso > 0) {
                    SystemClock.sleep(100);
                    progreso--;

                    // Update the progress bar
                    handler2.post(new Runnable() {
                        public void run() {
                            //puntaje.setText("handler");
                            progressBar.setProgress(progreso);
                            if (progreso == 50) {
                                groseria1.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
                // finjuego();
                seleccion++;
                culminador();
            }
        });
        hilo_1.start();

    }

    public void hilo2() {
        //ver= false;
        // puntaje.setText("hilo2");
        progressBar2.setVisibility(View.VISIBLE);
        progreso2 = 100;


        hilo_2 = new Thread(new Runnable() {
            public void run() {
                //puntaje.setText("run");

                while (progreso2 > 0) {
                    SystemClock.sleep(100);
                    progreso2--;

                    // Update the progress bar
                    handler3.post(new Runnable() {

                        public void run() {
                            //puntaje.setText("handler2");
                            progressBar2.setProgress(progreso2);
                            if (progreso2 == 50) {
                                groseria2.setVisibility(View.VISIBLE);
                            }

                        }
                    });
                }
                //finjuego();
                seleccion++;
                culminador();
            }
        });
        hilo_2.start();
    }

    @Override
    public void onDestroy() {

        if (hilo_1!=null || hilo_2 !=null ){
            hilo_1.interrupt();
            hilo_2.interrupt();
        }
        finish();
        super.onDestroy();
    }

    public void culminador() {

        //region Culmina el proceso
        if (seleccion == 1) {
            finjuego();

        }
        //endregion


    }

    public void contador(){
        new CountDownTimer(6000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                cuenta_atras--;
                tvcuentra_atras.setText(""+cuenta_atras);

            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    @Override
    public void onBackPressed(){
        seleccion=1;
        finish();

    }


}



