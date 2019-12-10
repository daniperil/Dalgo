import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

//Contraejemplo maximal

//El objetivo de este problema es buscar el mayor n�mero de individuos que contradicen la afirmaci�n.


//Tama�o del problema: N
//Condiciones de los casos de prueba: 1<=N<=10^4

public class ProblemaB {

	//La entrada contiene varios casos de prueba. (Paquete)
	//Cada caso de prueba comienza con una l�nea que contiene un entero positivo N.
	//En un paquete hay poder distinguier entre los distintos casos de prueba.


	//La clase main es la que se encarga de leer los datos y llamar los otros m�todos
	public static void main(String[] args) 
	{ 
		//		//Se leen los elementos del file en donde se encuentran los datos de las personas
		//		List l = readFileInList("C:\\Users\\Daniel Perilla\\Downloads\\Dalgo\\Dalgo\\proyectodalgo\\src\\tests\\Test1B"); 
		//		ArrayList<Integer> listas = new ArrayList<Integer>();
		//		Iterator<String> itr = l.iterator(); 



		List<String> list = Arrays.asList("-1", "2", "3", "4", "5");
		List<Integer> listas = transform(list, Integer::parseInt);

		for (int i = 0; i < listas.size(); i++)
		{
			System.out.println(listas.get(i));
		}

		List<Integer> respuesta = evaluar(listas);

		System.out.println("El n�mero de mayor de contra ejemplos es:"+ totalContradiciones(respuesta));
		
		
		//		while (itr.hasNext())
		//			System.out.println(itr.next()); 
	}


//	//M�todo que se encarga de leer los elementos del file
//	public static List<String> readFileInList(String fileName) 
//	{ 
//		List<String> lines = Collections.emptyList(); 
//		try
//		{ 
//			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8); 
//		} 
//		catch (IOException e) 
//		{ 
//			e.printStackTrace(); 
//		} 
//		return lines; 
//	} 

	
	//Este es el m�todo principal, este m�todo es el que se encarga de separar las listas 
	//en listas peque�as y de comparar los tama�os de los contraejemplos para dar el resultado final. 
	//La primer parte de separaci�n lo hace sabiendo que al prinicipio de cada lista 
	//Hay un n�mero de 1 a 9, por lo cu�l se sabe que ah� comieza una lista nueva.
	//Y que termina y que comienza otra nueva cuando aparece otra lista cuando aparece otra vez
	//Un n�mero del 1 al 9.
	//La segunda parte la de comparar los tama�os, lo hace mediante la ayuda de otro metodo
	//Llamado total contradicciones que le ayuda a identificar el n�mero de contradicciones en 
	//Una lista. Dato que luego utiliza para mirar cu�l seria la lista de resultado final. 
	private static List<Integer> evaluar(List<Integer> listas)
	{
		List<Integer> copia = listas;
		int contador = 0;

		int polo = 0;

		List<Integer> eval1 = new ArrayList<Integer>();
		List<Integer> eval2 = new ArrayList<Integer>();
		List<Integer> resp = new ArrayList<Integer>();

		while(copia.size() != 0) {
			for (int i = 0; contador == 0; i++) 
			{
				if(estaEnRango(copia.get(i)))
				{
					copia.remove(i);
					polo+=1;
					continue;
				}
				if(polo==1)
				{
					eval1.add(copia.get(i));	
					copia.remove(i);
				}
				if(polo==2)
				{
					eval2.add(copia.get(i));
					copia.remove(i);
				}
				if(polo==3)
				{
					if(totalContradiciones(eval1)>=totalContradiciones(eval2))
					{
						eval2.clear();
						eval2.add(copia.get(i));
						copia.remove(i);
						polo -=1;
					}
					else
					{
						eval1.clear();
						eval1.add(copia.get(i));
						copia.remove(i);
						polo -=2;
					}
				}
			}
		}
		
		if(totalContradiciones(eval1)>=totalContradiciones(eval2))
		{
			resp = eval1;
		}
		else
		{
			resp = eval2;
		}
		return resp;

	}

	//Con este m�todo se quiere evaluar el n�mero de elementos 
	//De la lista tal que se cumpla la posible falsedad de la afirmaci�n
	//Donde la medici�n de A es creciente y la de B es decreciente.
	//En este caso lo que se hizo fue declarar A y B y se metieron
	//Dentro de un for en donde cada letra tomaba el valor respectivo
	//Seg�n el orden de la lista. Estas se comparaban no con el siguiente,
	//Sino con el siguiente al siguiente. Para poder determinar si se manten�a el patr�n.
	//En el caso de que ambas condiciones se mantuvieran se dice que se aumenta un d�a a la respuesta.
	
	private static int totalContradiciones(List<Integer> eval)
	{
		int resp = 0;
		int A = 0;
		int B = 0;
		for (int i = 0; i < eval.size(); i=i+4) 
		{
			A=eval.get(i);
			B=eval.get(i+1);
			if(A<eval.get(i+2) && B>eval.get(i+3))
			{
				resp++;
			}
			
		}
		return resp;
	}


	// Generic function to convert List of String to List of Integer
	public static <T, U> List<U> transform(List<T> list, Function<T, U> function)
	{
		return list.stream()
				.map(function)
				.collect(Collectors.toList());
	}

	public static boolean estaEnRango(int n)
	{
		boolean resp = true;
		if(n == 1) {resp = false;}
		else if(n ==2) {resp = false;}
		else if(n ==3) {resp = false;}
		else if(n ==4) {resp = false;}
		else if(n ==5) {resp = false;}
		else if(n ==6) {resp = false;}
		else if(n ==7) {resp = false;}
		else if(n ==8) {resp = false;}
		else if(n ==9) {resp = false;}		
		return resp;
	}


}
