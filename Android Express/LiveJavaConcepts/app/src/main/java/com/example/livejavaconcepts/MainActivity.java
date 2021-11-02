package com.example.livejavaconcepts;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EscutarEvento{

    // Instanciando uma coleção de diversos checkBoxes...
    List<CheckBox> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    /*
        ** CONTEXTO ABAIXO USADO PARA EXPLICAÇÕES ESPECÍFICAS **
        setContentView(R.layout.activity_main);
     */
        // criando uma view sem o setContent (propriedade do AppCompatAcitivity.)

        /*
        View view = new View(this); // Essa classe é um molde genérico do Android: cria qq. tipo de view
        view.setBackgroundColor(Color.GREEN);
        view.setLayoutParams(new FrameLayout.LayoutParams(500, 500));
        setContentView(view);


        FrameLayout view = new FrameLayout(this);
        view.setLayoutParams(new FrameLayout.LayoutParams(500, 300));
        view.setBackgroundColor(Color.GREEN);

        TextView textview = new Button(this);
        // Herança: o botão foi declarado como TextView, porque ele é herdeiro
        view.addView(textview);

        setContentView(view);
    */
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(100, 100);

        CheckBox checkBox1 = new CheckBox(this);
        CheckBox checkBox2 = new CheckBox(this);
        CheckBox checkBox3 = new CheckBox(this);
        CheckBox checkBox4 = new CheckBox(this);
        CheckBox checkBox5 = new CheckBox(this);

        checkBox1.setLayoutParams(lp);
        checkBox2.setLayoutParams(lp);
        checkBox3.setLayoutParams(lp);
        checkBox4.setLayoutParams(lp);
        checkBox5.setLayoutParams(lp);

        list.add(checkBox1);
        list.add(checkBox2);
        list.add(checkBox3);
        list.add(checkBox4);
        list.add(checkBox5);

        // Criando um Linear Layout para receber a lista/coleção....
        LinearLayout view = new LinearLayout(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)); // largura e altura do layout
        // Iterando sobre a lista e adicionando cada elemento(checkbox)
        for(CheckBox checkBox: list) {
            view.addView(checkBox);
        }

        setContentView(view);

        // Criamos um tipo genérico que pode ser utilizado globalmente para criar qualquer outro tipo:
        // => Caso 1: Arrays de inteiros...:
        SizeArray<Integer> sizeArray = new SizeArray<Integer>(5); //=> Classe genérica...(T)
       // SizeArray sizeArray = new SizeArray(5);
        sizeArray.add(10);
        sizeArray.add(11);
        sizeArray.add(12);

        // => Caso 2: Arrays de Buttons...:
        SizeArray<Button> buttons = new SizeArray<Button>(5);
        buttons.add(new Button(this));
        buttons.add(new Button(this));
        buttons.add(new Button(this));
        buttons.add(new Button(this));

        ButtonSecondary buttonSecondary = new ButtonSecondary(this);
        buttonSecondary.setEvento(this);

        view.addView(buttonSecondary);
    }

    @Override
    public void execute(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }
}