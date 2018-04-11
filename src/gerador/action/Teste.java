package gerador.action;

import java.util.Random;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Random gerador = new Random();
		
		for (int i=0 ; i<10 ; i++){
			System.out.println(gerador.nextInt(5));
		}
		for (int i=0 ; i<10 ; i++){
			System.out.println(gerador.nextBoolean());
		}
	}

}
