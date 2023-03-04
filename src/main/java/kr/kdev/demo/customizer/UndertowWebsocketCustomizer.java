package kr.kdev.demo.customizer;

import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * [WARN] UT026010: Buffer pool was not set on WebSocketDeploymentInfo, the default pool will be used
 * <p>
 * 위 경고 로그는 웹소켓에 대한 버퍼 풀이 적용되지 않았기에 기본 풀이 사용될 것이라는 것이며 로그 출력 레벨을 조정하거나
 * {@link WebServerFactoryCustomizer}를 통해 {@link io.undertow.websockets.jsr.WebSocketDeploymentInfo}를 주입할 수 있다.
 */
@Component
public class UndertowWebsocketCustomizer implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

    private final ServerProperties.Undertow undertow;

    public UndertowWebsocketCustomizer(final ServerProperties serverProperties) {
        this.undertow = serverProperties.getUndertow();
    }

    @Override
    public void customize(UndertowServletWebServerFactory factory) {
        factory.addDeploymentInfoCustomizers(deploymentInfo -> {
            boolean direct = this.undertow.getDirectBuffers() != null && this.undertow.getDirectBuffers();
            int bufferSize = this.undertow.getBufferSize() != null ? (int) this.undertow.getBufferSize().toBytes() : 1024;
            WebSocketDeploymentInfo webSocketDeploymentInfo = new WebSocketDeploymentInfo();
            webSocketDeploymentInfo.setBuffers(new DefaultByteBufferPool(direct, bufferSize));
            deploymentInfo.addServletContextAttribute("io.undertow.websockets.jsr.WebSocketDeploymentInfo", webSocketDeploymentInfo);
        });
    }
}
