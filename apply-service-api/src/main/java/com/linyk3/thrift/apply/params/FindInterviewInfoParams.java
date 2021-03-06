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
public class FindInterviewInfoParams implements org.apache.thrift.TBase<FindInterviewInfoParams, FindInterviewInfoParams._Fields>, java.io.Serializable, Cloneable, Comparable<FindInterviewInfoParams> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("FindInterviewInfoParams");

  private static final org.apache.thrift.protocol.TField INTERVIEW_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("interviewId", org.apache.thrift.protocol.TType.STRING, (short)1);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new FindInterviewInfoParamsStandardSchemeFactory());
    schemes.put(TupleScheme.class, new FindInterviewInfoParamsTupleSchemeFactory());
  }

  /**
   * 面试Id 必填
   */
  public String interviewId; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 面试Id 必填
     */
    INTERVIEW_ID((short)1, "interviewId");

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
        case 1: // INTERVIEW_ID
          return INTERVIEW_ID;
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
    tmpMap.put(_Fields.INTERVIEW_ID, new org.apache.thrift.meta_data.FieldMetaData("interviewId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(FindInterviewInfoParams.class, metaDataMap);
  }

  public FindInterviewInfoParams() {
  }

  public FindInterviewInfoParams(
    String interviewId)
  {
    this();
    this.interviewId = interviewId;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public FindInterviewInfoParams(FindInterviewInfoParams other) {
    if (other.isSetInterviewId()) {
      this.interviewId = other.interviewId;
    }
  }

  public FindInterviewInfoParams deepCopy() {
    return new FindInterviewInfoParams(this);
  }

  @Override
  public void clear() {
    this.interviewId = null;
  }

  /**
   * 面试Id 必填
   */
  public String getInterviewId() {
    return this.interviewId;
  }

  /**
   * 面试Id 必填
   */
  public FindInterviewInfoParams setInterviewId(String interviewId) {
    this.interviewId = interviewId;
    return this;
  }

  public void unsetInterviewId() {
    this.interviewId = null;
  }

  /** Returns true if field interviewId is set (has been assigned a value) and false otherwise */
  public boolean isSetInterviewId() {
    return this.interviewId != null;
  }

  public void setInterviewIdIsSet(boolean value) {
    if (!value) {
      this.interviewId = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case INTERVIEW_ID:
      if (value == null) {
        unsetInterviewId();
      } else {
        setInterviewId((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case INTERVIEW_ID:
      return getInterviewId();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case INTERVIEW_ID:
      return isSetInterviewId();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof FindInterviewInfoParams)
      return this.equals((FindInterviewInfoParams)that);
    return false;
  }

  public boolean equals(FindInterviewInfoParams that) {
    if (that == null)
      return false;

    boolean this_present_interviewId = true && this.isSetInterviewId();
    boolean that_present_interviewId = true && that.isSetInterviewId();
    if (this_present_interviewId || that_present_interviewId) {
      if (!(this_present_interviewId && that_present_interviewId))
        return false;
      if (!this.interviewId.equals(that.interviewId))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_interviewId = true && (isSetInterviewId());
    list.add(present_interviewId);
    if (present_interviewId)
      list.add(interviewId);

    return list.hashCode();
  }

  @Override
  public int compareTo(FindInterviewInfoParams other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetInterviewId()).compareTo(other.isSetInterviewId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetInterviewId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.interviewId, other.interviewId);
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
    StringBuilder sb = new StringBuilder("FindInterviewInfoParams(");
    boolean first = true;

    sb.append("interviewId:");
    if (this.interviewId == null) {
      sb.append("null");
    } else {
      sb.append(this.interviewId);
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

  private static class FindInterviewInfoParamsStandardSchemeFactory implements SchemeFactory {
    public FindInterviewInfoParamsStandardScheme getScheme() {
      return new FindInterviewInfoParamsStandardScheme();
    }
  }

  private static class FindInterviewInfoParamsStandardScheme extends StandardScheme<FindInterviewInfoParams> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, FindInterviewInfoParams struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // INTERVIEW_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.interviewId = iprot.readString();
              struct.setInterviewIdIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, FindInterviewInfoParams struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.interviewId != null) {
        oprot.writeFieldBegin(INTERVIEW_ID_FIELD_DESC);
        oprot.writeString(struct.interviewId);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class FindInterviewInfoParamsTupleSchemeFactory implements SchemeFactory {
    public FindInterviewInfoParamsTupleScheme getScheme() {
      return new FindInterviewInfoParamsTupleScheme();
    }
  }

  private static class FindInterviewInfoParamsTupleScheme extends TupleScheme<FindInterviewInfoParams> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, FindInterviewInfoParams struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetInterviewId()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetInterviewId()) {
        oprot.writeString(struct.interviewId);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, FindInterviewInfoParams struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.interviewId = iprot.readString();
        struct.setInterviewIdIsSet(true);
      }
    }
  }

}

