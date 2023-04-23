package View;

import java.util.Scanner;

import View.Testview.TestKim;
import View.Testview.TestYeo;
import View.Testview.TestYu;

public class TestView {

    Scanner sc = new Scanner(System.in);

    public void testView() {
        ClearConsole.clear();
        int number = 0;
        label: while (true) {
            ClearConsole.clear();
            System.out.println("테스트 담당자 선택");
            System.out.println("1. 김민지");
            System.out.println("2. 여미지");
            System.out.println("3. 유승철");
            System.out.println("4. 종료 하기");
            System.out.print("번호 입력 : ");
            number = sc.nextInt();
            switch (number) {
                case 1:
                    new TestKim().view();
                    break;
                case 2:
                    new TestYeo().view();
                    break;
                case 3:
                    new TestYu().view();
                    break;
                case 4:
                    break label;
                default:
                    System.out.println("다시 입력하세요");
                    break;
            }
        }
    }
}
