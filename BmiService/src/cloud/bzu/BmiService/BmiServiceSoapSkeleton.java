/**
 * BmiServiceSoapSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cloud.bzu.BmiService;

public class BmiServiceSoapSkeleton implements cloud.bzu.BmiService.BmiServiceSoap, org.apache.axis.wsdl.Skeleton {
    private cloud.bzu.BmiService.BmiServiceSoap impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://bzu.cloud:8080/BmiService", "w"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://bzu.cloud:8080/BmiService", "h"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getBmiReport", _params, new javax.xml.namespace.QName("http://bzu.cloud:8080/BmiService", "getBmiReportResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://bzu.cloud:8080/BmiService", "BmiReport"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://bzu.cloud:8080/BmiService", "getBmiReport"));
        _oper.setSoapAction("http://bzu.cloud:8080/BmiService/getBmiReport");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getBmiReport") == null) {
            _myOperations.put("getBmiReport", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getBmiReport")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://bzu.cloud:8080/BmiService", "w"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://bzu.cloud:8080/BmiService", "h"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getBmiValue", _params, new javax.xml.namespace.QName("http://bzu.cloud:8080/BmiService", "getBmiValueResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://bzu.cloud:8080/BmiService", "getBmiValue"));
        _oper.setSoapAction("http://bzu.cloud:8080/BmiService/getBmiValue");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getBmiValue") == null) {
            _myOperations.put("getBmiValue", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getBmiValue")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://bzu.cloud:8080/BmiService", "bmi"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getBmiDesc", _params, new javax.xml.namespace.QName("http://bzu.cloud:8080/BmiService", "getBmiDescResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://bzu.cloud:8080/BmiService", "getBmiDesc"));
        _oper.setSoapAction("http://bzu.cloud:8080/BmiService/getBmiDesc");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getBmiDesc") == null) {
            _myOperations.put("getBmiDesc", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getBmiDesc")).add(_oper);
    }

    public BmiServiceSoapSkeleton() {
        this.impl = new cloud.bzu.BmiService.BmiServiceSoapImpl();
    }

    public BmiServiceSoapSkeleton(cloud.bzu.BmiService.BmiServiceSoap impl) {
        this.impl = impl;
    }
    public cloud.bzu.BmiService.BmiReport getBmiReport(double w, double h) throws java.rmi.RemoteException
    {
        cloud.bzu.BmiService.BmiReport ret = impl.getBmiReport(w, h);
        return ret;
    }

    public double getBmiValue(double w, double h) throws java.rmi.RemoteException
    {
        double ret = impl.getBmiValue(w, h);
        return ret;
    }

    public java.lang.String getBmiDesc(double bmi) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.getBmiDesc(bmi);
        return ret;
    }

}
