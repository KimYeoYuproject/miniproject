package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author miji
 * 주문내역 정보를 담을 객체 생성
 */
public class OrderVO {
	
	private int no;					// 주문번호
	private String orderer;			// 주문자
	private String menu;			// 메뉴
	private int price;				// 가격
	private Date  date;	// 주문일자
	private boolean	coupon;			// 쿠폰사용여부
	SimpleDateFormat dt = new SimpleDateFormat("YYYY-MM-d");
	
	
	// toString 생성
	@Override
	public String toString() {
		return "[주문번호 :" + no + ", 주문자: " + orderer + ", 메뉴: " + menu + ", 가격: " + price + "원" +", 주문일자: " + dt.format(date)
				+ ", 쿠폰사용: " + coupon + "]";
	}
	
	public String Print() {
		String couponYn = null;
		if(coupon == true) {
			couponYn = "사용함";
		} else {
			couponYn = "사용안함";
		}
		
		return " 메뉴: " + menu + ", 가격: " + price + "원" + ", 쿠폰사용: " + couponYn ;
	}
	

	// 기본생성자
	public OrderVO() { }
	
	// 모든객체 초기화 가능한 생성자
	public OrderVO(int no, String orderer, String menu, int price, Date date, boolean coupon) {
		super();
		this.no = no;
		this.orderer = orderer;
		this.menu = menu;
		this.price = price;
		this.date = date;
		this.coupon = coupon;
	}
	
	
	// getter / setter 생성
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getOrderer() {
		return orderer;
	}

	public void setOrderer(String oderer) {
		this.orderer = oderer;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isCoupon() {
		return coupon;
	}

	public void setCoupon(boolean coupon) {
		this.coupon = coupon;
	}
	

}
