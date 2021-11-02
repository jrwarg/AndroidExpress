package com.example.livejavaconcepts;

// Criando um text view no layout : na unha... (sem utilizar os layouts do Android)

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public abstract class TextView extends View { //-> A classe View nos obriga a criar um construtor:

    public TextView(Context context) {
        // Super => sempre usado quando configuramos alguma coisa da classes pais
        super(context);
    }

    //Paint paint = new Paint(); // precisamos instanciar um objeto da classe Paint (já existente no Android)
                                // para utilizá-lo (estar "vivo" no contexto) no método onDraw

    // EXPLICANDO MÉTODOS ABSTRATOS:
    // Criando um método abstrato que vai retornar uma cor (Paint, do Android)

    abstract Paint getPaint(); //** A classe pai PRECISA SER ABSTRATA também...(TextView)


    @Override // Sobreescrevendo o método onDraw -> veja o super
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = getPaint();
        canvas.drawText("Olá, mundo!", 0, 120, paint);

    }
}
