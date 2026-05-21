Status: Accepted
Date: 2026‑05‑21

Context
The system must integrate with:

External systems

Analytics pipelines

Real‑time dashboards

Downstream consumers

High‑volume event ingestion

Axon Server is not designed for:

High‑throughput streaming

Long‑term retention

Partitioned scalability

Multi‑consumer fan‑out

Analytics workloads

Decision
We adopt Apache Kafka as the event streaming backbone.

Rationale
Kafka provides:

High throughput

Horizontal scalability via partitions

Long retention

Replay for analytics

Multiple consumer groups

Integration ecosystem (Connect, Streams, Flink, Spark)

Axon Server does not provide:

Partitioning

Multi‑consumer fan‑out

Stream processing

Long‑term retention

Consequences
Kafka is used for integration events

Axon events may be published to Kafka

Kafka is not used as an event store for aggregates