🟦 ADR‑001 — Event Streaming vs Message Queue
Status: Accepted
Date: 2026‑05‑21
Context:  
The system requires asynchronous communication between multiple microservices, with the ability to:

replay events

scale horizontally

support high throughput

integrate with analytics and real‑time processing

maintain an immutable audit trail

Decision:  
We adopt an event streaming architecture instead of a traditional message queue.

Rationale:

Event logs provide replayability

Decoupled producers/consumers

Enables CQRS, event sourcing, auditability

Supports real‑time analytics and stream processing

Better fit for distributed systems

Consequences:

Requires schema governance (Avro/Protobuf)

Requires DevOps maturity (brokers, partitions, retention)

Consumers must be idempotent