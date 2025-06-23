package org.kob.kob.consumer;

import com.alibaba.fastjson2.JSONObject;
import jakarta.websocket.OnOpen;
import jakarta.websocket.server.ServerEndpoint;
import org.kob.kob.consumer.utils.Game;
import org.kob.kob.consumer.utils.JwtAuthentication;
import org.kob.kob.mapper.UserMapper;
import org.kob.kob.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket/{token}")  // WebSocket服务端点，通过/token传递用户标识
public class WebSocketServer {

    // 存储在线用户及其对应的WebSocket连接
    final private static ConcurrentHashMap<Integer, WebSocketServer> users = new ConcurrentHashMap<>();

    private static CopyOnWriteArraySet<User> matchpool = new CopyOnWriteArraySet<>();
    // 当前连接的用户信息
    private User user;
    
    // WebSocket会话对象，用于与客户端通信
    private Session session = null;

    // UserMapper静态变量，用于数据库操作
    private static UserMapper userMapper;


    // 使用Spring的@Autowired注入UserMapper，并赋值给静态变量
    @Autowired
    public void setUserMapper(UserMapper userMapper){
        WebSocketServer.userMapper = userMapper;
    }

    // 客户端建立连接时触发的方法
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        this.session = session;  // 初始化会话对象

        System.out.println("connected!");  // 日志输出：已连接

        Integer userId = JwtAuthentication.getUserId(token);  // 解析token为用户ID
        this.user = userMapper.selectById(userId);  // 查询用户信息
        if (this.user != null) {
            users.put(userId, this);  // 将用户加入在线列表
        }else{
            this.session.close();
        }
        System.out.println(users);
    }

    // 客户端断开连接时触发的方法
    @OnClose
    public void onClose() {
        System.out.println("disconnected!");  // 日志输出：已断开
        
        if(this.user != null){
            users.remove(this.user.getId());  // 从在线列表中移除当前用户
            matchpool.remove(this.user);
        }
    }

    // 接收到客户端消息时触发的方法
    private  void startMatching(){
        System.out.println("start matching!");
        matchpool.add(this.user);

        while(matchpool.size() >= 2){
            Iterator<User> it = matchpool.iterator();
            User a = it.next();
            User b = it.next();
            matchpool.remove(a);
            matchpool.remove(b);

            Game game = new Game(13, 14, 20);
            game.createMap();

            JSONObject resp = new JSONObject();
            resp.put("event","start-matching");
            resp.put("opponent_username",b.getUsername());
            resp.put("opponent_photo",b.getPhoto());
            resp.put("gamemap",game.getG());
            users.get(a.getId()).sendMessage(resp.toJSONString());
            JSONObject resp2 = new JSONObject();
            resp2.put("event","start-matching");
            resp2.put("opponent_username",a.getUsername());
            resp2.put("opponent_photo",a.getPhoto());
            resp2.put("gamemap",game.getG());
            users.get(b.getId()).sendMessage(resp2.toJSONString());
        }
    }
    private  void stopMatching(){
        System.out.println("stop matching!");
        matchpool.remove(this.user);
    }
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("received message!");  // 日志输出：收到消息
        JSONObject data = JSONObject.parseObject(message);
        String event = data.getString("event");
        if("start-matching".equals(event)){
            startMatching();
        }
        else if("stop-matching".equals(event)){
            stopMatching();
        }
    }

    // 发生错误时触发的方法
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();  // 打印错误堆栈信息
    }

    // 向客户端发送消息的方法
    public void sendMessage(String message) {
        synchronized (this.session) {  // 同步锁，确保线程安全
            try {
                this.session.getAsyncRemote().sendText(message);  // 异步发送文本消息
            } catch (Exception e) {
                e.printStackTrace();  // 异常处理
            }
        }
    }
}
