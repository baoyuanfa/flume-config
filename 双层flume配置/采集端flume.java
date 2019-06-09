# Name the components on this agent
a1.sources = r1
a1.channels = c1
a1.sinkgroups = g1
a1.sinks = k1 k2

# Describe/configure the source
a1.sources.r1.type = TAILDIR
a1.sources.r1.channels = c1
a1.sources.r1.positionFile = /opt/module/flume/checkpoint/behavior/taildir_position.json
a1.sources.r1.filegroups = f1
a1.sources.r1.filegroups.f1 = /opt/module/flume/test-data/test.txt
a1.sources.r1.fileHeader = true

# Describe the channel
a1.channels.c1.type = file
a1.channels.c1.checkpointDir = /opt/module/flume/checkpoint/behavior
a1.channels.c1.dataDirs = /opt/module/flume/data/behavior/
a1.channels.c1.maxFileSize = 104857600
a1.channels.c1.capacity = 1000000
a1.channels.c1.keep-alive = 60

# round轮询
a1.sinkgroups.g1.processor.type = load_balance
a1.sinkgroups.g1.processor.backoff = true
a1.sinkgroups.g1.processor.selector = round_robin
a1.sinkgroups.g1.processor.selector.maxTimeOut=10000

# Describe the sink
a1.sinks.k1.type = avro
a1.sinks.k1.channel = c1
a1.sinks.k1.hostname = hadoop1
a1.sinks.k1.port = 2222

a1.sinks.k2.type = avro
a1.sinks.k2.channel = c1
a1.sinks.k2.hostname = hadoop2
a1.sinks.k2.port = 4444

# Bind the source and sink to the channel
a1.sources.r1.channels = c1
a1.sinkgroups.g1.sinks = k1 k2
a1.sinks.k1.channel = c1
a1.sinks.k2.channel = c1
