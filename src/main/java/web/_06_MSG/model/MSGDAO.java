package web._06_MSG.model;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web._00_init.GlobalService;

public class MSGDAO {

	private DataSource ds = null;
	String result = "null";

	public MSGDAO() {
		Context ctx = null;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String Insert_MSG(MSGBean msgb, InputStream image, long imagesize) {
		String ans = "FALSE";
		try (Connection con = ds.getConnection();
				CallableStatement cs = con.prepareCall("{? =CALL insert_MSG(?,?,?,?,?)}");) {

			System.out.println("filename" + msgb.getMSGFILENAME());

			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, msgb.getMSGSOURCEID());
			cs.setString(3, msgb.getMSGENDID());
			cs.setString(4, msgb.getMSGTEXT());
			cs.setBinaryStream(5, image, imagesize);
			if (image == null) {
				cs.setString(6, null);
			} else {
				cs.setString(6, String.valueOf(new Date().getTime()));
			}
			cs.executeUpdate();
			ans = "TRUE";
		} catch (Exception e) {
			ans = "FALSE";
			e.printStackTrace();
		}
		return ans;
	}

	public Collection<MSGBean> FindMSGByKey(String INDID) {
		Collection<MSGBean> coll = new ArrayList<MSGBean>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(
						"SELECT m.*,i.indname,i2.indname FROM msg AS m, ind AS i,ind AS i2 WHERE (i2.indid=m.msgendid)AND(i.indid=m.msgsourceid) AND(MSGNO IN(SELECT LASTMSGNO FROM MSG_ROOM WHERE INDID1=? OR INDID2=?))");) {
			pstmt.setString(1, INDID);
			pstmt.setString(2, INDID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MSGBean msgb = new MSGBean();
				msgb.setMSGNO(rs.getInt(1));
				msgb.setMSGSTATUS(rs.getInt(2));
				msgb.setPOSTDATE(rs.getTimestamp(3));
				msgb.setMSGSOURCEID(rs.getString(4));
				msgb.setMSGENDID(rs.getString(5));
				msgb.setMSGTEXT(rs.getString(6));
				// msgb.setMSGIMAGE(rs.getBlob(7));
				msgb.setMSGFILENAME(rs.getString(8));
				msgb.setROOMNO(rs.getInt(9));
				msgb.setMSGSOURCENAME(rs.getString(10));
				msgb.setMSGENDNAME(rs.getString(11));
				coll.add(msgb);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coll;
	}

	public Collection<MSGBean> FindMSGByRoomNoKey(String INDID) {
		Collection<MSGBean> coll = new ArrayList<MSGBean>();
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(
						"SELECT m.*,i.indname,i2.indname FROM msg AS m ,ind AS i,ind AS i2 WHERE (ROOMNO=?) AND (m.msgsourceid=i.indid) AND (m.msgendid=i2.indid) ORDER BY postdate;");) {
			pstmt.setString(1, INDID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MSGBean msgb = new MSGBean();
				msgb.setMSGNO(rs.getInt(1));
				msgb.setMSGSTATUS(rs.getInt(2));
				msgb.setPOSTDATE(rs.getTimestamp(3));
				msgb.setMSGSOURCEID(rs.getString(4));
				msgb.setMSGENDID(rs.getString(5));
				msgb.setMSGTEXT(rs.getString(6));
				// msgb.setMSGIMAGE(rs.getBlob(7));
				msgb.setMSGFILENAME(rs.getString(8));
				msgb.setROOMNO(rs.getInt(9));
				msgb.setMSGSOURCENAME(rs.getString(10));
				msgb.setMSGENDNAME(rs.getString(11));
				coll.add(msgb);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coll;
	}

	public String READ_MSG(String key) {
		String ans = "FALSE";
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("UPDATE MSG SET MSGSTATUS=1 WHERE ROOMNO = ?");) {
			pstmt.setString(1, key);
			pstmt.executeUpdate();
			ans = "TRUE";
		} catch (Exception e) {
			ans = "FALSE";
			e.printStackTrace();
		}
		return ans;
	}
}
