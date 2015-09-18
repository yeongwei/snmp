package com.psl.wireless.alarm.knowledge.snmp;

public enum InputKnowledge {
    EVENT_TIME,
    NOTIFICATION_IDENTIFIER,
    SPECIFIC_PROBLEM,
    MANAGED_OBJECT_INSTANCE,
    MANAGED_OBJECT_CLASS,
    EVENT_TYPE,
    PROBABLE_CAUSE,
    TREND_INDICATION,
    MONITORED_ATTRIBUTE,
    ADDITIONAL_TEXT,
    ALARM_SEVERITY,
    ALARM_PREDICATE;
    
    public TrapKnowledge getMapping() {
      switch (this) {
      case EVENT_TIME:
        return TrapKnowledge.EVENT_TIME;
      case NOTIFICATION_IDENTIFIER:
        return TrapKnowledge.NOTIFICATION_IDENTIFIER;
      case SPECIFIC_PROBLEM:
        return TrapKnowledge.SPECIFIC_PROBLEM;
      case MANAGED_OBJECT_INSTANCE:
        return TrapKnowledge.MANAGED_OBJECT_INSTANCE;
      case MANAGED_OBJECT_CLASS:
        return TrapKnowledge.MANAGED_OBJECT_CLASS;
      case EVENT_TYPE:
        return TrapKnowledge.EVENT_TYPE;
      case PROBABLE_CAUSE:
        return TrapKnowledge.PROBABLE_CAUSE;
      case TREND_INDICATION:
        return TrapKnowledge.TREND_INDICATION;
      case MONITORED_ATTRIBUTE:
        return TrapKnowledge.MONITORED_ATTRIBUTE;
      case ADDITIONAL_TEXT:
        return TrapKnowledge.ADDITIONAL_TEXT;
      case ALARM_SEVERITY:
        return TrapKnowledge.ALARM_SEVERITY;
      case ALARM_PREDICATE:
        return TrapKnowledge.ALARM_PREDICATE;
      default:
        return null;
      }
    }
}
