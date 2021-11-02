package com.example.livejavaconcepts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
    //public class Button extends EscutarEvento{
//}
    public class Button extends TextView {
    public Button(Context context) {
        super(context);
    }


    Paint btnpaint = new Paint(); // precisamos instanciar um objeto da classe Paint (já existente no Android)
    // para utilizá-lo (estar "vivo" no contexto) no método onDraw
    Rect rect = new Rect(0, 0, 300, 300); // Mesma coisa

    @Override
    protected void onDraw(Canvas canvas) {

        btnpaint.setColor(Color.GRAY);

        canvas.drawRect(rect, btnpaint);

        super.onDraw(canvas);

    }

    // Como a classe pai é ABSTRACT, temos que sobreescrever o método
    @Override
    Paint getPaint() {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(50f);
        return paint;
    }
/*
        @Override
        void execute(String mensagem) {

        }*/
    }
