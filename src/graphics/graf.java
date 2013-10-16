package graphics;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.File;
import java.io.FileNotFoundException;

import test.TestNumber;
import transmit.Transmit;
import code_decode.Code_decode;

public class graf extends Frame {
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7446717466565270465L;
	int x; //- будущий икс
     int y; // будущий игрик.
       
       
      int xx = 100; // нулевая точка икса.
      int yy = 750; // нулевая точка игрика.

      
      // это все дерьмо для того чтобы вписать ИКС в график.
      // от тебя идет икс, заменяет мой икс + 100, (100 - это расстояние по оси икс до Ох по моему графику)
          public void setX(int x) {
            this.x = 50*x + 100;                 
        }
  
           public int getX() {
            return x;
        }
             // это все дерьмо для того чтобы вписать ИГШРИК в график.
      // от тебя идет икс, заменяет мой игрик + 750, (750 - это расстояние по оси игик до Оy по моему графику)  
          public void setY(int y) {
            this.y = 750-y*50-2;
        }
  
         public int  getY() {
            return y;
        }
                  
        
	public graf (String s) {
	super (s);
	setLayout(null);
 

   
       
       
        
    /*    Button bb5 = new Button ("обнов.");  // кнопка с надписью "надпись"
	bb5.setBounds(100,100,100,40); add (bb5);  // местоположение кнопки, ширина, высота
      */  

        
	Font f = new Font("Serif", Font.BOLD, 30);  //  Шрифт "Сериф" полужирный, размер -15
	setFont(f);	
	
        Label b1 = new Label ("Мы приняли на вход пары чисел:"); //  надпись.
	b1.setBounds (10,20,500,40); add(b1);    // место куда воткнуть надпись (х,y, ширина, высота); вставить "b1"

/*
        Label b2 = new Label ("Текст 2");	 //  надпись.
	b2.setBounds (10,830,200,40); add(b2);  // место куда воткнуть надпись (х,y, ширина, высота); вставить  "b2"
  */

        Label g1 = new Label ("Обновить пары чисел");	 //  надпись.
	g1.setBounds (680,40,300,40); add(g1);
	Button bb1 = new Button ("обнов."); // кнопка с надписью "надпись"
	bb1.setBounds(800,100,100,40); add (bb1); // место куда воткнуть кнопку (х,y, ширина, высота); вставить "bb1"
       
        Label g2 = new Label ("Сгенерировать новый график");	 //  надпись.
	g2.setBounds (570,150,450,40); add(g2);
	Button bb2 = new Button ("сген.");  // кнопка с надписью "надпись"
	bb2.setBounds(800,200,100,40); add (bb2); // место куда воткнуть кнопку (х,y, ширина, высота); вставить "bb2"
  
      final Label g4 = new Label ("0");	 //  надпись.
	g4.setBounds (700,600,110,40); add(g4);
	g4.setVisible(false);
        
		File file = new File("TestNumber1.txt");
		//заполняет array1 и array2 утроенными 0 и 1
		try {
			Code_decode.format(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String s1="";
		for(int i=0;i<Code_decode.listX.size();i++){
			String s2=String.format("(%s,%s)",Code_decode.listX.get(i),Code_decode.listY.get(i));
			s1=s1.concat(s2);
		}
        // в этом методе заложено поле на котором видны пары.
        final TextArea ta = new TextArea (s1, 1,50, TextArea.SCROLLBARS_NONE);
        ta.setEditable(false);  // false не дает вписывать свой хуев бред с клавы в проге
        ta.setBounds(40,110,400,80); add(ta); // местоположение 
        ta.repaint();
         //Этот метод вызывается в результате щелчка на кнопке "обнов."
       bb1.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent ae){
        	TestNumber test = new TestNumber();
     		test.SetPath("TestNumber1.txt");
     		test.randomArray();		
     		test.Write(test.getPath());
     		System.out.println("test = " + test.toString());
     		System.out.println(test.checkFile(test.getPath()));
     		File file = new File("TestNumber1.txt");
    		//заполняет array1 и array2 утроенными 0 и 1
    		try {
				Code_decode.format(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
     		String s1="";    		
     		for(int i=0;i<Code_decode.listX.size();i++){
    			String s2=String.format("(%s,%s)",Code_decode.listX.get(i),Code_decode.listY.get(i));
    			s1=s1.concat(s2);
    		}
    		ta.setText(s1);
    		ta.repaint();
    		Graphics g = getGraphics();
        	Graphics2D g2d = (Graphics2D)g;
        	g2d.clearRect(100, 200, 550, 550);
        	g2d.setColor(new Color(153,102,204));
        	g2d.fillRect(100, 200, 550, 550); 
        	g2d.draw (new Line2D.Double (100,200,100,900)); // вертикальная линия
            g2d.draw (new Line2D.Double (50,750,650,750));  // горизонтальная линия
            int p = 750;
            int p2 = 100;
            BasicStroke pen2 = new BasicStroke(5,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,30); // ширина пикс - 5.
            g2d.setStroke(pen2);
           g2d.setColor(Color.black);
           for (int i =0; i<11; i++) {
                g2d.drawString("" +i ,65, p);    // нумерация графика верт.
                 g2d.drawString(" " +i ,p2,785 );   // нумерация графика гор.
                p= p-50;  // т.е. p - двигается со скоростью 50 пикселов верх и рисует за собой короткую линию 9 раз.
                p2 = p2+50; // т.е. p2 - двигается со скоростью 50 пикселов вправо и рисует за собой короткую линию 9 раз.
                g2d.draw (new Line2D.Double (80,p,120,p)); // разметки вертикальной линии
                  g2d.draw (new Line2D.Double (p2,730,p2,775)); // разметки горизонтальной линии      
              }
        	    		
         }
    });
         
       // выпадающий список для хз чего то там.
       final Choice ch = new Choice();
       ch.add("sin");
       ch.add("cos");
       ch.setBounds(700,300,300,30); add(ch);      
       final TextArea ta3 = new TextArea ("0.1", 1,50, TextArea.SCROLLBARS_NONE); // та же хня, но с бин
       ta3.setBounds(700,400,250,50); add(ta3);
       // при каждом новом щелчке по клаве, 1 пара долна добавится.
       bb2.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
        	ta.repaint();
        	int c=ch.getSelectedIndex()+1;
        	File file = new File("TestNumber1.txt");
    		//заполняет array1 и array2 утроенными 0 и 1
    		try {
				Code_decode.format(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
    		//биполярное кодирование бит из array2
    		Integer [] bipArrayX = Code_decode.toBipolar(Code_decode.array2);
    		//биполярное кодирование бит из array1
    		Integer [] bipArrayY = Code_decode.toBipolar(Code_decode.array1);
    		//теперь ты портишь эти два массива
    		Integer[] transm=Transmit.transmit(bipArrayY, Transmit.generateSignal(c, Double.parseDouble(ta3.getText()), bipArrayY));
    		//а я их восстанавливаю и декодирую
    		Integer [] fromBipArrayX = Code_decode.fromBipolar(bipArrayX);
    		Integer [] fromBipArrayY = Code_decode.fromBipolar(transm);
    		//иии...final.. декодируем обратно в десятичную c.c.
    		Integer [] X = Code_decode.decode(fromBipArrayX,Code_decode.listArrays2);
    		Integer [] Y = Code_decode.decode(fromBipArrayY,Code_decode.listArrays1);
    		Graphics g = getGraphics();
        	Graphics2D g2d = (Graphics2D)g;
        	int col=Integer.parseInt(g4.getText());
        	switch(col){
        	case 0:
        		g2d.setColor(Color.yellow);
        		break;
        	case 1:
        		g2d.setColor(Color.red);
        		break;
        	case 2:
        		g2d.setColor(Color.green);
        		break;
        	}
        	col=(col+1)%3;
        	g4.setText(Integer.toString(col));
        	g4.repaint();
        	for(int i=0;i<X.length;i++){
	    		setX(X[i]);
	        	setY(Y[i]);     	
	            g2d.drawOval(getX(), getY(), 5, 5);
	            g2d.fillOval(getX(), getY(), 5, 5);
	        }
        	// сюда влетают пары и ИКС помещается в setX() а ИГРИК в setY();    
/**
    по идее здесь нужно вставить это
        Икс из той пары которая идет на вход помещается в setX(x) а игрик в setY(y);
      
      * а дальше рисуем овал (типа наша точка которую видно на координате)
      * g.setColor(Color.red); // красный цвет для нее
              g.fillOval(getX(), getY(), 5, 5); // нарисуем закрашенный овал, 5x5,красный (наша будущая точка на графике) 
  
  но я незнаю как переопределить граф. метод. т.е. сделать так чтобы при нажатии на точку овал Гетил значения.
  * **/
        	//g.fillOval(getX(), getY(), 5, 5);
        }
    });
         
       
     
       
       
       
       // параметры окошечка.
        
        setSize(1000,800);
        setVisible(true);
   
        }
        
        public void paint (Graphics gr) {
             Graphics2D g = (Graphics2D)gr;
             // Фон
             Color initColor = g.getColor(); // сохранили весь пздц который был до заливки
             g.setColor(new Color(153,102,204)); // выбрали цвет заливки (фиолетовый)
             g.fillRect(0,0,getSize().width-1,getSize().height-1); // закрасили им прямоугольник который шире и выше нашей проги на 1. кароче фон.
             g.setColor(initColor); // вернули краски, те что сохранили 2 строчки назад
      
             
             g.setColor(Color.orange);   // цвет поменяли на оранжевый
            BasicStroke pen1 = new BasicStroke(20,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,30); // выбрал прямоугольник, с учеченными краями, и с шириной пикс. - 20.
             BasicStroke pen2 = new BasicStroke(5,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,30); // ширина пикс - 5.
          
             g.setStroke(pen1); // пользуемся далее заготовкой pen 1. та что ширина пикс - 20.
            g.draw(new Rectangle2D.Double(40,100,250,70)); // нарисовали прямоугольник, он будет вокруг строчек с "парами" 
     
             //g.draw(new Rectangle2D.Double(38,890,250,70)); // нарисовали прямоугольник, он будет вокруг строчек с "010101"
          
        // ща будем рисовать график.    
        
             g.setColor(Color.black);  // цвет сменили на черный.
        g.setStroke(pen2);  // пользуемся далее заготовкой pen 1. та что ширина пикс - 5.
            g.draw (new Line2D.Double (100,200,100,900)); // вертикальная линия
            g.draw (new Line2D.Double (50,750,650,750));  // горизонтальная линия
            
            
            g.setColor(new Color(31,174,233)); // выбрали цвет Твиттерный. (голубоватый)
            BasicStroke pen3 = new BasicStroke(20,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,30);
             g.setStroke(pen3);
            //g.draw(new Rectangle2D.Double(799,80,100,60)); // обвели прямоугольник с этим цветом вокруг кнопки
            //g.draw(new Rectangle2D.Double(799,510,100,60)); // обвели прямоугольник с этим цветом вокруг кнопки
           
            int p = 750;
           int p2 = 100;
          
           g.setStroke(pen2);
          g.setColor(Color.black);
            
          // тут треш. рисуем меточки на графике.
                for (int i =0; i<11; i++) {
                  g.drawString("" +i ,65, p);    // нумерация графика верт.
                   g.drawString(" " +i ,p2,785 );   // нумерация графика гор.
                  p= p-50;  // т.е. p - двигается со скоростью 50 пикселов верх и рисует за собой короткую линию 9 раз.
                  p2 = p2+50; // т.е. p2 - двигается со скоростью 50 пикселов вправо и рисует за собой короткую линию 9 раз.
                  g.draw (new Line2D.Double (80,p,120,p)); // разметки вертикальной линии
                    g.draw (new Line2D.Double (p2,730,p2,775)); // разметки горизонтальной линии      
                }
           
               
            

      
      // ПОМЕХИ
        // по идее тут нужен такой же метод который гетил бы график, давал бы ИКС и ИГРИК овалу, а овал пропечатовался бы в нужном месте.
        /*
            g.draw (new Line2D.Double (900,800,600,800));  //центральная черная горизонтальная линия
            
     
            g.setColor(Color.red);
         g.draw (new Line2D.Double (900,700,600,700));  // горизонтальная линия по выше
 
           g.draw (new Line2D.Double (900,900,600,900));  // горизонтальная линия по ниже
           
           g.setColor(Color.black);
            g.drawString("0" ,900,800 );  // цифра - 0
            g.drawString("+5" ,900,700 ); // цифра - +5 
             g.drawString("-5" ,900,900 ); // цифра - -5
*/            }
        
          
          
           
            
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    
        
	/*public static void main (String [] args) {
	Frame f = new graf("proga");   // имя проги
       
        f.addWindowListener(new WindowAdapter() {					//  --/
            @Override
			public void windowClosing (WindowEvent ev) {    // закрытие программы если через Frame
				System.exit(0);								//  --/
			}
		});

	}*/
}