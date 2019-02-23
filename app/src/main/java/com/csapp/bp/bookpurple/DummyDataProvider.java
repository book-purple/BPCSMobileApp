package com.csapp.bp.bookpurple;

import com.csapp.bp.bookpurple.model.EventModel;
import com.csapp.bp.bookpurple.model.ServiceModel;

import java.util.ArrayList;
import java.util.List;

public class DummyDataProvider {

    private List<String> serviceName;
    private List<Integer> servicesImages;

    private List<String> eventName;
    private List<Integer> eventImages;

    private List<EventModel> eventModels;
    private List<ServiceModel> serviceModels;

    public List<EventModel> getEventModels() {
        return eventModels;
    }

    public List<ServiceModel> getServiceModels() {
        return serviceModels;
    }

    public List<String> getSericeName() {
        return serviceName;
    }

    public List<Integer> getImages() {
        return servicesImages;
    }

    public DummyDataProvider() {
        serviceName = new ArrayList<>();
        eventName = new ArrayList<>();
        populateServiceNames();

        servicesImages = new ArrayList<>();
        eventImages = new ArrayList<>();
        populateImages();

        eventModels = new ArrayList<>();
        serviceModels = new ArrayList<>();

        populateEventModels();
        populateServiceModels();
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

        eventName.add("Wedding &\nPre-Wedding");
        eventName.add("Birthday\nParty");
        eventName.add("Baby Shower");
        eventName.add("Bachelortte");
        eventName.add("Corporate\nEvent");
        eventName.add("Engagement");
        eventName.add("Cocktail Party");
        eventName.add("Other Events");
    }
}
