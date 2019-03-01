package com.unionblue.wechat.model;

import java.io.Serializable;

import com.unionblue.wechat.util.StringUtil;

/*公司信息*/
public class CompanyInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String companyno;
	
	/*原始公司名称*/
	private String rawcompanyname;
	
	/*原始公司地址*/
	private String rawaddress;
	
	/* 英文公司名称 */
	private String compayname;
	
	/* 英文公司地址 */
	private String englishaddress;
	
	/* 联絡人 */
	private String contact;
	
	/* 默认电子邮件 */
	private String email;
	
	/* 默认电话一 */
	private String phone1;
	
	/* 0:公司管理者 1:代理长传者 */
	private Integer status;
	
	private String uniformnumber;
	
	private String imageBase64;
	
	private boolean isReview = true;

	public String getCompanyno() {
		return companyno;
	}

	public void setCompanyno(String companyno) {
		this.companyno = companyno;
	}

	public String getRawcompanyname() {
		return rawcompanyname;
	}

	public void setRawcompanyname(String rawcompanyname) {
		this.rawcompanyname = rawcompanyname;
	}

	public String getRawaddress() {
		return rawaddress;
	}

	public void setRawaddress(String rawaddress) {
		this.rawaddress = rawaddress;
	}

	public String getCompayname() {
		return compayname;
	}

	public void setCompayname(String compayname) {
		this.compayname = (StringUtil.isEmpty(compayname) || compayname.startsWith("等待")) ? "等待蓝联专家翻译企业名称":compayname;
	}

	public String getEnglishaddress() {
		return englishaddress;
	}

	public void setEnglishaddress(String englishaddress) {
		this.englishaddress = (StringUtil.isEmpty(englishaddress) || englishaddress.startsWith("等待")) ? "等待蓝联专家翻译企业地址":englishaddress;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUniformnumber() {
		return uniformnumber;
	}

	public void setUniformnumber(String uniformnumber) {
		this.uniformnumber = uniformnumber;
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		if(!StringUtil.isEmpty(imageBase64)){
			setIsReview(imageBase64);
		}else{
			this.isReview = false;
		}
		this.imageBase64 = imageBase64;
	}

	public boolean getIsReview() {
    	return this.isReview;
	}

	public void setIsReview(String imageBase) {
		String image = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACIAAAAcCAYAAAAEN20fAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAL/SURBVEhLzZdbUuswDIa7uKyGZClN0m2kadkAw9NpcylsAIYmac8DA8O99MZNR7+cGDsDb9Aez6iyZUn/lzgPbof+k6FBLi8v6fT0lI6Pj+no6OhXDRrQgmYzBOT8/JzyPKerqyt6f3+XDYyPj496psZPraFxfX0tmtDG6IAqyzJ6e3uTxF0aNKENhg5eEd4ENkC6aw9tMHRwXi8vLxIE4a796+urfDMdfDwI7NPAICCg2qdpEBwNAvvyGmS73UpQ+TkNPY8O//I665HjOIbVcck7oZ43pLnk9+hE19v+JHSoF3If75DmX+zDWyC2KZjhgBuEEx2fhBybK+HJdqJAsDcfkucgZvYwa3iOhzJ6maZBNpvN15ZycZDTLPbIi2eUBx4NZjMaMEi+yRlkQDMjtxcPGMh8g21DvdG/Ng2yXq8lIH6GZiEL8ToNBaTqt0Bc7OcUugrEqm95PESYfr8Pb4HAVquVmgOARcrEAOlXlPkAqRTIGiAxVcjF0yK/qTf6oRYgVn9jH16DINBYkyBWg5QCUmqQmEGyVaZAJF/NS3jrKEIaACT5pn+91iDL5VKCnz7lhiGl44AcP6MiUs1SBomrkkECAQlEvM7nedHUcx3ApR8/jBcVrf62t0AsK2Py/FQaOn7MwgxQLhVIWQhIyuIAgThAZM518ZjXAGFxq5fZv2Ua5Pn52bJp5JIbTdV6xDDymgNKrLyExfs01fOAzaV+oWp0Pfbq2qTrUDBq6j9NgywWCwk0PumqhouRrwAQL/rkOr40VHkJ+QcKZLGAmEP+qO4jdeo7ARAAlK/7tvQ0yNPTkwSVv6DogAXR7CCiCx1n/4dj3T7v1x9jd6ziFxG53YTzxuQLAAtOjbom3u5Xewtkn6ZBHh8f92oCgkvJ3d2dBB4eHnbu7+/v1cUI17SyLHVw176qKnVVxMU1SRK6vb2VjV0aNKEtl2dc5c/OziRQFIVs4qgaQ8FPr29ubkQLmtCWvxPyywNUeEU4L3w8v2nQgBY0m/Gf/OUk+gdIlHBhv75NoQAAAABJRU5ErkJggg==";
		this.isReview = !imageBase.equals(image);
	}

	@Override
	public String toString() {
		return "CompanyInfo [companyno=" + companyno + ", rawcompanyname=" + rawcompanyname + ", rawaddress="
				+ rawaddress + ", compayname=" + compayname + ", englishaddress=" + englishaddress + ", contact="
				+ contact + ", email=" + email + ", phone1=" + phone1 + ", status=" + status + ", uniformnumber="
				+ uniformnumber + ", imageBase64=" + imageBase64 + ", isReview=" + isReview + "]";
	}

	

    	
	

	

}
