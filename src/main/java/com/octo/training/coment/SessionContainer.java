package com.octo.training.cmoment;

import com.octo.training.object.Internaute;

public class SessionContainer {

    private Internaute internaute;

    public SessionContainer() {
        this.internaute = new Internaute();
    }

    public Internaute getInternaute() {
        return internaute;
    }

    public void setMessageErreurKey(String messageErreurKey) {
    }
    
}
