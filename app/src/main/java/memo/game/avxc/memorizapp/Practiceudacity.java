package memo.game.avxc.memorizapp;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

import static memo.game.avxc.memorizapp.R.id.precio;

/**
 * This app displays an order form to order coffee.
 */
public class Practiceudacity extends AppCompatActivity {

    private int numero_cafes=1;
    private String[] typecoffe = new String[5];
    private int[] preciocoffe= new int[5];
    private int contador=0;
    private String conversor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practiceudacity);


        typecoffe[0]="coffe";
        typecoffe[1]="capuchino";
        typecoffe[2]="macchiato";
        typecoffe[3]="express";
        typecoffe[4]="double";

        preciocoffe[0]=1;
        preciocoffe[1]=3;
        preciocoffe[2]=8;
        preciocoffe[3]=6;
        preciocoffe[4]=4;


    }

    /**
     * This method is called when the order button is clicked.
     */
    public void comunica(View view) {

        String totalprice = "$ "+(numero_cafes*preciocoffe[contador])+" Please \nThanks";
        //display(numero_cafes);
        //displayPrice(numero_cafes*5);
        displayMessage(totalprice);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.cant);


        quantityTextView.setText("" + number);

    }

    private void display2(String s){
        TextView type = (TextView)findViewById(R.id.typecoffe);
        type.setText(s);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price);

        //Esto da formato al texto $
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void mas(View view){
        numero_cafes+=1;
        display(numero_cafes);
        //calculaprecio();

    }

    public void menos(View view){
        numero_cafes-=1;
        display(numero_cafes);
        //calculaprecio();

    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price);
        priceTextView.setText(message);
    }

    public void mas2(View view){
        contador+=1;



        if(contador>=typecoffe.length){
            contador=0;
        }
        display2(typecoffe[contador]);

        calculaprecio();


    }

    public void calculaprecio(){
        //graba el dato del precio segun el item que se escoja
        TextView preciotv =(TextView)findViewById(R.id.precio);
        preciotv.setText(""+preciocoffe[contador]);

    }
}