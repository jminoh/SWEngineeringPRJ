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
@WebServlet("/atm-services/withdraw")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Withdraw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account account = new Account();
		int amount = 0; // 출금할 금액
		int balance = 0; // 잔액
		int tradingResult = 0; // 거래 결과
		String page = null;
		
		String accountNumber = request.getParameter("accountNumber");
		amount = Integer.parseInt(request.getParameter("amount"));

		ATMService atmService = new ATMServiceImpl();
		account = atmService.withdraw(accountNumber, amount);
		balance = account.getBalance();
		tradingResult = account.getTradingResult();

		if (account != null && tradingResult == 200) { // 계좌와 처리 결과가 정상
			request.setAttribute("accountNumber", accountNumber);
			request.setAttribute("balance", balance);
			page = "/view/success.jsp";
		} else {
			switch (tradingResult)
			{
				case 600 : request.setAttribute("errorMsg", "거래에 실패하였습니다."); break; // 처리 실패
				case 700 : request.setAttribute("errorMsg", "출금하려는 금액이 계좌 잔액보다 많습니다."); break; // 계좌 잔액 부족
			}
			page = "/view/error.jsp";
		}
		
		atmService.deauthentication(accountNumber);
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
