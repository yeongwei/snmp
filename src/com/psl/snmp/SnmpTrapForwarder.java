package com.psl.snmp;

import java.util.logging.Logger;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.PDUv1;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.IpAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;

public class SnmpTrapForwarder {
  
  private static String community = "public";
  private static String targetHost = "10.211.50.18";
  //private static String targetHost = "127.0.0.1";
  private static int trapPort = 162;
  private static int snmpVersion = SnmpConstants.version1;
  private static String violationType = "Major";
  private static int snmpRetry = 2;
  private static int snmpTimeOut = 5000;
  
  private static Logger LOGGER;
  
  //.1.3.6.1.2.1.1.6 - Generic trap
  //.1.3.6.1.4.1.8066.1000.1.1.1 - Enterprise trap
  private static String trapOid = ".1.3.6.1.2.1.1.6";
  private static String enterpriseOid = ".1.3.6.1.4.1.8066.1000.1";
  private static String OID = enterpriseOid + ".1.1.";
  private static int genericTrap = 6;
  private static int specificTrap = 2;
  
  
  public static void main (String[] args) {
    
    LOGGER = Logger.getLogger(SnmpTrapForwarder.class.getName());
    
    try {
      // Create a PDU
      PDU pdu = DefaultPDUFactory.createPDU(snmpVersion);  
      PDUv1 pduV1 = (PDUv1) pdu;
      LOGGER.info("Created PDU.");
      
      pduV1.setType(PDU.V1TRAP);
      pduV1.setEnterprise(new OID(enterpriseOid));
      pduV1.setGenericTrap(genericTrap);
      pduV1.setSpecificTrap(specificTrap);
      LOGGER.info("As SNMPv1.");
      
      // Wireless specific
      pduV1.add(new VariableBinding(new OID(OID + "13"), new OctetString("additionalText")));
      pduV1.add(new VariableBinding(new OID(OID + "18"), new OctetString("alarmID")));
      pduV1.add(new VariableBinding(new OID(OID + "4"), new OctetString("time")));
      pduV1.add(new VariableBinding(new OID(OID + "3"), new OctetString("eventType")));
      pduV1.add(new VariableBinding(new OID(OID + "1"), new OctetString("managedObjectClass")));
      pduV1.add(new VariableBinding(new OID(OID + "2"), new OctetString("managedObjectInstance")));
      pduV1.add(new VariableBinding(new OID(OID + "12"), new OctetString("monitoredAttribute")));
      pduV1.add(new VariableBinding(new OID(OID + "10"), new OctetString("notificationIdentifier")));
      pduV1.add(new VariableBinding(new OID(OID + "7"), new OctetString("severity")));
      pduV1.add(new VariableBinding(new OID(OID + "5"), new OctetString("probableCause")));
      pduV1.add(new VariableBinding(new OID(OID + "6"), new OctetString("specificProblem")));
      pduV1.add(new VariableBinding(new OID(OID + "9"), new OctetString("alarmPredicate")));
      pduV1.add(new VariableBinding(new OID(OID + "8"), new OctetString("trendIndication")));
      pduV1.add(new VariableBinding(new OID(OID + "14"), new OctetString("ackStatus")));
      pduV1.add(new VariableBinding(new OID(OID + "15"), new OctetString("ackText")));
      pduV1.add(new VariableBinding(new OID(OID + "16"), new OctetString("ackUserName")));
      pduV1.add(new VariableBinding(new OID(OID + "20"), new OctetString("hostName")));
      pduV1.add(new VariableBinding(new OID(OID + "21"), new OctetString("port")));
      LOGGER.info("Configured PDU.");
      
      // Create Transport
      TransportMapping<?> transport = new DefaultUdpTransportMapping();
      transport.listen();
      LOGGER.info("Created transport layer.");
      
      // Create Target
      CommunityTarget communityTarget = new CommunityTarget();
      communityTarget.setCommunity(new OctetString(community));
      communityTarget.setVersion(snmpVersion);
      communityTarget.setAddress(new UdpAddress(targetHost + "/" + trapPort));
      communityTarget.setRetries(snmpRetry);
      communityTarget.setTimeout(snmpTimeOut);
      LOGGER.info("Created SNMP target.");
      
      // SNMP
      Snmp snmp = new Snmp(transport);
      LOGGER.info("Created SNMP object.");
      snmp.send(pdu, communityTarget);
      LOGGER.info("Sent PDU as SNMP protocol.");
      snmp.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    
  }
}
