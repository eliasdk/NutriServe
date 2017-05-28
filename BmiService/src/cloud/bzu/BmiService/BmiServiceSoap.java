/**
 * BmiServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cloud.bzu.BmiService;

public interface BmiServiceSoap extends java.rmi.Remote {
    public cloud.bzu.BmiService.BmiReport getBmiReport(double w, double h) throws java.rmi.RemoteException;
    public double getBmiValue(double w, double h) throws java.rmi.RemoteException;
    public java.lang.String getBmiDesc(double bmi) throws java.rmi.RemoteException;
}
