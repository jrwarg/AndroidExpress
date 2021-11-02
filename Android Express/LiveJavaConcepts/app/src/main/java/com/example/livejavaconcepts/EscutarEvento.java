package com.example.livejavaconcepts;


// INTERFACE : é uma classe abstrata com comportamento abstrato
// Não tem propriedades e métodos definidos

public interface EscutarEvento {
    void execute(String mensagem);
}
/*
* Agora, qualquer classe que, por exemplo, estender a classe EscutarEvento, terá,
* necessariamente, que sobreescrever o método execute...
* Vide caso na classe Button
* */