package controller;

import dao.impl.IngredientDAOImpl;
import dto.IngredientDTO;
import view.AdminView;
import view.FailView;

import java.util.List;

public class AdminController {
    public static void checkAdmin(String password) {
        // password == 1111 고정
        if (password.equals("1111")) {
            AdminView.startSetting();
        } else {
            FailView.errorMessage("관리자 비밀번호가 틀렸습니다.");
        }
    }

    public class list getStock(){
        List<IngredientDTO> list=IngredientDAOImpl.getInstance().findAll();
        if(list.size()>0){
            AdminService.
        }else{
            FailView.errorMessage("재고 항목이 존재하지 않습니다.");
        }

    }
}
