package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ActivityHutsuneakBete extends AppCompatActivity {

    private Button btnJarraitu, btn1, test1, btn2, test2, btn3, test3;
    private EditText etBaseliza, etBerreraiki, etGurutzearen;
    private ImageView imgZuzena1, imgZuzena2, imgZuzena3, imgOkerra1, imgOkerra2, imgOkerra3 ;
    LinearLayout target1, target2, target3;
    int contadorButton=0;
    int contadorGurutze=0;
    int contadorBerreraiki=0;
    int contadorBaseliza=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_hutsuneak_bete);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        target1=findViewById(R.id.target1);
        test1=findViewById(R.id.test1);
        btn1=findViewById(R.id.btn1);
        target1.setOnDragListener(dragListener);
        btn1.setOnLongClickListener(longClickListener);

        target2=findViewById(R.id.target2);
        test2=findViewById(R.id.test2);
        btn2=findViewById(R.id.btn2);
        target2.setOnDragListener(dragListener);
        btn2.setOnLongClickListener(longClickListener);

        target3=findViewById(R.id.target3);
        test3=findViewById(R.id.test3);
        btn3=findViewById(R.id.btn3);
        target3.setOnDragListener(dragListener);
        btn3.setOnLongClickListener(longClickListener);




        btnJarraitu=findViewById(R.id.buttonJarraitu);


        //Se activa al quitar el focus


        //Se activa al darle enter en el teclado


        //Se activa al quitar el focus


        //Se activa al darle enter en el teclado




        //Se activa al darle enter en el teclado


        //Se activa al quitar el focus



        btnJarraitu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //programar una accion aqui

            }
        });




    }




    View.OnLongClickListener longClickListener=new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data= ClipData.newPlainText("","");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data,myShadowBuilder,v,0);

            return true;
        }
    };

    View.OnDragListener dragListener= new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            final View view=(View) event.getLocalState();

            switch (dragEvent){

                case DragEvent.ACTION_DRAG_ENTERED:
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    break;

                case DragEvent.ACTION_DROP:

                    if (view.getId()==R.id.btn1 && v.getId()==R.id.target1){
                        //Toast.makeText(MainActivity.this, "Dropped", Toast.LENGTH_SHORT).show();

                        ConstraintLayout oldparent =(ConstraintLayout) view.getParent();
                        oldparent.removeView(view);
                        LinearLayout newParent =(LinearLayout)v;
                        test1.setVisibility(View.GONE);
                        newParent.addView(view);
                    }
                    else if (view.getId()==R.id.btn2 && v.getId()==R.id.target2){
                        //Toast.makeText(MainActivity.this, "Dropped", Toast.LENGTH_SHORT).show();

                        ConstraintLayout oldparent =(ConstraintLayout) view.getParent();
                        oldparent.removeView(view);
                        LinearLayout newParent =(LinearLayout)v;
                        test2.setVisibility(View.GONE);
                        newParent.addView(view);
                    }
                    else if (view.getId()==R.id.btn3 && v.getId()==R.id.target3){
                        //Toast.makeText(MainActivity.this, "Dropped", Toast.LENGTH_SHORT).show();

                        ConstraintLayout oldparent =(ConstraintLayout) view.getParent();
                        oldparent.removeView(view);
                        LinearLayout newParent =(LinearLayout)v;
                        test3.setVisibility(View.GONE);
                        newParent.addView(view);
                    }
                    break;
            }

            return true;
        }
    };






    //comprueba el contador para hacer visible el boton jarraitu
    private void Jarraitu(){

        if (contadorButton==3){

            Intent popUp = new Intent(ActivityHutsuneakBete.this, PopupHorizontal.class);
            String valor  = "hutsuneak";
            popUp.putExtra("valor", valor );
            startActivity(popUp);
            //System.out.println(contadorButton);
            //btnJarraitu.setVisibility(View.VISIBLE);

        }
    }

    //comprueba  la respuesta del editText Gurutzearen
    private void comprobarGurutzearen(){

        if(contadorGurutze==0) {
            if (etGurutzearen.getText().toString().equals("gurutzearen")) {
                imgZuzena1.setVisibility(View.VISIBLE);
                imgOkerra1.setVisibility(View.INVISIBLE);
                MediaPlayer mediaPlayer = MediaPlayer.create(ActivityHutsuneakBete.this, R.raw.erantzun_zuzena4_audioa);
                mediaPlayer.start();
                //etGurutzearen.setEnabled(false);
                etGurutzearen.setKeyListener(null);
                contadorButton++;
                System.out.println(contadorButton);
                Jarraitu();
                contadorGurutze++;


            } else {
                imgZuzena1.setVisibility(View.INVISIBLE);
                imgOkerra1.setVisibility(View.VISIBLE);
                MediaPlayer mediaPlayer = MediaPlayer.create(ActivityHutsuneakBete.this, R.raw.erantzun_okerra4_audioa);
                mediaPlayer.start();
            }
        }


    }

    //comprueba  la respuesta del editText de berreraiki
    private void comprobarBerreraiki(){

        if(contadorBerreraiki==0) {

            if (etBerreraiki.getText().toString().equals("berreraiki")){
                imgZuzena3.setVisibility(View.VISIBLE);
                imgOkerra3.setVisibility(View.INVISIBLE);
                MediaPlayer mediaPlayer= MediaPlayer.create(ActivityHutsuneakBete.this,R.raw.erantzun_zuzena4_audioa);
                mediaPlayer.start();
                //etBerreraiki.setEnabled(false);
                etBerreraiki.setKeyListener(null);
                contadorButton++;
                System.out.println(contadorButton);
                Jarraitu();
                contadorBerreraiki++;

            }
            else{
                imgZuzena3.setVisibility(View.INVISIBLE);
                imgOkerra3.setVisibility(View.VISIBLE);
                MediaPlayer mediaPlayer= MediaPlayer.create(ActivityHutsuneakBete.this,R.raw.erantzun_okerra4_audioa);
                mediaPlayer.start();
            }

        }

    }

    //comprueba  la respuesta del editText de baseliza
    private void comprobarBaseliza(){

        if(contadorBaseliza==0) {

            if (etBaseliza.getText().toString().equals("Landaederreagako Santo Kristo Baseliza")||
                    etBaseliza.getText().toString().equals("Landaederreagako Santo Kristo baseliza") ||
                    etBaseliza.getText().toString().equals("landaederreagako santo kristo baseliza")||
                    etBaseliza.getText().toString().equals("Landaederreagako santo kristo baseliza")){

                imgZuzena2.setVisibility(View.VISIBLE);
                imgOkerra2.setVisibility(View.INVISIBLE);
                MediaPlayer mediaPlayer= MediaPlayer.create(ActivityHutsuneakBete.this,R.raw.erantzun_zuzena4_audioa);
                mediaPlayer.start();
                //etBaseliza.setEnabled(false);
                etBaseliza.setKeyListener(null);
                contadorButton++;
                System.out.println(contadorButton);
                Jarraitu();
                contadorBaseliza++;

            }
            else{
                imgZuzena2.setVisibility(View.INVISIBLE);
                imgOkerra2.setVisibility(View.VISIBLE);
                MediaPlayer mediaPlayer= MediaPlayer.create(ActivityHutsuneakBete.this,R.raw.erantzun_okerra4_audioa);
                mediaPlayer.start();
            }

        }
    }








}

