package controller;

import dto.IngredientDTO;
import service.impl.AdminServiceImpl;
import view.AdminView;
import view.FailView;
import view.SuccesssView;

import java.util.List;

public class AdminController {
    public static void checkAdmin(String password) {
        // password == 1111 고정
        if (password.equals("1111")) {
            AdminView.startSetting();
        } else {
            FailView.errorMessage(1111, "관리자 비밀번호가 틀렸습니다.");
        }
    }

    public static void getStock(){
        List<IngredientDTO> list= (List<IngredientDTO>) AdminServiceImpl.getInstance().findAllIngredient();
        if(!list.isEmpty()){
            SuccesssView.printStocks(list);
        }else{
            FailView.errorMessage("재고 항목이 존재하지 않습니다.");
        }

    }
}
