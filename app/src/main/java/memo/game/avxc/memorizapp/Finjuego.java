package memo.game.avxc.memorizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class Finjuego extends AppCompatActivity {

    TextView puntaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finjuego);
        //FULLSCREEN
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        puntaje = (TextView)findViewById(R.id.puntuacion);

        finaliza();
    }

    public void finaliza(){

        Bundle datos  = this.getIntent().getExtras();

        String res  =datos.getString("puntaje");
        puntaje.setText(res);

    }
}
