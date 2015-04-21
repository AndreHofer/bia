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
    

   

    public void tratamento(String l[]) {
        this.linhas = l;;
        for(int i = 0; (i < this.linhas.length) && (this.linhas[i] != null); i++) {
                 
                    String linhaAtual = this.linhas[i];
                        if(i == 0) {
                        analisaLinha(linhaAtual);
                    }
                 
        }
    }

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
            
            /*System.out.println(atributos[0].getNome());
           
            Boolean parar = false;
            int i = 0;
            while(parar != true) {
                if(atributos[i].getNome() == null && !(atributos[i].getNome().equals(separandoIgual[0])) ) {
                    atributos[i].setNome(separandoIgual[0]);
                    atributos[i].setValor(n);
                    parar = true;
                    break;
                }
                i++;
            }*/
            
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

