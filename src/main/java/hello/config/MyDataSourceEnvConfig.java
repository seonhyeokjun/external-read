package hello.config;

import hello.datasource.MyDataSource;
import hello.datasource.MyDataSourcePropertiesV1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Slf4j
@EnableConfigurationProperties(MyDataSourcePropertiesV1.class)
public class MyDataSourceEnvConfig {
    private final MyDataSourcePropertiesV1 properties;

    public MyDataSourceEnvConfig(MyDataSourcePropertiesV1 properties) {
        this.properties = properties;
    }

    @Bean
    public MyDataSource dataSource() {
        MyDataSourcePropertiesV1.Etc etc = properties.getEtc();
        return new MyDataSource(properties.getUrl(), properties.getUsername(), properties.getPassword(), etc.getMaxConnection(), etc.getTimeout(), etc.getOptions());
    }
}
