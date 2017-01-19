package com.example.laura.petadoption.utils;

/**
 * Created by Laura on 1/19/2017.
 */

public interface Subject {
    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObservers();

    public void notifyObservers(String designName, String typeOfChange);
}
