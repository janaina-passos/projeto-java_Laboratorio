import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Laboratorio{
    Vetor <Exame> ExamesDisponiveis;
    Vetor <PedidoExame> pedidosDoDia;
    boolean dadosCarregados = false;
    boolean finalizado = false;

    Scanner sc = new Scanner(System.in);

    public Laboratorio(){
        ExamesDisponiveis = new Vetor <Exame> (200);
        pedidosDoDia = new Vetor <PedidoExame> (100);
    }


    public void executarMenu(){
        int opcao;
    	boolean continua = true;
    	    
    	while(continua){
    	    System.out.println("- - - MENU - - -");
    	    System.out.println("1.Carregar dados de exame");
    	    System.out.println("2.Novo paciente");
    	    System.out.println("3.Consultar paciente");
    	    System.out.println("4.Finalização dos atendimentos");
    	    System.out.println("5.Estatísticas");
    	    System.out.println("6.Sair");
    	    System.out.println("\nEscolha uma opção: ");
    	    opcao = sc.nextInt();
    	    sc.nextLine();
    	        
    	    switch(opcao){
    	        case 1:
                    if(finalizado == true){
                        System.out.println("Acesso negado! O dia já foi finalizado");
                    } else {
                        lerArquivo();
                    }
					break;
				case 2:
                    if(finalizado == true){
                        System.out.println("Acesso negado! O dia já foi finalizado");
                    } else{
                        cadastrarNovoPaciente();
                    }
                    break;
				case 3:
                    if(finalizado == true){
                        System.out.println("Acesso negado! O dia já foi finalizado");
                    } else{
					consultarPaciente();
                    }
					break;

				case 4:
                    if(finalizado == true){
                        System.out.println("Acesso negado! O dia já foi finalizado");
                    } else{
					finalizarAtendimento();
                    }
					break;
				
				case 5:
					estatisticas();
					break;

				case 6:
					continua = false;
					System.out.println("Encerrando...");
					System.out.println("Sistema encerrado!");
					break;
				default: 
					System.out.println("Opção inválida. Tente novamente.");

            }
    	}
    }


        public void lerArquivo() {
        try{
            FileReader arquivo = new FileReader("exames.txt"); 
        	BufferedReader linha = new BufferedReader(arquivo); 
        		
        	//le a linha de cabeçalho do TXT
        	String aux = linha.readLine();
        		
        	//le a primeira linha do TXT
        	aux = linha.readLine();
        	int posicao = 0;

        	while (aux != null){
        	    String[] dados =aux.split(",");
        		  
        		//criar objeto do tipo Exame
        		Exame ex = new Exame(dados[0], dados[1], Integer.parseInt(dados[2]));

        		ExamesDisponiveis.add(posicao,ex);
        		posicao++;

        		aux = linha.readLine();
        	}

        	arquivo.close();
            linha.close();
            dadosCarregados = true;
            System.out.println("Dados carregados com sucesso!");
        }

        catch(Exception e){
            System.out.println("Erro na carga do arquivo de Exames");
        }
                
    }


    public Exame buscarExame(String abrev){
            try {
                for (int i = 0; i < ExamesDisponiveis.size(); i++) {
                    Exame e = ExamesDisponiveis.get(i);
                    if (e.getAbreviacao().equalsIgnoreCase(abrev)) {
                        return e;
                    }
                }
            } 
            catch (Exception e) {}
            
            return null; //não encontrou o exame
    } 
  

    public void cadastrarNovoPaciente(){

        if(dadosCarregados == false){
            System.out.println("Carregue os dados primeiro!");
            return;
        }

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("NOME: ");
        String nome = sc.nextLine();

        PedidoExame pedido = new PedidoExame(cpf, nome);

        int contador = 1;
        while(true){
            System.out.print("EXAME " + contador + ": ");
            String abrev = sc.nextLine();

            if (abrev.equals("XXX")) {
                break;
            }
            
            Exame ex = buscarExame(abrev);

            if( ex != null){
                pedido.adicionarExame(ex);
                System.out.println(" - " + ex.getNome() + " - " + ex.getQtdeDias() + " dias");
                contador++;
                
            }

            else{
                System.out.println("Exame não encontrado");
            }

        }
        
        pedido.calcularDataLiberacao();

        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Os exames estarão disponíveis no dia " + pedido.getDataLiberacao().format(f) + " a partir das 17h" );

        try {
            pedidosDoDia.add(pedidosDoDia.size(), pedido);
        } 
        
        catch (Exception e) {
            System.out.println("Erro ao salvar pedido!");
        }

    }


    public void consultarPaciente(){

        if(dadosCarregados == false){
            System.out.println("Carregue os dados primeiro!");
            return;
        }

        System.out.print("CPF: ");
        String cpf  = sc.nextLine();

            try {
                for (int i = 0; i < pedidosDoDia.size(); i++) {
                    PedidoExame p = pedidosDoDia.get(i);

                    if (p.getCpf().equals(cpf)) {

                        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                        System.out.println("CPF: " + p.getCpf());
                        System.out.println("NOME: " + p.getNome());
                        System.out.println("DATA REALIZAÇÃO: " + p.getDataRealizacao().format(f));
                        
                        int cont = 1;
                        for (Exame ex : p.getExamesSolicitados()) {
                            System.out.println("EXAME " + cont + ": " + ex.getAbreviacao() + " - " + ex.getNome());
                            cont++;
                        }

                    System.out.println("DATA LIBERACAO: " + p.getDataLiberacao().format(f));
                    
                    }
                    else{
                        System.out.println("Paciente não encontrado");
                    }
                }
            }
            
            catch (Exception e) {
                System.out.println("Erro na consulta.");
            }
    }


     public void finalizarAtendimento(){
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String nomeArquivo = LocalDate.now().toString().replace("-", "") + ".txt";

            BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo));

            for (int i = 0; i < pedidosDoDia.size(); i++) {
                PedidoExame p = pedidosDoDia.get(i);

                bw.write(p.getCpf() + ";" + p.getNome() + ";" +
                        p.getDataRealizacao().format(formato) + ";" +
                        p.getDataLiberacao().format(formato));


                for (Exame ex : p.getExamesSolicitados()) {
                    bw.write(";" + ex.getAbreviacao());
                }

                bw.newLine();
            }

            bw.close();

            finalizado = true; 

            System.out.println("Arquivo gerado com sucesso!");
            System.out.println("Atendimentos finalizados!");

        } 
        catch (Exception e) {
            System.out.println("Erro ao salvar!");
        }
    }

        public void estatisticas(){
            if (pedidosDoDia.size() == 0) {
                System.out.println("Nenhum atendimento realizado.");
                return;
            }

            System.out.println("\n--- ESTATÍSTICAS ---");
            System.out.println("Total de pacientes atendidos: " + pedidosDoDia.size());
            int totalExames = 0;

            try {
                for (int i = 0; i < pedidosDoDia.size(); i++) {
                    PedidoExame p = pedidosDoDia.get(i);

                    totalExames += p.getExamesSolicitados().size();
                }

                System.out.println("Total de exames realizados: " + totalExames);

            } 
            
            catch (Exception e) {
                System.out.println("Erro ao calcular estatísticas.");
            }
        }
}