# 💻 RProtocol
- **RProtocol** is an API that provides communication between the server and client and flexible pipeline control for the network of your choice.

## 🌳 Features
- ### Packet Registration
 
  - Packet Register:
  ```java
  Packets.register(1,TestPacket.class);
  ```
  - Packet Unregister:
  ```java
  Packets.unregister(TestPacket.class);
  ```
  also,
  ```java
  Packets.unregister(1);
  ```
- **Packet Subscription:**
  
  -   
