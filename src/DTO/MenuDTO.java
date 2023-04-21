package DTO;

import java.util.ArrayList;
import java.util.List;

import Model.MenuVO;

public class MenuDTO {
    List<MenuVO> menuVO = new ArrayList<>();

    public MenuVO findByMenu(String menuName) {
        for (MenuVO m : menuVO) {
            if (m.getName() == menuName) {
                return m;
            }
        }
        return null;
    }

    public List<MenuVO> findAllByMenu() {
        return menuVO;
    }

    public boolean saveByMenu(MenuVO menu) {
        return menuVO.add(menu);
    }

    public boolean modifyByMenu(MenuVO menu) {
        boolean result = false;
        for (int i = 0; i < menuVO.size(); i++) {
            if (menuVO.get(i).getName() == menu.getName()) {
                menuVO.get(i).setName(menu.getName());
                menuVO.get(i).setCategory(menu.getCategory());
                menuVO.get(i).setPrice(menu.getPrice());
                result = true;
            }
        }
        return result;
    }

    public boolean deleteByMenu(String menuName) {
        boolean result = false;
        for (int i = 0; i < menuVO.size(); i++) {
            if (menuVO.get(i).getName() == menuName) {
                menuVO.remove(i);
                result = true;
            }
        }
        return result;

    }
}
