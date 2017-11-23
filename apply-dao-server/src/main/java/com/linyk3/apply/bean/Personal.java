package com.linyk3.apply.bean;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="personal")
public class Personal {
	private ObjectId _id;
	private String name; // required
	private String sex; // required
	private String birthday; // required
	private String school; // required
	private String diploma; // required
	private String major; // required
	  private String majorCategory; // required
	  private String locationId; // required
	  private String locationName; // required
	  private String positions; // required
	  private int salary; // required
	  private Collection favor;
	  private Collection ignore;
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getDiploma() {
		return diploma;
	}
	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getMajorCategory() {
		return majorCategory;
	}
	public void setMajorCategory(String majorCategory) {
		this.majorCategory = majorCategory;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getPositions() {
		return positions;
	}
	public void setPositions(String positions) {
		this.positions = positions;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Collection getFavor() {
		return favor;
	}
	public void setFavor(Collection favor) {
		this.favor = favor;
	}
	public Collection getIgnore() {
		return ignore;
	}
	public void setIgnore(Collection ignore) {
		this.ignore = ignore;
	}
	@Override
	public String toString() {
		return "Personal [_id=" + _id + ", name=" + name + ", sex=" + sex + ", birthday=" + birthday + ", school="
				+ school + ", diploma=" + diploma + ", major=" + major + ", majorCategory=" + majorCategory
				+ ", locationId=" + locationId + ", locationName=" + locationName + ", positions=" + positions
				+ ", salary=" + salary + ", favor=" + favor + ", ignore=" + ignore + "]";
	}
	
	
}
