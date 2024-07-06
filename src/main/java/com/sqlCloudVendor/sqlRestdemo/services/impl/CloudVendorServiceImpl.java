package com.sqlCloudVendor.sqlRestdemo.services.impl;

import com.sqlCloudVendor.sqlRestdemo.exceptions.CloudVendorNotFoundExcp;
import com.sqlCloudVendor.sqlRestdemo.models.CloudVendor;
import com.sqlCloudVendor.sqlRestdemo.repositories.CloudVendorRepo;
import com.sqlCloudVendor.sqlRestdemo.services.CloudVendorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloudVendorServiceImpl implements CloudVendorService {

    CloudVendorRepo cloudVendorRepo;

    public CloudVendorServiceImpl(CloudVendorRepo cloudVendorRepo) {
        this.cloudVendorRepo = cloudVendorRepo;
    }

    //Business logics
    @Override
    public String createCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepo.save(cloudVendor);
        return "Cloud vendor successfully created!";
    }

    @Override
    public String updateCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepo.save(cloudVendor);
        return "Cloud vendor updated successfully!";
    }

    @Override
    public String deleteCloudVendor(String cloudVendorId) {
        cloudVendorRepo.deleteById(cloudVendorId);
        return "Cloud Vendor successfully deleted!";
    }

    @Override
    public CloudVendor getCloudVendor(String cloudVendorId) {
        if(cloudVendorRepo.findById(cloudVendorId).isEmpty())
            throw new CloudVendorNotFoundExcp("Requested Cloud Vendor does not exist");
        return cloudVendorRepo.findById(cloudVendorId).get();
    }

    @Override
    public List<CloudVendor> getAllCloudVendors() {
        return cloudVendorRepo.findAll();
    }

    @Override
    public List<CloudVendor> getByVendorName(String vendorName){
        return cloudVendorRepo.findByVendorName(vendorName);
    }
}
