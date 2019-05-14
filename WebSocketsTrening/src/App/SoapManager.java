package App;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.util.Collections;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class SoapManager {
    public static Node CreateMessage(String message, String receiver)  throws Exception
    {
        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();

        SOAPHeader soapHeader = soapEnvelope.getHeader();
        SOAPHeaderElement senderHeader = soapHeader.addHeaderElement(soapEnvelope.createName(
                "Sender" , "Sender", ConnectionManager.name));
        SOAPHeaderElement receiverHeader = soapHeader.addHeaderElement(soapEnvelope.createName(
                "Receiver" , "Receiver", receiver));

        SOAPBody soapBody = soapEnvelope.getBody();

        Name bodyName = soapEnvelope.createName("Message", "message", message);
        SOAPBodyElement gltp = soapBody.addBodyElement(bodyName);

        Source source = soapPart.getContent();
        Node root = null;
        if (source instanceof DOMSource) {
            root = ((DOMSource) source).getNode();
        } else if (source instanceof SAXSource) {
            InputSource inSource = ((SAXSource) source).getInputSource();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = null;

            db = dbf.newDocumentBuilder();

            Document doc = db.parse(inSource);
            root = (Node) doc.getDocumentElement();
        }
        return root;

    }
    public static void dumpDocument(Node root) throws TransformerException {

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        try {
            transformer.transform(new DOMSource(root), new StreamResult(ConnectionManager.socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
