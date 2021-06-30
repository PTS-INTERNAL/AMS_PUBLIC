package com.ams.model;

import java.util.List;

public class ExportLoanMove {
	
	private List<LoanAssetModel> lstBorrow;
	private LoanCouponModel borrowCoupon;
	
	public LoanCouponModel getBorrowCoupon() {
		return borrowCoupon;
	}
	public void setLoanCoupon(LoanCouponModel borrowCoupon) {
		this.borrowCoupon = borrowCoupon;
	}
	private String codeExport;
	public String getCodeExport() {
		return codeExport;
	}
	public void setCodeExport(String codeExport) {
		this.codeExport = codeExport;
	}
	private String companyMaster;
	private String companyClient;
	private String confirmComment;
	public List<LoanAssetModel> getLstBorrow() {
		return lstBorrow;
	}
	public void setLstBorrow(List<LoanAssetModel> lstBorrow) {
		this.lstBorrow = lstBorrow;
	}
	public String getCompanyMaster() {
		return companyMaster;
	}
	public void setCompanyMaster(String companyMaster) {
		this.companyMaster = companyMaster;
	}
	public String getCompanyClient() {
		return companyClient;
	}
	public void setCompanyClient(String companyClient) {
		this.companyClient = companyClient;
	}
	public String getConfirmComment() {
		return confirmComment;
	}
	public void setConfirmComment(String confirmComment) {
		this.confirmComment = confirmComment;
	}
	public ExportLoanMove(List<LoanAssetModel> lstBorrow, String companyMaster, String companyClient,
			String confirmComment, String code) {
		super();
		this.lstBorrow = lstBorrow;
		this.companyMaster = companyMaster;
		this.companyClient = companyClient;
		this.confirmComment = confirmComment;
		this.codeExport = code;
	}
	
	public ExportLoanMove(List<LoanAssetModel> lstBorrow, LoanCouponModel coupon) {
		super();
		this.lstBorrow = lstBorrow;
		this.borrowCoupon = coupon;
	}
	
	
	

}
