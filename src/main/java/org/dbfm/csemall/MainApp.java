package org.dbfm.csemall;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("org/dbfm/conf/beans.xml");
		OfferDAO offerDAO = (OfferDAO)context.getBean("offerDAO");
		
		System.out.println("The record count is" + offerDAO.getRowCount());
		
		List<Offer> offerList = offerDAO.getOffers();
		
		for(Offer offer: offerList) {
			System.out.println(offer);
		}
		Offer offer = new Offer();
		offer.setName("minjoo");
		offer.setEmail("dbfm@naver.com");
		offer.setText("an instructor of spring framework");
		offerDAO.insert(offer);
		
		if(offerDAO.insert(offer)) {
			System.out.println("Object is inserted successfully!");
		}
		else
			System.out.println("Object insert failed.");
		
		offer = offerDAO.getOffer("dbfm");
		offer.setName("dbfm");
		if(offerDAO.update(offer)) {
			System.out.println("Object is updated successfully!");
		}
		else
			System.out.println("Object update failed.");
		
		offer = offerDAO.getOffer("dbfmdbfm");
		System.out.println(offer);
		
		if(offerDAO.delete(offer.getId())) {
			System.out.println("Object is deleted successfully!");
		}
		else
			System.out.println("Object delete failed.");
		
		context.close();
	}
}
