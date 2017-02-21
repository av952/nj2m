package memo.game.avxc.memorizapp;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Nivel_dificil extends AppCompatActivity {

    ImageView personaje,item;
    TextView texto,texto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel_dificil);
        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        personaje = (ImageView)findViewById(R.id.personajeprueba);
        item = (ImageView)findViewById(R.id.itemprueba);
        texto = (TextView)findViewById(R.id.texto);
        texto2 = (TextView)findViewById(R.id.texto2);

        personaje.setImageResource(R.drawable.per1);
        item.setImageResource(R.drawable.item3);


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width2 = metrics.widthPixels; // ancho absoluto en pixels
        int height2 = metrics.heightPixels; // alto absoluto en pixels
        int densidad_dpi = metrics.densityDpi;

        /*int width  = this.getResources().getConfiguration().screenWidthDp;
        int heigth = this.getResources().getConfiguration().screenHeightDp;*/


        int dp = width2/(densidad_dpi/160);

        int px =168;
        px += dp*(densidad_dpi/160);

        personaje.setX(width2-width2+1);
        personaje.setY(100-(width2/height2));
        personaje.setPivotX(personaje.getMaxWidth()/2);



        item.setX((personaje.getX()+dp/2));
        item.setY(100-(width2/height2));


        texto.setText("posicion del personaje en x"+personaje.getX()+ "dep= "+dp+" pivot x  personaje "+ personaje.getPivotX()+"posicion de el item en x"+item.getX());
        texto2.setText("Ancho "+width2+"altura"+height2);





    }
}
