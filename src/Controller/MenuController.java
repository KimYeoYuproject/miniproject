package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Model.MenuVO;

public class MenuController {
    List<MenuVO> menuVO = new ArrayList<>();

    public boolean saveByMenu(MenuVO menu) {
        for (MenuVO m : menuVO) {
            if (m.getName() == menu.getName()) {
                return false;
            }
        }
        menuVO.add(menu);
        return true;
    }

    public boolean modifyByMenu(MenuVO menu) {
        for (int i = 0; i < menuVO.size(); i++) {
            if (menuVO.get(i).getName() == menu.getName()) {
                menuVO.get(i).setName(menu.getName());
                menuVO.get(i).setCategory(menu.getCategory());
                menuVO.get(i).setPrice(menu.getPrice());
                return false;
            }
        }
        return true;
    }

    public Optional<MenuVO> findByMenu(String menuName) {
        for (MenuVO m : menuVO) {
            if (m.getName() == menuName) {
                return Optional.of(m);
            }
        }
        return null;
    }

    public Optional<List<MenuVO>> findAllByMenu() {
        return Optional.of(menuVO);
    }

    public boolean deleteBymenu(String menuName) {
        for (int i = 0; i < menuVO.size(); i++) {
            if (menuVO.get(i).getName() == menuName) {
                menuVO.remove(i);
                return true;
            }
        }
        return false;
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

    public Optional<List<String>> findAllCategory() {
        List<String> category = new ArrayList<>();

        for (MenuVO m : menuVO) {
            category.add(m.getCategory());
        }
        return Optional.of(category);
    }

}
