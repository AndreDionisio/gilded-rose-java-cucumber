Status: Rejected
Date: 2026‑05‑21
Context:  
RabbitMQ is a mature message broker widely used for:

work queues

routing patterns

low‑latency messaging

transactional messaging

But the system requires:

event replay

long‑term retention

partitioned scalability

event log semantics

Decision:  
RabbitMQ is not selected as the event streaming platform.

Rationale:

No native event replay

No durable event log

Scaling is limited compared to Kafka

Message ordering not guaranteed under load

Not designed for high‑volume event ingestion

Lacks ecosystem for stream processing

Consequences:

RabbitMQ may still be used for command queues, RPC, or task distribution

Teams must avoid using RabbitMQ for event‑driven workflows