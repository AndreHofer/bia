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
        // Somente para teste
        for(int y = 0;y < 2000 && atributos[y].getNome() != null; y++) {
                if(atributos[y] instanceof Inteiro) {
                    Inteiro n = (Inteiro) atributos[y];
                    System.out.println(y+" "+n.getNome()+ " "+ n.getInteiro());   
                }
                if(atributos[y] instanceof Escrita) {
                    Escrita n = (Escrita) atributos[y];
                    System.out.println(y+" "+n.getNome()+ " "+ n.getEscrita());   
                }
                if(atributos[y] instanceof Numeral) {
                    Numeral n = (Numeral) atributos[y];
                    System.out.println(y+" "+n.getNome()+ " "+ n.getNumeral());   
                }
                

            } 
    }

    
    public void analisaLinha(String l) {
            String linhaAtual;
            linhaAtual = l;
            int pos = 0 ;
            /* Aqui eu sei se a linha é uma declaração de variável                      */
            if(( (linhaAtual.indexOf("int") >= 0)) || ( linhaAtual.indexOf("double") >= 0)  || 
                (linhaAtual.indexOf("string") >= 0)) {
					// Se tem vírgula preciso quebrar em partes e mandar para análise igual
                if(l.indexOf(",") >= 0) {
                    String tipo = "";
                    String[] vetorTamanho = l.split(",");
                    int tamanhoVetor = vetorTamanho.length;
                    if(linhaAtual.indexOf("int") >= 0) {
                        tipo = "int";
                        //Removo espaços vazios, \\s ; e \"
                        linhaAtual = linhaAtual.replaceAll("[\\s;\"]",""); 
                        linhaAtual = linhaAtual.replaceAll("int","");
                    }else if(linhaAtual.indexOf("string") >=0 ) {
                        tipo = "string";
                        linhaAtual = linhaAtual.replaceAll("[\\s;\"]","");
                        linhaAtual = linhaAtual.replaceAll("string","");
                    }else if(linhaAtual.indexOf("double") >=0 ) {
                        tipo = "double";
                        linhaAtual = linhaAtual.replaceAll("[\\s;\"]","");
                        linhaAtual = linhaAtual.replaceAll("double","");
                    }
                    vetorTamanho = linhaAtual.split(",");
                    Variavel var = new Variavel();
					/*Eu tenho todas as variáveis quebradas em vetor tamanho, e o seu repsectivo tipo na variável tipo 
					 * então mando uma por vez ser tratada e em seguida chamo a função de inserção no vetor de variáveis 
					 * do interpretador */
                   for(int i = 0; i < tamanhoVetor; i++) {
                        var = var.tratarDeclaracaoVariavel(tipo+vetorTamanho[i]);
                        this.inserirVariavel(var);
                    } 
                    //System.out.println(tipo+vetorTamanho[0]);


                }else {
                  Variavel var = new Variavel();
                  var = var.tratarDeclaracaoVariavel(linhaAtual);
                  this.inserirVariavel(var);
                }
            }
            /*                                                                          */
            /*Fim da parte que analisa se a linha se trata de uma declaração de variável*/ 


            
    }

    public void inserirVariavel(Variavel var) {
		// -1 é só para inicializar
        int caminho = -1;
        boolean status = false;
        for(int i = 0;status == false; i++) {
           // Descobrindo uma variável do vetor que tenha valor null e checando se o status é false
            if(atributos[i].getNome() == null && status == false) {
				//salvo o indíce correspondente que está disponível para inserção
                caminho = i;
                status = true;
                //mudo o status para true, indicando que sei onde inserir
            }
        }
        if(caminho != -1) {
			//analiso o tipo da variável que recebi e instancio ela respectivamente no vetor de variáveis e atribuo a variável na posição escolhida 
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
