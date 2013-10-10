package soft;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TestNumber {
	int sumOfNum = 10; // ���-�� ������������ �����
	int infinum = 70; // ������� ������ ������������� ����� (������ �� �������
						// 0)
	int[] mas = new int[sumOfNum];
	String path = "B:/Poligon/TestNumber.txt";

	public String getPath() {
		return path;
	}

	public void SetPath(String path) {
		this.path = path.substring(0, path.length());
	}

	public int[] randomArray() { // ���������� ������ ������-��������� ����� �
									// ������� ���
		Random random = new Random();
		for (int i = 0; i < sumOfNum; i++) {
			mas[i] = random.nextInt(infinum);
		}

		return mas;
	}

	public boolean checkFile(String path) { // c������� � ��������� ������������
											// ������ � ����������
		try {
			Scanner reader = new Scanner(new File(path));

			int[] arr = new int[mas.length];
			for (int i = 0; i < mas.length; i++) {
				arr[i] = reader.nextInt();
			}
			reader.close();
			// System.out.println("arr = " + Arrays.toString(arr));
			// System.out.println("mas = " + Arrays.toString(mas));
			return Arrays.equals(arr, mas);

		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		return true;
	}

	public String toString() {
		return Arrays.toString(mas);
	}

	public void Write(String path) { // write mas in txt file
		try {
			PrintWriter fw = new PrintWriter(path);
			// fw.write(Arrays.toString(mas));

			for (int i = 0; i < mas.length; i++) {

				// String s = mas[i];
				// s = s + (char) mas[i] / 10;
				// s = s + (char) mas[i] % 10;
				fw.print(mas[i] + " ");
			}

			fw.close();

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		TestNumber test = new TestNumber();

		test.randomArray();
		test.Write(test.path);
		System.out.println("test = " + test.toString());
		System.out.println(test.checkFile(test.path));

		test.randomArray();
		test.Write(test.path);
		System.out.println("test1 = " + test.toString());
		System.out.println(test.checkFile(test.path));

	}

}