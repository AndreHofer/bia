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
    		/*Limitar áre if */
    		if((linhaAtual.indexOf("para")>=0) && (linhaAtual.indexOf(":")>=0)) {
    			
    			String quebrar[] = linhaAtual.split(":");
    			quebrar = quebrar[1].split(";");
    			
    			analisaLinha(quebrar[0]);
    			 String[] quebrar2 = quebrar[0].split("=");
    			  quebrar2 = quebrar2[0].split(" ");
    			boolean estado = true;
    			//System.out.println("var e: "+quebrar2[1]);
    			int y = i + 1;
    				boolean status = false;
    				while(status == false) {
    					if(this.linhas[y].indexOf("];")>=0) {
    						break;
    					}
    					y++;
    				}
    				int andar = (y - i) - 1;
    				
    			do{
    				
    					analisaLinha(quebrar2[1]+"= "+quebrar2[1]+"+ 1" );
    					Fluxo analiseFluxo = new Fluxo();
    				analiseFluxo.analiseFluxo(" :"+quebrar[1]+": ");

    		for(int e = 0; (e < analiseFluxo.dados.length) && (analiseFluxo.dados[e][0] != null);e++) {
					analiseFluxo.dados[e][1] = buscarValorVariavel(analiseFluxo.dados[e][0]);
    				}
    				estado = analiseFluxo.checarCondicao(" : "+quebrar[1]+" : ");
    				if(estado == false) {
    					break;
    				}
    				for(int and = i+1;and < (andar+i+1)  ;and++) {
			  		  //System.out.println(i+"--->>"+this.linhas[and]);
    					analisaLinha(this.linhas[and]);
    					}
    				
    				
    			}while(estado);
    		}else if((linhaAtual.indexOf("se") >=0) && (linhaAtual.indexOf(":") >= 0) ) {
    			Fluxo analiseFluxo = new Fluxo();
    			analiseFluxo.analiseFluxo(linhaAtual);
    			for(int e = 0; (e < analiseFluxo.dados.length) && (analiseFluxo.dados[e][0] != null);e++) {
					analiseFluxo.dados[e][1] = buscarValorVariavel(analiseFluxo.dados[e][0]);
    			}
    			if(analiseFluxo.checarCondicao(linhaAtual)){
    				int y = i + 1;
    				int andar = 0;
    				boolean status = false;
    				while(status == false) {
    					if(this.linhas[y].indexOf("];")>=0) {
    						break;
    					}
    					y++;
    				}
    				andar = (y - i) - 1;

    				for(int and = i+1;and < y  ;and++) {
			  		//System.out.println("--->>"+this.linhas[and]);
    					analisaLinha(this.linhas[and]);
    				}
    				i = y;	
    			}else{
    				int y = i + 1;
    				int andar = 0;
    				boolean status = false;
    				while(status == false) {
    					if(this.linhas[y].indexOf("];")>=0) {
    						break;
    					}
    					y++;
    				}
    				i = y;
    			}
    		}else{
    			analisaLinha(linhaAtual);
    		}
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
    	String[] comenta = new String[2];
    	String linhaAtual;
    	linhaAtual = l;
    	int pos = 0 ;
    	/* Aqui eu sei se a linha é uma declaração de variável */
    	if (linhaAtual.indexOf("#")>=0){
    		comenta=linhaAtual.split("#+");
    		linhaAtual=comenta[0];
    	}
    	/* Inserindo tipo para tratar as expressões */

    	if(linhaAtual.indexOf("=") >= 0) {
    		if(!(linhaAtual.indexOf("int") >= 0) && !(linhaAtual.indexOf("double") >= 0) &&
    			!(linhaAtual.indexOf("string") >= 0)) {
    			if((linhaAtual.indexOf("+")>=0) || (linhaAtual.indexOf("-")>=0) ||
    				(linhaAtual.indexOf("*")>=0) || (linhaAtual.indexOf("/")>=0)
    				|| (linhaAtual.indexOf("%")>=0)) {

    				linhaAtual = linhaAtual.replaceAll(";","");
    			String[] dividido = linhaAtual.split("=");
                   String var = dividido[0].replaceAll(" ","");
    			String tipo_var = getTipoVariavel(var);
    			String[] recorte = null;
    			String op = "";
    			if(dividido[1].indexOf("+") >= 0) {
    				recorte = dividido[1].split("\\+");
    				op = "+";
    			}else if(dividido[1].indexOf("-") >= 0) {
    				recorte = dividido[1].split("-");
    				op = "-";
    			}else if(dividido[1].indexOf("*") >= 0) {
    				recorte = dividido[1].split("\\*");
    				op = "*";
    			}else if(dividido[1].indexOf("/") >= 0) {
    				recorte = dividido[1].split("/");
    				op = "/";
    			}else if(dividido[1].indexOf("%") >= 0) {
    				recorte = dividido[1].split("%");
    				op = "%";
    			}
    			recorte[0] = buscarValorVariavel(recorte[0]);
    			recorte[1] = buscarValorVariavel(recorte[1]);
    			linhaAtual = tipo_var + " " + dividido[0] +" = "+recorte[0]+op+recorte[1];
    			

    		}else {
    			if((linhaAtual.indexOf("(escreve)")>=0)) {
    				linhaAtual = linhaAtual.replaceAll(";","");	
    				String[] dividido = linhaAtual.split("=");
    				String tipo_var = getTipoVariavel(dividido[0]);
    				linhaAtual = tipo_var+" " + linhaAtual;
    			}else{
    				
    				linhaAtual = linhaAtual.replaceAll(";","");
    				String[] dividido = linhaAtual.split("=");
    				String tipo_var = getTipoVariavel(dividido[0]);
    				linhaAtual = tipo_var + " " + dividido[0] + "="+buscarValorVariavel(dividido[1]);
    			}
    		}                            
    	}
    }


    /*Fim */
    /*Função de imprimir*/
    if(linhaAtual.indexOf("imprime") >= 0){
    	imprimir( linhaAtual);
    }else if(( (linhaAtual.indexOf("int") >= 0)) || ( linhaAtual.indexOf("double") >= 0)  || 
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
		/*Fim da parte que analisa se a linha se trata de uma declaração de variável*/ 

	}

	public void inserirVariavel(Variavel var) {
		// -1 é só para inicializar
		int caminho = -1;
		boolean status = false;
		for(int y = 0; y < 2000; y++) {
			if(atributos[y].getNome() != null) {
				if(atributos[y].getNome().replaceAll(" ","").equals(var.getNome().replaceAll(" ",""))) {
					caminho = y;
				}
			}
		}
		if(caminho == -1) {
			for(int i = 0;status == false; i++) {
				// Descobrindo uma variável do vetor que tenha valor null e checando se o status é false
				if(atributos[i].getNome() == null && status == false) {
					//salvo o indíce correspondente que está disponível para inserção
					caminho = i;
					status = true;
					//mudo o status para true, indicando que sei onde inserir
				}
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
	public String getTipoVariavel(String var) {
		String v = var.replaceAll(" ","");
		boolean status = false;

		for(int i = 0; status == false; i++ ) {
			if(atributos[i].getNome() != null) {

				if(atributos[i].getNome().replaceAll(" ","").equals(v.replaceAll(" ",""))) {
					if(atributos[i] instanceof Inteiro) {
						v = "int";
					}else if(atributos[i] instanceof Escrita) {
						v = "string";
					}else if(atributos[i] instanceof Numeral) {
						v = "double";
					}
					status = true;
					break;
				}
			}
			if(i == 1999) {
				status = true;
				break;
			}
		}

		return v;
	}

	public  String buscarValorVariavel(String s) {
		String st = s.replaceAll(" ","");
		String busca = s.replaceAll(" ","");
		boolean status = false;
		st = "0";
		for(int i = 0; status == false; i++ ) {
			if(atributos[i].getNome() != null) {

				if(atributos[i].getNome().replaceAll(" ","").equals(busca)) {
					if(atributos[i] instanceof Inteiro) {
						Inteiro n = (Inteiro) atributos[i];
						st = ""+n.getInteiro()+"";
					}else if(atributos[i] instanceof Escrita) {
						Escrita n = (Escrita) atributos[i];
						st = ""+n.getEscrita()+"";
					}else if(atributos[i] instanceof Numeral) {
						Numeral n = (Numeral) atributos[i];
						st = ""+n.getNumeral()+"";
					}
					status = true;
					break;
				}
			}

			if(i == 1999) {
				status = true;
				break;
			}
		}
		if(st == "0") {

			return s;
		}
		return st;
	}
	public void imprimir(String linhaAtual){		
		int n= linhaAtual.length() - linhaAtual.replaceAll("\\|","").length();
		String[] impressao = new String[n];
		String[] parte = new String[2];
		parte=linhaAtual.split ("\\(");
			impressao= parte[1].split ("\\|");
			impressao[n] = impressao[n].replaceAll("\\);",""); 
		//System.out.println(n);
			for(int a=0;a<=n;a++){
				if(impressao[a].indexOf("'") >=0 ){
					int m= impressao[a].length() - impressao[a].replaceAll("'","").length();
					String[] imprime = new String[m];
					imprime= impressao[a].split ("'");
					if(m>=2){
						System.out.print(imprime[1]);
					}
				}else{
					impressao[a]=impressao[a].replaceAll(" ","");
					for(int y = 0;((y < 2000) &&( atributos[y].getNome()!= null)); y++) {
						if(atributos[y].getNome().equals(impressao[a])){
							if(atributos[y] instanceof Inteiro) {
								Inteiro v = (Inteiro) atributos[y];
								System.out.print(v.getInteiro());   
							}
							if(atributos[y] instanceof Escrita) {
								Escrita v = (Escrita) atributos[y];
								System.out.print(v.getEscrita());   
							}
							if(atributos[y] instanceof Numeral) {
								Numeral v = (Numeral) atributos[y];
								System.out.print(v.getNumeral());   
							}
						}
					}
				}
			}
			System.out.print("\n");
		}

	}
