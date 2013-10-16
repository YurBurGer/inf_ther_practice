package code_decode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import transmit.*;

public class Code_decode {
	//список массивов, каждый массив содержит утроенное двоичное представление координаты Y
	public static List<Integer []> listArrays1;
	//список массивов, каждый массив содержит утроенное двоичное представление координаты Х
	public static List<Integer []> listArrays2;
	//хранит десятичное представление  Y
	public static List<String> listY;
	//хранит десятичное представление  Х
	public static List<String> listX;
	//хранит все массивы из listArrays1 соединенные в один
	public static Integer [] array1;
	//хранит все массивы из listArrays2 соединенные в один
	public static Integer [] array2;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("TestNumber1.txt");
		//заполняет array1 и array2 утроенными 0 и 1
		format(file);
		//биполярное кодирование бит из array2
		Integer [] bipArrayX = toBipolar(array2);
		//биполярное кодирование бит из array1
		Integer [] bipArrayY = toBipolar(array1);
		//теперь ты портишь эти два массива
		Integer[] transm=Transmit.transmit(bipArrayY, Transmit.generateSignal(1, 0.9, bipArrayY));
		//а я их восстанавливаю и декодирую
		Integer [] fromBipArrayX = fromBipolar(bipArrayX);
		Integer [] fromBipArrayY = fromBipolar(transm);
		//иии...final.. декодируем обратно в десятичную c.c.
		Integer [] X = decode(fromBipArrayX,listArrays2);
		Integer [] Y = decode(fromBipArrayY,listArrays1);
		//иксы :
		for(Integer x : X) {
			System.out.print(x + " ");
		}
		System.out.println();
		//игрики :
		for(Integer y : Y) {
			System.out.print(y + " ");
		}
	}
	
	public static void format(File file) throws FileNotFoundException {
		parse(file);
		List<String> list1 = listY;
		List<String> list2 = listX;
		listArrays1 = new LinkedList<>();
		listArrays2 = new LinkedList<>();
		//каждую координату У поочередно переводят в двоичную с.с. утраивают и записывают в массив а сам массив в список массивов listArrays1
		for(String s : list1) {
			listArrays1.add(f(toBinary(s)));
		}
		//соединяет все массивы из списка в один большой массив array1
		array1 = toArray(listArrays1);
		//каждую координату X поочередно переводят в двоичную с.с. утраивают и записывают в массив а сам массив в список массивов listArrays2
		for(String s : list2) {
			listArrays2.add(f(toBinary(s)));
		}
		//соединяет все массивы из списка в один большой массив array1
		array2 = toArray(listArrays2);
	}
	//парсит входную строку файла и записывает X в listX Y в listY
	public static void parse(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		String s = sc.nextLine();
		listY = new LinkedList<>();
		listX = new LinkedList<>();
		String array [] = s.split(",");
		for(String z : array) {
			String sz = z.trim();
			String sub=sz.substring(sz.indexOf(" ")+1, sz.length());
			listY.add(sub);
			sub=sz.substring(0,sz.indexOf(" "));
			listX.add(sub);
		}
		sc.close();
	}
	//переводит число в двоичную с.с.
	public static String toBinary(String s) {
		return Integer.toBinaryString(new Integer(s));
	}
	//утраивает каждый бит(для помехоустойчивого кодирования) 
	public static Integer [] f(String s) {
		Integer array [] = new Integer[s.length()*3];
		int c = 0;
		for(int i = 0; i < array.length; i = i + 3) {
			int k = s.charAt(c);
			k = k % 2;
			for(int j = i; j < i +3; j++) {
				array[j] = k;
			}
			c++;
		}
		return array;
	}
	//соединяет в один массив все массивы из входного параметра list
	public static Integer [] toArray(List<Integer []> list) {
		Integer k = 0;
		for(Integer [] x : list) {
			k = k + x.length;
		}
		Integer array [] = new Integer[k];
		int i = 0;
		for(Integer [] x : list) {
			for(Integer y : x) {
				array[i] = y;
				i++;
			}
		}
		return array;
	}
	//метод, обратный методу f(), возвращает массив в исходное состояние
	public static Integer [] deformat(Integer [] array ) {
		Integer mas [] = new Integer[(array.length)/3];
		int k = 0;
		for(int i = 0; i < mas.length; i++) {
			int buf [] = new int[3];
			for(int j = 0; j < 3; j++) {
				buf[j] = array[k+j];
			}
			if(oneBigger(buf)) {
				mas[i] = 1;
			}
			else {
				mas[i] = 0;
			}
			k +=3;
		}
		return mas;
	}
	//метод выявляет, каких бит больше: 1 и 0. Используется при декодировании(так как каждый бит я утраивал)
	public static boolean oneBigger(int array []) {
		int k = 0;
		for(int x : array) {
			if(x == 1) {
				k++;
			}
		}
		if(k <= 1) {
			return false;
		}
		return true;
	}
	//принимает на вход первым параметром либо array1 либо array2 и второым соответсвенно либо listArrys1 либо listArrays2
	//списки массивов необходимы для того, чтобы контролировать границы в общем массиве(arra1 или array2)
	//сам метод возвращает десятичное представление координат 
	public static Integer [] decode(Integer [] array,List<Integer []> col) {
		List<Integer> list = new ArrayList<>();
		Iterator<Integer []> it = col.iterator();
		int count = 0;
		array = deformat(array);
		Integer mas [] = null;
		while(it.hasNext()) {
			mas = deformat(it.next());
			int k = mas.length; 
			Integer ar [] = new Integer[k];
			for(int i = 0; i < k; i++) {
				if(count != array.length) {
					ar[i] = array[count];
				}
				else {
					break;
				}
				count ++;
			}
			list.add(dit(ar));
		}
		Integer buf [] = new Integer[list.size()];
		int i = 0;
		for(Integer x : list) {
			buf [i] = x;
			i++;
		}
		return buf;
	}
	//переводит число из двоичной с.с. в десятичную
	public static int dit(Integer [] array) {
		int k = 0;
		int deg = 1;
		for(int i = array.length - 1; i >= 0; i--) {
			k = array[i]*deg + k;
			deg *= 2;
		}
		return k;
	}
	//возвращает массив чисел в биполряном представлении
	public static Integer [] toBipolar(Integer [] array) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == 1) {
				array[i] = 5;
			}
			else {
				array[i] = -5;
			}
		}
		return array;
	}
	//декодирует в бинарный вид из биполярного
	public static Integer [] fromBipolar(Integer [] array) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] >= 0) {
				array[i] = 1;
			}
			else {
				array[i] = 0;
			}
		}
		return array;
	}
}
