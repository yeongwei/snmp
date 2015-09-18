# SNMP 

##

### Introduction

Sandbox / development / testing effort around SNMP protocol.

### Content

1. TNPM Wireless SNMP Trap Consumer / Forwarder

### User Guide

#### TNPM Wireless SNMP Trap

1. Entry method at `com.psl.wireless.alarm.knowledge.SnmpTrapForwarder`.
2. Input arguments; From `Eclipse`, go to `Run` then select `Run Configurations...`; navigate to `Arguments` tab; copy the following into the `Program arguments:` text box, ``` EVENT_TIME="1442474351" NOTIFICATION_IDENTIFIER="3" SPECIFIC_PROBLEM="Threshold Crossed" MANAGED_OBJECT_INSTANCE="10002" MANAGED_OBJECT_CLASS="Cell" EVENT_TYPE="Environmental" PROBABLE_CAUSE="informationMissing" TREND_INDICATION="more Severe" MONITORED_ATTRIBUTE="0" ADDITIONAL_TEXT="additional_text" ALARM_SEVERITY="Major" ALARM_PREDICATE="seizure_attempts" TARGET_HOST="127.0.0.1"```.
3. java.util.logging; From the `Run Configuration` Window; Add a the following entry `VM Arguments:` text box, ```-Djava.util.logging.config.file=<ABSOLUTE_PATH>/logging.properties```.