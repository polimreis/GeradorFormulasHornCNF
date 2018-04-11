package gerador.action;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class GerarFormulasHornAction extends DispatchAction {

	public ActionForward gerarCnf(ActionMapping pMap, ActionForm pForm,
			HttpServletRequest pReq, HttpServletResponse pResp)
			throws Exception {

		String sM = pReq.getParameter("M");
		String sN = pReq.getParameter("N");
		String sK = pReq.getParameter("K");
		
		int M;
		int N;
		int K;
		
		Random gerador = new Random();
		
		try {
			M = new Integer(sM);
			N = new Integer(sN);
			K = new Integer(sK);
		} catch (Exception e) {

			pReq.setAttribute("erro", "Valor invalido! Digite apenas numeros!");
			return pMap.findForward("INDEX");
		}
		
	
		for (int a=0;a<100; a++){
		
			String clausulas[] = new String[M];
			
			
			for (int i = 0; i < M; i++) {
				boolean literalPositivo = gerador.nextBoolean();
				if (literalPositivo){
					int aux = 0;
					for (int j = 0; j < K; j++) {
						int d;
						if (clausulas[i] != null) {
							  d = gerador.nextInt(N * 2) + 1;
							  if (d > N) {
								d = -(d - N);
								clausulas[i] = clausulas[i] + " " + d;
							  }else{
								  aux ++;
								  if(aux <=1){
									  clausulas[i] = clausulas[i] + " " + d;
								  }else{
									  j--;
								  }
							  }
						}
		
						else {
							d = gerador.nextInt(N * 2) + 1;
							if (d > N) {
								d = -(d - N);
							}else{
								  aux ++;
								  if(aux >1){
									  j--;
								  }
							  }
							clausulas[i] = "" + d;
						}
					}
				}else {
					int aux = 0;
					for (int j = 0; j < K; j++) {
						int d;
		
						if (clausulas[i] != null) {
							  d = - (gerador.nextInt(N) + 1);
							  clausulas[i] = clausulas[i] + " " + d;
						}
		
						else {
							d = -(gerador.nextInt(N) + 1);
							clausulas[i] = "" + d;
						}
					}
				}

		}
		int contador = 0;
		try {
			File diretorio = new File("/Users//polianareis//Documents//ARQUIVOS_CNS//");
			if (!diretorio.exists()){
				diretorio.mkdir();
			}
			File arquivo = new File(diretorio,"ArquivoCNF.cnf");

			boolean arquivoNovo = arquivo.exists();

			if (arquivoNovo == true) {
				do {
					contador++;
					arquivoNovo = false;
					arquivo = new File(diretorio,"ArquivoCNF" + contador + ".cnf");
					arquivoNovo = arquivo.exists();

				} while (arquivoNovo == true);

				arquivo.createNewFile();
			} else {
				arquivo.createNewFile();
			}

			FileWriter fw = new FileWriter(arquivo);
			fw.write("c ArquivoCNF" + contador
					+ ".cnf -  Gerado automaticamente pelo GeradorCNF\n");
			fw.write("c Universidade de Sao Paulo - IME \n");
			fw.write("c Disciplina: Laboratorio de Inteligencia Artificial\n");
			fw.write("c Professor: Marcelo Finger\n");
			fw.write("c Desenvolvido por: Poliana Magalhaes Reis / 2009 \n");
			fw.write("c \n");
			fw.write("p cnf " + N + " " + M + "\n");

			for (int i = 0; i < M; i++) {

					fw.write(clausulas[i] + " " + 0 + "\n");

				System.out.println(clausulas[i]);

			}
			fw.flush();

			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		}

		pReq.setAttribute("msg", "100 arquivos foram gerados com sucesso em /Users//polianareis//Documents//ARQUIVOS_CNS//");
		return pMap.findForward("INDEX");

	}

}
