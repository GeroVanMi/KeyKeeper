package xmlHandling;

import models.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class Reader {
    private Document document;

    public Reader(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(filePath));
        } catch (Exception e) {
            System.out.println("ERROR:");
            System.out.println(e.getMessage());
        }
    }

    public String getValueOfSingleChild(Element elem, String tagName) {
        if(elem.getElementsByTagName(tagName).item(0) == null) {
            return null;
        }
        return elem.getElementsByTagName(tagName).item(0).getTextContent();
    }

    public ArrayList<User> readAllUser() {
        NodeList userList = document.getElementsByTagName("user");
        ArrayList<User> userArrayList = new ArrayList<>();
        for(int i = 0; i < userList.getLength(); i++) {
            Element user = (Element)userList.item(i);
            userArrayList.add(new User(getValueOfSingleChild(user, "username"), getValueOfSingleChild(user, "password")));
        }
        return userArrayList;
    }
}
