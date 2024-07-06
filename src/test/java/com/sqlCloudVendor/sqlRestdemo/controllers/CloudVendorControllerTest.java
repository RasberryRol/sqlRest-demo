package com.sqlCloudVendor.sqlRestdemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sqlCloudVendor.sqlRestdemo.models.CloudVendor;
import com.sqlCloudVendor.sqlRestdemo.services.CloudVendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//annotation to check if the urls are working fine
@WebMvcTest(CloudVendorController.class)
class CloudVendorControllerTest {
    //mockMvc injection
    @Autowired
    private MockMvc mockMvc;

    //to mock the service layer because controller layer is talking to the service layer
    @MockBean
    private CloudVendorService cloudVendorService;

    CloudVendor cloudVendorOne;
    CloudVendor cloudVendorTwo;
    List<CloudVendor> cloudVendorList = new ArrayList<>();



    @BeforeEach
    void setUp() {
        cloudVendorOne = new CloudVendor("1", "Amazon",
                "Virginia", "XXX");

        cloudVendorTwo = new CloudVendor("2", "GCP",
                "New York", "ZZZZ");

        cloudVendorList.add(cloudVendorOne);
        cloudVendorList.add(cloudVendorTwo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetCloudVendorDetails() throws Exception {
        when(cloudVendorService.getCloudVendor("1")).thenReturn(cloudVendorOne);
        //using mockMvc, perform a get request on /cloudvendor/1.
        //if get() does its job by getting the value acquired from the url, the status should return Ok
        this.mockMvc.perform(get("/cloudvendor/1")).andDo(print()).andExpect(status().isOk());
        //perform() throws Exception
    }

    @Test
    void testGetAllCloudVendorDetails() throws Exception{
        //getAllCloudVendors returns a list so thenReturn takes a list
        when(cloudVendorService.getAllCloudVendors()).thenReturn(cloudVendorList);
        //using mockMvc, perform a get request on /cloudvendor.
        //if get() does its job by getting the record(s) acquired from the url, the status should return Ok
        this.mockMvc.perform(get("/cloudvendor")).andDo(print()).andExpect(status().isOk());
        //both lists added to cloudVendorList are expected
    }

    @Test
    void testCreateVendorDetails() throws Exception{
        //cloudVendorOne is an object, so we convert it to Json so that we can pass it
        //to the content body (requestJson, found below)
        //this is a fixed piece of code like a formula to convert an object to Json
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(cloudVendorOne);

        when(cloudVendorService.createCloudVendor(cloudVendorOne))
                .thenReturn("Cloud vendor created successfully!");
        //this is a post test, so the content type and content body which is Json
        this.mockMvc.perform(post("/cloudvendor")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateVendorDetails() throws Exception{
        //cloudVendorOne is an object, so we convert it to Json so that we can pass it
        //to the content body (requestJson, found below)
        //this is a fixed piece of code like a formula to convert an object to Json
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(cloudVendorOne);

        when(cloudVendorService.updateCloudVendor(cloudVendorOne))
                .thenReturn("Cloud vendor updated successfully!");
        //this is a put test, so the content type and content body which is Json
        this.mockMvc.perform(put("/cloudvendor/1")
                        .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testDeleteVendorDetails() throws Exception{
        when(cloudVendorService.deleteCloudVendor("1"))
                .thenReturn("Cloud vendor deleted successfully!");
        this.mockMvc.perform(delete("/cloudvendor/1")).andDo(print())
                .andExpect(status().isOk());
    }
}