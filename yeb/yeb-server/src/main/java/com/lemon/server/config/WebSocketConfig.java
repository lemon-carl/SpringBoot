package com.lemon.server.config;

import com.lemon.server.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket 配置类
 *
 * @author: Lemon
 * @Date : 2021/7/11 10:36
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 添加 Endpoint ，则网页可以通过 WebSockect 连接上服务，
     * 也就是配置websocket的服务地址，并指定是否使用SocketJS
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 1.将 /ws/ep 路径注册为 stomp 的端点，用户连接这个端点就可以进行websocket通讯，支持 SockJS
        // 2. setAllowedOrigins("*") 允许跨域
        // 3. withSockJS() 支持 sockJS访问
        registry.addEndpoint("/ws/ep").setAllowedOrigins("http://localhost:8081").withSockJS();
    }

    /**
     * 输入通道参数配置
     *
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {

                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

                // 判断是否连接，如果是，需要获取token，并设置用户对象
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String token = accessor.getFirstNativeHeader("Auth-Token");
                    if (StringUtils.hasLength(token)) {
                        String authToken = token.substring(tokenHead.length());
                        String userName = jwtTokenUtil.getUserNameFromToken(authToken);
                        // token 中存在用户名
                        if (StringUtils.hasLength(userName)) {
                            // 登录
                            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                            // 验证token是否有效，重新设置用户对象
                            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                                System.out.println("---------------连接上了------------------------");
                                UsernamePasswordAuthenticationToken authenticationToken =
                                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                                // 获取 Spring security 全局上下文 设置Authentication 更新当前登录用户对象
                                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                                accessor.setUser(authenticationToken);
                            }
                        }
                    }
                }
                return message;
            }
        });
    }

    /**
     * 配置消息代理
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 配置代理域，可以配置多个，配置代理目的地的前缀为 /queue,可以在配置域上向客户端推送消息
        registry.enableSimpleBroker("/queue");
    }
}
