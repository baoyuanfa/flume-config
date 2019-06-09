#消费kafka数据的flume
a4.sources = r1
a4.sinks = k1
a4.channels = c1

a4.sources.r1.type = org.apache.flume.source.kafka.KafkaSource
a4.sources.r1.batchSize = 1000
a4.sources.r1.kafka.bootstrap.servers = hadoop1:9092,hadoop2:9092,hadoop3:9092
a4.sources.r1.kafka.topics = log-analysis
a4.sources.r1.kafka.consumer.group.id = test
a4.sources.r1.interceptors = i1
a4.sources.r1.interceptors.i1.type = timestamp

a4.sinks.k1.type=hdfs
a4.sinks.k1.hdfs.path=hdfs://hadoop1:9000/flume/%Y%m%d
a4.sinks.k1.hdfs.fileType=DataStream
a4.sinks.k1.hdfs.writeFormat=TEXT
# 每600秒滚动一个文件
a4.sinks.k1.hdfs.rollInterval=60
# 每128M滚动一个文件
a4.sinks.k1.hdfs.rollSize=134217728
a4.sinks.k1.hdfs.rollCount=0
# 每次拉取1000个event写入HDFS
a4.sinks.k1.hdfs.batchsize=1000
a4.sinks.k1.hdfs.threadsPoolSize=16
a4.sinks.k1.hdfs.filePrefix=flume.%Y%m%d%H%M
a4.sinks.k1.hdfs.idelTimeout=600

a4.sinks.k1.hdfs.round=true
a4.sinks.k1.hdfs.roundValue=10
a4.sinks.k1.hdfs.roundUnit= minute

# Describe the channel
a4.channels.c1.type = memory
a4.channels.c1.capacity = 1000
a4.channels.c1.transactionCapacity = 100

# Bind the source and sink to the channel
a4.sources.r1.channels = c1
a4.sinks.k1.channel = c1
