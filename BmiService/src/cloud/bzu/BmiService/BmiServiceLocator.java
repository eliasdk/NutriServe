/**
 * BmiServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cloud.bzu.BmiService;

public class BmiServiceLocator extends org.apache.axis.client.Service implements cloud.bzu.BmiService.BmiService {

    public BmiServiceLocator() {
    }


    public BmiServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BmiServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BmiServiceSoap
    private java.lang.String BmiServiceSoap_address = "http://bzu.cloud:8080/BmiService/webservices/bmiservice.asmx";

    public java.lang.String getBmiServiceSoapAddress() {
        return BmiServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BmiServiceSoapWSDDServiceName = "BmiServiceSoap";

    public java.lang.String getBmiServiceSoapWSDDServiceName() {
        return BmiServiceSoapWSDDServiceName;
    }

    public void setBmiServiceSoapWSDDServiceName(java.lang.String name) {
        BmiServiceSoapWSDDServiceName = name;
    }

    public cloud.bzu.BmiService.BmiServiceSoap getBmiServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BmiServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBmiServiceSoap(endpoint);
    }

    public cloud.bzu.BmiService.BmiServiceSoap getBmiServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            cloud.bzu.BmiService.BmiServiceSoapStub _stub = new cloud.bzu.BmiService.BmiServiceSoapStub(portAddress, this);
            _stub.setPortName(getBmiServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBmiServiceSoapEndpointAddress(java.lang.String address) {
        BmiServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (cloud.bzu.BmiService.BmiServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                cloud.bzu.BmiService.BmiServiceSoapStub _stub = new cloud.bzu.BmiService.BmiServiceSoapStub(new java.net.URL(BmiServiceSoap_address), this);
                _stub.setPortName(getBmiServiceSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BmiServiceSoap".equals(inputPortName)) {
            return getBmiServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://bzu.cloud:8080/BmiService", "BmiService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://bzu.cloud:8080/BmiService", "BmiServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BmiServiceSoap".equals(portName)) {
            setBmiServiceSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
