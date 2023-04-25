package View.Testview;

import java.util.Scanner;

import Controller.OrderController;
import View.ClearConsole;

/**
 * @author miji
 * 주문하기 실행 테스트뷰
 */
public class TestYeo {
    public void view() {
    	
    	OrderController order = new OrderController();
    	
    	Scanner	sc = new Scanner(System.in);
    	
    	ClearConsole.clear();

//    	System.out.println("2. 메뉴를 선택해주세요");	// MenuDTO에서 정보를 받아 화면에 출력해줌, 회원인 경우 추천메뉴 4가지 보여줌(중복 제거)
//    	
//    	System.out.println("더 주문하시겠습니까? Y/N");	// 주문내역 더 받을지 묻기 Y>주문메뉴 추가, N>다음으로 진행
//    	
//    	System.out.println("메뉴 선택이 완료되었습니다. 주문하신 메뉴는 00 이고 총 주문금액은 00 입니다."); // 주문 내역과 금액 출력
//    	
//    	// 쿠폰이 0개인 경우 (신규회원인 경우)
//    	System.out.println("결제하시겠습니까? Y/N"); // Y> 결제완료문구 출력, N>초기메뉴 돌아감
//    	
//    	// 쿠폰이 0개가 아닌경우 (기존회원인 경우)
//    	System.out.println("보유하신 쿠폰은 00개 입니다. 쿠폰을 사용하시겠습니까? Y/N"); // 회원인 경우만 쿠폰 갯수와 쿠폰사용 여부 묻기. Y>전체금액의 10퍼 할인과 쿠폰 -1, N>정상금액 
//    	System.out.println("쿠폰사용으로 할인되었습니다. 결제하실 금액은 00원 입니다. 결제하시겠습니까? Y/N"); // Y> 결제완료문구 출력, N>초기메뉴 돌아감
//    	
//    	System.out.println("주문번호: 000  주문이 완료 되었습니다. 감사합니다.");
    	

    	
    	while (true) {
    		System.out.println();
	    	System.out.println("♥어서오세요~ 카페김여유 입니다^___^! 메뉴를 선택해주세요♥"); // 초기메뉴...
	    	System.out.println("1. 주문하기");
	    	System.out.println("2. 주문내역보기");
	    	System.out.println("3. 관리자메뉴");
	    	System.out.println("4. 종료하기");
	    	int menu = sc.nextInt();
	    	
	    	if(menu ==1) {
	    		// 주문입력
	    		while (true) {
	    			System.out.println("주문을 시작합니다.");
	    			order.insertOrder();
	    			
	    			System.out.println();
	    			System.out.println("새로 주문하시겠습니까?");
	    			char ys = sc.next().charAt(0);
	    			if(ys == 'Y') {
	    				continue;
	    				
	    			} else {
	    				break;
	    			}
	    		}
	    		
	
	        	
	        	
	    	} else if (menu ==2) {
	    		System.out.println("===================================");
	    		System.out.println("1. 주문자로 주문내역 보기");
	    		System.out.println("2. 주문번호로 주문내역 보기");
	    		System.out.println("3. 전체 주문내역 보기");
	        	int select = sc.nextInt();
	
	        	switch(select) {
	        	
	        	case 1: 
	        		order.searchOrderByOrderer();
	        		System.out.println("주문자로 검색완료");
	        		break;
	        		
		    	case 2: 
		    		order.searchOrderByNo();
		    		System.out.println("주문번호로 검색완료");
		    		break;
	        	
	        
		    	case 3: 
		    		System.out.println("전체주문내역");
		    		order.selectAll();
		    		break;
		    	} 
	    		
	        	
		    } else if(menu ==4) {
		    	break;
		    		
		    }

	    	
	    } 
 
    	
    	
    	
    }

 }

