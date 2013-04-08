package com.sgcib.test;

import org.junit.Assert;
import org.junit.Test;

public class AddressTest {

    @Test public void should_return_same_address() {
        String generalLeclerc = "6 avenue General Leclerc 75003";
        Assert.assertEquals("6 avenue General Leclerc 75003", cleanAddress(generalLeclerc));
    }

    @Test public void should_clean_address_with_whitespace_before() {
        String generalLeclerc = " 7 avenue General Leclerc 75003";
        Assert.assertEquals("7 avenue General Leclerc 75003", cleanAddress(generalLeclerc));
    }

    @Test public void should_clean_address_with_two_whitespace_inside() {
        String generalLeclerc = "8 avenue General  Leclerc 75003";
        Assert.assertEquals("8 avenue General Leclerc 75003", cleanAddress(generalLeclerc));
    }

    @Test public void should_clean_address_with_three_whitespace_inside() {
        String generalLeclerc = "8 avenue General   Leclerc  75003";
        Assert.assertEquals("8 avenue General Leclerc 75003", cleanAddress(generalLeclerc));
    }

    private String cleanAddress(String address) {
        String cleanedAddress = address.trim();
        while (cleanedAddress.contains("  ")) {
            cleanedAddress = cleanedAddress.replace("  ", " ");
        }
        return cleanedAddress;
    }



}
