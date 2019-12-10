import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import java.util.ArrayList;
/**
 * Clase que representa la solución al problema Dentro o fuera
 * @author Juan Diego Camacho
 * @author Daniel Perilla
 *
 */
public class ProblemaC {

	private Coordenada[] poligono;
	private int tam;
	boolean enVertice;
	Coordenada punto;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String linea;
		int puntox;
		int puntoy;
		int lados;
		int numServicios;
		String data[][];
		
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
	public ProblemaC(String[] data, int lados, int corx, int cory) {
		poligono = new Coordenada[lados];
		tam = lados;
		enVertice = false;
		for(int i = 0; i < 2*lados; i+=2 ) {
			poligono[i/2] = new Coordenada(Integer.parseInt(data[i]), Integer.parseInt(data[i+1])); 
			if(Integer.parseInt(data[i])== corx && Integer.parseInt(data[i+1])== cory)
				enVertice = true;
		}
		punto = new Coordenada(corx, cory);
	}
	
	public Coordenada[] getPoligono() {
		return poligono;
	}
	
	public int fueraODentro() {
		if (enVertice)
			return 0;
		int intersecciones = 0;
		for(int i = 1; i <tam; i++) {
			if(poligono[i].getY()==poligono[i-1].getY() && poligono[i].getY() == punto.getY() 
					&& punto.getX()>Math.min(poligono[i].getX(), poligono[i+1].getX()) && 
					punto.getX()<Math.max(poligono[i].getX(),poligono[i-1].getX()) ) {
				return 0;
			}
			
			if(punto.getY()> Math.min(poligono[i].getY(), poligono[i-1].getY()) &&
					punto.getY()<= Math.max(poligono[i].getY(), poligono[i-1].getY()) &&
					punto.getX() <= Math.max(poligono[i].getX(), poligono[i-1].getX())&&
					poligono[i].getY() != poligono[i-1].getY()) {
				
				double xCalculado = (double)(punto.getY()-poligono[i].getY())*
						(poligono[i-1].getX()-poligono[i].getX())/(poligono[i-1].getY()-poligono[i].getY()) +
						poligono[i].getX();
				
				if(xCalculado == punto.getX())
					return 0;
				if(poligono[i].getX() == poligono[i-1].getX() || punto.getX() < xCalculado) {
					intersecciones++;
				}	
			}
		}
		if(intersecciones % 2 != 0)
			return 1;
		else
			return -1;	
	}
	
	class Coordenada {
		private int x;
		private int y;
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
