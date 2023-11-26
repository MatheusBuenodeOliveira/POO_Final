// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileReader;
// import java.io.PrintStream;
// import java.nio.charset.Charset;
// import java.util.Locale;
// import java.util.Scanner;

// public class LerArquivos {
//     private Scanner entrada = null;                 // Atributo para entrada de dados
	
// 	public LerArquivos( String arquivo) {
// 		try {
// 			BufferedReader streamEntrada = new BufferedReader(new FileReader(arquivo));
// 			entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
// 			PrintStream streamSaida = new PrintStream(new File("dadosout.txt"), Charset.forName("UTF-8"));
// 			System.setOut(streamSaida);             // Usa como saida um arquivo
// 		} catch (Exception e) {
// 			System.out.println(e);
// 		}
// 		Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
// 		entrada.useLocale(Locale.ENGLISH);
		
		

// 	}
// }
