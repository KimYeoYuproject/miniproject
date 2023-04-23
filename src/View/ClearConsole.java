package View;

/*
 * 메뉴 간 이동시 화면 구분을 위한 빈 줄 생성기
 */
public class ClearConsole {
    public static void clear() {
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }
}
