public class NoAVL {
    public NoAVL esquerda;
    public NoAVL direita;
    public int dado;
    public int altura;
    public String palavra;
    // public Lista; FALTOU A LISTA DE OCORRENCIAS REFERENCIADAS NA ARVORE!

    public NoAVL(int valor) {
        this.dado = valor;
        this.esquerda = null;
        this.direita = null;
        this.altura = -1;
    }

    public NoAVL(String p) { // Construtor para segunda parte da implementação!
        palavra = p;
        altura = 0;
    }
}
