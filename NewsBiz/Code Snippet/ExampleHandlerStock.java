package com.techfest.appsurd.newsbiz;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ExampleHandlerStock extends DefaultHandler {

	// ===========================================================
	// Fields
	// ===========================================================

	private boolean in_currentweather = false;
	private boolean in_stock = false;
	private boolean in_location = false;
	private boolean in_time = false;
	private boolean in_wind = false;
	private boolean in_visibility = false;
	private boolean in_skyconditions = false;
	private boolean in_temperature = false;
	private boolean in_dewpoint = false;
	private boolean in_relativehumidity = false;
	private boolean in_pressure = false;
	private boolean in_status = false;

	private boolean in_previousclose = false;
	private boolean in_percentagechange = false;
	private boolean in_annrange = false;
	private boolean in_earns = false;
	private boolean in_pe = false;
	private boolean in_name = false;

	private ParsedExampleDataSetStock myParsedExampleDataSet = new ParsedExampleDataSetStock();

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public ParsedExampleDataSetStock getParsedStkData() {
		return this.myParsedExampleDataSet;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	@Override
	public void startDocument() throws SAXException {
		this.myParsedExampleDataSet = new ParsedExampleDataSetStock();
	}

	@Override
	public void endDocument() throws SAXException {
		// Nothing to do
	}

	/**
	 * Gets be called on opening tags like: <tag> Can provide attribute(s), when
	 * xml was like: <tag attribute="attributeValue">
	 */
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		if (localName.equals("StockQuotes")) {
			this.in_currentweather = true;
		} else if (localName.equals("Stock")) {
			this.in_stock = true;

		} else if (localName.equals("Symbol")) {
			this.in_location = true;
		} else if (localName.equals("Last")) {
			this.in_time = true;
		} else if (localName.equals("Date")) {
			this.in_wind = true;
		} else if (localName.equals("Time")) {
			this.in_visibility = true;
		} else if (localName.equals("Change")) {
			this.in_skyconditions = true;
		} else if (localName.equals("Open")) {
			this.in_temperature = true;
		} else if (localName.equals("High")) {
			this.in_dewpoint = true;
		} else if (localName.equals("Low")) {
			this.in_relativehumidity = true;
		} else if (localName.equals("Volume")) {
			this.in_pressure = true;
		} else if (localName.equals("MktCap")) {
			this.in_status = true;

		} else if (localName.equals("PreviousClose")) {
			this.in_previousclose = true;
		} else if (localName.equals("PercentageChange")) {
			this.in_percentagechange = true;
		} else if (localName.equals("AnnRange")) {
			this.in_annrange = true;
		} else if (localName.equals("Earns")) {
			this.in_earns = true;
		} else if (localName.equals("P-E")) {
			this.in_pe = true;
		} else if (localName.equals("Name")) {
			this.in_name = true;
		}

	}

	/**
	 * Gets be called on closing tags like: </tag>
	 */
	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		if (localName.equals("StockQuotes")) {
			this.in_currentweather = false;

		} else if (localName.equals("Stock")) {
			this.in_stock = false;

		} else if (localName.equals("Symbol")) {
			this.in_location = false;
		} else if (localName.equals("Last")) {
			this.in_time = false;
		} else if (localName.equals("Date")) {
			this.in_wind = false;
		} else if (localName.equals("Time")) {
			this.in_visibility = false;
		} else if (localName.equals("Change")) {
			this.in_skyconditions = false;
		} else if (localName.equals("Open")) {
			this.in_temperature = false;
		} else if (localName.equals("High")) {
			this.in_dewpoint = false;
		} else if (localName.equals("Low")) {
			this.in_relativehumidity = false;
		} else if (localName.equals("Volume")) {
			this.in_pressure = false;
		} else if (localName.equals("MktCap")) {
			this.in_status = false;

		} else if (localName.equals("PreviousClose")) {
			this.in_previousclose = false;
		} else if (localName.equals("PercentageChange")) {
			this.in_percentagechange = false;
		} else if (localName.equals("AnnRange")) {
			this.in_annrange = false;
		} else if (localName.equals("Earns")) {
			this.in_earns = false;
		} else if (localName.equals("P-E")) {
			this.in_pe = false;
		} else if (localName.equals("Name")) {
			this.in_name = false;
		}

	}

	/**
	 * Gets be called on the following structure: <tag>characters</tag>
	 */
	@Override
	public void characters(char ch[], int start, int length) {
		if (this.in_location) {
			myParsedExampleDataSet.setLocationString(new String(ch, start,
					length));
		} else if (this.in_time) {
			myParsedExampleDataSet.setTimeString(new String(ch, start, length));
		} else if (this.in_wind) {
			myParsedExampleDataSet.setWindString(new String(ch, start, length));
		} else if (this.in_visibility) {
			myParsedExampleDataSet.setVisibilityString(new String(ch, start,
					length));
		} else if (this.in_skyconditions) {
			myParsedExampleDataSet.setSkyConditionString(new String(ch, start,
					length));
		} else if (this.in_temperature) {
			myParsedExampleDataSet.setTemperatureString(new String(ch, start,
					length));
		} else if (this.in_dewpoint) {
			myParsedExampleDataSet.setDewPointString(new String(ch, start,
					length));
		} else if (this.in_relativehumidity) {
			myParsedExampleDataSet.setRelativeHumidityString(new String(ch,
					start, length));
		} else if (this.in_pressure) {
			myParsedExampleDataSet.setPressureString(new String(ch, start,
					length));
		} else if (this.in_status) {
			myParsedExampleDataSet
					.setStatusString(new String(ch, start, length));
		}

		else if (this.in_previousclose) {
			myParsedExampleDataSet.setprevcloseString(new String(ch, start,
					length));
		} else if (this.in_percentagechange) {
			myParsedExampleDataSet.setpercentagechgString(new String(ch, start,
					length));
		} else if (this.in_annrange) {
			myParsedExampleDataSet.setannrangeString(new String(ch, start,
					length));
		} else if (this.in_earns) {
			myParsedExampleDataSet
					.setearnsString(new String(ch, start, length));
		} else if (this.in_pe) {
			myParsedExampleDataSet.setpeString(new String(ch, start, length));
		} else if (this.in_name) {
			myParsedExampleDataSet.setnameString(new String(ch, start, length));
		}

	}
}