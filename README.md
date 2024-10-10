
# Section 8. 외부 설정과 프로필 2

---

## 1. 프로젝트 설정
- Spring Boot 프로젝트에서 외부 설정을 사용하기 위해서는 기본적으로 `application.properties` 또는 `application.yml` 파일을 사용합니다.
- 환경에 따라 다양한 설정 파일을 활용해 설정값을 관리할 수 있습니다.

---

## 2. 외부 설정 사용 - Environment
- **`Environment`** 인터페이스는 애플리케이션의 환경 변수 및 외부 설정 값을 쉽게 가져올 수 있도록 해줍니다.
- **사용 예시**:
```java
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    @Autowired
    private Environment env;

    public void printProperty() {
        String value = env.getProperty("my.property");
        System.out.println("Property Value: " + value);
    }
}
```

---

## 3. 외부설정 사용 - @Value
- **`@Value`** 어노테이션을 사용하여 설정 파일에 정의된 값을 쉽게 주입받을 수 있습니다.
- **사용 예시**:
```java
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyComponent {

    @Value("${my.property}")
    private String myProperty;

    public void printProperty() {
        System.out.println("Property Value: " + myProperty);
    }
}
```

---

## 4. 외부설정 사용 - @ConfigurationProperties 시작
- **`@ConfigurationProperties`**는 외부 설정을 매핑하여 객체 형태로 주입받을 수 있게 해주는 어노테이션입니다.
- **사용 예시**:
```java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "my")
public class MyProperties {

    private String property;

    // getter and setter
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
```

---

## 5. 외부설정 사용 - @ConfigurationProperties 생성자
- **생성자 기반**으로 외부 설정을 주입받을 수 있으며, Spring Boot 2.2 이상에서 지원됩니다.
- **사용 예시**:
```java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "my")
public class MyProperties {

    private final String property;

    public MyProperties(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }
}
```

---

## 6. 외부설정 사용 - @ConfigurationProperties 검증
- **`@Validated`** 어노테이션을 사용하여 외부 설정 값을 검증할 수 있습니다.
- **사용 예시**:
```java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "my")
@Validated
public class MyProperties {

    @NotNull
    private String property;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
```

---

## 7. YAML
- **YAML** 파일을 사용하여 설정을 작성할 수 있습니다. YAML은 `application.yml` 파일에 계층적 구조로 설정을 정의하는 방식입니다.
- **예시**:
```yaml
my:
  property: "value"
```

---

## 8. @Profile
- **`@Profile`** 어노테이션을 사용하여 특정 환경(프로필)에 따라 다른 빈을 로드하거나 설정할 수 있습니다.
- **사용 예시**:
```java
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevComponent {
    // 개발 환경에서만 로드
}
```
- `application.properties` 또는 `application.yml` 파일에서 활성화된 프로필을 설정할 수 있습니다:
```properties
spring.profiles.active=dev
```

---

**외부 설정과 프로필을 적절히 사용하면 다양한 환경에서의 설정을 쉽게 관리할 수 있으며, @Profile을 통해 개발, 테스트, 운영 환경에 따라 구성을 다르게 할 수 있습니다.**
