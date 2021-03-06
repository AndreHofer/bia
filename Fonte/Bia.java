        /**
         * Exemplo de interpretador.
         *
         * Esse é o ponto de partida para o interpretador da linguagem 'Blah'.
         * O único objetivo desse programa é instanciar um objeto Blah, que é
         * o interpretador da linguagem, passando para ele o conteudo do arquivo
         * a ser interpretador. Para mais informações, veja o arquivo Blah.java
         *
         * Para executar, rode o seguinte comando no terminal:
         * java Blah ./teste.blah
         *
         * Por Fernando Bevilacqua <fernando.bevilacqua@uffs.edu.br>
         */

        import java.util.Scanner;
        import java.io.*;

        class Bia {
            public static void main(String args[]) throws Exception {
                File f;
                Scanner s;
                Interpretador b;
                String linhas[] = new String[2000]; 

                // args[0] conterá o caminho para o arquivo que serah interpretado
                f = new File(args[0]);
                // Mandamos o Scanner ler a partir do arquivo.
                s = new Scanner(f);
                // Instanciamos o interpretador.
                b = new Interpretador();


            // Lemos todas as linhas do arquivo para dentro do
            // vetor "linhas".
            int i = 0;
            String juntalinhas = new String();
            juntalinhas="";
            while(s.hasNext()) {
                linhas[i] = s.nextLine();
                linhas[i]=linhas[i].replaceAll("\n","");
                if((linhas[i].indexOf(";")>=0)||(linhas[i].indexOf("[")>=0)){
    				linhas[i]=juntalinhas+linhas[i];
    				juntalinhas="";
    				i++;
    			} else{
    				juntalinhas=juntalinhas+linhas[i]+" ";
    			}
            }
            // Inicializamos o interpretador com o vetor de linhas. A partir
            // desse ponto, o objeto "b" irá interpretar o código lido do arquivo.
            b.setVolta(linhas);
            b.tratamento(linhas,0,0);

        }
    }
