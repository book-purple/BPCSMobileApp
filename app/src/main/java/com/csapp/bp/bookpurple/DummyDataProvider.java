package com.csapp.bp.bookpurple;

import com.csapp.bp.bookpurple.model.BusinessModel;
import com.csapp.bp.bookpurple.model.EventModel;
import com.csapp.bp.bookpurple.model.LandingOffersModel;
import com.csapp.bp.bookpurple.model.ServiceModel;

import java.util.ArrayList;
import java.util.List;

public class DummyDataProvider {

    private List<String> landingOfferBannerName;
    private List<Integer> landingOfferColor;

    private List<String> serviceName;
    private List<Integer> servicesImages;

    private List<String> eventName;
    private List<Integer> eventImages;

    private List<LandingOffersModel> landingOffersModels;
    private List<EventModel> eventModels;
    private List<ServiceModel> serviceModels;
    private List<BusinessModel> businessModels1;

    public List<LandingOffersModel> getLandingOffersModels() {
        return landingOffersModels;
    }

    public List<EventModel> getEventModels() {
        return eventModels;
    }

    public List<ServiceModel> getServiceModels() {
        return serviceModels;
    }

    public List<BusinessModel> getBusinessModels1 () {
        return businessModels1;
    }

    public List<String> getSericeName() {
        return serviceName;
    }

    public List<Integer> getImages() {
        return servicesImages;
    }

    public DummyDataProvider() {

        landingOfferBannerName = new ArrayList<>();
        landingOfferColor = new ArrayList<>();
        populateLandingBannerData();

        serviceName = new ArrayList<>();
        eventName = new ArrayList<>();
        populateServiceNames();

        servicesImages = new ArrayList<>();
        eventImages = new ArrayList<>();
        populateImages();

        landingOffersModels = new ArrayList<>();
        eventModels = new ArrayList<>();
        serviceModels = new ArrayList<>();

        businessModels1 = new ArrayList<>();

        populateLandingOffersBanner();
        populateEventModels();
        populateServiceModels();
        populateBusinessModels1();
    }

    private void populateLandingOffersBanner() {
        for (int i = 0; i < landingOfferBannerName.size(); i++) {
            LandingOffersModel landingOffersModel = new LandingOffersModel();
            landingOffersModel.text = landingOfferBannerName.get(i);
            landingOffersModel.color = landingOfferColor.get(i);
            landingOffersModels.add(landingOffersModel);
        }
    }

    private void populateBusinessModels1() {
        for (int i = 0; i < landingOfferBannerName.size(); i++) {
            BusinessModel businessModel = new BusinessModel();
            businessModel.text = landingOfferBannerName.get(i);
            businessModel.color = landingOfferColor.get(i);
            businessModels1.add(businessModel);
        }
    }

    private void populateServiceModels() {
        for (int i = 0; i < serviceName.size(); i++) {
            ServiceModel serviceModel = new ServiceModel();
            serviceModel.serviceImage = servicesImages.get(i);
            serviceModel.serviceName = serviceName.get(i);
            serviceModels.add(serviceModel);
        }
    }

    private void populateEventModels() {
        for (int i = 0; i < eventName.size(); i++) {
            EventModel eventModel = new EventModel();
            eventModel.eventImage = eventImages.get(i);
            eventModel.eventName = eventName.get(i);
            eventModels.add(eventModel);
        }
    }

    private void populateLandingBannerData () {
        landingOfferBannerName.add("Banner1");
        landingOfferBannerName.add("Banner2");
        landingOfferBannerName.add("Banner3");
        landingOfferBannerName.add("Banner4");

        landingOfferColor.add(R.color.green);
        landingOfferColor.add(R.color.blue);
        landingOfferColor.add(R.color.red);
        landingOfferColor.add(R.color.blue);
    }

    private void populateImages() {
        servicesImages.add(R.drawable.waiter);
        servicesImages.add(R.drawable.redcarpet);
        servicesImages.add(R.drawable.star);
        servicesImages.add(R.drawable.confetti);
        servicesImages.add(R.drawable.rings);
        servicesImages.add(R.drawable.champagneglass);
        servicesImages.add(R.drawable.barservice);
        servicesImages.add(R.drawable.application);
        servicesImages.add(R.drawable.waiter);
        servicesImages.add(R.drawable.confetti);
        servicesImages.add(R.drawable.rings);
        servicesImages.add(R.drawable.rings);

        eventImages.add(R.drawable.waiter);
        eventImages.add(R.drawable.redcarpet);
        eventImages.add(R.drawable.star);
        eventImages.add(R.drawable.confetti);
        eventImages.add(R.drawable.rings);
        eventImages.add(R.drawable.champagneglass);
        eventImages.add(R.drawable.barservice);
        eventImages.add(R.drawable.application);
    }

    private void populateServiceNames() {
        serviceName.add("Catering");
        serviceName.add("Venues");
        serviceName.add("Photography");
        serviceName.add("Music & DJ");
        serviceName.add("Decorations");
        serviceName.add("Transport");
        serviceName.add("Service 1");
        serviceName.add("Service 2");
        serviceName.add("Service 3");
        serviceName.add("Service 3");
        serviceName.add("Service 4");
        serviceName.add("Other Service");

        eventName.add("Wedding");
        eventName.add("Birthday\nParty");
        eventName.add("Baby Shower");
        eventName.add("Bachelortte");
        eventName.add("Corporate\nEvent");
        eventName.add("Engagement");
        eventName.add("Cocktail Party");
        eventName.add("Other Events");
    }
}
