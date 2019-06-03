package com.gmail.erofeev.st.alexei.onlinemarket.service.impl;

import com.gmail.erofeev.st.alexei.onlinemarket.service.ItemXMLService;
import com.gmail.erofeev.st.alexei.onlinemarket.service.exception.ServiceException;
import com.gmail.erofeev.st.alexei.onlinemarket.service.model.xml.ItemListXMLDTO;
import com.gmail.erofeev.st.alexei.onlinemarket.service.model.xml.ItemXMLDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ItemXMLServiceImpl implements ItemXMLService {
    private static final Logger logger = LoggerFactory.getLogger(ItemXMLServiceImpl.class);

    @Override
    public List<ItemXMLDTO> importFromXMLFile(InputStream inputFileStream, File xsd) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ItemListXMLDTO.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ItemListXMLDTO itemsXML = (ItemListXMLDTO) jaxbUnmarshaller.unmarshal(inputFileStream);
            return itemsXML.getItems();
        } catch (JAXBException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceException("Can't import from xml: " + e.getMessage());
        }
    }

    @Override
    public void isValidByXsdScheme(InputStream xml, File xsd) {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsd);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
            xml.close();
        } catch (SAXException | IOException e) {
            logger.error(e.getMessage(), e);
            throw new ServiceException("Can't parse xml file: " + e.getMessage());
        }
    }
}
