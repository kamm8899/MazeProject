
public class PairInt {
	
	private int x;
	private int y;

	public PairInt(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y =y;
	}
//x coordinate
	public int getX() {
		return x;
		
	}
	//y coordinate 
	public int getY() {
		return y;
		
}
	//set x 
	public void setX(int x ) {
		this.x= x;
		
	}
	
	//set y 
	public void setY(int y) {
		this.y=y;
	}
	public boolean equals(PairInt p) {
		if(p.getX()==x && p.getY()==y){
			return true;
		}
		return false;
	}
	//String to String method 
	public String toString() {
		String a = "(" + x +", " + y +")";
		return a;
	}
	//copy of pairInt object
	public PairInt copy() {
		PairInt copy = new PairInt(x,y);
		return copy;
	}
}
