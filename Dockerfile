FROM tomcat:9
ADD target/recipe.war /usr/local/tomcat/webapps/
COPY ./src/main/resources/db/general.db /webapps/general.db
EXPOSE 8080
CMD ["catalina.sh", "run"]