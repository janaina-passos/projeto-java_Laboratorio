public class Exame{
    //atributos
    String abrev;
    String nome;
    int qtdeDias;
    
    public Exame(String abrev, String nome, int qtdeDias){
        this.abrev = abrev;
        this.nome = nome;
        this.qtdeDias = qtdeDias;
    }

   public void mostrarExame() {        
        System.out.println("Abreviação: " + abrev);
        System.out.println("Nome exame: " + nome);
        System.out.println("Qtde dias.: " + qtdeDias);        
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Exame)) return false;

        Exame outro = (Exame) obj;
        return this.abrev.equals(outro.abrev);
    }
    
    //método -> get
    public String getAbreviacao() {
        return abrev;
    }

    public String getNome() {
        return nome;
    }

    public int getQtdeDias() {
        return qtdeDias;
    }
}