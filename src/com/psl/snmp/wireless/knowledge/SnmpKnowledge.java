package com.psl.snmp.wireless.knowledge;

public enum SnmpKnowledge {
  
  TRAP_OID, ENTERPRISE_OID, ENTERPRISE_FULL_OID, GENERIC_TRAP_ID, SPECIFIC_TRAP_OID;

  public Object get() {
    switch (this) {
    case TRAP_OID:
      return ".1.3.6.1.2.1.1.6";
    case ENTERPRISE_OID:
      return ".1.3.6.1.4.1.8066.1000.1";
    case ENTERPRISE_FULL_OID:
      return ".1.3.6.1.4.1.8066.1000.1.1.1";
    case GENERIC_TRAP_ID:
      return 6;
    case SPECIFIC_TRAP_OID:
      return 2;
    default:
      return null;
    }
  }
}
