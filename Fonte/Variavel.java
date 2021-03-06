/*******************************************************************************
Nome: Variável.java
Autor: Marcelo Acordi - marcelopancotte@gmail.com
         André Hofer - andreluizhofer@gmail.com
Decrição: Esta classe é reponsável por quebrar e analisar o que for relacionado as variáveis.
*******************************************************************************/
	import java.util.Scanner;
	class Variavel{
		private String nome;
		
		public Variavel (){
			this.nome= null;
		}
		
		public void setNome(String n){
			this.nome = n;
		}

		public String getNome(){
			return this.nome;
		}
		
		/* Função tratarDeclaracaoVariavel nela eu quebro a linha e descubro os valores 
		e retorno uma variável pronta com os dados */
		public Variavel tratarDeclaracaoVariavel(String l) {
			
			Variavel retornaVariavel = new Variavel();
			String nome = "";
			String linhaAtual = l;
			String[] linhaQuebrada = null;
			if(linhaAtual.indexOf("int") >= 0) {
				retornaVariavel = new Inteiro();
				linhaAtual = linhaAtual.replaceAll(" ","");
				linhaAtual = linhaAtual.replaceAll("int","int ");
				linhaQuebrada = linhaAtual.split(" ");
			}else if(linhaAtual.indexOf("string") >= 0) {
				retornaVariavel = new Escrita();
				linhaAtual = linhaAtual.replaceAll(" ","");
				linhaAtual = linhaAtual.replaceAll("string","string ");
				linhaQuebrada = linhaAtual.split(" ");
			}else if(linhaAtual.indexOf("double") >= 0) {
				retornaVariavel = new Numeral();
				linhaAtual = linhaAtual.replaceAll(" ","");
				linhaAtual = linhaAtual.replaceAll("double","double ");
				linhaQuebrada = linhaAtual.split(" ");
			}
			// parte separada da declaração, int  =  linhaQuebrada1>>a + b
			String variavelDeclarada = linhaQuebrada[1];

			if(!(linhaAtual.indexOf(",") >= 0)) {
				if(!(linhaAtual.indexOf("=") >= 0)) {
					if(variavelDeclarada.indexOf(";") >= 0) {
						variavelDeclarada = variavelDeclarada.replaceAll(";","");
					}

					if (retornaVariavel instanceof Inteiro) {
						retornaVariavel = fazSet(variavelDeclarada,"inteiro");
					}else if(retornaVariavel instanceof Numeral) {
						retornaVariavel = fazSet(variavelDeclarada,"double");
					}else if(retornaVariavel instanceof Escrita) {
						retornaVariavel = fazSet(variavelDeclarada,"string");
					}
				}else{
					if (retornaVariavel instanceof Inteiro) {
						retornaVariavel = fazSet(variavelDeclarada,"inteiro");
					}else if(retornaVariavel instanceof Numeral) {
						retornaVariavel = fazSet(variavelDeclarada,"double");
					}else if(retornaVariavel instanceof Escrita) {
						retornaVariavel = fazSet(variavelDeclarada,"string");
					}
				}
			}
			return retornaVariavel;
		}

		private Variavel fazSet(String linha,String tipo) {
			Variavel estadoAtual = new Variavel();
			String[] nomeValor = new String[2];
			String[] opera = new String[2];
			if (linha.indexOf("=") >= 0) {
				nomeValor = linha.split("=");
				nomeValor[1] = nomeValor[1].replaceAll("[;\"]","");
			}else {
				nomeValor[0] = linha;
				if(tipo.equals("inteiro")) {
					nomeValor[1] = "0"; 
				}else if(tipo.equals("double")) {
					nomeValor[1] = "0.0"; 
				}else{
					nomeValor[1] = "";
				}
			}
			/*Aqui ele faz a leitura do teclado, no caso de uma declaração de variavel*/
			if(nomeValor[1].equals("(escreve)")){
				nomeValor[1]= escrever(nomeValor[1]);
			}
			if(tipo.equals("inteiro")) {
				Inteiro estado = new Inteiro();
				estado.setNome(nomeValor[0]);
				if ((nomeValor[1].indexOf("+")>= 0)||(nomeValor[1].indexOf("-")>= 0)||(nomeValor[1].indexOf("*")>= 0)||(nomeValor[1].indexOf("/")>= 0)||(nomeValor[1].indexOf("%")>= 0)) {
					if (nomeValor[1].indexOf("+")>= 0){
						opera=nomeValor[1].split("\\+");
						estado.soma((Integer.valueOf(opera[0]).intValue()),Integer.valueOf(opera[1]).intValue());
						
					} else if(nomeValor[1].indexOf("-")>= 0){
						opera=nomeValor[1].split("-");
						estado.subtrai((Integer.valueOf(opera[0]).intValue()),Integer.valueOf(opera[1]).intValue());
					}else if(nomeValor[1].indexOf("*")>= 0){
						opera=nomeValor[1].split("\\*");
						estado.multiplica((Integer.valueOf(opera[0]).intValue()),Integer.valueOf(opera[1]).intValue());
					}else if(nomeValor[1].indexOf("/")>= 0){
						opera=nomeValor[1].split("/");
						estado.divide((Integer.valueOf(opera[0]).intValue()),Integer.valueOf(opera[1]).intValue());
					}else if(nomeValor[1].indexOf("%")>= 0){
						opera=nomeValor[1].split("%");
						estado.mod((Integer.valueOf(opera[0]).intValue()),Integer.valueOf(opera[1]).intValue());
					}
				}else{
					estado.setInteiro(Integer.valueOf(nomeValor[1]).intValue());
				}
				estadoAtual = estado;
			}else if(tipo.equals("double")) {  
				Numeral estado = new Numeral();
				estado.setNome(nomeValor[0]);
				if ((nomeValor[1].indexOf("+")>= 0)||(nomeValor[1].indexOf("-")>= 0)||(nomeValor[1].indexOf("*")>= 0)||(nomeValor[1].indexOf("/")>= 0)||(nomeValor[1].indexOf("%")>= 0)) {
					if (nomeValor[1].indexOf("+")>= 0){
						opera=nomeValor[1].split("\\+");
						estado.soma(Double.valueOf(opera[0]).doubleValue(),Double.valueOf(opera[1]).doubleValue());
					} else if(nomeValor[1].indexOf("-")>= 0){
						opera=nomeValor[1].split("-");
						estado.subtrai(Double.valueOf(opera[0]).doubleValue(),Double.valueOf(opera[1]).doubleValue());
					}else if(nomeValor[1].indexOf("*")>= 0){
						opera=nomeValor[1].split("\\*");
						estado.multiplica(Double.valueOf(opera[0]).doubleValue(),Double.valueOf(opera[1]).doubleValue());
					}else if(nomeValor[1].indexOf("/")>= 0){
						opera=nomeValor[1].split("/");
						estado.divide(Double.valueOf(opera[0]).doubleValue(),Double.valueOf(opera[1]).doubleValue());
					}else if(nomeValor[1].indexOf("%")>= 0){
						opera=nomeValor[1].split("%");
						estado.mod(Double.valueOf(opera[0]).doubleValue(),Double.valueOf(opera[1]).doubleValue());
					}
				}else{
					estado.setNumeral(Double.valueOf(nomeValor[1]).doubleValue());
				}
				estadoAtual = estado;
			}else if(tipo.equals("string")) {
				Escrita estado = new Escrita();
				estado.setNome(nomeValor[0]);
				estado.setEscrita(nomeValor[1]);
				estadoAtual = estado;
			}
			
			return estadoAtual;
		}

		public String escrever(String nomeValor){
			Scanner scanner = new Scanner(System.in);
			System.out.println("Digite:");//teste
			nomeValor = scanner.nextLine();
		
			return nomeValor;
		}

	}
