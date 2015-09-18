package com.psl.snmp.wireless;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.snmp4j.PDUv1;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;

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
      String millisec = value;
      if (millisec.length() == 10) {
        LOGGER.finest(String.format("Appending zeros as milliseconds."));
        millisec += "000";
      } 
      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
      Date date = new Date(Long.parseLong(millisec));
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
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString(value);
      break;
    case TREND_INDICATION:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString(value);
      break;
    case ACK:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString("");
      break;
    case ACK_COMMENT:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString("");
      break;
    case ACK_USERNAME:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString("");
      break;
    case HOSTNAME:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString("tnpm");
      break;
    case PORT:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString("8080");
      break;
    case REGION:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString("REGION");
      break;
    case DOMAIN:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString("DOMAIN");
      break;
    case VENDOR:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString("VENDOR");
      break;
    case EMS:
      oid = new OID(trapKnw.getOid());
      octetString = new OctetString("EMS");
      break;
    default:  
      oid = null;
      octetString = null;
      break;
    }
    
    if (oid == null || octetString == null) {
      LOGGER.info(
          String.format("PDU skipping %s %s.", oid, octetString));
    } else {
      VariableBinding variableBinding = new VariableBinding(oid, octetString);
      pdu.add(variableBinding);
      LOGGER.finest(
          String.format(
              "PUD added %s.", variableBinding));
    }
  }
}
