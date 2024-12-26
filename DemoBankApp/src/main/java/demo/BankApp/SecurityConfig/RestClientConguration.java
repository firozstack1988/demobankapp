package com.example.PayrollApp.RestClientConfig;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConguration {

	@Value("${server.ssl.trust-store}")
	private Resource trustStore;

	@Value("${server.ssl.trust-store-password}")
	private String trustStorePassword;
	
	 @Bean
	 public RestTemplate restTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException, MalformedURLException, IOException {
	
		 SSLContext sslContext = SSLContextBuilder.create()
					.loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
					.build();

		Registry<ConnectionSocketFactory> socketRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register(URIScheme.HTTPS.getId(), new SSLConnectionSocketFactory(sslContext))
					.register(URIScheme.HTTP.getId(), new PlainConnectionSocketFactory())
					.build();

		HttpClient httpClient = HttpClientBuilder.create()
					.setConnectionManager(new PoolingHttpClientConnectionManager(socketRegistry))
					.setConnectionManagerShared(true)
					.build();

		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		        
		
		return new RestTemplate(requestFactory);
	 }
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
