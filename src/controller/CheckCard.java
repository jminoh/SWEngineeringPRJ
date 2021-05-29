package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.Account;
import model.service.ATMService;
import model.service.ATMServiceImpl;

/**
 * Servlet implementation class Card
 */
@WebServlet("/check/card")
public class CheckCard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accountNumber = null;
		String page = null;
		int exist = 0;
		String action = request.getParameter("action");

		if (request.getParameter("accountNumber") != null) {
			accountNumber = request.getParameter("accountNumber");
			ATMService atmService = new ATMServiceImpl();
			exist = atmService.checkAccount(accountNumber); // 계좌 확인
		}

		if (exist > 0) { 
			request.setAttribute("accountNumber", accountNumber); // 계좌번호 저장

			if (action.equals("deposit")) { // 정상완료 시 입금화면으로 이동
				page = "/view/deposit.jsp";
			}
			else if (action.equals("transfer") || action.equals("withdraw")) { // 정상완료 시 송금 또는 출금화면으로 이동
				request.setAttribute("action", action);
				page = "/view/certification.jsp";
			}
		}
		else { // 계좌 번호 없을 시 에러페이지로 이동
			request.setAttribute("errorMsg", "계좌번호를 확인해주세요.");
			page = "/view/error.jsp";
		} 

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
