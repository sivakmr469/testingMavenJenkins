package com.accenture.tag.file.uploader.utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
 
public class XmlDomDocument {
 
    private Document m_doc;
 
    // Note: The parse methods have been omitted in this code listing
 
    public XmlDomDocument() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        m_doc = builder.newDocument();
    }
 
    public void addChildElement(String parentTag, int parentIndex, String childTag, String childValue) {
        NodeList list = m_doc.getElementsByTagName(parentTag);
        Element parent = (Element) list.item(parentIndex);
        Element child = m_doc.createElement(childTag);
        if (childValue != null) {
            child.appendChild(m_doc.createTextNode(childValue));
        }
        if (parent == null) {
            m_doc.appendChild(child);
        }
        else {
            parent.appendChild(child);
        }
    }
 
    public void setAttributeValue(String elementTag, int elementIndex, String attributeTag,
                                  String attributeValue) {
        NodeList list = m_doc.getElementsByTagName(elementTag);
        Element element = (Element) list.item(elementIndex);
        element.setAttribute(attributeTag, attributeValue);
    }
 
    public String renderXml() throws Exception {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(m_doc), new StreamResult(out));
        return out.toString();
    }
}