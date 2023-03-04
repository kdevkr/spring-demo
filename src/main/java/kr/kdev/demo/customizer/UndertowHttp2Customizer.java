package kr.kdev.demo.customizer;

import io.undertow.servlet.api.SecurityConstraint;
import io.undertow.servlet.api.SecurityInfo;
import io.undertow.servlet.api.TransportGuaranteeType;
import io.undertow.servlet.api.WebResourceCollection;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class UndertowHttp2Customizer
    implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

    private final ServerProperties serverProperties;
    private final int httpPort;

    public UndertowHttp2Customizer(final ServerProperties serverProperties,
                                   final Environment environment) {
        this.serverProperties = serverProperties;
        this.httpPort = environment.getProperty("server.http-port", Integer.class, 8080);
    }

    @Override
    public void customize(UndertowServletWebServerFactory factory) {
        factory.addBuilderCustomizers(builder -> builder.addHttpListener(this.httpPort, "0.0.0.0"));
        factory.addDeploymentInfoCustomizers(deploymentInfo -> deploymentInfo.addSecurityConstraint(
                new SecurityConstraint()
                    .addWebResourceCollection(new WebResourceCollection().addUrlPattern("/*"))
                    .setTransportGuaranteeType(TransportGuaranteeType.CONFIDENTIAL)
                    .setEmptyRoleSemantic(SecurityInfo.EmptyRoleSemantic.PERMIT))
            .setConfidentialPortManager(exchange -> serverProperties.getPort()));
    }
}
