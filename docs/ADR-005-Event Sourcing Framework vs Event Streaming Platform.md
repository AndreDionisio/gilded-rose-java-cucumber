Status: Accepted
Date: 2026‑05‑21

Context
We need to design a system that supports:

Strong domain modeling (DDD)

Aggregates with invariants

Event sourcing

Replayable projections

Distributed microservices

High‑throughput event distribution

Two categories of tools are being evaluated:

Axon Framework → event sourcing + CQRS + DDD

Kafka → distributed event streaming platform

These tools solve different problems, but teams often treat them as alternatives.

Decision
We explicitly distinguish between:

Event Sourcing Framework (Axon)

Event Streaming Platform (Kafka)

They are not interchangeable.

Rationale
Axon handles domain logic, aggregates, commands, sagas, consistency

Kafka handles scalable event distribution, retention, analytics

Axon is about how you model and store domain events

Kafka is about how you publish and consume events across systems

Consequences
We evaluate Axon and Kafka independently

We avoid misusing Kafka as an event store

We avoid misusing Axon Server as a streaming platform