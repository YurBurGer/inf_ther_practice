package transmit;
import java.util.LinkedList;


public class Transmit {
	public static LinkedList<Double> generateSignal(int type,int mul,LinkedList<Double> param){
		LinkedList<Double> res=new LinkedList<>();
		switch (type){
		case 1:
			for(Double d:param){
				res.addLast(5*Math.sin(d)*mul);
			}
			break;
		default:
			for(Double d:param){
				res.addLast(0.0*d);
			}
		}		
		return res;
	}
	public static LinkedList<Integer> transmit(LinkedList<Integer> main,LinkedList<Double> add){
		LinkedList<Integer> res=new LinkedList<>();
		for(int i=0;i<main.size();i++){
			double result=main.get(i)+add.get(i);
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
		return res;
	}
}
