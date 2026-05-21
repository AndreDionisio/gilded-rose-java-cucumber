Status: Accepted
Date: 2026‑05‑21

Context
We need:

Axon for domain‑driven event sourcing

Kafka for distributed event streaming

These two systems must coexist.

Decision
We adopt a dual‑event‑pipeline strategy:

Axon Event Store

Stores domain events

Used for replay, aggregates, sagas

Kafka Topics

Receive published domain events

Used for integration, analytics, downstream consumers

Rationale
Axon events represent domain truth

Kafka events represent integration messages

Publishing Axon events to Kafka is a standard pattern

Keeps domain model clean while enabling scalable distribution

Consequences
Requires an event publisher from Axon → Kafka

Kafka consumers must treat events as immutable facts

Axon remains the source of truth

Kafka becomes the distribution layer