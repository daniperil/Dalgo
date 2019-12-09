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
	 * @param a arreglo de números
	 * @return El tamaño del arreglo recurrente más largo dentro de a
	 */
	public int arregloRecurrenteMasLargo(int[] a) {
		int max = 0;
		int c = -1;
		int in = 0;
		for(int i = 1; i< a.length; i++) {
			for(int k = i; k<a.length; k++) {
				if(a[k-i]!=a[k]) {
					c = -1;
					in = k;
				}
				c++;
				if(c>max) {
					max = c;
				}
				
			}
		}
		return max;
	}
	public int buscarSubArreglo(int[] arreglo){
		//Longitud del arreglo mas largo 
		int l= 0;
		int c=0;
		int d=0;
		int mp=-1;
		int ip=-1;
		int mn=-1;
		for (int i=arreglo.length-1; i>=0; i--)
		{
			if (arreglo[i]<0)
			{
				if (arreglo[i]*-1>mn)
				{
					mn=arreglo[i]*-1;
					d=ip-i;
				}
			}
			else 
			{
				if (arreglo[i]>mp)
				{
					if(arreglo[i]>mn)
					{
						mp=arreglo[i];
						ip=i;
						c=0;
					}
					else 
					{
						mp= arreglo[i];
						ip=i;
						c-=d;
					}
						
				}
			}
			c++;
			if(c>l)
			{
				l=c;
			}
		}
		return l;
	}
}
