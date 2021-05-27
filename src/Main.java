public class Main {

    public static void main(String[] args) {

        ArvoreAVL arvore = new ArvoreAVL();

        // RAIZ SEMPRE IRA RECEBER OS MÉTODOS DE INSERÇÃO!

        arvore.raiz = arvore.insere(arvore.raiz,10);
        arvore.raiz = arvore.insere(arvore.raiz,15);
        arvore.raiz = arvore.insere(arvore.raiz,12);
        arvore.raiz = arvore.insere(arvore.raiz,9);
        arvore.raiz = arvore.insere(arvore.raiz,7);
        arvore.raiz = arvore.insere(arvore.raiz,5);

        System.out.println();
        arvore.existeElemento(arvore.raiz, 10);

        System.out.println();
        arvore.existeElemento(arvore.raiz, 9);

        System.out.println();
        arvore.removeElemento(arvore.raiz, 9);

        System.out.println();
        arvore.existeElemento(arvore.raiz, 9);

        System.out.println();
        System.out.println("--- IN ORDEM ---");
        arvore.imprimeInordem(arvore.raiz);

        System.out.println();
        System.out.println("--- PRE ORDEM ---");
        arvore.imprimePreordem(arvore.raiz);

        System.out.println();
        System.out.println("--- POS ORDEM ---");
        arvore.imprimePosordem(arvore.raiz);
    }
}
