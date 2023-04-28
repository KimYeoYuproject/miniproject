package View;

import java.util.Scanner;

import Controller.OrderController;
import DTO.OrderDTO;


public class MainView {
	private OrderDTO orderDto = new OrderDTO();
	private OrderController order = new OrderController(orderDto);
	private AdminOrderView admorder = new AdminOrderView(orderDto);
	
	private Scanner	sc = new Scanner(System.in);
	
	public void mainView() {
		
		mainlabel:
		while (true) {
			ClearConsole.clear();
			System.out.println();
	    	System.out.println("♥어서오세요~ 카페김여유 입니다^___^! 메뉴를 선택해주세요♥");
	    	System.out.println("1. 주문하기");
	    	System.out.println("2. 주문내역보기");
	    	System.out.println("3. 회원정보");
	    	System.out.println("4. 관리자메뉴");
	    	System.out.println("5. 종료하기");
	    	int menu = sc.nextInt();
	    	
	    	
	    	switch (menu) {
			case 1: 
				label:
				while (true) {
					ClearConsole.clear();
	    			System.out.println("주문을 시작합니다.");
	    			order.insertOrder();
	    			
	    			System.out.println();
	    			System.out.println("새로 주문하시겠습니까?  Y/N :  ");
	    			char ys = sc.next().charAt(0);
	    			if(ys == 'Y') {
	    				continue label;
	    				
	    			} else {
	    				break;
	    			}
				}
				break;
			case 2: 
				admorder.adminOrderView();
				break;
			case 3: 
				break;
			case 4: 
				break;
			case 5: 
				System.out.println("시스템을 종료합니다.");
				break mainlabel;

			default:
				System.out.println("잘못된 번호입니다. 다시선택해주세요.");
				
			}
	    	 
	    	 
	    	 
	    	 
	    } 
	}
	
}
