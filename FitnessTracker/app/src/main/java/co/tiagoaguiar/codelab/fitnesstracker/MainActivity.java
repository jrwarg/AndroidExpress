package co.tiagoaguiar.codelab.fitnesstracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

	// Declarando o Linear Layout -> é sempre uma view...
	//private View btnIMC; => SUBSTITUÍDO PELO RECYCLERVIEW

	// Declarando o RecyclerView
	private RecyclerView rvMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	// Instanciando o objeto...
	//	btnIMC = findViewById(R.id.btn_imc);

	// Criar uma nova tela -> evento de ação: click -> instanciar uma view.onclicklistener
		// novo objeto criado toda vez que o "botão" for acionado...
		//btnIMC.setOnClickListener(view -> {
          //  Intent intent = new Intent(MainActivity.this, ImcActivity.class );
            //startActivity(intent);
        //});

		rvMain = findViewById(R.id.rv_main);
		// 1 -> Definir o comportamento de exibição do Layout da Recycler View
			// tipos:
			// mosaic
			// grid (galeria)
			// linear (horizontal | vertical)
		rvMain.setLayoutManager(new LinearLayoutManager(this));
		// 2 -> Adaptador: Espera uma classe to tipo adapter => ver MainAdapter abaixo...
		MainAdapter adapter = new MainAdapter();
		rvMain.setAdapter(adapter);
	}

	private class MainAdapter extends RecyclerView.Adapter<MainViewHolder>{
        // Esta classe pede a célula que será a referencia do item do Layout a que nos referimos no RecyclerView (main_item)
        // e possue, necessariamente, três métodos
		@NonNull
		@Override
        // Este método pede a célula específica

		public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		    // o inflater instancia o layout dinâmico XML
			return new MainViewHolder(getLayoutInflater().inflate(R.layout.main_item, parent, false));
		}
        // Este método é responsável pela troca de conteúdo do container do Recycler View -> na rolagem
		@Override
		public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
            // Para obter a position -> ver função bind abaixo, no ViewHolder
            holder.bind(position);
		}
        // Quantidade de items na tela que serão passados para a Recycler view
		@Override
		public int getItemCount() {
			return 15;
		}
	}

	// Entenda como sendo a VIEW DA CELULA que está dentro do Recycler View
	private class MainViewHolder extends RecyclerView.ViewHolder {

		public MainViewHolder(@NonNull View itemView) {
			super(itemView);
		}

		public void bind(int position){
            TextView textTest = itemView.findViewById(R.id.textview_test);
            textTest.setText("teste de rolagem " + position);
        }
	}

}