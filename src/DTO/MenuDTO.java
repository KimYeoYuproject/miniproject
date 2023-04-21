package DTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Model.MenuVO;

public class MenuDTO {
    List<MenuVO> menuVO = new ArrayList<>();

    public MenuVO findByMenu(String menuName) {
        for (MenuVO m : menuVO) {
            if (m.getName().equals(menuName)) {
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
            if (menuVO.get(i).getName().equals(menu.getName())) {
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
            if (menuVO.get(i).getName().equals(menuName)) {
                menuVO.remove(i);
                result = true;
            }
        }
        return result;
    }

    public boolean deleteAllBymenu() {
        boolean result = false;
        for (int i = 0; i < menuVO.size(); i++) {
            menuVO.remove(i);
        }
        if (menuVO == null) {
            result = true;
        }
        return result;
    }

    public Set<String> findAllCategory() {
        Set<String> category = new HashSet<>();

        for (MenuVO m : menuVO) {
            category.add(m.getCategory());
        }
        return category;
    }
}
