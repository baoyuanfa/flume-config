# Name the components on this agent
a3.sources = r1
a3.sinks = k1
a3.channels = c2

# Describe/configure the source
a3.sources.r1.type = avro
a3.sources.r1.bind = hadoop2
a3.sources.r1.port = 4444

# Describe the sink
a3.sinks.k1.type = org.apache.flume.sink.kafka.KafkaSink
a3.sinks.k1.topic = log-analysis
a3.sinks.k1.brokerList = hadoop1:9092,hadoop2:9092,hadoop3:9092
a3.sinks.k1.requiredAcks = 1
a3.sinks.k1.kafka.producer.type = sync
a3.sinks.k1.batchSize = 1

# Describe the channel
a3.channels.c2.type = memory
a3.channels.c2.capacity = 1000
a3.channels.c2.transactionCapacity = 100

# Bind the source and sink to the channel
a3.sources.r1.channels = c2
a3.sinks.k1.channel = c2
