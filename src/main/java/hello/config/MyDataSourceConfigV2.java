package hello.config;

import hello.datasource.MyDataSource;
import hello.datasource.MyDataSourcePropertiesV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static hello.datasource.MyDataSourcePropertiesV2.*;

@Slf4j
@Configuration
@EnableConfigurationProperties(MyDataSourcePropertiesV2.class)
public class MyDataSourceConfigV2 {
    private final MyDataSourcePropertiesV2 properties;

    public MyDataSourceConfigV2(MyDataSourcePropertiesV2 properties) {
        this.properties = properties;
    }

    @Bean
    public MyDataSource dataSource() {
        Etc etc = properties.getEtc();
        return new MyDataSource(properties.getUrl(), properties.getUsername(), properties.getPassword(), etc.getMaxConnection(), etc.getTimeout(), etc.getOptions());
    }
}
