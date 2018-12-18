package Algorithms;


public class Range implements RangeIN {
	double min;
	double max;
	double relation;

	public Range(double min, double max) {
		this.min=min;
		this.max=max;
	}
	
	public Range(int min, int max) {
		this.min=min;
		this.max=max;
	}
	
	

	@Override
	public double distance() {
		return max-min;

	}

	public double getMin() {
		return min;
	}


	public double getMax() {
		return max;
	}


	public double getRelation() {
		return relation;
	}


	@Override
	public double relation (double x) {
		relation = (x-min)/distance();
		return relation;
	}

	@Override
	public double  getval(double relation) {

		return (min + relation*distance());
	}
	public int  getvalI(double relation) {

		return (int) (min + relation*distance());
	}


	@Override
	public boolean isIn(Object arg) {
		// TODO Auto-generated method stub
		return false;
	}


}
