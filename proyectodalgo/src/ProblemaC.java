import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase que representa la solución al problema Dentro o fuera
 * @author Juan Diego Camacho
 * @author Daniel Perilla
 *
 */
public class ProblemaC {
	
	/**
	 * Arreglo de objetos tipo coordenada que representan el poligono que se va a analizar
	 */
	private Coordenada[] poligono;
	
	/**
	 * Tamaño del poligono (M)
	 */
	private int tam;
	
	/**
	 * Booleano que permite evaluar si el punto que se quiere observar está en un vertice del poligono
	 */
	boolean enVertice;
	
	/**
	 * Punto que se quiere evaluar
	 */
	Coordenada punto;
	
	/**
	 * Método principal del programa
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String linea;
		int puntox;
		int puntoy;
		int lados;
		
		while (true)
		{																	
			linea = br.readLine();
			if (linea.equals("0 0 0 0"))
				return;
			lados = Integer.parseInt(linea.split(" ")[1]);
			puntox = Integer.parseInt(linea.split(" ")[2]);
			puntoy = Integer.parseInt(linea.split(" ")[3]);
			linea = br.readLine();
			
			String[] datos = linea.split(" ");
			ProblemaC a = new ProblemaC(datos, lados, puntox, puntoy);
			System.out.println(a.fueraODentro());
			
		}
					
	}
	
	/**
	 * Constructor de un objeto tipo ProgramaC que crea el poligono y almacena el punto que se va a evaluar
	 * @param data Cadena de texto dividida de la cual se obtienen los números para crear el poligono
	 * @param lados Cantidad de lados del poligono
	 * @param corx Coordenada en x del punto que se va a evaluar
	 * @param cory Coordenada en y del punto que se va a evaluar
	 */
	public ProblemaC(String[] data, int lados, int corx, int cory) {
		poligono = new Coordenada[lados];
		tam = lados;
		enVertice = false;
		for(int i = 0; i < 2*lados; i+=2 ) {
			poligono[i/2] = new Coordenada(Integer.parseInt(data[i]), Integer.parseInt(data[i+1])); 
			if(Integer.parseInt(data[i])== corx && Integer.parseInt(data[i+1])== cory)
				// Al construir el poligono se evalua si el punto a evaluar se encuentra en uno de los vertices del poligono
				enVertice = true;
		}
		punto = new Coordenada(corx, cory);
	}
	
	/**
	 * Regresa el poligono de la instancia de la clase
	 * @return poligono
	 */
	public Coordenada[] getPoligono() {
		return poligono;
	}
	
	/**
	 * Algoritmo en el que se evalua si el punto se encuentra dentro del poligono, fuera de este o en su frontera
	 *  Precondición - El poligono se ha inicializado
	 *  Postcondición - Se retorna el estado del punto con respecto al poligono
	 * @return 1 Si el punto está dentro del poligono,
	 * @return 0 Si el punto está en la frontera del poligono
	 * @return -1 Si el punto está por fuera del poligono
	 */
	public int fueraODentro() {
		// Si el punto está en el vertice se retorna inmediatamente la respuesta
		if (enVertice)
			return 0;
		// Intersecciones cuenta el número de veces que intersecta una línea trazada desde el punto con los lados del poligono
		int intersecciones = 0;
		for(int i = 1; i <tam; i++) {
			//Se evalua si el punto está en una línea horizontal del poligono
			if(poligono[i].getY()==poligono[i-1].getY() && poligono[i].getY() == punto.getY() 
					&& punto.getX()>Math.min(poligono[i].getX(), poligono[i+1].getX()) && 
					punto.getX()<Math.max(poligono[i].getX(),poligono[i-1].getX()) ) {
				return 0;
			}
			
			if(punto.getY()> Math.min(poligono[i].getY(), poligono[i-1].getY()) &&
					punto.getY()<= Math.max(poligono[i].getY(), poligono[i-1].getY()) &&
					punto.getX() <= Math.max(poligono[i].getX(), poligono[i-1].getX())&&
					poligono[i].getY() != poligono[i-1].getY()) {
				
				// Se calcula de la ecuación de la recta  para x (x =(y-y1+mx1)/m) un x estimado de una recta
				double xCalculado = (double)(punto.getY()-poligono[i].getY())*
						(poligono[i-1].getX()-poligono[i].getX())/(poligono[i-1].getY()-poligono[i].getY()) +
						poligono[i].getX();
				
				// Si el estimado es igual al real entonces el punto está en la linea
				if(xCalculado == punto.getX())
					return 0;
				// Si la línea es horizontal o el valor real es menor al estimado entonces la línea cruza por un lado del poligono y se suma a las intersecciones
				if(poligono[i].getX() == poligono[i-1].getX() || punto.getX() < xCalculado) {
					intersecciones++;
				}	
			}
		}
		// Si las intersecciones son impares entonces el punto está dentro del poligono
		if(intersecciones % 2 != 0)
			return 1;
		else
			return -1;	
	}
	
	/**
	 * Clase para modelar una coordenada
	 *
	 */
	class Coordenada {
		/**
		 * Posición en x de la coordenada
		 */
		private int x;
		
		/**
		 * Posición en y de la coordenada
		 */
		private int y;
		
		/**
		 * Constructor de una coordenada
		 * @param x
		 * @param y
		 */
		public Coordenada(int x, int y ) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public String toString() {
			return "("+x+", "+y+")";
		}
		
	}
	
	public String toString() {
		String r = "[";
		for(int i = 0; i<poligono.length; i++) {
			r+=poligono[i].toString()+", ";
		}
		r+="]";
		return r;
	}
}
