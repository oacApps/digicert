
Step 1: Download repo from https://github.com/oacApps/digicert.git
Step 2: Build the application using command "mvn clean install" or from IDE
Step 3: Run "docker build -t digicert/usermanagement:1.0.1 ." command to build docker images
Step 4: Run "docker-compose up" command to run application 
Setp 5: Browse http://localhost:9000/digicert/swagger-ui/ for swagger. You will be able to test all rest end point from there.

