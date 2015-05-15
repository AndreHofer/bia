/*******************************************************************************
Nome: Escrita.java
Autor: Marcelo Acordi - marcelopancotte@gmail.com
         André Hofer
Decrição: Esta classe é reponsável criar espaço da escrita.
*******************************************************************************/
class Escrita extends Variavel{
	private String conteudo;
	
	public void setEscrita(String c){
		this.conteudo = c;
	}

	public String getEscrita(){
		return this.conteudo;
	}	
}
