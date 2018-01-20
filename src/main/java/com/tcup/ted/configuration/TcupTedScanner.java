package com.tcup.ted.configuration;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.inject.Inject;

@Configuration
@PropertySources({
        @PropertySource("classpath:application.properties")
})
@ComponentScan(basePackages = {"com.tcup.ted"})
@EnableMongoRepositories(basePackages = {"com.tcup.ted.db.repositories",
        "com.tcup.ted.services.companies.repositories"})
public class TcupTedScanner{

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Bean
    public GitHubClient getGitHubClient(@Value("${github.access.token}") String token){
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token(token);
        client.setHeaderAccept("application/vnd.github.barred-rock-preview");
        return client;
    }

    @Bean
    public RepositoryService getGitRepositoryService(@Autowired GitHubClient client) {
        return new RepositoryService(client);
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
