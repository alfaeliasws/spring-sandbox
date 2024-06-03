package be.springconfig.properties;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties("application")
public class ApplicationProperties {
    
    private Date expireDate;

    private Duration durationTimeout;

    private String name;

    private Integer version;

    private boolean productionMode;    

    private DatabaseProperties database;

    private List<Role> defaultRoles;

    private Map<String, Role> roles;

    @Getter
    @Setter
    public static class DatabaseProperties{

        private String username;

        private String password;
        
        private String database;

        private String url;

        private List<String> whitelistTables;
        
        private Map<String, Integer> maxTableSize;
        
    }
    
    @Getter
    @Setter
    public static class Role{
        
        private String id;

        private String name;
    }

}
