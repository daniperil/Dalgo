import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Clase que presenta la solución al problema del arreglo recurrente más largo
 * @author Juan Diego Camacho
 * @author Daniel Perilla
 *
 */
public class ProblemaA {

	public static void main(String[] args) throws Exception {
		ProblemaA instancia = new ProblemaA();
		try ( 
				InputStreamReader is= new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(is);
				) { 
			String line = br.readLine();

			while(line!=null && line.length()>0 && !"0".equals(line)) {
				int tam=Integer.parseInt(line);
				line = br.readLine();
				final String [] dataStr = line.split(" ");
				final int[] numeros = Arrays.stream(dataStr).mapToInt(f->Integer.parseInt(f)).toArray();
				int respuesta = instancia.arregloRecurrenteMasLargo(numeros);
				System.out.println(respuesta);
				line = br.readLine();
			}
		}
	}

	/**
	 * Algoritmo de solución que dado un arreglo busca el arreglo recurrente más largo dentro de este
	 * precondición - true, no hay condiciones iniciales para el arreglo
	 * postcondición - se retorna max como el arreglo recurrente más largo del arreglo a
	 * @param a arreglo de números
	 * @return El tamaño del arreglo recurrente más largo dentro de a
	 */
	public int arregloRecurrenteMasLargo(int[] a) {
		// La variable max alamacena cual es el elemento máximo globalmente, se inicializa en cero para retornar una respuesta adecuada en
		// un caso nulo
		int max = 0;
		// c es una variable que representa un tamaño de subarreglo recurrente más grande localmente para cada una de las distancias posibles
		int c = -1;
		// en el ciclo la i se interpreta como la d del enunciado del problema
		for(int i = 1; i< a.length; i++) {
			for(int k = i; k<a.length; k++) {
				// En caso de que sean diferentes los valores en las posiciones en las que se va a comparar se reinicia el contador local
				if(a[k-i]!=a[k]) {
					c = -1;
				}
				// El contador local se actualiza por cada iteración y en caso de que no se cumpla la condición estará en cero
				c++;
				if(c>max) {
					max = c;
				}
				
			}
		}
		return max;
	}
	
}
