package com.example.livejavaconcepts;

// Classe criada para explicar o conceito de Tipos Genéricos
// O "T" após o nome da classe especifica isso -> poderia ser qualquer Letra
// Estamos dizendo que não é necessário que a classe seja utilizada para instanciar os tipos
// primitivos do Java, mas agora, podemos utilizar  QUALQUER TIPO que queiramos

public class SizeArray<T> {

    private T[] contents;
    // private String[] contents; => substituida por T
    private int count;

    public SizeArray(int size) {
        contents = (T[]) new Object[size];// cast para T -> Java não aceita diretamente T = new T
        count = 0;
    }

    public void add(T object) {
        //public void add(String object){
        contents[count] = object;
        count++;
        // |x|x|x|x|
    }

    public T get(int index) {
        return contents[index];
    }
}
