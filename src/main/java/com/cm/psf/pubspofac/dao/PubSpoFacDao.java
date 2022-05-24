package com.cm.psf.pubspofac.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cm.psf.pubspofac.PubSpoFac;
import com.cm.psf.pubspofac.PubSpoFacSelect;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository // 퍼시스턴스 레이어, DB나 파일같은 외부 I/O 작업을 처리
public class PubSpoFacDao implements IPubSpoFacDao {

	private JdbcTemplate template;

	
	/* bean에 등록한 db정보를 dataSource 파라미터로 가져옴 */

	/* db 접속 */
	@Autowired
	public PubSpoFacDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	/* 검색된 데이터들 보내기 */
	@Override
	public List<PubSpoFac> pubSpoFacSelect(final PubSpoFacSelect pubSpoFacS) {
		List<PubSpoFac> psfs = null;
		String sql = null;

		if (pubSpoFacS.getpSelectOne().equals("user") && pubSpoFacS.getpSelectTwo().equals("ALL")) {
			sql = "SELECT * FROM public_sports_facilities";
			System.out.println("user search");
		} else if(pubSpoFacS.getpSelectOne().equals("user")) {
			sql = "SELECT * FROM public_sports_facilities where psf_facility_type='" + pubSpoFacS.getpSelectTwo() + "'";
			System.out.println("user search");
		} else if(pubSpoFacS.getpSelectTwo().equals("ALL")) {
			sql = "SELECT * FROM public_sports_facilities where psf_administrative_division='" + pubSpoFacS.getpSelectOne() + "'";
			
		} else {
			sql = "SELECT * FROM public_sports_facilities where psf_administrative_division='"+ pubSpoFacS.getpSelectOne() + "' and psf_facility_type='" + pubSpoFacS.getpSelectTwo() + "'";

		}
		

		/* new Object[] { pubSpoFacS.getpSelectOne(), pubSpoFacS.getpSelectTwo() }*/
		psfs = template.query(sql, new RowMapper<PubSpoFac>() {

			@Override
			public PubSpoFac mapRow(ResultSet rs, int rowNum) throws SQLException {
				PubSpoFac psf = new PubSpoFac();
				psf.setPsfId(rs.getInt("psf_id"));
				psf.setPsfAdministrativeDivision(rs.getString("psf_administrative_division"));
				psf.setPsfName(rs.getString("psf_name"));
				psf.setPsfLatitude(rs.getDouble("psf_latitude"));
				psf.setPsfLongitude(rs.getDouble("psf_longitude"));
				psf.setPsfAddress(rs.getString("psf_address"));
				psf.setPsfFacilityType(rs.getString("psf_facility_type"));
				psf.setPsfManagementDepartment(rs.getString("psf_management_department"));
				psf.setPsfMangementGroup(rs.getString("psf_mangement_group"));
				psf.setPsfPhoneNumber(rs.getString("psf_phone_number"));

				return psf;
			}
		});

		return psfs;
	}
}
