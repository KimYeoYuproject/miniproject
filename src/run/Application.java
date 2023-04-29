package run;

import View.MainView;

public class Application {
	public static void main(String[] args) {
		try {
			new MainView().mainView();
		} catch (Exception e) {
			System.out.println("오류가 발생되어 프로그램을 종료합니다.");
		}

	}

}
