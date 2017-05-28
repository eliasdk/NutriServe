/**
 * BmiService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cloud.bzu.BmiService;

public interface BmiService extends javax.xml.rpc.Service {
    public java.lang.String getBmiServiceSoapAddress();

    public cloud.bzu.BmiService.BmiServiceSoap getBmiServiceSoap() throws javax.xml.rpc.ServiceException;

    public cloud.bzu.BmiService.BmiServiceSoap getBmiServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
