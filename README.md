# 💻 RProtocol
- **RProtocol** is an API that provides communication between the server and client and flexible pipeline control for the network of your choice.

## 🌳 Features
- ### Packet Registration
 
  Packet Register:
  ```java
  Packets.register(1,TestPacket.class);
  ```
  Packet Unregister:
  ```java
  Packets.unregister(TestPacket.class);
  ```
  also,
  ```java
  Packets.unregister(1);
  ```
- ### Packet Subscription:
  
  With subscription, you can monitor the registered packets and trigger desired actions whenever a tracked packet arrives.
  ```java
  Packets.subscribe(TestPacket.class).handler(packet -> System.out.println(packet.getMessage()));
  ```   
  You can filter subscriptions.
  ```java
  Packets.subscribe(TestPacket.class)
   .filter((subscription, packet) -> "Hello world".equals(packet.getMessage()))
   .handler(packet -> System.out.println(packet.getMessage()));
  ```
  And many more.
  
- ### Pipeline Management:

  ```java
  PipelineFactory<SocketChannel> pipelineFactory = () -> {
            return new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    
                    pipeline.addLast(new ChannelReader(){
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            //logic
                        }

                        @Override
                        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                            //logic
                        }
                    });
                    
                    pipeline.addLast(new PacketEncoder());
                    pipeline.addLast(new PacketDecoder());
                }
            };
        };
  ```
