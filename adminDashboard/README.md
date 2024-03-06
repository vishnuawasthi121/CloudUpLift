#Admin UI

http://localhost:8091/#/applications



http://localhost:8091/#/applications/spring-boot-application

The comment by @the hand of NOD made me relook into my pom.xml file and the pom.xml file provided in the blog

The problem lies in the version number of the spring-boot-starter-parent. The tutorial uses a version 2.1.0.RELEASE whereas the latest one when you prepare a project from Spring Intializr is 2.2.1.RELEASE

I downgraded to 2.1.0.RELEASE and it worked! With 2.2.1.RELEASE there is some dependency that is going cyclic with spring-boot-admin-starter-server

P.S. It seems that the codecentric team is already working upon it and would release the 2.2.0 compliant version by 22 Nov 2019 (https://github.com/codecentric/spring-boot-admin/milestones)