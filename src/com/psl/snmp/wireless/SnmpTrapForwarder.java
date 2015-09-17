package com.psl.snmp.wireless;

import java.util.HashMap;
import java.util.logging.Logger;

import org.snmp4j.PDU;
import org.snmp4j.PDUv1;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.util.DefaultPDUFactory;

import com.psl.snmp.wireless.knowledge.CommunityKnowledge;
import com.psl.snmp.wireless.knowledge.InputKnowledge;
import com.psl.snmp.wireless.knowledge.SnmpKnowledge;
import com.psl.snmp.wireless.knowledge.TrapKnowledge;

public class SnmpTrapForwarder {

  private final static int snmpVersion = SnmpConstants.version1;
  
  private static Logger LOGGER;

  private static String targetHost = "127.0.0.1";
  private static String communityString = "public";
  
  private static int trapPort = 162;
  
  private static int snmpRetry = 2;
  private static int snmpTimeOut = 5000;
  
  private static HashMap<String, String> inputMap = new HashMap<String, String>();
  private static PDUv1 pdu;
  
  public static void main(String[] args) {

    LOGGER = Logger.getLogger(SnmpTrapForwarder.class.getName());

    try {
      LOGGER.info("Initiate as SNMPv1.");
      
      // Parse input
      parseInput(args);
      
      // Validate input
      validateInput();
      
      // Create a PDU
      createPdu();
      
      // Set created PDU
      setPdu();
      
      /*
      LOGGER.info("Configured PDU.");

      // Create Transport
      TransportMapping<?> transport = new DefaultUdpTransportMapping();
      transport.listen();
      LOGGER.info("Created transport layer.");

      // Create Target
      CommunityTarget communityTarget = new CommunityTarget();
      communityTarget.setCommunity(new OctetString(communityString));
      communityTarget.setVersion(snmpVersion);
      communityTarget.setAddress(new UdpAddress(targetHost + "/" + trapPort));
      communityTarget.setRetries(snmpRetry);
      communityTarget.setTimeout(snmpTimeOut);
      LOGGER.info("Created SNMP target.");

      // SNMP
      Snmp snmp = new Snmp(transport);
      LOGGER.info("Created SNMP object.");
      snmp.send(pdu, communityTarget);
      LOGGER.info("Forwarded PDU.");
      snmp.close();
      */
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }
  
  /**
   * 
   * @param args
   */
  private static void parseInput(String[] args) throws Exception {
    for (String i : args) {
      LOGGER.finest(
          String.format("About to process %s.", i));
      String[] parts = i.split("=");
      LOGGER.finest(
          String.format("Found input key %s with value %s.", parts[0], parts[1]));
      inputMap.put(parts[0], parts[1]);
    }
    
    LOGGER.finest("Iterating inputMap.");
    for (Object key : inputMap.keySet()) {
      LOGGER.finest(
          String.format("Found input key %s with value %s.", key, inputMap.get(key)));
    }
  }
  
  /**
   * 
   */
  private static void validateInput() throws Exception {
    if (inputMap.containsKey(
        CommunityKnowledge.TARGET_HOST.getString())) {
      targetHost = inputMap.get(
          CommunityKnowledge.TARGET_HOST.getString());
      LOGGER.info(String.format("TARGET_HOST is set to %s.", targetHost));
    } else {
      throw new Exception("TARGET_HOST must be provided.");
    }
    
    if (inputMap.containsKey(
        CommunityKnowledge.TRAP_PORT.getString())) {
      trapPort = Integer.parseInt(
          inputMap.get(CommunityKnowledge.TRAP_PORT.getString()));
    }
    LOGGER.info(String.format("TRAP_PORT is set to %d.", trapPort));
    
    if (inputMap.containsKey(
        CommunityKnowledge.COMMUNITY_STRING.getString())) {
      communityString = inputMap.get(
          CommunityKnowledge.COMMUNITY_STRING.getString());
    }
    LOGGER.info(String.format("COMMUNITY_STRING is set to %s.", communityString));
  }
  
  /**
   * 
   */
  private static void createPdu() {
    LOGGER.finest(
        String.format("Creating PDU with version %s.", snmpVersion));
    pdu = (PDUv1) DefaultPDUFactory.createPDU(snmpVersion);
    
    LOGGER.finest(
        String.format("Creating PDU as type %s.", PDU.V1TRAP));
    pdu.setType(PDU.V1TRAP);
    
    LOGGER.finest(
        String.format(
            "Creating PDU with Enterprise OID %s.",
            SnmpKnowledge.ENTERPRISE_OID.get().toString()));
    pdu.setEnterprise(
        new OID(SnmpKnowledge.ENTERPRISE_OID.get().toString()));
    
    LOGGER.finest(
        String.format(
            "Creating PDU with Generic Trap OID %s.",
            SnmpKnowledge.GENERIC_TRAP_ID.get().toString()));
    pdu.setGenericTrap(
        Integer.parseInt(SnmpKnowledge.GENERIC_TRAP_ID.get().toString()));
    
    LOGGER.finest(
        String.format(
            "Creating PDU with Specific Trap OID %s.",
            SnmpKnowledge.SPECIFIC_TRAP_OID.get().toString()));
    pdu.setSpecificTrap(
        Integer.parseInt(SnmpKnowledge.SPECIFIC_TRAP_OID.get().toString()));

    LOGGER.info("Created PDUv1.");
  }
  
  /**
   * 
   */
  public static void setPdu() {
    for (Object key : inputMap.keySet()) {
      try {
          InputKnowledge inpKnw = InputKnowledge.valueOf(key.toString());
          TrapKnowledge trapKnw = inpKnw.getMapping();
          LOGGER.finest(
              String.format(
                  "Trap knowledge found %s.", 
                  trapKnw.toString()));
          
          OID oid = new OID(trapKnw.getOid());
          OctetString octetString = new OctetString(inputMap.get(key));
          VariableBinding variableBinding = new VariableBinding(oid, octetString);
          pdu.add(variableBinding);
          LOGGER.finest(
              String.format(
                  "PUD added %s value %s.", 
                  oid, variableBinding));
          
      } catch (Exception ex) {
        LOGGER.finest(String.format("Input knowledge does not have %s.", key.toString()));
      }
    }
  }
}