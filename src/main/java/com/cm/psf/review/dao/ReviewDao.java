package com.cm.psf.review.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.cm.psf.member.Member;
import com.cm.psf.pubspofac.PubSpoFac;
import com.cm.psf.review.Review;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class ReviewDao implements IReviewDao {

	private JdbcTemplate template;

	@Autowired
	public ReviewDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	// 마커 클릭 시 해당 psf의 리뷰 목록 보여주기
	@Override
	public List<Review> reviewPsfSelect(final int psfId) {

		List<Review> reviews = null;

		final String sql = "SELECT * FROM review, member WHERE review.member_id = member.member_id AND review.psf_id = ?";

		reviews = template.query(sql, new Object[] { psfId }, new RowMapper<Review>() {
			@Override
			public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
				Review rv = new Review();
				rv.setReviewId(rs.getInt("review_id"));
				rv.setReviewContent(rs.getString("review_content"));
				rv.setReviewGrade(rs.getDouble("review_grade"));

				rv.setReviewDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(rs.getDate("review_date")));
				rv.setMember(
						new Member(rs.getString("member_id"), rs.getString("member_pw"), rs.getString("member_name")));

				rv.setModfiedOrNot(rs.getString("MODIFIED_OR_NOT"));
				return rv;
			}
		});

		if (reviews.isEmpty())
			return null;

		return reviews;
	}

	// 리뷰등록
	@Override
	public int reviewInsert(final Review review) {

		int result = 0;

		final String sql = "INSERT INTO review (review_id, review_content, review_grade, review_date, psf_id, member_id,  MODIFIED_OR_NOT) values ((SELECT NVL(MAX(review_id)+1,0) FROM REVIEW),?,?,?,?,?,'N')";

		result = template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, review.getReviewContent());
				pstmt.setDouble(2, review.getReviewGrade());
				pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
				pstmt.setInt(4, review.getPsf().getPsfId());
				pstmt.setString(5, review.getMember().getMemId());
			}
		});

		return result;
	}

	// 내가 쓴 리뷰 리스트
	@Override
	public List<Review> myReviewSelect(final String memId) {

		List<Review> reviews = null;

		final String sql = "SELECT * FROM review, member, public_sports_facilities WHERE review.member_id = member.member_id AND review.psf_id = public_sports_facilities.psf_id AND review.member_id = ?";

		reviews = template.query(sql, new Object[] { memId }, new RowMapper<Review>() {
			@Override
			public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
				Review rv = new Review();
				rv.setReviewId(rs.getInt("review_id"));
				rv.setReviewContent(rs.getString("review_content"));
				rv.setReviewGrade(rs.getDouble("review_grade"));
				rv.setReviewDate(new SimpleDateFormat("yyyy-MM-dd:HH:mm").format(rs.getDate("review_date")));
				rv.setMember(
						new Member(rs.getString("member_id"), rs.getString("member_pw"), rs.getString("member_name")));
				rv.setPsf(new PubSpoFac(rs.getInt("psf_id"), rs.getString("psf_administrative_division"),
						rs.getString("psf_name"), rs.getDouble("psf_latitude"), rs.getDouble("psf_longitude"),
						rs.getString("psf_address"), rs.getString("psf_facility_type"),
						rs.getString("psf_management_department"), rs.getString("psf_mangement_group"),
						rs.getString("psf_phone_number")));
				rv.setModfiedOrNot(rs.getString("MODIFIED_OR_NOT"));
				return rv;
			}
		});

		if (reviews.isEmpty())
			return null;

		return reviews;
	}

	// 리뷰 삭제
	@Override
	public int reviewDelete(final int reviewId) {

		int result = 0;

		final String sql = "DELETE review WHERE review_id = ?";

		result = template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, reviewId);
			}
		});
		return result;
	}

	// 리뷰 수정
	@Override
	public int reviewUpdate(final Review review) {

		int result = 0;

		final String sql = "UPDATE review SET review_content = ?, review_grade = ?, review_date = sysdate, MODIFIED_OR_NOT ='Y' WHERE review_id = ?";

		result = template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, review.getReviewContent());
				pstmt.setDouble(2, review.getReviewGrade());
				pstmt.setInt(3, review.getReviewId());
			}
		});

		return result;
	}
}
