package com.example.demo

import io.awspring.cloud.dynamodb.DynamoDbTemplate
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import java.util.*

@SpringBootApplication
class DemoApplication(
 val dynamoDbTemplate: DynamoDbTemplate
) {
	fun execute() {
		dynamoDbTemplate.save(Person(UUID.randomUUID().toString()))
	}
}

fun main(args: Array<String>) {
	val context: ConfigurableApplicationContext =runApplication<DemoApplication>(*args)
	val runner = context.getBean("demoApplication", DemoApplication::class.java)
	runner.execute()
}



@DynamoDbBean
data class Person(
var id: String? = null
) {

	@DynamoDbPartitionKey
	fun getPrimaryKey(): String? {
		return id
	}

	fun setPrimaryKey(id: String) {
		this.id = id
	}
}
