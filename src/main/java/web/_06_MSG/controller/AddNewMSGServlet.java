package web._06_MSG.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import web._00_init.GlobalService;
import web._01_register.model.MemberBean;
import web._02_login.model.GoogleLoginBean;
import web._06_MSG.model.JSON_Find_Bean;
import web._06_MSG.model.JSON_In_Up_Bean;
import web._06_MSG.model.MSGBean;
import web._06_MSG.model.MSGDAO;
import web._06_MSG.model.MSG_ErrorBean;

//@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
//		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/web/_06_MSG/controller/AddNewMSG")
public class AddNewMSGServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddNewMSGServlet() {
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
		String Type = "INSERT";
		String Ans = "TRUE";
		String mfjb_json = "";
		JSON_In_Up_Bean mfjb = new JSON_In_Up_Bean();
		try {
			HttpSession session = request.getSession(false);
			MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
			INDID = mb.getIndid();
			System.out.println("session INDID=" + INDID);
		} catch (Exception e) {
			Ans = "FALSE";
			mfjb.setMessage("Session Not Found");
		}
		String MSGENDID = request.getParameter("MSGENDID");
		String MSGTEXT = request.getParameter("MSGTEXT");
		System.out.println("MSGENDID=" + MSGENDID + "	MSGTEXT=" + MSGTEXT);
		MSG_ErrorBean msgeb = new MSG_ErrorBean();
		Gson gson = new Gson();
		if (Ans.equals("TRUE")) {
			if (MSGENDID == null || MSGENDID.trim().length() == 0) {
				msgeb.setErrorMSGENDID("帳號欄必須輸入");
				Ans = "FALSE";
			}
			if (Ans.equals("TRUE")) {
				if (MSGENDID.toUpperCase().equals(INDID.toUpperCase())) {
					msgeb.setErrorMSGENDID("收件者不可是本人");
					Ans = "FALSE";
				}
			}
			if (MSGTEXT == null || MSGTEXT.trim().length() == 0) {
				msgeb.setErrorMSGTEXT("訊息欄必須輸入");
				Ans = "FALSE";
			}
			if (!Ans.equals("TRUE")) {
				mfjb.setMsgeb(msgeb);
			}
		}
		if (Ans.equals("TRUE")) {
			try {
				MSGBean msgb = new MSGBean(INDID, MSGENDID, MSGTEXT, null);
				String SQLAns = new MSGDAO().Insert_MSG(msgb, null, 0L);
				if (!SQLAns.equals("TRUE")) {
					mfjb.setMessage("SQL ERROR");
					Ans = "FALSE";
				}
			} catch (Exception e) {
				mfjb.setMessage("SQL ERROR");
				Ans = "FALSE";
			}
		}
		mfjb.setType(Type);
		mfjb.setAns(Ans);
		mfjb_json = gson.toJson(mfjb);
		System.out.println("mfjb_json" + mfjb_json);
		response.setContentType("application/json; charset=UTF8");
		try (PrintWriter out = response.getWriter();) {
			out.print(mfjb_json);
		}
		return;
	}
}
