package com.cm.psf.pubspofac;

/* ����ü���ü� */
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

	private int psfId; // �����ĺ���ȣ
	private String psfAdministrativeDivision; // ��������
	private String psfName; // �ü� �̸�
	private double psfLatitude; // ����
	private double psfLongitude; // �浵
	private String psfAddress; // �ü� ���θ� �ּ�
	private String psfFacilityType; // �ü�Ÿ�� ex) �߱���, �౸��, �״Ͻ���
	private String psfManagementDepartment; // ���������� ex)ü����
	private String psfMangementGroup; // �����׷� ex) ������ġ��ü ��
	private String psfPhoneNumber; // ���������� ��ȭ��ȣ

	/* ���� ���� */
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
