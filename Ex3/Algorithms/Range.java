package Algorithms;


public class Range implements RangeIN {
	double min;
	double max;
	double relation;

	public Range(double min, double max) {
		this.min=min;
		this.max=max;
	}


	@Override
	public double distance() {
		return max-min;

	}

	@Override
	public double relation (double x) {
		relation= (x-min)/distance();
		return relation;
	}

	@Override
	public double getval(double relation) {

		return min + relation*distance();
	}


	@Override
	public boolean isIn(Object arg) {
		// TODO Auto-generated method stub
		return false;
	}


}
