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
		int amount = 0; // ����� �ݾ�
		int balance = 0; // �ܾ�
		int tradingResult = 0; // �ŷ� ���
		String page = null;
		
		String accountNumber = request.getParameter("accountNumber");
		amount = Integer.parseInt(request.getParameter("amount"));

		ATMService atmService = new ATMServiceImpl();
		account = atmService.withdraw(accountNumber, amount);
		balance = account.getBalance();
		tradingResult = account.getTradingResult();

		if (account != null && tradingResult == 200) { // ���¿� ó�� ����� ����
			request.setAttribute("accountNumber", accountNumber);
			request.setAttribute("balance", balance);
			page = "/view/success.jsp";
		} else {
			switch (tradingResult)
			{
				case 600 : request.setAttribute("errorMsg", "�ŷ��� �����Ͽ����ϴ�."); break; // ó�� ����
				case 700 : request.setAttribute("errorMsg", "����Ϸ��� �ݾ��� ���� �ܾ׺��� �����ϴ�."); break; // ���� �ܾ� ����
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
