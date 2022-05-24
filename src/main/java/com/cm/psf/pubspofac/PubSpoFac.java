package com.cm.psf.pubspofac;

/* 공공체육시설 */
public class PubSpoFac {

	public PubSpoFac() {

	}

	public PubSpoFac(int psfId, String psfAdministrativeDivision, String psfName, double psfLatitude,
			double psfLongitude, String psfAddress, String psfFacilityType, String psfManagementDepartment,
			String psfMangementGroup, String psfPhoneNumber) {
		super();
		this.psfId = psfId;
		this.psfAdministrativeDivision = psfAdministrativeDivision;
		this.psfName = psfName;
		this.psfLatitude = psfLatitude;
		this.psfLongitude = psfLongitude;
		this.psfAddress = psfAddress;
		this.psfFacilityType = psfFacilityType;
		this.psfManagementDepartment = psfManagementDepartment;
		this.psfMangementGroup = psfMangementGroup;
		this.psfPhoneNumber = psfPhoneNumber;
	}

	private int psfId; // 고유식별번호
	private String psfAdministrativeDivision; // 행정구역
	private String psfName; // 시설 이름
	private double psfLatitude; // 위도
	private double psfLongitude; // 경도
	private String psfAddress; // 시설 도로명 주소
	private String psfFacilityType; // 시설타입 ex) 야구장, 축구장, 테니스장
	private String psfManagementDepartment; // 관리행정팀 ex)체육부
	private String psfMangementGroup; // 관리그룹 ex) 지방자치단체 등
	private String psfPhoneNumber; // 관리행정팀 전화번호

	/* 게터 세터 */
	public int getPsfId() {
		return psfId;
	}

	public void setPsfId(int psfId) {
		this.psfId = psfId;
	}

	public String getPsfAdministrativeDivision() {
		return psfAdministrativeDivision;
	}

	public void setPsfAdministrativeDivision(String psfAdministrativeDivision) {
		this.psfAdministrativeDivision = psfAdministrativeDivision;
	}

	public String getPsfName() {
		return psfName;
	}

	public void setPsfName(String psfName) {
		this.psfName = psfName;
	}

	public double getPsfLatitude() {
		return psfLatitude;
	}

	public void setPsfLatitude(double psfLatitude) {
		this.psfLatitude = psfLatitude;
	}

	public double getPsfLongitude() {
		return psfLongitude;
	}

	public void setPsfLongitude(double psfLongitude) {
		this.psfLongitude = psfLongitude;
	}

	public String getPsfAddress() {
		return psfAddress;
	}

	public void setPsfAddress(String psfAddress) {
		this.psfAddress = psfAddress;
	}

	public String getPsfFacilityType() {
		return psfFacilityType;
	}

	public void setPsfFacilityType(String psfFacilityType) {
		this.psfFacilityType = psfFacilityType;
	}

	public String getPsfManagementDepartment() {
		return psfManagementDepartment;
	}

	public void setPsfManagementDepartment(String psfManagementDepartment) {
		this.psfManagementDepartment = psfManagementDepartment;
	}

	public String getPsfMangementGroup() {
		return psfMangementGroup;
	}

	public void setPsfMangementGroup(String psfMangementGroup) {
		this.psfMangementGroup = psfMangementGroup;
	}

	public String getPsfPhoneNumber() {
		return psfPhoneNumber;
	}

	public void setPsfPhoneNumber(String psfPhoneNumber) {
		this.psfPhoneNumber = psfPhoneNumber;
	}

	@Override
	public String toString() {
		return "PubSpoFac [psfId=" + psfId + ", psfAdministrativeDivision=" + psfAdministrativeDivision + ", psfName="
				+ psfName + ", psfLatitude=" + psfLatitude + ", psfLongitude=" + psfLongitude + ", psfAddress="
				+ psfAddress + ", psfFacilityType=" + psfFacilityType + ", psfManagementDepartment="
				+ psfManagementDepartment + ", psfMangementGroup=" + psfMangementGroup + ", psfPhoneNumber="
				+ psfPhoneNumber + "]";
	}
}
