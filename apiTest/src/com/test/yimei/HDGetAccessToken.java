package com.test.yimei;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.0-milestone2
 * 2016-01-13T10:24:15.280+08:00
 * Generated source version: 3.0.0-milestone2
 * 
 */
@WebServiceClient(name = "HD_GetAccess_Token", 
                  wsdlLocation = "http://116.58.219.253:9099/HD_GetAccess_Token.asmx?wsdl",
                  targetNamespace = "http://tempuri.org/") 
public class HDGetAccessToken extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://tempuri.org/", "HD_GetAccess_Token");
    public final static QName HDGetAccessTokenSoap = new QName("http://tempuri.org/", "HD_GetAccess_TokenSoap");
    public final static QName HDGetAccessTokenSoap12 = new QName("http://tempuri.org/", "HD_GetAccess_TokenSoap12");
    static {
        URL url = null;
        try {
            url = new URL("http://116.58.219.253:9099/HD_GetAccess_Token.asmx?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(HDGetAccessToken.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://116.58.219.253:9099/HD_GetAccess_Token.asmx?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public HDGetAccessToken(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public HDGetAccessToken(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public HDGetAccessToken() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public HDGetAccessToken(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public HDGetAccessToken(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public HDGetAccessToken(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName);
    }    

    /**
     *
     * @return
     *     returns HDGetAccessTokenSoap
     */
    @WebEndpoint(name = "HD_GetAccess_TokenSoap")
    public HDGetAccessTokenSoap getHDGetAccessTokenSoap() {
        return super.getPort(HDGetAccessTokenSoap, HDGetAccessTokenSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HDGetAccessTokenSoap
     */
    @WebEndpoint(name = "HD_GetAccess_TokenSoap")
    public HDGetAccessTokenSoap getHDGetAccessTokenSoap(WebServiceFeature... features) {
        return super.getPort(HDGetAccessTokenSoap, HDGetAccessTokenSoap.class, features);
    }
    /**
     *
     * @return
     *     returns HDGetAccessTokenSoap
     */
    @WebEndpoint(name = "HD_GetAccess_TokenSoap12")
    public HDGetAccessTokenSoap getHDGetAccessTokenSoap12() {
        return super.getPort(HDGetAccessTokenSoap12, HDGetAccessTokenSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HDGetAccessTokenSoap
     */
    @WebEndpoint(name = "HD_GetAccess_TokenSoap12")
    public HDGetAccessTokenSoap getHDGetAccessTokenSoap12(WebServiceFeature... features) {
        return super.getPort(HDGetAccessTokenSoap12, HDGetAccessTokenSoap.class, features);
    }

}
