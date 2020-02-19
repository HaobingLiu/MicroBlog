package com.ice.po;

public class Mood {
	private int u_id;
	private String m_date;
	private String m_images;
	private String m_content;
	private int m_comment_num;
	private int m_transmit_num;
	private String u_m_name;
	private String m_u_images;
	
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int uId) {
		u_id = uId;
	}
	public String getM_date() {
		return m_date;
	}
	public void setM_date(String mDate) {
		m_date = mDate;
	}
	public String getM_images() {
		return m_images;
	}
	public void setM_images(String mImages) {
		m_images = mImages;
	}
	public String getM_content() {
		return m_content;
	}
	public void setM_content(String mContent) {
		m_content = mContent;
	}
	public int getM_comment_num() {
		return m_comment_num;
	}
	public void setM_comment_num(int mCommentNum) {
		m_comment_num = mCommentNum;
	}
	public int getM_transmit_num() {
		return m_transmit_num;
	}
	public void setM_transmit_num(int mTransmitNum) {
		m_transmit_num = mTransmitNum;
	}
	int m_id;
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	
	public String getM_u_images() {
		return m_u_images;
	}
	public void setM_u_images(String m_u_images) {
		this.m_u_images = m_u_images;
	}
	public String getU_m_name() {
		return u_m_name;
	}
	public void setU_m_name(String uMName) {
		u_m_name = uMName;
	}

}
