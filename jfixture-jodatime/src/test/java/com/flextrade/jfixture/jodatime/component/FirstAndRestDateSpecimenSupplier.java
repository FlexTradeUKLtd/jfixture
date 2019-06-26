package com.flextrade.jfixture.jodatime.component;

import com.flextrade.jfixture.SpecimenSupplier;

import java.util.Date;

class FirstAndRestDateSpecimenSupplier implements SpecimenSupplier<Date> {
    private final Date firstDate;
    private final Date restDate;

    private boolean isFirstCall = true;

    public FirstAndRestDateSpecimenSupplier(Date firstDate, Date restDate) {

        this.firstDate = firstDate;
        this.restDate = restDate;
    }

    @Override
    public Date create() {
        if (isFirstCall) {
            isFirstCall = false;
            return firstDate;
        }

        return restDate;
    }
}
