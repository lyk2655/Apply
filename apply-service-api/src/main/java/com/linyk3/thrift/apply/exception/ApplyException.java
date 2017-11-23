/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.linyk3.thrift.apply.exception;

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
public class ApplyException extends TException implements org.apache.thrift.TBase<ApplyException, ApplyException._Fields>, java.io.Serializable, Cloneable, Comparable<ApplyException> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ApplyException");

  private static final org.apache.thrift.protocol.TField MSG_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("msgId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField CUSTOM_MSG_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("customMsgId", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField MSG_CONTENT_FIELD_DESC = new org.apache.thrift.protocol.TField("msgContent", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ApplyExceptionStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ApplyExceptionTupleSchemeFactory());
  }

  /**
   * 错误Id
   * 
   * @see com.linyk3.thrift.apply.commons.APPLY_EXCEPTION_MSG_ID
   */
  public com.linyk3.thrift.apply.commons.APPLY_EXCEPTION_MSG_ID msgId; // required
  /**
   * 服务器端自定义错误ID
   */
  public int customMsgId; // required
  /**
   * 错误原因内容
   */
  public String msgContent; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 错误Id
     * 
     * @see com.linyk3.thrift.apply.commons.APPLY_EXCEPTION_MSG_ID
     */
    MSG_ID((short)1, "msgId"),
    /**
     * 服务器端自定义错误ID
     */
    CUSTOM_MSG_ID((short)2, "customMsgId"),
    /**
     * 错误原因内容
     */
    MSG_CONTENT((short)3, "msgContent");

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
        case 1: // MSG_ID
          return MSG_ID;
        case 2: // CUSTOM_MSG_ID
          return CUSTOM_MSG_ID;
        case 3: // MSG_CONTENT
          return MSG_CONTENT;
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
  private static final int __CUSTOMMSGID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.MSG_ID, new org.apache.thrift.meta_data.FieldMetaData("msgId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, com.linyk3.thrift.apply.commons.APPLY_EXCEPTION_MSG_ID.class)));
    tmpMap.put(_Fields.CUSTOM_MSG_ID, new org.apache.thrift.meta_data.FieldMetaData("customMsgId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.MSG_CONTENT, new org.apache.thrift.meta_data.FieldMetaData("msgContent", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ApplyException.class, metaDataMap);
  }

  public ApplyException() {
  }

  public ApplyException(
    com.linyk3.thrift.apply.commons.APPLY_EXCEPTION_MSG_ID msgId,
    int customMsgId,
    String msgContent)
  {
    this();
    this.msgId = msgId;
    this.customMsgId = customMsgId;
    setCustomMsgIdIsSet(true);
    this.msgContent = msgContent;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ApplyException(ApplyException other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetMsgId()) {
      this.msgId = other.msgId;
    }
    this.customMsgId = other.customMsgId;
    if (other.isSetMsgContent()) {
      this.msgContent = other.msgContent;
    }
  }

  public ApplyException deepCopy() {
    return new ApplyException(this);
  }

  @Override
  public void clear() {
    this.msgId = null;
    setCustomMsgIdIsSet(false);
    this.customMsgId = 0;
    this.msgContent = null;
  }

  /**
   * 错误Id
   * 
   * @see com.linyk3.thrift.apply.commons.APPLY_EXCEPTION_MSG_ID
   */
  public com.linyk3.thrift.apply.commons.APPLY_EXCEPTION_MSG_ID getMsgId() {
    return this.msgId;
  }

  /**
   * 错误Id
   * 
   * @see com.linyk3.thrift.apply.commons.APPLY_EXCEPTION_MSG_ID
   */
  public ApplyException setMsgId(com.linyk3.thrift.apply.commons.APPLY_EXCEPTION_MSG_ID msgId) {
    this.msgId = msgId;
    return this;
  }

  public void unsetMsgId() {
    this.msgId = null;
  }

  /** Returns true if field msgId is set (has been assigned a value) and false otherwise */
  public boolean isSetMsgId() {
    return this.msgId != null;
  }

  public void setMsgIdIsSet(boolean value) {
    if (!value) {
      this.msgId = null;
    }
  }

  /**
   * 服务器端自定义错误ID
   */
  public int getCustomMsgId() {
    return this.customMsgId;
  }

  /**
   * 服务器端自定义错误ID
   */
  public ApplyException setCustomMsgId(int customMsgId) {
    this.customMsgId = customMsgId;
    setCustomMsgIdIsSet(true);
    return this;
  }

  public void unsetCustomMsgId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CUSTOMMSGID_ISSET_ID);
  }

  /** Returns true if field customMsgId is set (has been assigned a value) and false otherwise */
  public boolean isSetCustomMsgId() {
    return EncodingUtils.testBit(__isset_bitfield, __CUSTOMMSGID_ISSET_ID);
  }

  public void setCustomMsgIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CUSTOMMSGID_ISSET_ID, value);
  }

  /**
   * 错误原因内容
   */
  public String getMsgContent() {
    return this.msgContent;
  }

  /**
   * 错误原因内容
   */
  public ApplyException setMsgContent(String msgContent) {
    this.msgContent = msgContent;
    return this;
  }

  public void unsetMsgContent() {
    this.msgContent = null;
  }

  /** Returns true if field msgContent is set (has been assigned a value) and false otherwise */
  public boolean isSetMsgContent() {
    return this.msgContent != null;
  }

  public void setMsgContentIsSet(boolean value) {
    if (!value) {
      this.msgContent = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case MSG_ID:
      if (value == null) {
        unsetMsgId();
      } else {
        setMsgId((com.linyk3.thrift.apply.commons.APPLY_EXCEPTION_MSG_ID)value);
      }
      break;

    case CUSTOM_MSG_ID:
      if (value == null) {
        unsetCustomMsgId();
      } else {
        setCustomMsgId((Integer)value);
      }
      break;

    case MSG_CONTENT:
      if (value == null) {
        unsetMsgContent();
      } else {
        setMsgContent((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case MSG_ID:
      return getMsgId();

    case CUSTOM_MSG_ID:
      return Integer.valueOf(getCustomMsgId());

    case MSG_CONTENT:
      return getMsgContent();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case MSG_ID:
      return isSetMsgId();
    case CUSTOM_MSG_ID:
      return isSetCustomMsgId();
    case MSG_CONTENT:
      return isSetMsgContent();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ApplyException)
      return this.equals((ApplyException)that);
    return false;
  }

  public boolean equals(ApplyException that) {
    if (that == null)
      return false;

    boolean this_present_msgId = true && this.isSetMsgId();
    boolean that_present_msgId = true && that.isSetMsgId();
    if (this_present_msgId || that_present_msgId) {
      if (!(this_present_msgId && that_present_msgId))
        return false;
      if (!this.msgId.equals(that.msgId))
        return false;
    }

    boolean this_present_customMsgId = true;
    boolean that_present_customMsgId = true;
    if (this_present_customMsgId || that_present_customMsgId) {
      if (!(this_present_customMsgId && that_present_customMsgId))
        return false;
      if (this.customMsgId != that.customMsgId)
        return false;
    }

    boolean this_present_msgContent = true && this.isSetMsgContent();
    boolean that_present_msgContent = true && that.isSetMsgContent();
    if (this_present_msgContent || that_present_msgContent) {
      if (!(this_present_msgContent && that_present_msgContent))
        return false;
      if (!this.msgContent.equals(that.msgContent))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_msgId = true && (isSetMsgId());
    list.add(present_msgId);
    if (present_msgId)
      list.add(msgId.getValue());

    boolean present_customMsgId = true;
    list.add(present_customMsgId);
    if (present_customMsgId)
      list.add(customMsgId);

    boolean present_msgContent = true && (isSetMsgContent());
    list.add(present_msgContent);
    if (present_msgContent)
      list.add(msgContent);

    return list.hashCode();
  }

  @Override
  public int compareTo(ApplyException other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetMsgId()).compareTo(other.isSetMsgId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMsgId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.msgId, other.msgId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCustomMsgId()).compareTo(other.isSetCustomMsgId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCustomMsgId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.customMsgId, other.customMsgId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMsgContent()).compareTo(other.isSetMsgContent());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMsgContent()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.msgContent, other.msgContent);
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
    StringBuilder sb = new StringBuilder("ApplyException(");
    boolean first = true;

    sb.append("msgId:");
    if (this.msgId == null) {
      sb.append("null");
    } else {
      sb.append(this.msgId);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("customMsgId:");
    sb.append(this.customMsgId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("msgContent:");
    if (this.msgContent == null) {
      sb.append("null");
    } else {
      sb.append(this.msgContent);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ApplyExceptionStandardSchemeFactory implements SchemeFactory {
    public ApplyExceptionStandardScheme getScheme() {
      return new ApplyExceptionStandardScheme();
    }
  }

  private static class ApplyExceptionStandardScheme extends StandardScheme<ApplyException> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ApplyException struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // MSG_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.msgId = com.linyk3.thrift.apply.commons.APPLY_EXCEPTION_MSG_ID.findByValue(iprot.readI32());
              struct.setMsgIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CUSTOM_MSG_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.customMsgId = iprot.readI32();
              struct.setCustomMsgIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // MSG_CONTENT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.msgContent = iprot.readString();
              struct.setMsgContentIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ApplyException struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.msgId != null) {
        oprot.writeFieldBegin(MSG_ID_FIELD_DESC);
        oprot.writeI32(struct.msgId.getValue());
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(CUSTOM_MSG_ID_FIELD_DESC);
      oprot.writeI32(struct.customMsgId);
      oprot.writeFieldEnd();
      if (struct.msgContent != null) {
        oprot.writeFieldBegin(MSG_CONTENT_FIELD_DESC);
        oprot.writeString(struct.msgContent);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ApplyExceptionTupleSchemeFactory implements SchemeFactory {
    public ApplyExceptionTupleScheme getScheme() {
      return new ApplyExceptionTupleScheme();
    }
  }

  private static class ApplyExceptionTupleScheme extends TupleScheme<ApplyException> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ApplyException struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetMsgId()) {
        optionals.set(0);
      }
      if (struct.isSetCustomMsgId()) {
        optionals.set(1);
      }
      if (struct.isSetMsgContent()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetMsgId()) {
        oprot.writeI32(struct.msgId.getValue());
      }
      if (struct.isSetCustomMsgId()) {
        oprot.writeI32(struct.customMsgId);
      }
      if (struct.isSetMsgContent()) {
        oprot.writeString(struct.msgContent);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ApplyException struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.msgId = com.linyk3.thrift.apply.commons.APPLY_EXCEPTION_MSG_ID.findByValue(iprot.readI32());
        struct.setMsgIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.customMsgId = iprot.readI32();
        struct.setCustomMsgIdIsSet(true);
      }
      if (incoming.get(2)) {
        struct.msgContent = iprot.readString();
        struct.setMsgContentIsSet(true);
      }
    }
  }

}
