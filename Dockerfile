FROM tomcat:9
ADD target/recipe.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]