Status: Accepted
Date: 2026‑05‑21

Context
The domain requires:

Aggregates with strong invariants

Command handling

Event sourcing

Sagas for long‑running workflows

Replayable projections

Clean separation of command vs query models

Decision
We adopt Axon Framework for:

Aggregates

Commands

Events

Sagas

Event sourcing

CQRS projections

Rationale
Axon provides:

Aggregate lifecycle management

Command bus with routing

Event sourcing handlers

Sagas for distributed workflows

Replayable projections

DDD‑aligned architecture

Zero boilerplate for CQRS/ES

Kafka does not provide:

Aggregates

Command routing

Sagas

Event sourcing semantics

Consequences
Domain events are stored in Axon’s event store (Axon Server or RDBMS)

Projections can be rebuilt at any time

Teams follow DDD patterns

Requires training in Axon concepts