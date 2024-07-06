package com.sqlCloudVendor.sqlRestdemo.repositories;

import com.sqlCloudVendor.sqlRestdemo.models.CloudVendor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class CloudVendorRepoTest {

    @Autowired
    private CloudVendorRepo cloudVendorRepo;
    CloudVendor cloudVendor;

    @BeforeEach
    void setUp(){
        cloudVendor = new CloudVendor("1", "Amazon",
                "Virginia", "XXX");
        cloudVendorRepo.save(cloudVendor);
    }

    @AfterEach
    void tearDown(){
        cloudVendor = null;
        cloudVendorRepo.deleteAll();
    }

    @Test
    void testFindByVendorName_Found()
    {
        List<CloudVendor> cloudVendorList = cloudVendorRepo.findByVendorName("Amazon");
        assert(cloudVendorList.get(0).getVendorId()).equals(cloudVendor.getVendorId());
        assert(cloudVendorList.get(0).getVendorAddress()).equals(cloudVendor.getVendorAddress());
    }

    @Test
    void testFindByVendorName_NotFound()
    {
        List<CloudVendor> cloudVendorList = cloudVendorRepo.findByVendorName("GCP");
        assertThat(cloudVendorList.isEmpty()).isTrue();
    }
}




