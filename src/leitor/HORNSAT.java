/*Criado por Poliana Magalhães Reis
 * Fevereiro de 2010
 * */

package leitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HORNSAT {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			String[] linhaDoArquivo;
			Map valoracao = new HashMap(); // Vetor para armazenar a valoração
			List clausulasList = new ArrayList<String[]>(); // Lista de
															// Clausulas
			List<String> listaLiterais = null;
			List valoresClausulasList = new ArrayList<Integer>();
			List listaTodasClausulas = new ArrayList<List>();
			List listaCopia1 = new ArrayList<String>();
			List listaSimplificadaCopia;
			List clausula2Copia = new ArrayList<String>();
			boolean todasHorn = true;

			Integer iLiteral = new Integer(0);
			Integer valorClausula;
			String str;
			int horn = 0;
			String msg = "";
			boolean SAT = false;

			// Le Arquivo no formato CNF
			String arquivo = "/Users//polianareis//Desktop//ARQUIVOS_CNS//zchaff64/ArquivoCNF.cnf";
			BufferedReader in = new BufferedReader(new FileReader(arquivo));

			while (in.ready()) {

				str = in.readLine(); // String de uma linha do arquivo
				linhaDoArquivo = str.split(" "); // separa a string em um vetor

				// Verifica se a linha do vetor contem a palavra valoracao,
				// se contiver significa que nesta linha está a valoraçao da
				// formula
				if (linhaDoArquivo.length > 1
						&& linhaDoArquivo[1].equals("valoracao")) {
					// Cria um vetor de valoração
					valoracao = Utils.lerValoracao(linhaDoArquivo);
				}
				
				// Monta uma lista de clausulas
				if (!linhaDoArquivo[0].equals("c")
						&& !linhaDoArquivo[0].equals("p")) {
					listaLiterais = new ArrayList<String>();
					clausulasList.add(linhaDoArquivo);
					valorClausula = 0;
					horn = 0;

					// Verifica se todas as clausulas são clausulas de horn
					for (int i = 0; i < linhaDoArquivo.length - 1; i++) {
						Integer literal;
						String sAtomo = linhaDoArquivo[i];
						listaLiterais.add(sAtomo);
						Integer iAtomo = new Integer(sAtomo);

						// Verifica se existe uma valoração
						if (valoracao.size() > 0) {
							if (iAtomo > 0) {
								horn++;
								literal = (Integer) valoracao.get(iAtomo);
							} else {
								literal = (Integer) valoracao.get(-iAtomo);
								if (literal == 1) {
									literal = 0;
								} else if (literal == 0) {
									literal = 1;
								}
							}
							valorClausula = valorClausula + literal;
						} else {
							if (iAtomo > 0) {
								horn++;
							}
						}
						if (horn > 1) {
							todasHorn = false;
							msg += "\nA Clausula " + clausulasList.size()	+ " não é uma clausula de Horn!";
						}
					}
					valoresClausulasList.add(valorClausula);
					if (listaLiterais.size() > 0) {
						listaTodasClausulas.add(listaLiterais);
					}

					System.out.println(str);
				}
			}

			Iterator it = valoresClausulasList.iterator();
			Integer result = 1;

			// Se o valor de alguma clausula é zero o result será zero (Falso),
			// o que prova que a valoração
			// não satizfaz a formula
			while (it.hasNext()) {
				result = result * (Integer) it.next();
			}

			// Verifica se a valoraçao satisfaz ou nao a formula
		//	Utils.apresentarMensagem(msg, result);

			// Cria uma copia da lista de todas clausulas para manipulação das
			// clausulas
			listaCopia1.addAll(listaTodasClausulas);

			// Se uma clausula tiver literais opostos, elimina a clausula
			// pois ela nao traz nenhuma informacao, se uma clausula tiver dois
			// literais iguais, elimina um literal (redundancia)
			List listaSimplificada1 = Utils.simplificaFormula(listaCopia1);
			
			// Se todas as clausulas nao forem clausulas de horn,
			// simplificar as clausulas para deixar com formato de horn
			listaSimplificadaCopia = new ArrayList<String>();
			int cont =0;
			if (!todasHorn ) {
				while ( cont < 50 && !SAT){
					System.out.println("\n	ITERACAO NUMERO => "+ cont + " \n");
					List listaSimplificada2 = Utils.sorteioAleatorio(listaSimplificada1);
					listaSimplificadaCopia.addAll(listaSimplificada2);
					SAT = Utils.verificarSAT(listaSimplificada2,listaSimplificadaCopia);
					cont++;
				}
			
			}else{
				listaSimplificadaCopia.addAll(listaSimplificada1);
				SAT = Utils.verificarSAT(listaSimplificada1,listaSimplificadaCopia);
			}
			
			
			
			if (SAT) {
				System.out.println("A formula é SAT");
			} else {
				System.out.println("A formula é UNSAT");
			}

			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
