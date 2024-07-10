package poesy.gateway;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import cn.d619.poesy.gateway.GatewayApplication;

@SpringBootTest(classes = GatewayApplication.class)
public class GatewayTest {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    public void getInstancesTest() {
        List<ServiceInstance> instances = discoveryClient.getInstances("poesy-user");
        System.out.println("\n=====> Instances <=====");
        System.out.println("Found " + instances.size() + " instances");
        instances.forEach(instance -> {
            System.out.println("Host: " + instance.getHost() + ", Port: " + instance.getPort());
        });
        System.out.println("=====> Instances <=====\n");
    }
}
