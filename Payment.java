package dentalSurgery;
import java.time.LocalDate;
import java.util.Date;
public class Payment {

	private static int highestPaymentNo = 0;
	private int paymentNo;
	private double paymentAmt;
	private LocalDate paymentDate;
	private boolean isPaid;
	
	public Payment() {
	}

	public Payment(double paymentAmt,LocalDate paymentDate) {
		highestPaymentNo++;
		setPaymentNo(highestPaymentNo);
		setPaymentDate(paymentDate);
		setPaid(true);
		this.paymentAmt = paymentAmt;
	}

	public static int getHighestPaymentNo() {
		return highestPaymentNo;
	}

	public static void setHighestPaymentNo(int highestPaymentNo) {
		Payment.highestPaymentNo = highestPaymentNo;
	}

	public int getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}

	public double getPaymentAmt() {
		return paymentAmt;
	}

	public void setPaymentAmt(double paymentAmt) {
		this.paymentAmt = paymentAmt;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate2) {
		this.paymentDate = paymentDate2;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
	
	public String toString() {
		return ("Number: " + this.paymentNo + " Amount: " + this.paymentAmt + " Date: " + this.paymentDate + " Paid: " + this.isPaid);
	}
	
}
