package com.example.livejavaconcepts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

public class ButtonSecondary extends Button{

    private EscutarEvento evento;

    public ButtonSecondary(Context context){
        super(context);
    }

    public void setEvento(EscutarEvento escutarEvento) {
        this.evento = escutarEvento;
    }

    @Override
    Paint getPaint() {
        evento.execute("Estou dentro do getPaint do Button Secondary");
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(50f);
        return paint;
    }
}
