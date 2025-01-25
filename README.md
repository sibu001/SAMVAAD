>  **Online Code Editor Feature Setup:**

1. In this project we have a functionality of code editor, for that we are using judge0 apis to run and compile code  

Below is the setup instruction of judge0 application



1. Install Docker and Docker Compose.
1. Download and extract the release archive:
  > *  wget https://github.com/judge0/judge0/releases/download/v1.13.0/judge0-v1.13.0.zip
  > *  unzip judge0-v1.13.0.zip
3. Run all services and wait a few seconds until everything is initialized:
  > * cd judge0-v1.13.0
  > * docker-compose up -d db redis
  > * sleep 10s
  > * docker-compose up -d
  > * sleep 5s
4. Your instance of Judge0 CE v1.13.0 is now available at http://\<IP ADDRESS OF YOUR SERVER\>:2358.


For more information visit  : https://github.com/judge0/judge0/blob/master/CHANGELOG.md#deployment-procedure
