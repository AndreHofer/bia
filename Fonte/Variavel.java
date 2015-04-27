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
		String variavelDeclarada = linhaQuebrada[1];
		if(!(linhaAtual.indexOf(",") >= 0)) {
			if(!(linhaAtual.indexOf("=") >= 0)) {
				if(variavelDeclarada.indexOf(";") >= 0) {
					variavelDeclarada = variavelDeclarada.substring(0,variavelDeclarada.indexOf(";"));
					retornaVariavel.setNome(variavelDeclarada);
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
		String[] nomeValor;
		nomeValor = linha.split("=");
		nomeValor[1] = nomeValor[1].replaceAll("[;\"]","");
		if(tipo.equals("inteiro")) {
			 Inteiro estado = new Inteiro();
			estado.setNome(nomeValor[0]);
			estado.setInteiro(Integer.valueOf(nomeValor[1]).intValue());
			estadoAtual = estado;
		}else if(tipo.equals("double")) {  
			 Numeral estado = new Numeral();
			estado.setNome(nomeValor[0]);
			estado.setNumeral(Double.valueOf(nomeValor[1]).doubleValue());
			estadoAtual = estado;
		}else if(tipo.equals("string")) {
			 Escrita estado = new Escrita();
			estado.setNome(nomeValor[0]);
			estado.setEscrita(nomeValor[1]);
			estadoAtual = estado;
		}
		return estadoAtual;
	}

	
}
