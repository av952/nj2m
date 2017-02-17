package memo.game.avxc.memorizapp;

import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;


import static android.R.attr.handle;
import static memo.game.avxc.memorizapp.R.id.imgv_libro1;
import static memo.game.avxc.memorizapp.R.id.textView;
import static memo.game.avxc.memorizapp.R.id.tiempo;

/**
 * Created by Usuario on 16/02/2017.
 */

public class Cronometro_2 {

    public CountDownTimer countDownTimer;
    private int tiempototal = 11000;
    private int tiempocambio =5000;
    private TextView textView;
    public Nivel_facil nivel_facil;

public Cronometro_2(TextView tv){
        this.textView = tv;



    }

    public void cuentaatras(){

        countDownTimer= new CountDownTimer(tiempototal,1000){

            @Override
            public void onTick(long millisUntilFinished) {

               textView.setText(" "+(millisUntilFinished/1000));
                //sonido
                //click_tiempo.play(flujoDeMusica,0.2f,0.2f,0,0,1);
            }

            @Override
            public void onFinish() {

                textView.setText("muere");
                /*mal++;
                quitavidas();
                azar();
                if (interruptor==true){
                    cuentaatras();
                }*/
            }
        }.start();




    }

    public  void cuentaatras2(){


    }




}

