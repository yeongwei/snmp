package com.psl.wireless.alarm.knowledge.snmp;

public enum CommunityKnowledge {

  TARGET_HOST,
  COMMUNITY_STRING,
  TRAP_PORT,
  SNMP_RETRY,
  SNMP_TIMEOUT;
  
  public String getString() {
    switch (this) {
    case TARGET_HOST:
      return "TARGET_HOST";
    case COMMUNITY_STRING:
      return "COMMUNITY_STRING";
    case TRAP_PORT:
      return "TRAP_PORT";
    case SNMP_RETRY:
      return "SNMP_RETRY";
    case SNMP_TIMEOUT:
      return "SNMP_TIMEOUT";
    default: 
      return null;
    }
  }
}
