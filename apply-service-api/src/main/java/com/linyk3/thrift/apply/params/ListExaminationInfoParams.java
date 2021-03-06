/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.linyk3.thrift.apply.params;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2016-4-12")
public class ListExaminationInfoParams implements org.apache.thrift.TBase<ListExaminationInfoParams, ListExaminationInfoParams._Fields>, java.io.Serializable, Cloneable, Comparable<ListExaminationInfoParams> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ListExaminationInfoParams");

  private static final org.apache.thrift.protocol.TField EXAMINATION_ID_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("examinationIdList", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField COMPANY_ID_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("companyIdList", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField SCHOOL_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("schoolList", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField STATUS_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("statusList", org.apache.thrift.protocol.TType.LIST, (short)4);
  private static final org.apache.thrift.protocol.TField BEGIN_DATE_FIELD_DESC = new org.apache.thrift.protocol.TField("beginDate", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField END_DATE_FIELD_DESC = new org.apache.thrift.protocol.TField("endDate", org.apache.thrift.protocol.TType.STRING, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ListExaminationInfoParamsStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ListExaminationInfoParamsTupleSchemeFactory());
  }

  /**
   * 笔试Id列表 选填
   */
  public List<String> examinationIdList; // required
  /**
   * 公司Id列表 选填
   */
  public List<String> companyIdList; // required
  /**
   * 学校列表 选填
   */
  public List<String> schoolList; // required
  /**
   * 笔试状态列表 选填
   */
  public List<Integer> statusList; // required
  /**
   * 开始日期 选填
   */
  public String beginDate; // required
  /**
   * 结束日期 选填
   */
  public String endDate; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 笔试Id列表 选填
     */
    EXAMINATION_ID_LIST((short)1, "examinationIdList"),
    /**
     * 公司Id列表 选填
     */
    COMPANY_ID_LIST((short)2, "companyIdList"),
    /**
     * 学校列表 选填
     */
    SCHOOL_LIST((short)3, "schoolList"),
    /**
     * 笔试状态列表 选填
     */
    STATUS_LIST((short)4, "statusList"),
    /**
     * 开始日期 选填
     */
    BEGIN_DATE((short)5, "beginDate"),
    /**
     * 结束日期 选填
     */
    END_DATE((short)6, "endDate");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // EXAMINATION_ID_LIST
          return EXAMINATION_ID_LIST;
        case 2: // COMPANY_ID_LIST
          return COMPANY_ID_LIST;
        case 3: // SCHOOL_LIST
          return SCHOOL_LIST;
        case 4: // STATUS_LIST
          return STATUS_LIST;
        case 5: // BEGIN_DATE
          return BEGIN_DATE;
        case 6: // END_DATE
          return END_DATE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.EXAMINATION_ID_LIST, new org.apache.thrift.meta_data.FieldMetaData("examinationIdList", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.COMPANY_ID_LIST, new org.apache.thrift.meta_data.FieldMetaData("companyIdList", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.SCHOOL_LIST, new org.apache.thrift.meta_data.FieldMetaData("schoolList", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.STATUS_LIST, new org.apache.thrift.meta_data.FieldMetaData("statusList", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32))));
    tmpMap.put(_Fields.BEGIN_DATE, new org.apache.thrift.meta_data.FieldMetaData("beginDate", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.END_DATE, new org.apache.thrift.meta_data.FieldMetaData("endDate", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ListExaminationInfoParams.class, metaDataMap);
  }

  public ListExaminationInfoParams() {
  }

  public ListExaminationInfoParams(
    List<String> examinationIdList,
    List<String> companyIdList,
    List<String> schoolList,
    List<Integer> statusList,
    String beginDate,
    String endDate)
  {
    this();
    this.examinationIdList = examinationIdList;
    this.companyIdList = companyIdList;
    this.schoolList = schoolList;
    this.statusList = statusList;
    this.beginDate = beginDate;
    this.endDate = endDate;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ListExaminationInfoParams(ListExaminationInfoParams other) {
    if (other.isSetExaminationIdList()) {
      List<String> __this__examinationIdList = new ArrayList<String>(other.examinationIdList);
      this.examinationIdList = __this__examinationIdList;
    }
    if (other.isSetCompanyIdList()) {
      List<String> __this__companyIdList = new ArrayList<String>(other.companyIdList);
      this.companyIdList = __this__companyIdList;
    }
    if (other.isSetSchoolList()) {
      List<String> __this__schoolList = new ArrayList<String>(other.schoolList);
      this.schoolList = __this__schoolList;
    }
    if (other.isSetStatusList()) {
      List<Integer> __this__statusList = new ArrayList<Integer>(other.statusList);
      this.statusList = __this__statusList;
    }
    if (other.isSetBeginDate()) {
      this.beginDate = other.beginDate;
    }
    if (other.isSetEndDate()) {
      this.endDate = other.endDate;
    }
  }

  public ListExaminationInfoParams deepCopy() {
    return new ListExaminationInfoParams(this);
  }

  @Override
  public void clear() {
    this.examinationIdList = null;
    this.companyIdList = null;
    this.schoolList = null;
    this.statusList = null;
    this.beginDate = null;
    this.endDate = null;
  }

  public int getExaminationIdListSize() {
    return (this.examinationIdList == null) ? 0 : this.examinationIdList.size();
  }

  public java.util.Iterator<String> getExaminationIdListIterator() {
    return (this.examinationIdList == null) ? null : this.examinationIdList.iterator();
  }

  public void addToExaminationIdList(String elem) {
    if (this.examinationIdList == null) {
      this.examinationIdList = new ArrayList<String>();
    }
    this.examinationIdList.add(elem);
  }

  /**
   * 笔试Id列表 选填
   */
  public List<String> getExaminationIdList() {
    return this.examinationIdList;
  }

  /**
   * 笔试Id列表 选填
   */
  public ListExaminationInfoParams setExaminationIdList(List<String> examinationIdList) {
    this.examinationIdList = examinationIdList;
    return this;
  }

  public void unsetExaminationIdList() {
    this.examinationIdList = null;
  }

  /** Returns true if field examinationIdList is set (has been assigned a value) and false otherwise */
  public boolean isSetExaminationIdList() {
    return this.examinationIdList != null;
  }

  public void setExaminationIdListIsSet(boolean value) {
    if (!value) {
      this.examinationIdList = null;
    }
  }

  public int getCompanyIdListSize() {
    return (this.companyIdList == null) ? 0 : this.companyIdList.size();
  }

  public java.util.Iterator<String> getCompanyIdListIterator() {
    return (this.companyIdList == null) ? null : this.companyIdList.iterator();
  }

  public void addToCompanyIdList(String elem) {
    if (this.companyIdList == null) {
      this.companyIdList = new ArrayList<String>();
    }
    this.companyIdList.add(elem);
  }

  /**
   * 公司Id列表 选填
   */
  public List<String> getCompanyIdList() {
    return this.companyIdList;
  }

  /**
   * 公司Id列表 选填
   */
  public ListExaminationInfoParams setCompanyIdList(List<String> companyIdList) {
    this.companyIdList = companyIdList;
    return this;
  }

  public void unsetCompanyIdList() {
    this.companyIdList = null;
  }

  /** Returns true if field companyIdList is set (has been assigned a value) and false otherwise */
  public boolean isSetCompanyIdList() {
    return this.companyIdList != null;
  }

  public void setCompanyIdListIsSet(boolean value) {
    if (!value) {
      this.companyIdList = null;
    }
  }

  public int getSchoolListSize() {
    return (this.schoolList == null) ? 0 : this.schoolList.size();
  }

  public java.util.Iterator<String> getSchoolListIterator() {
    return (this.schoolList == null) ? null : this.schoolList.iterator();
  }

  public void addToSchoolList(String elem) {
    if (this.schoolList == null) {
      this.schoolList = new ArrayList<String>();
    }
    this.schoolList.add(elem);
  }

  /**
   * 学校列表 选填
   */
  public List<String> getSchoolList() {
    return this.schoolList;
  }

  /**
   * 学校列表 选填
   */
  public ListExaminationInfoParams setSchoolList(List<String> schoolList) {
    this.schoolList = schoolList;
    return this;
  }

  public void unsetSchoolList() {
    this.schoolList = null;
  }

  /** Returns true if field schoolList is set (has been assigned a value) and false otherwise */
  public boolean isSetSchoolList() {
    return this.schoolList != null;
  }

  public void setSchoolListIsSet(boolean value) {
    if (!value) {
      this.schoolList = null;
    }
  }

  public int getStatusListSize() {
    return (this.statusList == null) ? 0 : this.statusList.size();
  }

  public java.util.Iterator<Integer> getStatusListIterator() {
    return (this.statusList == null) ? null : this.statusList.iterator();
  }

  public void addToStatusList(int elem) {
    if (this.statusList == null) {
      this.statusList = new ArrayList<Integer>();
    }
    this.statusList.add(elem);
  }

  /**
   * 笔试状态列表 选填
   */
  public List<Integer> getStatusList() {
    return this.statusList;
  }

  /**
   * 笔试状态列表 选填
   */
  public ListExaminationInfoParams setStatusList(List<Integer> statusList) {
    this.statusList = statusList;
    return this;
  }

  public void unsetStatusList() {
    this.statusList = null;
  }

  /** Returns true if field statusList is set (has been assigned a value) and false otherwise */
  public boolean isSetStatusList() {
    return this.statusList != null;
  }

  public void setStatusListIsSet(boolean value) {
    if (!value) {
      this.statusList = null;
    }
  }

  /**
   * 开始日期 选填
   */
  public String getBeginDate() {
    return this.beginDate;
  }

  /**
   * 开始日期 选填
   */
  public ListExaminationInfoParams setBeginDate(String beginDate) {
    this.beginDate = beginDate;
    return this;
  }

  public void unsetBeginDate() {
    this.beginDate = null;
  }

  /** Returns true if field beginDate is set (has been assigned a value) and false otherwise */
  public boolean isSetBeginDate() {
    return this.beginDate != null;
  }

  public void setBeginDateIsSet(boolean value) {
    if (!value) {
      this.beginDate = null;
    }
  }

  /**
   * 结束日期 选填
   */
  public String getEndDate() {
    return this.endDate;
  }

  /**
   * 结束日期 选填
   */
  public ListExaminationInfoParams setEndDate(String endDate) {
    this.endDate = endDate;
    return this;
  }

  public void unsetEndDate() {
    this.endDate = null;
  }

  /** Returns true if field endDate is set (has been assigned a value) and false otherwise */
  public boolean isSetEndDate() {
    return this.endDate != null;
  }

  public void setEndDateIsSet(boolean value) {
    if (!value) {
      this.endDate = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case EXAMINATION_ID_LIST:
      if (value == null) {
        unsetExaminationIdList();
      } else {
        setExaminationIdList((List<String>)value);
      }
      break;

    case COMPANY_ID_LIST:
      if (value == null) {
        unsetCompanyIdList();
      } else {
        setCompanyIdList((List<String>)value);
      }
      break;

    case SCHOOL_LIST:
      if (value == null) {
        unsetSchoolList();
      } else {
        setSchoolList((List<String>)value);
      }
      break;

    case STATUS_LIST:
      if (value == null) {
        unsetStatusList();
      } else {
        setStatusList((List<Integer>)value);
      }
      break;

    case BEGIN_DATE:
      if (value == null) {
        unsetBeginDate();
      } else {
        setBeginDate((String)value);
      }
      break;

    case END_DATE:
      if (value == null) {
        unsetEndDate();
      } else {
        setEndDate((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case EXAMINATION_ID_LIST:
      return getExaminationIdList();

    case COMPANY_ID_LIST:
      return getCompanyIdList();

    case SCHOOL_LIST:
      return getSchoolList();

    case STATUS_LIST:
      return getStatusList();

    case BEGIN_DATE:
      return getBeginDate();

    case END_DATE:
      return getEndDate();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case EXAMINATION_ID_LIST:
      return isSetExaminationIdList();
    case COMPANY_ID_LIST:
      return isSetCompanyIdList();
    case SCHOOL_LIST:
      return isSetSchoolList();
    case STATUS_LIST:
      return isSetStatusList();
    case BEGIN_DATE:
      return isSetBeginDate();
    case END_DATE:
      return isSetEndDate();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ListExaminationInfoParams)
      return this.equals((ListExaminationInfoParams)that);
    return false;
  }

  public boolean equals(ListExaminationInfoParams that) {
    if (that == null)
      return false;

    boolean this_present_examinationIdList = true && this.isSetExaminationIdList();
    boolean that_present_examinationIdList = true && that.isSetExaminationIdList();
    if (this_present_examinationIdList || that_present_examinationIdList) {
      if (!(this_present_examinationIdList && that_present_examinationIdList))
        return false;
      if (!this.examinationIdList.equals(that.examinationIdList))
        return false;
    }

    boolean this_present_companyIdList = true && this.isSetCompanyIdList();
    boolean that_present_companyIdList = true && that.isSetCompanyIdList();
    if (this_present_companyIdList || that_present_companyIdList) {
      if (!(this_present_companyIdList && that_present_companyIdList))
        return false;
      if (!this.companyIdList.equals(that.companyIdList))
        return false;
    }

    boolean this_present_schoolList = true && this.isSetSchoolList();
    boolean that_present_schoolList = true && that.isSetSchoolList();
    if (this_present_schoolList || that_present_schoolList) {
      if (!(this_present_schoolList && that_present_schoolList))
        return false;
      if (!this.schoolList.equals(that.schoolList))
        return false;
    }

    boolean this_present_statusList = true && this.isSetStatusList();
    boolean that_present_statusList = true && that.isSetStatusList();
    if (this_present_statusList || that_present_statusList) {
      if (!(this_present_statusList && that_present_statusList))
        return false;
      if (!this.statusList.equals(that.statusList))
        return false;
    }

    boolean this_present_beginDate = true && this.isSetBeginDate();
    boolean that_present_beginDate = true && that.isSetBeginDate();
    if (this_present_beginDate || that_present_beginDate) {
      if (!(this_present_beginDate && that_present_beginDate))
        return false;
      if (!this.beginDate.equals(that.beginDate))
        return false;
    }

    boolean this_present_endDate = true && this.isSetEndDate();
    boolean that_present_endDate = true && that.isSetEndDate();
    if (this_present_endDate || that_present_endDate) {
      if (!(this_present_endDate && that_present_endDate))
        return false;
      if (!this.endDate.equals(that.endDate))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_examinationIdList = true && (isSetExaminationIdList());
    list.add(present_examinationIdList);
    if (present_examinationIdList)
      list.add(examinationIdList);

    boolean present_companyIdList = true && (isSetCompanyIdList());
    list.add(present_companyIdList);
    if (present_companyIdList)
      list.add(companyIdList);

    boolean present_schoolList = true && (isSetSchoolList());
    list.add(present_schoolList);
    if (present_schoolList)
      list.add(schoolList);

    boolean present_statusList = true && (isSetStatusList());
    list.add(present_statusList);
    if (present_statusList)
      list.add(statusList);

    boolean present_beginDate = true && (isSetBeginDate());
    list.add(present_beginDate);
    if (present_beginDate)
      list.add(beginDate);

    boolean present_endDate = true && (isSetEndDate());
    list.add(present_endDate);
    if (present_endDate)
      list.add(endDate);

    return list.hashCode();
  }

  @Override
  public int compareTo(ListExaminationInfoParams other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetExaminationIdList()).compareTo(other.isSetExaminationIdList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExaminationIdList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.examinationIdList, other.examinationIdList);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCompanyIdList()).compareTo(other.isSetCompanyIdList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCompanyIdList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.companyIdList, other.companyIdList);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSchoolList()).compareTo(other.isSetSchoolList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSchoolList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.schoolList, other.schoolList);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStatusList()).compareTo(other.isSetStatusList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStatusList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.statusList, other.statusList);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBeginDate()).compareTo(other.isSetBeginDate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBeginDate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.beginDate, other.beginDate);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEndDate()).compareTo(other.isSetEndDate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEndDate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.endDate, other.endDate);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ListExaminationInfoParams(");
    boolean first = true;

    sb.append("examinationIdList:");
    if (this.examinationIdList == null) {
      sb.append("null");
    } else {
      sb.append(this.examinationIdList);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("companyIdList:");
    if (this.companyIdList == null) {
      sb.append("null");
    } else {
      sb.append(this.companyIdList);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("schoolList:");
    if (this.schoolList == null) {
      sb.append("null");
    } else {
      sb.append(this.schoolList);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("statusList:");
    if (this.statusList == null) {
      sb.append("null");
    } else {
      sb.append(this.statusList);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("beginDate:");
    if (this.beginDate == null) {
      sb.append("null");
    } else {
      sb.append(this.beginDate);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("endDate:");
    if (this.endDate == null) {
      sb.append("null");
    } else {
      sb.append(this.endDate);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ListExaminationInfoParamsStandardSchemeFactory implements SchemeFactory {
    public ListExaminationInfoParamsStandardScheme getScheme() {
      return new ListExaminationInfoParamsStandardScheme();
    }
  }

  private static class ListExaminationInfoParamsStandardScheme extends StandardScheme<ListExaminationInfoParams> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ListExaminationInfoParams struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // EXAMINATION_ID_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list128 = iprot.readListBegin();
                struct.examinationIdList = new ArrayList<String>(_list128.size);
                String _elem129;
                for (int _i130 = 0; _i130 < _list128.size; ++_i130)
                {
                  _elem129 = iprot.readString();
                  struct.examinationIdList.add(_elem129);
                }
                iprot.readListEnd();
              }
              struct.setExaminationIdListIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // COMPANY_ID_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list131 = iprot.readListBegin();
                struct.companyIdList = new ArrayList<String>(_list131.size);
                String _elem132;
                for (int _i133 = 0; _i133 < _list131.size; ++_i133)
                {
                  _elem132 = iprot.readString();
                  struct.companyIdList.add(_elem132);
                }
                iprot.readListEnd();
              }
              struct.setCompanyIdListIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SCHOOL_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list134 = iprot.readListBegin();
                struct.schoolList = new ArrayList<String>(_list134.size);
                String _elem135;
                for (int _i136 = 0; _i136 < _list134.size; ++_i136)
                {
                  _elem135 = iprot.readString();
                  struct.schoolList.add(_elem135);
                }
                iprot.readListEnd();
              }
              struct.setSchoolListIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // STATUS_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list137 = iprot.readListBegin();
                struct.statusList = new ArrayList<Integer>(_list137.size);
                int _elem138;
                for (int _i139 = 0; _i139 < _list137.size; ++_i139)
                {
                  _elem138 = iprot.readI32();
                  struct.statusList.add(_elem138);
                }
                iprot.readListEnd();
              }
              struct.setStatusListIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // BEGIN_DATE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.beginDate = iprot.readString();
              struct.setBeginDateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // END_DATE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.endDate = iprot.readString();
              struct.setEndDateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ListExaminationInfoParams struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.examinationIdList != null) {
        oprot.writeFieldBegin(EXAMINATION_ID_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.examinationIdList.size()));
          for (String _iter140 : struct.examinationIdList)
          {
            oprot.writeString(_iter140);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.companyIdList != null) {
        oprot.writeFieldBegin(COMPANY_ID_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.companyIdList.size()));
          for (String _iter141 : struct.companyIdList)
          {
            oprot.writeString(_iter141);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.schoolList != null) {
        oprot.writeFieldBegin(SCHOOL_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.schoolList.size()));
          for (String _iter142 : struct.schoolList)
          {
            oprot.writeString(_iter142);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.statusList != null) {
        oprot.writeFieldBegin(STATUS_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, struct.statusList.size()));
          for (int _iter143 : struct.statusList)
          {
            oprot.writeI32(_iter143);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.beginDate != null) {
        oprot.writeFieldBegin(BEGIN_DATE_FIELD_DESC);
        oprot.writeString(struct.beginDate);
        oprot.writeFieldEnd();
      }
      if (struct.endDate != null) {
        oprot.writeFieldBegin(END_DATE_FIELD_DESC);
        oprot.writeString(struct.endDate);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ListExaminationInfoParamsTupleSchemeFactory implements SchemeFactory {
    public ListExaminationInfoParamsTupleScheme getScheme() {
      return new ListExaminationInfoParamsTupleScheme();
    }
  }

  private static class ListExaminationInfoParamsTupleScheme extends TupleScheme<ListExaminationInfoParams> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ListExaminationInfoParams struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetExaminationIdList()) {
        optionals.set(0);
      }
      if (struct.isSetCompanyIdList()) {
        optionals.set(1);
      }
      if (struct.isSetSchoolList()) {
        optionals.set(2);
      }
      if (struct.isSetStatusList()) {
        optionals.set(3);
      }
      if (struct.isSetBeginDate()) {
        optionals.set(4);
      }
      if (struct.isSetEndDate()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetExaminationIdList()) {
        {
          oprot.writeI32(struct.examinationIdList.size());
          for (String _iter144 : struct.examinationIdList)
          {
            oprot.writeString(_iter144);
          }
        }
      }
      if (struct.isSetCompanyIdList()) {
        {
          oprot.writeI32(struct.companyIdList.size());
          for (String _iter145 : struct.companyIdList)
          {
            oprot.writeString(_iter145);
          }
        }
      }
      if (struct.isSetSchoolList()) {
        {
          oprot.writeI32(struct.schoolList.size());
          for (String _iter146 : struct.schoolList)
          {
            oprot.writeString(_iter146);
          }
        }
      }
      if (struct.isSetStatusList()) {
        {
          oprot.writeI32(struct.statusList.size());
          for (int _iter147 : struct.statusList)
          {
            oprot.writeI32(_iter147);
          }
        }
      }
      if (struct.isSetBeginDate()) {
        oprot.writeString(struct.beginDate);
      }
      if (struct.isSetEndDate()) {
        oprot.writeString(struct.endDate);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ListExaminationInfoParams struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list148 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.examinationIdList = new ArrayList<String>(_list148.size);
          String _elem149;
          for (int _i150 = 0; _i150 < _list148.size; ++_i150)
          {
            _elem149 = iprot.readString();
            struct.examinationIdList.add(_elem149);
          }
        }
        struct.setExaminationIdListIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list151 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.companyIdList = new ArrayList<String>(_list151.size);
          String _elem152;
          for (int _i153 = 0; _i153 < _list151.size; ++_i153)
          {
            _elem152 = iprot.readString();
            struct.companyIdList.add(_elem152);
          }
        }
        struct.setCompanyIdListIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list154 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.schoolList = new ArrayList<String>(_list154.size);
          String _elem155;
          for (int _i156 = 0; _i156 < _list154.size; ++_i156)
          {
            _elem155 = iprot.readString();
            struct.schoolList.add(_elem155);
          }
        }
        struct.setSchoolListIsSet(true);
      }
      if (incoming.get(3)) {
        {
          org.apache.thrift.protocol.TList _list157 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, iprot.readI32());
          struct.statusList = new ArrayList<Integer>(_list157.size);
          int _elem158;
          for (int _i159 = 0; _i159 < _list157.size; ++_i159)
          {
            _elem158 = iprot.readI32();
            struct.statusList.add(_elem158);
          }
        }
        struct.setStatusListIsSet(true);
      }
      if (incoming.get(4)) {
        struct.beginDate = iprot.readString();
        struct.setBeginDateIsSet(true);
      }
      if (incoming.get(5)) {
        struct.endDate = iprot.readString();
        struct.setEndDateIsSet(true);
      }
    }
  }

}

