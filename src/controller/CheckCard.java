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
		ATMService atmService = new ATMServiceImpl();

		if (request.getParameter("accountNumber") != null) {
			accountNumber = request.getParameter("accountNumber");
			exist = atmService.checkAccount(accountNumber); // ���� Ȯ��
		}

		if (exist > 0) { 
			request.setAttribute("accountNumber", accountNumber); // ���¹�ȣ ����
			request.setAttribute("action", action);

			if (action.equals("deposit")) { // ����Ϸ� �� �Ա�ȭ������ �̵�
				page = "/view/deposit.jsp";
			}
			else if (action.equals("transfer") || action.equals("withdraw")) { // ����Ϸ� �� �۱� �Ǵ� ���ȭ������ �̵�
				// �������� ������ �̵� ��, �������� ��ȣ ����
				int result = atmService.setCertification(accountNumber);
				if (result == 200) {
					page = "/view/checkCertification.jsp";
				} else {
					request.setAttribute("errMsg", "���� �ܰ�(��������)�� ������ �� �����ϴ�.");
					page = "/view/error.jsp";
				}
			}
		}
		else { // ���� ��ȣ ���� �� ������������ �̵�
			request.setAttribute("errorMsg", "���¹�ȣ�� Ȯ�����ּ���.");
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
