package Model;

import java.time.LocalDateTime;

/**
 * @author miji
 * 주문내역 정보를 담을 객체 생성
 */
public class OderVO {
	
	private int no;					// 주문번호
	private int oderer;				// 주문자
	private String menu;			// 메뉴
	private int price;				// 가격
	private LocalDateTime  date;	// 주문일자
	private boolean	coupon;			// 쿠폰사용여부
	
	
	// toString 생성
	@Override
	public String toString() {
		return "OderVO [no=" + no + ", oderer=" + oderer + ", menu=" + menu + ", price=" + price + ", date=" + date
				+ ", coupon=" + coupon + "]";
	}

	// 기본생성자
	public OderVO() { }
	
	// 모든객체 초기화 가능한 생성자
	public OderVO(int no, int oderer, String menu, int price, LocalDateTime date, boolean coupon) {
		super();
		this.no = no;
		this.oderer = oderer;
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

	public int getOderer() {
		return oderer;
	}

	public void setOderer(int oderer) {
		this.oderer = oderer;
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public boolean isCoupon() {
		return coupon;
	}

	public void setCoupon(boolean coupon) {
		this.coupon = coupon;
	}
	

}
