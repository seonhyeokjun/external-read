package hello.config;

import hello.datasource.MyDataSource;
import hello.datasource.MyDataSourcePropertiesV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static hello.datasource.MyDataSourcePropertiesV3.Etc;

@Slf4j
@Configuration
@EnableConfigurationProperties(MyDataSourcePropertiesV3.class)
public class MyDataSourceConfigV3 {
    private final MyDataSourcePropertiesV3 properties;

    public MyDataSourceConfigV3(MyDataSourcePropertiesV3 properties) {
        this.properties = properties;
    }

    @Bean
    public MyDataSource dataSource() {
        Etc etc = properties.getEtc();
        return new MyDataSource(properties.getUrl(), properties.getUsername(), properties.getPassword(), etc.getMaxConnection(), etc.getTimeout(), etc.getOptions());
    }
}
