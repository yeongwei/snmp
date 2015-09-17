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
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".13";
    case ALARM_ID:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".18";
    case EVENT_TIME:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".4"; 
    case EVENT_TYPE:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".3";
    case MANAGED_OBJECT_CLASS:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".1";
    case MANAGED_OBJECT_INSTANCE:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".2";
    case MONITORED_ATTRIBUTE:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".12";
    case NOTIFICATION_IDENTIFIER:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".10";
    case ALARM_SEVERITY:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".7";
    case PROBABLE_CAUSE:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".5";
    case SPECIFIC_PROBLEM:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".6";
    case ALARM_PREDICATE:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".9";
    case TREND_INDICATION:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".8";
    case ACK:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".14";
    case ACK_COMMENT:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".15";
    case ACK_USERNAME:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".16";
    case HOSTNAME:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".20";
    case PORT:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".21";
    case REGION:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".22";
    case DOMAIN:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".23";
    case VENDOR:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".24";
    case EMS:
      return SnmpKnowledge.ENTERPRISE_FULL_OID + ".25";
    default:
      return null;
    }
  }
}
