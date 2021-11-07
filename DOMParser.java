import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/** 
 * Class for parsing a Document XML from a URL or from a local path with the DOM method
 * 
 * The constructor creates :
 * - DocumentBuilder
 * - InputSource
 * 
 * @author Constantin Teodorescu
 * @version 01/11/2021 
 * @see <a href="https://devconstantin.github.io/"> Constantin personal portfolio </a>
 */
public class DOMParser {
	
	//Class fields
	private InputSource is;
	private DocumentBuilder builder;
	
	/**
	 * Constructor for parsing an XML file either from the Internet or from a local path
	 * @param strURL The variable strURL defines the URL of the file either coming from Internet or a local path
	 */

	public DOMParser(String strURL) {

		/* 
		 * When calling the constructor we have to give it the location of the XML, either is from Internet or from a local path
		 * If we want to get an XML from our computer we have to change the "new URL(strURL).openStream()" for "new FileInputStream(strURL)"
		 */

		// We open the page and create the InputSource (The source from which we get the XML, the "is")		
		try {	
			this.is=new InputSource(new URL(strURL).openStream());
		} catch (IOException e1) { 
			try {
				this.is=new InputSource(new FileInputStream(strURL));
			} catch (FileNotFoundException e) { System.err.println("The URL or local path is incorrect"); }
		}
		
		// We create the attribute builder and declare it as a Document Builder
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) { e.printStackTrace(); }
	}
	/**
	 * Method for getting a Document with the XML
	 * @return The document with the XML
	 */
	public Document getDocumento() {
		
		// Now we will start parsing and creating the document
		Document doc = null;

			try {
				doc = builder.parse(is);
			} catch (SAXException | IOException e) { e.printStackTrace(); }
		
		// Thanks to this document now we can get the NodesList if used in another class
		return doc;
	}
}