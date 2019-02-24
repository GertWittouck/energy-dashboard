package com.gwi.energydashboard.data.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
@ConfigurationProperties("energydashboard.db")
public class DBProperties {

    @NotNull
    private String url;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private Boolean testWhileIdle;

    @NotNull
    private String validationQuery;

    @NotNull
    private Boolean showSql;

    @NotNull
    private String ddlAuto;

    @NotNull
    private String driverClassName;

    @NotNull
    private String auditLogSuffix;
}