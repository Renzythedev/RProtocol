# RProtocol

RProtocol is a Netty-based network protocol implementation. This project provides a framework for low-level network communication in Java, with support for custom packet structures, protocol management, and asynchronous operations.

## Features
- **Packet Encoder and Decoder**: Custom classes for encoding and decoding packets.
- **Pipeline Structure**: Leverage Netty's pipeline model to manage data flows.
- **Packet Registration and Subscription**: Register and handle incoming packets to the server.
- **Asynchronous Support**: Handle packets asynchronously, allowing for non-blocking I/O operations.
- **Flexible Design**: Extensible structures to create custom packets and protocols.

### Maven Dependency

To add RProtocol to your project, include the following Maven dependency:

```xml
<dependency>
    <groupId>me.renzy</groupId>
    <artifactId>Protocol</artifactId>
    <version>1.1</version>
</dependency>
```
