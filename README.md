# RProtocol

RProtocol is a Netty-based network protocol implementation. This project provides a framework for low-level network communication in Java, with support for custom packet structures, protocol management, and asynchronous operations.

## Features
- **Packet Encoder and Decoder**: Custom classes for encoding and decoding packets.
- **Pipeline Structure**: Leverage Netty's pipeline model to manage data flows.
- **Packet Registration and Subscription**: Register and handle incoming packets to the server.
- **Asynchronous Support**: Handle packets asynchronously, allowing for non-blocking I/O operations.
- **Flexible Design**: Extensible structures to create custom packets and protocols.

## Installation

Follow these steps to set up and use the project:

### Requirements
- Java 8 or higher
- Maven build system

### Maven Dependency

To add RProtocol to your project, include the following Maven dependency:

```xml
<dependency>
    <groupId>com.renzy</groupId>
    <artifactId>rprotocol</artifactId>
    <version>1.0.0</version>
</dependency>
```
