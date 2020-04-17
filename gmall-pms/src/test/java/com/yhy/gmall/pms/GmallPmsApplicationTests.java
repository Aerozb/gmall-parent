package com.yhy.gmall.pms;

import com.yhy.gmall.pms.entity.Brand;
import com.yhy.gmall.pms.service.BrandService;
import com.yhy.gmall.pms.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallPmsApplicationTests {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Test
    public void contextLoads() {
//        Product product = productService.getById(1);
//        System.out.println(product);
        Brand brand = new Brand();
        brand.setName("一加");
        brandService.save(brand);
    }

}
