package com.jrwar.beberagua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // DECLARANDO VARIÁVEIS DE CAMPO

    private Button btnNotify;
    private EditText editMinutes;
    private TimePicker timePicker;

    private int hour;
    private int minute;
    private int interval;

    private boolean activated;

    // Classe para ativar pequeno banco de dados local no Android:
    // Mode Private -> nenhum app no futuro irá conseguir acessar esses dados
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // INSTANCIANDO...
        // PEGAR AS REFERÊNCIAS DO LAYOUT (UNIDADE ESTÁTICA)=> PODEMOS, AGORA, MANIPULÁ-LAS PROGRAMAR
        // A FUNÇÃO FINDVIEWBYID IRÁ CRIAR O OBJETO E INSTANCIÁ-LO

        btnNotify = findViewById(R.id.btnNotify);
        editMinutes = findViewById(R.id.edit_txt_number_interval);
        timePicker = findViewById(R.id.time_picker);

        // Mudar as propriedades para formato relógio 24 horas:
        timePicker.setIs24HourView(true);

        preferences = getSharedPreferences("db", Context.MODE_PRIVATE);

        // No primeiro acesso ao banco, o activated ainda não existe, não está criado, então
        // retorna false ...
        preferences.getBoolean("activated", false);
        // verificar as propriedades para mudar o status:
        if(activated){
            btnNotify.setText(R.string.pause);
            int color = ContextCompat.getColor(this, android.R.color.black);
            btnNotify.setBackgroundColor(color);
            activated = true;
        }

    }

    public void notifyClick(View view) {
        // "Escutando" os eventos de touch:
        String sInterval = editMinutes.getText().toString();
        if(sInterval.isEmpty()){
            Toast.makeText(this, R.string.error_msg, Toast.LENGTH_SHORT).show();
            return;
        }

        hour = timePicker.getCurrentHour();
        minute = timePicker.getCurrentMinute();
        interval = Integer.parseInt(sInterval);

        if(!activated){
            btnNotify.setText(R.string.pause);
            int color = ContextCompat.getColor(this, android.R.color.black);
            btnNotify.setBackgroundColor(color);
            activated = true;

            // Escrevendo os dados no banco através do editor:

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("activated", true);
            editor.putInt("interval", interval);
            editor.putInt("hour", hour);
            editor.putInt("minute", minute);
            editor.apply();



        } else {
            btnNotify.setText(R.string.notify);
            int color = ContextCompat.getColor(this, R.color.colorAccent);
            btnNotify.setBackgroundColor(color);
            activated = false;

            // Caso não ativado, remover os dados do banco:

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("activated", false);
            editor.remove("interval");
            editor.remove("hour");
            editor.remove("minute");
            editor.apply();
        }



        Log.d("Teste", "hora: " + hour + " minutos:" + minute + " intervalo: " + interval);


    }
}
// Setando no botão a variável (SEM USAR O XML):
// btnNotify.setOnClickListener(notifyClick);

        /* EVENTO DE CLICK COM OBJETO ANÔNIMO:
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // "Escutando" os eventos de touch:
                String sInterval = editMinutes.getText().toString();
                hour = timePicker.getCurrentHour();
                minute = timePicker.getCurrentMinute();
                interval = Integer.parseInt(sInterval);

                Log.d("Teste", "hora: " + hour + " minutos:" + minute + " intervalo: " + interval);


            }
        });

    }
 /*
    // EVENTO DE CLICK COM VARIÁVEL ANÔNIMA

    SEM USAR O XML => MAIS "RAIZ" NO JAVA
    //
    public View.OnClickListener notifyClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                // "Escutando" os eventos de touch:
                String sInterval = editMinutes.getText().toString();
                hour = timePicker.getCurrentHour();
                minute = timePicker.getCurrentMinute();
                interval = Integer.parseInt(sInterval);

                Log.d("Teste", "hora: " + hour + " minutos:" + minute + " intervalo: "+ interval);
            }
    };


    EVENTO DE CLICK VIA XML

    Neste tipo de programação, será necessário declarar a função no XML:
    android:onClick="notifyClick"
    Esta seria uma solução mais "fácil" => básica para fazer a escuta do click

    O que foi adotado no código... acima
         */
