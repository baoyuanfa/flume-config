# Name the components on this agent
a2.sources = r1
a2.sinks = k1
a2.channels = c1

# Describe/configure the source
a2.sources.r1.type = avro
a2.sources.r1.bind = hadoop1
a2.sources.r1.port = 2222

# Describe the sink
a2.sinks.k1.type = org.apache.flume.sink.kafka.KafkaSink
a2.sinks.k1.topic = log-analysis
a2.sinks.k1.brokerList = hadoop1:9092,hadoop2:9092,hadoop3:9092
a2.sinks.k1.requiredAcks = 1
a2.sinks.k1.kafka.producer.type = sync
a2.sinks.k1.batchSize = 1


# Describe the channel
a2.channels.c1.type = memory
a2.channels.c1.capacity = 1000
a2.channels.c1.transactionCapacity = 100

# Bind the source and sink to the channel
a2.sources.r1.channels = c1
a2.sinks.k1.channel = c1
