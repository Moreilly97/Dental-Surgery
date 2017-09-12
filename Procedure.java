package dentalSurgery;

import java.io.Serializable;

public class Procedure implements Serializable{
	
	private static int maxProcedure;
	private int procNo;
	private String procName;
	private double procCost;
	
	public Procedure() {
	}

	public Procedure(String procName, double procCost) {
		maxProcedure++;
		this.procNo = maxProcedure;
		this.procName = procName;
		this.procCost = procCost;
	}
	
	


	public static int getMaxProcedure() {
		return maxProcedure;
	}

	public static void setMaxProcedure(int maxProcedure) {
		Procedure.maxProcedure = maxProcedure;
	}

	public int getProcNo() {
		return procNo;
	}

	public void setProcNo(int procNo) {
		this.procNo = procNo;
	}

	public String getProcName() {
		return procName;
	}

	public void setProcName(String procName) {
		this.procName = procName;
	}

	public double getProcCost() {
		return procCost;
	}

	public void setProcCost(double procCost) {
		this.procCost = procCost;
	}

	public String toString() {
		return ("Number: " + this.procNo + " Name: " + this.procName
				+ " Cost: " + this.procCost);
	}
	
}
