package Algorithms;
/**
 * this interface represent Range
 * @author YuvalAmar and DvirHacohen
 *
 */
public interface RangeIN {
	/**
	 * 
	 * @return the distance 
	 */
	public double distance();
	/**
	 * 
	 * @param x given x
	 * @return the relation in the range
	 */
	public double relation(double x);
	/**
	 * 
	 * @param the relation
	 * @return the value of the relation
	 */
	public double getval(double relation);
	/**
	 * 
	 * @param number
	 * @return if it is in the range
	 */
	boolean isIn(double number);
}
