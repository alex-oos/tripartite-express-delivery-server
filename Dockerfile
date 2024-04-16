# 找一个包含jdk 和maven的镜像，然后替换即可
FROM micr.cloud.mioffice.cn/hdmap-namespace/java-openjdk8-centos7.3:v1.0
ARG env
#该变量是需要传递，指定是哪一个环境
WORKDIR /root/workspace
COPY . /root/workspace

RUN cd /root/workspace && mvn -B clean install -P${env} -Dmaven.test.skip=true -Dautoconfig.skip -pl tripartite-express-delivery-server -am && cp -r tripartite-express-delivery-server/target target && ls -lah target/

CMD java -Dopentelemetry.javaagent.slf4j.simpleLogger.defaultLogLevel=WARN  -Dfile.encoding=utf-8 -jar target/tripartite-express-delivery-server-1.0-SNAPSHOT.jar
