package com.JMR;

public class Tabelas {

public String runProgeto () {
		
		System.out.println(runPom());
		return "" ;
	}
	
	
    public String runlinha() {
        System.out.println("_______________________________________________________________________________________");
        return "\t\tNovo Conteudo ou Recurso:\n\n\t";
    }
    
    public String runPom() {
        System.out.println(runlinha() + "Verção do Afiliado: \n"
        		+ " |_ 1.1.2.7.04-AFILIADO-XP \n");
        return "";
    }
    

    
    public String runRodape() {
        System.out.println(runlinha() + "Rodapé da pagina com CSS e HTML \n "
                + " |_ id \n |_ https://www.youtube.com/watch?v=SUFzgCwJZkk \n"
                + " |_ Icones ____________________\n"
                + " |_ https://fontawesome.com/");
        return "";
    }

 
  
    public String runRecurssos() {
        System.out.println(runlinha() + "|_ https://www.youtube.com/watch?v=HWFwgQYdEJE"
        		+ "\n|_ Link para o Debug no codigo: \n"
                + "|_ https://www.youtube.com/watch?v=zS7UG63we4g"
                + "\n|_ Spring Security: \n");
        System.out.println(runlinha() + "|_ Documentação do Spring Security: * Table 11.1. Common built-in expressions *\n"
				+ "|_ https://docs.spring.io/spring-security/site/docs/5.5.8/reference/html5/ \n"
				+ "|_ https://docs.spring.io/spring-security/site/docs/5.1.5.RELEASE/reference/htmlsingle/ \n"
				+ "|_ Melhor opção para configuração de segurança: \n"
				+ "|_ * hasAuthority([authority]) e hasAnyAuthority([authority1,authority2]) *");
        System.out.println(runlinha() + "Curso de spring boot: \n" 
				+ "|_ https://www.youtube.com/watch?v=7aoQTEzagKE");
        return "";
    }


    public String runInternet() {
        System.out.println(runlinha() + "Fazer um biuld da aplicação, Comandos: \n"
                + "|_ Java-build-package\n"
                + "|_ package -e\n\n"
                + "|_ \tSubir a Aplicação no CMD, Comandos: \n"
                + "|_ C:\\Users> cd * local do build *"
                + "|_ java -jar * build do APP *"
                + "\n\n\tAplicação OK -> Link de acesso: http://localhost:8080/"
        );
        return "";
    }
    
    public String runBuildingAfiliado() {
        System.out.println(runlinha() + "|_ * Building do progeto Afiliado * \n\n"
        		+ "|_ cd C:\\IDEs\\ProgetosVideos\\ProgetosVideos\\afiliado_24 \n"
        		+ "|_ C:\\IDEs\\ProgetosVideos\\ProgetosVideos\\afiliado_24> mvn package \n"
        		+ "|_ C:\\IDEs\\ProgetosVideos\\ProgetosVideos\\afiliado_24> cd .\\target \n"
        		+ "|_ C:\\IDEs\\ProgetosVideos\\ProgetosVideos\\afiliado_24\\target> java -jar afiliado-0.0.1.40-AFILIADO.jar \n\n"
        		+ "___________________________________________________________________________\n"
        		+ "|_ \t\t * Mini Servidor por Computador, Celular, etc * \n\n"
        		+ "|_ C:\\Windows\\System32> ipconfig \n"
        		+ "|_ Endereço IPv4. . . . . . . .  . . . . . . . : 192.168.0.112 \n"
        		+ "|_ http://192.168.0.112:8082/exibirProdutosVenda \n");
        
        return "";
    }
    
    

}
