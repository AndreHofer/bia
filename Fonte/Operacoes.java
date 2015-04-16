class Operacoes{
	private int a;
	private int b;
	
	public void setOperacoes (int x, int y){
		this.a=x;
		this.b=y;
	}
	
	public int soma (){
		return (this.a + this.b);
	}
	
	public int subtracao (){
		return (this.a - this.b);
	}
	
	public int multiplicacao (){
		return (this.a * this.b);
	}
	
	public int divisao (){
		return (this.a / this.b);
	}
	
	public int mod (){
		return (this.a % this.b);
	}
}

