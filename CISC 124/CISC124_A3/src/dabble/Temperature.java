package dabble;

public class Temperature {
	private double celsiusValue;
	
	public Temperature(double c) {
		this.celsiusValue = c;
	}
	
	public double getCelsius() {
		return this.celsiusValue;
	}
	
	public double getFahrenheit() {
		return Temperature.CelsiusToFahrenheit(this.celsiusValue);
	}
	
	public static Temperature fromFahrenheit(double f) {
		double c = Temperature.FahrenheitToCelsius(f);
		return new Temperature(c);
	}
	
	public static double CelsiusToFahrenheit(double c) {
		return (c * 9.0 / 5.0) + 32.0;
	}
	
	public static double FahrenheitToCelsius(double f) {
		return (f - 32.0) * (5.0 / 9.0);
	}
	
	
	public static void main(String[] args) {
		Temperature temp1 = new Temperature(32);
		Temperature temp2 = Temperature.fromFahrenheit(85);
		
		System.out.println("temp1 celcius: " + temp1.getCelsius());
		System.out.println("temp1 fahrenheit: " + temp1.getFahrenheit());

		System.out.println("temp2 celcius: " + temp2.getCelsius());
		System.out.println("temp2 fahrenheit: " + temp2.getFahrenheit());
	}
}
