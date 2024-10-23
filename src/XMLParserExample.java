import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLParserExample {
    public static void main(String[] args) {
        try{
            DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new File("archivo.xml"));

            // normaliza: Elimina nodos vacíos
            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();
            System.out.println("Elemento raíz: " + root.getNodeName());

            NodeList nodeList = document.getElementsByTagName("persona");
            System.out.println(nodeList.getLength());

            List<Persona> personas = new ArrayList<Persona>();

            for (int i = 0 ; i < nodeList.getLength() ; i++ ){
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){


                    Element element = (Element) node; // representa al elemento persona
                    String nombre = element.getElementsByTagName("nombre") //obtner lista de subelementos
                                            .item(0) // obtiene el elemento nombre
                                            .getTextContent(); // saca el string
                    System.out.println(nombre);

                    int edad = Integer.parseInt(element.getElementsByTagName("edad") //obtner lista de subelementos
                            .item(0) // obtiene el elemento nombre
                            .getTextContent()); // saca el string

                    Persona persona = new Persona (nombre, edad);
                    personas.add(persona);
                }

            }

            System.out.println(personas.size());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
