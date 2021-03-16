package dabble;

public class Point2 {
    private double x;
    private double y;

    public Point2(double x, double y) {
    	this.x = x;
    	this.y = y;
    }
    
    public double getX() {
    	return this.x;
    }
    
    public double getY() {
    	return this.y;
    }
    
    public static double manhattan(Point2 p) {
    	
    	double x = Math.abs(p.x);
    	double y = Math.abs(p.y);
    	return x + y;
    }
    
    public static void main(String[] args) {
		Point2 p = new Point2(-1.5, 3);
		
		System.out.println(Point2.manhattan(p));
	}
    // constructors not shown

}
