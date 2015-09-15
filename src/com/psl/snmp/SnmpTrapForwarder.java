package com.psl.snmp;

import java.util.logging.Logger;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
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
  private static int snmpVersion = SnmpConstants.version2c;
  private static String violationType = "Major";
  private static int snmpRetry = 2;
  private static int snmpTimeOut = 5000;
  
  private static Logger LOGGER;
  
  /*
      Omnibus Probe sample log
      ========================
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] ReqId:     1676627794
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] community: public
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] IPaddress: 10.211.50.18
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] PeerIPaddress:     10.211.25.48
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] ReceivedPort:      162
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] ReceivedTime:      1442312765
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] Protocol:  UDP
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] SNMP_Version:      2
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] UpTime:    0
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] Uptime:
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] .1.3.6.1.2.1.1.3.0:
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] notify:    .1.3.6.1.2.1.1.6
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] .1.3.6.1.6.3.1.1.4.1.0:    .1.3.6.1.2.1.1.6
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] Node:      10.211.50.18
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] .1.3.6.1.6.3.18.1.3.0:     10.211.50.18
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] PeerAddress:       10.211.25.48
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] EventCount:        3
      2015-09-15T18:26:05: Debug: D-UNK-000-000: [Event Processor] Processing alert {0 remaining}
      2015-09-15T18:26:05: Debug: D-UNK-000-000: Rules file processing took 64 usec.
   */
  //.1.3.6.1.2.1.1.6 - Generic trap
  //.1.3.6.1.4.1.8066.1000.1.1.1 - Enterprise trap
  private static String trapOid = ".1.3.6.1.2.1.1.6";
  private static String enterpriseOid = ".1.3.6.1.4.1.8066.1000.1";
  private static String OID = enterpriseOid + ".1.1.";
  
  
  public static void main (String[] args) {
    
    LOGGER = Logger.getLogger(SnmpTrapForwarder.class.getName());
    
    try {
      // Create a PDU
      PDU pdu = DefaultPDUFactory.createPDU(snmpVersion);
      pdu.setType(PDU.TRAP);
      LOGGER.info("Created PDU.");
      
      pdu.add(new VariableBinding(SnmpConstants.sysUpTime));
      LOGGER.info("SnmpConstants.snmpTrapOID: " + SnmpConstants.snmpTrapOID);
      //pdu.add(new VariableBinding(SnmpConstants.snmpTrapOID, new OID(trapOid))); // $notify
      pdu.add(new VariableBinding(SnmpConstants.snmpTrapOID, new OID(enterpriseOid)));
      pdu.add(new VariableBinding(SnmpConstants.snmpTrapAddress, new IpAddress(targetHost)));
      
      // Etisalat Wireless specific
      pdu.add(new VariableBinding(new OID(OID + "13"), new OctetString("additionalText")));
      pdu.add(new VariableBinding(new OID(OID + "18"), new OctetString("alarmID")));
      pdu.add(new VariableBinding(new OID(OID + "4"), new OctetString("time")));
      pdu.add(new VariableBinding(new OID(OID + "3"), new OctetString("eventType")));
      pdu.add(new VariableBinding(new OID(OID + "1"), new OctetString("managedObjectClass")));
      pdu.add(new VariableBinding(new OID(OID + "2"), new OctetString("managedObjectInstance")));
      pdu.add(new VariableBinding(new OID(OID + "12"), new OctetString("monitoredAttribute")));
      pdu.add(new VariableBinding(new OID(OID + "10"), new OctetString("notificationIdentifier")));
      pdu.add(new VariableBinding(new OID(OID + "7"), new OctetString("severity")));
      pdu.add(new VariableBinding(new OID(OID + "5"), new OctetString("probableCause")));
      pdu.add(new VariableBinding(new OID(OID + "6"), new OctetString("specificProblem")));
      pdu.add(new VariableBinding(new OID(OID + "9"), new OctetString("alarmPredicate")));
      pdu.add(new VariableBinding(new OID(OID + "8"), new OctetString("trendIndication")));
      pdu.add(new VariableBinding(new OID(OID + "14"), new OctetString("ackStatus")));
      pdu.add(new VariableBinding(new OID(OID + "15"), new OctetString("ackText")));
      pdu.add(new VariableBinding(new OID(OID + "16"), new OctetString("ackUserName")));
      pdu.add(new VariableBinding(new OID(OID + "20"), new OctetString("hostName")));
      pdu.add(new VariableBinding(new OID(OID + "21"), new OctetString("port")));
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
