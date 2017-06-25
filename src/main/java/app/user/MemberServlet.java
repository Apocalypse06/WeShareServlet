package app.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import app.main.ImageUtil;

@SuppressWarnings("serial")
@WebServlet("/UserServlet")
public class MemberServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuffer jsonIn = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {// 讀client端送的json
			jsonIn.append(line);
		}
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		// UserDao userDAO = new UserDAO();
		MemberDAO mbDAO = new MemberDAO();
		String action = jsonObject.get("action").getAsString();
		System.out.println("action: " + action);

		if (action.equals("getAll")) {
			String account = jsonObject.get("account").getAsString();
			System.out.println("--------------account: " + account);
			// MemberBean user = mbDAO.get(action);
			// List<Object[]> user = mbDAO.get(action);
			List<MemberBean> user = mbDAO.get(account);
			for (MemberBean pop : user) {
				System.out.println("" + pop.getTal() + "" + pop.getEmail() + "" + pop.getAddress());
			}
			System.out.println("GOGO1");
			writeText(response, gson.toJson(user));
		} else if (action.equals("getImage")) {
			String account = jsonObject.get("account").getAsString();
			OutputStream os = response.getOutputStream();
//			account = jsonObject.get("account").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = mbDAO.getImage(account);
			if (image != null) {
				image = ImageUtil.shrink(image, imageSize);// ImageUtil縮圖
				response.setContentType("image/jpeg");
				// 只要送一張圖，就不用轉json，指定他傳送的型態，如果要用json就要用Base64
				// // encode才能傳送
				response.setContentLength(image.length);// 輸出圖的長度
			}
			os.write(image);// 送到client端
		} else if (action.equals("userLogin")) {
			String userJson = jsonObject.get("user").getAsString();
			MemberBean mb = gson.fromJson(userJson, MemberBean.class);// 轉為Spot物件
			int count = 0;
			boolean check;
			try {
				check = mbDAO.checkPassword(mb);
				System.out.println("checkPassword = " + check);
				if (check == true) {
					count = 1;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			writeText(response, String.valueOf(count));
		} else if (action.equals("userRegister") || action.equals("userUpdate") || action.equals("userInRegister")) {
			String userJson = jsonObject.get("user").getAsString();
			MemberBean user = gson.fromJson(userJson, MemberBean.class);// 轉為Spot物件
			String imageBase64 = jsonObject.get("imageBase64").getAsString();
			// System.out.println("imageBase64" + imageBase64);
			byte[] image = Base64.getMimeDecoder().decode(imageBase64);
			int count = 0;
			boolean check;
			Blob blob = null;
			byte[] imagea = new byte[0];
			// List<MemberBean> allEmployees = new ArrayList<MemberBean>();
			if (action.equals("userRegister")) {
				try {
					if (imageBase64.equals(new byte[0])) {
						try {
							File f = new File("images/user.png");
							long size = f.length();
							InputStream is = new FileInputStream(f);
							byte[] ba = new byte[(int) size];
							blob = new SerialBlob(ba);
							System.out.println("asdsadsadsadsaddas" + imageBase64);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						// Blob imageBlob = new SerialBlob(image);
						blob = new SerialBlob(image);
						System.out.println("有給圖片");
					}
					// user.setImage(imageBlob);
					user.setImage(blob);
					String name = user.getUserId();
					check = mbDAO.isExists(name);
					System.out.println("checkRepeat = " + check);
					if (check == false) {
						count = mbDAO.save(user);
					} else {
						count = -1;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (action.equals("userInRegister")) {
				// try {
				// String insJson = jsonObject.get("ins").getAsString();
				// InstiutionBean ins = gson.fromJson(insJson,
				// InstiutionBean.class);// 轉為Spot物件
				// String imageBase64In =
				// jsonObject.get("imageBase64In").getAsString();
				// //System.out.println("imageBase64" + imageBase64);
				// byte[] imageIn =
				// Base64.getMimeDecoder().decode(imageBase64In);
				// Blob blobIn = null;
				// if (imageBase64.equals(new byte[0])) {
				// try {
				// File f = new File("images/user.png");
				// long size = f.length();
				// InputStream is = new FileInputStream(f);
				// byte[] ba = new byte[(int) size];
				// blob = new SerialBlob(ba);
				// System.out.println("asdsadsadsadsaddas" + imageBase64);
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
				// } else {
				// // Blob imageBlob = new SerialBlob(image);
				// blob = new SerialBlob(image);
				// blobIn = new SerialBlob(imageIn);
				// System.out.println("有給圖片-----In");
				// }
				// // user.setImage(imageBlob);
				// user.setImage(blob);
				// ins.setImage(blobIn);
				// //user.setInstiutionBean(ins);
				// check = mbDAO.isExists(user.getUserId());
				// System.out.println("checkRepeat = " + check);
				// if (check == false) {
				// count = mbDAO.save(user);
				// } else {
				// count = -1;
				// }
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
				try {
					String insJson = jsonObject.get("ins").getAsString();
					InstiutionBean ins = gson.fromJson(insJson, InstiutionBean.class);// 轉為Spot物件
					String imageBase64In = jsonObject.get("imageBase64In").getAsString();
					// System.out.println("imageBase64" + imageBase64);
					byte[] imageIn = Base64.getMimeDecoder().decode(imageBase64In);
					Blob blobIn = null;
					if (imageBase64.equals(new byte[0])) {
						try {
							File f = new File("images/user.png");
							long size = f.length();
							InputStream is = new FileInputStream(f);
							byte[] ba = new byte[(int) size];
							blob = new SerialBlob(ba);
							System.out.println("asdsadsadsadsaddas" + imageBase64);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						// Blob imageBlob = new SerialBlob(image);
						blob = new SerialBlob(image);
						blobIn = new SerialBlob(imageIn);
						System.out.println("有給圖片-----In");
					}
					// user.setImage(imageBlob);
					user.setImage(blob);
					ins.setImage(blobIn);
					// user.setInstiutionBean(ins);
					check = mbDAO.isExists(user.getUserId());
					System.out.println("checkRepeat = " + check);
					if (check == false) {
						count = mbDAO.saveIn(user, ins);
					} else {
						count = -1;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			else if (action.equals("userUpdate")) {

				// count = mdDao.update(spot, image);
			}
			writeText(response, String.valueOf(count));
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// UserDAO userDAO = new UserDAO();
		// List<User> user = userDAO.getAll();
		// writeText(response, new Gson().toJson(spots));
	}

	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		// System.out.println("outText: " + outText);
		out.print(outText);
	}
}