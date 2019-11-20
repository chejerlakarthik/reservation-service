# reservation-service
Spring-Boot App using Kotlin

Spring Boot App using Kotlin and the usual Spring annotations.

Idea is to slowly translate this app to use zero annotations and wire the dependencies manually by instantiating 
a Spring ApplicationContext ourselves.

Spring (and Spring Boot) uses a lot of annotations which use runtime reflection to make things work, almost magically.
However, when things do not work, it can be very hard to debug. It is important for the application developers to be in 
absolute control over the control flow, the boot-strapping and execution of the application code and not rely on 
framework magic so much as we do when using the Spring annotations.

Another motivation to go annotation-less is to regain the benefits of using a statically typed language when 
developing code for a JVM. Annotations take away the compile-time type safety away from us.
