if [[ ${TRAVIS_PULL_REQUEST} = 'false' ]];
then 
    mvn deploy --settings ./settings.xml;
else 
    mvn clean verify;
fi