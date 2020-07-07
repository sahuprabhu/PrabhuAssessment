package com.nordea.assessment.service;

import com.nordea.assessment.dao.DocumentErrorRepo;
import com.nordea.assessment.dao.EndToEndRepo;
import com.nordea.assessment.entity.EndToEnd;
import com.nordea.assessment.model.*;
import com.nordea.assessment.utility.XMLValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceTest {

    @Mock
    EndToEndRepo endToEndRepo;
    @Mock
    DocumentErrorRepo documentErrorRepo;
    @Mock
    XMLValidator xmlValidator;

    DocumentService serviceToTest;

    @Before
    public void setUp() {
        serviceToTest  = new DocumentService(endToEndRepo, documentErrorRepo, xmlValidator);
    }

    @Test
    public void testSaveEndToEndId() {

        //Given
        Document document = new Document();
        PaymentIdentification1 paymentIdentification1 = new PaymentIdentification1();
        paymentIdentification1.setEndToEndId("EndToEnd");
        CreditTransferTransaction26  creditTransferTransaction = new CreditTransferTransaction26();
        creditTransferTransaction.setPmtId(paymentIdentification1);
        PaymentInstruction22 paymentInstruction22 = new PaymentInstruction22();
        paymentInstruction22.getCdtTrfTxInf().add(creditTransferTransaction);
        CustomerCreditTransferInitiationV08 custromerCrTrIn = new CustomerCreditTransferInitiationV08();
        custromerCrTrIn.getPmtInf().add(paymentInstruction22);
        document.setCstmrCdtTrfInitn(custromerCrTrIn);

        //When
        serviceToTest.saveEndToEndId(document);

        //Then
        ArgumentCaptor<EndToEnd> argumentCaptor = ArgumentCaptor.forClass(EndToEnd.class);
        Mockito.verify(endToEndRepo).save(argumentCaptor.capture());
        Assert.assertEquals("EndToEnd", argumentCaptor.getValue().getEndToEndId());
    }

    @Test
    public void testFindEndToEndById() {

        //Given
        EndToEnd endToEnd = new EndToEnd();
        endToEnd.setEndToEndId("EndToEnd");
        endToEnd.setId(1);
        endToEnd.setModifiedTimeStamp(new Date());
        Mockito.when(endToEndRepo.findById(any())).thenReturn(Optional.of(endToEnd));

        //When
        Optional<EndToEnd> result = serviceToTest.findEndToEndById(1);

        //Then
        Assert.assertEquals(endToEnd.getEndToEndId(), result.get().getEndToEndId());
        Assert.assertEquals(endToEnd.getId(), result.get().getId());
        Assert.assertEquals(endToEnd.getModifiedTimeStamp(), result.get().getModifiedTimeStamp());
    }

    @Test
    public void testFindEndToEndById_WhenException() {

        //Given
        Mockito.when(endToEndRepo.findById(any())).thenThrow(new RuntimeException());

        //When
        Optional<EndToEnd> result = serviceToTest.findEndToEndById(1);

        //Then
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void testfindAllEndToEnd() {

        //Given
        EndToEnd endToEnd = new EndToEnd();
        endToEnd.setEndToEndId("EndToEnd");
        endToEnd.setId(1);
        endToEnd.setModifiedTimeStamp(new Date());
        List endToEndList=new ArrayList<>();
        endToEndList.add(endToEnd);
        Mockito.when(endToEndRepo.findAll()).thenReturn(endToEndList);

        //When
        List<EndToEnd> result = serviceToTest.findAllEndToEnd();

        //Then
        Assert.assertEquals(endToEndList, result);
    }

    @Test
    public void testfindAllEndToEnd_WhenException() {

        //Given
        Mockito.when(endToEndRepo.findAll()).thenThrow(new RuntimeException());

        //When
        List<EndToEnd> result = serviceToTest.findAllEndToEnd();

        //Then
        Assert.assertNull(result);
    }

    @Test
    public void testvalidateXml_Success() throws IOException, SAXException {

        //Given
        String xmlFileName = "xmlFileName";
        String xsdFileName = "xsdFileName";
        Mockito.when(xmlValidator.validate(xmlFileName, xsdFileName)).thenReturn(true);

        //When
        boolean result = serviceToTest.validateXml(xmlFileName, xsdFileName);

        //Then
        Assert.assertTrue(result);
    }

    @Test
    public void testvalidateXml_FailedValidation() throws IOException, SAXException {

        //Given
        String xmlFileName = "xmlFileName";
        String xsdFileName = "xsdFileName";
        Mockito.when(xmlValidator.validate(xmlFileName, xsdFileName)).thenThrow(new SAXException());

        //When
        boolean result = serviceToTest.validateXml(xmlFileName, xsdFileName);

        //Then
        Assert.assertFalse(result);
    }
}