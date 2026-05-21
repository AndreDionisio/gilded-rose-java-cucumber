Status: Accepted
Date: 2026‑05‑21
Context:  
Some services require:

low‑latency request/reply

guaranteed delivery of commands

routing patterns (topic, fanout, direct)

simple work queues

These are not event‑streaming use cases.

Decision:  
RabbitMQ may be used only for:

command queues

synchronous request/reply over AMQP

task distribution

workflows that do not require replay

Rationale:  
RabbitMQ excels at:

flexible routing

low latency

simple operational model

predictable delivery semantics

Consequences:

RabbitMQ must not be used for event logs

Kafka remains the system‑wide event backbone

Integration guidelines must clearly separate events vs commands