package co.tiagoaguiar.codelab.fitnesstracker;

import static androidx.core.os.LocaleListCompat.create;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ImcActivity extends AppCompatActivity {

    private EditText editHeight;
    private EditText editWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

    editHeight = findViewById(R.id.edit_imc_height);
    editWeight = findViewById(R.id.edit_imc_weight);

    Button btnSend = findViewById(R.id.btn_imc_send);
    /*
    * FUNÇÃO ONCLICK LISTENER SEM LAMBDAS:
    * btnSend.setOnClickListener(new View.onClickListener()){
    *   @Override
    *   public void onClick(View view){
    *       if(!validate)....

     Replace com lambdas: uma forma mais estilizada e enxuta de declaração de classes Anônimas:
        quando essas classes são simples, possuem somente uma função/método
        Disponíveis quando a compilação é feita com Java 8 -> ver alteração no build.gradle(app)
     */
    btnSend.setOnClickListener(v -> {
        if (!validate()) {
            Toast.makeText(ImcActivity.this, R.string.fields_messages, Toast.LENGTH_LONG).show();
            return;
        }
        // Iniciando o cálculo para o evento ONCLICKLISTENER(BUTTON):
        // Primeiro pegando os dados ainda em formato String (porisso o sHeight)
        String sHeight = editHeight.getText().toString();
        String sWeight = editWeight.getText().toString();
        // Transformando os dados em Int => para podermos fazer os cálculos
        int height = Integer.parseInt(sHeight);
        int weight = Integer.parseInt(sWeight);
        // Determinando uma variável chamando a função calculada (calculateImc)
        double result = calculateImc(height, weight);
        // Log.d("teste", "resultado: " + result);

        // Determinando uma variável chamando a função IMC
        int imcResponseId = imcResponse(result);

        // criando um alerta como resposta ao result
        AlertDialog dialog = new AlertDialog.Builder(ImcActivity.this)
                .setTitle(getString(R.string.imc_response, result)) // -> Setando o título do Alerta dinamicamente (de acordo com o result)
                .setMessage(imcResponseId)
                .setPositiveButton(android.R.string.ok, (dialog1, which) -> {
                })
                .create();
        dialog.show();

        // Toast.makeText(ImcActivity.this, R.string.fields_messages, Toast.LENGTH_SHORT).show();-> trocado pela criação do alerta

        // ESCONDER O TECLADO: Utilizar o serviço gerenciador -> Métodos do android para gerenciar os serviços
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editHeight.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(editWeight.getWindowToken(), 0);


    });

    }
    // Anotações '@' servem para garantir a compilação correta: nesse caso, por exemplo,
    // um número qualquer seria um inteiro, mas não é um arquivo de Resource do android
    // Isso garante a consistência do método!

    // FUNÇÃO PARA RETORNAR AS STRINGS DE FORMA LEGÍVEL AO USUÁRIO:
    @StringRes
    private int imcResponse(double imc){
        if(imc < 15)
            return R.string.imc_severely_low_weight;
        else if (imc < 16)
            return R.string.imc_very_low_weight;
        else if (imc < 18.5)
            return R.string.imc_very_low_weight;
        else if (imc < 25)
            return R.string.normal;
        else if (imc < 30)
            return R.string.imc_high_weight;
        else if (imc < 35)
            return R.string.imc_so_high_weight;
        else if (imc < 40)
            return R.string.imc_severely_high_weight;
        else
            return R.string.imc_extreme_weight;

    }
    // FUNÇÃO PARA CALCULAR O IMC:
    private  double calculateImc(int height, int weight) {
        // peso / (altura * altura)
         return weight / (((double) height / 100) * ((double) height / 100));
    }

    // FUNÇÃO INICIAL PARA VALIDAÇÃO DOS DADOS DIGITADOS PELO USUÁRIO:
    // Suprimimos o if / else, colocando o return diretamente
    private boolean validate() {
        return (!editHeight.getText().toString().startsWith("0")
                && !editWeight.getText().toString().startsWith("0")
                && !editHeight.getText().toString().isEmpty()
                && !editWeight.getText().toString().isEmpty()
        );
    }
}