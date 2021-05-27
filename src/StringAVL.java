public class StringAVL {

    public NoAVL raiz;

    public StringAVL() {
        raiz = null;
    }

    public NoAVL insere(NoAVL arvore,String palavra){

        if(arvore == null) {
            return (new NoAVL(palavra));
        }

        if(palavra.compareTo(raiz.palavra) <= 0) {
            arvore.esquerda = insere(arvore.esquerda, palavra);
        }else if(palavra.compareTo(raiz.palavra) > 0) {
            arvore.direita = insere(arvore.direita, palavra);
        }else {
            return arvore;
        }

        arvore.altura = 1 + maior(altura(arvore.esquerda), altura(arvore.direita));

        //balanceamento
        int balanceamento = fatorBalanceamento(arvore);

        // ROTAÇÃO SIMPLES
        if(balanceamento > 1 && fatorBalanceamento(arvore.esquerda)>=0) {
            return rotacaoDireita(arvore);
        }if(balanceamento < -1 && fatorBalanceamento(arvore.direita)<=0) {
            return rotacaoEsquerda(arvore);
        }

        // DUPLA ROTAÇÃO
        if(balanceamento > 1 && fatorBalanceamento(arvore.esquerda)<0) {
            arvore.esquerda = rotacaoEsquerda(arvore.esquerda);
            return rotacaoDireita(arvore);
        }if(balanceamento < -1 && fatorBalanceamento(arvore.direita)>0) {
            arvore.direita = rotacaoDireita(arvore.direita);
            return rotacaoEsquerda(arvore);
        }
        return arvore;
    }

    public NoAVL removeAtual(NoAVL noAtual) {
        NoAVL no1;
        NoAVL no2;

        if(noAtual.esquerda==null) {
            no2 = noAtual.direita;
            return no2;
        }no1 = noAtual;
        no2 = noAtual.esquerda;
        while(no2.direita!=null) {
            no1 = no2;
            no2 = no2.direita;
        }if(no1!=noAtual) {
            no1.direita = no2.esquerda;
            no2.esquerda = noAtual.esquerda;
        }no2.direita = noAtual.direita;
        return no2;
    }

    public NoAVL remove(NoAVL arvore,String palavra){
        System.out.println("\nRemovendo a palavra '" + palavra + "' da arvore...");

        if(raiz==null) {
            return arvore;
        }
        NoAVL noAnterior = null;
        NoAVL noAtual = raiz;
        while(noAtual != null) {
            if(palavra == noAtual.palavra) {
                if(noAtual==raiz) {
                    raiz = removeAtual(noAtual);
                }else {
                    if(noAnterior.direita==noAtual) {
                        noAnterior.direita = removeAtual(noAtual);
                    }else {
                        noAnterior.esquerda = removeAtual(noAtual);
                    }
                    return arvore;
                }
            }noAnterior = noAtual;
            if(palavra.compareTo(raiz.palavra)>0) {
                noAtual = noAtual.direita;
            }else {
                noAtual = noAtual.esquerda;
            }
        }

        arvore.altura = 1 + maior(altura(arvore.esquerda), altura(arvore.direita));

        //balanceamento
        int balanceamento = fatorBalanceamento(arvore);

        // rotacao simples
        if(balanceamento > 1 && fatorBalanceamento(arvore.esquerda)>=0) {
            return rotacaoDireita(arvore);
        }if(balanceamento < -1 && fatorBalanceamento(arvore.direita)<=0) {
            return rotacaoEsquerda(arvore);
        }

        // rotacao dupla
        if(balanceamento > 1 && fatorBalanceamento(arvore.esquerda)<0) {
            arvore.esquerda = rotacaoEsquerda(arvore.esquerda);
            return rotacaoDireita(arvore);
        }if(balanceamento < -1 && fatorBalanceamento(arvore.direita)>0) {
            arvore.direita = rotacaoDireita(arvore.direita);
            return rotacaoEsquerda(arvore);
        }
        return arvore;
    }

    public boolean existeElemento(NoAVL arvore,String palavra){
        System.out.println("\nBuscando a palavra '" + palavra + "' na arvore: ");

        NoAVL no = arvore;

        while(no!=null && palavra != no.palavra) {
            if(palavra.compareTo(raiz.palavra)<=0) {
                no = no.esquerda;
            }else {
                no = no.direita;
            }
        }
        if(no!=null && palavra == no.palavra) {
            System.out.println("Palavra existe!");
            return true;
        }else {
            System.out.println("Palavra não existe!");
            return false;
        }
    }

    public void preOrdem(NoAVL no){
        if(no!=null) {
            System.out.print(no.palavra + " ");
            preOrdem(no.esquerda);
            preOrdem(no.direita);
        }
    }

    public void inOrdem(NoAVL no){
        if(no != null){
            inOrdem(no.esquerda);
            System.out.print(no.palavra + " ");
            inOrdem(no.direita);
        }
    }

    public void posOrdem(NoAVL no){
        if(no != null){
            posOrdem(no.esquerda);
            posOrdem(no.direita);
            System.out.print(no.palavra + " ");
        }
    }

    public int altura(NoAVL no){
        if(no == null) {
            return -1;
        }
        int alturaE = altura(no.esquerda);
        int alturaD = altura(no.direita);
        if(alturaE < alturaD) {
            return alturaD + 1;
        }return alturaE + 1;
    }

    // rotacao da arvore para o balanceamento
    public int maior(int a, int b) {
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

        no.altura = maior(altura(no.esquerda), altura(no.direita)) + 1;
        no2.altura = maior(altura(no2.esquerda), altura(no2.direita)) + 1;

        return no2;
    }

    public NoAVL rotacaoDireita(NoAVL no) {
        NoAVL no2;
        NoAVL armNo;

        no2 = no.esquerda;
        armNo = no2.direita;
        no2.direita = no;
        no.esquerda = armNo;

        no.altura = maior(altura(no.esquerda), altura(no.direita)) + 1;
        no2.altura = maior(altura(no2.esquerda), altura(no2.direita)) + 1;

        return no2;
    }

    public int fatorBalanceamento(NoAVL no) {
        if(no == null) {
            return 0;
        }else {
            return altura(no.esquerda) - altura(no.direita);
        }
    }
}
