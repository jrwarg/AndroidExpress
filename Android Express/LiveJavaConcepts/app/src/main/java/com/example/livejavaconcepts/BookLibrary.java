package com.example.livejavaconcepts;

public class BookLibrary extends Book{

    public BookLibrary(){
        // Explicando o conceito de Herança;
        // O super obriga a execução de todos os métodos "acima" (pais) para depois retornar e dar
        // as instruções do que escrevemos no nosso código/bloco
        // Poderíamos, dessa forma, alterar o resultado das classes pais para atingir um det. resultado
        // isso também é chamado de ABSTRAÇÃO

        super();
        // resto do código
    }

    private boolean borrowed;

}
