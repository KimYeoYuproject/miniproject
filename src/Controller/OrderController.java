package Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

import DTO.OrderDTO;
import Model.MenuVO;
import Model.OrderVO;

/**
 * @author miji
 * 저장된 주문정보 데이터를 입력받아 저장하고 금액을 출력해준다.
 * 저장된 주문내역을 출력해준다.
 */
public class OrderController {
	
	MenuController menulist = new MenuController();
	
	
	OrderDTO od =  new OrderDTO();
	Scanner sc = new Scanner(System.in);
	java.util.Date today = new java.util.Date();
	int no = 1;		// 주문번호는 1번부터 시작하여 새로운 주문건 마다 1씩 늘어나도록 하기
	Date date= today;			// 주문일자를 현재시간으로 저장해줌
	// private 해주기
	
	// 주문정보 입력받아 배열에 저장하기
	public void insertOrder() {
		SimpleDateFormat dt = new SimpleDateFormat("YYYY-MM-DD");
		dt.format(new Date());
		
		String orderer;
		String menu;
		int price;

		boolean coupon = false;
		
		// 주문번호, 주문자, 메뉴, 가격, 주문일자, 쿠폰사용여부를 입력받아 Order 객체 생성
		System.out.println("주문 도와드리겠습니다~");
		System.out.println(no);
		// 주문자는 전화번호를 입력받아 저장
		System.out.print("1. 전화번호를 입력해주세요 :  ");
		orderer = sc.next();
		
		// 쿠폰이 있는 경우 쿠폰 사용할지 묻기
		System.out.print("쿠폰을 사용하시겠습니까?  Y/N :  ");
		char ys = sc.next().charAt(0);
		if(ys == 'Y') {
			coupon = true;
			
		} else {
			coupon = false;
		}
			
		label:
		while(true) {
			// 사용자로부터 매뉴를 입력받음 메뉴가 여러개일경우 주문번호와 주문자가 같은 배열을 하나더 생성함
			System.out.print("2. 메뉴를 선택해주세요:  ");
			// 메뉴 DTO에서 메뉴를 받아와서 화면에 뿌려줌
			
			
			List<MenuVO> menulist2 = menulist.findAllByMenu();
			
			for(int i =0; i< menulist2.size(); i++) {
				System.out.println(menulist2.get(i).getName() +" " + menulist2.get(i).getPrice() + "원");
				
			}

			// 한글로 메뉴를 입력받음
			menu = sc.next();
			
			// 메뉴 가격을 불러와서 저장해줌
			price = 5000;
			
			OrderVO order = new OrderVO(no, orderer, menu, price, date, coupon);
			
			od.insertOrder(order);
			
			System.out.println("추가로 주문하시겠습니까? Y/N :  ");
			char ys2 = sc.next().charAt(0);
			if(ys2 == 'Y') {
				continue label;
			} else {
				// NO일때만 break하도록 수정하기
				
				// 주문완료 문구 출력
				List<OrderVO> orderList = od.searchOrderByNo(no);
							
				System.out.println("주문이 완료되었습니다! "+ orderList.get(0).getOrderer() +" 님이 주문하신 내역은 다음과 같습니다.");		
				for(OrderVO print : orderList) {
					if(order != null){
						System.out.println(print.Print());
					}
				}	// 널포인트익셉션 잡기	
				
				
				// 결제금액 출력
				double priceSum =0;
				for(OrderVO print : orderList) {
					if(order != null){
						priceSum += print.getPrice();
					}
				}
				
				// 쿠폰 사용여부에 따라 할인 적용
				if(coupon == true) {
					priceSum = priceSum * 0.9;
					System.out.println("※ 결제하실 금액은 10% 할인되어 " +(int) priceSum+ "원 입니다. ※" );
				} else {
					System.out.println("※ 결제하실 금액은 " +(int) priceSum+ "원 입니다. ※" );
				}
				no++;
				break label;
				
			}
			
		} 
		
	}
	
	
	
	//  주문 전체목록 불러오기
	public void selectAll() {
		
		List<OrderVO> orderList = od.selectAll();
		for(int i = 0; i < orderList.size(); i++) {
			System.out.println(i + "번주문 : " + orderList.get(i));
		}
	}
	
	
	// 주문번호를 선택해서 검색하기
	public void searchOrderByNo() {
		System.out.print("주문번호를 입력하세요");
		int no = sc.nextInt();
		
		List<OrderVO> orderList = od.searchOrderByNo(no);
				
		for(OrderVO order : orderList) {
			if(order != null){
				System.out.println(order);
			}
		}	// 널포인트익셉션 잡기	
	}
	
	
	
	// 고객번호를 선택해서 검색하기
	public void searchOrderByOrderer() {
		System.out.println("고객번호를 입력하세요");
		String orderer = sc.next();
		
		List<OrderVO> orderList = od.searchOrderByOrderer(orderer);
				
		for(OrderVO order : orderList) {
			if(order != null){
				System.out.println(order);
			}
		}	// 널포인트익셉션 잡기	
	}
	
	
	
	

}
