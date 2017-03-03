package com.tcup.ted.configuration;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@PropertySources({
        @PropertySource("classpath:application.properties")
})
@ComponentScan(basePackages = {"com.tcup.ted"})
public class TcupTedScanner {

    @Bean
    public GitHubClient getGitHubClient(){
        return new GitHubClient();
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
