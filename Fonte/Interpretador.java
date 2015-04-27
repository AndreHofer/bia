/**
 * Exemplo de interpretador.
 *
 * Esse código é um exemplo de interpretador para a linguagem 'Blah'. Esse programa
 * não faz qualquer interpretação, ele apenas recebe o conteúdo de um arquivo que foi
 * passado pela linha de comando.
 *
 * Por Fernando Bevilacqua <fernando.bevilacqua@uffs.edu.br>
 */

class Interpretador {
    private String linhas[];
    /*A ideia é criar um vetor de variáveis que fique
    armazenando as mesmas durante a execução do interpretador*/
    private Variavel[] atributos = new Variavel[2000];

    /*Criando o construtor */
    public Interpretador() {
        //Instanciando todas as variáveis do interpretador.
        for(int i = 0; i < 2000; i++) {
                atributos[i] = new Variavel();
        }
    }

   
    //Varrendo linha por linha, e chamando a função analisaLinha, para determinar do que se trata.
    public void tratamento(String l[]) {
        this.linhas = l;
        for(int i = 0; (i < this.linhas.length) && (this.linhas[i] != null); i++) {
                 String linhaAtual = this.linhas[i];
                 analisaLinha(linhaAtual);
        }
        //Imprimindo o conteúdo do vetor de variáveis
       for(int y = 0;y < 2000 && atributos[y].getNome() != null; y++) {
                if(atributos[y] instanceof Inteiro) {
                    Inteiro n = (Inteiro) atributos[y];
                    System.out.println(y+" "+n.getNome()+ " "+ n.getInteiro());   
                }
                

            } 
    }

    
    public void analisaLinha(String l) {
            String linhaAtual;
            linhaAtual = l;
            
            /* Aqui eu sei se a linha é uma declaração de variável                      */
            /*                                                                          */
            if((linhaAtual.indexOf("int") >= 0) || (linhaAtual.indexOf("double") >= 0)  || 
                (linhaAtual.indexOf("string") >= 0)) {
                if(l.indexOf(",") >= 0) {
                    String[] vetorTamanho = l.split(",");
                    int tamanhoVetor = vetorTamanho.length;
                    Variavel[] vetor_var = new Variavel[tamanhoVetor];


                }else {
                  Variavel var = new Variavel();
                  var = var.tratarDeclaracaoVariavel(linhaAtual);
                  //System.out.println(var.getNome());
                  this.inserirVariavel(var);
                }
            }
            /*                                                                          */
            /*Fim da parte que analisa se a linha se trata de uma declaração de variável*/ 


            
    }

    public void inserirVariavel(Variavel var) {
        int caminho = -1;
        boolean status = false;
        for(int i = 0;status == false; i++) {
           // System.out.println(atributos[i].getNome());
            if(atributos[i].getNome() == null && status == false) {
                caminho = i;
                status = true;
            }
        }
        if(caminho != -1) {
        if(var instanceof Inteiro) {
            Inteiro n = (Inteiro) var;
            atributos[caminho] = new Inteiro();
            atributos[caminho] = n;
            

        }else if(var instanceof Escrita) {
            Escrita n = (Escrita) var;
            atributos[caminho] = new Escrita();
            atributos[caminho] = n;
        }else if(var instanceof Numeral) {
            Numeral n = (Numeral) var;
            atributos[caminho] = new Numeral();
            atributos[caminho] = n;
            }
        }
    }



    
}


