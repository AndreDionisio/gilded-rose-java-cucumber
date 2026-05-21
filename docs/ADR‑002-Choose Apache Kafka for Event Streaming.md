Status: Accepted
Date: 2026‑05‑21
Context:  
We need a platform for:

high‑volume event ingestion

long‑term retention

replay

partitioning for horizontal scalability

integration with Spark/Flink/KSQL

strong ordering guarantees per partition

Decision:  
We choose Apache Kafka as the event streaming backbone.

Rationale:

High throughput (millions of msgs/sec)

Horizontal scalability via partitions

Replayable event log

Strong ordering within partitions

Long retention (hours → years)

Ecosystem: Kafka Connect, Schema Registry, KSQL, Streams API

Exactly‑once semantics supported

Native support for event‑driven architectures

Consequences:

Operational complexity (brokers, Zookeeper/Kraft, partitions)

Requires schema evolution strategy

Requires consumer offset management

Not ideal for request/reply or RPC patterns