package com.ktdsuniversity.edu.app.v1;

public class Company {
	private String companyName; // 근무중인 회사의 이름
	private String job; // 직급(사원, 대리, 과장, 차장, 부장, 이사, 대표)
	private String address; // 근무중인 회사의 주소

	public Company(String companyName, String job, String address) {
		this.companyName = companyName;
		this.job = job;
		this.address = address;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public String getJob() {
		return this.job;
	}

	public String getAddress() {
		return this.address;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Company [회사명 =" + companyName + ", 직급 =" + job + ", 주소 =" + address + "]";
	}

}
