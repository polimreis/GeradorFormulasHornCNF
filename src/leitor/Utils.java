package leitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.sun.jdi.IntegerValue;

public class Utils {


	static List simplificaFormula(List listaOriginal) {
		System.out.println("\nINICIO SIMPLIFICACÃO\n");
		Iterator it = listaOriginal.iterator();
		List listaSimplificada = new ArrayList<String>();
        listaSimplificada.addAll(listaOriginal);
		
		while (it.hasNext()){
			List clausula = (List) it.next();
			List clausulaSimplificada = new ArrayList<String>();
			clausulaSimplificada.addAll(clausula);
			
			System.out.println(clausula);
			
			int aux =0;
			
			for(int i=0;i< clausula.size()-1; i++){
				for (int j=0;j< clausula.size(); j++){
					if(i!=j){
						Integer comparador1 = Integer.valueOf((String)clausula.get(i));
						Integer comparador2 = - Integer.valueOf((String) clausula.get(j));
						Integer comparador3 = Integer.valueOf((String) clausula.get(j));
						
						if(comparador1.equals(comparador2)){
							System.out.println("TESTE 1");
							listaSimplificada.remove(clausula);
							break;
						}
						
						if(comparador1.equals(comparador3)){
							aux ++;
							System.out.println("TESTE 2");
							clausulaSimplificada.remove(j);
						}
					}
				}
			}
			
			if (aux >0){
				listaSimplificada.remove(clausula);
				listaSimplificada.add(clausulaSimplificada);
			}
			
		}
		return  listaSimplificada;
		
	}
	
	
	// PENSAR COMO FAZER ESSE MÉTODO
	static List simplificaFormulaParaHorn(List listaOriginal) {
		System.out.println("\nINICIO SIMPLIFICACÃO PARA HORN SAT\n");
		
		Iterator it = listaOriginal.iterator();
		List listaSimplificada = new ArrayList<String>();
        listaSimplificada.addAll(listaOriginal);
		
		while (it.hasNext()){
			List clausula = (List) it.next();
			List clausulaSimplificada = new ArrayList<String>();
			clausulaSimplificada.addAll(clausula);
			
			System.out.println(clausula);
			int aux =0;
			
			for(int i=0;i< clausula.size(); i++){
				Integer literal = Integer.valueOf((String)clausula.get(i));
				System.out.println(clausulaSimplificada);		
				if(literal>0){;
					aux++;
					if (aux >1){
						clausulaSimplificada.remove(String.valueOf(literal));
					}
				}
				System.out.println(clausulaSimplificada);	
			}
			if (aux > 1){
				listaSimplificada.remove(clausula);
				if (clausulaSimplificada!= null && clausulaSimplificada.size()>0){
					listaSimplificada.add(clausulaSimplificada);
				}
			}
		}
		return  listaSimplificada;
		
	}
	
	static List sorteioAleatorio(List listaOriginal) {
		System.out.println("\nINICIO VARREDURA TOTAL SAT\n");
	
		boolean SAT = false;
		Iterator it = listaOriginal.iterator();
		List listaPositiva = new ArrayList<String>();
		List listaNegativa = new ArrayList<String>();
		
		//separando as clausulas positivas das clausulas negativas
		while (it.hasNext()){
			List clausula = (List) it.next();
			List clausulaPositiva = new ArrayList<String>();
			List clausulaNegativa = new ArrayList<String>();
			
			System.out.println(clausula);
			
			for(int i=0;i< clausula.size(); i++){
				String literal=(String)clausula.get(i);
			
				if(Integer.valueOf(literal)>0){;
					clausulaPositiva.add(literal);
				}else{
					clausulaNegativa.add(literal);
				}
			}
			listaNegativa.add(clausulaNegativa);
			listaPositiva.add(clausulaPositiva);
			
		}
		System.out.println("LISTA NEGATIVA:"+ listaNegativa);
		
		List listaPositivaAleatoria = new ArrayList();
		
		Iterator itera = listaPositiva.iterator();
		
		while(itera.hasNext()){	
			List lista = (List) itera.next();
			float tamanho = lista.size();
			
			if (tamanho<1){
				listaPositivaAleatoria.add(0);
			}else if (tamanho==1){
				listaPositivaAleatoria.add(lista.get(0));
			}else{
				Random gerador = new Random();
				float range  = 1/tamanho;
				float inicioRange =0;
				float fimRange = range;
				float num = gerador.nextFloat();
				System.out.println( " FLOAT:"+ num);
				int result= 0;
				
				for(int i = 0; i<tamanho; i++){ 
					if(inicioRange<num && num<fimRange && result ==0){
						listaPositivaAleatoria.add(lista.get(i));
						result++;
					}
					inicioRange =  inicioRange +range;
					fimRange = fimRange + range;
				}
			}
		}
		
		System.out.println("LISTA POSITIVA ALEATORIA:"+ listaPositivaAleatoria);
	
		List listaAleatoria = new ArrayList();
		for (int i = 0; i< listaNegativa.size(); i++){
			 List clausulaAleatoria = new ArrayList();
			 clausulaAleatoria  = (List) listaNegativa.get(i);
			 clausulaAleatoria.add(listaPositivaAleatoria.get(i));
			 listaAleatoria.add(clausulaAleatoria);
		}
		 
		System.out.println("LISTA  ALEATORIA:"+ listaAleatoria); 
		

		List listaCopiaAleatoria = new ArrayList();
		Iterator iterator = listaAleatoria.iterator();
		while(iterator.hasNext()){
			List listaClausulas = (List) iterator.next();
			List listaClausulasCopia = new ArrayList();
			for (int i=0; i< listaClausulas.size(); i++){
				if (!listaClausulas.get(i).equals(0)){
					listaClausulasCopia.add(listaClausulas.get(i));
					
				}
			}
			
			listaCopiaAleatoria.add(listaClausulasCopia);
			
		}
		
		System.out.println(" LISTA COPIA ALEATORIA : " + listaCopiaAleatoria );
		
		return listaCopiaAleatoria;
	}
	

	static boolean verificarSAT(List listaTodasClausulas,List listaCopia1) {
		List clausula2Copia;
		Integer iLiteral;
		String literalOposto = "";
		String sLiteral =  "";
		boolean satisfazivel = true;
		boolean clausulaUnitaria = true;
		
		//Verifica se a fórmula do arquivo é ou não é satisfazível

	    // Quando nao houver clausulas unitárias nas formulas não há nada a propagar.  
		// Como todas as cláusulas são de horn, então todas as cláusula possuem pelo menos 
		// um literal negativo ,  se não tiver clausulas unitarias,  fazendo todos os atomos
		// falsos v satisfaz todas as cláusulas.  Se restarem clausulas unitarias opostas, entao é UNSAT
		
		//Enquanto existir clausulas unitarias na formula faça:
		while (clausulaUnitaria) {
			clausulaUnitaria = false;
			Iterator it1 = listaTodasClausulas.iterator();
			while (it1.hasNext()) {
				iLiteral = new Integer(0);
				List clausula = (List) it1.next();
				// se o tamanho da clausula for 1 (clausula unitária) continua a simplificação
				// se nao existir mais nenhuma clausula unitaria significa que o problema é satisfazível
				if (clausula.size() == 1) {
					clausulaUnitaria = true;
					listaCopia1.remove(clausula);
					sLiteral =  (String) clausula.get(0);
					iLiteral = new Integer (sLiteral);
					// literal oposto da clausua unitaria encontrada na formula
					literalOposto = ""+ (-1*iLiteral);
					Iterator it2 = listaCopia1.iterator();
					while (it2.hasNext()) {
						List clausula2 = (List) it2.next();
						if (clausula2.size() == 1) {
							// se existir alguma clausula unitaria oposta a outra clausula da formula, o problema é 
							// insatisfazível
							if (clausula2.get(0).equals(literalOposto)) {
								satisfazivel = false;
								System.out.println("\nEste Problema é insatisfazível!");
							}	
						}
					}
				}
				int aux1;
				int aux2;
				// se  não existir alguma clausula unitaria oposta a outra clausula da formula
				// continua a simplificação ou seja, apague os literais opostos das outras clausulas
				// consequentemente deverá surgir outras clausulas unitárias
				if (iLiteral != 0  && satisfazivel != false){
					Iterator it2 = listaTodasClausulas.iterator();
					while (it2.hasNext()) {
						aux1 = 0;
						aux2 = 0;
						clausula2Copia = new ArrayList<String>();
						List clausula2 = (List) it2.next();
						// Cria a nova lista de clausulas , agora simplificada
						if (clausula2.size() != 1) {
							clausula2Copia.addAll(clausula2);
							Iterator it5 = clausula2.iterator();
							// Simplifica formula
							while (it5.hasNext()) {
								String atom = (String) it5.next();
								if (atom.equals(literalOposto )) {
									aux1++;
									clausula2Copia.remove(atom);
								}else if (atom.equals(sLiteral)) {
									aux2++;
								}
							} // fim da simplificação
							if (aux1>0){
								listaCopia1.remove(clausula2);
								listaCopia1.add(clausula2Copia);
							}else if(aux2>0){
								listaCopia1.remove(clausula2);
							}
						}
					}
				}
			}
			listaTodasClausulas = new ArrayList<List>();
			listaTodasClausulas.addAll(listaCopia1);
		}
		// se nao existe mais nenhuma clausula unitária, o problema é satisfazível
		if (satisfazivel) {
			System.out.println("\nEste problema é satisfazível");
		}
		
		return satisfazivel;
	}

	// Verifica se a valoraçao satisfaz ou nao a formula
	static void apresentarMensagem(String msg, Integer result) {
		if (result > 0) {
			System.out.println("\nA valoração satizfaz a formula");
		}
		if (result == 0) {
			System.out.println("\nA valoração NAO satizfaz a formula");
		}
		if (msg == "") {
			System.out
					.println("\nTodas clausulas do arquivo são clausulas de Horn! ");
		} else {
			System.out.println(msg);
		}
	}

	//Atravez de uma string, cria-se um vetor com a valoração
	static Map lerValoracao(String[] linhaValoracao) {
		Map valoracao = new HashMap();
		for (int i = 2; i < linhaValoracao.length; i++) {
			if (linhaValoracao[i].equals(String.valueOf(i - 1))) {
				valoracao.put(i - 1, 1);
			} else {
				valoracao.put(i - 1, 0);
			}
		}
		return valoracao;
	}
	


}
