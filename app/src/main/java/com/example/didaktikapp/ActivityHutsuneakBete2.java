package com.example.didaktikapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ClipData;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class ActivityHutsuneakBete2 extends AppCompatActivity {

    //Esta pantalla es un juego de arrastrar los elementos a sus correspondientes casillas.
    private Button btn1, btn2,  btn3, btn4;
    private LinearLayout target1, target2, target3, target4;
    private int contadorButton = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_hutsuneak_bete2);

        //Se defienen los elementos y se les da listener de arrastre y long click

        target1 = findViewById(R.id.target1);

        btn1 = findViewById(R.id.btn1);
        target1.setOnDragListener(dragListener);
        btn1.setOnLongClickListener(longClickListener);

        target2 = findViewById(R.id.target2);

        btn2 = findViewById(R.id.btn2);
        target2.setOnDragListener(dragListener);
        btn2.setOnLongClickListener(longClickListener);

        target3 = findViewById(R.id.target3);

        btn3 = findViewById(R.id.btn3);
        target3.setOnDragListener(dragListener);
        btn3.setOnLongClickListener(longClickListener);

        target4 = findViewById(R.id.target4);

        btn4 = findViewById(R.id.btn4);
        target4.setOnDragListener(dragListener);
        btn4.setOnLongClickListener(longClickListener);


    }


    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadowBuilder, v, 0);

            return true;
        }
    };

    //Listener de arrastre, se activa cuando se suelta el elemento
    View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            final View view = (View) event.getLocalState();

            switch (dragEvent) {

                case DragEvent.ACTION_DRAG_ENTERED:
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    break;

                case DragEvent.ACTION_DROP:
                        //Si el elemento arrastrado coincide con el target
                    if (view.getId() == R.id.btn1 && v.getId() == R.id.target3) {

                        //el elemento se añade al target,se bloquea su movimiento, suena un audio,
                        // se suma a un contador y se llama a jarraitu() para comprobar si el juego ha acabado
                        ConstraintLayout oldparent = (ConstraintLayout) view.getParent();
                        oldparent.removeView(view);
                        LinearLayout newParent = (LinearLayout) v;
                        newParent.addView(view);
                        MediaPlayer mediaPlayer = MediaPlayer.create(ActivityHutsuneakBete2.this, R.raw.erantzun_zuzena2_audioa);
                        mediaPlayer.start();
                        contadorButton++;
                        btn1.setKeyListener(null);
                        target3.setEnabled(false);
                        Jarraitu();
                    } else if (view.getId() == R.id.btn2 && v.getId() == R.id.target2) {

                        ConstraintLayout oldparent = (ConstraintLayout) view.getParent();
                        oldparent.removeView(view);
                        LinearLayout newParent = (LinearLayout) v;
                        newParent.addView(view);
                        MediaPlayer mediaPlayer = MediaPlayer.create(ActivityHutsuneakBete2.this, R.raw.erantzun_zuzena2_audioa);
                        mediaPlayer.start();
                        contadorButton++;
                        btn2.setKeyListener(null);
                        target2.setEnabled(false);
                        Jarraitu();
                    } else if (view.getId() == R.id.btn3 && v.getId() == R.id.target1) {

                        ConstraintLayout oldparent = (ConstraintLayout) view.getParent();
                        oldparent.removeView(view);
                        LinearLayout newParent = (LinearLayout) v;
                        newParent.addView(view);
                        MediaPlayer mediaPlayer = MediaPlayer.create(ActivityHutsuneakBete2.this, R.raw.erantzun_zuzena2_audioa);
                        mediaPlayer.start();
                        contadorButton++;
                        btn3.setKeyListener(null);
                        target1.setEnabled(false);
                        Jarraitu();
                    } else if (view.getId() == R.id.btn4 && v.getId() == R.id.target4) {

                        ConstraintLayout oldparent = (ConstraintLayout) view.getParent();
                        oldparent.removeView(view);
                        LinearLayout newParent = (LinearLayout) v;
                        newParent.addView(view);
                        MediaPlayer mediaPlayer = MediaPlayer.create(ActivityHutsuneakBete2.this, R.raw.erantzun_zuzena2_audioa);
                        mediaPlayer.start();
                        contadorButton++;
                        btn4.setKeyListener(null);
                        target4.setEnabled(false);
                        Jarraitu();
                    }

                    else {
                        MediaPlayer mediaPlayer = MediaPlayer.create(ActivityHutsuneakBete2.this, R.raw.erantzun_okerra2_audioa);
                        mediaPlayer.start();
                    }
                    break;
            }

            return true;
        }
    };

    //comprueba el contador para activar el popup
    private void Jarraitu() {

        //Si los cuatro elementos estan en el target
        if (contadorButton == 4) {

            //Intent a popupHorizontal, el primero se ejecuta cuando estas haciendo el recorrido (desde el mapa),
            //el segundo cuando entras desde el menú de juegos
            try {
                if (getIntent().getStringExtra("valor").equals("historia2")){

                    Intent popUp = new Intent(ActivityHutsuneakBete2.this, PopupHorizontal.class);
                    String valor  = "hutsuneak2historia";
                    popUp.putExtra("valor", valor );
                    startActivity(popUp);
                }
            }
            catch (Exception e){
                Intent popUp = new Intent(ActivityHutsuneakBete2.this, PopupHorizontal.class);
                String valor  = "hutsuneak2";
                popUp.putExtra("valor", valor );
                startActivity(popUp);
            }







        }
    }







}
