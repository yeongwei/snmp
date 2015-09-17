package com.psl.snmp.wireless.knowledge;

public enum TrapKnowledge {
  ADDITIONAL_TEXT,
  ALARM_ID,
  EVENT_TIME,
  EVENT_TYPE,
  MANAGED_OBJECT_CLASS,
  MANAGED_OBJECT_INSTANCE,
  MONITORED_ATTRIBUTE,
  NOTIFICATION_IDENTIFIER,
  ALARM_SEVERITY,
  PROBABLE_CAUSE,
  SPECIFIC_PROBLEM,
  ALARM_PREDICATE,
  TREND_INDICATION,
  ACK,
  ACK_COMMENT,
  ACK_USERNAME,
  HOSTNAME,
  PORT,
  REGION,
  DOMAIN,
  VENDOR,
  EMS;
  
  public String getOid() {
    switch (this) {
    case ADDITIONAL_TEXT:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".13";
    case ALARM_ID:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".18";
    case EVENT_TIME:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".4"; 
    case EVENT_TYPE:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".3";
    case MANAGED_OBJECT_CLASS:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".1";
    case MANAGED_OBJECT_INSTANCE:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".2";
    case MONITORED_ATTRIBUTE:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".12";
    case NOTIFICATION_IDENTIFIER:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".10";
    case ALARM_SEVERITY:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".7";
    case PROBABLE_CAUSE:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".5";
    case SPECIFIC_PROBLEM:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".6";
    case ALARM_PREDICATE:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".9";
    case TREND_INDICATION:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".8";
    case ACK:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".14";
    case ACK_COMMENT:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".15";
    case ACK_USERNAME:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".16";
    case HOSTNAME:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".20";
    case PORT:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".21";
    case REGION:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".22";
    case DOMAIN:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".23";
    case VENDOR:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".24";
    case EMS:
      return SnmpKnowledge.ENTERPRISE_FULL_OID.get() + ".25";
    default:
      return null;
    }
  }
}
