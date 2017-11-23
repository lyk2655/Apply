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
public class ListUserInfoParams implements org.apache.thrift.TBase<ListUserInfoParams, ListUserInfoParams._Fields>, java.io.Serializable, Cloneable, Comparable<ListUserInfoParams> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ListUserInfoParams");

  private static final org.apache.thrift.protocol.TField NAME_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("nameList", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField USER_ID_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("userIdList", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ListUserInfoParamsStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ListUserInfoParamsTupleSchemeFactory());
  }

  /**
   * 用户名列表和用户Id列表 二选一 选填 不填选择全部
   */
  public List<String> nameList; // required
  public List<String> userIdList; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 用户名列表和用户Id列表 二选一 选填 不填选择全部
     */
    NAME_LIST((short)1, "nameList"),
    USER_ID_LIST((short)2, "userIdList");

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
        case 1: // NAME_LIST
          return NAME_LIST;
        case 2: // USER_ID_LIST
          return USER_ID_LIST;
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
    tmpMap.put(_Fields.NAME_LIST, new org.apache.thrift.meta_data.FieldMetaData("nameList", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.USER_ID_LIST, new org.apache.thrift.meta_data.FieldMetaData("userIdList", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ListUserInfoParams.class, metaDataMap);
  }

  public ListUserInfoParams() {
  }

  public ListUserInfoParams(
    List<String> nameList,
    List<String> userIdList)
  {
    this();
    this.nameList = nameList;
    this.userIdList = userIdList;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ListUserInfoParams(ListUserInfoParams other) {
    if (other.isSetNameList()) {
      List<String> __this__nameList = new ArrayList<String>(other.nameList);
      this.nameList = __this__nameList;
    }
    if (other.isSetUserIdList()) {
      List<String> __this__userIdList = new ArrayList<String>(other.userIdList);
      this.userIdList = __this__userIdList;
    }
  }

  public ListUserInfoParams deepCopy() {
    return new ListUserInfoParams(this);
  }

  @Override
  public void clear() {
    this.nameList = null;
    this.userIdList = null;
  }

  public int getNameListSize() {
    return (this.nameList == null) ? 0 : this.nameList.size();
  }

  public java.util.Iterator<String> getNameListIterator() {
    return (this.nameList == null) ? null : this.nameList.iterator();
  }

  public void addToNameList(String elem) {
    if (this.nameList == null) {
      this.nameList = new ArrayList<String>();
    }
    this.nameList.add(elem);
  }

  /**
   * 用户名列表和用户Id列表 二选一 选填 不填选择全部
   */
  public List<String> getNameList() {
    return this.nameList;
  }

  /**
   * 用户名列表和用户Id列表 二选一 选填 不填选择全部
   */
  public ListUserInfoParams setNameList(List<String> nameList) {
    this.nameList = nameList;
    return this;
  }

  public void unsetNameList() {
    this.nameList = null;
  }

  /** Returns true if field nameList is set (has been assigned a value) and false otherwise */
  public boolean isSetNameList() {
    return this.nameList != null;
  }

  public void setNameListIsSet(boolean value) {
    if (!value) {
      this.nameList = null;
    }
  }

  public int getUserIdListSize() {
    return (this.userIdList == null) ? 0 : this.userIdList.size();
  }

  public java.util.Iterator<String> getUserIdListIterator() {
    return (this.userIdList == null) ? null : this.userIdList.iterator();
  }

  public void addToUserIdList(String elem) {
    if (this.userIdList == null) {
      this.userIdList = new ArrayList<String>();
    }
    this.userIdList.add(elem);
  }

  public List<String> getUserIdList() {
    return this.userIdList;
  }

  public ListUserInfoParams setUserIdList(List<String> userIdList) {
    this.userIdList = userIdList;
    return this;
  }

  public void unsetUserIdList() {
    this.userIdList = null;
  }

  /** Returns true if field userIdList is set (has been assigned a value) and false otherwise */
  public boolean isSetUserIdList() {
    return this.userIdList != null;
  }

  public void setUserIdListIsSet(boolean value) {
    if (!value) {
      this.userIdList = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case NAME_LIST:
      if (value == null) {
        unsetNameList();
      } else {
        setNameList((List<String>)value);
      }
      break;

    case USER_ID_LIST:
      if (value == null) {
        unsetUserIdList();
      } else {
        setUserIdList((List<String>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NAME_LIST:
      return getNameList();

    case USER_ID_LIST:
      return getUserIdList();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case NAME_LIST:
      return isSetNameList();
    case USER_ID_LIST:
      return isSetUserIdList();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ListUserInfoParams)
      return this.equals((ListUserInfoParams)that);
    return false;
  }

  public boolean equals(ListUserInfoParams that) {
    if (that == null)
      return false;

    boolean this_present_nameList = true && this.isSetNameList();
    boolean that_present_nameList = true && that.isSetNameList();
    if (this_present_nameList || that_present_nameList) {
      if (!(this_present_nameList && that_present_nameList))
        return false;
      if (!this.nameList.equals(that.nameList))
        return false;
    }

    boolean this_present_userIdList = true && this.isSetUserIdList();
    boolean that_present_userIdList = true && that.isSetUserIdList();
    if (this_present_userIdList || that_present_userIdList) {
      if (!(this_present_userIdList && that_present_userIdList))
        return false;
      if (!this.userIdList.equals(that.userIdList))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_nameList = true && (isSetNameList());
    list.add(present_nameList);
    if (present_nameList)
      list.add(nameList);

    boolean present_userIdList = true && (isSetUserIdList());
    list.add(present_userIdList);
    if (present_userIdList)
      list.add(userIdList);

    return list.hashCode();
  }

  @Override
  public int compareTo(ListUserInfoParams other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetNameList()).compareTo(other.isSetNameList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNameList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.nameList, other.nameList);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUserIdList()).compareTo(other.isSetUserIdList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserIdList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userIdList, other.userIdList);
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
    StringBuilder sb = new StringBuilder("ListUserInfoParams(");
    boolean first = true;

    sb.append("nameList:");
    if (this.nameList == null) {
      sb.append("null");
    } else {
      sb.append(this.nameList);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("userIdList:");
    if (this.userIdList == null) {
      sb.append("null");
    } else {
      sb.append(this.userIdList);
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

  private static class ListUserInfoParamsStandardSchemeFactory implements SchemeFactory {
    public ListUserInfoParamsStandardScheme getScheme() {
      return new ListUserInfoParamsStandardScheme();
    }
  }

  private static class ListUserInfoParamsStandardScheme extends StandardScheme<ListUserInfoParams> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ListUserInfoParams struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // NAME_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.nameList = new ArrayList<String>(_list0.size);
                String _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = iprot.readString();
                  struct.nameList.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setNameListIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // USER_ID_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list3 = iprot.readListBegin();
                struct.userIdList = new ArrayList<String>(_list3.size);
                String _elem4;
                for (int _i5 = 0; _i5 < _list3.size; ++_i5)
                {
                  _elem4 = iprot.readString();
                  struct.userIdList.add(_elem4);
                }
                iprot.readListEnd();
              }
              struct.setUserIdListIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ListUserInfoParams struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.nameList != null) {
        oprot.writeFieldBegin(NAME_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.nameList.size()));
          for (String _iter6 : struct.nameList)
          {
            oprot.writeString(_iter6);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.userIdList != null) {
        oprot.writeFieldBegin(USER_ID_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.userIdList.size()));
          for (String _iter7 : struct.userIdList)
          {
            oprot.writeString(_iter7);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ListUserInfoParamsTupleSchemeFactory implements SchemeFactory {
    public ListUserInfoParamsTupleScheme getScheme() {
      return new ListUserInfoParamsTupleScheme();
    }
  }

  private static class ListUserInfoParamsTupleScheme extends TupleScheme<ListUserInfoParams> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ListUserInfoParams struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetNameList()) {
        optionals.set(0);
      }
      if (struct.isSetUserIdList()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetNameList()) {
        {
          oprot.writeI32(struct.nameList.size());
          for (String _iter8 : struct.nameList)
          {
            oprot.writeString(_iter8);
          }
        }
      }
      if (struct.isSetUserIdList()) {
        {
          oprot.writeI32(struct.userIdList.size());
          for (String _iter9 : struct.userIdList)
          {
            oprot.writeString(_iter9);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ListUserInfoParams struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list10 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.nameList = new ArrayList<String>(_list10.size);
          String _elem11;
          for (int _i12 = 0; _i12 < _list10.size; ++_i12)
          {
            _elem11 = iprot.readString();
            struct.nameList.add(_elem11);
          }
        }
        struct.setNameListIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list13 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.userIdList = new ArrayList<String>(_list13.size);
          String _elem14;
          for (int _i15 = 0; _i15 < _list13.size; ++_i15)
          {
            _elem14 = iprot.readString();
            struct.userIdList.add(_elem14);
          }
        }
        struct.setUserIdListIsSet(true);
      }
    }
  }

}

