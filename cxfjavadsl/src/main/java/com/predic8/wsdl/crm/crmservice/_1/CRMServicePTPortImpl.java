
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.predic8.wsdl.crm.crmservice._1;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.0.0
 * 2016-10-17T12:25:07.483-04:00
 * Generated source version: 3.0.0
 * 
 */

@javax.jws.WebService(
                      serviceName = "CustomerService",
                      portName = "CRMServicePTPort",
                      targetNamespace = "http://predic8.com/wsdl/crm/CRMService/1/",
                      wsdlLocation = "http://www.predic8.com:8080/crm/CustomerService?wsdl",
                      endpointInterface = "com.predic8.wsdl.crm.crmservice._1.CRMServicePT")
                      
public class CRMServicePTPortImpl implements CRMServicePT {

    private static final Logger LOG = Logger.getLogger(CRMServicePTPortImpl.class.getName());

    /* (non-Javadoc)
     * @see com.predic8.wsdl.crm.crmservice._1.CRMServicePT#getAll(*
     */
    public java.util.List<com.predic8.crm._1.CustomerType> getAll() { 
        LOG.info("Executing operation getAll");
        try {
            java.util.List<com.predic8.crm._1.CustomerType> _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.predic8.wsdl.crm.crmservice._1.CRMServicePT#create(com.predic8.crm._1.CustomerType  customer )*
     */
    public void create(com.predic8.crm._1.CustomerType customer) { 
        LOG.info("Executing operation create");
        System.out.println(customer);
        try {
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.predic8.wsdl.crm.crmservice._1.CRMServicePT#get(java.lang.String  id )*
     */
    public com.predic8.crm._1.CustomerType get(java.lang.String id) { 
        LOG.info("Executing operation get");
        System.out.println(id);
        try {
            com.predic8.crm._1.CustomerType _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
