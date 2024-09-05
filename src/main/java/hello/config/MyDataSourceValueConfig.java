package hello.config;

import hello.datasource.MyDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.time.Duration;

@Slf4j
@Configuration
public class MyDataSourceValueConfig {
    @Value("${my.datasource.url}")
    private String url;

    @Value("${my.datasource.username}")
    private String username;

    @Value("${my.datasource.password}")
    private String password;

    @Value("${my.datasource.max-connection}")
    private int maxConnection;

    @Value("${my.datasource.timeout}")
    private Duration timeout;

    @Value("${my.datasource.options}")
    private List<String> options;

    @Bean
    public MyDataSource myDataSource1() {
        return new MyDataSource(url, username, password, maxConnection, timeout, options);
    }

    @Bean
    public MyDataSource myDataSource2(
            @Value("${my.datasource.url}") String url,
            @Value("${my.datasource.username}") String username,
            @Value("${my.datasource.password}") String password,
            @Value("${my.datasource.max-connection}") int maxConnection,
            @Value("${my.datasource.timeout}") Duration timeout,
            @Value("${my.datasource.options}") List<String> options
    ) {
        return new MyDataSource(url, username, password, maxConnection, timeout, options);
    }
}
