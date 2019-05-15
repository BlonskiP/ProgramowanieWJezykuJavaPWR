package App;

import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.html.HTMLDocument;
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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
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
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class SoapManager {
    public static Controller guiControler;
    public static void setControler(Controller ctr){ SoapManager.guiControler=ctr;}
    public static Node CreateMessage(String message, String receiver)  throws Exception
    {
        MessageFactory msgFac=MessageFactory.newInstance();
        SOAPMessage soapMessage= msgFac.createMessage();

        SOAPBody soapBody= soapMessage.getSOAPBody();
        SOAPHeader soapHeader = soapMessage.getSOAPHeader();
        SOAPFactory fac = SOAPFactory.newInstance();



        Name bodyName = fac.createName("messageText");
        SOAPBodyElement text = soapBody.addBodyElement(bodyName);

        ///
        Name childName = fac.createName("value");
        SOAPElement value = text.addChildElement(childName);
        value.addTextNode(message);

        // HEADERS
        Name hvalues = fac.createName("HeaderValues","hv","no values");
        SOAPElement hvaluesElement = soapHeader.addChildElement(hvalues);

        // Header Values
        Name senderName = fac.createName("Sender");
        SOAPElement sender = hvaluesElement.addChildElement(senderName);
        sender.addTextNode(ConnectionManager.host+":"+ConnectionManager.listeningPort);

        Name receiverName = fac.createName("receiver");
        SOAPElement receiverElement = hvaluesElement.addChildElement(receiverName);
        receiverElement.addTextNode(receiver);
        soapMessage.writeTo(System.out);


        //////////////////
        Source source = soapMessage.getSOAPPart().getContent();
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
    public static void dumpDocument(Node root) throws TransformerException, ParserConfigurationException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();

            // Uncomment if you do not require XML declaration
            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

            //A character stream that collects its output in a string buffer,
            //which can then be used to construct a string.
            StringWriter writer = new StringWriter();

            //transform document to string
            transformer.transform(new DOMSource(root), new StreamResult(writer));

            String xmlString = writer.getBuffer().toString();
            OutputStream os = ConnectionManager.socket.getOutputStream();
            os.write(xmlString.getBytes());
            os.flush();
            os.close();
        }
        catch (TransformerException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void createMessageFromStream(String newMessage) {
        InputStream is = new ByteArrayInputStream(newMessage.getBytes());

        try {
            if(is.available()>0) {
                SOAPMessage msg = MessageFactory.newInstance().createMessage(null, is);
                System.out.println("msg format ok");

               SOAPHeader head = msg.getSOAPHeader();
               SOAPFactory fc= SOAPFactory.newInstance();



                Node d = (Node) head.extractAllHeaderElements().next();
                Node senderNode = d.getFirstChild().getFirstChild();
                Node recNode = d.getLastChild().getFirstChild();
                String target = recNode.getNodeValue();
                if(!target.equals(ConnectionManager.name))
                {
                    ConnectionManager.sendMessage(newMessage);
                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
    }
}
