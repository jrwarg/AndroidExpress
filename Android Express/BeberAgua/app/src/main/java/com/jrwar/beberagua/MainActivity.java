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

    private boolean activated; // declarando uma variavel para o evento de troca de cor do botão

    // Classe para ativar pequeno banco de dados local no Android:
    // Neste caso, quando o usuário retornar ao app, deverá encontrar sua programação anterior novamente...
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

        // Estabelecendo o banco de dados para conter os dados digitados do usuário
        // Na próxima abertura do app é necessário recarregar as últimas informações
        preferences = getSharedPreferences("db", Context.MODE_PRIVATE);

        // No primeiro acesso ao banco, o activated ainda não existe, não está criado, então
        // retorna false ...
        // Buscando a chave para saber se o botão está ativado ou não
        activated = preferences.getBoolean("activated", false);

        // verificar as propriedades para mudar o status:
        if(activated){
            // Se ativado, mudar a cor do botão
            btnNotify.setText(R.string.pause);
            int color = ContextCompat.getColor(this, android.R.color.black);
            btnNotify.setBackgroundColor(color);

            // buscar as propriedades do campo de texto:
            int interval = preferences.getInt("interval", 0);
            int hour = preferences.getInt("hour", timePicker.getCurrentHour());
            int minute = preferences.getInt("minute", timePicker.getCurrentMinute());

            // mudar as propriedades do campo:
            editMinutes.setText(String.valueOf(interval));// converte o inteiro para string
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
        }

    }
    // EVENTO DE CLICK VIA XML
    public void notifyClick(View v) {
        // "Escutando" os eventos de touch:
        String sInterval = editMinutes.getText().toString();
        // Garantindo que o campo de intervalo não está vazio... e mostrando mensagem
        if(sInterval.isEmpty()){
            Toast.makeText(this, R.string.error_msg, Toast.LENGTH_SHORT).show();
            return;
        }

        hour = timePicker.getCurrentHour();
        minute = timePicker.getCurrentMinute();
        interval = Integer.parseInt(sInterval); // transformando em Int

        if(!activated){
            btnNotify.setText(R.string.pause); // mudando o texto do botão quando clicado
            int color = ContextCompat.getColor(this, android.R.color.black);// retornar um desenhável
            btnNotify.setBackgroundColor(color); //mudando a cor do botão
            activated = true; // mudando o status do botão após o click

            // Escrevendo os dados no banco através do editor do banco de dados (SharedPreferences):
            // Salvando os registros digitados pelo usuário num arquivo interno (XML)
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("activated", true);
            editor.putInt("interval", interval);
            editor.putInt("hour", hour);
            editor.putInt("minute", minute);
            editor.apply(); // efetivar a escrita no bd



        } else {
            btnNotify.setText(R.string.notify);
            // classe obrigatória para obter o desenhável: ContextCompat
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
