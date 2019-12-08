import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
//Autora: Vilma Tirado Gómez
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
				int respuesta = instancia.buscarSubArreglo(numeros);
				System.out.println(respuesta);
				line = br.readLine();
			}
		}
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
