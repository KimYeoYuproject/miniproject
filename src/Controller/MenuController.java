package Controller;

import java.util.List;
import java.util.Set;

import DAO.MenuDAO;
import Model.MenuDTO;

/**
 * @author yoosc89
 */
public class MenuController {

    private MenuDAO menuDAO = new MenuDAO();

    /**
     * 메뉴 저장
     * 
     * @param menu 메뉴 객체
     * @return
     */
    public boolean saveByMenu(MenuDTO menu) {
        if (this.menuDAO.findByMenu(menu.getName()) == null) {
            this.menuDAO.saveByMenu(menu);
            return true;
        }
        return false;
    }

    /**
     * 메뉴 수정
     * 
     * @param menu
     * @return
     */
    public boolean modifyByMenu(MenuDTO oldMenu, MenuDTO newMenu) {
        MenuDTO menuVO = findByMenu(oldMenu.getName());
        if (menuVO != null) {
            return this.menuDAO.modifyByMenu(oldMenu, newMenu);
        }
        return false;
    }

    /**
     * 메뉴 이름으로 찾아 정보 표시
     * 
     * @param menuName
     * @return
     */
    public MenuDTO findByMenu(String menuName) {
        return this.menuDAO.findByMenu(menuName).orElse(null);
    }

    /**
     * 메뉴 전체 조회
     * 
     * @return
     */
    public List<MenuDTO> findAllByMenu() {
        return this.menuDAO.findAllByMenu();

    }

    /**
     * 카테고리별 메뉴 검색
     * 
     * @param category 출력할 카테고리
     * @return
     */
    public List<MenuDTO> categoryFindAllbyMenu(String category) {
        return menuDAO.findAllByMenu()
                .stream()
                .filter(menuVO -> menuVO.getCategory().equals(category))
                .toList();
    }

    /**
     * 메뉴 삭제
     * 
     * @param menuName
     * @return
     */
    public boolean deleteBymenu(String menuName) {
        if (findByMenu(menuName) == null) {
            return false;
        }
        return this.menuDAO.deleteByMenu(menuName);
    }

    /**
     * 메뉴 전체 삭제
     * 
     * @return
     */
    public boolean deleteAllBymenu() {
        return this.menuDAO.deleteAllBymenu();
    }

    /**
     * 카테고리 추출항 리스트 만드는 메소드
     * 
     * @return
     */
    public Set<String> findAllCategory() {
        return this.menuDAO.findAllCategory();
    }

}
