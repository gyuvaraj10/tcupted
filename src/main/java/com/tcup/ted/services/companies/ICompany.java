package com.tcup.ted.services.companies;

import com.tcup.ted.services.companies.model.TcupCompany;

/**
 * Created by Yuvaraj on 13/05/2017.
 */
public interface ICompany {

    TcupCompany createCompany(TcupCompany company);

    TcupCompany getCompany(String companyName);
}
