package com.sqlCloudVendor.sqlRestdemo.controllers;


import com.sqlCloudVendor.sqlRestdemo.models.CloudVendor;
import com.sqlCloudVendor.sqlRestdemo.responsePackage.ResponseHandler;
import com.sqlCloudVendor.sqlRestdemo.services.CloudVendorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorController {
    CloudVendorService cloudVendorService;

    public CloudVendorController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

    //Read cloud vendor data by id
    @GetMapping("{vendorId}")
    //this is to annotate what the method is doing
    //class level uses @ApiModel(description=""), see CloundVendor
    //field level uses @ApiModelProperty(notes=""), found in CloudVendor
    @ApiOperation(value="cloud vendor id", notes="Provide cloud vendor details",  //DOCUMENTATION
                    response = ResponseEntity.class)
    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") String vendorId){
        return ResponseHandler.responseBuilder("Requested vendor details are given here.",
                HttpStatus.OK, cloudVendorService.getCloudVendor(vendorId)); //METHOD IS UPDATED TO CUSTOMIZE THE HTTP RESPONSE
    }

    //Read all cloud vendor data
    @GetMapping()
    public List<CloudVendor> getAllCloudVendorDetails(){
        return cloudVendorService.getAllCloudVendors();
    }

    @PostMapping()
    public String createVendorDetails(@RequestBody CloudVendor cloudVendor){
        cloudVendorService.createCloudVendor(cloudVendor);
        return "Cloud vendor created successfully!";
    }

    @PutMapping("{vendorId}")
    public String updateVendorDetails(@RequestBody CloudVendor cloudVendor){
        cloudVendorService.updateCloudVendor(cloudVendor);
        return "Cloud vendor updated successfully!";
    }

    @DeleteMapping("{vendorId}")
    public String deleteVendorDetails(@PathVariable("vendorId") String vendorId){
        cloudVendorService.deleteCloudVendor(vendorId);
        return "Cloud vendor deleted successfully!";
    }
}
