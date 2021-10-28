package co.tiagoaguiar.codelab.fitnesstracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Declarando o Linear Layout -> é sempre uma view...
    //private View btnIMC; => SUBSTITUÍDO PELO RECYCLERVIEW

    // Declarando o RecyclerView
    private RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	/*
	AÇÕES ANTERIORES AO RECYCLERVIEW...Didática
	Instanciando o objeto...
		btnIMC = findViewById(R.id.btn_imc);

	 Criar uma nova tela -> evento de ação: click -> instanciar uma view.onclicklistener
		 novo objeto criado toda vez que o "botão" for acionado...
		btnIMC.setOnClickListener(view -> {
        Intent intent = new Intent(MainActivity.this, ImcActivity.class );
        startActivity(intent);
        //});
    */
        rvMain = findViewById(R.id.rv_main);

        // Criando um array List para os objetos da classe MainItem
        // Instanciando o Array List:
        List<MainItem> mainItems = new ArrayList<>();
        mainItems.add(new MainItem(1, R.drawable.ic_baseline_wb_sunny_24, R.string.label_imc, Color.GREEN));
        mainItems.add(new MainItem(2, R.drawable.ic_baseline_visibility_24, R.string.label_tmb, Color.BLUE));

        // 1 -> Definir o comportamento de exibição do Layout da Recycler View
        // tipos:
        // mosaic
        // grid (galeria)
        // linear (horizontal | vertical)
        rvMain.setLayoutManager(new GridLayoutManager(this, 2));

        // 2 -> Adaptador: Espera uma classe to tipo adapter => ver MainAdapter abaixo...
        MainAdapter adapter = new MainAdapter(mainItems);
        adapter.setListener(id -> {
            switch (id) {
                case 1:
                startActivity(new Intent(MainActivity.this, ImcActivity.class));
                    break;
            case 2:
                startActivity(new Intent(MainActivity.this, TmbActivity.class));
                break;
            }

        });

        rvMain.setAdapter(adapter);
    }

    // Esta classe pede a célula(View) que será a referencia do item do LAYOUT a que nos referimos no RecyclerView (Layout main_item)
    // e possue, necessariamente, três métodos

    private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {


        private List<MainItem> mainItems;
        private onItemClickListener listener;

        public MainAdapter(List<MainItem> mainItems) {
            this.mainItems = mainItems;
        }

        public void setListener(onItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        // Este método pede a célula específica (Layout main_item.xml)
        // Infla o ViewHolder com o conteúdo do main_item Layout...

        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // o inflater instancia o layout dinâmico XML
            return new MainViewHolder(getLayoutInflater().inflate(R.layout.main_item, parent, false));
        }

        // Este método é responsável pela troca de conteúdo do container do Recycler View -> na rolagem
        @Override
        public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
            // Para obter a position -> ver função bind abaixo, no ViewHolder
            MainItem mainItemCurrent = mainItems.get(position);
            holder.bind(mainItemCurrent);
        }

        // Quantidade de items na tela que serão passados para a Recycler view
        @Override
        public int getItemCount() {
            return mainItems.size();
        }


        // Entenda como sendo a VIEW DA CELULA que está dentro do Recycler View
        private class MainViewHolder extends RecyclerView.ViewHolder {

            public MainViewHolder(@NonNull View itemView) {
                super(itemView);
            }

            public void bind(MainItem item) {
                TextView txtName = itemView.findViewById(R.id.item_txt_name);
                ImageView imgIcon = itemView.findViewById(R.id.item_img_icon);
                LinearLayout btnImc = (LinearLayout) itemView.findViewById(R.id.btn_imc);

                btnImc.setOnClickListener(view -> {
                    listener.onClick(item.getId());

                    txtName.setText(item.getTextStringId());
                    imgIcon.setImageResource(item.getDrawableId());
                    btnImc.setBackgroundColor(item.getColor());
                });
            }


        }
    }
}