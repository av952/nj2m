package memo.game.avxc.memorizapp;

import android.provider.ContactsContract;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class Nivel_medio extends AppCompatActivity implements View.OnTouchListener{

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel_medio);
        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        img = (ImageView)findViewById(R.id.probando);
        img.setOnTouchListener(this);

        img.setImageResource(R.drawable.libro1);

        img.setPivotX(0);
        img.setPivotY(0);
        img.setX(100);
        img.setY(200);

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

                img.getX();
                img.getY();

                return true;
            /*
            Detecta el movimiento a travez de la pantalla
             */
            case (MotionEvent.ACTION_MOVE):

                img.setX(cordx);
                img.setY(cordy);

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
