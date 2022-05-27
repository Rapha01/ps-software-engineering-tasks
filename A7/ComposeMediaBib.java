import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

/**
 *The Class ComposeMediaBib offers an *.xlm parser where you can parse a *.xlm file. 
 *After reading the *.xlm file a Data Structure which follows 
 *the composite design pattern "Web-Shop" will be built
 *
 *@author Michael Pingert
 *@author Sebastian Hattinger
 *@version V1.0
 *@since 06.12.2015 
**/


public class ComposeMediaBib {
	public static Archive xmlToComposite(File File) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(File);
			doc.getDocumentElement().normalize();
			Element rootNode = doc.getDocumentElement();
			Archive testArchiv = new Archive("testArchiv");

			buildXml(rootNode, testArchiv);

			return testArchiv;
		} catch (Exception e) {
			System.err.println("parsing error");
		}
		return null;
	}
	
	
public static void buildXml(Element nodeEle, Archive parentArchive){
		
		if(nodeEle.getNodeName() == "list"){
			Archive temp = new Archive(nodeEle.getAttribute("name"));
			NodeList nodeList = nodeEle.getChildNodes();
			Element nodeListItem =null;
			
			parentArchive.add(temp);
			
			for(int i=0;i < nodeList.getLength();i++){
				if (nodeList.item(i) instanceof Element == true){
				nodeListItem = (Element)nodeList.item(i);
				buildXml(nodeListItem, temp);
				}
			}
		}
		
		if(nodeEle.getNodeName() == "book"){
			Book temp = new Book(nodeEle.getAttribute("name"),Double.parseDouble(nodeEle.getAttribute("price")),Integer.parseInt(nodeEle.getAttribute("isbn")));
			parentArchive.add(temp);
		}
		if(nodeEle.getNodeName() == "cd"){
			Cd temp = new Cd(nodeEle.getAttribute("name"),Double.parseDouble(nodeEle.getAttribute("price")));
			parentArchive.add(temp);
		}
		if(nodeEle.getNodeName() == "item"){
			Medium temp = new Medium(nodeEle.getAttribute("name"),Double.parseDouble(nodeEle.getAttribute("price")));
			parentArchive.add(temp);
		}
		
	}


}
