package com.kafka.demo.original

import java.util.Properties

import com.kafka.demo.KafkaHelper
import com.typesafe.scalalogging.Logger
import org.apache.kafka.clients.consumer.ConsumerConfig._
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG
import org.apache.kafka.common.serialization.StringDeserializer

/*
 * https://kafka.apache.org/20/javadoc/index.html?org/apache/kafka/clients/consumer/KafkaConsumer.html
 */
object Consumer {
  private[this] val logger = Logger(getClass.getSimpleName)

  private[this] val BOOTSTRAP_SERVERS_VALUE = "localhost:9092"
  private[this] val TOPIC_NAME = "topic-no-schema-original"
  private[this] val GROUP_ID_VALUE = "consumer-original"
  private[this] val TIMEOUT_MILLS = 100

  private[this] def newConsumer(): KafkaConsumer[String, String] = {
    val props = new Properties()
    props.put(BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_VALUE)
    props.put(GROUP_ID_CONFIG, GROUP_ID_VALUE)
    props.put(ENABLE_AUTO_COMMIT_CONFIG, "true")
    props.put(AUTO_COMMIT_INTERVAL_MS_CONFIG, "100")
    props.put(KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    props.put(VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    new KafkaConsumer(props)
  }

  def main(args: Array[String]): Unit =
    KafkaHelper.consume(newConsumer(), TOPIC_NAME, TIMEOUT_MILLS)

}
