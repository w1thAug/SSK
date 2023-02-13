package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dto.LangTestLog;
/**
 * @author Jiwon Lee
 * 언어 발달 테스트 기록(lang_test_log) DAO
 * 테스트 기록 삽입
 * 테스트 기록 조회(사용자 별)
 * 테스트 기록 조회(아이디로)
 *
 */
public class LangTestLogDAO {
	private final static String SQLST_INSERT_LANG_TEST_LOG = "insert lang_test_log(user_id, lang_test_date, lang_test_time) values(?,?,?)";
	private final static String SQLST_SELECT_LANG_TEST_LOG_BY_USER = "select * from lang_test_log where user_id = ?";
	private final static String SQLST_SELECT_LANG_TEST_LOG_BY_ID = "select * from lang_test_log where lang_test_log_id = ?";
	
	/*언어 발달 테스트 기록 삽입*/
	public static boolean insertLangTestLog(Connection con, LangTestLog langTestLog) {
		try {
			PreparedStatement pstmt = con.prepareStatement(SQLST_INSERT_LANG_TEST_LOG, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1,langTestLog.getUserId());
			pstmt.setDate(2, langTestLog.getLangTestDate());
			pstmt.setTime(3, langTestLog.getLangTestTime());
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				langTestLog.setLangTestLogId(rs.getInt(1));
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*사용자별 언어 발달 테스트 기록 조회*/
	public static ArrayList<LangTestLog> getLangTestLogByUserId(Connection con, int userId){
		try {
			PreparedStatement pstmt = con.prepareStatement(SQLST_SELECT_LANG_TEST_LOG_BY_USER);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			ArrayList<LangTestLog> langTestLogList = new ArrayList<LangTestLog>();
			while(rs.next()) {
				LangTestLog langTestLog = new LangTestLog();
				langTestLog.setLangTestLogId(rs.getInt(1));
				langTestLog.setUserId(rs.getInt(2));
				langTestLog.setLangTestDate(rs.getDate(3));
				langTestLog.setLangTestTime(rs.getTime(4));
				
				langTestLogList.add(langTestLog);
			}
			return langTestLogList;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/*언어 발달 테스트 기록 조회*/
	public static LangTestLog getLangTestLogById(Connection con, int langTestLogId) {
		try {
			PreparedStatement pstmt = con.prepareStatement(SQLST_SELECT_LANG_TEST_LOG_BY_ID);
			pstmt.setInt(1, langTestLogId);
			
			ResultSet rs = pstmt.executeQuery();
			
			LangTestLog langTestLog = new LangTestLog();
			while(rs.next()) {
				langTestLog.setLangTestLogId(rs.getInt(1));
				langTestLog.setUserId(rs.getInt(2));
				langTestLog.setLangTestDate(rs.getDate(3));
				langTestLog.setLangTestTime(rs.getTime(4));
			}
			return langTestLog;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
