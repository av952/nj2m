package memo.game.avxc.memorizapp;

import android.content.ClipData;
import android.content.ClipDescription;
import android.media.Image;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class Prueba extends AppCompatActivity implements View.OnTouchListener{


    private ImageView personaje,libro,itemprueba;
    private LinearLayout top,bottom;
    private TextView textto;
    Almacen almacen = new Almacen();

    private int r1,ir1;

    Random random;

    //float 160216
    float xinicial, yinicial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

        /*personaje=(ImageView)findViewById(R.id.personaje);
        personaje.setOnDragListener(this);
        libro=(ImageView)findViewById(R.id.libro);
        libro.setOnDragListener(this);*/

        itemprueba =(ImageView)findViewById(R.id.itemprueba);

        textto=(TextView)findViewById(R.id.textotv);

        personaje =(ImageView) findViewById(R.id.personaje);

        libro=(ImageView)findViewById(R.id.libro);
        libro.setOnTouchListener(this);
        itemprueba.setImageResource(almacen.listaitem[0]);

        /*libros();
        item();*/

    }

    private void item() {
        ir1= random.nextInt(almacen.listaitem.length);
        itemprueba.setImageResource(almacen.listaitem[ir1]);

    }

    private void libros() {

        r1 = random.nextInt(almacen.listalibros.length);

        libro.setImageResource(almacen.listalibros[r1]);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int accion = event.getActionMasked();

        switch (accion){
            case MotionEvent.ACTION_DOWN:
                xinicial = event.getX();
                yinicial =  event.getY();
                textto.setText("ACTION_DOWN"+"x "+xinicial+"y "+yinicial);
                break;
            case MotionEvent.ACTION_MOVE:


        }


        return false;
    }
}
