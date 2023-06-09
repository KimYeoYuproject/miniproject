package View;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Controller.MenuController;
import Model.MenuDTO;

/**
 * @author yoosc89
 */
public class CoffeMenuView {

    private MenuController menuController;

    private Scanner sc = new Scanner(System.in);

    public CoffeMenuView(MenuController menuController) {
        this.menuController = menuController;
    }

    public void addMenu() throws InputMismatchException {

        String name = null;
        String category = null;
        int price = 0;

        System.out.print("메뉴 이름 : ");
        name = sc.nextLine();

        System.out.print("메뉴 카테고리 : ");
        category = sc.nextLine();

        System.out.print("메뉴 가격 : ");
        price = sc.nextInt();

        sc.nextLine();

        if (this.menuController.saveByMenu(new MenuDTO(name, category, price, LocalDateTime.now()))) {
            System.out.println("메뉴 생성 성공");
        } else {
            System.out.println("같은 메뉴가 존재 합니다.");
        }
    }

    public void findByMenu() {

        String name = null;

        System.out.print("메뉴 이름 : ");
        name = sc.nextLine();
        System.out.println(name);

        MenuDTO menuDTO = this.menuController.findByMenu(name);

        if (menuDTO != null) {
            System.out.println(menuDTO);
        } else {
            System.out.println("메뉴를 찾을 수 없습니다.");
        }
    }

    public void findAllByMenu() {
        try {

            List<MenuDTO> menuList = this.menuController.findAllByMenu();
            System.out.println("===== 정렬 방법 선택 =====");
            System.out.println("1.메뉴이름(ASC)");
            System.out.println("2.메뉴이름(DESC)");
            System.out.println("3.카테고리(ASC)");
            System.out.println("4.카테고리(DESC)");
            System.out.println("5.가격(ASC)");
            System.out.println("6.가격(DESC)");
            System.out.println("9.종료");

            int number = sc.nextInt();
            sc.nextLine();

            switch (number) {
                case 1:
                    menuList.sort(Comparator.comparing(MenuDTO::getName));
                    break;
                case 2:
                    menuList.sort(Comparator.comparing(MenuDTO::getName).reversed());
                    break;
                case 3:
                    menuList.sort(Comparator.comparing(MenuDTO::getCategory));
                    break;
                case 4:
                    menuList.sort(Comparator.comparing(MenuDTO::getCategory).reversed());
                    break;
                case 5:
                    menuList.sort(Comparator.comparing(MenuDTO::getPrice));
                    break;
                case 6:
                    menuList.sort(Comparator.comparing(MenuDTO::getPrice).reversed());
                    break;
                case 9:
                    return;
                default:
                    System.out.println("업는 메뉴 입니다.");
                    break;

            }
            menuList.forEach(System.out::println);

        } catch (NullPointerException e) {
            System.out.println("메뉴가 없습니다.");
        }
    }

    public void deleteByMenu() {

        String name;

        System.out.print("메뉴 이름 : ");
        name = sc.nextLine();

        if (this.menuController.deleteBymenu(name)) {
            System.out.println(name + "을(를) 삭제 했습니다.");
        } else {
            System.out.println("해당 메뉴를 찾을 수 없습니다.");
        }
    }

    public void deleteAllbyMenu() {
        if (menuController.deleteAllBymenu()) {
            System.out.println("정상적으로 삭제 되었습니다.");
        } else {
            System.out.println("삭제할 메뉴가 없습니다.");
        }
    }

    public void findAllCategory() {
        this.menuController.findAllCategory().forEach(System.out::println);

    }

    public void modifyByMenu() throws InputMismatchException {

        List<MenuDTO> menuList = this.menuController.findAllByMenu().stream().toList();

        int number = 0;

        for (int i = 0; i < menuList.size(); i++) {
            System.out.printf("%2d, %s\n", i, menuList.get(i));
        }
        while (true) {
            try {
                System.out.print("수정하고자 하는 메뉴의 번호를 입력하세요 : ");
                number = sc.nextInt();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("숫자만 입력해야합니다. 다시 입력해주세요 : ");
            }
        }

        String name = null;
        String category = null;
        int price = 0;

        System.out.printf("메뉴 이름(변경 없으면 공란 엔터) (현재값 : %s) : ", menuList.get(number).getName());
        name = sc.nextLine();

        System.out.printf("메뉴 카테고리(변경 없으면 공란 엔터) (현재값 : %s) : ", menuList.get(number).getCategory());
        category = sc.nextLine();

        System.out.printf("메뉴 이름(수정 없으면 0 입력 후 엔터) (현재값 : %s) : ", menuList.get(number).getPrice());
        price = sc.nextInt();
        sc.nextLine();

        MenuDTO menuDTO = new MenuDTO()
                .setName(name == "" ? menuList.get(number).getName() : name)
                .setCategory(category == "" ? menuList.get(number).getCategory() : category)
                .setPrice(price == 0 ? menuList.get(number).getPrice() : price)
                .setCreateDate(menuList.get(number).getCreateDate());

        this.menuController.modifyByMenu(menuList.get(number), menuDTO);
    }

    public void categoryFindAllbyMenu() throws InputMismatchException {

        int number = 0;
        List<String> categoryList = new ArrayList<>(this.menuController.findAllCategory());
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println(i + ", " + categoryList.get(i));
        }
        System.out.print("검색할 카테고리 번호를 입력하세요 : ");
        number = sc.nextInt();
        sc.nextLine();

        ClearConsole.clear();

        List<MenuDTO> menuList = this.menuController
                .categoryFindAllbyMenu(categoryList.get(number))
                .stream().toList();

        menuList.forEach(System.out::println);

    }

    public void coffeMenu() throws InputMismatchException {
        int number = 0;
        label: while (true) {
            ClearConsole.clear();
            System.out.println("1. 메뉴 추가 ");
            System.out.println("2. 메뉴 조회");
            System.out.println("3. 메뉴 전체 조회");
            System.out.println("4. 메뉴 수정");
            System.out.println("5. 메뉴 삭제");
            System.out.println("6. 메뉴 모두 삭제");
            System.out.println("7. 메뉴 카테고리 조회");
            System.out.println("8. 카테고리별 메뉴 조회");
            System.out.println("9. 종료 ");
            System.out.println();
            System.out.print("사용할 메뉴 선택 : ");
            number = sc.nextInt();
            sc.nextLine();
            ClearConsole.clear();
            switch (number) {
                case 1:
                    addMenu();
                    break;
                case 2:
                    findByMenu();
                    break;
                case 3:
                    findAllByMenu();
                    break;
                case 4:
                    modifyByMenu();
                    break;
                case 5:
                    deleteByMenu();
                    break;
                case 6:
                    deleteAllbyMenu();
                    break;
                case 7:
                    findAllCategory();
                    break;
                case 8:
                    categoryFindAllbyMenu();
                    break;
                case 9:
                    break label;
                default:
                    System.out.println("없는 메뉴 입니다.");
                    break;

            }

        }
    }
}
