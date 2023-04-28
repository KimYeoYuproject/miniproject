package View.Testview;

import java.util.Scanner;

import Controller.OrderController;
import View.ClearConsole;
import View.MainView;

/**
 * @author miji
 * 주문하기 실행 테스트뷰
 */
public class TestYeo {
    public void view() {
    	
//    	OrderController order = new OrderController();
    	Scanner	sc = new Scanner(System.in);
    	
    	ClearConsole.clear();
    	MainView main = new MainView();
    	main.mainView();

//    	while (true) {
//    		System.out.println();
//	    	System.out.println("♥어서오세요~ 카페김여유 입니다^___^! 메뉴를 선택해주세요♥");
//	    	System.out.println("1. 주문하기");
//	    	System.out.println("2. 주문내역보기");
//	    	System.out.println("3. 회원정보");
//	    	System.out.println("4. 관리자메뉴");
//	    	System.out.println("4. 종료하기");
//	    	int menu = sc.nextInt();
//	    	
//	    	if(menu ==1) {
//	    		// 주문입력
//	    		while (true) {
//	    			System.out.println("주문을 시작합니다.");
//	    			order.insertOrder();
//	    			
//	    			System.out.println();
//	    			System.out.println("새로 주문하시겠습니까?  Y/N :  ");
//	    			char ys = sc.next().charAt(0);
//	    			if(ys == 'Y') {
//	    				continue;
//	    				
//	    			} else {
//	    				break;
//	    			}
//	    		}
//	    		
//	
//	        	
//	        	
//	    	} else if (menu ==2) {
//	    		System.out.println("===================================");
//	    		System.out.println("1. 주문자로 주문내역 보기");
//	    		System.out.println("2. 주문번호로 주문내역 보기");
//	    		System.out.println("3. 전체 주문내역 보기");
//	        	int select = sc.nextInt();
//	
//	        	switch(select) {
//	        	
//	        	case 1: 
//	        		order.searchOrderByOrderer();
//	        		System.out.println("주문자로 검색완료");
//	        		break;
//	        		
//		    	case 2: 
//		    		order.searchOrderByNo();
//		    		System.out.println("주문번호로 검색완료");
//		    		break;
//	        	
//	        
//		    	case 3: 
//		    		System.out.println("전체주문내역");
//		    		order.selectAll();
//		    		break;
//		    	} 
//	    		
//	        	
//		    } else if(menu ==4) {
//		    	break;
//		    		
//		    }
//
//	    	
//	    } 
 
    	
    	
    	
    }

 }

