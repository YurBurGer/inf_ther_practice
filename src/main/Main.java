/**
 * @author Yuriy Gerasimov
 */
package main;

import graphics.graf;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

/**
 * @author Yuriy Gerasimov
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {

		Frame f = new graf("proga");   // имя проги
	    f.addWindowListener(new WindowAdapter() {					//  --/
            @Override
			public void windowClosing (WindowEvent ev) {    // закрытие программы если через Frame
				System.exit(0);								//  --/
			}
		});
	}

}
