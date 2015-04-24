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
        this.linhas = l;;
        for(int i = 0; (i < this.linhas.length) && (this.linhas[i] != null); i++) {
                 
                    String linhaAtual = this.linhas[i];
                        
                        analisaLinha(linhaAtual);
                    
                 
        }
      /*  for(int y = 0;y < 2000 && atributos[y].getNome() != null; y++) {
                System.out.println(y+" "+atributos[y].getNome() + " " + atributos[y].getValor());

            } */
    }

    //Esta  função determina se é uma declaração de variável, se é um loop, se é uma expressão aritmética.
        public void analisaLinha(String l) {
            String linhaAtual;
            linhaAtual = l;
    
    /*Início da parte que analisa se a linha se trata de uma declaração de variável*/ 
    //Quebro a string em espaços em branco ex: Int valor = 1;
    // linhaAtual[0] = "Int", linhaAtual[1] = "valor", linhaAtual[2] = "=", linhaAtual = "1"
    //e então vou analisando ela

            String[] linhaAtualPedacos = linhaAtual.split(" ");
            //System.out.println(linhaAtualPedacos[0]);
            int tamanho = linhaAtualPedacos.length - 1;
                if((linhaAtualPedacos[0].equals("int")) ) {
                    String ultimoElemento = linhaAtualPedacos[tamanho];
                    tamanho = ultimoElemento.length();
                    //System.out.println(ultimoElemento);
                    //System.out.println(ultimoElemento.length());
                    if(tamanho > 1) {
                        if(ultimoElemento.charAt(tamanho - 1) == ';') {
                            tratarDeVariavel(linhaAtual);
                        }
                    }else {
                        if(ultimoElemento.equals(";")) {
                            tratarDeVariavel(linhaAtual);
                        }
                    } 
                }else{
                    //String[] vetor_op = {'+','-','*','^','/','<','>','<=','>=','%'}
                    //int cond = 0;
                    String[] vetor_op = {"+","-","*","^","/","<",">","<=",">=","%","int"};
                    boolean cond = false;
                    for(int i = 0; i < vetor_op.length; i++) {
                        String a = vetor_op[i];
                        if(linhaAtual.indexOf(vetor_op[i])>= 0) {
                            cond = true;
                        }
                    }
                   if((linhaAtual.indexOf("=")>= 0) && (cond == false)) {
                       String[] quebrandoTudo = linhaAtual.split("=");
                            for(int r = 0; r < atributos.length && atributos[r].getNome() != null;r++) {
                                    if(atributos[r].getNome().replaceAll(" ","").equals(quebrandoTudo[0].replaceAll(" ",""))) {
                                        atributos[r].setValor(Double.parseDouble(quebrandoTudo[1].replaceAll(";","")));
                                    }
                              }


                    }


                }
              
    /*Fim da parte que analisa se a linha se trata de uma declaração de variável*/  
            }


            // Se já constatado se tratar da declaração de uma variável, eu inicio o tratamento para armazenar a mesma no vetor de variáveis
        private void tratarDeVariavel(String l) {
        String linha;
        linha = l;
        String[] linhaAtualPedacos = linha.split(" ");
        //int tam = linhaAtualPedacos.length - 1;
        String semTipo = ""; 
        for (int i = 1; i < linhaAtualPedacos.length; i++) {
            semTipo += linhaAtualPedacos[i];
        }
         if(semTipo.indexOf(",") >=0) {
              String[] semTipoM = semTipo.split(",");
              for(int u = 0; u < semTipoM.length; u++) {
                   // System.out.println(semTipoM[u]);
                    if(semTipoM[u].indexOf("=") >= 0) {
                            String[] quebrandoTudo = semTipoM[u].split("=");
                            double n = Double.parseDouble(quebrandoTudo[1].replaceAll(";",""));
                            int i = 0;
                              boolean status = false;
                             while(status != true) {
                                  if(atributos[i].getNome() == null && status != true) {
                                    atributos[i].setNome(quebrandoTudo[0]);
                                   atributos[i].setValor(n); 
                                     status = true;
                                     break;
                                     }
                                        i++;
                                        }
                                  }else{
                                            
                                        int y = 0;
                                        boolean status = false;
                                        while(status != true) {
                                           if(atributos[y].getNome() == null && status != true) {
                                                atributos[y].setNome(semTipoM[u].replaceAll(";",""));
                                                atributos[y].setValor(0);
                                                status = true;
                                               break;
                                         }
                                         y++;
                                     }
                                }
                    }

         }else {
		if(semTipo.indexOf("=") >=0) {
			String[] separandoIgual = semTipo.split("=");
            //System.out.println(separandoIgual[1]);
            double  n =  Double.parseDouble(separandoIgual[1].replaceAll(";",""));
            
            int i = 0;
            boolean status = false;
            while(status != true) {
                if(atributos[i].getNome() == null && status != true) {
                    atributos[i].setNome(separandoIgual[0]);
                    atributos[i].setValor(n); 
                     status = true;
                    break;
                }
               i++;
            }
		}else{
				//System.out.println(semTipo);
                int y = 0;
                boolean status = false;
                while(status != true) {
                    if(atributos[y].getNome() == null && status != true) {
                        atributos[y].setNome(semTipo.replaceAll(";",""));
                        atributos[y].setValor(0);
                        status = true;
                        break;
                    }
                    y++;
                }

            }
        }

    }
}


  

    
    /*public void interpreta(String l[]) {
        this.linhas = l;

        for(int i = 0; i < this.linhas.length; i++) {
            if(this.linhas[i] != null) {
                // TODO: interpretar a linha
                System.out.println("Linha " + (i + 1) + ": " + this.linhas[i]);
            }
        }
    }*/

