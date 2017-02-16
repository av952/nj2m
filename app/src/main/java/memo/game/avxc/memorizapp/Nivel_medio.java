package memo.game.avxc.memorizapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.provider.ContactsContract;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Nivel_medio extends AppCompatActivity implements View.OnTouchListener{

    private ImageView img;

    Bitmap bitmap;
    float x,y;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel_medio);
        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        img = (ImageView)findViewById(R.id.probando);
        img.setOnTouchListener(this);

        img.setImageResource(R.drawable.libro1);

        img.setX(100);
        img.setY(200);




        //bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.item3);

        bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Canvas  canvas = new Canvas(bitmap);


        canvas.drawBitmap(bitmap,x,y,null);


        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pruebaanima);



    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        /*
        pasa a entero el evento asociado al moition event
        para poderutilizar el switch
         */

        int action = event.getAction();

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

                img.startAnimation(animation);

                return true;
            /*
            Detecta el movimiento a travez de la pantalla
             */
            case (MotionEvent.ACTION_MOVE):



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
