
package org.cn.kkl.rsl.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cn.kkl.rsl.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddWaybill_QNAME = new QName("http://ws.rsl.kkl.cn.org/", "addWaybill");
    private final static QName _WaybillDetailListResponse_QNAME = new QName("http://ws.rsl.kkl.cn.org/", "waybillDetailListResponse");
    private final static QName _AddWaybillResponse_QNAME = new QName("http://ws.rsl.kkl.cn.org/", "addWaybillResponse");
    private final static QName _WaybillDetailList_QNAME = new QName("http://ws.rsl.kkl.cn.org/", "waybillDetailList");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cn.kkl.rsl.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddWaybill }
     * 
     */
    public AddWaybill createAddWaybill() {
        return new AddWaybill();
    }

    /**
     * Create an instance of {@link AddWaybillResponse }
     * 
     */
    public AddWaybillResponse createAddWaybillResponse() {
        return new AddWaybillResponse();
    }

    /**
     * Create an instance of {@link WaybillDetailList }
     * 
     */
    public WaybillDetailList createWaybillDetailList() {
        return new WaybillDetailList();
    }

    /**
     * Create an instance of {@link WaybillDetailListResponse }
     * 
     */
    public WaybillDetailListResponse createWaybillDetailListResponse() {
        return new WaybillDetailListResponse();
    }

    /**
     * Create an instance of {@link Waybill }
     * 
     */
    public Waybill createWaybill() {
        return new Waybill();
    }

    /**
     * Create an instance of {@link WaybillDetail }
     * 
     */
    public WaybillDetail createWaybillDetail() {
        return new WaybillDetail();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddWaybill }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.rsl.kkl.cn.org/", name = "addWaybill")
    public JAXBElement<AddWaybill> createAddWaybill(AddWaybill value) {
        return new JAXBElement<AddWaybill>(_AddWaybill_QNAME, AddWaybill.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WaybillDetailListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.rsl.kkl.cn.org/", name = "waybillDetailListResponse")
    public JAXBElement<WaybillDetailListResponse> createWaybillDetailListResponse(WaybillDetailListResponse value) {
        return new JAXBElement<WaybillDetailListResponse>(_WaybillDetailListResponse_QNAME, WaybillDetailListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddWaybillResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.rsl.kkl.cn.org/", name = "addWaybillResponse")
    public JAXBElement<AddWaybillResponse> createAddWaybillResponse(AddWaybillResponse value) {
        return new JAXBElement<AddWaybillResponse>(_AddWaybillResponse_QNAME, AddWaybillResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WaybillDetailList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.rsl.kkl.cn.org/", name = "waybillDetailList")
    public JAXBElement<WaybillDetailList> createWaybillDetailList(WaybillDetailList value) {
        return new JAXBElement<WaybillDetailList>(_WaybillDetailList_QNAME, WaybillDetailList.class, null, value);
    }

}
