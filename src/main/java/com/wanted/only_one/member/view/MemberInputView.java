package com.wanted.only_one.member.view;

import com.wanted.only_one.member.controller.AuthController;

import java.util.Scanner;

public class MemberInputView {

    private final AuthController authController;
    private final MemberOutputView outputView;
    private final Scanner sc = new Scanner(System.in);

    public MemberInputView(AuthController authController,
                           MemberOutputView outputView) {
        this.authController = authController;
        this.outputView = outputView;
    }

    // 메인 메뉴
    public void displayMainMenu() {
        while (true) {
            System.out.println();
            System.out.println("=================================");
            System.out.println("only one에 오신 걸 환영합니다");
            System.out.println("=================================");
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 종료");
            System.out.println("=================================");
            System.out.print("메뉴 선택 : ");

            int menu = inputInt();

            switch (menu) {
                case 1:
                    displaySignUpMenu();
                    break;
                case 2:
                    displayLoginMenu();
                    break;
                case 3:
                    outputView.printMessage("== 프로그램을 종료합니다. ==");
                    System.exit(0);
                    break;
                default:
                    outputView.printError("올바른 메뉴를 선택해주세요.");
            }
        }
    }

    // 회원가입 메뉴
    private void displaySignUpMenu() {
        while (true) {
            System.out.println();
            System.out.println("=================================");
            System.out.println("            회원가입");
            System.out.println("=================================");
            System.out.println("1. 학생 회원가입");
            System.out.println("2. 강사 회원가입");
            System.out.println("3. 이전으로");
            System.out.println("=================================");
            System.out.print("메뉴 선택 : ");

            int menu = inputInt();

            switch (menu) {
                case 1:
                    signUp("STUDENT");
                    break;
                case 2:
                    signUp("TEACHER");
                    break;
                case 3:
                    return;  // ← while 빠져나가서 메인으로
                default:
                    outputView.printError("올바른 메뉴를 선택해주세요.");
            }
        }
    }

    // 회원가입 처리 - 동의
    private void signUp(String role) {
        while (true) {
            System.out.println();
            System.out.println("=================================");
            System.out.println("       개인정보 수집 동의");
            System.out.println("=================================");
            System.out.println("개인정보 수집 및 이용에 동의하십니까?");
            System.out.println("1. 예");
            System.out.println("2. 아니오");
            System.out.print("선택 : ");

            int agree = inputInt();
            switch (agree) {
                case 1:
                    enterInfo(role);  // 동의 → 정보 입력
                    break;
                case 2:
                    outputView.printMessage("개인정보 수집에 동의하지 않으면 가입할 수 없습니다.");
                    return;  // ← 메인으로
                default:
                    System.out.println(" ");
                    outputView.printError("========올바른 메뉴를 선택해주세요.");
            }
        }
    }

    // 회원가입 정보 입력
    private void enterInfo(String role) {
        boolean result = false;
        while (true) {


            System.out.print("이름을 입력하십시오 : ");
            String name = sc.nextLine();

            System.out.print("이메일을 입력하십시오 : ");
            String email = sc.nextLine();
            boolean emailresult = authController.emailMix(email);
            if (!emailresult) {
                outputView.printError("\n이미 존재하는 이메일입니다");
                continue;

            }
            System.out.print("비밀번호를 입력하십시오 (특수기호 포함) : ");
            String password = sc.nextLine();
            boolean pwdResult = authController.pwdInclude(password);
            if (!pwdResult) {
                outputView.printError("\n특수기호는 필수입니다");
                continue;
            }

            result = authController.signUp(name, email, password, role);
            outputView.printSignUpResult(result);
            break;
        }
        // 회원가입 성공하면 로그인 화면으로 이동
        if (result) {
            displayLoginMenu();  // ← 추가!
        }
    }



    // 로그인 메뉴
    private void displayLoginMenu() {
        while (true) {
            System.out.println();
            System.out.println("=================================");
            System.out.println("            로그인");
            System.out.println("=================================");
            System.out.println("1. 학생 로그인");
            System.out.println("2. 강사 로그인");
            System.out.println("3. 비밀번호 초기화");
            System.out.println("4. 이전으로");
            System.out.println("=================================");
            System.out.print("메뉴 선택 : ");

            int menu = inputInt();

            switch (menu) {
                case 1:
                case 2:  // 학생/강사 로그인 동일하게 처리
                    signIn();
                    break;
                case 3:
                    resetPassword();
                    break;
                case 4:
                    return;  // ← 메인으로
                default:
                    outputView.printError("올바른 메뉴를 선택해주세요.");
            }
        }
    }

    // 로그인 처리
    private void signIn() {
        System.out.println("이메일을 입력하십시오");
        System.out.print("이메일 : ");
        String email = sc.nextLine();

        System.out.println("비밀번호를 입력하십시오");
        System.out.print("비밀번호 : ");
        String password = sc.nextLine();

        boolean result = authController.signIn(email, password);
        outputView.printSignInResult(result);
    }

    // 비밀번호 초기화
    private void resetPassword() {
        System.out.println();
        System.out.println("=================================");
        System.out.println("         비밀번호 초기화");
        System.out.println("=================================");
        System.out.print("이메일 : ");
        String email = sc.nextLine();

        System.out.print("새 비밀번호 (특수기호 포함) : ");
        String newPassword = sc.nextLine();

        boolean result = authController.resetPassword(email, newPassword);
        outputView.printResetPasswordResult(result);
    }

    // 숫자 입력 예외처리
    private int inputInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("숫자만 입력해주세요 : ");
            }
        }
    }
}