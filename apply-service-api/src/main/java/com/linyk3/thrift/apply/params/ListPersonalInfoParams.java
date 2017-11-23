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
public class ListPersonalInfoParams implements org.apache.thrift.TBase<ListPersonalInfoParams, ListPersonalInfoParams._Fields>, java.io.Serializable, Cloneable, Comparable<ListPersonalInfoParams> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ListPersonalInfoParams");

  private static final org.apache.thrift.protocol.TField NAME_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("nameList", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField PERSONAL_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("personalId", org.apache.thrift.protocol.TType.LIST, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ListPersonalInfoParamsStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ListPersonalInfoParamsTupleSchemeFactory());
  }

  /**
   * 个人名字和个人Id列表 二选一 选填，不填选择全部
   */
  public List<String> nameList; // required
  public List<String> personalId; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 个人名字和个人Id列表 二选一 选填，不填选择全部
     */
    NAME_LIST((short)1, "nameList"),
    PERSONAL_ID((short)2, "personalId");

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
        case 2: // PERSONAL_ID
          return PERSONAL_ID;
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
    tmpMap.put(_Fields.PERSONAL_ID, new org.apache.thrift.meta_data.FieldMetaData("personalId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ListPersonalInfoParams.class, metaDataMap);
  }

  public ListPersonalInfoParams() {
  }

  public ListPersonalInfoParams(
    List<String> nameList,
    List<String> personalId)
  {
    this();
    this.nameList = nameList;
    this.personalId = personalId;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ListPersonalInfoParams(ListPersonalInfoParams other) {
    if (other.isSetNameList()) {
      List<String> __this__nameList = new ArrayList<String>(other.nameList);
      this.nameList = __this__nameList;
    }
    if (other.isSetPersonalId()) {
      List<String> __this__personalId = new ArrayList<String>(other.personalId);
      this.personalId = __this__personalId;
    }
  }

  public ListPersonalInfoParams deepCopy() {
    return new ListPersonalInfoParams(this);
  }

  @Override
  public void clear() {
    this.nameList = null;
    this.personalId = null;
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
   * 个人名字和个人Id列表 二选一 选填，不填选择全部
   */
  public List<String> getNameList() {
    return this.nameList;
  }

  /**
   * 个人名字和个人Id列表 二选一 选填，不填选择全部
   */
  public ListPersonalInfoParams setNameList(List<String> nameList) {
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

  public int getPersonalIdSize() {
    return (this.personalId == null) ? 0 : this.personalId.size();
  }

  public java.util.Iterator<String> getPersonalIdIterator() {
    return (this.personalId == null) ? null : this.personalId.iterator();
  }

  public void addToPersonalId(String elem) {
    if (this.personalId == null) {
      this.personalId = new ArrayList<String>();
    }
    this.personalId.add(elem);
  }

  public List<String> getPersonalId() {
    return this.personalId;
  }

  public ListPersonalInfoParams setPersonalId(List<String> personalId) {
    this.personalId = personalId;
    return this;
  }

  public void unsetPersonalId() {
    this.personalId = null;
  }

  /** Returns true if field personalId is set (has been assigned a value) and false otherwise */
  public boolean isSetPersonalId() {
    return this.personalId != null;
  }

  public void setPersonalIdIsSet(boolean value) {
    if (!value) {
      this.personalId = null;
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

    case PERSONAL_ID:
      if (value == null) {
        unsetPersonalId();
      } else {
        setPersonalId((List<String>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case NAME_LIST:
      return getNameList();

    case PERSONAL_ID:
      return getPersonalId();

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
    case PERSONAL_ID:
      return isSetPersonalId();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ListPersonalInfoParams)
      return this.equals((ListPersonalInfoParams)that);
    return false;
  }

  public boolean equals(ListPersonalInfoParams that) {
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

    boolean this_present_personalId = true && this.isSetPersonalId();
    boolean that_present_personalId = true && that.isSetPersonalId();
    if (this_present_personalId || that_present_personalId) {
      if (!(this_present_personalId && that_present_personalId))
        return false;
      if (!this.personalId.equals(that.personalId))
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

    boolean present_personalId = true && (isSetPersonalId());
    list.add(present_personalId);
    if (present_personalId)
      list.add(personalId);

    return list.hashCode();
  }

  @Override
  public int compareTo(ListPersonalInfoParams other) {
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
    lastComparison = Boolean.valueOf(isSetPersonalId()).compareTo(other.isSetPersonalId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPersonalId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.personalId, other.personalId);
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
    StringBuilder sb = new StringBuilder("ListPersonalInfoParams(");
    boolean first = true;

    sb.append("nameList:");
    if (this.nameList == null) {
      sb.append("null");
    } else {
      sb.append(this.nameList);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("personalId:");
    if (this.personalId == null) {
      sb.append("null");
    } else {
      sb.append(this.personalId);
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

  private static class ListPersonalInfoParamsStandardSchemeFactory implements SchemeFactory {
    public ListPersonalInfoParamsStandardScheme getScheme() {
      return new ListPersonalInfoParamsStandardScheme();
    }
  }

  private static class ListPersonalInfoParamsStandardScheme extends StandardScheme<ListPersonalInfoParams> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ListPersonalInfoParams struct) throws org.apache.thrift.TException {
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
                org.apache.thrift.protocol.TList _list16 = iprot.readListBegin();
                struct.nameList = new ArrayList<String>(_list16.size);
                String _elem17;
                for (int _i18 = 0; _i18 < _list16.size; ++_i18)
                {
                  _elem17 = iprot.readString();
                  struct.nameList.add(_elem17);
                }
                iprot.readListEnd();
              }
              struct.setNameListIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PERSONAL_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list19 = iprot.readListBegin();
                struct.personalId = new ArrayList<String>(_list19.size);
                String _elem20;
                for (int _i21 = 0; _i21 < _list19.size; ++_i21)
                {
                  _elem20 = iprot.readString();
                  struct.personalId.add(_elem20);
                }
                iprot.readListEnd();
              }
              struct.setPersonalIdIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ListPersonalInfoParams struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.nameList != null) {
        oprot.writeFieldBegin(NAME_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.nameList.size()));
          for (String _iter22 : struct.nameList)
          {
            oprot.writeString(_iter22);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.personalId != null) {
        oprot.writeFieldBegin(PERSONAL_ID_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.personalId.size()));
          for (String _iter23 : struct.personalId)
          {
            oprot.writeString(_iter23);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ListPersonalInfoParamsTupleSchemeFactory implements SchemeFactory {
    public ListPersonalInfoParamsTupleScheme getScheme() {
      return new ListPersonalInfoParamsTupleScheme();
    }
  }

  private static class ListPersonalInfoParamsTupleScheme extends TupleScheme<ListPersonalInfoParams> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ListPersonalInfoParams struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetNameList()) {
        optionals.set(0);
      }
      if (struct.isSetPersonalId()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetNameList()) {
        {
          oprot.writeI32(struct.nameList.size());
          for (String _iter24 : struct.nameList)
          {
            oprot.writeString(_iter24);
          }
        }
      }
      if (struct.isSetPersonalId()) {
        {
          oprot.writeI32(struct.personalId.size());
          for (String _iter25 : struct.personalId)
          {
            oprot.writeString(_iter25);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ListPersonalInfoParams struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list26 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.nameList = new ArrayList<String>(_list26.size);
          String _elem27;
          for (int _i28 = 0; _i28 < _list26.size; ++_i28)
          {
            _elem27 = iprot.readString();
            struct.nameList.add(_elem27);
          }
        }
        struct.setNameListIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list29 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.personalId = new ArrayList<String>(_list29.size);
          String _elem30;
          for (int _i31 = 0; _i31 < _list29.size; ++_i31)
          {
            _elem30 = iprot.readString();
            struct.personalId.add(_elem30);
          }
        }
        struct.setPersonalIdIsSet(true);
      }
    }
  }

}

