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
@WebServlet("/atm-services/deposit")
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deposit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account account = new Account();
		int amount = 0; // 입금할 금액
		int balance = 0; // 잔액
		int tradingResult = 0; // 거래 결과
		String page = null;

		String accountNumber = request.getParameter("accountNumber");
		try { // 계좌번호 없이 입금버튼 눌렀을 경우
			amount = Integer.parseInt(request.getParameter("amount"));
		} catch (NumberFormatException e) {
			request.setAttribute("errorMsg", "금액을 확인해 주세요.");
			page = "/view/error.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
		String action = request.getParameter("action");

		ATMService atmService = new ATMServiceImpl();
		account = atmService.deposit(accountNumber, amount);
		balance = account.getBalance();
		tradingResult = account.getTradingResult();

		if (account != null && tradingResult == 200) { // 계좌와 처리 결과가 정상
			request.setAttribute("action", action);
			request.setAttribute("accountNumber", accountNumber);
			request.setAttribute("amount", amount);
			request.setAttribute("balance", balance);
			page = "/view/success.jsp";
		} else {
			request.setAttribute("errorMsg", "입금 실패하였습니다.");
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