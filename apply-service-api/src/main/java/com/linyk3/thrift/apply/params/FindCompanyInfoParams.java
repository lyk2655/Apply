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
public class FindCompanyInfoParams implements org.apache.thrift.TBase<FindCompanyInfoParams, FindCompanyInfoParams._Fields>, java.io.Serializable, Cloneable, Comparable<FindCompanyInfoParams> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("FindCompanyInfoParams");

  private static final org.apache.thrift.protocol.TField COMPANY_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("companyName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField COMPANY_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("companyId", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new FindCompanyInfoParamsStandardSchemeFactory());
    schemes.put(TupleScheme.class, new FindCompanyInfoParamsTupleSchemeFactory());
  }

  /**
   * 公司名字 和 公司Id 二选一 必填
   */
  public String companyName; // required
  public String companyId; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 公司名字 和 公司Id 二选一 必填
     */
    COMPANY_NAME((short)1, "companyName"),
    COMPANY_ID((short)2, "companyId");

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
        case 1: // COMPANY_NAME
          return COMPANY_NAME;
        case 2: // COMPANY_ID
          return COMPANY_ID;
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
    tmpMap.put(_Fields.COMPANY_NAME, new org.apache.thrift.meta_data.FieldMetaData("companyName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.COMPANY_ID, new org.apache.thrift.meta_data.FieldMetaData("companyId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(FindCompanyInfoParams.class, metaDataMap);
  }

  public FindCompanyInfoParams() {
  }

  public FindCompanyInfoParams(
    String companyName,
    String companyId)
  {
    this();
    this.companyName = companyName;
    this.companyId = companyId;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public FindCompanyInfoParams(FindCompanyInfoParams other) {
    if (other.isSetCompanyName()) {
      this.companyName = other.companyName;
    }
    if (other.isSetCompanyId()) {
      this.companyId = other.companyId;
    }
  }

  public FindCompanyInfoParams deepCopy() {
    return new FindCompanyInfoParams(this);
  }

  @Override
  public void clear() {
    this.companyName = null;
    this.companyId = null;
  }

  /**
   * 公司名字 和 公司Id 二选一 必填
   */
  public String getCompanyName() {
    return this.companyName;
  }

  /**
   * 公司名字 和 公司Id 二选一 必填
   */
  public FindCompanyInfoParams setCompanyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  public void unsetCompanyName() {
    this.companyName = null;
  }

  /** Returns true if field companyName is set (has been assigned a value) and false otherwise */
  public boolean isSetCompanyName() {
    return this.companyName != null;
  }

  public void setCompanyNameIsSet(boolean value) {
    if (!value) {
      this.companyName = null;
    }
  }

  public String getCompanyId() {
    return this.companyId;
  }

  public FindCompanyInfoParams setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public void unsetCompanyId() {
    this.companyId = null;
  }

  /** Returns true if field companyId is set (has been assigned a value) and false otherwise */
  public boolean isSetCompanyId() {
    return this.companyId != null;
  }

  public void setCompanyIdIsSet(boolean value) {
    if (!value) {
      this.companyId = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case COMPANY_NAME:
      if (value == null) {
        unsetCompanyName();
      } else {
        setCompanyName((String)value);
      }
      break;

    case COMPANY_ID:
      if (value == null) {
        unsetCompanyId();
      } else {
        setCompanyId((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case COMPANY_NAME:
      return getCompanyName();

    case COMPANY_ID:
      return getCompanyId();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case COMPANY_NAME:
      return isSetCompanyName();
    case COMPANY_ID:
      return isSetCompanyId();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof FindCompanyInfoParams)
      return this.equals((FindCompanyInfoParams)that);
    return false;
  }

  public boolean equals(FindCompanyInfoParams that) {
    if (that == null)
      return false;

    boolean this_present_companyName = true && this.isSetCompanyName();
    boolean that_present_companyName = true && that.isSetCompanyName();
    if (this_present_companyName || that_present_companyName) {
      if (!(this_present_companyName && that_present_companyName))
        return false;
      if (!this.companyName.equals(that.companyName))
        return false;
    }

    boolean this_present_companyId = true && this.isSetCompanyId();
    boolean that_present_companyId = true && that.isSetCompanyId();
    if (this_present_companyId || that_present_companyId) {
      if (!(this_present_companyId && that_present_companyId))
        return false;
      if (!this.companyId.equals(that.companyId))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_companyName = true && (isSetCompanyName());
    list.add(present_companyName);
    if (present_companyName)
      list.add(companyName);

    boolean present_companyId = true && (isSetCompanyId());
    list.add(present_companyId);
    if (present_companyId)
      list.add(companyId);

    return list.hashCode();
  }

  @Override
  public int compareTo(FindCompanyInfoParams other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetCompanyName()).compareTo(other.isSetCompanyName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCompanyName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.companyName, other.companyName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCompanyId()).compareTo(other.isSetCompanyId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCompanyId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.companyId, other.companyId);
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
    StringBuilder sb = new StringBuilder("FindCompanyInfoParams(");
    boolean first = true;

    sb.append("companyName:");
    if (this.companyName == null) {
      sb.append("null");
    } else {
      sb.append(this.companyName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("companyId:");
    if (this.companyId == null) {
      sb.append("null");
    } else {
      sb.append(this.companyId);
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

  private static class FindCompanyInfoParamsStandardSchemeFactory implements SchemeFactory {
    public FindCompanyInfoParamsStandardScheme getScheme() {
      return new FindCompanyInfoParamsStandardScheme();
    }
  }

  private static class FindCompanyInfoParamsStandardScheme extends StandardScheme<FindCompanyInfoParams> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, FindCompanyInfoParams struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // COMPANY_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.companyName = iprot.readString();
              struct.setCompanyNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // COMPANY_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.companyId = iprot.readString();
              struct.setCompanyIdIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, FindCompanyInfoParams struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.companyName != null) {
        oprot.writeFieldBegin(COMPANY_NAME_FIELD_DESC);
        oprot.writeString(struct.companyName);
        oprot.writeFieldEnd();
      }
      if (struct.companyId != null) {
        oprot.writeFieldBegin(COMPANY_ID_FIELD_DESC);
        oprot.writeString(struct.companyId);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class FindCompanyInfoParamsTupleSchemeFactory implements SchemeFactory {
    public FindCompanyInfoParamsTupleScheme getScheme() {
      return new FindCompanyInfoParamsTupleScheme();
    }
  }

  private static class FindCompanyInfoParamsTupleScheme extends TupleScheme<FindCompanyInfoParams> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, FindCompanyInfoParams struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetCompanyName()) {
        optionals.set(0);
      }
      if (struct.isSetCompanyId()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetCompanyName()) {
        oprot.writeString(struct.companyName);
      }
      if (struct.isSetCompanyId()) {
        oprot.writeString(struct.companyId);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, FindCompanyInfoParams struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.companyName = iprot.readString();
        struct.setCompanyNameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.companyId = iprot.readString();
        struct.setCompanyIdIsSet(true);
      }
    }
  }

}
