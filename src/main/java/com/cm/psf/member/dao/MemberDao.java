package com.cm.psf.member.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cm.psf.member.Member;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class MemberDao implements IMemberDao {

	private JdbcTemplate template;
	
	@Autowired
	public MemberDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	//회원가입
	@Override
	public int memberInsert(final Member member) {
		
		int result = 0;
		
		final String sql = "INSERT INTO member (member_id, member_pw, member_name) values (?,?,?)";
		
		result = template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemId());
				pstmt.setString(2, member.getMemPw());
				pstmt.setString(3, member.getMemName());
			}
		});
		
		return result;	
	}
	
	//로그인
	@Override
	public Member memberSelect(final Member member) {
		
		List<Member> members = null;
		
		final String sql = "SELECT * FROM member WHERE member_id = ? AND member_pw = ?";

		members = template.query(sql, new Object[]{member.getMemId(), member.getMemPw()}, new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member mem = new Member();
				mem.setMemId(rs.getString("member_id"));
				mem.setMemPw(rs.getString("member_pw"));
				mem.setMemName(rs.getString("member_name"));

				return mem;
			}		
		});
		
		if(members.isEmpty()) 
			return null;
		
		return members.get(0);	
	}

	//회원정보 수정
	@Override
	public int memberUpdate(final Member member) {
		
		int result = 0;
		
		final String sql = "UPDATE member SET member_pw = ?, member_name = ? WHERE member_id = ?";
		
		result = template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemPw());
				pstmt.setString(2, member.getMemName());
				pstmt.setString(3, member.getMemId());
			}
		});
		return result;
	}
	
	//회원탈퇴
	@Override
	public int memberDelete(final Member member) {
		
		int result = 0;
		
		final String sql = "DELETE member WHERE member_id = ? AND member_pw = ?";
		
		result = template.update(sql, new PreparedStatementSetter() {
					
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemId());
				pstmt.setString(2, member.getMemPw());
			}
		});
		return result;
	}
	
	//중복검사
	@Override
	public boolean isMemberId(final Member member) {
		
		List<Member> members = null;
		
		final String sql = "SELECT * FROM member WHERE member_id = ?";

		members = template.query(sql, new Object[]{member.getMemId()}, new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member mem = new Member();
				mem.setMemId(rs.getString("member_id"));
				mem.setMemPw(rs.getString("member_pw"));
				mem.setMemName(rs.getString("member_name"));

				return mem;
			}		
		});
				
		return members.isEmpty();	
	}
}