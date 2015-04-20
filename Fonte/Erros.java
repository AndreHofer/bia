class Erro {
	 private String linhas[];

    public void inicioFim(String l[]) {
        //System.out.println("Entrou");
        this.linhas  = l;
        for(int i = 0; (i < this.linhas.length) && (this.linhas[i] != null); i++) {
           for (int y = 0; y < this.linhas[i].length(); y++){
                if(this.linhas[i].charAt(y) == '#') {
                    if(y != ((this.linhas[i].length()) - 1)) {
                        if(this.linhas[i].charAt(y+1) == '>') {
                            System.out.println("Parabéns você começou seu arquivo de forma correta!");
                        }    
                    }
                }
            }

        }
    }
}