package be.springvalidation.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@ConfigurationProperties("database")
public class DatabaseProperties {
    
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;



}
