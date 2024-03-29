package com.example.didaktikapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import static android.view.View.INVISIBLE;

public class MikaFinal extends AppCompatActivity {

    // Screen Size
    private int screenWidth;
    private int screenHeight;

    // Images
    private ImageView arrowUp;
    private ImageView arrowDown;
    private ImageView arrowRight;
    private ImageView arrowLeft;
    private ImageView botella;

    // Button
    private Button pauseBtn;

    // Position
    private float arrowUpX;
    private float arrowUpY;
    private float arrowDownX;
    private float arrowDownY;
    private float arrowRightX;
    private float arrowRightY;
    private float arrowLeftX;
    private float arrowLeftY;

    // Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();


    // Status Check
    private boolean pause_flg = false;

    private int contadorClicks=0;

    TextView porcentaje;
    int porciento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mika_final);
        porcentaje = findViewById(R.id.porcentaje);
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle(R.string.ultimo);
        dialogo1.setMessage(R.string.mika_final);
        dialogo1.setCancelable(false);
        dialogo1.setIcon(R.drawable.mika_sin_fondo);

        dialogo1.setPositiveButton("Onartu", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
            }
        });

        dialogo1.show();

        arrowUp = findViewById(R.id.arrowUp);
        arrowDown = findViewById(R.id.arrowDown);
        arrowRight = findViewById(R.id.arrowRight);
        arrowLeft = findViewById(R.id.arrowLeft);
        botella=findViewById(R.id.imageViewBotella);

        // Get Screen Size.
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        // Move to out of screen.
        arrowUp.setX(-80.0f);
        arrowUp.setY(-80.0f);
        arrowDown.setX(-80.0f);
        arrowDown.setY(screenHeight + 80.0f);
        arrowRight.setX(screenWidth + 80.0f);
        arrowRight.setY(-80.0f);
        arrowLeft.setX(-80.0f);
        arrowLeft.setY(-80.0f);




        arrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrowUp.setVisibility(INVISIBLE);
                MediaPlayer mediaPlayer = MediaPlayer.create(MikaFinal.this, R.raw.correct);
                mediaPlayer.start();
                contadorClicks++;
                botellaProgreso();


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        arrowUp.setVisibility(View.VISIBLE);

                    }
                }, 2000);
            }
        });

        arrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrowDown.setVisibility(INVISIBLE);
                MediaPlayer mediaPlayer = MediaPlayer.create(MikaFinal.this, R.raw.correct);
                mediaPlayer.start();
                contadorClicks++;
                botellaProgreso();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        arrowDown.setVisibility(View.VISIBLE);

                    }
                }, 2000);
            }
        });

        arrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrowRight.setVisibility(INVISIBLE);
                MediaPlayer mediaPlayer = MediaPlayer.create(MikaFinal.this, R.raw.correct);
                mediaPlayer.start();
                contadorClicks++;
                botellaProgreso();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        arrowRight.setVisibility(View.VISIBLE);

                    }
                }, 2000);
            }
        });

        arrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrowLeft.setVisibility(INVISIBLE);
                MediaPlayer mediaPlayer = MediaPlayer.create(MikaFinal.this, R.raw.correct);
                mediaPlayer.start();
                contadorClicks++;
                botellaProgreso();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        arrowLeft.setVisibility(View.VISIBLE);
                    }
                }, 2000);
            }
        });


        // Start the timer.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();

                    }
                });

            }
        }, 0, 8);

    }

    public void botellaProgreso(){
        porciento = porciento + 4;
        porcentaje.setText(porciento + "%");
        if (contadorClicks==1){
            botella.setImageResource(R.drawable.botella2);
        } else if (contadorClicks==10){
            botella.setImageResource(R.drawable.botella3);
        } else if (contadorClicks==15){
            botella.setImageResource(R.drawable.botella4);
        } else if (contadorClicks==20){
            botella.setImageResource(R.drawable.botella5);
        } else if (contadorClicks==25){
            botella.setImageResource(R.drawable.botella6);
            arrowDown.setVisibility(INVISIBLE);
            arrowUp.setVisibility(INVISIBLE);
            arrowRight.setVisibility(INVISIBLE);
            arrowLeft.setVisibility(INVISIBLE);
            MediaPlayer mediaPlayer= MediaPlayer.create(MikaFinal.this,R.raw.erantzunzuzena6_audioa);
            mediaPlayer.start();
            try {
                if (getIntent().getStringExtra("valor").equals("mika")){

                    Intent popUp = new Intent(MikaFinal.this, Popup.class);
                    String valor  = "mika7historia";
                    popUp.putExtra("valor", valor );
                    startActivity(popUp);
                }
                } catch (Exception e){
                    Intent popUp = new Intent(MikaFinal.this, Popup.class);
                    String valor  = "mikalibre";
                    popUp.putExtra("valor", valor );
                    startActivity(popUp);
                }
        }else if(contadorClicks>25){
            porcentaje.setVisibility(INVISIBLE);
        }
    }


    public void changePos() {
        // Up
        arrowUpY -= 10;
        if (arrowUp.getY() + arrowUp.getHeight() < 0) {
            arrowUpX = (float)Math.floor(Math.random() * (screenWidth - arrowUp.getWidth()));
            arrowUpY = screenHeight + 100.0f;
        }
        arrowUp.setX(arrowUpX);
        arrowUp.setY(arrowUpY);

        // Down
        arrowDownY += 10;
        if (arrowDown.getY() > screenHeight) {
            arrowDownX = (float)Math.floor(Math.random() * (screenWidth - arrowDown.getWidth()));
            arrowDownY = -100.0f;
        }
        arrowDown.setX(arrowDownX);
        arrowDown.setY(arrowDownY);

        // Right
        arrowRightX += 10;
        if (arrowRight.getX() > screenWidth) {
            arrowRightX = -100.0f;
            arrowRightY = (float)Math.floor(Math.random() * (screenHeight - arrowRight.getHeight()));
        }
        arrowRight.setX(arrowRightX);
        arrowRight.setY(arrowRightY);

        // Left
        arrowLeftX -= 10;
        if (arrowLeft.getX() + arrowLeft.getWidth() < 0) {
            arrowLeftX = screenWidth + 100.0f;
            arrowLeftY = (float)Math.floor(Math.random() * (screenHeight - arrowLeft.getHeight()));
        }
        arrowLeft.setX(arrowLeftX);
        arrowLeft.setY(arrowLeftY);

    }




}
