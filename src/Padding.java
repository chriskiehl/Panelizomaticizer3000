
public enum Padding {
	BODY (60), 
	PANEL (50);
	
	private double value; 
	
	private Padding(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}
	
}
