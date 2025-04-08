### 1. **Chat App**  
- **Protocols/Services:**  
  - WebSocket (real-time communication)
    - **Alternatives:** 
      - **Server-Sent Events (SSE)** - *Pros:* Simpler implementation, works over HTTP. *Cons:* One-way communication only, no native binary support.
      - **Long Polling** - *Pros:* Works with older browsers, simpler to implement. *Cons:* Higher latency, more server resources.
      - **gRPC** - *Pros:* Efficient binary protocol, strong typing. *Cons:* Limited browser support, more complex setup.
  
  - WebRTC (peer-to-peer media)
    - **Alternatives:**
      - **RTMP** - *Pros:* Well-established, good for streaming. *Cons:* Flash-based, becoming obsolete.
      - **HLS** - *Pros:* Works over HTTP, wide compatibility. *Cons:* Higher latency, not designed for real-time.
      - **SIP/RTP** - *Pros:* Industry standard for VoIP. *Cons:* Complex, higher overhead than WebRTC.
  
  - Redis (pub/sub for message delivery)
    - **Alternatives:**
      - **NATS** - *Pros:* Higher throughput, simpler architecture. *Cons:* Less mature ecosystem.
      - **RabbitMQ** - *Pros:* More features, better persistence. *Cons:* More complex, potentially higher latency.
      - **Apache Pulsar** - *Pros:* Multi-tenancy, geo-replication. *Cons:* Heavier, more complex deployment.
  
  - Kafka (event streaming)
    - **Alternatives:**
      - **Amazon Kinesis** - *Pros:* Managed service, easy scaling. *Cons:* Vendor lock-in, potentially costly.
      - **Google Pub/Sub** - *Pros:* Serverless, global availability. *Cons:* Higher latency than Kafka, vendor lock-in.
      - **Azure Event Hubs** - *Pros:* Managed service, AMQP support. *Cons:* Limited throughput tiers, vendor lock-in.
  
  - Push Notifications (APNs/FCM)
    - **Alternatives:**
      - **OneSignal** - *Pros:* Cross-platform, simpler API. *Cons:* Potential privacy concerns, third-party dependency.
      - **Pusher** - *Pros:* Real-time APIs, simpler integration. *Cons:* Subscription costs, third-party dependency.
      - **Custom MQTT** - *Pros:* Full control, lightweight. *Cons:* Must build infrastructure, no native OS integration.
  
  - End-to-End Encryption (Signal Protocol)
    - **Alternatives:**
      - **Matrix (Olm/Megolm)** - *Pros:* Open standard, supports group chats. *Cons:* Less widespread adoption.
      - **OTR (Off-the-Record)** - *Pros:* Well-established, simple. *Cons:* Limited to two-party conversations, no offline messaging.
      - **PGP** - *Pros:* Well-established, strong security. *Cons:* Complex key management, no forward secrecy.

### 2. **Instagram-like App** (Photo Sharing & Feed)  
- **Techniques/Services:**  
  - Image Caching (LRU, Memcached)
    - **Alternatives:**
      - **Redis** - *Pros:* More features, data structures. *Cons:* Higher memory usage.
      - **Varnish** - *Pros:* HTTP accelerator, designed for content. *Cons:* Less flexible than Memcached.
      - **CDN Edge Caching** - *Pros:* Distributed globally, offloads origin. *Cons:* Less control, potentially costly.
  
  - CDN (Cloudflare, Akamai)
    - **Alternatives:**
      - **Fastly** - *Pros:* Edge computing, programmable. *Cons:* More complex, potentially costly.
      - **AWS CloudFront** - *Pros:* Deep AWS integration. *Cons:* Higher latency in some regions.
      - **Self-hosted CDN** - *Pros:* Full control, potentially lower costs. *Cons:* Operational complexity, limited global reach.
  
  - Sharded Databases (user-based)
    - **Alternatives:**
      - **NoSQL (MongoDB, Cassandra)** - *Pros:* Horizontal scaling, flexible schema. *Cons:* Eventual consistency, complex queries.
      - **NewSQL (CockroachDB, TiDB)** - *Pros:* SQL interface with horizontal scaling. *Cons:* Newer technology, performance tradeoffs.
      - **Database-as-a-Service** - *Pros:* Managed scaling, reduced ops. *Cons:* Vendor lock-in, potentially costly.
  
  - Object Storage (S3 for images/videos)
    - **Alternatives:**
      - **Google Cloud Storage** - *Pros:* Strong consistency, global edge network. *Cons:* Different API than S3.
      - **Azure Blob Storage** - *Pros:* Tiered storage, geo-redundancy. *Cons:* Different API than S3.
      - **MinIO** - *Pros:* Self-hosted, S3-compatible. *Cons:* Operational overhead, limited features.
  
  - Feed Ranking (ML models, Redis for fan-out)
    - **Alternatives:**
      - **Apache Cassandra** - *Pros:* Linear scalability, time-series optimized. *Cons:* Complex operations, eventual consistency.
      - **ScyllaDB** - *Pros:* Higher performance than Cassandra. *Cons:* Smaller community, fewer tools.
      - **Custom in-memory solution** - *Pros:* Tailored to specific needs. *Cons:* Development effort, scaling challenges.
  
  - Elasticsearch (hashtags, users)
    - **Alternatives:**
      - **Algolia** - *Pros:* Managed service, optimized for UI. *Cons:* Potentially costly, less control.
      - **Apache Solr** - *Pros:* Mature, feature-rich. *Cons:* More complex setup than Elasticsearch.
      - **PostgreSQL with pg_trgm** - *Pros:* Unified database, simpler architecture. *Cons:* Limited scaling, less powerful search.


### 3. **Google Drive** (Cloud File Storage)  
- **Protocols/Services:**  
  - gRPC or REST APIs (file operations)
    - **Alternatives:**
      - **GraphQL** - *Pros:* Flexible queries, reduced over-fetching. *Cons:* Learning curve, complex caching.
      - **SOAP** - *Pros:* Strong contracts, enterprise support. *Cons:* Verbose, heavier payload.
      - **WebDAV** - *Pros:* HTTP-based, file system operations. *Cons:* Less efficient, limited features.
  
  - Chunked Upload/Resumable Upload (HTTP Range)
    - **Alternatives:**
      - **tus protocol** - *Pros:* Open standard, client libraries. *Cons:* Less widespread adoption.
      - **Multipart uploads** - *Pros:* Widely supported, parallel uploads. *Cons:* More complex state management.
      - **WebSockets for streaming** - *Pros:* Real-time progress, bidirectional. *Cons:* More complex, connection management.
  
  - Blob Storage (GCS/S3)
    - **Alternatives:**
      - **Azure Blob Storage** - *Pros:* Tiered storage, geo-redundancy. *Cons:* Different API than S3/GCS.
      - **Ceph** - *Pros:* Open-source, self-hosted. *Cons:* Operational complexity, resource intensive.
      - **Backblaze B2** - *Pros:* Lower cost, S3-compatible API. *Cons:* Fewer regions, less integrated.
  
  - Metadata Service (relational DB)
    - **Alternatives:**
      - **MongoDB** - *Pros:* Flexible schema, horizontal scaling. *Cons:* Complex queries, eventual consistency.
      - **DynamoDB** - *Pros:* Managed, auto-scaling. *Cons:* Limited query capabilities, vendor lock-in.
      - **Elasticsearch** - *Pros:* Full-text search, analytics. *Cons:* Not designed as primary database.
  
  - Versioning (immutable file chunks + metadata layer)
    - **Alternatives:**
      - **Git-like model** - *Pros:* Efficient for text, branching. *Cons:* Less efficient for binary files.
      - **Copy-on-write snapshots** - *Pros:* Space-efficient, fast. *Cons:* Complex implementation.
      - **Delta encoding** - *Pros:* Bandwidth efficient. *Cons:* CPU intensive, complex recovery.
  
  - Encryption at Rest & in Transit (TLS, AES)
    - **Alternatives:**
      - **ChaCha20-Poly1305** - *Pros:* Faster on mobile, no hardware acceleration needed. *Cons:* Less hardware support.
      - **Client-side encryption** - *Pros:* Zero-knowledge, provider can't access. *Cons:* Key management complexity.
      - **Hardware Security Modules** - *Pros:* Physical security, compliance. *Cons:* Costly, operational complexity.
  
  - Access Control (OAuth, IAM)
    - **Alternatives:**
      - **SAML** - *Pros:* Enterprise SSO integration, mature. *Cons:* XML-based, complex.
      - **JWT-based custom system** - *Pros:* Stateless, flexible. *Cons:* Token size, revocation challenges.
      - **RBAC with ACLs** - *Pros:* Fine-grained control. *Cons:* Complex management at scale.

### 4. **Google Docs** (Collaborative Editing)  
- **Techniques/Services:**  
  - Operational Transformation (OT) / Conflict-free Replicated Data Types (CRDT)
    - **Alternatives:**
      - **Differential Synchronization** - *Pros:* Simpler than OT, robust. *Cons:* Can be less efficient.
      - **JSON Patch/Merge** - *Pros:* Standardized, simpler. *Cons:* Limited conflict resolution.
      - **Version Vectors** - *Pros:* Detects causality violations. *Cons:* Grows with number of participants.
  
  - WebSocket or gRPC for real-time collaboration
    - **Alternatives:**
      - **Server-Sent Events** - *Pros:* Simpler, HTTP-based. *Cons:* One-way communication.
      - **MQTT** - *Pros:* Lightweight, pub/sub model. *Cons:* Requires broker, less web-native.
      - **Firebase Realtime Database** - *Pros:* Managed service, client libraries. *Cons:* Vendor lock-in, scaling limits.
  
  - Persistent Document Store (Cloud Spanner, MongoDB, etc.)
    - **Alternatives:**
      - **PostgreSQL with JSONB** - *Pros:* SQL + JSON, ACID. *Cons:* Scaling complexity.
      - **CouchDB** - *Pros:* Document-oriented, offline-first. *Cons:* Eventually consistent.
      - **FaunaDB** - *Pros:* Global distribution, ACID. *Cons:* Proprietary query language.
  
  - Change Logs (Append-only log, event sourcing)
    - **Alternatives:**
      - **Kafka** - *Pros:* Scalable, durable. *Cons:* Operational complexity.
      - **Blockchain/DLT** - *Pros:* Immutable, distributed. *Cons:* Performance overhead, complexity.
      - **Time-series DB** - *Pros:* Optimized for temporal data. *Cons:* Not designed for general event sourcing.
  
  - User Presence (Redis + pub/sub)
    - **Alternatives:**
      - **NATS** - *Pros:* Higher throughput, simpler. *Cons:* Less feature-rich than Redis.
      - **Socket.io** - *Pros:* Client libraries, fallbacks. *Cons:* JavaScript-focused.
      - **Firebase Presence** - *Pros:* Managed service, offline support. *Cons:* Vendor lock-in.

### 5. **YouTube/Netflix** (Video Streaming)  
- **Protocols/Services:**  
  - HLS/DASH (adaptive streaming)
    - **Alternatives:**
      - **WebRTC** - *Pros:* Lower latency, peer-to-peer capable. *Cons:* More complex, higher server load.
      - **RTMP** - *Pros:* Lower latency than HLS. *Cons:* Flash-based, limited browser support.
      - **SRT (Secure Reliable Transport)** - *Pros:* Low latency, error correction. *Cons:* Less widespread adoption.
  
  - CDN (Edge caching, Akamai/Cloudflare)
    - **Alternatives:**
      - **Multi-CDN strategy** - *Pros:* Redundancy, optimized delivery. *Cons:* Complex integration, cost.
      - **P2P delivery (WebRTC)** - *Pros:* Reduced server costs, scalability. *Cons:* Variable performance, privacy concerns.
      - **Self-hosted edge network** - *Pros:* Full control, potentially lower costs. *Cons:* Operational complexity.
  
  - Transcoding Pipelines (FFmpeg)
    - **Alternatives:**
      - **AWS Elemental MediaConvert** - *Pros:* Managed service, scalable. *Cons:* Vendor lock-in, potentially costly.
      - **GStreamer** - *Pros:* Flexible pipeline architecture. *Cons:* Steeper learning curve than FFmpeg.
      - **Hardware encoders** - *Pros:* Higher performance. *Cons:* Less flexible, costly.
  
  - Storage Tiering (SSD cache → S3 → Glacier)
    - **Alternatives:**
      - **Google Cloud Storage classes** - *Pros:* Integrated with GCP. *Cons:* Different API than AWS.
      - **Azure Blob Storage tiers** - *Pros:* Integrated with Azure. *Cons:* Different API than AWS.
      - **Hybrid (hot data on-prem, cold in cloud)** - *Pros:* Cost optimization, performance. *Cons:* Complex management.
  
  - User Watch History (Time-series DB / Redis)
    - **Alternatives:**
      - **Cassandra** - *Pros:* Linear scalability, time-series optimized. *Cons:* Complex operations.
      - **InfluxDB** - *Pros:* Purpose-built for time-series. *Cons:* Limited query capabilities.
      - **Elasticsearch** - *Pros:* Powerful analytics, search. *Cons:* Resource intensive.
  
  - ABR Logic (adaptive bitrate)
    - **Alternatives:**
      - **Buffer-based adaptation** - *Pros:* Responsive to actual conditions. *Cons:* Can be unstable.
      - **Hybrid (throughput + buffer)** - *Pros:* More stable, responsive. *Cons:* Complex implementation.
      - **ML-based prediction** - *Pros:* Can anticipate network changes. *Cons:* Training complexity, cold start.

### 6. **Uber/Lyft** (Ride Hailing App)  
- **Protocols/Services:**  
  - Real-time Location: MQTT/WebSocket
    - **Alternatives:**
      - **gRPC streams** - *Pros:* Efficient binary protocol, bidirectional. *Cons:* HTTP/2 requirement, complex setup.
      - **Server-Sent Events** - *Pros:* Simple HTTP-based protocol. *Cons:* One-way communication only.
      - **Firebase Realtime Database** - *Pros:* Managed service, offline support. *Cons:* Vendor lock-in, scaling limits.
  
  - Geospatial Indexing: S2, QuadTree, Geohash
    - **Alternatives:**
      - **R-tree** - *Pros:* Efficient for range queries. *Cons:* More complex updates.
      - **H3 (Uber's hexagonal system)** - *Pros:* Uniform cell sizes, hierarchical. *Cons:* Less common, learning curve.
      - **PostgreSQL PostGIS** - *Pros:* SQL integration, rich features. *Cons:* Scaling challenges.
  
  - ETA/Routing: Google Maps API, OSRM
    - **Alternatives:**
      - **Mapbox** - *Pros:* Customizable, competitive pricing. *Cons:* Less comprehensive data in some regions.
      - **HERE Maps** - *Pros:* Strong enterprise focus, offline capabilities. *Cons:* Less developer-friendly.
      - **Custom routing engine** - *Pros:* Full control, potentially lower costs. *Cons:* Development effort, data quality.
  
  - Matching System: Kafka for event streams
    - **Alternatives:**
      - **RabbitMQ** - *Pros:* Mature, feature-rich. *Cons:* Less scalable than Kafka.
      - **Redis Streams** - *Pros:* Simpler operations, lower latency. *Cons:* Less durable than Kafka.
      - **Amazon Kinesis** - *Pros:* Managed service, easy scaling. *Cons:* Vendor lock-in, potentially costly.
  
  - Payment: PCI-compliant services (Stripe, Adyen)
    - **Alternatives:**
      - **Braintree** - *Pros:* PayPal integration, mature. *Cons:* Less global coverage than Stripe.
      - **Square** - *Pros:* Integrated hardware options. *Cons:* Less international support.
      - **In-house payment processing** - *Pros:* Lower transaction fees, control. *Cons:* PCI compliance burden, development cost.
  
  - Push Notifications + SMS (Twilio, FCM)
    - **Alternatives:**
      - **OneSignal** - *Pros:* Cross-platform, simpler API. *Cons:* Third-party dependency.
      - **Amazon SNS** - *Pros:* AWS integration, scalable. *Cons:* Less feature-rich than dedicated services.
      - **Plivo/Nexmo** - *Pros:* Global SMS coverage. *Cons:* Can be costly at scale.

### 7. **Twitter/X** (Social Feed with Millions of Users)  
- **Techniques/Services:**  
  - Fan-out on Write/Read (Redis, Kafka)
    - **Alternatives:**
      - **Hybrid approach** - *Pros:* Optimized for different user types. *Cons:* Complex implementation.
      - **Cassandra** - *Pros:* Linear scalability, time-series optimized. *Cons:* Complex operations.
      - **Custom in-memory solution** - *Pros:* Tailored performance. *Cons:* Development effort, scaling challenges.
  
  - Timeline Cache (Redis, Memcached)
    - **Alternatives:**
      - **Aerospike** - *Pros:* Flash storage, high performance. *Cons:* Less widely used, learning curve.
      - **Couchbase** - *Pros:* Distributed caching, persistence. *Cons:* More complex than Redis/Memcached.
      - **Hazelcast** - *Pros:* In-memory data grid, distributed. *Cons:* Java-centric, operational complexity.
  
  - CDN for media
    - **Alternatives:**
      - **Multi-CDN approach** - *Pros:* Redundancy, optimized delivery. *Cons:* Complex integration.
      - **Edge computing platforms** - *Pros:* Customizable logic at edge. *Cons:* Development complexity.
      - **P2P content delivery** - *Pros:* Cost savings at scale. *Cons:* Variable performance, privacy concerns.
  
  - Rate Limiting (Token Bucket, NGINX/LB layer)
    - **Alternatives:**
      - **Redis-based rate limiting** - *Pros:* Centralized, flexible. *Cons:* Additional dependency.
      - **API Gateway services** - *Pros:* Managed service, integrated. *Cons:* Vendor lock-in, potentially costly.
      - **Application-level rate limiting** - *Pros:* Fine-grained control. *Cons:* Resource intensive.
  
  - Search: Elasticsearch, Lucene
    - **Alternatives:**
      - **Solr** - *Pros:* Mature, feature-rich. *Cons:* Less popular than Elasticsearch now.
      - **Algolia** - *Pros:* Managed service, UI-optimized. *Cons:* Potentially costly at scale.
      - **TypeSense** - *Pros:* Simpler than Elasticsearch, fast. *Cons:* Fewer features, smaller community.
  
  - Mentions & Hashtags: Inverted Index
    - **Alternatives:**
      - **Graph database (Neo4j)** - *Pros:* Natural fit for relationships. *Cons:* Scaling challenges, specialized.
      - **Trigram indexes (PostgreSQL)** - *Pros:* Integrated with relational DB. *Cons:* Less scalable than dedicated solution.
      - **Custom trie-based solution** - *Pros:* Memory efficient, fast prefix search. *Cons:* Complex implementation.

### 8. **Slack/Discord** (Team Messaging)  
- **Protocols/Services:**  
  - WebSocket (bi-directional real-time messages)
    - **Alternatives:**
      - **gRPC streams** - *Pros:* Efficient binary protocol, strong typing. *Cons:* HTTP/2 requirement, complex setup.
      - **MQTT** - *Pros:* Lightweight, pub/sub model. *Cons:* Requires broker, less web-native.
      - **Server-Sent Events + REST** - *Pros:* Simpler implementation. *Cons:* Not bidirectional, requires separate endpoints.
  
  - Event Bus (Kafka/NATS)
    - **Alternatives:**
      - **RabbitMQ** - *Pros:* Mature, feature-rich. *Cons:* Less scalable than Kafka.
      - **Amazon EventBridge** - *Pros:* Managed service, easy integration. *Cons:* Vendor lock-in.
      - **Redis Streams** - *Pros:* Simpler operations, lower latency. *Cons:* Less durable than Kafka.
  
  - Rate Limiting (API Gateway)
    - **Alternatives:**
      - **Custom middleware** - *Pros:* Tailored to specific needs. *Cons:* Development effort.
      - **Redis-based solution** - *Pros:* Centralized, flexible. *Cons:* Additional dependency.
      - **Service mesh (Istio)** - *Pros:* Consistent across services. *Cons:* Operational complexity.
  
  - Message Queue (RabbitMQ, Kafka)
    - **Alternatives:**
      - **Amazon SQS** - *Pros:* Managed service, simple. *Cons:* Limited throughput per queue.
      - **Google Pub/Sub** - *Pros:* Managed service, global. *Cons:* Higher latency than dedicated solution.
      - **ZeroMQ** - *Pros:* Lightweight, no broker required. *Cons:* Less durable, more development effort.
  
  - Media Upload (S3 + presigned URLs)
    - **Alternatives:**
      - **Google Cloud Storage** - *Pros:* Strong consistency, global edge network. *Cons:* Different API than S3.
      - **Azure Blob Storage** - *Pros:* Tiered storage, geo-redundancy. *Cons:* Different API than S3.
      - **Cloudflare Images/Stream** - *Pros:* Integrated CDN, processing. *Cons:* Limited customization.

### 9. **Dropbox** (File Sync)  
- **Protocols/Services:**  
  - Delta Sync Protocol (rsync-like)
    - **Alternatives:**
      - **WebDAV** - *Pros:* HTTP-based, widely supported. *Cons:* Less efficient than custom protocols.
      - **Git-like model** - *Pros:* Efficient for text, branching. *Cons:* Less efficient for binary files.
      - **Block-level sync** - *Pros:* Efficient for large files. *Cons:* Complex implementation.
  
  - Chunked Upload
    - **Alternatives:**
      - **tus protocol** - *Pros:* Open standard, client libraries. *Cons:* Less widespread adoption.
      - **Multipart uploads (S3)** - *Pros:* Widely supported, parallel uploads. *Cons:* More complex state management.
      - **BITS (Background Intelligent Transfer Service)** - *Pros:* Windows integration. *Cons:* Platform specific.
  
  - Local Watchers + Background Sync
    - **Alternatives:**
      - **Polling** - *Pros:* Simpler implementation, cross-platform. *Cons:* Less efficient, higher latency.
      - **Push notifications from server** - *Pros:* Real-time updates. *Cons:* Requires persistent connection.
      - **Hybrid approach** - *Pros:* Balanced efficiency and reliability. *Cons:* Complex implementation.
  
  - Conflict Resolution (last-writer-wins / UI merge)
    - **Alternatives:**
      - **Vector clocks** - *Pros:* Better causality tracking. *Cons:* Complex implementation, growing metadata.
      - **Operational Transformation** - *Pros:* Real-time collaboration possible. *Cons:* Complex implementation.
      - **CRDTs** - *Pros:* Automatic merging, offline-first. *Cons:* Larger metadata, complex implementation.
  
  - Encryption + Key Management
    - **Alternatives:**
      - **Client-side encryption** - *Pros:* Zero-knowledge, provider can't access. *Cons:* Key management complexity.
      - **HSM-based key management** - *Pros:* Hardware security, compliance. *Cons:* Costly, operational complexity.
      - **Threshold cryptography** - *Pros:* Distributed trust. *Cons:* Complex implementation, performance impact.
  
  - Webhooks for sync trigger
    - **Alternatives:**
      - **Long polling** - *Pros:* Works through firewalls. *Cons:* Server resource intensive.
      - **WebSockets** - *Pros:* Real-time, bidirectional. *Cons:* Connection management complexity.
      - **Server-Sent Events** - *Pros:* Simple HTTP-based protocol. *Cons:* One-way communication only.

### 10. **Spotify** (Music Streaming)  
- **Protocols/Services:**  
  - CDN + Caching Layers
    - **Alternatives:**
      - **Multi-CDN approach** - *Pros:* Redundancy, optimized delivery. *Cons:* Complex integration.
      - **Edge computing platforms** - *Pros:* Customizable logic at edge. *Cons:* Development complexity.
      - **ISP caching partnerships** - *Pros:* Reduced bandwidth costs. *Cons:* Complex negotiations, limited control.
  
  - MP3/OGG Streaming
    - **Alternatives:**
      - **AAC** - *Pros:* Better quality at same bitrate as MP3. *Cons:* Patent licensing, less universal support.
      - **FLAC** - *Pros:* Lossless quality. *Cons:* Larger file sizes, higher bandwidth.
      - **Opus** - *Pros:* Excellent quality/bitrate ratio. *Cons:* Less widespread adoption than MP3/AAC.
  
  - P2P (experimental in older versions)
    - **Alternatives:**
      - **WebRTC data channels** - *Pros:* Browser-native, NAT traversal. *Cons:* Complex signaling, privacy concerns.
      - **BitTorrent-like protocol** - *Pros:* Efficient distribution. *Cons:* ISP throttling, negative perception.
      - **Multicast (where supported)** - *Pros:* Network efficient. *Cons:* Limited support in public internet.
  
  - Metadata Store (artist, song info: Cassandra/Postgres)
    - **Alternatives:**
      - **MongoDB** - *Pros:* Flexible schema, horizontal scaling. *Cons:* Complex queries, eventual consistency.
      - **Redis** - *Pros:* In-memory performance, simple. *Cons:* Limited query capabilities, memory constraints.
      - **Elasticsearch** - *Pros:* Full-text search, analytics. *Cons:* Not designed as primary database.
  
  - Recommendation Engine (collaborative filtering, graph DB)
    - **Alternatives:**
      - **Content-based filtering** - *Pros:* Works without user data, explainable. *Cons:* Limited discovery, feature engineering.
      - **Deep learning models** - *Pros:* Can capture complex patterns. *Cons:* Black box, training complexity.
      - **Hybrid approaches** - *Pros:* Combines strengths of multiple methods. *Cons:* Complex implementation.
  
  - Search Index (Elasticsearch)
    - **Alternatives:**
      - **Solr** - *Pros:* Mature, feature-rich. *Cons:* Less popular than Elasticsearch now.
      - **Algolia** - *Pros:* Managed service, UI-optimized. *Cons:* Potentially costly at scale.
      - **PostgreSQL with pg_trgm** - *Pros:* Integrated with relational DB. *Cons:* Less scalable than dedicated solution.