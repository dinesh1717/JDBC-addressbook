package com.bridgelabz.addressbookjdbc;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;


public class AddressBookServiceTest {
    @Before
    public void init() {
        System.out.println("Welcome to AddressBook Management Service");
    }

    @Test
    public void givenAddressBookDB_WhenRetrivedShouldMatchPersonCount() {
        AddressBookService addressBookService = new AddressBookService();
        List<Person> addressBookData = addressBookService.readAddressBookData(AddressBookService.IOService.DB_IO);
        Assert.assertEquals(3, addressBookData.size());
    }

    @Test
    public void givenContactNumber_WhenUpdated_ShouldSyncWithDb() {
        AddressBookService addressBookService = new AddressBookService();
        addressBookService.updateContactNumber("dinesh","11111111");
        boolean result = addressBookService.checkAddressBookInSyncWithDB("archana");
        Assert.assertTrue(result);
    }
    @Test
    public void givenDateRangeWhenRetrieved_ShouldMatchEntryCount() {
        AddressBookService addressBookService = new AddressBookService();
        LocalDate startDate = LocalDate.of(2017, 01, 01);
        LocalDate endDate = LocalDate.now();
        List<Person> addressBookDataList =
                addressBookService.readAddressBookDateRange(AddressBookService
                        .IOService.DB_IO, startDate, endDate);
        Assert.assertEquals(2, addressBookDataList.size());
    }
}
