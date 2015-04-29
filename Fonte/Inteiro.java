class Inteiro extends Variavel{
	private int valor;
	
	public void setInteiro(int v){
		this.valor = v;
	}

	public int getInteiro(){
		return this.valor;
	}	
	public void soma (int a){
		this.valor= (this.valor+ a);
	}
	public void subtrai (int a){
		this.valor= (this.valor - a);
	}
	public void multiplica (int a){
		this.valor= (this.valor * a);
	}
	public void divide (int a){
		this.valor= (this.valor / a);
	}
	public void mod (int a){
		this.valor= (this.valor % a);
	}
}

