package controller;

import view.AdminView;
import view.FailView;
import view.MenuView;

public class AdminController {
    public static void checkAdmin(String password) {
        // password == 1111 고정
        if (password.equals("1111")) {
            AdminView.startSetting();
        } else {
            FailView.errorMessage("관리자 비밀번호가 틀렸습니다.");
        }
    }
}
