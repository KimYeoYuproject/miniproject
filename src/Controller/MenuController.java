package Controller;

import java.util.List;
import java.util.Optional;

import DTO.MenuDTO;
import Model.MenuVO;

public class MenuController {

    private MenuDTO menuDTO = new MenuDTO();

    public boolean saveByMenu(MenuVO menu) {
        if (menuDTO.findByMenu(menu.getName()) != null) {
            menuDTO.saveByMenu(menu);
            return true;
        }
        return false;
    }

    public boolean modifyByMenu(MenuVO menu) {
        MenuVO menuVO = findByMenu(menu.getName()).get();
        if (menuVO != null) {
            return menuDTO.modifyByMenu(menuVO);
        }
        return false;
    }

    public Optional<MenuVO> findByMenu(String menuName) {
        return Optional.of(menuDTO.findByMenu(menuName));
    }

    public Optional<List<MenuVO>> findAllByMenu() {
        return Optional.of(menuDTO.findAllByMenu());
    }

    public boolean deleteBymenu(String menuName) {
        if (findByMenu(menuName).get() == null) {
            return false;
        }
        return menuDTO.deleteByMenu(menuName);
    }

    public boolean deleteAllBymenu() {
        return menuDTO.deleteAllBymenu();
    }

    public Optional<List<String>> findAllCategory() {
        return Optional.of(menuDTO.findAllCategory());
    }

}
