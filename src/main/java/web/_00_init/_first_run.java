package web._00_init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.serial.SerialBlob;

import web._01_register.model.MemberBean;
import web._01_register.model.OrgBean;

public class _first_run {

	public static void main(String[] args) {
		Connection con;
		Statement stmt;
		try {
			// 連上後端的資料庫
			con = DriverManager.getConnection(GlobalService.DB_URLMySQL, GlobalService.USERID, GlobalService.PASSWORD);
			stmt = con.createStatement();
			DropTable(stmt);
			System.out.println("[初始化]表格刪除成功");
			CreateTable(stmt);
			System.out.println("[初始化]表格新建成功");
			CreateData(con);
			System.out.println("[初始化]資料新建成功");
			System.out.println("[初始化]完成");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}

	}

	public static void DropTable(Statement stmt) throws SQLException {
		stmt.executeUpdate(sql_Common.DROP_TABLE_GOODS);

		stmt.executeUpdate(sql_Common.DROP_TABLE_GOODSTYPE);
		stmt.executeUpdate(sql_Common.DROP_TABLE_LOCAL);

		stmt.executeUpdate(sql_Common.DROP_TABLE_MSG);
		stmt.executeUpdate(sql_Common.DROP_TABLE_MSG_ROOM);
		stmt.executeUpdate(sql_Common.DROP_TABLE_FEEDBACK);
		stmt.executeUpdate(sql_Common.DROP_TABLE_DEAL);

		stmt.executeUpdate(sql_Common.DROP_TABLE_ORG);
		stmt.executeUpdate(sql_Common.DROP_TABLE_ORGTYPE);
		stmt.executeUpdate(sql_Common.DROP_TABLE_IND);
		stmt.executeUpdate(sql_Common.DROP_FUNCTION_INSERT_DEAL);
		stmt.executeUpdate(sql_Common.DROP_FUNCTION_CHECK_ROOMNO);
	}

	public static void CreateTable(Statement stmt) throws SQLException {
		stmt.executeUpdate(sql_Common.CREATE_TABLE_IND);
		stmt.executeUpdate(sql_Common.CREATE_TABLE_ORGTYPE);
		stmt.executeUpdate(sql_Common.CREATE_TABLE_ORG);
		stmt.executeUpdate(sql_Common.CREATE_TABLE_GOODSTYPE);
		stmt.executeUpdate(sql_Common.CREATE_TABLE_LOCAL);

		stmt.executeUpdate(sql_Common.CREATE_TABLE_GOODS);
		stmt.executeUpdate(sql_Common.CREATE_TABLE_DEAL);
		stmt.executeUpdate(sql_Common.CREATE_TABLE_FEEDBACK);
		stmt.executeUpdate(sql_Common.CREATE_TABLE_MSG_ROOM);
		stmt.executeUpdate(sql_Common.CREATE_TABLE_MSG);
		stmt.executeUpdate(sql_Common.CREATE_FUNCTION_INSERT_DEAL);
		stmt.executeUpdate(sql_Common.CREATE_FUNCTION_CHECK_ROOMNO);

	}

	public static void CreateData(Connection con) throws SQLException {
		MemberBean mb1 = new MemberBean(1, null, "kitty", "123", "凱蒂", "0229625270", "kitty@gmail.com", "新北市板橋區中正路100號",
				new SerialBlob(GlobalService.read_BinaryFile_To_ByteArray("src\\main\\webapp\\images\\kitty.jpg")),
				"kitty.jpg");
		MemberBean mb2 = new MemberBean(2, null, "Google", "123", "谷哥大神", "0229625270", "Google@gmail.com",
				"台北市大安區xxx巷xxxx號",
				new SerialBlob(GlobalService.read_BinaryFile_To_ByteArray("src\\main\\webapp\\images\\Google.jpg")),
				"Google.jpg");

		OrgBean ob2 = new OrgBean(mb2.getIndid(), null, "我是社福簡介", "社福負責人", 1, "立案核准", "勸募許可",
				new SerialBlob(GlobalService.read_BinaryFile_To_ByteArray("src\\main\\webapp\\images\\Googleorg.jpg")),
				"Googleorg.jpg");

		MemberBean mb3 = new MemberBean(1, null, "micky", "123", "米奇", "0228825252", "micky@gmail.com", "米奇的家",
				new SerialBlob(GlobalService.read_BinaryFile_To_ByteArray("src\\main\\webapp\\images\\micky.png")),
				"micky.png");
		ORGTYPE_DATA(con);
		IND_DATA(mb1, con);
		ORG_DATA(mb2, ob2, con);
		IND_DATA(mb3, con);
		LOCAL_DATA(con);
		GOODSTYPE_DATA(con);
		org_data(con);
		goods_data(con);
		MSG_DATA(con);
	}

	public static void org_data(Connection con) throws SQLException {
		try {
			String line1 = "";
			String sql1 = "insert into ind "
					+ " (usertype,postdate,indid,indpassword,indname,indphone,indemail,indaddress,indimage,indfilename) "
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			con.setAutoCommit(false);
			PreparedStatement pstmt1 = con.prepareStatement(sql1);
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream("src//main//java//web//orgind.txt"), "UTF8"));
			while ((line1 = br.readLine()) != null) {
				if (line1.startsWith("\uFEFF")) {
					line1 = line1.substring(1);
				}
				String[] token = line1.split("\\|");
				pstmt1.setInt(1, Integer.parseInt(token[0]));
				java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
				pstmt1.setTimestamp(2, now);
				pstmt1.setString(3, token[2].trim());
				pstmt1.setString(4, GlobalService.getMD5Endocing(GlobalService.encryptString(token[3].trim())));
				pstmt1.setString(5, token[4].trim());
				pstmt1.setString(6, token[5].trim());
				pstmt1.setString(7, token[6].trim());
				pstmt1.setString(8, token[7].trim());
				File aFile = new File("src\\main\\webapp\\images\\org\\" + token[8].trim());
				long size = aFile.length();
				InputStream is = new FileInputStream(aFile);
				pstmt1.setBlob(9, is, size);
				String fileName = aFile.getName();
				fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
				pstmt1.setString(10, fileName);
				int r = pstmt1.executeUpdate();
			}

			String line2 = "";
			String sql2 = "insert into org "
					+ " (indid,updatetime,intro,leader,orgtypes,registerno,raiseno,orgimage,orgfilename) "
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt2 = con.prepareStatement(sql2);

			BufferedReader br2 = new BufferedReader(
					new InputStreamReader(new FileInputStream("src//main//java//web//org.txt"), "UTF8"));
			while ((line2 = br2.readLine()) != null) {
				if (line2.startsWith("\uFEFF")) {
					line2 = line2.substring(1);
				}
				String[] token = line2.split("\\|");
				pstmt2.setString(1, token[0].trim());
				java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
				pstmt2.setTimestamp(2, now);
				pstmt2.setString(3, token[2].trim());
				pstmt2.setString(4, token[3].trim());
				pstmt2.setInt(5, Integer.parseInt(token[4]));
				pstmt2.setString(6, token[5].trim());
				pstmt2.setString(7, token[6].trim());

				File aFile = new File("src\\main\\webapp\\images\\org\\" + token[7].trim());
				long size = aFile.length();
				InputStream is = new FileInputStream(aFile);
				pstmt2.setBlob(8, is, size);
				String fileName = aFile.getName();
				fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
				pstmt2.setString(9, fileName);
				int r = pstmt2.executeUpdate();
			}
			con.setAutoCommit(true);
			// System.out.println("新增一筆ord紀錄是否成功=" + r);
		} catch (SQLException e) {
			System.err.println("新建ind_org表格時發生SQL例外: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("新建ind_org表格時發生IO例外: " + e.getMessage());
		}
	}

	public static void goods_data(Connection con) throws SQLException {
		try {
			String line = "";
			String sql = "insert into goods "
					+ " (goodsno,  goodsstatus,  updatetime, indid, goodstype, goodsname, goodsloc, goodsnote, qty, goodsshipway, deadline,goodsimage,goodsfilename) "
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);

			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream("src//main//java//web//goods.txt"), "UTF8"));
			while ((line = br.readLine()) != null) {
				// 去除 UTF8_BOM
				if (line.startsWith("\uFEFF")) {
					line = line.substring(1);
				}
				String[] token = line.split("\\|");
				pstmt.setInt(1, Integer.parseInt(token[0]));
				pstmt.setInt(2, Integer.parseInt(token[1]));
				java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
				pstmt.setTimestamp(3, now);
				pstmt.setString(4, token[3].trim());
				pstmt.setInt(5, Integer.parseInt(token[4]));
				pstmt.setString(6, token[5].trim());
				pstmt.setInt(7, Integer.parseInt(token[6]));
				pstmt.setString(8, token[7].trim());
				pstmt.setInt(9, Integer.parseInt(token[8]));
				pstmt.setInt(10, Integer.parseInt(token[9]));
				pstmt.setLong(11, Long.parseLong(token[10]));
				// 讀取圖片檔
				File aFile = new File("src\\main\\webapp\\images\\goods\\" + token[11].trim());
				long size = aFile.length();
				InputStream is = new FileInputStream(aFile);
				pstmt.setBlob(12, is, size);
				// 設定fileName欄位
				String fileName = aFile.getName();
				fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
				pstmt.setString(13, fileName);

				int r = pstmt.executeUpdate();
				// System.out.println("新增一筆goods紀錄是否成功=" + r);
			}
			// System.out.println("goods資料新增成功") ;
		} catch (SQLException e) {
			System.err.println("新建goods表格時發生SQL例外: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("新建goods表格時發生IO例外: " + e.getMessage());
		}
	}

	public static void IND_DATA(MemberBean mb1, Connection con) throws SQLException {

		PreparedStatement pstmt1 = con.prepareStatement(sql_Common.INSERT_TABLE_IND);
		pstmt1.setInt(1, mb1.getUsertype());
		pstmt1.setTimestamp(2, mb1.getPostdate());
		pstmt1.setString(3, mb1.getIndid());
		pstmt1.setString(4, GlobalService.getMD5Endocing(GlobalService.encryptString(mb1.getIndpassword())));
		pstmt1.setString(5, mb1.getIndname());
		pstmt1.setString(6, mb1.getIndphone());
		pstmt1.setString(7, mb1.getIndemail());
		pstmt1.setString(8, mb1.getIndaddress());
		pstmt1.setBlob(9, mb1.getIndimage());
		pstmt1.setString(10, mb1.getIndfilename());
		pstmt1.executeUpdate();
	}

	public static void ORG_DATA(MemberBean mb2, OrgBean ob2, Connection con) throws SQLException {
		con.setAutoCommit(false);
		PreparedStatement pstmt1 = con.prepareStatement(sql_Common.INSERT_TABLE_IND);
		pstmt1.setInt(1, mb2.getUsertype());
		pstmt1.setTimestamp(2, mb2.getPostdate());
		pstmt1.setString(3, mb2.getIndid());
		pstmt1.setString(4, GlobalService.getMD5Endocing(GlobalService.encryptString(mb2.getIndpassword())));
		pstmt1.setString(5, mb2.getIndname());
		pstmt1.setString(6, mb2.getIndphone());
		pstmt1.setString(7, mb2.getIndemail());
		pstmt1.setString(8, mb2.getIndaddress());
		pstmt1.setBlob(9, mb2.getIndimage());
		pstmt1.setString(10, mb2.getIndfilename());
		pstmt1.executeUpdate();
		PreparedStatement pstmt2 = con.prepareStatement(sql_Common.INSERT_TABLE_ORG);
		pstmt2.setString(1, ob2.getIndid());
		pstmt2.setTimestamp(2, ob2.getUpdatetime());
		pstmt2.setString(3, ob2.getIntro());
		pstmt2.setString(4, ob2.getLeader());
		pstmt2.setInt(5, ob2.getOrgtypes());
		pstmt2.setString(6, ob2.getRegisterno());
		pstmt2.setString(7, ob2.getRaiseno());
		pstmt2.setBlob(8, mb2.getIndimage());
		pstmt2.setString(9, ob2.getOrgfilename());
		pstmt2.executeUpdate();
		con.setAutoCommit(true);
	}

	public static void LOCAL_DATA(Connection con) throws SQLException {

		try (// java 7.0 提共自動關閉的資源
				BufferedReader bf = new BufferedReader(
						new InputStreamReader(new FileInputStream("src//main//java//web//local.txt"), "UTF8"));) {
			String Read_line = "";

			while ((Read_line = bf.readLine()) != null) {
				String[] sa = Read_line.split("\\|");

				try {
					PreparedStatement pstmt = con.prepareStatement(sql_Common.INSERT_TABLE_LOCAL);
					pstmt.setInt(1, Integer.parseInt(sa[1]));
					pstmt.setString(2, sa[0]);
					pstmt.executeUpdate();

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	};

	public static void GOODSTYPE_DATA(Connection con) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(sql_Common.INSERT_TABLE_GOODSTYPE);
		pstmt.setString(1, "乾貨食品");
		pstmt.executeUpdate();
		pstmt.setString(1, "服飾配件");
		pstmt.executeUpdate();
		pstmt.setString(1, "生活用品");
		pstmt.executeUpdate();
		pstmt.setString(1, "家電機器");
		pstmt.executeUpdate();
		pstmt.setString(1, "其他類型");
		pstmt.executeUpdate();
	}

	public static void ORGTYPE_DATA(Connection con) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(sql_Common.INSERT_TABLE_ORGTYPE);
		pstmt.setInt(1, Integer.parseInt("1"));
		pstmt.setString(2, "兒少福利");
		pstmt.executeUpdate();
		pstmt.setInt(1, Integer.parseInt("2"));
		pstmt.setString(2, "偏鄉教育");
		pstmt.executeUpdate();
		pstmt.setInt(1, Integer.parseInt("3"));
		pstmt.setString(2, "老人福利");
		pstmt.executeUpdate();
		pstmt.setInt(1, Integer.parseInt("4"));
		pstmt.setString(2, "身障福利");
		pstmt.executeUpdate();
		pstmt.setInt(1, Integer.parseInt("5"));
		pstmt.setString(2, "其他類型");
		pstmt.executeUpdate();
	}

	public static void MSG_DATA(Connection con) throws SQLException {
		System.out.println("[MSG]");
		try (// java 7.0 提共自動關閉的資源
				BufferedReader bf = new BufferedReader(
						new InputStreamReader(new FileInputStream("src//main//java//web//msg.txt"), "UTF8"));) {
			String Read_line = "";

			while ((Read_line = bf.readLine()) != null) {
				String[] sa = Read_line.split("\\|");

				try {
					PreparedStatement pstmt = con.prepareStatement(sql_Common.INSERT_TABLE_MSG);
					pstmt.setString(1, sa[0]);
					pstmt.setString(2, sa[1]);
					pstmt.setString(3, sa[2]);
					pstmt.setString(4, "");
					pstmt.setString(5, sa[0]);
					pstmt.setString(6, sa[1]);
					pstmt.executeUpdate();
					GlobalService.random_time_1_2();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
// MYSQL
//
// DROP DATABASE WESHARE;
// CREATE DATABASE WESHARE;
//
//
