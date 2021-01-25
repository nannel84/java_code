package VO;

public class MemberVO {

//변수선언 (private)
	private String rownum;
	private String name;
	private String phone;
	private String address;
	private String relation;
	private String name1;
	private String num;

	
// 생성자 	
	public MemberVO() {
	}
	
	public MemberVO(String name) {
		this.name = name;
	}
	
	
	
	public MemberVO(String name1, String num) {
		this.name1 = name1;
		this.num = num;
	}

	public MemberVO(String name, String phone, String address, String relation) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.relation = relation;
	}

	public MemberVO(String rownum, String name, String phone, String address, String relation) {
		this.rownum = rownum;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.relation = relation;
	}
	


	public MemberVO(String rownum, String name, String phone, String address, String relation, String name1,
			String num) {
		this.rownum = rownum;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.relation = relation;
		this.name1 = name1;
		this.num = num;
	}

	public MemberVO(String name, String phone, String address, String relation, String name1, String num) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.relation = relation;
		this.name = name1;
		this.num = num;
	}

	
//private로 만들어서  get/set  적용	
	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	@Override
	public String toString() {
		return "[이름 : " + name
				+ " , 전화번호 : " + phone
				+ " , 주소 : " + address
				+ " , 종류 : " + relation
				+ "]";
	}
	
	
}
