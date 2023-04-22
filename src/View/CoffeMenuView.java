package View;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Controller.MenuController;
import Model.MenuVO;

public class CoffeMenuView {
    private MenuController menuController;

    private Scanner sc = new Scanner(System.in);

    public CoffeMenuView(MenuController menuController) {
        this.menuController = menuController;
    }

    public void addMenu() {

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

        boolean result = menuController.saveByMenu(new MenuVO(name, category, price, new Date()));

        if (result) {
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
        MenuVO menuVO = menuController.findByMenu(name).get();
        if (menuVO != null) {
            System.out.println(menuVO);
        } else {
            System.out.println("메뉴를 찾을 수 없습니다.");
        }
    }

    public void findAllByMenu() {
        try {
            for (MenuVO m : menuController.findAllByMenu().get()) {
                System.out.println(m);
            }
        } catch (NullPointerException e) {
            System.out.println("메뉴가 없습니다.");
        }
    }

    public void deleteByMenu() {
        String name;
        System.out.print("메뉴 이름 : ");
        name = sc.nextLine();
        if (menuController.deleteBymenu(name)) {
            System.out.println(name + "을(를) 삭제 했습니다.");
        } else {
            System.out.println("해당 메뉴를 찾을 수 없습니다.");
        }
    }

    public void findAllCategory() {
        for (String s : menuController.findAllCategory().get()) {
            System.out.println(s);
        }
        System.out.println(menuController.findAllCategory().get());
    }

    public void modifyByMenu() {
        List<MenuVO> menuList = menuController.findAllByMenu().get();
        int number = 0;
        for (int i = 0; i < menuList.size(); i++) {
            System.out.printf("%2d %s\n", i, menuList.get(i));
        }
        System.out.print("수정하고자 하는 메뉴의 번호를 입력하세요 : ");
        number = sc.nextInt();
        sc.nextLine();

        String name = null;
        String category = null;
        int price = 0;

        System.out.println("변경이 없으면 공란 엔터");

        System.out.printf("메뉴 이름(변경 없으면 공란) (%s) : ", menuList.get(number).getName());
        name = sc.nextLine();
        menuList.get(number).setName(name == null ? menuList.get(number).getName() : name);

        System.out.printf("메뉴 카테고리(변경 없으면 공란) (%s) : ", menuList.get(number).getCategory());
        category = sc.nextLine();
        menuList.get(number).setCategory(category == null ? menuList.get(number).getCategory() : category);

        System.out.printf("메뉴 이름(수정 없으면 0입력) (%s) : ", menuList.get(number).getPrice());
        price = sc.nextInt();
        sc.nextLine();
        menuList.get(number).setPrice(price == 0 ? menuList.get(number).getPrice() : price);

        menuController.modifyByMenu(menuList.get(number));

    }

    public void coffeMenu() {
        int number = 0;
        label: while (true) {
            System.out.println("1. 메뉴 추가 ");
            System.out.println("2. 메뉴 조회");
            System.out.println("3. 메뉴 전체 조회");
            System.out.println("4. 메뉴 수정");
            System.out.println("5. 메뉴 삭제");
            System.out.println("6. 메뉴 카테고리 조회");
            System.out.println("9.종료 ");
            System.out.print("사용할 메뉴 선택 : ");
            number = sc.nextInt();
            sc.nextLine();
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
                    findAllCategory();
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
