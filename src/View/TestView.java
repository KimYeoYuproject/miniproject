package View;

import java.util.Scanner;

import View.Testview.TestKim;
import View.Testview.TestYeo;
import View.Testview.TestYu;

public class TestView {

    Scanner sc = new Scanner(System.in);

    public void testView() {
        int number = 0;
        System.out.println("테스트 담당자 선택");
        System.out.println("1. 김민지");
        System.out.println("2. 여미지");
        System.out.println("3. 유승철");
        sc.nextInt();

        label: while (true) {
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
                default:
                    break label;
            }
        }
    }
}
