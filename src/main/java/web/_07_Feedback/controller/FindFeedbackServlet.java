package web._07_Feedback.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import web._01_register.model.MemberBean;
import web._07_Feedback.model.FeedbackBean;
import web._07_Feedback.model.FeedbackDAO;
import web._07_Feedback.model.JSON_Find_Bean;

@WebServlet("/web/_07_Feedback/controller/FindFeedbacklg")
public class FindFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FindFeedbackServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		do_First(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		do_First(request, response);
	}

	public void do_First(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		String INDID = "";
		String Type = "FIND";
		String Ans = "TRUE";
		String mfjb_json = "";
		JSON_Find_Bean mfjb = new JSON_Find_Bean();

		try {
			HttpSession session = request.getSession(false);
			MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
			INDID = mb.getIndid();
			System.out.println("session INDID=" + INDID);
		} catch (Exception e) {
			Ans = "FALSE";
			mfjb.setMessage("Session Not Found");
		}
		Gson gson = new Gson();
		if (Ans.equals("TRUE")) {
			Collection<FeedbackBean> collEND = new FeedbackDAO().FindByINDID(INDID);
			System.out.println(INDID + "一共有" + collEND.size() + "筆評價");
//			Collection<DEALBean> collSOURCE = new DealDAO().FindBySOURCEKey_DEAL(INDID);
//			System.out.println(INDID + "一共有" + collSOURCE.size() + "的交易訂單(賣家)");
			if(collEND.size()==0){
				mfjb.setMessage("Not Feedback");
			}else{
				mfjb.setCfb(collEND);
			}
		}
		mfjb.setType(Type);
		mfjb.setAns(Ans);
		mfjb_json = gson.toJson(mfjb);
		System.out.println(mfjb_json);
		response.setContentType("application/json; charset=UTF8");
		try (PrintWriter out = response.getWriter();) {
			out.print(mfjb_json);
		}
		return;
	}

}
