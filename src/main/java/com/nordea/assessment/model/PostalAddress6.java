//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.04 at 11:29:01 AM IST 
//

package com.nordea.assessment.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for PostalAddress6 complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="PostalAddress6"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AdrTp" type="{urn:iso:std:iso:20022:tech:xsd:pain.001.001.08}AddressType2Code" minOccurs="0"/&gt;
 *         &lt;element name="Dept" type="{urn:iso:std:iso:20022:tech:xsd:pain.001.001.08}Max70Text" minOccurs="0"/&gt;
 *         &lt;element name="SubDept" type="{urn:iso:std:iso:20022:tech:xsd:pain.001.001.08}Max70Text" minOccurs="0"/&gt;
 *         &lt;element name="StrtNm" type="{urn:iso:std:iso:20022:tech:xsd:pain.001.001.08}Max70Text" minOccurs="0"/&gt;
 *         &lt;element name="BldgNb" type="{urn:iso:std:iso:20022:tech:xsd:pain.001.001.08}Max16Text" minOccurs="0"/&gt;
 *         &lt;element name="PstCd" type="{urn:iso:std:iso:20022:tech:xsd:pain.001.001.08}Max16Text" minOccurs="0"/&gt;
 *         &lt;element name="TwnNm" type="{urn:iso:std:iso:20022:tech:xsd:pain.001.001.08}Max35Text" minOccurs="0"/&gt;
 *         &lt;element name="CtrySubDvsn" type="{urn:iso:std:iso:20022:tech:xsd:pain.001.001.08}Max35Text" minOccurs="0"/&gt;
 *         &lt;element name="Ctry" type="{urn:iso:std:iso:20022:tech:xsd:pain.001.001.08}CountryCode" minOccurs="0"/&gt;
 *         &lt;element name="AdrLine" type="{urn:iso:std:iso:20022:tech:xsd:pain.001.001.08}Max70Text" maxOccurs="7" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostalAddress6", propOrder = { "AdrTp", "Dept", "SubDept", "StrtNm", "BldgNb", "PstCd", "TwnNm",
		"CtrySubDvsn", "Ctry", "AdrLine" })
public class PostalAddress6 {

	@XmlElement(name = "AdrTp")
	@XmlSchemaType(name = "string")
	protected AddressType2Code AdrTp;
	@XmlElement(name = "Dept")
	protected String Dept;
	@XmlElement(name = "SubDept")
	protected String SubDept;
	@XmlElement(name = "StrtNm")
	protected String StrtNm;
	@XmlElement(name = "BldgNb")
	protected String BldgNb;
	@XmlElement(name = "PstCd")
	protected String PstCd;
	@XmlElement(name = "TwnNm")
	protected String TwnNm;
	@XmlElement(name = "CtrySubDvsn")
	protected String CtrySubDvsn;
	@XmlElement(name = "Ctry")
	protected String Ctry;
	@XmlElement(name = "AdrLine")
	protected List<String> AdrLine;

	/**
	 * Gets the value of the adrTp property.
	 * 
	 * @return possible object is {@link AddressType2Code }
	 * 
	 */
	public AddressType2Code getAdrTp() {
		return AdrTp;
	}

	/**
	 * Sets the value of the adrTp property.
	 * 
	 * @param value allowed object is {@link AddressType2Code }
	 * 
	 */
	public void setAdrTp(AddressType2Code value) {
		this.AdrTp = value;
	}

	/**
	 * Gets the value of the dept property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDept() {
		return Dept;
	}

	/**
	 * Sets the value of the dept property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setDept(String value) {
		this.Dept = value;
	}

	/**
	 * Gets the value of the subDept property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSubDept() {
		return SubDept;
	}

	/**
	 * Sets the value of the subDept property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setSubDept(String value) {
		this.SubDept = value;
	}

	/**
	 * Gets the value of the strtNm property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStrtNm() {
		return StrtNm;
	}

	/**
	 * Sets the value of the strtNm property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setStrtNm(String value) {
		this.StrtNm = value;
	}

	/**
	 * Gets the value of the bldgNb property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBldgNb() {
		return BldgNb;
	}

	/**
	 * Sets the value of the bldgNb property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setBldgNb(String value) {
		this.BldgNb = value;
	}

	/**
	 * Gets the value of the pstCd property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPstCd() {
		return PstCd;
	}

	/**
	 * Sets the value of the pstCd property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setPstCd(String value) {
		this.PstCd = value;
	}

	/**
	 * Gets the value of the twnNm property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTwnNm() {
		return TwnNm;
	}

	/**
	 * Sets the value of the twnNm property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setTwnNm(String value) {
		this.TwnNm = value;
	}

	/**
	 * Gets the value of the ctrySubDvsn property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCtrySubDvsn() {
		return CtrySubDvsn;
	}

	/**
	 * Sets the value of the ctrySubDvsn property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setCtrySubDvsn(String value) {
		this.CtrySubDvsn = value;
	}

	/**
	 * Gets the value of the ctry property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCtry() {
		return Ctry;
	}

	/**
	 * Sets the value of the ctry property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setCtry(String value) {
		this.Ctry = value;
	}

	/**
	 * Gets the value of the adrLine property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the adrLine property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getAdrLine().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * 
	 * 
	 */
	public List<String> getAdrLine() {
		if (AdrLine == null) {
			AdrLine = new ArrayList<String>();
		}
		return this.AdrLine;
	}

}
