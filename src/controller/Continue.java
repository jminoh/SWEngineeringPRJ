package controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.ATMService;
import model.service.ATMServiceImpl;

/**
 * Servlet implementation class Home
 */
@WebServlet("/check/continue")
public class Continue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Continue() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		String accountNumber = request.getParameter("accountNumber");
		String checkContinue = request.getParameter("continue");
		String page = null;

		if (checkContinue.equals("yes")) { // 계속거래 시 거래 페이지로 이동
			request.setAttribute("accountNumber", accountNumber);
			request.setAttribute("action", action);

			if (action.equals("deposit")) {
				page = "/view/deposit.jsp";
			}else if (action.equals("transfer")) {
				page = "/view/transfer.jsp";			
			}else if (action.equals("withdraw")) {
				page = "/view/withdraw.jsp";
			}			
		} else {
			if (action.equals("transfer") || action.equals("withdraw")) { // 계속거래 하지 않을 시 메인으로 이동
				ATMService atmService = new ATMServiceImpl();
				atmService.deauthentication(accountNumber); // 거래 종료 시 본인인증 해제
			}
			page = "/index.jsp";
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
