package xmlHandling;


import models.Entry;
import models.User;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.jdom2.output.XMLOutputter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Writer {

    private Document document;
    private Element root;

    public Writer(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document w3cDocument = builder.parse(new File("../resources/xml/passwords.xml"));
            document = new DOMBuilder().build(w3cDocument);
            root = document.getRootElement();
        } catch (Exception e) {
            System.out.println("ERROR:");
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(User user) {
        Element userElem = new Element("user");

        Element userName = new Element("username");
        Element userPassword = new Element("password");

        userElem.addContent(userName.addContent(user.getName()));
        userElem.addContent(userPassword.addContent(user.getPassword()));
        for(Entry entry : user.getEntries()) {
            Element xmlEntry = new Element("entry");

            Element entryWebsite = new Element("website");
            Element entryPassword = new Element("password");
            Element entryUsername = new Element("username");

            xmlEntry.addContent(entryWebsite.addContent(entry.getWebsite()));
            xmlEntry.addContent(entryPassword.addContent(entry.getPassword()));
            xmlEntry.addContent(entryUsername.addContent(entry.getUsername()));

            userElem.addContent(xmlEntry);
        }
        root.addContent(userElem);
    }

    public void saveMultipleUser(List<User> userList) {
        for(User user : userList) {
            saveUser(user);
        }
}

    public void saveChanges() {
        try {
        XMLOutputter outputter = new XMLOutputter();
        FileOutputStream outputStream = new FileOutputStream("../resources/xml/passwords.xml");
        outputter.output(document, outputStream);
        outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
