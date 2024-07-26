package com.example.demo.models.dto;

import com.example.demo.models.Restaurant;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Restaurant}
 */
public class RestaurantDto implements Serializable {
    private final String name;
    private final String addressStreet;
    private final String addressCity;
    private final String addressState;
    private final String addressZip;

    public RestaurantDto(String name, String addressStreet, String addressCity, String addressState, String addressZip) {
        this.name = name;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressZip = addressZip;
    }

    public String getName() {
        return name;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public String getAddressZip() {
        return addressZip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantDto entity = (RestaurantDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.addressStreet, entity.addressStreet) &&
                Objects.equals(this.addressCity, entity.addressCity) &&
                Objects.equals(this.addressState, entity.addressState) &&
                Objects.equals(this.addressZip, entity.addressZip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, addressStreet, addressCity, addressState, addressZip);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "addressStreet = " + addressStreet + ", " +
                "addressCity = " + addressCity + ", " +
                "addressState = " + addressState + ", " +
                "addressZip = " + addressZip + ")";
    }
}