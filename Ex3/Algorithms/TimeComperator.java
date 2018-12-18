package Algorithms;

import java.util.Comparator;

public class TimeComperator implements Comparator<NextStep>{

	@Override
	public int compare(NextStep next1, NextStep next2) {
		if (next1.getTime()<next2.getTime()) 
			return -1;
		else if (next1.getTime()>next2.getTime()) 
			return 1;
		else return 0;
	}

}
