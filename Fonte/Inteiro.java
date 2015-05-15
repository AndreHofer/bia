/*******************************************************************************
Nome: Inteiro.java
Autor: Marcelo Acordi - marcelopancotte@gmail.com
         André Hofer - andreluizhofer@gmail.com
Decrição: Esta classe é reponsável por fazer as tarefas em relação a variável inteiro.
*******************************************************************************/
class Inteiro extends Variavel{
	private int valor;
	
	public void setInteiro(int v){
		this.valor = v;
	}
	
	public int getInteiro(){
		return this.valor;
	}	
	
	public void soma (int a, int b){
		this.valor= (a+ b);
	}
	
	public void subtrai (int a, int b){
		this.valor= (a - b);
	}
	
	public void multiplica (int a, int b){
		this.valor= (a * b);
	}
	
	public void divide (int a, int b){
		this.valor= (a / b);
	}
	
	public void mod (int a, int b){
		this.valor= (a % b);
	}
}
