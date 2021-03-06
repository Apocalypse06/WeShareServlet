﻿package web._00_init;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

//本類別會依據傳入的書籍編號(BookID)讀取eBook表格內CoverImage欄位內的圖片，
//然後傳回給提出請求的瀏覽器
@WebServlet("/_00_init/getImage")
public class GetImageFromDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ImageBean ib = new ImageBean();
		InputStream is = null;
		OutputStream os = null;
		String mimeType = "text/jpg";
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		if (!id.equals("")) {
			ib = getImage(id, type);
			if (ib.FileName.equals("FALSE") & type.equals("MEMBER")) {
				System.out.println("[Image MEMBER]DEF");
				is = getServletContext().getResourceAsStream("/dist/img/icon_member1.png");
			} else if (ib.FileName.equals("FALSE") & type.equals("ORG")) {
				System.out.println("[Image ORG]DEF");
				is = getServletContext().getResourceAsStream("/dist/img/org_house_default.png");
			} else if (ib.FileName.equals("FALSE") & type.equals("MSG")) {
				System.out.println("[Image MSG]DEF");
				is = getServletContext().getResourceAsStream("/dist/img/icon_member1.png");
			} else if (ib.FileName.equals("FALSE") & type.equals("GOODS")) {
				System.out.println("[Image GOODS]DEF");
				is = getServletContext().getResourceAsStream("/dist/img/icon_member1.png");
			} else if (ib.FileName.equals("FALSE") & type.equals("DEAL")) {
				System.out.println("[Image DEAL]DEF");
				is = getServletContext().getResourceAsStream("/dist/img/icon_member1.png");
			} else if (ib.FileName.equals("FALSE")) {
				System.out.println("[Image DEAL]DEF");
				is = getServletContext().getResourceAsStream("/dist/img/icon_member1.png");
			} else {
				System.out.println("[Image]OK");
				mimeType = getServletContext().getMimeType(ib.FileName);
				is = ib.getIs();
			}
		} else {
			System.out.println("[Image]DEF id=null");
			is = getServletContext().getResourceAsStream("/dist/img/null.png");
		}

		response.setContentType(mimeType);

		// 設定輸出資料的型態
		// 取得能寫出非文字資料的OutputStream物件
		os = response.getOutputStream();
		int count = 0;
		byte[] bytes = new byte[8192];
		while ((count = is.read(bytes)) != -1) {
			os.write(bytes, 0, count);
		}
		os.close();
	}

	synchronized public ImageBean getImage(String id, String type) {
		ImageBean ib = new ImageBean();
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		System.out.println("id=" + id + "  type=" + type);
		boolean ans = true;
		try {

			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/BookDataSQLver");
			conn = ds.getConnection();
			if (!id.trim().equals("")) {
				if (type.equals("MEMBER")) {
					pstmt = conn.prepareStatement(
							"SELECT count(indFileName), indImage,indFileName from ind where indId = ?");
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();

				} else if (type.equals("ORG")) {
					pstmt = conn.prepareStatement(
							"SELECT count(orgFileName), orgImage,orgFileName from org where indId = ?");
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
				} else if (type.equals("MSG")) {
					pstmt = conn.prepareStatement(
							"SELECT count(msgFileName),msgImage,msgFileName from MSG where msgno = ?");
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();

				} else if (type.equals("GOODS")) {
					pstmt = conn.prepareStatement(
							"SELECT count(goodsFileName),goodsImage,goodsFileName from goods where goodsno = ?");
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();

				} else if (type.equals("DEAL")) {
					pstmt = conn.prepareStatement(
							"SELECT COUNT(DEALFILENAME),DEALIMAGE,DEALFILENAME FROM DEAL WHERE DEALNO = ?");
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();

				} else {
					System.out.println("type not found:" + type);
				}
				if (rs.next()) {
					if (rs.getString(1).equals("1")) {
						if (rs.getString(3).equals("")) {
							ib.setFileName("FALSE");
							System.out.println("[Image SQL] picture not found");
						} else {
							ib.setFileName("TRUE");
							ib.setFileName(rs.getString(1));
						}

					} else {
						System.out.println("[Image SQL]picture Is Null");
						ib.setFileName("FALSE");
					}
					ib.setIs(rs.getBinaryStream(2));
				}
			} else {
				System.out.println("[Image SQL]ID Is Null");
				ib.setFileName("FALSE");
			}
			conn.close();

		} catch (NamingException e) {
			ib.setFileName("FALSE");
			// e.printStackTrace();
		} catch (Exception e) {
			ib.setFileName("FALSE");
			// e.printStackTrace();
		} finally {
		}
		// System.out.println("[Image SQL]Ans=" + ib.getFileName());
		return ib;
	}
}