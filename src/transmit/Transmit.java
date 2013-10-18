package transmit;
import java.util.LinkedList;


public class Transmit {
	//генерируем помеху из +-5 вольтового сигнала
	public static LinkedList<Double> generateSignal(int type,double mul,Integer[] param){
		LinkedList<Double> res=new LinkedList<>();
		switch (type){
		case 1:
			for(int i=0;i<param.length;i++){
				res.addLast(10*Math.sin(i)*mul);
			}
			break;
		case 2:
			for(int i=0;i<param.length;i++){
				res.addLast(10*Math.cos(i)*mul);
			}
			break;
		default:
			for(int i=0;i<param.length;i++){
				res.addLast(0.0*i);
			}
		}		
		return res;
	}
	//добавляем к основному сигналу помеху
	//округляя в каку-либо сторону.
	public static Integer[] transmit(Integer[] main,LinkedList<Double> add){
		LinkedList<Integer> res=new LinkedList<>();
		for(int i=0;i<main.length;i++){
			double result=main[i]+add.get(i);
			if(Math.abs(result)-0.5<=0.0001){
				res.add(0);
			}
			else{
				if(result>0){
					res.add(5);
				}
				else{
					res.add(-5);
				}				
			}
		}
		Integer[] a=res.toArray(new Integer[res.size()]);
		return a; 
	}
}
