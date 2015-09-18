package com.psl.wireless.alarm.knowledge.snmp;

public enum SeverityKnowledge {
  CLEARED, INDETERMINATE, CRITICAL, MAJOR, MINOR, WARNING;

  public String getString() {
    switch (this) {
    case CLEARED:
      return "1";
    case INDETERMINATE:
      return "2";
    case CRITICAL:
      return "3";
    case MAJOR:
      return "4";
    case MINOR:
      return "5";
    case WARNING:
      return "6";
    default:
      return "7";
    }
  }
}
