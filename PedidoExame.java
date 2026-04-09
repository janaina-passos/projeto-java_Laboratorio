import java.util.*;
import java.time.*;

public class PedidoExame{
    //atributos
    String cpf;
    String nome; 
    LocalDate dataRealizacao;
    LocalDate dataLiberacao;
    ArrayList<Exame> examesSolicitados; 
    
    //construtor
    public PedidoExame(String cpf, String nome){
        this.cpf = cpf;
        this.nome = nome;
        this.dataRealizacao = dataRealizacao;
        this.dataRealizacao = LocalDate.now();
        
        examesSolicitados = new ArrayList<>();
    }

    public void adicionarExame(Exame ex){
        examesSolicitados.add(ex);
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public LocalDate getDataLiberacao() {
        return dataLiberacao;
    }

    public ArrayList<Exame> getExamesSolicitados() {
        return examesSolicitados;
    }

    public void calcularDataLiberacao() {
        int maior = 0;

        for (Exame e : examesSolicitados) {
            if (e.getQtdeDias() > maior) {
                maior = e.getQtdeDias();
            }
        }

        dataLiberacao = dataRealizacao.plusDays(maior);
    }
    
}