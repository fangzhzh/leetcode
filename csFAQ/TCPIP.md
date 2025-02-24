# TCP/IP and the 5-Layer Model

## Overview of TCP/IP
TCP/IP (Transmission Control Protocol/Internet Protocol) is a set of communication protocols used for the Internet and similar networks. It defines how data is transmitted over networks and is the foundation of the Internet. TCP/IP allows different types of computers on various networks to communicate with each other.

TCP is a connection-oriented protocol, meaning that a connection is established before data is sent. Data is sent in packets, and each packet is routed independently through the network. This allows for efficient use of network resources and enables communication between devices on different networks.

TCP/IP is also a packet-switched protocol, meaning that data is broken into small packets and transmitted independently. Each packet contains source and destination IP addresses, as well as sequence numbers to ensure that packets are reassembled in the correct order.

## The 5-Layer Model
The TCP/IP model consists of five layers, each with its own specific functions:

1. **Application Layer**  
   This layer is responsible for providing network services to end-users. It includes protocols like HTTP, FTP, SMTP, and DNS. It enables applications to communicate over the network.

2. **Transport Layer**  
   This layer ensures reliable data transfer between hosts. It includes protocols like TCP (Transmission Control Protocol) for connection-oriented communication and UDP (User Datagram Protocol) for connectionless communication.

3. **Internet Layer**  
   This layer handles the routing of data packets across networks. It includes the IP (Internet Protocol) which is responsible for addressing and routing packets to their destination.

4. **Network Interface Layer**  
   This layer is responsible for the physical transmission of data over the network. It includes protocols that operate on the local network, such as Ethernet and Wi-Fi.

5. **Physical Layer**  
   This layer deals with the physical connection between devices, including the hardware technologies involved in transmitting raw bits over a physical medium.
### Story telling
* Open a webpage(application layer)
* TCP connect from client to server: (transport layer)
    * 3-way handshake (SYN → SYN-ACK → ACK)
* data is broken down into IP packets(including **IP addressing**) and **routing**, then sent over the network(internet layer)
* data transmitted over a physical network medium, wifi, ethernet (network interface layer)
* network card bit transmission (physical layer)
## Common Interview Questions
1. **What is TCP/IP?**  
   TCP/IP is a suite of communication protocols used to interconnect network devices on the Internet.

2. **What are the layers of the TCP/IP model?**  
   The layers are Application, Transport, Internet, Network Interface, and Physical.

3. **What is the difference between TCP and UDP?**  
   TCP is connection-oriented and ensures reliable data transfer, while UDP is connectionless and does not guarantee delivery.
