/**
 * BmiServiceSoapImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cloud.bzu.BmiService;

public class BmiServiceSoapImpl implements cloud.bzu.BmiService.BmiServiceSoap{
    public cloud.bzu.BmiService.BmiReport getBmiReport(double w, double h) throws java.rmi.RemoteException {
    	BmiReport report = new BmiReport();
    	report.setBmiValue(w/h/h);
    	if(report.getBmiValue()<25 ) {
    		report.setBmiDesc("Normal");
    	}
    	else {
    		report.setBmiDesc("Over Weight");
    	}
    	return report;
    }

    public double getBmiValue(double w, double h) throws java.rmi.RemoteException {
        return w/h/h;
    }

    public java.lang.String getBmiDesc(double bmi) throws java.rmi.RemoteException {
    	if(bmi<25 ) {
    		return "Normal";
    	}
    	else {
    		return "Over Weight";
    	}
    }

}
