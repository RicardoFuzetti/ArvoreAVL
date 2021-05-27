# ArvoreAVL

#Parte I : Implementação de Árvore AVL (60% Pontos)
Neste trabalho você deve implementar uma Árvore AVL que armazenará valores inteiros.

 

A implementação deve fornecer as seguintes funções:

a) insere_elemento(ArvoreAVL, Elemento);

b) remove_elemento(ArvoreAVL,Elemento);

c) existe_elemento(NoAVL, Elemento);  // verifica se um elemento existe na árvore

d) imprime_preordem(NoAVL); // imprime os elementos da árvore em preordem

e) imprime_inordem(NoAVL); // imprime os elementos da árvore em inordem

f) imprime_posordem(NoAVL); // imprime os elementos da árvore em posordem

g) altura(NoAVL); // retorna a altura da árvore.

 

Dicas de implementação:

- implemente inicialmente procedimentos de inserção e remoção para Árvore Binária de Busca simples;

- implemente as demais funções (itens c,d,e,f e g);

- implemente as operações de inserção e remoção de uma árvore AVL

Parte II : Aplicação de Árvore AVL (40% Pontos)
Utilize a implementação de árvore AVL desenvolvida na parte I para desenvolter um contador de palavras em arquivos txt. O programa deve receber como entrada o nome de um diretório onde há arquivos ".txt" e apresentar como saída um relatório que indica quantas vezes um termo foi encontrado em cada arquivo e o total de ocorrências. Exemplo:

Entre com um termo a ser pesquisado: "computador"

Total de ocorrências de "computador": 6

Arquivo "teste.txt": 3

Arquivo "livro.txt": 2

Arquivo "exame.txt": 1
