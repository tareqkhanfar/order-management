# Building the Application
This process involves compiling your source code into a format that can be executed. 
In the context of a Java application using Maven, you would do the following:

Open your terminal and navigate to the directory containing your application's source code.
Run the Maven command to build your application. This command will compile your code, run any tests,
and package your application into a JAR file.

mvn clean install

After running this command, you'll find the JAR file in the target directory of your project.

 Creating a Docker Image for Your Application
To create a Docker image, you need a Dockerfile. A Dockerfile is a text file that contains all the commands needed to build a Docker image.
Here's a basic Dockerfile for a Spring Boot application:

 Use an official OpenJDK runtime as a parent image
FROM openjdk:8-jdk-alpine

 Set the working directory in the container to /app
WORKDIR /app

 Copy the JAR file into the container at /app
COPY target/my-app.jar /app/my-app.jar

 Make port 8080 available to the world outside this container
EXPOSE 8080

 Run the JAR file 
ENTRYPOINT ["java","-jar","/app/my-app.jar"]

Replace my-app.jar with the name of your JAR file. Save this Dockerfile in the root directory of your project.

Next, build the Docker image by running the following command in your terminal:

docker build -t my-app .
Replace my-app with whatever you want to name your Docker image. The .
specifies that Docker should look for the Dockerfile in the current directory.

#Running the Docker Image
After you've built your Docker image, you can run it in a Docker container. Use the following command to do this:

docker run -p 8080:8080 -d my-app

This command tells Docker to run a new container with your image. The -p 8080:8080 option maps port 8080 in the container to port 8080 on your host machine. 
The -d option tells Docker to run the container in detached mode, which means it runs in the background.

After running this command, your application should be up and running in a Docker container. You can access it by going to http://localhost:8080 in your web browser.

