public class ArvoreAVL {
    public NoAVL raiz;

    public ArvoreAVL() {
        this.raiz = null;
    }

    public NoAVL insere(NoAVL arvore, int elemento) {

        if(arvore == null) {
            return (new NoAVL(elemento));
        }
        if(elemento < arvore.dado) {
            arvore.esquerda = insere(arvore.esquerda, elemento);
        }else if(elemento > arvore.dado) {
            arvore.direita = insere(arvore.direita, elemento);
        }else {
            return arvore;
        }

        arvore.altura = 1 + numeroMaior(altura(arvore.esquerda), altura(arvore.direita));

        // BALANCEAMENTO
        int balanceamento = fatorBalancemaneto(arvore);

        // ROTACAO SIMPLES
        if(balanceamento > 1 && fatorBalancemaneto(arvore.esquerda) >= 0) {
            return rotacaoDireita(arvore);
        }if(balanceamento < -1 && fatorBalancemaneto(arvore.direita) <= 0) {
            return rotacaoEsquerda(arvore);
        }

        // ROTACAO DUPLA
        if(balanceamento > 1 && fatorBalancemaneto(arvore.esquerda)<0) {
            arvore.esquerda = rotacaoEsquerda(arvore.esquerda);
            return rotacaoDireita(arvore);
        }if(balanceamento < -1 && fatorBalancemaneto(arvore.direita)>0) {
            arvore.direita = rotacaoDireita(arvore.direita);
            return rotacaoEsquerda(arvore);
        }
        return arvore;
    }

    public int altura(NoAVL no){
        if(no == null) {
            return -1;
        }

        int alturaEsquerda = altura(no.esquerda);
        int alturaDireita = altura(no.direita);

        if(alturaEsquerda < alturaDireita) {
            return alturaDireita + 1;
        } else{
            return alturaEsquerda + 1;
        }
    }

    public int fatorBalancemaneto(NoAVL no){
        if (no == null){
            return 0;
        } else {
            return altura(no.esquerda) - altura(no.direita);
        }
    }

    public int numeroMaior(int a, int b) {
        if(a > b) {
            return a;
        }else {
            return b;
        }
    }

    public NoAVL rotacaoEsquerda(NoAVL no) {
        NoAVL no2;
        NoAVL armNo;

        no2 = no.direita;
        armNo = no2.esquerda;
        no2.esquerda = no;
        no.direita = armNo;

        no.altura = numeroMaior(altura(no.esquerda), altura(no.direita)) + 1;
        no2.altura = numeroMaior(altura(no2.esquerda), altura(no2.direita)) + 1;

        return no2;
    }

    public NoAVL rotacaoDireita(NoAVL no) {
        NoAVL no2;
        NoAVL armNo;

        no2 = no.esquerda;
        armNo = no2.direita;
        no2.direita = no;
        no.esquerda = armNo;

        no.altura = numeroMaior(altura(no.esquerda), altura(no.direita)) + 1;
        no2.altura = numeroMaior(altura(no2.esquerda), altura(no2.direita)) + 1;

        return no2;
    }

    public NoAVL removeAtual(NoAVL noAtual) {
        NoAVL no1;
        NoAVL no2;
        if(noAtual.esquerda == null) {
            no2 = noAtual.direita;
            return no2;
        }no1 = noAtual;
        no2 = noAtual.esquerda;
        while(no2.direita != null) {
            no1 = no2;
            no2 = no2.direita;
        }if(no1 != noAtual) {
            no1.direita = no2.esquerda;
            no2.esquerda = noAtual.esquerda;
        }no2.direita = noAtual.direita;
        return no2;
    }

    public boolean existeElemento(NoAVL arvore,int elemento){

        System.out.println("--- ELEMENTO " + elemento + " EXISTE? ---");

        NoAVL no = arvore;

        while(no != null && elemento != no.dado) {
            if(no.dado > elemento) {
                no = no.esquerda;
            }else {
                no = no.direita;
            }
        }
        if(no!=null && elemento == no.dado) {
            System.out.println("Elemento existe!");
            return true;
        }else {
            System.out.println("Elemento inexistente!");
            return false;
        }
    }

    public void removeElemento(NoAVL arvore, int elemeneto){
        if(raiz==null) {
            return;
        }

        System.out.println("--- REMOVENDO O ELEMENTO " + elemeneto + "! ---");

        NoAVL noAnterior = null;
        NoAVL noAtual = raiz;
        while(noAtual != null) {
            if(elemeneto == noAtual.dado) {
                if(noAtual==raiz) {
                    raiz = removeAtual(noAtual);
                }else {
                    if(noAnterior.direita == noAtual) {
                        noAnterior.direita = removeAtual(noAtual);
                    }else {
                        noAnterior.esquerda = removeAtual(noAtual);
                    }
                    return;
                }
            }noAnterior = noAtual;
            if(elemeneto > noAtual.dado) {
                noAtual = noAtual.direita;
            }else {
                noAtual = noAtual.esquerda;
            }
        }

        arvore.altura = 1 + numeroMaior(altura(arvore.esquerda), altura(arvore.direita));

        // BALANCEAMENTO
        int balanceamento = fatorBalancemaneto(arvore);

        // ROTACAO SIMPLES
        if(balanceamento > 1 && fatorBalancemaneto(arvore.esquerda)>=0) {
            rotacaoDireita(arvore);
            return;
        }if(balanceamento < -1 && fatorBalancemaneto(arvore.direita)<=0) {
            rotacaoEsquerda(arvore);
            return;
        }

        // ROTACAO DUPLA
        if(balanceamento > 1 && fatorBalancemaneto(arvore.esquerda)<0) {
            arvore.esquerda = rotacaoEsquerda(arvore.esquerda);
            rotacaoDireita(arvore);
            return;
        }if(balanceamento < -1 && fatorBalancemaneto(arvore.direita)>0) {
            arvore.direita = rotacaoDireita(arvore.direita);
            rotacaoEsquerda(arvore);
        }
    }

    public void imprimePreordem(NoAVL no){
        if(no!=null) {
            System.out.print(no.dado + " ");
            imprimePreordem(no.esquerda);
            imprimePreordem(no.direita);
        }
    }

    public void imprimeInordem(NoAVL no){
        if(no != null){
            imprimeInordem(no.esquerda);
            System.out.print(no.dado  + " ");
            imprimeInordem(no.direita);
        }
    }

    public void imprimePosordem(NoAVL no){
        if(no != null){
            imprimePosordem(no.esquerda);
            imprimePosordem(no.direita);
            System.out.print(no.dado  + " ");
        }
    }




    /*      --- MÉTODOS DESENVOLVIDOS QUANDO UTILIZAMOS O OBJETO DE 'ArvoreAVL' PARA REFERENCIAR A 'raiz' ---


    public void existeElemento(NoAVL no, int elemento){

    if (no != null){
        if (no.dado == elemento){
            System.out.println("O elemento " + elemento + " existe na arvore");
        } else {
            existeElemento(no.esquerda, elemento);
            existeElemento(no.direita, elemento);
        }
    }

    public void preOrdem(NoAVL raiz){ // IMPRESSÃO DO MENOR PARA O MAIOR ELEMENTO [PRONTO]
        if (raiz != null){
            System.out.println(raiz.dado);
            preOrdem(raiz.esquerda);
            preOrdem(raiz.direita);
        }
    }

    public void inOrdem(NoAVL raiz){ // IMPRESSÃO DA ESQUERDA PARA A DIREITA [PRONTO]
        if (raiz != null){
            inOrdem(raiz.esquerda);
            System.out.println(raiz.dado);
            inOrdem(raiz.direita);
        }
    }

    public void posOrdem(NoAVL raiz){ // IMPRESSÃO DA DIREITA PARA A ESQUERDA [PRONTO]
        if (raiz != null){
            posOrdem(raiz.esquerda);
            posOrdem(raiz.direita);
            System.out.println(raiz.dado);
        }
    }



    public void maiorElemento(){ // MAIOR ELEMENTO DE TODA A ÁRVORE [PRONTO]
        NoAVL raizMaior = raiz;
        if (raizMaior != null){
            NoAVL noMaior = null;
            while (raizMaior != null){
                noMaior = raizMaior;
                raizMaior = raizMaior.direita;
            }
            System.out.println(noMaior.dado);
        } else {
            System.out.println("Raiz vazia");
        }
    }

    public void menorElemento(){ // MENOR ELEMENTO DE TODA A ÁRVORE [PRONTO]
        NoAVL raizMenor = raiz;
        if (raizMenor != null){
            NoAVL noMenor = null;
            while (raizMenor != null){
                noMenor = raizMenor;
                raizMenor = raizMenor.esquerda;
            }
            System.out.println(noMenor.dado);
        } else {
            System.out.println("Raiz vazia");
        }
    } */

}
