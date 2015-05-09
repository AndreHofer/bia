class Numeral extends Variavel{
	private double valor;
	
	public void setNumeral(double v){
		this.valor = v;
	}

	public double getNumeral(){
		return this.valor;
	}	
	public void soma (double a, double b){
		this.valor= (a+ b);
	}
	
	public void subtrai (double a, double b){
		this.valor= (a - b);
	}
	
	public void multiplica (double a, double b){
		this.valor= (a * b);
	}
	
	public void divide (double a, double b){
		this.valor= (a / b);
	}
	
	public void mod (double a, double b){
		this.valor= (a % b);
	}
}
