package com.psl.snmp.wireless;

import java.awt.font.NumericShaper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.snmp4j.PDUv1;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;

import com.psl.snmp.wireless.knowledge.SeverityKnowledge;
import com.psl.snmp.wireless.knowledge.TrapKnowledge;

public class SnmpTrapField {
  
  private static Logger LOGGER = Logger.getLogger(SnmpTrapField.class.getName());
  
  public static void apply(PDUv1 pdu, TrapKnowledge trapKnw, String value) {
    
    OID oid = null;
    OctetString octetString = null;
    
    switch (trapKnw) {
    case ADDITIONAL_TEXT:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString(value);
      break;
    case ALARM_ID:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString("9999");
      break;
    case EVENT_TIME:
      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY, HH:mm:ss");
      Date date = new Date(Long.parseLong(value));
      String newDateTime = sdf.format(date);
      
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString(newDateTime);
      break; 
    case EVENT_TYPE:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString(value);
      break;
    case MANAGED_OBJECT_CLASS:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString(value);
      break;
    case MANAGED_OBJECT_INSTANCE:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString(value);
      break;
    case MONITORED_ATTRIBUTE:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString(value);
      break;
    case NOTIFICATION_IDENTIFIER:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString(value);
      break;
    case ALARM_SEVERITY:
      String severity = "-1";
      try {
        SeverityKnowledge x = SeverityKnowledge.valueOf(value.toUpperCase());
        severity = x.getString();
      } catch (Exception ex) {
        LOGGER.finest(String.format(
            "Serverity Knowledge has not information for %s.", 
            value.toUpperCase()));
      }
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString(severity);
      break;
    case PROBABLE_CAUSE:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString(value);
      break;
    case SPECIFIC_PROBLEM:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString(value);
      break;
    case ALARM_PREDICATE:
      break;
    case TREND_INDICATION:
      break;
    case ACK:
      break;
    case ACK_COMMENT:
      break;
    case ACK_USERNAME:
      break;
    case HOSTNAME:
      break;
    case PORT:
      break;
    case REGION:
      break;
    case DOMAIN:
      break;
    case VENDOR:
      break;
    case EMS:
      break;
    default:  
      oid = null;
      octetString = null;
      break;
    }
  }
}
