package com.sqlCloudVendor.sqlRestdemo.repositories;

import com.sqlCloudVendor.sqlRestdemo.models.CloudVendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CloudVendorRepo extends JpaRepository<CloudVendor, String> {
    //like findById which by default comes with JpaRepository,
    // we create findByVendorName and JpaRepository will treat it as it was already
    //part of the system. You could create more methods such as
    //findByVendorAddress or phoneNumber, but not byId because it is already built in
    List<CloudVendor> findByVendorName(String vendorName);
}
