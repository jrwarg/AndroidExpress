package co.tiagoaguiar.codelab.fitnesstracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	// Declarando o Linear Layout -> é sempre uma view...
	private View btnIMC;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	// Instanciando o objeto...
		btnIMC = findViewById(R.id.btn_imc);
	// Criar uma nova tela -> evento de ação: click -> instanciar uma view.onclicklistener
		// novo objeto criado toda vez que o "botão" for acionado...
		btnIMC.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, ImcActivity.class );
				startActivity(intent);
			}

		});

	}



}