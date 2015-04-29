class Numeral extends Variavel{
	private double valor;
	
	public void setNumeral(double v){
		this.valor = v;
	}

	public double getNumeral(){
		return this.valor;
	}	
	public void soma (double a){
		this.valor= (this.valor+ a);
	}
	public void subtrai (double a){
		this.valor= (this.valor - a);
	}
	public void multiplica (double a){
		this.valor= (this.valor * a);
	}
	public void divide (double a){
		this.valor= (this.valor / a);
	}
	public void mod (double a){
		this.valor= (this.valor % a);
	}	
}
