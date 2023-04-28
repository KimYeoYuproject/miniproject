package View;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import Controller.UserController;
import Model.UserVO;

public class UserView {
    private UserController userController;

    Scanner sc = new Scanner(System.in);

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void findByUser() {
        System.out.println("======== 회원 조회 =======");
        System.out.print("조회한 회원의 연락처를 입력하하세요 : ");
        String phoneNumber = sc.nextLine();
        UserVO userVO = userController.getUser(phoneNumber);
        System.out.println(userVO);
    }

    public void findAllByUser() {
        System.out.println("======== 전체 회원 조회 =======");
        userController.findAllByUser().stream().forEach(x -> {
            System.out.println("연락처 : " + x.getPhone() + ", 이름 : " + x.getName() + ", 생성날짜 : " + x.getCreateDate());
        });
        ;
    }

    public void addUser() {
        System.out.println("====== 회원 추가 ======");

        System.out.print("연락처 : ");
        String phoneNumber = sc.nextLine();

        System.out.print("회원 이름 : ");
        String name = sc.nextLine();

        userController.addUser(new UserVO(phoneNumber, name, LocalDate.now()));
        System.out.println("회원 추가 완료했습니다.");
    }

    public void modifyByUser() {

        System.out.print("수정할 계정(연락처)를 입력하세요 : ");
        String phoneNumber = sc.nextLine();
        UserVO userVO = userController.getUser(phoneNumber);
        UserVO updateUserVO = userController.getUser(phoneNumber);

        while (true) {
            System.out.println("====== 회원 정보 수정 ======");
            System.out.println("1. " + userVO.getName());
            System.out.println("2. " + userVO.getPhone());
            System.out.println("9. 종료 ");

            System.out.println("수정할 항목을 선택하세요");
            int number = sc.nextInt();

            switch (number) {
                case 1:
                    System.out.print("현재값(" + userVO.getName() + ") 변경 : ");
                    String updateName = sc.nextLine();
                    updateUserVO.setName(updateName);
                    break;
                case 2:
                    System.out.print("현재값(" + userVO.getPhone() + ") 변경 : ");
                    String updatePhoneNuber = sc.nextLine();
                    updateUserVO.setPhone(updatePhoneNuber);
                    break;
                case 9:
                    break;
                default:
                    System.out.println("없는 메뉴 입니다.");
                    break;

            }
            userController.updateUser(userVO, updateUserVO);
        }

    }

    public void deleteByUser() {
        System.out.print("삭제할 계정(연락처)를 입력하세요 : ");
        String phoneNumber = sc.nextLine();
        userController.deleteUser(userController.getUser(phoneNumber));
        System.out.println(phoneNumber + "을(를) 삭제했습니다.");
    }

    public void userView() throws InputMismatchException {
        int number;

        label: while (true) {
            System.out.println("===== 회원 기능 =====");
            System.out.println("1. 회원 조회");
            System.out.println("2. 전체 회원 조회");
            System.out.println("3. 회원 추가");
            System.out.println("4. 회원 수정");
            System.out.println("5. 회원 삭제");
            System.out.println("9. 종료");
            System.out.println();
            System.out.print("메뉴를 선택하세요 : ");
            number = sc.nextInt();
            sc.nextLine();

            switch (number) {
                case 1:
                    findByUser();
                    break;
                case 2:
                    findAllByUser();
                    break;
                case 3:
                    addUser();
                    break;
                case 4:
                    modifyByUser();
                    break;
                case 5:
                    deleteByUser();
                    break;
                case 9:
                    break label;
                default:
                    System.out.println("없는 메뉴를 선택했습니다.");
                    break;
            }
        }
    }

}
