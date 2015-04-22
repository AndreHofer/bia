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
        for(int y = 0;y < 2000 && atributos[y].getNome() != null; y++) {
                System.out.println(y+" "+atributos[y].getNome() + " " + atributos[y].getValor());

            }
    }

    //Esta  função determina se é uma declaração de variável, se é um loop, se é uma expressão aritmética.
        public void analisaLinha(String l) {
            String linhaAtual;
            linhaAtual = l;
              
    //Analisando se a linha atual se trata de uma declaração de variável
            String[] linhaAtualPedacos = linhaAtual.split(" ");
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
                }
                
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

         }else {
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

