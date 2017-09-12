package dentalSurgery;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
public class Invoice implements Serializable{

	private int invoiceNo;
	private double invoiceAmt;
	private LocalDate invoiceDate;
	private boolean isPaid;
	private ArrayList<Procedure> in_procList;
	private ArrayList<Payment> in_paymentList;
	public double procCost;
	
	public Invoice(LocalDate invoiceDate, boolean isPaid, double invoiceAmt, Payment a, Procedure b) {
		invoiceNo++;
		this.invoiceDate = invoiceDate ;
		this.isPaid = isPaid;
		this.invoiceAmt = invoiceAmt;
		 in_paymentList = new ArrayList<Payment>();
		 in_procList = new ArrayList<Procedure>();
	}
	
	public double getTotalCost() {
		double totalCost = 0;
		for (Procedure current : in_procList) {
			totalCost += current.getProcCost();
		}
		return totalCost;
	}
	
	public double getAmountPaid() {
		double amountPaid = 0;
		for (Payment current : in_paymentList) {
			amountPaid += current.getPaymentAmt();
		}
		return amountPaid;
	}
	
	public double getAmountOwed() {
		double amountOwed = 0;
		amountOwed = getTotalCost() - getAmountPaid();
		return amountOwed;
	}
	
	public int getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public double getInvoiceAmt() {
		return invoiceAmt;
	}
	public void setInvoiceAmt(double invoiceAmt) {
		this.invoiceAmt = invoiceAmt;
	}
	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public boolean isPaid() {
		return isPaid;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	public ArrayList<Procedure> getIn_procList() {
		return in_procList;
	}
	public void setIn_procList(ArrayList<Procedure> in_procList) {
		this.in_procList = in_procList;
	}
	public ArrayList<Payment> getIn_paymentList() {
		return in_paymentList;
	}
	public void setIn_paymentList(ArrayList<Payment> in_paymentList) {
		this.in_paymentList = in_paymentList;
	}
	
	public String toString() {
		return "Invoice [invoiceNo= " + invoiceNo + ", invoiceAmt= " + invoiceAmt + ", invoiceDate= " + invoiceDate
				+ ", isPaid= " + isPaid + ", in_procList= " + in_procList + ", in_paymentList= " + in_paymentList + "]";
	}






}
