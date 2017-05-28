/**
 * BmiReport.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cloud.bzu.BmiService;

public class BmiReport  implements java.io.Serializable {
    private double bmiValue;

    private java.lang.String bmiDesc;

    public BmiReport() {
    }

    public BmiReport(
           double bmiValue,
           java.lang.String bmiDesc) {
           this.bmiValue = bmiValue;
           this.bmiDesc = bmiDesc;
    }


    /**
     * Gets the bmiValue value for this BmiReport.
     * 
     * @return bmiValue
     */
    public double getBmiValue() {
        return bmiValue;
    }


    /**
     * Sets the bmiValue value for this BmiReport.
     * 
     * @param bmiValue
     */
    public void setBmiValue(double bmiValue) {
        this.bmiValue = bmiValue;
    }


    /**
     * Gets the bmiDesc value for this BmiReport.
     * 
     * @return bmiDesc
     */
    public java.lang.String getBmiDesc() {
        return bmiDesc;
    }


    /**
     * Sets the bmiDesc value for this BmiReport.
     * 
     * @param bmiDesc
     */
    public void setBmiDesc(java.lang.String bmiDesc) {
        this.bmiDesc = bmiDesc;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BmiReport)) return false;
        BmiReport other = (BmiReport) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.bmiValue == other.getBmiValue() &&
            ((this.bmiDesc==null && other.getBmiDesc()==null) || 
             (this.bmiDesc!=null &&
              this.bmiDesc.equals(other.getBmiDesc())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += new Double(getBmiValue()).hashCode();
        if (getBmiDesc() != null) {
            _hashCode += getBmiDesc().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BmiReport.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://bzu.cloud:8080/BmiService", "BmiReport"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bmiValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bzu.cloud:8080/BmiService", "BmiValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bmiDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://bzu.cloud:8080/BmiService", "BmiDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
