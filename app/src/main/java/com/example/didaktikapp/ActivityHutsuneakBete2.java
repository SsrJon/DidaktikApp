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

    private Button btn1, btn2,  btn3, btn4;
    private LinearLayout target1, target2, target3, target4;
    private int contadorButton = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_hutsuneak_bete2);


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

                    if (view.getId() == R.id.btn1 && v.getId() == R.id.target1) {

                        ConstraintLayout oldparent = (ConstraintLayout) view.getParent();
                        oldparent.removeView(view);
                        LinearLayout newParent = (LinearLayout) v;
                        newParent.addView(view);
                        MediaPlayer mediaPlayer = MediaPlayer.create(ActivityHutsuneakBete2.this, R.raw.erantzun_zuzena2_audioa);
                        mediaPlayer.start();
                        contadorButton++;
                        Jarraitu();
                    } else if (view.getId() == R.id.btn2 && v.getId() == R.id.target2) {

                        ConstraintLayout oldparent = (ConstraintLayout) view.getParent();
                        oldparent.removeView(view);
                        LinearLayout newParent = (LinearLayout) v;
                        newParent.addView(view);
                        MediaPlayer mediaPlayer = MediaPlayer.create(ActivityHutsuneakBete2.this, R.raw.erantzun_zuzena2_audioa);
                        mediaPlayer.start();
                        contadorButton++;
                        Jarraitu();
                    } else if (view.getId() == R.id.btn3 && v.getId() == R.id.target3) {

                        ConstraintLayout oldparent = (ConstraintLayout) view.getParent();
                        oldparent.removeView(view);
                        LinearLayout newParent = (LinearLayout) v;
                        newParent.addView(view);
                        MediaPlayer mediaPlayer = MediaPlayer.create(ActivityHutsuneakBete2.this, R.raw.erantzun_zuzena2_audioa);
                        mediaPlayer.start();
                        contadorButton++;
                        Jarraitu();
                    } else if (view.getId() == R.id.btn4 && v.getId() == R.id.target4) {

                        ConstraintLayout oldparent = (ConstraintLayout) view.getParent();
                        oldparent.removeView(view);
                        LinearLayout newParent = (LinearLayout) v;
                        newParent.addView(view);
                        MediaPlayer mediaPlayer = MediaPlayer.create(ActivityHutsuneakBete2.this, R.raw.erantzun_zuzena2_audioa);
                        mediaPlayer.start();
                        contadorButton++;
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

        if (contadorButton == 4) {

            Intent popUp = new Intent(ActivityHutsuneakBete2.this, PopupHorizontal.class);
            String valor = "hutsuneak2";
            popUp.putExtra("valor", valor);
            startActivity(popUp);
        }
    }







}
