package com.sqlCloudVendor.sqlRestdemo.services.impl;

import com.sqlCloudVendor.sqlRestdemo.models.CloudVendor;
import com.sqlCloudVendor.sqlRestdemo.repositories.CloudVendorRepo;
import com.sqlCloudVendor.sqlRestdemo.services.CloudVendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CloudVendorServiceImplTest {
    //Mocking is used to prevent service layer from talking
    //directly to the database
    //We don't use mocking for repo classes because repo classes
    //are supposed to talk to the database
    @Mock
    private CloudVendorRepo cloudVendorRepo;

    private CloudVendorService cloudVendorService;
    //to close all unwanted resources when the entire class cases'
    //executions get finished
    AutoCloseable autoCloseable;
    CloudVendor cloudVendor;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        cloudVendorService = new CloudVendorServiceImpl(cloudVendorRepo);
        cloudVendor = new CloudVendor("1", "Amazon",
                "USA", "xxxx");
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void testCreateCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepo.class);

        when(cloudVendorRepo.save(cloudVendor)).thenReturn(cloudVendor);
        assert(cloudVendorService.createCloudVendor(cloudVendor)).equals("Cloud vendor successfully created!");
    }

    @Test
    void testUpdateCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepo.class);

        when(cloudVendorRepo.save(cloudVendor)).thenReturn(cloudVendor);
        assert(cloudVendorService.updateCloudVendor(cloudVendor)).equals("Cloud vendor updated successfully!");
    }

    @Test
    void testDeleteCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepo.class, Mockito.CALLS_REAL_METHODS);

        //the deleteById library method returns void (does not return anything to work on)
        //so thenReturn cannot be used
        doAnswer(Answers.CALLS_REAL_METHODS).when(cloudVendorRepo)
                .deleteById(any());
        assert(cloudVendorService.deleteCloudVendor("1"))
                .equals("Cloud Vendor successfully deleted!");
    }

    @Test
    void testGetCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepo.class);

        //when findById is called, then "this" cloudVendor value should be returned
        //Optional.ofNullable -> cloudVendor entity may or may not exist
        when(cloudVendorRepo.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));
        //make sure that the returned value is equal to cloudVendor for the id specified
        assert(cloudVendorService.getCloudVendor("1").getVendorName())
                .equals(cloudVendor.getVendorName());
    }

    @Test
    void testGetAllCloudVendors() {
        mock(CloudVendor.class);
        mock(CloudVendorRepo.class);

        when(cloudVendorRepo.findAll()).thenReturn(
                new ArrayList<CloudVendor>(Collections.singleton(cloudVendor)));
        //get(0) is used because one or more records is being returned
        assert(cloudVendorService.getAllCloudVendors().get(0).getVendorAddress())
                .equals(cloudVendor.getVendorAddress());
    }

    @Test
    void testGetByVendorName() {
        mock(CloudVendor.class);
        mock(CloudVendorRepo.class);

        //here, there can be multiple records with the same name, an arrayList is used
        when(cloudVendorRepo.findByVendorName("Amazon")).thenReturn(
                new ArrayList<CloudVendor>(Collections.singleton(cloudVendor)));

        //here, we pick the record by vendorName but compare the Ids from that record
        //get(0) is used because one or more records is being returned
        assert(cloudVendorService.getByVendorName("Amazon").get(0).getVendorId())
                .equals(cloudVendor.getVendorId());
    }
}