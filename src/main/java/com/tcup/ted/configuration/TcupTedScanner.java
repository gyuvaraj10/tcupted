package com.tcup.ted.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
@PropertySources({
        @PropertySource("classpath:application.properties")
})
@ComponentScan(basePackages = {"com.tcup.ted"})
@EnableMongoRepositories(basePackages = {"com.tcup.ted.db.repositories",
        "com.tcup.ted.services.companies.repositories"})
public class TcupTedScanner{

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

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter(mapper);
        return converter;
    }
}
