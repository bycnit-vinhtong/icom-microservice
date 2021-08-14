package com.icommerce.catalog;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

@Configuration
class RibbonConfig {

   int port = 8080;

   @Bean
   public ServerList<Server> serverList() {
      return new StaticServerList<>(new Server("127.0.0.1", port), new Server("127.0.0.1", port));
   }
}*/
