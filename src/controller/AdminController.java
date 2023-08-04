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
            FailView.errorMessage(0,"재고 항목이 존재하지 않습니다.");
        }

    }

    public static void updatestockbyId(Long menuId,int updatestock) {
        IngredientDTO dto=AdminServiceImpl.getInstance().updateStockByIngredientId(menuId,updatestock);
        System.out.println(dto.getStock());
        if(dto.getStock()!=0){
            SuccesssView.messagePrint(dto.getIngredientId()+" 수량 변경 완료");
        }else{
            FailView.errorMessage(0,"찾으시는 재료가 존재하지 않습니다.");
        }
    }

    public static void deleteByIngredientID(String menuid) {
        AdminServiceImpl.getInstance().deleteByIngredientId(Long.valueOf(menuid));
        if(true){
            SuccesssView.messagePrint("삭제 완료");
        }else{
            FailView.errorMessage(0,"찾으시는 재료가 존재하지 않습니다.");
        }

    }
}
