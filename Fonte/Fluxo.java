class Fluxo {
	public String[][] dados = new String[50][2];
	private String[] vetLogicos = {">",">=","<","<=","==","!="};
	private int ind = -1; 
	
	
	public void analiseFluxo (String l) {
		String[] linha = l.split(":");
		String[] quebrado = null;
		
		for(int x = 0; x < vetLogicos.length; x++) {
			
			if(linha[1].indexOf(vetLogicos[x])>=0) {
				quebrado = linha[1].split(vetLogicos[x]);
				ind = x;
			}
		}
		for(int i = 0; i < quebrado.length;i++) {
			inserirVariaveis(quebrado[i]);
		}
		
	}

	public void inserirVariaveis(String s) {
		String var = s;
		for(int y = 0; y < dados.length; y++ ) {
			if(dados[y][0] == null) {
				
				dados[y][0] = s;
				dados[y][1] = s; 
				break;
			}
		}
		
	}

	public boolean checarCondicao(String s) {
		String[] quebrar = s.split(":");
		String linhaSalva = quebrar[1];
		String[] quebrado = null;
		if(linhaSalva.indexOf("!=")>=0) {
			quebrado = linhaSalva.split("!=");
			quebrado[0] = atualizarValor(quebrado[0]); 
			quebrado[1] = atualizarValor(quebrado[1]);
			return ( (Double.valueOf(quebrado[0]).doubleValue()) !=
				(Double.valueOf(quebrado[1]).doubleValue()) );

		}else if(linhaSalva.indexOf(">=")>=0) {
			quebrado = linhaSalva.split(">=");
			quebrado[0] = atualizarValor(quebrado[0]); 
			quebrado[1] = atualizarValor(quebrado[1]);
			
			return ( (Double.valueOf(quebrado[0]).doubleValue()) >=
				(Double.valueOf(quebrado[1]).doubleValue()) );

		}else if(linhaSalva.indexOf("==")>=0) {
			quebrado = linhaSalva.split("==");
			quebrado[0] = atualizarValor(quebrado[0]); 
			quebrado[1] = atualizarValor(quebrado[1]);
			return ( (Double.valueOf(quebrado[0]).doubleValue()) ==
				(Double.valueOf(quebrado[1]).doubleValue()) );

		}else if(linhaSalva.indexOf("<=")>=0) {
			quebrado = linhaSalva.split("<=");
			quebrado[0] = atualizarValor(quebrado[0]); 
			quebrado[1] = atualizarValor(quebrado[1]);
			return ( (Double.valueOf(quebrado[0]).doubleValue()) <=
				(Double.valueOf(quebrado[1]).doubleValue()) );

		}else if(linhaSalva.indexOf("<")>=0) {
			quebrado = linhaSalva.split("<");
			quebrado[0] = atualizarValor(quebrado[0]); 
			quebrado[1] = atualizarValor(quebrado[1]);
			return ( (Double.valueOf(quebrado[0]).doubleValue()) <
				(Double.valueOf(quebrado[1]).doubleValue()) );

		}else if(linhaSalva.indexOf(">")>=0) {
			quebrado = linhaSalva.split(">");
			quebrado[0] = atualizarValor(quebrado[0]); 
			quebrado[1] = atualizarValor(quebrado[1]);
			return ( (Double.valueOf(quebrado[0]).doubleValue()) >
				(Double.valueOf(quebrado[1]).doubleValue()) );
		}

		return false;
	}

	public String atualizarValor(String s) {
		String valor = s.replaceAll(" ","");
		for(int i = 0; i < dados.length && dados[i][0] != null;i++) {
			
			dados[i][0] = dados[i][0].replaceAll(" ","");
			if(dados[i][0].equals(valor)) {
				valor = dados[i][1];
			}	
		}
		return valor;
	}






}