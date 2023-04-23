package Controller;

import java.util.List;
import java.util.Set;

import DTO.MenuDTO;
import Model.MenuVO;

public class MenuController {

    private MenuDTO menuDTO = new MenuDTO();

    /**
     * 메뉴 저장
     * 
     * @param menu 메뉴 객체
     * @return
     */
    public boolean saveByMenu(MenuVO menu) {
        if (menuDTO.findByMenu(menu.getName()) == null) {
            menuDTO.saveByMenu(menu);
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
    public boolean modifyByMenu(MenuVO menu) {
        MenuVO menuVO = findByMenu(menu.getName());
        if (menuVO != null) {
            return menuDTO.modifyByMenu(menuVO);
        }
        return false;
    }

    /**
     * 메뉴 이름으로 찾아 정보 표시
     * 
     * @param menuName
     * @return
     */
    public MenuVO findByMenu(String menuName) {
        return menuDTO.findByMenu(menuName);
    }

    /**
     * 메뉴 전체 조회
     * 
     * @return
     */
    public List<MenuVO> findAllByMenu() {
        return menuDTO.findAllByMenu();
    }

    /**
     * 카테고리별 메뉴 검색
     * 
     * @param category 출력할 카테고리
     * @return
     */
    public List<MenuVO> categoryFindAllbyMenu(String category) {
        return menuDTO.findAllByMenu()
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
        return menuDTO.deleteByMenu(menuName);
    }

    /**
     * 메뉴 전체 삭제
     * 
     * @return
     */
    public boolean deleteAllBymenu() {
        return menuDTO.deleteAllBymenu();
    }

    /**
     * 카테고리 추출항 리스트 만드는 메소드
     * 
     * @return
     */
    public Set<String> findAllCategory() {
        return menuDTO.findAllCategory();
    }

}
