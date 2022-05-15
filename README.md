# Information Service
This is the implementation of a service for a university to maintain data
on its professors, courses, departments and course schedules.

There are 4 different tables for 4 different resources:
department, professor, course, and
schedule.

The functionality is create, read by id and delete by id for all 4 tables. Additionally, there is an endpoint for
getting all professors with their respective courses with their names.

It is a SpringBoot application implemented with Java, Maven and PostgreSQL database.

**Build/Run**
- 
In order to run the application, first
`mvn clean install -DskipTests:true`
command should be run. The application is containerized. So using `docker-compose up`
command is sufficient to run the application. There is also a Swagger API documentation
After running the application it can be seen under `http://localhost:8080/swagger-ui.html`
link. 

**Design Considerations**
- 
When designing the application, I chose not to use any ORMs like Hibernate since the application itself
does not require any associations and is not a complex application. Other point that I had considered
was the performance. The start up performance of JDBC is better than Hibernate hence has faster development
performance.

Decided to use SpringBoot framework since dependency management is easier with SpringBoot.
It packages required third-party dependencies through starter packages in one place, makes it
easier to start working right away. It also enables the automatic configuration which can be
changed any time by the developer.

Other design decision that I made was containerizing the application using Docker. Containerization
makes the start up easier, provides an isolated environment for the application, makes it platform independent and 
provides high scalability.

I also used Swagger to be able to provide REST API documentation and make it more human readable.
It is easier to test and debug.


**Next steps/Improvements**
- 
- Authentication/authorization can and should be implemented if the data is sensitive. Without using them,
the data is vulnerable.
- An API Gateway can be implemented for routing the requests from client side to microservices. It can include security,
caching, monitoring, etc... as well.
- Load Balancer can also be implemented to distribute the application traffic among multiple servers. It helps prevent
failures, if the traffic is very high the load balancer ensures that the application is not slowing down. All these 
things enables handling user requests as fast as possible.
- Using a cache mechanism such as Redis also improves performance. There will be no need to query the database for 
every get method. It can also be analyzed the frequency of the change in data in order to understand when to invalidate 
the data and create the cache again.
- Kubernetes/Openshift platforms can be used to orchestrate containers and managing workloads.


**Scalability**
- 
By definition, scalability is the ability of the application to adapt; increase or decrease
in cost and/or in performance in order to meet the demands of the system that it runs.
By using Docker and containerizing the application, I tried to increase the scalability.



**Performance**
- 
As I mentioned above, I did not use any ORMs since the application does not require any complex transactions
and wanted to make the start up and the overall performance of the application faster.
As I said in the Improvements section, a load balancer can be added to distribute the traffic among servers to improve 
the performance.
Monitoring the activities using a third-party tool is also something to do in order to improve performance. 
It enables us to quickly spot the bugs and fix them.

**Unit tests / Coverage**
- 

I have included unit tests and the coverage is near 90%. Unit tests are important for the overall quality
of the application. It shows developers that it meets the standards before deployment, helps to find minor bugs
and be able to write better code, helps agile process because it is easier to change the code if necessary.
These benefits help reduce the cost as well.