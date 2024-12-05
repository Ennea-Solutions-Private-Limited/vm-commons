package com.ennea.enneaservices.utils.aws;

import com.ennea.enneaservices.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.DecryptionFailureException;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.InternalServiceErrorException;
import software.amazon.awssdk.services.secretsmanager.model.InvalidParameterException;
import software.amazon.awssdk.services.secretsmanager.model.InvalidRequestException;
import software.amazon.awssdk.services.secretsmanager.model.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class AwsSecretManagerClient implements ApplicationListener<ContextRefreshedEvent> {

    private final SecretsManagerClient secretsManagerClient;
    final Logger logger = LoggerFactory.getLogger(AwsSecretManagerClient.class);
    @Value("${cloud.aws.secretName}")
    private List<String> secretName;

    @Autowired
    public AwsSecretManagerClient(SecretsManagerClient secretsManagerClient) {
        this.secretsManagerClient = secretsManagerClient;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // On application boot, fetch secrets and save as Environment variable
        List<String> secretJson = getSecrets();
        ConfigurableApplicationContext applicationContext =
            (ConfigurableApplicationContext) event.getApplicationContext();
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        Properties props = new Properties();
        int secretIndex = 0;
        for(String name : secretName){
            String[] splitSecretName = name.split(Constants.SECRET_NAME_SEPARATOR);
            if(splitSecretName.length == 0){
                logger.error("Provide secret name with secret name separator");
            }
            props.put(splitSecretName[0], secretJson.get(secretIndex));
            secretIndex++;
        }
        environment.getPropertySources().addFirst
            (new PropertiesPropertySource(Constants.AWS_SECRET_MANAGER, props));

    }

    private List<String> getSecrets() {
        // Fetch secrets from application.yml to load into configurable environment
        List<String> listSecretValue = new ArrayList<>();
        for(String name : secretName){
            String secret = null;
            String[] splitSecretName = name.split(Constants.SECRET_NAME_SEPARATOR);
            if(splitSecretName.length != 2){
                logger.error("Provide secret name with secret name separator");
            }
            GetSecretValueRequest getSecretValueRequest =
                GetSecretValueRequest.builder().secretId(splitSecretName[1]).build();
            GetSecretValueResponse getSecretValueResult;
            try {
                getSecretValueResult = secretsManagerClient.getSecretValue(getSecretValueRequest);
                if(getSecretValueResult != null && getSecretValueResult.
                                                       secretString() != null){
                    secret = getSecretValueResult.secretString();
                }
            } catch (DecryptionFailureException | InternalServiceErrorException
                     | InvalidParameterException
                     | InvalidRequestException | ResourceNotFoundException e) {
                logger.error("Issue while fetching secret value using listener : ", e);
            }
            listSecretValue.add(secret);
        }
        return listSecretValue;
    }

    public String getSecret(String secretName) {
        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder().secretId(secretName).build();
        GetSecretValueResponse getSecretValueResponse = null;
        try {
            getSecretValueResponse = secretsManagerClient.getSecretValue(getSecretValueRequest);
        } catch (InvalidParameterException ipx) {
            logger.error("The request had invalid params: ", ipx);
        } catch (Exception e) {
            logger.error("Exception occurred while getting secret value : ", e);
        }
        String secret = null;
        if(getSecretValueResponse != null){
            secret = getSecretValueResponse.secretString();
        }
        return secret;
    }
}