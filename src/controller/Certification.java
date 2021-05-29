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
@WebServlet("/certification")
public class Certification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Certification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account account = new Account();
		String page = null; // 다음 페이지 변수
		int result = 0;

		String accountNumber = request.getParameter("accountNumber");
		int checkNumber = Integer.parseInt(request.getParameter("checkNumber"));
		String action = request.getParameter("action");
		
		ATMService atmService = new ATMServiceImpl();
		result = atmService.certification(accountNumber, checkNumber);
		
		if (result == 200) { // 계좌와 처리 결과가 정상
			request.setAttribute("accountNumber", accountNumber);
			request.setAttribute("checkNumber", checkNumber);
			
			if (action.equals("transfer")) { // 정상완료 시 송금화면으로 이동
				page = "/view/transfer.jsp";
			}
			else if (action.equals("withdraw")) { // 정상완료 시 출금화면으로 이동
				page = "/view/withdraw.jsp";
			}
			
		} else {
			request.setAttribute("errorMsg", "본인인증이 실패하였습니다.");
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
