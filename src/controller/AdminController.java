package controller;

import view.AdminView;
import view.FailView;

/**
 *
 */
public class AdminController {
    /**
     * method checkAdmin: 관리자 인증 메소드 (password = 1111)
     * @param password 관리자 인증 비밀번호 입력 값
     */
    public static void checkAdmin(String password) {
        // password == 1111 고정
        if (password.equals("1111")) {
            AdminView.startSetting();
        } else {
            FailView.errorMessage(1111, "관리자 비밀번호가 틀렸습니다.");
        }
    }

//    public class List<IngredientDTO> list getStock(){
//        List<IngredientDTO> list= AdminServiceImpl.getInstance().findAllIngredient();
//        if(list.size()>0){
//            return list;
//        }else{
//            FailView.errorMessage("재고 항목이 존재하지 않습니다.");
//        }
//
//    }
}
