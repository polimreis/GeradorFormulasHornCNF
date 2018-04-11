package leitor;

import java.math.BigDecimal;

public class CalculaFatorial {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 int  n = 50;  
	 BigDecimal fatorial = new BigDecimal(1);  
		   
		 for ( int i = n; i > 0; --i ) {  
		    fatorial = fatorial.multiply(new BigDecimal(i));  
		 }  
		   
		 System.out.println(fatorial);
	}

}
