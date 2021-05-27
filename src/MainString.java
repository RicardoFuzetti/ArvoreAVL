import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainString {

    // Parte 2 do Projeto
    static int cont;
    static String arqNome;

    public static void setCont(int c) {
        cont = c;
    }

    public static String getCont() {
        return String.valueOf(cont);
    }

    public static void setArqNome(String a) {
        arqNome = a;
    }

    public static String getArqNome() {
        return arqNome;
    }

    public static void ler(String listaArq, String palavra) {
        try { //
            File texto = new File(listaArq);
            Scanner leitor = new Scanner(texto);
            String p = palavra.toLowerCase();
            String ponto = p + ".";
            String virgula = p + ",";
            String exclamacao = p + "!";
            String interrogacao = p + "?";
            String pontoVirgula = p + ";";
            String doisPontos = p+ ":";
            String traco = p+ "-";
            String aspas = "'" +p+ "'";
            String abreAspas = "'" +p;
            String fechaAspas = p+ "'";
            String parenteses = "(" +p+ ")";
            String abreParenteses = "(" +p;
            String fechaParenteses = p+ ")";

            cont = 0;
            while(leitor.hasNextLine()) {
                String caractere = leitor.next().toLowerCase();
                if(p.equals(caractere) || ponto.equals(caractere) ||
                        virgula.equals(caractere) || exclamacao.equals(caractere)
                        || interrogacao.equals(caractere) || pontoVirgula.equals(caractere)
                        || doisPontos.equals(caractere) || aspas.equals(caractere)
                        || abreAspas.equals(caractere) || fechaAspas.equals(caractere)
                        || parenteses.equals(caractere) || abreParenteses.equals(caractere)
                        || fechaParenteses.equals(caractere) || traco.equals(caractere)) {
                    cont++;
                }
            }
            if(cont > 0) {
                setCont(cont);
            }String nome = texto.getName();
            setArqNome(nome);
            leitor.close();
        }catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        StringAVL arvore = new StringAVL();

        String local = "C:\\";
        String arq1 = local + "Discurso.txt";
        String arq2 = local + "FarosteCabloco.txt";
        String arq3 = local + "Musica.txt";

        ArrayList<String> listaArq = new ArrayList<String>(); // ArrayList para indexar os 3 arquivos de texto
        listaArq.add(arq1);
        listaArq.add(arq2);
        listaArq.add(arq3);
        // lista de arquivos - alterar estrutura no arovre

        System.out.println("************************************");

        System.out.println("\nAdicionando elementos na arvoreAVL...");
        arvore.raiz = arvore.insere(arvore.raiz, "ao");
        arvore.raiz = arvore.insere(arvore.raiz, "a");
        arvore.raiz = arvore.insere(arvore.raiz,  "cidade");
        arvore.raiz = arvore.insere(arvore.raiz, "eu");
        arvore.raiz = arvore.insere(arvore.raiz, "aqueles");

        System.out.println("\n--- IN ORDEM ---");
        arvore.inOrdem(arvore.raiz);
        System.out.println("\n\n--- PRE ORDEM ---");
        arvore.preOrdem(arvore.raiz);
        System.out.println("\n\n--- POS ORDEM ---");
        arvore.posOrdem(arvore.raiz);

        System.out.println("\n\n************************************");
        System.out.println("\n--- REMOÇÃO DE PALAVRAS ---");

        arvore.remove(arvore.raiz, "eu");
        System.out.println("\nPre ordem apos remoção: ");
        arvore.preOrdem(arvore.raiz);

        System.out.println("\n\n************************************");
        System.out.println("\n--- VERIFICANDO EXISTÊNCIA DAS PALAVRAS ---");

        arvore.existeElemento(arvore.raiz, "ao");
        arvore.existeElemento(arvore.raiz, "a");

        System.out.println("\n************************************");

        for(NoAVL dir = arvore.raiz; dir!=null; dir = dir.direita) {
            for(NoAVL esq = dir; esq!=null; esq = esq.esquerda) {
                StringAVL arvoreTexto = new StringAVL();
                NoAVL raizTexto = new NoAVL("palavra");
                arvoreTexto.raiz = raizTexto;
                String pAtual = esq.palavra;
                System.out.println();
                System.out.println("--- Buscando a palavra '" + pAtual + "' nos arquivos ---");
                for(int i = 0; i < 3; i++) {
                    String arq = listaArq.get(i);
                    ler(arq, esq.palavra);
                    arvoreTexto.insere(raizTexto, raizTexto.palavra);
                    System.out.println();
                    System.out.println(getArqNome() + " " + getCont() + " vez(es)!");
                }
            }
        }
    }
}
