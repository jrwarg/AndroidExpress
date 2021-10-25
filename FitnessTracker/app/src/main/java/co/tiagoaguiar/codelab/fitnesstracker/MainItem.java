package co.tiagoaguiar.codelab.fitnesstracker;
// Criando itens dinâmicos de uma célula -> no caso a do Layout main_item.xml
public class MainItem {
    // Items dinâmicos adicionados/declarados
    private int id;
    private int drawableId;
    private int textStringId;
    private int color;

    // Criando um construtor

    public MainItem(int id, int drawableId, int textStringId, int color) {
        this.id = id;
        this.drawableId = drawableId;
        this.textStringId = textStringId;
        this.color = color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTextStringId(int textStringId) {
        this.textStringId = textStringId;
    }

    public int getColor() {
        return color;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public int getId() {
        return id;
    }

    public int getTextStringId() {
        return textStringId;
    }
}
